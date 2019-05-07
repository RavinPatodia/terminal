;(function(){
	/**
	 *  判断table中勾选了几条数据开启状态
	 */
	$.fn.open = function(dataSource, dialog , idInput, nameLabel) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
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
				var lineState = checkboxes.data('linestate');
				var name = checkboxes.data('name'); //获取table中勾选了的checkbox那行对应的名称，如果名称在第三个那么就是eq(2)。
				if(lineState == false ){
					bootbox.alert("【"+name+"】现在是离线状态，无法操作")
				}
				else{
					var id = checkboxes.val();
					var status = checkboxes.data('state');
					if( status== true){
						bootbox.alert("【"+name+"】已经是启用状态，无法启用")
					}
					else{
						ids=ids+id+",";
						names=names+"【"+name+"】、";
					}
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
	 *  判断table中勾选了几条数据禁用状态
	 */
	$.fn.close = function(dataSource, dialog, idInput, nameLabel) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
		var $this = this;
		if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		var $idInput;
		//如果未传indInput、nameLabel参数，则使用默认值
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
				var lineState = checkboxes.data('linestate');
				var name = checkboxes.data('name'); //获取table中勾选了的checkbox那行对应的名称，如果名称在第三个那么就是eq(2)。
				if(lineState == false ){
					bootbox.alert("【"+name+"】现在是离线状态，无法操作")
				}
				else {
					var id = checkboxes.val();
					var status = checkboxes.data('state');
					if( status== false){
						bootbox.alert("【"+name+"】已经是禁用状态，无法禁用")
					}
					else{
						ids=ids+id+",";
						names=names+"【"+name+"】";
					}
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
})();

