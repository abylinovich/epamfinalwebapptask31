$(document).ready(function () {

    var ELEMENTS = {
        BTN_SUBMIT_QUESTION: '.jsSubmitQuestion',
        BTN_SUBMIT_ANSWER: '.jsSubmitAnswer',
        BTN_EDIT_QUESTION_SHOW: '.jsEditQuestionShow',
        BTN_EDIT_QUESTION_HIDE: '.jsEditQuestionHide',
        BTN_SUBMIT_EDIT_QUESTION: '.jsEditQuestionSubmit',
        BTN_SUBMIT_DELETE_QUESTION: '.jsDeleteQuestionSubmit',

        SELECT_THEME: '.jsSelectTheme',
        INPUT_TITLE: '.jsQuestionTitle',
        INPUT_QUESTION: '.jsQuestion',
        INPUT_ANSWER: '.jsAnswer',

        EDIT_QUESTION_CONTAINER: '.jsEditQuestionContainer',
        EDIT_BAR_CONTAINER: '.jsEditBarContainer',

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
        $submitEditQuestionShowButton = $(ELEMENTS.BTN_EDIT_QUESTION_SHOW),
        $submitEditQuestionHideButton = $(ELEMENTS.BTN_EDIT_QUESTION_HIDE),
        $submitEditQuestionButton = $(ELEMENTS.BTN_SUBMIT_EDIT_QUESTION),
        $submitDeleteQuestionButton = $(ELEMENTS.BTN_SUBMIT_DELETE_QUESTION),

        $selectThemeField = $(ELEMENTS.SELECT_THEME),
        $titleField = $(ELEMENTS.INPUT_TITLE),
        $questionField = $(ELEMENTS.INPUT_QUESTION),
        $answerField = $(ELEMENTS.INPUT_ANSWER),

        $editQuestionContainer = $(ELEMENTS.EDIT_QUESTION_CONTAINER),
        $editBarContainer = $(ELEMENTS.EDIT_BAR_CONTAINER),

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
            ($titleErrorNotification.show(),
                Validation.switchButtons([$submitQuestionButton], false),
                Validation.switchButtons([$submitEditQuestionButton], false)) :
            ($titleErrorNotification.hide(),
                Validation.switchButtons([$submitQuestionButton], true),
                Validation.switchButtons([$submitEditQuestionButton], true));
    });

    $questionField.on('blur', function () {
        $submitQuestionErrorNotification.hide();
        !Validation.validateMessageField($questionField) ?
            ($questionErrorNotification.show(),
                Validation.switchButtons([$submitQuestionButton], false),
                Validation.switchButtons([$submitEditQuestionButton], false)) :
            ($questionErrorNotification.hide(),
                Validation.switchButtons([$submitQuestionButton], true),
                Validation.switchButtons([$submitEditQuestionButton], true));
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

        if($answerField.val() == "" || !Validation.validateOnEmpty([$answerField])) {
            $submitAnswerErrorNotification.show();
            Validation.switchButtons([$submitAnswerButton], false);
        }

    });

    $submitEditQuestionShowButton.click(function (event) {
        event.stopPropagation();

        $editBarContainer.hide();
        $editQuestionContainer.show();
    });

    $submitEditQuestionHideButton.click(function (event) {
        event.stopPropagation();

        $editQuestionContainer.hide();
        $editBarContainer.show();
    });

    $submitEditQuestionButton.click(function (event) {
        event.stopPropagation();

        if(!Validation.validateOnEmpty([
                $titleField,
                $questionField
            ])) {

            $submitQuestionErrorNotification.show();
            Validation.switchButtons([$submitEditQuestionButton], false);
        }

        return confirm("Are you sure?");
    });

    $submitDeleteQuestionButton.click(function (event) {
        event.stopPropagation();

        return confirm("Are you sure?");
    });

});