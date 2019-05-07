/**
 * jQuery Form Fill - http://makoto.blog.br/formFill
 * --------------------------------------------------------------------------
 *
 * Licensed under The MIT License
 * 
 * @version     0.1
 * @since       16.06.2010
 * @author      Makoto Hashimoto
 * @link        http://makoto.blog.br/formFill
 * @twitter     http://twitter.com/makoto_vix
 * @license     http://www.opensource.org/licenses/mit-license.php MIT 
 * @package     jQuery Plugins
 * 
 * Usage:
 * --------------------------------------------------------------------------
 * 
 *	$('form#formUser').fill({"name" : "Makoto Hashimoto", "email" : "makoto@makoto.blog.br"});
 *  
 *  <form id="formUser">
 *  	<label>Name:</label>
 *  	<input type="text" name="user.name"/>
 *  	<label>E-mail:</label>
 *  	<input type="text" name="user.email"/>
 *  </form>
 *  
 *  自己写的表单填充插件，修改了本来的Jquery.formFill。
 *  原来的使用方式：
 *  <form id="formUser">
 *  	<label>Name:</label>
 *  	<input type="text" name="user.name"/>
 *  	<label>E-mail:</label>
 *  	<input type="text" name="user.email"/>
 *  </form>
 *  
 *  现在的使用方式：
 *  <form id="formUser">
 *  	<label>Name:</label>
 *  	<input type="text" fill="user.name"/>
 *  	<label>E-mail:</label>
 *  	<input type="text" fill="user.email"/>
 *  </form>
 *  
 *  动态记载的select、input，icheck插件的控件不能fill进去，不会有效果而且会有冲突
 *  
 */
(function($){
	
	function Fill() {
		this.defaults = {
			styleElementName: 'object',	// object | none
			dateFormat: 'mm-dd-yy',
			debug: false,
			elementsExecuteEvents: ['checkbox', 'radio', 'select-one']
		};
	};
	
	$.extend(Fill.prototype, {
		setDefaults : function (settings) {
			this.defaults = $.extend({}, this.defaults, settings);
			return this;
		},
		
		fill : function (obj, _element, settings) {
			options = $.extend({}, this.defaults, settings);

			_element.find("*").each(function(i, item){				
				if ($(item).is("input") || $(item).is("select") || $(item).is("textarea")||($(item).is("p")&&$(item).hasClass("form-control-static"))
						||($(item).is("label")&&$(item).hasClass("show"))||($(item).is("div")&&$(item).hasClass("icheck-inline"))) {
					try {
						var objName;
						var arrayAtribute;
						try {
							if (options.styleElementName == "object") {
								// Verificando se � um array
								if ($(item).attr("fill").match(/\[[0-9]*\]/i)) {
									objName = $(item).attr("fill").replace(/^[a-z]*[0-9]*[a-z]*\./i, 'obj.').replace(/\[[0-9]*\].*/i, "");
									arrayAtribute = $(item).attr("fill").match(/\[[0-9]*\]\.[a-z0-9]*/i) + "";
									arrayAtribute = arrayAtribute.replace(/\[[0-9]*\]\./i, "");
								} else {
									objName = $(item).attr("fill").replace(/^[a-z]*[0-9]*[a-z]*\./i, 'obj.');
								}
							} else if (options.styleElementName == "none") {
								objName = 'obj.' + $(item).attr("fill");
							}
							var value = eval(objName);
							//alert(objName);
							//alert(value);	
						} catch(e) {
							if (options.debug) {
								debug(e.message);
							}
						}					

						if (value != null) {
							switch ($(item).attr("type")) {
								case "hidden":
								case "password":
								case "textarea":
									$(item).val(value);
								break;

								case "text":
									if ($(item).hasClass("hasDatepicker")) {
										var re = /^[-+]*[0-9]*$/;
										var dateValue = null;
										if (re.test(value)) {
											dateValue = new Date(parseInt(value));
											var strDate = dateValue.getUTCFullYear() + '-' + (dateValue.getUTCMonth() + 1) + '-' + dateValue.getUTCDate();
											dateValue = $.datepicker.parseDate('yy-mm-dd', strDate);
										} else if (value) {										
											dateValue = $.datepicker.parseDate(options.dateFormat, value);
										}
										$(item).datepicker('setDate', dateValue);							
									} else if ($(item).attr("alt") == "double") {
										$(item).val(value.toFixed(2));
									} else {
										$(item).val(value);
									}
								break;

								case "select-one":
									if (value) {
										$(item).val(value);
									}
								break;
								//这里使用的是iCheck插件的选中方法
								case "radio":
									$(item).each(function (i, radio) {
										if ($(radio).val() == value) {
											$(radio).iCheck('check');
										}
									});
								break;
								case "checkbox":
									if ($.isArray(value)) {
										$.each(value, function(i, arrayItem) {
											if (typeof(arrayItem) == 'object') {											
												arrayItemValue = eval("arrayItem." + arrayAtribute);
											} else {
												arrayItemValue = arrayItem;
											}
											if ($(item).val() == arrayItemValue) {
												$(item).attr("checked", "checked");
											}
										}); 
									} else {
										if ($(item).val() == value) {
											$(item).attr("checked", "checked");
										}
									}						
								break;
							}
							//switch插件
							if(($(item).is("input")&&$(item).hasClass("make-switch"))){
								//alert(value);
								$(item).bootstrapSwitch('state',value,value);
							}
							//查看Table中某一个对象查看详情
							if(($(item).is("label")&&$(item).hasClass("show"))||($(item).is("p")&&$(item).hasClass("form-control-static"))){
								var type = $(item).data('type');
								if(type=='boolean'){
									var trueValue = $(item).data("true");
									var falseValue = $(item).data("false");
									if(value==true){
										$(item).text(trueValue);
									}
									else if(value==false){
										$(item).text(falseValue);
									}
								}
								else if(type=='int'){
									var min = $(item).data("min");
									var max = $(item).data("max");
									for(var i=min;i<=max;i++){
										var showValue = $(item).data("num"+i);
										if(value==i){
											$(item).text(showValue);
										}
									}
								}
								else{
									$(item).text(value);
								}
							}
							//TextArea插件
							if($(item).is("textarea")){
								$(item).val(value);
							}
							//更改select2插件的选项
							if($(item).hasClass("bs-select")||$(item).hasClass("select2")){
								$(item).val(value).trigger("change");
							}
							executeEvents(item);
						}
					} catch(e) {
						if (options.debug) {
							debug(e.message);
						}
					}

				}
			});
		},
		clearFormFill : function (_element,settings) {
			options = $.extend({}, this.defaults, settings);

			_element.find("*").each(function(i, item){
				if (($(item).is("label")&&$(item).hasClass("show"))||($(item).is("label")&&$(item).hasClass("show"))) {
					$(item).text('');
				}
			});
		}
	});
	
	$.fn.fill = function (obj, settings) {
		$.fill.fill(obj, $(this), settings);
		return this;
	};
	
	$.fill = new Fill();
	
	$.fn.clearValidator = function (settings) {
		$.fill.clearFormFill($(this),settings);
		return this;
	};
	
	function executeEvents(element) {
		if (jQuery.inArray($(element).attr('type'), $.fill.defaults.elementsExecuteEvents)) {
			if ($(element).attr('onchange')) {
				$(element).change();
			}

			if ($(element).attr('onclick')) {
				$(element).click();
			}
		}	
	};
	
	function debug(message) {                                                                                            // Throws error messages in the browser console.
        if (window.console && window.console.log) {
            window.console.log(message);
        }
    };
})(jQuery);