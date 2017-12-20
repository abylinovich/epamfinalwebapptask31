$(document).ready(function () {

    var ELEMENTS = {
        BTN_SUBMIT_LOGIN: '.jsSubmitLogin',
        INPUT_USERNAME: '.jsUsername',
        INPUT_PASSWORD: '.jsPassword',

        NOTIFICATION_USERNAME_ERROR: '.jsUsernameError',
        NOTIFICATION_PASSWORD_ERROR: '.jsPasswordError',
        NOTIFICATION_SUBMIT_ERROR: '.jsSubmitError'
        };

    var
        $submitButton = $(ELEMENTS.BTN_SUBMIT_LOGIN),
        $usernameField = $(ELEMENTS.INPUT_USERNAME),
        $passwordField = $(ELEMENTS.INPUT_PASSWORD),

        $usernameErrorNotification = $(ELEMENTS.NOTIFICATION_USERNAME_ERROR),
        $passwordErrorNotification = $(ELEMENTS.NOTIFICATION_PASSWORD_ERROR),
        $submitErrorNotification = $(ELEMENTS.NOTIFICATION_SUBMIT_ERROR)
        ;

    $usernameField.on('blur', function () {
        $submitErrorNotification.hide();
        !Validation.validateAlphanumericField($usernameField) ?
            ($usernameErrorNotification.show(), Validation.switchButtons([$submitButton], false)) :
            ($usernameErrorNotification.hide(), Validation.switchButtons([$submitButton], true));
    });

    $passwordField.on('blur', function () {
        $submitErrorNotification.hide();
        !Validation.validateAlphanumericField($passwordField) ?
            ($passwordErrorNotification.show(), Validation.switchButtons([$submitButton], false)) :
            ($passwordErrorNotification.hide(), Validation.switchButtons([$submitButton], true));
    });

    $submitButton.click(function (event) {
        event.stopPropagation();

        if(!Validation.validateOnEmpty([$usernameField, $passwordField])) {
            $submitErrorNotification.show();
            Validation.switchButtons([$submitButton], false);
        }

    });

});