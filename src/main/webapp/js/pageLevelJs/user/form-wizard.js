var FormWizard = function () {
    return {
        init: function () {
            if (!jQuery().bootstrapWizard) {
                return;
            }

            var form = $('#submit_form');
            var error = $('.alert-danger', form);
            var success = $('.alert-success', form);
            form.validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                	uacc: {
                        maxlength: 20,
                        required: true
                    },
                    upwd: {
                        minlength: 6,
                        maxlength: 8,
                        required: true
                    },
                    rpassword: {
                        minlength: 6,
                        maxlength: 8,
                        required: true,
                        equalTo: "#submit_form_password"
                    },
                    name: {
                        required: true
                    },
                    idCard: {
                    	isIdCardNo: true,
                        minlength: 18,
                        maxlength: 20,
                        required: true
                    },
                    birthday: {
                        required: true
                    },
                    gender: {
                        
                    },
                    mobilePhone: {
                    	minlength: 11,
                        maxlength: 11,
                        required: true
                    },
                    tel: {
                        
                    },
                    fax: {
                        
                    },
                    qq: {
                    },
                    zipCode: {
                    	minlength: 6,
                        maxlength: 6,
                    },
                    email: {
                        email: true
                    },
                    ugroupPK: {
                        required: true
                    },
                    hasParkingSpaces: {
                        required: true
                    },
                    addr: {
                        required: true
                    },
                    city: {
                        required: true
                    },
                    dist: {
                        required: true
                    },
                    province: {
                        required: true
                    },
                    company: {
                        
                    },
                    //car
                    licensePlateType: {
                        required: true
                    },
                    carColor: {
                        required: true
                    },
                    carModel: {
                        required: true
                    },
                    carType: {
                        required: true
                    }
                },

                messages: { // custom messages for radio buttons and checkboxes
                   /* 'payment[]': {
                        required: "Please select at least one option",
                        minlength: jQuery.validator.format("Please select at least one option")
                    }*/
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success.hide();
                    error.show();
                    Metronic.scrollTo(error, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element).closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    if (label.attr("for") == "gender" || label.attr("for") == "payment[]") { // for checkboxes and radio buttons, no need to show OK icon
                        label.closest('.form-group').removeClass('has-error').addClass('has-success');
                        label.remove(); // remove error label here
                    } else { // display success icon for other inputs
                        label.addClass('valid') // mark the current input as valid and display OK icon
                        .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
                    }
                },
                errorPlacement: function (error, element) {
                    if (element.parent(".date-picker").size() > 0) {
                    error.insertAfter(element.parent(".date-picker").parent());
                    }
                    else if (element.parent().parent(".addr").size() > 0) {
                    error.insertAfter(element.parent().parent(".addr"));
                    }  
                    else{
                       error.insertAfter(element.parent()); 
                    } 
                }, 
                submitHandler: function (form) {
                    success.show();
                    error.hide();
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                }

            });

           var displayConfirm = function() {
                $('#tab4 .form-control-static', form).each(function(){
                    var input = $('[name="'+$(this).attr("data-display")+'"]', form);
                    if (input.is(":radio")) {
                        input = $('[name="'+$(this).attr("data-display")+'"]:checked', form);
                    }
                    if (input.is(":text") || input.is("textarea")) {
                        $(this).html(input.val());
                    } else if (input.is("select")) {
                        $(this).html(input.find('option:selected').text());
                    } else if (input.is(":radio") && input.is(":checked")) {
                        $(this).html(input.attr("data-title"));
                    } else if ($(this).attr("data-display") == 'payment[]') {
                        var payment = [];
                        $('[name="payment[]"]:checked', form).each(function(){ 
                            payment.push($(this).attr('data-title'));
                        });
                        $(this).html(payment.join("<br>"));
                    }
                });
            }

            var handleTitle = function(tab, navigation, index) {
                var total = navigation.find('li').length;
                var current = index + 1;
                // set wizard title
                $('.step-title', $('#form_wizard_1')).text('Step ' + (index + 1) + ' of ' + total);
                // set done steps
                jQuery('li', $('#form_wizard_1')).removeClass("done");
                var li_list = navigation.find('li');
                for (var i = 0; i < index; i++) {
                    jQuery(li_list[i]).addClass("done");
                }

                if (current == 1) {
                    $('#form_wizard_1').find('.button-previous').hide();
                } else {
                    $('#form_wizard_1').find('.button-previous').show();
                }

                if (current >= total) {
                    $('#form_wizard_1').find('.button-next').hide();
                    $('#form_wizard_1').find('.button-submit').show();
                    displayConfirm();
                } else {
                    $('#form_wizard_1').find('.button-next').show();
                    $('#form_wizard_1').find('.button-submit').hide();
                }
                Metronic.scrollTo($('.page-title'));
            }

            // default form wizard
            $('#form_wizard_1').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                onTabClick: function (tab, navigation, index, clickedIndex) {
                    return false;
                    /*
                    success.hide();
                    error.hide();
                    if (form.valid() == false) {
                        return false;
                    }
                    handleTitle(tab, navigation, clickedIndex);
                    */
                },
                onNext: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                   if (form.valid() == false) {
                        return false;
                   }

                    handleTitle(tab, navigation, index);
                },
                onPrevious: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    handleTitle(tab, navigation, index);
                },
                onTabShow: function (tab, navigation, index) {
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    $('#form_wizard_1').find('.progress-bar').css({
                        width: $percent + '%'
                    });
                }
            });

            $('#form_wizard_1').find('.button-previous').hide();
            $('#form_wizard_1 .button-submit').click(function () {
				if($("#add_table tr").length<=1){
					$('#form_wizard_1 .button-submit').attr('disabled',true);
					return false;
				}
               addUser.init();
               //触发两次回退事件
               $('.button-previous').trigger('click');
               $('.button-previous').trigger('click');
            }).hide();

            //apply validation on select2 drop down value change, this only needed for chosen dropdown integration.
           $('#licensePlateType', form).change(function () {
                form.validate().element($(this)); 
            });
           $('#carColor', form).change(function () {
               form.validate().element($(this)); 
           });
           $('#carModel', form).change(function () {
               form.validate().element($(this)); 
           });
           $('#ugroupPK', form).change(function () {
               form.validate().element($(this)); 
           });
        }

    };

}();