INFRASTRUCTURE_ERRORS = [
    'Original error: Error: ESOCKETTIMEDOUT',
    "The server didn't respond in time.",
    'An unknown server-side error occurred while processing the command.',
    'Could not proxy command to remote server. Original error: Error: socket hang up'
]

OTHER_RERUN_ERRORS = [
]


def is_infrastructure_error(error):
    for infra_error in INFRASTRUCTURE_ERRORS:
        if infra_error in error:
            return True
    return False


def should_rerun_test(test_error):
    rerun_errors = OTHER_RERUN_ERRORS
    for rerun_error in rerun_errors:
        if rerun_error in test_error:
            return True
    return False
