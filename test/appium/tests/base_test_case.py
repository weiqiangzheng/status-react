import pytest
import sys
import re
import subprocess
import asyncio

from os import environ
from appium import webdriver
from abc import ABCMeta, abstractmethod
from selenium.common.exceptions import WebDriverException
from tests import test_suite_data, api_requests
from tests.conftest import get_run_count
from support.test_rerun import is_infrastructure_error
from views.base_view import BaseView


class AbstractTestCase:

    __metaclass__ = ABCMeta

    @property
    def sauce_username(self):
        return environ.get('SAUCE_USERNAME')

    @property
    def sauce_access_key(self):
        return environ.get('SAUCE_ACCESS_KEY')

    @property
    def executor_sauce_lab(self):
        return 'http://%s:%s@ondemand.saucelabs.com:80/wd/hub' % (self.sauce_username, self.sauce_access_key)

    @property
    def executor_local(self):
        return 'http://localhost:4723/wd/hub'

    def print_sauce_lab_info(self, driver):
        sys.stdout = sys.stderr
        print("SauceOnDemandSessionID=%s job-name=%s" % (driver.session_id,
                                                         pytest.config.getoption('build')))

    def add_local_devices_to_capabilities(self):
        updated_capabilities = list()
        raw_out = re.split(r'[\r\\n]+', str(subprocess.check_output(['adb', 'devices'])).rstrip())
        for line in raw_out[1:]:
            serial = re.findall(r"(([\d.\d:]*\d+)|\bemulator-\d+)", line)
            if serial:
                capabilities = self.capabilities_local
                capabilities['udid'] = serial[0][0]
                updated_capabilities.append(capabilities)
        return updated_capabilities

    @property
    def capabilities_sauce_lab(self):
        desired_caps = dict()
        desired_caps['app'] = 'sauce-storage:' + test_suite_data.apk_name

        desired_caps['build'] = pytest.config.getoption('build')
        desired_caps['name'] = test_suite_data.current_test.name
        desired_caps['platformName'] = 'Android'
        desired_caps['appiumVersion'] = '1.7.2'
        desired_caps['platformVersion'] = '7.1'
        desired_caps['deviceName'] = 'Android GoogleAPI Emulator'
        desired_caps['deviceOrientation'] = "portrait"
        desired_caps['commandTimeout'] = 600
        desired_caps['idleTimeout'] = 1000
        desired_caps['unicodeKeyboard'] = True
        desired_caps['automationName'] = 'UiAutomator2'
        desired_caps['setWebContentDebuggingEnabled'] = True
        desired_caps['ignoreUnimportantViews'] = False
        return desired_caps

    def update_capabilities_sauce_lab(self, key, value):
        caps = self.capabilities_sauce_lab.copy()
        caps[key] = value
        return caps

    @property
    def capabilities_local(self):
        desired_caps = dict()
        desired_caps['app'] = pytest.config.getoption('apk')
        desired_caps['deviceName'] = 'nexus_5'
        desired_caps['platformName'] = 'Android'
        desired_caps['appiumVersion'] = '1.7.2'
        desired_caps['platformVersion'] = '7.1'
        desired_caps['newCommandTimeout'] = 600
        desired_caps['fullReset'] = False
        desired_caps['unicodeKeyboard'] = True
        desired_caps['automationName'] = 'UiAutomator2'
        desired_caps['setWebContentDebuggingEnabled'] = True
        return desired_caps

    @abstractmethod
    def setup_method(self, method):
        raise NotImplementedError('Should be overridden from a child class')

    @abstractmethod
    def teardown_method(self, method):
        raise NotImplementedError('Should be overridden from a child class')

    @property
    def environment(self):
        return pytest.config.getoption('env')

    @property
    def implicitly_wait(self):
        return 8

    errors = []

    def verify_no_errors(self):
        if self.errors:
            pytest.fail('. '.join([self.errors.pop(0) for _ in range(len(self.errors))]))


