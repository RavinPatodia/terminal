var park = function () {
	
	var url="1";
	
	var detailSearch = function(){
		$("#detailSearch").bind("click",function(){
			$('.search_2').toggle();
		});
	}
	
	var handleWysihtml5 = function() {
        if (!jQuery().wysihtml5) {
            
            return;
        }

        if ($('.wysihtml5').size() > 0) {
            $('.wysihtml5').wysihtml5({
                "stylesheets": ["../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
            });
        }
    }
	
	var dateTimeInit = function(){
		$('.date-picker').datepicker({
			rtl: Metronic.isRTL(),
			orientation: "left",
			format:'yyyy-mm-dd',
			language:'zh-CN'
		});
	}
	
	var timepickerInit = function(){
		$('.timepicker-24').timepicker({
			autoclose: true,
			minuteStep: 5,
			showSeconds: false,
			showMeridian: false
		});
		// handle input group button click
        $('.timepicker').parent('.input-group').on('click', '.input-group-btn', function(e){
            e.preventDefault();
            $(this).parent('.input-group').find('.timepicker').timepicker('showWidget');
        });
	}
	
	var touchspinInit = function(){
		$(".touchspin").TouchSpin({          
            buttondown_class: 'btn green',
            buttonup_class: 'btn green',
            min: 0,
            max: 99999999,
            stepinterval: 50,
            decimals: 2,
            step: 0.01,
            maxboostedstep: 10000000,
            prefix: '￥'
        }); 
	}
	
	var monthSpinnerInit = function(){
		$(".monthSpinner").spinner({value:1, min: 1, max: 100});
	}
	
	var spinnerInit = function(){
		$(".spinner").spinner({
			value:0, 
			min: 0 ,
			decimals: 0,
			max:99999,
			numberFormat: "d5"
		});
	}
	
	var discountInit = function(){
		$(".discount").TouchSpin({
			buttondown_class: 'btn blue',
			buttonup_class: 'btn blue',
			min: 0,
			max: 100,
			step: 0.01,
			decimals: 2,
			boostat: 5,
			maxboostedstep: 10,
			postfix: '%'
		});  
	}
	
	var toastrInit = function(){
		toastr.options = {
				  "closeButton": true,
				  "debug": false,
				  "positionClass": "toast-bottom-right",
				  "onclick": null,
				  "showDuration": "1000",
				  "hideDuration": "1000",
				  "timeOut": "5000",
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				};
	};

	var urlParse = function(){
		$('form').find("*").each(function(i, item){
			if($(item).hasClass("multi-select")&&$(item).data('options')!=undefined){
				parseURL($(item),'pageUrl');
			}
			if($(item).is("input")&&$(item).data('ajax')!=undefined){
				parseURL($(item),'pageUrl');
			}
		});
		
	};
	
	var modalInit = function(){
		$('.modal').on('hidden.bs.modal', function () {
			$that = $(this);
			var form = $that.find("form").eq(0);
			if(form==null||form==undefined||form.length==0){
				return;
			}
			
			var saveBtn = $that.find(".save");
			var selects = form.find("select");        
			var iChecks = form.find(".icheck");
			
			saveBtn.prop("disabled", false);//将表单中的提交按钮启用
			
			//表单中select处理
			selects.each(function(i,obj){
				$that = $(this);
				var defaultSelect = 0;
				var select = $that.data("selected");
				if(select==undefined){
					$that.val(defaultSelect).trigger("change");
				}
				else{
					$that.val(select).trigger("change");
				}
			});
			form.find('.ic_default').iCheck('check');
			/* iChecks.each(function(i,obj){
				$that = $(this);
				var checkValue = $that.data("check");
					$that.iCheck('check');
				if(checkValue==true){
					//alert($that[0].outerHTML);
					//alert($that.attr("id"))
				}
			}); */
			form.find(".select2").val(null).trigger("change");
			form[0].reset();//将表单中的输入框回复到默认状态
			var showModalValue = form.find(".show");
			if(showModalValue.length>0)
				showModalValue.text('');	
			var hasErrors = form.find(".has-error");
			if (hasErrors.length>0)
				check(form).resetForm();//清楚相应的验证错误信息	
			else 
				console.log("modal无需清除验证");
		});
		$('.modal').on('show.bs.modal', function () {
			$that = $(this);
			var form = $that.find("form").eq(0);
			if(form==null||form==undefined||form.length==0){
				return;
			}
			form.find("*").each(function(i, item){
				if($(item).hasClass("multi-select")&&$(item).data('options')!=undefined){
					var url = $(item).data('options');
					$.ajax({
					       url: url,
					       error: function(error) {
					           alert('获取muti-select数据错误'+error);
					       },
					       success: function(data) {
					       	$(item).multiSelect("addParkOptionAndSelectOption",data);
					       }
					   });
				}
				if($(item).is("input")&&$(item).data('ajax')!=undefined){
						var url = $(item).data('ajax');
						$.ajax({
						       url: url,
						       error: function(error) {
						           alert(error);
						       },
						       success: function(data) {
						       	var obj = data;
						       	var object = obj.object;
						       	if(obj.success){
						       		$(item).val(object);
						       	}
						       	else{
						       		alert(obj.msg);
						       	}
						       }
						   });
				};
			});
		});
	};
	
	var datetimepickerInit = function(){
		 if (!jQuery().datetimepicker) {
			 	console.log("dateTimePicker初始化失败");
	            return;
	     }
		 
		 $(".form_meridian_datetime").datetimepicker({
	            isRTL: Metronic.isRTL(),
	            format: "yyyy-mm-dd HH:mm:dd",
	            showMeridian: true,	
	            autoclose: true,
	            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left"),
	            todayBtn: true,
	            language:'zh-CN'
	        });

        $('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
	}
	
	var handleBootstrapSelect = function() {
        $('.bs-select').selectpicker({
            iconBase: 'fa',
            tickIcon: 'fa-check'
        });
    };
    
    return {
        //main function to initiate the module
        init: function () {            
        	detailSearch();
        	urlParse();
        },
        dateTimeInit:function(){
        	dateTimeInit();
        },
        toastrInit:function(){
        	toastrInit();
        },
        timepickerInit:function(){
        	timepickerInit();
        },
        discountInit:function(){
        	discountInit();
        },
        touchspinInit:function(){
        	touchspinInit();
        },
        monthSpinnerInit:function(){
        	monthSpinnerInit();
        },
        spinnerInit:function(){
        	spinnerInit();
        },
        modalInit:function(){
        	modalInit();
        },
        datetimepickerInit:function(){
        	datetimepickerInit();
        },
        selectInit:function(){
        	handleBootstrapSelect();
        }
    };
}();