;(function(){
	$.global_config = {//表格插件的全局配置
		table_config:{
			"bStateSave": true, //是否把获得数据存入cookie
	        "lengthMenu": [
	            [10, 15, 20],
	            [10, 15, 20] // 更改每页显示记录数
	        ],
	        // 设置初始值
	        "bProcessing": true,
	        "pageLength": 10,            
	        "bFilter": false,
	        "bAutoWidth":false,//自动宽度
            "sPaginationType": "full_numbers", 
            //"columnDefs": [],
	        "language": {
	            "search": "搜索: ",
	            "lengthMenu": "  _MENU_ 条数据",
	            "emptyTable": "表格中没有数据~",
	            "info": "显示 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
	            "infoEmpty": "没有数据~",
	            "infoFiltered": "(在 _MAX_ 条数据中查询)",
	            "lengthMenu": "显示 _MENU_ 条数据",
	            "search": "查询:",
	            "zeroRecords": "没有找到对应的数据",
	            "paginate": {
	                "previous":"上一页",
	                "next": "下一页",
	                "last": "末页",
	                "first": "首页"
            	}
	        }
		},
		validator_config:{
			 errorElement: 'span', //default input error message container
		    errorClass: 'help-block help-block-error', // default input error message class
		    focusInvalid: false, // do not focus the last invalid input
		    ignore: "", // validate all fields including form hidden input
			errorPlacement: function (error, element) { // render error placement for each input type
		        /*if (element.parent(".input-group").size() > 0) {
		            error.insertAfter(element.parent(".input-group"));
		        } else if (element.attr("data-error-container")) { 
		            error.appendTo(element.attr("data-error-container"));
		        } else if (element.parents('.radio-list').size() > 0) { 
		            error.appendTo(element.parents('.radio-list').attr("data-error-container"));
		        } else if (element.parents('.radio-inline').size() > 0) { 
		            error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
		        } else if (element.parents('.checkbox-list').size() > 0) {
		            error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
		        } else if (element.parents('.checkbox-inline').size() > 0) { 
		            error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
		        } else {
		            error.insertAfter(element); // for other inputs, just perform default behavior
		        }*/
				 error.insertAfter(element.parent());
		    }, 
		    highlight: function (element) { // hightlight error inputs
		       $(element)
		            .closest('.form-group').addClass('has-error'); // set error class to the control group
		    },

		    unhighlight: function (element) { // revert the change done by hightlight
		        $(element)
		            .closest('.form-group').removeClass('has-error'); // set error class to the control group
		    },

		    success: function (label) {
		        label
		            .closest('.form-group').removeClass('has-error'); // set success class to the control group
		    }		
		}
	};

	//---start---
	    $.table_init = function($table){//表格初始化
	    	var tableWrapper = jQuery('#favourable_wrapper');

		    $table.find('.group-checkable').change(function () {
		        var set = jQuery(this).attr("data-set");
		        var checked = jQuery(this).is(":checked");
		        jQuery(set).each(function () {
		            if (checked) {
		                $(this).attr("checked", true);
		                $(this).parents('tr').addClass("active");
		            } else {
		                $(this).attr("checked", false);
		                $(this).parents('tr').removeClass("active");
		            }
		        });
		        jQuery.uniform.update(set);
		    });

		    $table.on('change', 'tbody tr .checkboxes', function () {
		        $(this).parents('tr').toggleClass("active");
		    });

		    tableWrapper.find('.dataTables_length select').addClass("form-control input-xsmall input-inline"); 
	    };
	    //---end---

	    $.fn.toggleStyle = function(theme){//切换主题
			 $(this).children('li').children("a").bind('click',function(e){
					document.getElementById('style_color').setAttribute('href',$(this).data('url'));
			});
		};

		//双击事件
		$.fn.dbclick = function(dialog,url,form){
			//alert(url);
			var showUrl = url;
			this.live('dblclick',function(){
				//alert(showUrl);
				var id = $(this).find(".checkboxes").val();//获取双击那行checkbox，获取checkbox的值（这里传入的是 id)
				$.ajax({
					url:showUrl+id+"?t="+Math.random(),
					success:function(data){
						var obj = data;
						var formObj = obj.object;
						//alert(data);
						if(formObj.ftpPicUrl!=null){
							$('.operImage').find('img').attr('src',formObj.ftpPicUrl);
						}else{
							$('.operImage').find('img').attr('src','/park/assets/admin/layout/img/AAAAAA&amp.gif');
						}
						
						if(obj.success){
							$(form,dialog).fill(formObj);//表单填充插件
							//alert(obj.msg);
							dialog.modal('show');
						}
						else{
							//alert(obj.msg);
						}
					}
				});
				dialog.modal('show');
			})
		};
		//编辑事件
		$.fn.edit = function(dataSource,dialog,url){//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
			var $this = this;
			if(!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
			this.bind('click',function(){//按钮绑定点击事件
				var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
				if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
					bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据再进行编辑！");   
					return;
				}
				var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
					$.ajax({
						url : url + id+"?t="+Math.random(),
						success : function(data) {
							edit.callback(data, dialog);
						},
						error : function(error) {
							alert(error.status + "," + error.readyState);
						}
					});
				});
			};
		
			//编辑事件
			$.fn.bindAuth = function(dataSource,dialog,url){//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
				var $this = this;
				if(!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
					return;
				this.bind('click',function(){//按钮绑定点击事件
					var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
					if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
						bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据再进行绑定！");   
						return;
					}
					var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
						$.ajax({
							url : url + id+"?t="+Math.random(),
							success : function(data) {
								bindAuth.callback(data, dialog);
							},
							error : function(error) {
								alert(error.status + "," + error.readyState);
							}
						});
					});
				};
		/**
		 *  判断table中勾选了几条数据启用
		 */
		$.fn.enable_new = function(dataSource, dialog , idInput, nameLabel) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
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
		 *  判断table中勾选了几条数据禁用
		 */
		$.fn.disable_new = function(dataSource, dialog, idInput, nameLabel) {//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
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
		 * url：删除操作的URL
		 * module：模块名称
		 * preMethod:弹出删除确认框的预处理操作
		 * afterMethod:在执行玩删除操作以后的操作
		 * @author lzy
		 *
		 */
		$.fn.delUseDialog = function(dataSource, url ,module,preMethod,afterMethod) { 
			var $this = this;
			if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
			this.bind('click', function() {//按钮绑定点击事件
				var dataObj = dataSource.children("tbody").children(
						".active");//找到是否有被选中的数据项
				if (dataObj.length == 0) {//若没有选中或者选中的行数大于的1话
					bootbox.alert("您必须选择相应的记录进行删除");
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
				var confirmMsg = "你确定要删除"+names+"?";
				if(preMethod){
					ids = preMethod(ids);
				}
				if(ids==null||ids=="")
					return;
				bootbox.confirm(confirmMsg, function(result) {
	                   if(result){
	                	   $.ajax({
	                	        url: url+ids,
	                	        async: false,
	                	        error: function(request) {
	                	            alert("Connection error");
	                	        },
	                	        success: function(data) {
	                	        	var obj = data;
	                	        	if(obj.success){
	                	        		toastr.success(obj.msg, module);
	                	        		dataSource.dataTable().fnDraw(false);
	                	        	}
	                	        	else{
	                	        		toastr.error(obj.msg,module);
	                	        	}
	                	        }
	                	    }); 
	                   }
                }); 
				if(afterMethod){
					afterMethod(ids);
				}
			});
		};

		$.fn.delLogUseDialog = function(dataSource, url ,module,preMethod,afterMethod) { 
			var $this = this;
			if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
			this.bind('click', function() {//按钮绑定点击事件
				var dataObj = dataSource.children("tbody").children(
						".active");//找到是否有被选中的数据项
				if (dataObj.length == 0) {//若没有选中或者选中的行数大于的1话
					bootbox.alert("您必须选择相应的记录进行删除");
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
				var confirmMsg = "你确定要删除本条日志?";
				if(preMethod){
					ids = preMethod(ids);
				}
				if(ids==null||ids=="")
					return;
				bootbox.confirm(confirmMsg, function(result) {
	                   if(result){
	                	   $.ajax({
	                	        url: url+ids,
	                	        async: false,
	                	        error: function(request) {
	                	            alert("Connection error");
	                	        },
	                	        success: function(data) {
	                	        	var obj = data;
	                	        	if(obj.success){
	                	        		toastr.success(obj.msg, module);
	                	        		dataSource.dataTable().fnDraw(false);
	                	        	}
	                	        	else{
	                	        		toastr.error(obj.msg,module);
	                	        	}
	                	        }
	                	    }); 
	                   }
                }); 
				if(afterMethod){
					afterMethod(ids);
				}
			});
		};

		$.fn.delPspUseDialog = function(dataSource, url ,module,preMethod,afterMethod) { 
			var $this = this;
			if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
			this.bind('click', function() {//按钮绑定点击事件
				var dataObj = dataSource.children("tbody").children(
						".active");//找到是否有被选中的数据项
				if (dataObj.length == 0) {//若没有选中或者选中的行数大于的1话
					bootbox.alert("您必须选择相应的记录进行删除");
					return;
				}
				var ids="";
				var names="";
				dataObj.each(function(i, item){
					var id = $(item).find(".checkboxes").val();
					var name = $(item).children('td').eq(1).html();
					var isRent = $(item).children('td').eq(3).html();
					var nowCar = $(item).children('td').eq(4).html();
					if(isRent == "是"){
						bootbox.alert("【"+name+"】车位已经被租赁，无法删除");
						return;
					};
					if(nowCar !=""){
						bootbox.alert("【"+name+"】车位上已停车【"+nowCar+"】，无法删除");
						return;
					};
					ids=ids+id+",";
					names=names+"【"+name+"】、";
				});
				if(ids.length==0)
					return;
				ids=ids.substring(0,ids.length-1);
				names=names.substring(0,names.length-1);
				var confirmMsg = "你确定要删除"+names+"?";
				if(preMethod){
					ids = preMethod(ids);
				}
				if(ids==null||ids=="")
					return;
				bootbox.confirm(confirmMsg, function(result) {
	                   if(result){
	                	   $.ajax({
	                	        url: url+ids,
	                	        async: false,
	                	        error: function(request) {
	                	            alert("Connection error");
	                	        },
	                	        success: function(data) {
	                	        	var obj = data;
	                	        	if(obj.success){
	                	        		toastr.success(obj.msg, module);
	                	        		dataSource.dataTable().fnDraw(false);
	                	        	}
	                	        	else{
	                	        		toastr.error(obj.msg,module);
	                	        	}
	                	        }
	                	    }); 
	                   }
                }); 
				if(afterMethod){
					afterMethod(ids);
				}
			});
		};

		$.fn.enableUseDialog = function(dataSource, url ,module,preMethod,afterMethod) { 
			var $this = this;
			if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
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
				var confirmMsg = "你确定要启用"+names+"?";
				if(preMethod){
					ids = preMethod(ids);
				}
				if(ids==null||ids=="")
					return;
				bootbox.confirm(confirmMsg, function(result) {
	                   if(result){
	                	   $.ajax({
	                	        url: url+ids,
	                	        async: false,
	                	        error: function(request) {
	                	            alert("Connection error");
	                	        },
	                	        success: function(data) {
	                	        	var obj = data;
	                	        	if(obj.success){
	                	        		toastr.success(obj.msg, module);
	                	        		dataSource.dataTable().fnDraw(false);
	                	        	}
	                	        	else{
	                	        		toastr.error(obj.msg,module);
	                	        	}
	                	        }
	                	    }); 
	                   }
                }); 
				if(afterMethod){
					afterMethod(ids);
				}
			});
		};

		$.fn.disableUseDialog = function(dataSource, url ,module,preMethod,afterMethod) { 
			var $this = this;
			if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
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
				var confirmMsg = "你确定要禁用"+names+"?";
				if(preMethod){
					ids = preMethod(ids);
				}
				if(ids==null||ids=="")
					return;
				bootbox.confirm(confirmMsg, function(result) {
	                   if(result){
	                	   $.ajax({
	                	        url: url+ids,
	                	        async: false,
	                	        error: function(request) {
	                	            alert("Connection error");
	                	        },
	                	        success: function(data) {
	                	        	var obj = data;
	                	        	if(obj.success){
	                	        		toastr.success(obj.msg, module);
	                	        		dataSource.dataTable().fnDraw(false);
	                	        	}
	                	        	else{
	                	        		toastr.error(obj.msg,module);
	                	        	}
	                	        }
	                	    }); 
	                   }
                }); 
				if(afterMethod){
					afterMethod(ids);
				}
			});
		};

		$.fn.openUseDialog = function(dataSource, url ,module,preMethod,afterMethod) { 
			var $this = this;
			if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
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
				var confirmMsg = "你确定要启用"+names+"?";
				if(preMethod){
					ids = preMethod(ids);
				}
				if(ids==null||ids=="")
					return;
				bootbox.confirm(confirmMsg, function(result) {
	                   if(result){
	                	   $.ajax({
	                	        url: url+ids,
	                	        async: false,
	                	        error: function(request) {
	                	            alert("Connection error");
	                	        },
	                	        success: function(data) {
	                	        	var obj = data;
	                	        	if(obj.success){
	                	        		toastr.success(obj.msg, module);
	                	        		dataSource.dataTable().fnDraw(false);
	                	        	}
	                	        	else{
	                	        		toastr.error(obj.msg,module);
	                	        	}
	                	        }
	                	    }); 
	                   }
                }); 
				if(afterMethod){
					afterMethod(ids);
				}
			});
		};

		$.fn.closeUseDialog = function(dataSource, url ,module,preMethod,afterMethod) { 
			var $this = this;
			if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
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
				var confirmMsg = "你确定要禁用"+names+"?";
				if(preMethod){
					ids = preMethod(ids);
				}
				if(ids==null||ids=="")
					return;
				bootbox.confirm(confirmMsg, function(result) {
	                   if(result){
	                	   $.ajax({
	                	        url: url+ids,
	                	        async: false,
	                	        error: function(request) {
	                	            alert("Connection error");
	                	        },
	                	        success: function(data) {
	                	        	var obj = data;
	                	        	if(obj.success){
	                	        		toastr.success(obj.msg, module);
	                	        		dataSource.dataTable().fnDraw(false);
	                	        	}
	                	        	else{
	                	        		toastr.error(obj.msg,module);
	                	        	}
	                	        }
	                	    }); 
	                   }
                }); 
				if(afterMethod){
					afterMethod(ids);
				}
			});
		};

		$.fn.recoverUseDialog = function(dataSource, url ,module,preMethod,afterMethod) { 
			var $this = this;
			if (!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
			this.bind('click',function(){//按钮绑定点击事件
				var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
				if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
					bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据再进行启用！");   
					return;
				}
				var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
				$.ajax({
					url : url + id,
					success : function(data) {
						recover.callback(data, dialog);
					},
					error : function(error) {
						alert(error.status + "," + error.readyState);
					}
				});
				var confirmMsg = "你确定要回复本条出入场记录?";
				if(preMethod){
					ids = preMethod(ids);
				}
				if(ids==null||ids=="")
					return;
				bootbox.confirm(confirmMsg, function(result) {
	                   if(result){
	                	   $.ajax({
	                	        url: url+ids,
	                	        async: false,
	                	        error: function(request) {
	                	            alert("Connection error");
	                	        },
	                	        success: function(data) {
	                	        	var obj = data;
	                	        	if(obj.success){
	                	        		toastr.success(obj.msg, module);
	                	        		dataSource.dataTable().fnDraw(false);
	                	        	}
	                	        	else{
	                	        		toastr.error(obj.msg,module);
	                	        	}
	                	        }
	                	    }); 
	                   }
                }); 
				if(afterMethod){
					afterMethod(ids);
				}
			});
		};
		
		//编辑事件
		$.fn.recover = function(dataSource,dialog,url){//参数1:数据源 参数2：对应的弹窗实体 参数3：如果弹出的弹出需要获取数据的话，那就参数3就是用于获取数据的地址
			var $this = this;
			if(!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
				return;
			this.bind('click',function(){//按钮绑定点击事件
				var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
				if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
					bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据再进行启用！");   
					return;
				}
				var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
					$.ajax({
						url : url + id,
						success : function(data) {
							recover.callback(data, dialog);
						},
						error : function(error) {
							alert(error.status + "," + error.readyState);
						}
					});
				});
			};
})();

