 //上下移动插件
 ;(function(){
	  	$.fn.initOrder  = function(json,jsonName){
	  		var $that = this;
	  		var util_fc = {
	  			add_current:function(){
	  				$('.active',$that).removeClass('active');
	  				$(this).addClass('active');
	  			},
	  			goPrev:function(){
	  				var $current = $('.active',$that);//获取选中项
	  				if(!util_fc.if_select($current))
	  					return;

	  				var prevSiblingIndex = $current.index()-1;

	  				if(prevSiblingIndex == -1){//是否有前一项
	  					util_fc.showTip('已经到顶部了！');
	  					return;
	  				}	 
	  				$current.insertBefore($that.children('.result_pt').find('span').eq(prevSiblingIndex)); 					
	  				return;
	  			},
	  			goNext:function(){
	  				var $current = $('.active',$that);//获取选中项
	  				if(!util_fc.if_select($current))
	  					return;

	  				var nextSiblingIndex = $current.index()+1;
	  				
	  				if(nextSiblingIndex == $that.children('.result_pt').find('span').length){//是否有后一项
	  					util_fc.showTip('已经到底部了！');
	  					return;
	  				}	 
	  				$current.insertAfter($that.children('.result_pt').find('span').eq(nextSiblingIndex)); 					
	  				return;
	  			},
	  			goTop:function(){
	  				var $current = $('.active',$that);
	  				$current.insertBefore($that.children('.result_pt').find('span').eq(0));
	  				return;
	  			},
	  			goBottom:function(){
	  				var $current = $('.active',$that);
	  				$current.insertAfter($that.children('.result_pt').find('span').eq($that.children('.result_pt').find('span').length - 1));
	  				return;
	  			},
	  			showTip : function(info){
	  				$('.error_log',$that).fadeIn().text(info);
	  				setTimeout(function(){
	  					$('.error_log',$that).fadeOut('1s');
	  				},3000);
	  			},
	  			if_select:function(obj){
	  				var $current = obj;//获取选中项
	  				if(!$current.length){//是否有选中项
	  					util_fc.showTip('请选择一项');
	  					return false;
	  				}
	  				return true;
	  			},
	  			addItem:function(){
	  				var temp = $(this).clone();
	  				$('.result_pt',$that).append(temp);
	  				$(this).remove();
	  			},
	  			deleteItem:function(){
	  				var temp = $that.children('.result_pt').find('.active').clone();
	  				 $('.fake_select',$that).append(temp);
	  				 $that.children('.result_pt').find('.active').remove();
	  			},
	  			changeValue:function(){
	  				var value_text = $('.value_text',$that);
	  				var itemList = $('.result_pt > span',$that);
	  				var item_value = [];
	  				itemList.each(function(index,v){
	  					item_value.push($(v).data('id'));
	  				});
	  				value_text.val(item_value.join(','));
	  			},
	  			getValue:function(){
	  				$($that).off('click','.result_pt > span');//解除事件
		  			$($that).off('click','.fake_select > span');
		  			$('.top,.prev,.next,.bottom,.delete,.operation_pt > a',$that).off('click');
	  				if(!json)
	  					return;
	  				var itemlist = json[jsonName];
					var $left_pt = $('.fake_select',$that).empty();
					var $right_pt = $('.result_pt',$that).empty();
					 $(itemlist).each(function(i,v){
						//console.log("id="+itemlist[i].id+",text="+itemlist[i].text);
						if(!itemlist[i].if_select)
							$left_pt.append('<span data-id="'+itemlist[i].id+'">'+itemlist[i].text+'</span>');
						else
							$right_pt.append('<span data-id="'+itemlist[i].id+'">'+itemlist[i].text+'</span>');

						util_fc.changeValue();
					}); 
	  			}
	  		};
	  		var init = function(){
	  			util_fc.getValue();
	  			$($that).on('click','.result_pt > span',util_fc.add_current);//绑定选项的点击事件
	  			$('.top',$that).on('click',util_fc.goTop);
	  			$('.prev',$that).on('click',util_fc.goPrev);
	  			$('.next',$that).on('click',util_fc.goNext);
	  			$('.bottom',$that).on('click',util_fc.goBottom);
	  			$($that).on('click','.fake_select > span',util_fc.addItem);
	  			$('.delete',$that).on('click',util_fc.deleteItem);

	  			$('.operation_pt > a' ,$that).bind('click',util_fc.changeValue);
	  			$($that).on('click','.fake_select > span',util_fc.changeValue);
	  		};

	  		return this.each(function(){
	  			init();
	  		});
	  	}
	  })();