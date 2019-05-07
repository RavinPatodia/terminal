var ComponentsDropdowns = function () {
	var handleBootstrapSelect = function() {
        $('.bs-select').selectpicker({
            iconBase: 'fa',
            tickIcon: 'fa-check'
        });
    };
    
  //动态加载下拉框的JS
	var ruleDropDown = function(){
		$(".timeCharge").select2({
			placeholder: "搜索计时规则",
            allowClear: true,
            formatNoMatches: "没有匹配计时规则",
            formatSearching: "查询中...",
            ajax: {
                url: "/park/charge/timeCharge/select?t="+ Math.random(),
                dataType: 'json',
                delay: 250,
                data: function (term, page) {
                    return {
                        name: term
                    };
                },
                results: function (data, page) {
                    return {results: data};
                },
                
            },
            initSelection: function (element, callback) {
                // the input tag has a value attribute preloaded that points to a preselected movie's id
                // this function resolves that id attribute to an object that select2 can render
                // using its formatResult renderer - that way the movie name is shown preselected
            	var id=element.id;
            	var text = element.name;
            	if(id!=''&&text!=""){
                    callback({id:id,text:text});
            	}
            },
            //data:data,
			minimumInputLength: 1,
			formatResult: formatTimeCharge, // omitted for brevity, see the source of this page
			formatSelection: formatTimeChargeSelection, // omitted for brevity, see the source of this page
            escapeMarkup: function (m) {
                return m;
            }
        });
		
		$(".meterCharge").select2({
			placeholder: "搜索计次规则",
            allowClear: true,
            formatNoMatches: "没有匹配的计次规则",
            formatSearching: "查询中...",
            ajax: {
                url: "/park/charge/meterCharge/select?t="+ Math.random(),
                dataType: 'json',
                delay: 250,
                data: function (term, page) {
                    return {
                        name: term
                    };
                },
                results: function (data, page) {
                    return {results: data};
                },
                
            },
            initSelection: function (element, callback) {
                // the input tag has a value attribute preloaded that points to a preselected movie's id
                // this function resolves that id attribute to an object that select2 can render
                // using its formatResult renderer - that way the movie name is shown preselected
            	var id=element.id;
            	var text = element.name;
            	if(id!=''&&text!=""){
                    callback({id:id,text:text});
            	}
            },
            //data:data,
			minimumInputLength: 1,
			formatResult: formatMeterCharge, // omitted for brevity, see the source of this page
			formatSelection: formatMeterChargeSelection, // omitted for brevity, see the source of this page
            escapeMarkup: function (m) {
                return m;
            }
        });
		
		$(".anchorGroup").select2({
			placeholder: "搜索长期规则组规则",
            allowClear: true,
            formatNoMatches: "没有匹配的长期规则组规则",
            formatSearching: "查询中...",
            ajax: {
                url: "/park/charge/anchorGroup/select?t="+ Math.random(),
                dataType: 'json',
                delay: 250,
                data: function (term, page) {
                    return {
                        name: term
                    };
                },
                results: function (data, page) {
                	//alert(data);
                    return {results: data};
                },
                
            },
            initSelection: function (element, callback) {
                // the input tag has a value attribute preloaded that points to a preselected movie's id
                // this function resolves that id attribute to an object that select2 can render
                // using its formatResult renderer - that way the movie name is shown preselected
            	var id=element.id;
            	var text = element.name;
            	if(id!=''&&text!=""){
                    callback({id:id,text:text});
            	}
            },
            //data:data,
			minimumInputLength: 1,
			formatResult: formatAnchorGroup, // omitted for brevity, see the source of this page
			formatSelection: formatAnchorGroupSelection, // omitted for brevity, see the source of this page
            escapeMarkup: function (m) {
                return m;
            }
        });
		
		$(".ruleAppliType").select2({
			//placeholder: "搜索规则应用类型",
            allowClear: true,
            formatNoMatches: "没有匹配的规则应用类型",
            formatSearching: "查询中...",
            ajax: {
                url: "/park/charge/ruleAppliType/select?t="+ Math.random(),
                dataType: 'json',
                delay: 500,
                data: function (term, page) {
                    return {
                        name: term
                    };
                },
                results: function (data, page) {
                    return {results: data};
                },
                
            },
            initSelection: function (element, callback) {
                // the input tag has a value attribute preloaded that points to a preselected movie's id
                // this function resolves that id attribute to an object that select2 can render
                // using its formatResult renderer - that way the movie name is shown preselected
            	var id=element.id;
            	var text = element.name;
            	if(id!=''&&text!=""){
                    callback({id:id,text:text});
            	}
            },
            //data:data,
			minimumInputLength: 1,
			formatResult: formatRuleAppliType, // omitted for brevity, see the source of this page
			formatSelection: formatRuleAppliTypeSelection, // omitted for brevity, see the source of this page
			escapeMarkup: function (m) {
                return m;
            }
        });
	};
	
	 function formatTimeCharge (repo) {
		    if (repo.loading) return repo.name;
		    var billingModel = repo.billingModel;
		    var billingModelName;
		    if(billingModel==0)
		    	billingModelName="标准";
		    else if(billingModel==1)
		    	billingModelName="时段";
		    else if(billingModel==2)
		    	billingModelName="昼夜模式";
		    else if(billingModel==3)
		    	billingModelName="昼夜模式二";
		    var markup = "<div style='display:inline;'>"+repo.name+"</div><div style='float:right;color:#4F4F4F;display:inline'>"+billingModelName+"</div>"
		    return markup;
		  }
	 
	function formatTimeChargeSelection (repo) {
		 return  repo.name;
	}
	
	function formatMeterCharge (repo) {
	    if (repo.loading) return repo.name;
	    var markup = "<div style='display:inline;'>"+repo.name+"</div><div style='float:right;color:#4F4F4F;display:inline'>"+repo.car_metering+"</div>"+
	    "<div style='float:right;color:#4F4F4F;display:inline'>"+repo.truck_metering+"</div>"+"<div style='float:right;color:#4F4F4F;display:inline'>"+repo.free_time+"</div>";
	    return markup;
	}
	
	function formatMeterChargeSelection (repo) {
		 return  repo.name;
	}
	
	function formatAnchorGroup (repo) {
	    if (repo.loading) return repo.name;
	    //alert(repo.name);
	    //alert(repo);
	    var markup = "<div style='display:inline;'>"+repo.name+"</div>"
	    return markup;
	}
 
	function formatAnchorGroupSelection (repo) {
		 return  repo.name;
	}
    
    function formatRuleAppliType (repo) {
	    if (repo.loading) return repo.name;
	    
	    var markup = "<div style='display:inline;'>"+repo.name+"</div>"
	    return markup;
	  }
 
	function formatRuleAppliTypeSelection (repo) {
		 return  repo.name;
	}
	
    return {
        //main function to initiate the module
        init: function () {            
            handleBootstrapSelect();
        },
        initRuleDropDown:function(){
        	ruleDropDown();
        }
    };
}();
