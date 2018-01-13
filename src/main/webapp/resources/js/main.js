$(document).ready(function () {

    var ELEMENTS = {
        BTN_SUBMIT_QUESTION: '.jsSubmitQuestion',

        SELECT_THEME: '.jsSelectTheme',
        INPUT_TITLE: '.jsQuestionTitle',
        INPUT_QUESTION: '.jsQuestion',

        NOTIFICATION_SUBMIT_QUESTION_ERROR: '.jsSubmitQuestionError',

        NOTIFICATION_THEME_SELECT_ERROR: '.jsSelectThemeError',
        NOTIFICATION_TITLE_ERROR: '.jsQuestionTitleError',
        NOTIFICATION_QUESTION_ERROR: '.jsQuestionError'
        };

    var
        $submitQuestionButton = $(ELEMENTS.BTN_SUBMIT_QUESTION),

        $selectThemeField = $(ELEMENTS.SELECT_THEME),
        $titleField = $(ELEMENTS.INPUT_TITLE),
        $questionField = $(ELEMENTS.INPUT_QUESTION),

        $submitQuestionErrorNotification = $(ELEMENTS.NOTIFICATION_SUBMIT_QUESTION_ERROR),

        $selectThemeErrorNotification = $(ELEMENTS.NOTIFICATION_THEME_SELECT_ERROR),
        $titleErrorNotification = $(ELEMENTS.NOTIFICATION_TITLE_ERROR),
        $questionErrorNotification = $(ELEMENTS.NOTIFICATION_QUESTION_ERROR)
        ;

    $selectThemeField.on('blur', function () {
        $submitQuestionErrorNotification.hide();
        !Validation.validateOnEmpty([$selectThemeField]) ?
            ($selectThemeErrorNotification.show(), Validation.switchButtons([$submitQuestionButton], false)) :
            ($selectThemeErrorNotification.hide(), Validation.switchButtons([$submitQuestionButton], true));
    });

    $titleField.on('blur', function () {
        $submitQuestionErrorNotification.hide();
        !Validation.validateQuestionTitleField($titleField) ?
            ($titleErrorNotification.show(), Validation.switchButtons([$submitQuestionButton], false)) :
            ($titleErrorNotification.hide(), Validation.switchButtons([$submitQuestionButton], true));
    });

    $questionField.on('blur', function () {
        $submitQuestionErrorNotification.hide();
        !Validation.validateQuestionField($questionField) ?
            ($questionErrorNotification.show(), Validation.switchButtons([$submitQuestionButton], false)) :
            ($questionErrorNotification.hide(), Validation.switchButtons([$submitQuestionButton], true));
    });

    $submitQuestionButton.click(function (event) {
        event.stopPropagation();

        if(!Validation.validateOnEmpty([
                $selectThemeField,
                $titleField,
                $questionField
            ])) {

            $submitQuestionErrorNotification.show();
            Validation.switchButtons([$submitQuestionButton], false);
        }
    });

});