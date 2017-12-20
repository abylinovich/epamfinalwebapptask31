$(document).ready(function () {

    var ELEMENTS = {
        BTN_SUBMIT_LOGIN: '.jsSubmitRegister',
        INPUT_USERNAME: '.jsUsername',
        INPUT_PASSWORD: '.jsPassword',
        INPUT_PASSWORD_REPEAT: '.jsPasswordRepeat',
        INPUT_FIRST_NAME: '.jsFirstName',
        INPUT_LAST_NAME: '.jsLastName',
        INPUT_EMAIL: '.jsEmail',
        INPUT_AGE: '.jsAge',

        NOTIFICATION_USERNAME_ERROR: '.jsUsernameError',
        NOTIFICATION_PASSWORD_ERROR: '.jsPasswordError',
        NOTIFICATION_PASSWORD_REPEAT_ERROR: '.jsPasswordRepeatError',
        NOTIFICATION_FIRST_NAME_ERROR: '.jsFirstNameError',
        NOTIFICATION_LAST_NAME_ERROR: '.jsLastNameError',
        NOTIFICATION_EMAIL_ERROR: '.jsEmailError',
        NOTIFICATION_AGE_ERROR: '.jsAgeError',
        NOTIFICATION_SUBMIT_ERROR: '.jsSubmitError'
        };

    var
        $submitButton = $(ELEMENTS.BTN_SUBMIT_LOGIN),
        $usernameField = $(ELEMENTS.INPUT_USERNAME),
        $passwordField = $(ELEMENTS.INPUT_PASSWORD),
        $passwordRepeatField = $(ELEMENTS.INPUT_PASSWORD_REPEAT),
        $firstnameField = $(ELEMENTS.INPUT_FIRST_NAME),
        $lastnameField = $(ELEMENTS.INPUT_LAST_NAME),
        $emailField = $(ELEMENTS.INPUT_EMAIL),
        $ageField = $(ELEMENTS.INPUT_AGE),

        $usernameErrorNotification = $(ELEMENTS.NOTIFICATION_USERNAME_ERROR),
        $passwordErrorNotification = $(ELEMENTS.NOTIFICATION_PASSWORD_ERROR),
        $passwordRepeatErrorNotification = $(ELEMENTS.NOTIFICATION_PASSWORD_REPEAT_ERROR),
        $firstnameErrorNotification = $(ELEMENTS.NOTIFICATION_FIRST_NAME_ERROR),
        $lastnameErrorNotification = $(ELEMENTS.NOTIFICATION_LAST_NAME_ERROR),
        $emailErrorNotification = $(ELEMENTS.NOTIFICATION_EMAIL_ERROR),
        $ageErrorNotification = $(ELEMENTS.NOTIFICATION_AGE_ERROR),
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

    $passwordRepeatField.on('blur', function () {
        $submitErrorNotification.hide();
        !Validation.validatePasswordRepeating($passwordField, $passwordRepeatField) ?
            ($passwordRepeatErrorNotification.show(), Validation.switchButtons([$submitButton], false)) :
            ($passwordRepeatErrorNotification.hide(), Validation.switchButtons([$submitButton], true));
    });

    $firstnameField.on('blur', function () {
        $submitErrorNotification.hide();
        !Validation.validateTextField($firstnameField) ?
            ($firstnameErrorNotification.show(), Validation.switchButtons([$submitButton], false)) :
            ($firstnameErrorNotification.hide(), Validation.switchButtons([$submitButton], true));
    });

    $lastnameField.on('blur', function () {
        $submitErrorNotification.hide();
        !Validation.validateTextField($lastnameField) ?
            ($lastnameErrorNotification.show(), Validation.switchButtons([$submitButton], false)) :
            ($lastnameErrorNotification.hide(), Validation.switchButtons([$submitButton], true));
    });

    $emailField.on('blur', function () {
        $submitErrorNotification.hide();
        !Validation.validateEmail($emailField) ?
            ($emailErrorNotification.show(), Validation.switchButtons([$submitButton], false)) :
            ($emailErrorNotification.hide(), Validation.switchButtons([$submitButton], true));
    });

    $ageField.on('blur', function () {
        $submitErrorNotification.hide();
        !Validation.validateAgeField($ageField) ?
            ($ageErrorNotification.show(), Validation.switchButtons([$submitButton], false)) :
            ($ageErrorNotification.hide(), Validation.switchButtons([$submitButton], true));
    });

    $submitButton.click(function (event) {
        event.stopPropagation();

        if(!Validation.validateOnEmpty([$usernameField,
                $passwordField,
                $passwordRepeatField,
                $firstnameField,
                $lastnameField,
                $emailField,
                $ageField])) {

            $submitErrorNotification.show();
            Validation.switchButtons([$submitButton], false);
        }
    });

});