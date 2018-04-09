package im.status.ethereum;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class BGPerformanceInstrumentedTest {

    private static final String BASIC_SAMPLE_PACKAGE = "im.status.ethereum";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final long ELEMENT_VISIBLE_TIMEOUT = 1000 * 15;
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Launch Status app
        Context context = InstrumentationRegistry.getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);

        // Wait for Instabug popup to disappear
        BySelector instabugPopupSelector = By.textContains("Shake your device");
        mDevice.wait(Until.hasObject(instabugPopupSelector), 20000);
        mDevice.wait(Until.gone(instabugPopupSelector), 3000);
    }

    @Test
    public void testBackgroundPerformance() {
        BySelector newChatSelector = By.desc("new-chat-button");
        BySelector joinPublicChatSelector = By.desc("join-public-chat-button");
        BySelector chatNameSelector = By.desc("chat-name-input");

        createAccount("test1234", "auto-tester");

        // Join public chat
        click(newChatSelector);
        click(joinPublicChatSelector);
        setText(chatNameSelector, "lukasz-test-bot");
        mDevice.pressEnter();

        // Put app into background
        mDevice.pressHome();

        // Sit in the background for some time
        long bgActivityTimeout = 1000*60*15;
        waitFor(bgActivityTimeout);
    }

    private void createAccount(String password, String name) {
        BySelector nameSelector = By.clazz("android.widget.EditText");

        click(By.text("CREATE ACCOUNT"));
        setText(By.clazz("android.widget.EditText"), password);
        click(By.text("NEXT"));
        setText(By.clazz("android.widget.EditText"), password);
        click(By.text("NEXT"));

        waitForObject(nameSelector);
        setText(nameSelector, name);
        click(By.text("NEXT"));

        click(By.text("SHARE USAGE"));
    }

    private void waitFor(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void waitForObject(BySelector selector) {
        mDevice.wait(Until.hasObject(selector), ELEMENT_VISIBLE_TIMEOUT);
    }

    private void setText(BySelector selector, String text) {
        waitForObject(selector);
        mDevice.findObject(selector).setText(text);
    }

    private void click(BySelector selector) {
        waitForObject(selector);
        mDevice.findObject(selector).click();
    }
}