class SingleDeviceTestCase(AbstractTestCase):

    @property
    def capabilities(self):
        return {
            'local': {
                'executor': self.executor_local,
                'capabilities': self.capabilities_local
            },
            'sauce': {
                'executor': self.executor_sauce_lab,
                'capabilities': self.capabilities_sauce_lab
            }
        }

    def start_driver(self, rerun_count):
        for i in range(rerun_count):
            try:
                driver = webdriver.Remote(self.capabilities[self.environment]['executor'],
                                          self.capabilities[self.environment]['capabilities'])
                self.driver.implicitly_wait(self.implicitly_wait)
                BaseView(self.driver).accept_agreements()
                test_suite_data.current_test.testruns[-1].jobs.append(self.driver.session_id)
                return driver
            except WebDriverException as exception:
                if not is_infrastructure_error(exception.msg):
                    raise exception
                if i == (get_run_count() - 1):
                    raise exception

    def setup_method(self, method):
        self.driver = self.start_driver(rerun_count=get_run_count())

    def teardown_method(self, method):
        if self.environment == 'sauce':
            self.print_sauce_lab_info(self.driver)
        try:
            self.driver.quit()
        except (WebDriverException, AttributeError):
            pass


class LocalMultipleDeviceTestCase(AbstractTestCase):

    def setup_method(self, method):
        self.drivers = dict()

    def start_drivers(self, quantity):
        capabilities = self.add_local_devices_to_capabilities()
        for driver in range(quantity):
            self.drivers[driver] = webdriver.Remote(self.executor_local, capabilities[driver])
            self.drivers[driver].implicitly_wait(self.implicitly_wait)
            BaseView(self.drivers[driver]).accept_agreements()
            test_suite_data.current_test.testruns[-1].jobs.append(self.drivers[driver].session_id)

    def teardown_method(self, method):
        for driver in self.drivers:
            try:
                self.drivers[driver].quit()
            except WebDriverException:
                pass


class SauceMultipleDeviceTestCase(AbstractTestCase):

    @classmethod
    def setup_class(cls):
        cls.loop = asyncio.new_event_loop()
        asyncio.set_event_loop(cls.loop)

    def setup_method(self, method):
        self.drivers = dict()

    def start_drivers(self, quantity=2):
        if self.__class__.__name__ == 'TestOfflineMessages':
            capabilities = self.update_capabilities_sauce_lab('platformVersion', '6.0')
        else:
            capabilities = self.capabilities_sauce_lab
        self.drivers = self.loop.run_until_complete(self.create_drivers_in_threads(quantity, webdriver.Remote,
                                                                                   self.drivers,
                                                                                   self.executor_sauce_lab,
                                                                                   capabilities))
        for driver in range(quantity):
            self.drivers[driver].implicitly_wait(self.implicitly_wait)
            BaseView(self.drivers[driver]).accept_agreements()
            test_suite_data.current_test.testruns[-1].jobs.append(self.drivers[driver].session_id)

    def teardown_method(self, method):
        for driver in self.drivers:
            try:
                self.print_sauce_lab_info(self.drivers[driver])
                self.drivers[driver].quit()
            except (WebDriverException, AttributeError):
                pass

    @asyncio.coroutine
    def create_drivers_in_threads(self, quantity: int, func: type, returns: dict, *args):
        loop = asyncio.get_event_loop()
        for i in range(quantity):
            returns[i] = loop.run_in_executor(None, func, *args)
        for k in returns:
            returns[k] = yield from returns[k]
        return returns

    @classmethod
    def teardown_class(cls):
        cls.loop.close()


environments = {'local': LocalMultipleDeviceTestCase,
                'sauce': SauceMultipleDeviceTestCase}


class MultipleDeviceTestCase(environments[pytest.config.getoption('env')]):

    def setup_method(self, method):
        super(MultipleDeviceTestCase, self).setup_method(method)
        self.senders = dict()

    def teardown_method(self, method):
        for user in self.senders:
            api_requests.faucet(address=self.senders[user]['address'])
        super(MultipleDeviceTestCase, self).teardown_method(method)

