var deviceSelect2 = function(){
	var initAllDrivewaySelect=function(){
		$('.allDriveway').select2({
			placeholder: "搜索车道信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的车道信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/driveway/getDriveway?",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	name:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        },
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.text;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}

	var initInDrivewaySelect=function(){
		$('.inDriveway').select2({
			placeholder: "搜索进口车道信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的车道信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/driveway/getDrivewayIn?",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	name:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.text;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}

	var initOutDrivewaySelect=function(){
		$('.outDriveway').select2({
			placeholder: "搜索出口车道信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的车道信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/driveway/getDrivewayOut?",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	name:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.text;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	 return {
	        initAllDrivewaySelect:function(){
	        	initAllDrivewaySelect();
	        },
	        initInDrivewaySelect:function(){
	        	initInDrivewaySelect();
	        },
	        initOutDrivewaySelect:function(){
	        	initOutDrivewaySelect();
	        }
	    };
}();

var operSelect2=function(){
	 var initOperNameOperName=function(){
	    	$('.operName_operName').select2({
	    			placeholder: "搜索操作员名称",
	    	        allowClear: true,
	    	        formatNoMatches: "没有匹配的操作员名称",
	    	        formatSearching: "查询中...",
	    	        ajax: {
	    	            url: "/park/admin/sys/operater/getOperNames?"+"t="+ Math.random(),
	    	            dataType: 'json',
	    	            delay: 250,
	    	            data: function (term, page) {
	    	                return {
	    	                	operName:term
	    	                };
	    	            },
	    	            results: function (data, page) {
	    	                return {results: data};
	    	            }
	    	        }, 
	    	        initSelection: function (element, callback) {
	    	        	var id=element.id;
	    	        	var text = element.name;
	    	        	if(id!=''&&text!=""){
	    	                callback({id:id,text:text});
	    	        	}
	    	        },
	    			minimumInputLength: 0,
	    			escapeMarkup: function (m) {
	    	            return m;
	    	        }
	    	    });
	    }
	    
	    var initOperNameId=function(){
	    	$('.operName_id').select2({
				placeholder: "搜索操作员名称",
		        allowClear: true,
		        formatNoMatches: "没有匹配的操作员名称",
		        formatSearching: "查询中...",
		        ajax: {
		            url: "/park/admin/sys/operater/getOperIds?"+"t="+ Math.random(),
		            dataType: 'json',
		            delay: 250,
		            data: function (term, page) {
		                return {
		                	operName:term
		                };
		            },
		            results: function (data, page) {
		                return {results: data};
		            }
		        }, 
		        initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength: 0,
				escapeMarkup: function (m) {
		            return m;
		        }
		    });
	    }
	    var initNameName=function(){
	    	$('.oper_name_name').select2({
				placeholder: "搜索操作员姓名",
		        allowClear: true,
		        formatNoMatches: "没有匹配的操作员姓名",
		        formatSearching: "查询中...",
		        ajax: {
		            url: "/park/admin/sys/operater/getNames?"+"t="+ Math.random(),
		            dataType: 'json',
		            delay: 500,
		            data: function (term, page) {
		                return {
		                	name:term
		                };
		            },
		            results: function (data, page) {
		                return {results: data};
		            }
		        },
		        initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength: 0,
				escapeMarkup: function (m) {
		            return m;
		        }
		    });
	    }
	    var initNameId=function(){
	    	$('.oper_name_id').select2({
				placeholder: "搜索操作员姓名",
		        allowClear: true,
		        formatNoMatches: "没有匹配的操作员姓名",
		        formatSearching: "查询中...",
		        ajax: {
		            url: "/park/admin/sys/operater/getNameId?"+"t="+ Math.random(),
		            dataType: 'json',
		            delay: 500,
		            data: function (term, page) {
		                return {
		                	name:term
		                };
		            },
		            results: function (data, page) {
		                return {results: data};
		            }
		        },
		        initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength: 0,
				escapeMarkup: function (m) {
		            return m;
		        }
		    });
	    }
	    
    	
	return {
			initOperNameOperName:function(){
				initOperNameOperName();
	        },
	        initOperNameId:function(){
	        	initOperNameId();
	        },
	        initNameName:function(){
	        	initNameName();
	        },
	        initNameId:function(){
	        	initNameId();
	        }
	};
}();

var userSelect2=function(){
	 	var initUserUaccUacc=function(){
	    	$('.user_uacc_uacc').select2({
	    		placeholder: "搜索客户名",
	            allowClear: true,
	            formatNoMatches: "没有匹配的客户名",
	            formatSearching: "查询中...",
	            ajax: {
	                url: "/park/user/user/getUserUaccNotBlack?"+"t="+ Math.random(),
	                dataType: 'json',
	                delay: 500,
	                data: function (term, page) {
	                    return {
	                    	uacc:term
	                    };
	                },
	                results: function (data, page) {
	                    return {results: data};
	                }
	            },
	            initSelection: function (element, callback) {
	            	var id=element.id;
	            	var text = element.name;
	            	if(id!=''&&text!=""){
	                    callback({id:id,text:text});
	            	}
	            },
	    		minimumInputLength: 0,
	    		escapeMarkup: function (m) {
	                return m;
	            }
	        });
	    }
	    var initUserNameName=function(){
	    	$('.user_name_name').select2({
	    		placeholder: "搜索客户姓名",
	            allowClear: true,
	            formatNoMatches: "没有匹配的客户姓名",
	            formatSearching: "查询中...",
	            ajax: {
	                url: "/park/user/user/getUserNameNameNotBlack?"+"t="+ Math.random(),
	                dataType: 'json',
	                delay: 500,
	                data: function (term, page) {
	                    return {
	                    	name:term
	                    };
	                },
	                results: function (data, page) {
	                    return {results: data};
	                }
	            },
	            initSelection: function (element, callback) {
	            	var id=element.id;
	            	var text = element.name;
	            	if(id!=''&&text!=""){
	                    callback({id:id,text:text});
	            	}
	            },
	    		minimumInputLength: 0,
	    		escapeMarkup: function (m) {
	                return m;
	            }
	        });
	    }
	    
	    var initUserNameId=function(){
	    	$('.user_name_id').select2({
	    		placeholder: "搜索客户姓名",
	            allowClear: true,
	            formatNoMatches: "没有匹配的客户姓名",
	            formatSearching: "查询中...",
	            ajax: {
	                url: "/park/user/user/getUserNameIdNotBlack?"+"t="+ Math.random(),
	                dataType: 'json',
	                delay: 500,
	                data: function (term, page) {
	                    return {
	                    	name:term
	                    };
	                },
	                results: function (data, page) {
	                    return {results: data};
	                }
	            },
	            initSelection: function (element, callback) {
	            	var id=element.id;
	            	var text = element.name;
	            	if(id!=''&&text!=""){
	                    callback({id:id,text:text});
	            	}
	            },
	    		minimumInputLength: 0,
	    		escapeMarkup: function (m) {
	                return m;
	            }
	        });
	    }
	    initNotBlackNameId=function(){
	    	$('.notBlack_name_id').select2({
				placeholder: "搜索用户",
		      allowClear: true,
		      formatNoMatches: "没有匹配的用户",
		      formatSearching: "查询中...",
		      ajax: {
		          url: pageUrl.user_user_getUserNameIdNotBlack,
		          dataType: 'json',
		          delay: 500,
		          data: function (term, page) {
		              return {};
		          },
		          results: function (data, page) {
		              return {results: data};
		          }
		      }, 
		      initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength: 0,
				escapeMarkup: function (m) {
		          return m;
		      }
		  });
	    }
	return {
			initUserUaccUacc:function(){
				initUserUaccUacc();
	        },
	        initUserNameName:function(){
	        	initUserNameName();
	        },
	        initUserNameId:function(){
	        	initUserNameId();
	        },
	        initNotBlackNameId:function(){
	        	initNotBlackNameId();
	        }
	};
}();
var blackListSelect2=function(){
	
	var initBlackNameId=function(){
		$('.balckUser_name_id').select2({
			placeholder: "搜索黑名单名客户",
	        allowClear: true,
	        formatNoMatches: "没有匹配的黑名单客户",
	        formatSearching: "查询中...",
	        ajax: {
	            url: pageUrl.user_blackList_getUserNames,
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	name:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	
	var initBlackNumId=function(){
		$('.balckUser_num_id').select2({
			placeholder: "搜索黑名单编号",
	        allowClear: true,
	        formatNoMatches: "没有匹配的黑名单编号",
	        formatSearching: "查询中...",
	        ajax: {
	            url: pageUrl.user_blackList_getBlackIds,
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	blacklistId:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
		
		
	}
	 return {
		 	initBlackNameId:function(){
		 		initBlackNameId();
	        },
	        initBlackNumId:function(){
	        	initBlackNumId();
	        }
	    };
	
}();
var ugroupSelect2=function(){
	 	var initUGroupNameId=function(){
	    	$('.ugroup_name_id').select2({
	    		placeholder: "搜索客户组",
	            allowClear: true,
	            formatNoMatches: "没有匹配的客户组",
	            formatSearching: "查询中...",
	            ajax: {
	                url: "/park/user/userGroup/getNameId?"+"t="+ Math.random(),
	                dataType: 'json',
	                delay: 500,
	                data: function (term, page) {
	                    return {
	                    	ugroupName:term
	                    };
	                },
	                results: function (data, page) {
	                    return {results: data};
	                }
	            },
	            initSelection: function (element, callback) {
	            	var id=element.id;
	            	var text = element.name;
	            	if(id!=''&&text!=""){
	                    callback({id:id,text:text});
	            	}
	            },
	    		minimumInputLength: 0,
	    		escapeMarkup: function (m) {
	                return m;
	            }
	        });
	    }
		var initUGroupNameName=function(){
			$('.ugroup_name_name').select2({
				placeholder: "搜索客户组名称",
		        allowClear: true,
		        formatNoMatches: "没有匹配的客户组名称",
		        formatSearching: "查询中...",
		        ajax: {
		            url:  "/park/user/userGroup/getNameName?"+"t="+ Math.random(),
		            dataType: 'json',
		            delay: 500,
		            data: function (term, page) {
		                return {
		                	name:term
		                };
		            },
		            results: function (data, page) {
		                return {results: data};
		            }
		        }, 
		        initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength: 0,
				escapeMarkup: function (m) {
		            return m;
		        }
		    });
	    }
		var initUGroupUidUid=function(){
			$('.group_uid_uid').select2({
				placeholder: "搜索客户组编号",
		        allowClear: true,
		        formatNoMatches: "没有匹配的客户组编号",
		        formatSearching: "查询中...",
		        ajax: {
		            url:  "/park/user/userGroup/getUidUid?"+"t="+ Math.random(),
		            dataType: 'json',
		            delay: 500,
		            data: function (term, page) {
		                return {
		                	ugroupId:term
		                };
		            },
		            results: function (data, page) {
		                return {results: data};
		            }
		        }, 
		        initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength: 0,
				escapeMarkup: function (m) {
		            return m;
		        }
		    });
	}
	var initMemberUGroupNameId=function(){
		$('.ugroup_member_name_id').select2({
			placeholder: "搜索客户组",
	        allowClear: true,
	        formatNoMatches: "没有匹配的客户组",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/user/userGroup/getMemberUGroupNameId?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	ugroupName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });	
	}
	var initLongMemberUgroupNameId=function(){
		$('.ugroup_long_member_name_id').select2({
			placeholder: "搜索客户组名称",
	        allowClear: true,
	        formatNoMatches: "没有匹配的客户组名称",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/user/userGroup/getLongMemberUGroupNameId?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	groupName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	  }
	var initTempUgroupNameId=function(){
		$('.ugroup_temp_name_id').select2({
			placeholder: "搜索客户组名称",
	        allowClear: true,
	        formatNoMatches: "没有匹配的客户组名称",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/user/userGroup/getTempUGroupNameId?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	groupName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });	
	}
	var initNotLongGroupNameId=function(){
		$('.ugroup_notlong_name_id').select2({
			placeholder: "搜索客户组名称",
	        allowClear: true,
	        formatNoMatches: "没有匹配的客户组名称",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/user/userGroup/getNotlongNameId?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	groupName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });	
	}
	return {
		initUGroupNameId:function(){
			initUGroupNameId();
		},
		initUGroupNameName:function(){
			initUGroupNameName();
		},
		initUGroupUidUid:function(){
			initUGroupUidUid();
		},
		initMemberUGroupNameId:function(){
			initMemberUGroupNameId();
		},
		initLongMemberUgroupNameId:function(){
			initLongMemberUgroupNameId();
		},
		initTempUgroupNameId:function(){
			initTempUgroupNameId();
		},
		initNotLongGroupNameId:function(){
			initNotLongGroupNameId();
		}
	};
}();
var carSelect2 = function(){
	var initLicensePlateLicensePlate=function(){
    	$('.car_licensePlate_licensePlate').select2({
    		placeholder: "搜索车牌号",
            allowClear: true,
            formatNoMatches: "没有匹配的车牌号",
            formatSearching: "查询中...",
            ajax: {
                url:  "/park/caradmission/car/licensePlate?"+"t="+ Math.random(),
                dataType: 'json',
                delay: 500,
                data: function (term, page) {
                    return {
                    	licensePlate:term
                    };
                },
                results: function (data, page) {
                    return {results: data};
                }
            },
            initSelection: function (element, callback) {
            	var id=element.id;
            	var text = element.name;
            	if(id!=''&&text!=""){
                    callback({id:id,text:text});
            	}
            },
    		minimumInputLength: 0,
    		escapeMarkup: function (m) {
                return m;
            }
        });
    }
	var initCarType=function(){
		$('.car_type').select2({
			placeholder: "搜索车牌类型",
	        allowClear: true,
	        formatNoMatches: "没有匹配的车牌类型",
	        formatSearching: "查询中...",
	        ajax: {
	            url:  "/park/caradmission/carType/typeName?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 250,
	            data: function (term, page) {
	                return {
	                	type:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	var initCarColor=function(){
		$('.car_color').select2({
			placeholder: "搜索车辆颜色",
	        allowClear: true,
	        formatNoMatches: "没有匹配的车辆颜色",
	        formatSearching: "查询中...",
	        ajax: {
	            url:  "/park/caradmission/carType/color?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	carColor:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        },
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	var initCarModule=function(){
		$('.car_module').select2({
			placeholder: "搜索车辆型号",
	        allowClear: true,
	        formatNoMatches: "没有匹配的车辆型号",
	        formatSearching: "查询中...",
	        ajax: {
	            url:  "/park/caradmission/carType/module?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	carModel:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        },
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	return {
		 	initLicensePlateLicensePlate:function(){
	        	initLicensePlateLicensePlate();
	        },
	        initCarType:function(){
	        	initCarType();
	        },
	        initCarColor:function(){
	        	initCarColor();
	        },
	        initCarModule:function(){
	        	initCarModule();
	        }
	};
}();
//设备管理模块下拉框
var deviceSelect = function(){
	var initBayonet=function(){
		$('.bayonetName').select2({
			placeholder: "搜索卡口信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的卡口信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/driveway/getBayonet?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	bayonetName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	var initBrakeMachine=function(){
	    $('.brakeMachine').select2({
			placeholder: "搜索闸机信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的闸机信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/driveway/getBrake?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	brakeMachine:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	var initLedScreenIn=function(){
		$('.ledScreenIn').select2({
			placeholder: "搜索入口显示屏信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的显示屏信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/driveway/getScreenIn?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	ledScreenIn:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	var initLedScreenOut=function(){
	    $('.ledScreenOut').select2({
			placeholder: "搜索出口显示屏信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的显示屏信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/driveway/getScreenOut?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	ledScreenOut:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	var initTerminal=function(){
		$('.terminalPK').select2({
			placeholder: "搜索终端机信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的终端机信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/driveway/getTerminal?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	terminalName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	return {
			initBayonet:function(){
				initBayonet();
	        },
	        initBrakeMachine:function(){
	        	initBrakeMachine();
	        },
	        initLedScreenIn:function(){
	        	initLedScreenIn();
	        },
	        initLedScreenOut:function(){
	        	initLedScreenOut();
	        },
	        initTerminal:function(){
	        	initTerminal();
	        }
	};
}();
//停车场管理模块下拉框
var parkSelect = function(){
	var initRule = function(){
		$('.rulePK').select2({
			placeholder: "搜索租赁规则",
	        allowClear: true,
	        formatNoMatches: "没有匹配的租赁规则",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/park/getChargeRule",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	ruleName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	return {
		initRule:function(){
			initRule();
        }
	};
}();
//车位锁管理模块下拉框
var pspLockSelect = function(){
	var initPsp = function(){
		$('.pspId').select2({
			placeholder: "搜索车位",
		    allowClear: true,
		    formatNoMatches: "没有匹配的车位",
		    formatSearching: "查询中...",
		    ajax: {
		        url: "/park/device/pSpLock/getPspId?"+"t="+ Math.random(),
		        dataType: 'json',
		        delay: 500,
		        data: function (term, page) {
		            return {
		            	pspId:term
		            };
		        },
		        results: function (data, page) {
		            return {results: data};
		        }
		    }, 
		    initSelection: function (element, callback) {
		    	var id=element.id;
		    	var text = element.name;
		    	if(id!=''&&text!=""){
		            callback({id:id,text:text});
		    	}
		    },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
		        return m;
		    }
		});

	}
	return {
		initPsp:function(){
			initPsp();
        }
	};
}();
//显示屏管理模块下拉框
var screenSelect = function(){
	var initDataMaster = function(){
		$('.dataMasterName').select2({
			placeholder: "搜索数据转换主机信息",
	        allowClear: true,
	        formatNoMatches: "没有匹配的信息",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/device/screen/getDataMaster?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	dataMasterName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	return {
		initDataMaster:function(){
			initDataMaster();
        }
	};
}();
//车位管理模块下拉框
var pspSelect = function(){
	var initPark = function(){
		$('.parkName').select2({
			placeholder: "搜索停车场",
	        allowClear: true,
	        formatNoMatches: "没有匹配的停车场",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/pSp/pSp/getPark?"+"t="+ Math.random(),
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	parkName:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	var initLicensePlate = function(){
		$('.car_licensePlate').select2({
			placeholder: "搜索车牌号",
	        allowClear: true,
	        formatNoMatches: "没有匹配的车牌号",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/park/caradmission/car/licensePlate",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	licensePlate:term
	                };
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        },
	        initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	return {
		initPark:function(){
			initPark();
        },
        initLicensePlate:function(){
        	initLicensePlate();
        }
	};
}();

var chargeSelect = function(){
	
	//长期收费规则组
	var initAnchorGroup = function(){
		$(".anchorGroup").select2({
			placeholder: "搜索长期规则组规则",
            allowClear: true,
            formatNoMatches: "没有匹配的长期规则组规则",
            formatSearching: "查询中...",
            ajax: {
                url: pageUrl.charge_anchorGroup_getAnchorGroup,
                dataType: 'json',
                delay: 250,
                data: function (term, page) {
                    return {
                        name: term
                    };
                },
                results : function(data, page) {
					return {
						results : data
					};
				}
            },
            initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength : 0,
			escapeMarkup : function(m) {
				return m;
			}
        });
	}
	
	//计时计次规则
	var initTimeAndMeterCharge = function(){
		$('.timeAndMeter').select2({
			placeholder : "搜索计时、计次收费规则",
			allowClear : true,
			formatNoMatches : "没有匹配的计时、计次收费规则",
			formatSearching : "查询中...",
			ajax : {
				url : pageUrl.charge_chargeRule_getTimeAndMeter,
				dataType : 'json',
				delay : 500,
				data : function(term, page) {
					return {
						chargeRuleCount:term
					};
				},
				results : function(data, page) {
					return {
						results : data
					};
				}
			},
			initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength : 0,
			escapeMarkup : function(m) {
				return m;
			}
		});
	}
	
	//优惠规则组
	var initDctGroup = function(){
		$('.dctGroup').select2({
			placeholder : "搜索优惠规则组",
			allowClear : true,
			formatNoMatches : "没有匹配的优惠规则组",
			formatSearching : "查询中...",
			ajax : {
				url : pageUrl.charge_dctRuleGroup_getDctGroup,
				dataType : 'json',
				delay : 500,
				data : function(term, page) {
					return {
						dctRuleGroupName:term
					};
				},
				results : function(data, page) {
					return {
						results : data
					};
				}
			},
			initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength : 0,
			escapeMarkup : function(m) {
				return m;
			}
		});
	}
	
	return {
		initAnchorGroup:function(){
			initAnchorGroup();
        },
        initTimeAndMeterCharge:function(){
        	initTimeAndMeterCharge();
        },
        initDctGroup:function(){
        	initDctGroup();
        }
	};
}();