var parseURL = function(replaceObj,obj){
	var $section = replaceObj;//区域对象
	if(!obj)return;
	var html = $section.parent().html();//获取
	var regExp = new RegExp('@{'+obj+'\.[a-zA-Z0-9_]+}','g');
	var html = html.replace(regExp,function(word){
		var x = word.slice(2,word.length).slice(0,-1);
		console.log(eval(x));
		return eval(x);
	});
	$section.parent().html(html);
};

/**
 *  table刷新
 */
var reload = function(){
	oTable.fnDraw(); 
}; 

var clearForm = function(){
	$("#searchForm")[0].reset();
	$("#searchForm .select2").val(null).trigger("change");
	$("#searchForm .bs-select").val('-1').trigger("change");
	oTable.fnDraw(); 
}

var dbclick = function(){
	var init = function(dblObj,modal,editAndDblUrl,modalForm){
		dblObj.dbclick(modal,editAndDblUrl,modalForm);//双击查看详情
	}
	return{
		init:function(dblObj,modal,editAndDblUrl,modalForm){
			init(dblObj,modal,editAndDblUrl,modalForm);
		}
	}
}();

var timeformat = function(time, format) {
    var t = new Date(time);
    var tf = function(i) {
        return (i < 10 ? '0': '') + i
    };
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g,
    function(a) {
        switch (a) {
        case 'yyyy':
            return tf(t.getFullYear());
            break;
        case 'MM':
            return tf(t.getMonth() + 1);
            break;
        case 'mm':
            return tf(t.getMinutes());
            break;
        case 'dd':
            return tf(t.getDate());
            break;
        case 'HH':
            return tf(t.getHours());
            break;
        case 'ss':
            return tf(t.getSeconds());
            break;
        }
    });
}

