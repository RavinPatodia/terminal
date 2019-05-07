/**
 * 格式化传入的禁用启用enableFlag的值。如果为true返回是，false返回否
 */
var formatEnableFlag = function(data){
	var result;
    if(data==true){
    	result = "启用";
    }
    else{
    	result = "禁用";
    }
    return result;
}

