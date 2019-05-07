/**
 * --------------------------------------------------------------------------
 *
 * @version     0.1
 * @author      luzhenyu
 * @package     jQuery Plugins
 * 
 * Usage:
 * --------------------------------------------------------------------------
 * 
 *  为了hiernate validator后台错误信息能推送到前台而写的插件
 *  
 *  $("#addForm").clearValidator(); 清空错误信息
	$("#addForm").fillValidator(obj.validateMsg); 添加错误信息
 *  
 *  <small class="h-validator" fill="obj.ruleName"></small>  
 */
(function($){
	
	function FillValidator() {
		this.defaults = {
			styleElementName: 'object',	// object | none
			dateFormat: 'mm-dd-yy',
			debug: false,
		};
	};
	
	$.extend(FillValidator.prototype, {
		setDefaults : function (settings) {
			this.defaults = $.extend({}, this.defaults, settings);
			return this;
		},
		
		fillValidator : function (obj, _element, settings) {
			options = $.extend({}, this.defaults, settings);

			_element.find("*").each(function(i, item){
				if (($(item).is("small")&&$(item).hasClass("h-validator"))) {
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
							if(($(item).is("small")&&$(item).hasClass("h-validator"))){
								$(item).text(value);
							}
						}
					} catch(e) {
						if (options.debug) {
							debug(e.message);
						}
					}

				}
			});
		},
		clearValidator : function (_element,settings) {
			options = $.extend({}, this.defaults, settings);

			_element.find("*").each(function(i, item){
				if (($(item).is("small")&&$(item).hasClass("h-validator"))) {
					$(item).text('');
				}
			});
		}
		
		
	});
	
	
	
	$.fn.fillValidator = function (obj, settings) {
		$.fillValidator.fillValidator(obj, $(this), settings);
		return this;
	};
	
	$.fn.clearValidator = function (settings) {
		$.fillValidator.clearValidator($(this),settings);
		return this;
	};
	
	
	
	$.fillValidator = new FillValidator();
	
	function debug(message) {                                                                                            // Throws error messages in the browser console.
        if (window.console && window.console.log) {
            window.console.log(message);
        }
    };
})(jQuery);