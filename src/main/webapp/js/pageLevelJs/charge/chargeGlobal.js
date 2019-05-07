;(function(){
	/**
	 *  判断table中勾选了几条数据查看规则修改历史
	 */
	$.fn.showHistory = function(dataSource,dialog,url){//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
		var $this = this;
		if(!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		this.bind('click',function(){//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
			if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
				bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据查看规则修改历史！");   
				return;
			}
			var ruleId = dataObj.find("td").eq(1).text();
			//alert(url);
			window.location=url+ruleId;
		});
		
	};
	

	/**
	 *  判断table中勾选了几条数据启用
	 */
	$.fn.enable2 = function(dataSource, dialog , idInput, nameLabel) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
		var $this = this;
		if (!dataSource){//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			
			return;
		}
		var $idInput;
		if(idInput===undefined||idInput==null){
			$idInput = $("#idEnable");
		}
		else{
			$idInput = idInput;
		}
		var $nameLabel;
		if(nameLabel===undefined||nameLabel==null){
			$nameLabel = $("#nameEnable");
		}
		else{
			$nameLabel = nameLabel;
		}
		this.bind('click', function() {//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(
					".active");//找到是否有被选中的数据项
			if (dataObj.length == 0) {//若没有选中或者选中的行数大于的1话
				bootbox.alert("您必须选择相应的对象进行启用");
				return;
			}
			var ids="";
			var names="";
			dataObj.each(function(i, item){
				var checkboxes = $(item).find(".checkboxes");
				var id = checkboxes.val();
				var name = checkboxes.data('name'); //获取table中勾选了的checkbox那行对应的名称，如果名称在第三个那么就是eq(2)。
				var status = checkboxes.data('state');
				if( status== true){
					bootbox.alert("【"+name+"】已经是启用状态，无法启用")
				}
				else{
					ids=ids+id+",";
					names=names+"【"+name+"】、";
				}
			});
			if(ids.length==0)
				return;
			ids=ids.substring(0,ids.length-1);
			names=names.substring(0,names.length-1);
			$idInput.val(ids);
			$nameLabel.text(names);
			dialog.modal('show');
		});
	};
	
	/**
	 *  判断table中勾选了几条数据生效
	 */
	$.fn.effectRule = function(dataSource, dialog, url) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
		var $this = this;
		if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		this.bind('click', function() {//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(
					".active");//找到是否有被选中的数据项
			if (dataObj.length !=1) {//若没有选中或者选中的行数大于的1话
				bootbox.alert("启用将成为当前生效的规则，所以您只能选择一条规则进行启用");
				return;
			}
			initEnable(dataObj,dialog);//在每个需要使用禁用启用的页面中定义，主要用户初始化传入页面需要显示的参数
		});
	};
	
	/**
	 *  判断table中勾选了几条数据生效
	 */
	$.fn.effectRule2 = function(dataSource, dialog, idInput, nameLabel) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
		var $this = this;
		if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		var $idInput;
		if(idInput===undefined||idInput==null){
			$idInput = $("#idEnable");
		}
		else{
			$idInput = idInput;
		}
		var $nameLabel;
		if(nameLabel===undefined||nameLabel==null){
			$nameLabel = $("#nameEnable");
		}
		else{
			$nameLabel = nameLabel;
		}
		this.bind('click', function() {//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(
					".active");//找到是否有被选中的数据项
			if (dataObj.length !=1) {//若没有选中或者选中的行数大于的1话
				bootbox.alert("启用将成为当前生效的规则，所以您只能选择一条规则进行启用");
				return;
			}
			var ids="";
			var names="";
			dataObj.each(function(i, item){
				var checkboxes = $(item).find(".checkboxes");
				var id = checkboxes.val();
				var name = checkboxes.data('name'); //获取table中勾选了的checkbox那行对应的名称，如果名称在第三个那么就是eq(2)。
				var status = checkboxes.data('historyflag');
				if(status==false){
					bootbox.alert("【"+name+"】规则是当前生效的规则，请选择历史规则")
				}
				else{
					ids=ids+id+",";
					names=names+"【"+name+"】、";
				}
			});
			if(ids.length==0)
				return;
			ids=ids.substring(0,ids.length-1);
			names=names.substring(0,names.length-1);
			$idInput.val(ids);
			$nameLabel.text(names);
			dialog.modal('show');
		});
	};
	/**
	 *  判断table中勾选了几条数据禁用
	 */
	$.fn.disable2 = function(dataSource, dialog, idInput, nameLabel) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
		var $this = this;
		if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		var $idInput;
		if(idInput===undefined||idInput==null){
			$idInput = $("#idDisable");
		}
		else{
			$idInput = idInput;
		}
		var $nameLabel;
		if(nameLabel===undefined||nameLabel==null){
			$nameLabel = $("#nameDisable");
		}
		else{
			$nameLabel = nameLabel;
		}
		this.bind('click', function() {//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(
					".active");//找到是否有被选中的数据项
			if (dataObj.length == 0) {//若没有选中或者选中的行数大于的1话
				bootbox.alert("您必须选择相应的对象进行禁用");
				return;
			}
			var ids="";
			var names="";
			dataObj.each(function(i, item){
				var checkboxes = $(item).find(".checkboxes");
				var id = checkboxes.val();
				var name = checkboxes.data('name'); //获取table中勾选了的checkbox那行对应的名称，如果名称在第三个那么就是eq(2)。
				var status = checkboxes.data('state');
				if( status== false){
					bootbox.alert("【"+name+"】已经是禁用状态，无法禁用")
				}
				else{
					ids=ids+id+",";
					names=names+"【"+name+"】、";
				}
			});
			if(ids.length==0)
				return;
			ids=ids.substring(0,ids.length-1);
			names=names.substring(0,names.length-1);
			$idInput.val(ids);
			$nameLabel.text(names);
			dialog.modal('show');
		});
	};
	/**
	 *  判断table中勾选了几条数据删除
	 */
	$.fn.delete_new = function(dataSource, dialog, idInput, nameLabel) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
		var $this = this;
		if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		var $idInput;
		if(idInput===undefined||idInput==null){
			$idInput = $("#idDel");
		}
		else{
			$idInput = idInput;
		}
		var $nameLabel;
		if(nameLabel===undefined||nameLabel==null){
			$nameLabel = $("#nameDel");
		}
		else{
			$nameLabel = nameLabel;
		}
		this.bind('click', function() {//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(
					".active");//找到是否有被选中的数据项
			if (dataObj.length == 0) {//若没有选中或者选中的行数大于的1话
				bootbox.alert("您必须选择相应的对象进行删除");
				return;
			}
			var ids="";
			var names="";
			dataObj.each(function(i, item){
				var id = $(item).find(".checkboxes").val();
				var name = $(item).children('td').eq(1).html();
				ids=ids+id+",";
				names=names+"【"+name+"】、";
			});
			if(ids.length==0)
				return;
			ids=ids.substring(0,ids.length-1);
			names=names.substring(0,names.length-1);
			$("#idDel").val(ids);
			$("#nameDel").text(names);
			dialog.modal('show');
		});
	};
	/**
	 * 
	 */
	$.fn.addRule = function(dataSource, dialog, url) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
		var $this = this;
		if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		this.bind('click',function(){//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
			if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
				bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行规则组添加规则！");   
				return;
			}
			var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
				$.ajax({
					url : url + id,
					success : function(data) {
						addRule.callback(data, dialog);
					},
					error : function(error) {
						alert(error.status + "," + error.readyState);
					}
				});
			});
	};
	
})();


var charge = function(){
	
	var ruleDropDown = function(){   
		$(".timeCharge").select2({
			placeholder: "搜索计时规则",
            allowClear: true,
            formatNoMatches: "没有匹配计时规则",
            formatSearching: "查询中...",
            ajax: {
                url: "/park/charge/timeCharge/select?enableFlag=1&t="+ Math.random(),
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
			minimumInputLength: 0,
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
			minimumInputLength: 0,
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
			minimumInputLength: 0,
			formatResult: formatAnchorGroup, // omitted for brevity, see the source of this page
			formatSelection: formatAnchorGroupSelection, // omitted for brevity, see the source of this page
            escapeMarkup: function (m) {
                return m;
            }
        });
		
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
		    var markup = "<div style='display:inline;'>"+repo.name+"</div><div style='float:right;color:#4F4F4F;display:inline'>"+repo.carMetering+"元</div>"+
		    "<div style='float:right;color:#4F4F4F;display:inline'>"+repo.truckMetering+"元</div>"+"<div style='float:right;color:#4F4F4F;display:inline'>"+repo.meteringFree+"分钟</div>";
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
	};
	
	 return {
	        initRuleDropDown:function(){
	        	ruleDropDown();
	        }
	    };
}();

