$(document).ready(function () {

    var ELEMENTS = {
        BTN_SUBMIT_QUESTION: '.jsSubmitQuestion',
        BTN_SUBMIT_ANSWER: '.jsSubmitAnswer',

        SELECT_THEME: '.jsSelectTheme',
        INPUT_TITLE: '.jsQuestionTitle',
        INPUT_QUESTION: '.jsQuestion',
        INPUT_ANSWER: '.jsAnswer',

        NOTIFICATION_SUBMIT_QUESTION_ERROR: '.jsSubmitQuestionError',
        NOTIFICATION_SUBMIT_ANSWER_ERROR: '.jsSubmitAnswerError',

        NOTIFICATION_THEME_SELECT_ERROR: '.jsSelectThemeError',
        NOTIFICATION_TITLE_ERROR: '.jsQuestionTitleError',
        NOTIFICATION_QUESTION_ERROR: '.jsQuestionError',
        NOTIFICATION_ANSWER_ERROR: '.jsAnswerError'
        };

    var
        $submitQuestionButton = $(ELEMENTS.BTN_SUBMIT_QUESTION),
        $submitAnswerButton = $(ELEMENTS.BTN_SUBMIT_ANSWER),

        $selectThemeField = $(ELEMENTS.SELECT_THEME),
        $titleField = $(ELEMENTS.INPUT_TITLE),
        $questionField = $(ELEMENTS.INPUT_QUESTION),
        $answerField = $(ELEMENTS.INPUT_ANSWER),

        $submitQuestionErrorNotification = $(ELEMENTS.NOTIFICATION_SUBMIT_QUESTION_ERROR),
        $submitAnswerErrorNotification = $(ELEMENTS.NOTIFICATION_SUBMIT_ANSWER_ERROR),

        $selectThemeErrorNotification = $(ELEMENTS.NOTIFICATION_THEME_SELECT_ERROR),
        $titleErrorNotification = $(ELEMENTS.NOTIFICATION_TITLE_ERROR),
        $questionErrorNotification = $(ELEMENTS.NOTIFICATION_QUESTION_ERROR),
        $answerErrorNotification = $(ELEMENTS.NOTIFICATION_ANSWER_ERROR)
        ;


    $selectThemeField.on('blur', function () {
        $submitQuestionErrorNotification.hide();
        !Validation.validateOnEmpty([$selectThemeField]) ?
            ($selectThemeErrorNotification.show(), Validation.switchButtons([$submitQuestionButton], false)) :
            ($selectThemeErrorNotification.hide(), Validation.switchButtons([$submitQuestionButton], true));
    });

    $titleField.on('blur', function () {
        $submitQuestionErrorNotification.hide();
        !Validation.validateTitleField($titleField) ?
            ($titleErrorNotification.show(), Validation.switchButtons([$submitQuestionButton], false)) :
            ($titleErrorNotification.hide(), Validation.switchButtons([$submitQuestionButton], true));
    });

    $questionField.on('blur', function () {
        $submitQuestionErrorNotification.hide();
        !Validation.validateMessageField($questionField) ?
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

    $answerField.on('blur', function () {
        $submitAnswerErrorNotification.hide();
        !Validation.validateMessageField($answerField) ?
            ($answerErrorNotification.show(), Validation.switchButtons([$submitAnswerButton], false)) :
            ($answerErrorNotification.hide(), Validation.switchButtons([$submitAnswerButton], true));
    });

    $submitAnswerButton.click(function (event) {
        event.stopPropagation();

        if(!Validation.validateOnEmpty([$answerField])) {
            $submitAnswerErrorNotification.show();
            Validation.switchButtons([$submitQuestionButton], false);
        }

    });

});