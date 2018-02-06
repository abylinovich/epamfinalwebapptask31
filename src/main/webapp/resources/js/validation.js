+function () {

    var $alphanumericPattern = /^[\d\u0041-\u007E\u0061-\u007A\u0410-\u044F]{3,15}$/,
        $emailPattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
        $textPattern = /^[\u0041-\u005A\u0061-\u007A\u0410-\u044F]{2,15}$/,
        $questionTitlePattern = /^[\u0020-\u007E\u0410-\u044F]{1,50}$/,
        $messagePattern = /^[\u0020-\u007E\u0410-\u044F]{1,255}$/
    ;

    var Validation = {

        switchButtons: function (btns, condition) {
            if (btns) {
                if (condition) {
                    btns.some(function (button) {
                        button.removeAttr('disabled')
                    })
                } else {
                    btns.some(function (button) {
                        button.attr("disabled", true)
                    })
                }
            }
        },

        validateOnEmpty: function (inputs) {
            for(var i = 0; i < inputs.length; i++) {
                if(!inputs[i].val()) {
                    return false;
                }
            }
            return true;
        },

        validateAlphanumericField: function (value) {
            if(value.val() === "") {
                return true;
            }
            return $alphanumericPattern.test(value.val());
        },

        validateEmail: function (email) {
            if(email.val() === "") {
                return true;
            }
            return $emailPattern.test(email.val().toLowerCase());
        },

        validatePasswordRepeating: function (password, repeat) {
            if(password.val() === "" || repeat.val() === "") {
                return true;
            }
            return (password.val() === repeat.val());
        },

        validateTextField: function (value) {
            var text = value.val();
            if(text === "") {
                return true;
            }
            return $textPattern.test(text);
        },

        validateAgeField: function (value) {
            var age = value.val();
            if(age === "") {
                return true;
            }
            if(age > 10 && age < 99) {
                return true;
            }
            return false;
        },

        validateTitleField: function (value) {
            var text = value.val();
            if(text === "") {
                return true;
            }
            return $questionTitlePattern.test(text);
        },

        validateMessageField: function (value) {
            var text = value.val();
            if(text === "") {
                return true;
            }
            return $messagePattern.test(text);
        }

    };

    window.Validation = Validation;

}();
