//工具函数---start
function CreateEmptyString(l) {
    var a = [];
    for (var i = 0; i < l; i++) {
        a[i] = '*';
    }
    return a.join('');
}


//向列表中添加表项
function AddList(sel, text, value) {
    var option = document.createElement("option");
    option.appendChild(document.createTextNode(text));
    option.setAttribute("value", value);
    sel.appendChild(option);
    return;
}


//去除时间格式内的HTTP关键字
function FormatTime(time) {
    var tmp_string = time.split(' ');
    var tmp_string_cp = tmp_string[1].split(':');
    return tmp_string[0] + '%20' + tmp_string_cp[0] + '%3A' + tmp_string_cp[1] + '%3A' + tmp_string_cp[2];
}



var IS_SHOW_DEBUG = 0;  //0-关闭, 1-开启
var gdownloadID = 1;   //下载编码
var gdownloadID2 = 1;

function DebugAlert(str) {
    if (IS_SHOW_DEBUG == 1) {
        alert(str);
    }
}

String.prototype.replaceAll = function (f, r) {
    var s = new String(this);
    while (s.indexOf(f) != -1) {
        s = s.replace(f, r);
    }
    return s.toString();
}

/**
* 解析从控件返回的xml字符串
*/
function loadXML(xmlString) {
    if (!g_xmlActive) {
        return;
    }
    g_xmlActive.loadXML(xmlString);
    if (0 == g_xmlActive.parseError.errorCode) {
        return g_xmlActive;
    }
    else {
        //        alert("xml解析错误:" + g_xmlActive.parseError.reason);
        return null;
    }
}

function getElementById(dom, tagName) {
    for (var i = 0; i < dom.childNodes.length; i++) {
        var node = dom.childNodes[i];
        if (node.baseName == tagName) {
            return node.nodeTypedValue;
        }
        else {
            if (node.childNodes.length != 0) {
                getElementById(dom.childNodes[i], tagName);
            }
        }
    }
}

//工具函数---end



//全局变量---start

var g_UserLoginId = '';
var g_UserName = '';    //登录用户名
var g_PassWord = '';    //登录用户密码
var g_ServerIP = '';    //服务器IP地址
var g_imosActivePlayer = null;
var g_curFrameNum = '';
var g_xmlActive = null;



//全局变量---end



//================>功能函数---start
//检查控件
function InitPage() {
    g_imosActivePlayer = document.all.h3c_IMOS_ActiveX;

    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
    }

    var xmldoc;
    try {
        xmldoc = new ActiveXObject("Microsoft.XMLDOM");
        if (!xmldoc) {
            xmldoc = new ActiveXObject("MSXML2.DOMDocument.3.0");
        }
    }
    catch (e) {
    }
    g_xmlActive = xmldoc;
    if (!g_xmlActive) {
        alert("xml解析器获取错误，将导致某些功能不可用");
    }
    else {
        g_xmlActive.async = "false";
    }
    DoLogin();
    DoGetCamList();
    //获取初始化的xml信息
    //GetInitXML();
}

//登录    
function DoLogin() {
    //    if (!g_imosActivePlayer) {
    //        alert("未安装控件，请先安装后再使用本页面");
    //        return;
    //    }
    //    var serverIP = document.getElementById("ServerIPText").value;
    //    var userName = document.getElementById("UserNameText").value;
    //    var passWd = document.getElementById("PassWordText").value.toString();

    //    serverIP = "192.168.1.100";
    //    userName = "test";
    //    passWd = "test";

    //alert(typeof passWd);
    var flag = g_imosActivePlayer.IMOSAX_InitOCX(serverIP, "8800", userName, passWd, 1);
    //    if (flag == 0) {
    //        alert("宇视登陆成功");
    //    } else {
    //        alert("宇视登陆失败");
    //    }

    //获取用户信息
    var retStr = g_imosActivePlayer.IMOSAX_GetUserLoginInfo();
    // alert(retStr);
    var userObj = loadXML(retStr);

    g_UserLoginId = userObj.documentElement.selectNodes("//LOGININFO/UserLoginIDInfo/UserLoginCode")[0].text;
    // alert(g_UserLoginId);
}


//退出登录
function DoLogout() {
    if (g_imosActivePlayer) {
        var flag = g_imosActivePlayer.IMOSAX_UnregOCX();
        //alert("宇视退出状态："+flag);
        if (0 != flag) {
            //    alert("宇视系统成功退出");
            //暂时不提示
        }
    }
}

//启动轮切
function DoStartSwitch() {
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var cameraId = document.getElementById("SwitchIDText").value;
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartFrameSwitch(frameNum, cameraId);
    if (0 == flag) {
        alert("启动轮切成功");
    }
    else {
        alert("停止轮切失败，错误码：" + flag);
    }
}

//释放实况
function DoStopSwitch() {
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopFrameSwitch(frameNum);
    if (0 == flag) {
        alert("停止轮切成功");
    }
    else {
        alert("停止轮切失败，错误码：" + flag);
    }
}
//ld 2013.12.4 双击播放
var frameNum = 1;
function dbPlay(obj) {
    $("#CamIDText").val(obj);
    DoStartPlay();
    frameNum++;
}

//启动实况
function DoStartPlay() {
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var cameraId = document.getElementById("CamIDText").value;
    //        var frameNum = g_curFrameNum;
    //    var cameraId = "sic235_1";
    //    var frameNum = 1;
    var frameNum = g_curFrameNum;
    //     frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartFrameLive(frameNum, cameraId);
    if (0 == flag) {
        //        alert("实况播放成功");
    }
    else {
        alert("播放实况失败，错误码：" + flag);
    }
}

//释放实况
function DoStopPlay() {
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopFramelive(frameNum);
    if (0 == flag) {
        alert("停止实况成功");
    }
    else {
        alert("停止实况失败，错误码：" + flag);
    }
}

//启动文件
function DoStartFile() {
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var cameraId = document.getElementById("FileIDText").value;
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartLocalReplay(frameNum, cameraId);
    if (0 == flag) {
        alert("播放文件成功");
    }
    else {
        alert("播放文件失败，错误码：" + flag);
    }
}

//释放实况
function DoStopFile() {
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartLocalReplay(frameNum);
    if (0 == flag) {
        alert("停止文件成功");
    }
    else {
        alert("停止文件失败，错误码：" + flag);
    }
}

//单步 
function DoStepFile() {
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var frameNum = g_curFrameNum;
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_OneStepPlay(frameNum);
    if (0 != flag) {
        alert("单步文件失败，错误码：" + flag);
    }
}



/**
*  查询类型枚举值
*/

/*
'CODE_TYPE' => 0, ///< 编码类型(除用户编码之外) //
'NAME_TYPE' => 1, ///< 名称类型(除用户名称之外) //
'USER_CODE_TYPE' => 2, ///< 用户编码类型 //
'USER_NAME_TYPE' => 3, ///< 用户名称类型 //
'TIME_TYPE' => 4, ///< 时间类型 //
'EVENT_TYPE' => 5, ///< 告警事件类型 //
'EVENT_SECURITY' => 6, ///< 告警事件级别 //
'EVENT_COMFIRMED' => 7, ///< 告警是否已确认 //
'EVENT_TIME' => 8, ///< 告警时间 //
'DEV_SUB_TYPE'=>9,///< 设备子类型//
'INDEX_TYPE'=>10,///< 索引类型(如视频输入通道索引,视频输出通道索引,串口索引,开关量索引) //
'RES_SUB_TYPE' => 11,///< 资源子类型 //
    
'SRV_TYPE' => 12,               ///< 业务类型 //
'MONITOR_TYPE' => 13,           ///< 监视器类型 //
'MONITOR_NAME' => 14,           ///< 监视器名称 //
'MONITOR_DOMAIN' => 15,         ///< 监视器所在域 //
'CAMERA_NAME' => 16,            ///< 摄像机名称 //
'USER_LOGIN_IP' => 17,          ///< 用户登录IP //
'MON_SRV_STATUS' => 18,         ///< 实况状态 //
'VOCBRD_SRV_STATUS' => 19,      ///< 广播状态 //
    
//begin added by y07083 2010.11.29 for 备份支持条件查询
'CASE_DESC'               => 20,       ///< 案例描述 //
'TASK_STATUS'             => 21,       ///< 任务状态 //
'TASK_SUB_TIME'           => 22,       ///< 任务提交时间 //
'TASK_END_TIME'           => 23,       ///< 任务结束时间 //
'BAK_START_TIME'          => 24,       ///< 备份开始时间 //
'BAK_END_TIME'            => 25,       ///< 备份结束时间 //
'FILE_CREATE_TIME'        => 26,       ///< 文件创建时间 //
'FILE_CAPACITY'           => 27,       ///< 文件容量 //
'FILE_TYPE '              => 28,       ///< 文件类型 //
'FILE_LOCK_FLAG'          => 29,       ///< 文件锁定标识 //
//end added by y07083 2010.11.29 for 备份支持条件查询
'PHY_DEV_IP'              => 31,       //设备IP地址查询// //added by lisufen 06653 for baobiao
'DOMAIN_FLAG'=>100,///< 域类型,现只支持本外域 //
//Added by liubing,2010-01-19,for VVD39123//
'EXT_DOMAIN_TPYE' => 200,      ///< 外域类型:上/下/平级域 //
'EXT_DOMAIN_IP' => 201,      ///< 外域IP //
'EXT_DOMAIN_PORT'  => 202,      ///< 外域PORT //
'EXT_DOMAIN_TRUNK_NUM' => 203,      ///< 外域干线数量 //
'EXT_DOMAIN_MULTICAST' => 204,      ///< 域间组播策略 //
'EXT_DOMAIN_SESSION' => 205,      ///< 外域引流标志 //
    
'JOB_STATUS' => 210,          ///< 升级任务状态 //
'JOB_CREATOR' => 211,          ///< 升级任务创建者 //
'JOB_EXEC_TIME' => 212,          ///< 升级任务调度时间 //
'JOB_ESTB_TIME' => 213,          ///< 升级任务制定时间 //
'JOB_END_TIME' => 214,          ///< 升级任务完成时间 //
'JOB_RESULT' => 215,          ///< 升级结果 //
    
'OPER_TIME' => 220,      ///< 操作时间 //
'OPER_IP' => 221,      ///< 操作者IP地址 //
'OPER_TYPE' => 222,      ///< 操作类型 //
'OPER_RESULT' => 223,      ///< 操作结果 //
'OPER_SERVICE_TYPE' => 224,      ///< 操作的业务类型 //
'OPER_OBJ' => 225,      ///< 操作对象 //

'LABEL_TIME' => 230,          ///< 标签时间点 //
'REC_START_TIME' => 231,          ///< 标签录像开始时间，格式为"hh:mm:ss" //
'REC_END_TIME' => 232,          ///< 标签录像结束时间，格式为"hh:mm:ss" //

'USER_FULL_NAME' => 240,      ///< 用户全名 //
'USER_TEL_PHONE' => 241,      ///< 用户座机电话 //
'USER_MOBILE_PHONE' => 242,      ///< 用户移动电话 //
'USER_EMAIL' => 243,      ///< 用户电子邮件 //
'USER_IS_LOCKED' => 244,      ///< 用户是否被锁定 //
'USER_DESC' => 245,           ///< 用户是否被锁定 //

'ROLE_PRIORITY' => 250,           ///< 角色优先级 //
//Begin:Added by zkf2003,2010-07-20 of第三方告警//
'DEV_TYPE' => 255, ///< 设备ID //
//End:Added by zkf2003,2010-07-20 of 第三方告警 VVD47339//
'QUERY_TYPE_MAX' => 251,        ///< 无效值 //
'RES_TYPE' => 256,                ///<资源类型//
'IS_QUERY_SUB' => 257,          ///<是否查下级域//
'RES_ID' => 258,                ///< 资源ID // //added by lisufen 06653 for ROLE VVD52953
'SUPPORT_LINK' => 259,          ///< 是否支持联动 //  //added by ykf1510 for 新增告警类型自定义VVD58218
'SUPPORT_GUARD' => 260,         ///< 是否支持布防 //  //added by ykf1510 for 新增告警类型自定义VVD58218
'RES_ATTRIBUTE' => 261          ///< 支持按资源属性查询 //  //added by kf1606 for vvd56513
*/

/**
* 查询条件枚举值
*/
/*
'EQUAL_FLAG' => 0, ///< 等于 //
'GREAT_FLAG' => 1, ///< 大于 //
'LITTLE_FLAG' => 2, ///< 小于 //
'GEQUAL_FLAG' => 3, ///< 大于等于 //
'LEQUAL_FLAG' => 4, ///< 小于等于 //
'LIKE_FLAG' => 5, ///< 模糊查询 //
'ASCEND_FLAG' => 6, ///< 升序 //
'DESCEND_FLAG' => 7, ///< 降序 //     
'NEQUAL_FLAG' => 8, ///< 不等于 //  //Begin:Modified by zkf2003 for VVD52949 2010-01-27//
'LOGIC_FLAG_MAX' => 9 ///< 无效值 // //Begin:Added by zkf2003 for VVD52949 2010-01-27//
*/

/**
* 资源类型枚举
*/

/*
define('RESTYPE_ORG','1');                      ///< 组织域 //
define('RESTYPE_OUTER_DOMAIN','2');             ///< 外域 //
define('RESTYPE_LOCAL_DOMAIN','3');             ///< 本域 //

define('RESTYPE_DM','11');                     ///< DM //
define('RESTYPE_MS','12');                     ///< MS //
define('RESTYPE_VX500','13');                  ///< VX500 //
define('RESTYPE_MONITOR','14');              ///< 监视器 //

define('RESTYPE_EC','15');                     ///< EC //
define('RESTYPE_DC','16');                     ///< DC //
// Add by nixueliang for V1 ECR 2010-10-12 //
define('RESTYPE_ECR','18');                     ///< ECR //

define('RESTYPE_CAMERA','1001');               ///< 摄像机 //
define('RESTYPE_ALARM_SOURCE','1003');         ///< 告警源 //

define('RESTYPE_STORAGE_DEV','1004');          ///< 存储设备 //
define('RESTYPE_TRANS_CHANNEL','1005');        ///< 透明通道 //

define('RESTYPE_ALARM_OUTPUT','1200');         ///< 告警输出 //

define('RESTYPE_GUARD_TOUR_RESOURCE','2001');  ///< 轮切资源 //
define('RESTYPE_GUARD_TOUR_PLAN','2002');      ///< 轮切计划 //
define('RESTYPE_MAP',' 2003');                  ///< 地图 //

define('RESTYPE_XP','2005');                   ///< XP //
define('RESTYPE_XP_WIN','2006');               ///< XP窗格 //
define('RESTYPE_GUARD_PLAN','2007');           ///< 布防计划 //

define('RESTYPE_CAMERA_GROUP','3002');         ///< 摄像机组 //
define('RESTYPE_MONITOR_GROUP','3003');        ///< 监视器组 //
define('RESTYPE_SALVO_RESOURCE','3004');       ///< 组显示资源 //
define('RESTYPE_GROUP_SWITCH_RESOURCE','3010');///< 组轮巡资源 //
define('RESTYPE_GROUP_SWITCH_PLAN','3011');    ///< 组轮巡计划资源 //
*/

/**
* 获取资源接口演示（以摄像机为例）
* 这个接口会用到很多枚举值，相应的枚举值见上边的注释部分
* 资源类型枚举有：摄像机（1001），ec（15），dc（16）等
* 查询条件解释，举例如下：
* “资源类型 == 摄像机” 可以解析为：
*       '<QueryType>256</QueryType>'+   //256表示按照‘资源’查询，在上述枚举中可以找到
*       '<LogicFlag>0</LogicFlag>'+     //0表示‘等于’          ，在上述枚举中可以找到
*       '<QueryData>1001</QueryData>'+  //1001表示‘摄像机’     ，在上述枚举中可以找到
*/
var reuncount = 0;
function DoGetCamList() {
    var strXmlQueryCondition = "";
    strXmlQueryCondition = '<?xml version="1.0" ?>' +
'<data>' +
  '<ItemNum>3</ItemNum>' +                   //总共有四个查询条件
'<QueryConditionList count="3">' +
'<item>' +                                   // 不查询子域
      '<QueryType>257</QueryType> ' +
      '<LogicFlag>0</LogicFlag> ' +
      '<QueryData>8</QueryData> ' +          //设备类型1.标清2.标清云台3.高清4.高清云台。这个8没有默认查询所有
    '</item>' +
  '<item>' +                                //查询的资源类型是摄像头
      '<QueryType>256</QueryType>' +
      '<LogicFlag>0</LogicFlag>' +
      '<QueryData>1001</QueryData>' +
    '</item>'
    +
  '<item>' +                               // 查询的资源子类型是固定摄像头
      '<QueryType>1</QueryType>' +
      '<LogicFlag>6</LogicFlag>' +
      '<QueryData />' +
    '</item>' +
  '<item>' +                               // 查询结果按照名称的升序排序
      '<QueryType>1</QueryType> ' +
      '<LogicFlag>6</LogicFlag>' +
      '<QueryData /> ' +
   '</item>' +
   '</QueryConditionList>' +
  '</data>';

    var strXmlQueryPageInfo = '<?xml version="1.0" ?> ' +
'<data>' +
  '<PageRowNum>200</PageRowNum>' +                     //最多返回100个记录
  '<PageFirstRowNumber>' + reuncount + '</PageFirstRowNumber>' +       //从第0个记录开始返回
  '<QueryCount>1</QueryCount>' +                       //还需要返回总记录数
'</data>';

    var retStr = "";
    retStr = g_imosActivePlayer.IMOSAX_QueryOrgResListEx("iccsid", strXmlQueryCondition, strXmlQueryPageInfo);

    var cameraListObj = loadXML(retStr);

    if (!cameraListObj) {
        return;
    }
    //获取xml信息cameraListObj.documentElement.xml
    //    cameraListObj.documentElement.xml = cameraListObj.documentElement.xml.replace("<", "?");
    //    var ccdata = "<result>"+
    // "<RespPageInfo>"+
    //"    <RowNum>2</RowNum>"+
    //"    <TotalRowNum>1156</TotalRowNum>"+
    //"  </RespPageInfo>"+
    //"  <ResList count=\"2\">"+
    //"    <item>"+
    //"      <ResItemV1>"+
    //"        <ResCode>317204</ResCode>"+
    //"        <ResName>1食堂东北角</ResName>"+
    //"        <ResType>1001</ResType>                      "+
    //"        <ResSubType>1</ResSubType>                   "+
    //"        <ResStatus>1</ResStatus>                     "+
    //"        <ResExtStatus>1</ResExtStatus>               "+
    //"        <ResIsBeShare>1</ResIsBeShare>               "+
    //"        <OrgCode>org@51606#20140218090349</OrgCode>  "+
    //"        <StreamNum>2</StreamNum>                     "+
    //"        <ResIsForeign>1</ResIsForeign>               "+
    //"      </ResItemV1>                                   "+
    //"      <OrgName>室外</OrgName>                        "+
    //"      <Reserve/>                                     "+
    //"    </item>                                          "+
    //"    <item>                                           "+
    //"      <ResItemV1>                                    "+
    //"        <ResCode>hic5421e-7-041_1</ResCode>          "+
    //"        <ResName>2办公楼东主路</ResName>             "+
    //"        <ResType>1001</ResType>                      "+
    //"        <ResSubType>3</ResSubType>                   "+
    //"        <ResStatus>1</ResStatus>                     "+
    //"        <ResExtStatus>0</ResExtStatus>               "+
    //"        <ResIsBeShare>1</ResIsBeShare>               "+
    //"        <OrgCode>org@51606#20140218090349</OrgCode>  "+
    //"        <StreamNum>1</StreamNum>                     "+
    //"        <ResIsForeign>0</ResIsForeign>               "+
    //"      </ResItemV1>                                   "+
    //"      <OrgName>室外</OrgName>                        "+
    //"      <Reserve/>                                     "+
    //"    </item>                                          "+
    //"  </ResList>                                         "+
    //"</result>";                                          
    //var vmdata = ccdata.replace(/</g, '&lt;');
    var vmdata = cameraListObj.documentElement.xml.replace(/</g, '&lt;');
    vmdata = vmdata.replace(/=/g, '&ld;');
//    $.ajax({
//        url: '/Setting/VideoList',
//        type: 'post',
//        async: false,
//        data: vmdata,
//        success: function (data) {
//            // alert("进入后台");
//            // if (data != '') {
//            //    alert(data);
//            // }
//
//        }, error: function () {
//            alert("错误");
//        }
//    });
//    if (retStr.length <= 150) {
//        //alert("加载成功");
//        return;
//    } else {
//        reuncount += 200;
//        DoGetCamList();
//    }
}
function DoGetMatrixList() {
    var retStr = "";
    retStr = g_imosActivePlayer.IMOSAX_QueryOrgResList("iccsid", 14, 0, 0, 0);
    var cameraListObj = loadXML(retStr);
    if (!cameraListObj) {
        return;
    }

    var vmdata = cameraListObj.documentElement.xml.replace(/</g, '&lt;');
    vmdata = vmdata.replace(/=/g, '&ld;');
    $.ajax({
        url: '/Setting/MonitorList',
        type: 'post',
        async: false,
        data: vmdata,
        success: function (data) {
            // if (data != '') {
            //    alert(data);
            //}
        },
        error: function () {
            alert('error');
        }
    });
}
//发送云台指令

function DoStartPtz() {
    //开始云台控制    /* 发送云台控制指令*/
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    //摄像机名称
    var cameraId = document.getElementById("CamIDText").value;
    var PtzCmd = parseInt(document.getElementById("PtzCmdText").value, 16);
    var ptzSpeed = 6;
    var flag = g_imosActivePlayer.IMOSAX_SendPtzCtrlCommand(cameraId, PtzCmd, ptzSpeed, ptzSpeed, 0);
    if (0 != flag) {
        alert("云台控制出错，错误码：" + flag);
    }

}

function DoStopPtz() {
    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    //摄像机名称
    var cameraId = document.getElementById("CamIDText").value;
    var PtzCmd = 0x0901;
    var ptzSpeed = 6;
    var flag = g_imosActivePlayer.IMOSAX_SendPtzCtrlCommand(cameraId, PtzCmd, ptzSpeed, ptzSpeed, 0);
    if (0 != flag) {
        alert("云台控制出错，错误码：" + flag);
    }
}


function DoQueryRecord() {
    var cameraId = document.getElementById("CamIDText").value;
    var startQueryTime = document.getElementById("StartTimeText").value;
    var endQueryTime = document.getElementById("EndTimeText").value;
    var retStr = g_imosActivePlayer.IMOSAX_QueryRecord(cameraId, startQueryTime, endQueryTime);
    alert(retStr);
    var recordListObj = loadXML(retStr);
    var RecordObjArray = recordListObj.documentElement.selectNodes("//RESLIST/item");

    var FileListObj = document.all.FileList;
    FileListObj.innerHTML = '';
    for (i = 0; i < RecordObjArray.length; i++) {
        var FileName = RecordObjArray[i].selectSingleNode("FileName").text;
        var StartTime = RecordObjArray[i].selectSingleNode("StartTime").text;
        var EndTime = RecordObjArray[i].selectSingleNode("EndTime").text;
        AddList(FileListObj, StartTime + '~' + EndTime, FileName);
    }

}

function DoQueryRecord1() {
    var cameraId = document.getElementById("CamIDText").value;
    var startQueryTime = document.getElementById("StartTimeText1").value;
    var endQueryTime = document.getElementById("EndTimeText1").value;
    var retStr = g_imosActivePlayer.IMOSAX_QueryBakRecord(cameraId, startQueryTime, endQueryTime);
    alert(retStr);

    var recordListObj = loadXML(retStr);
    var RecordObjArray = recordListObj.documentElement.selectNodes("//RESLIST/item");

    var FileListObj = document.all.FileList;
    FileListObj.innerHTML = '';
    for (i = 0; i < RecordObjArray.length; i++) {
        var FileName = RecordObjArray[i].selectSingleNode("BakFilePath").text;
        var StartTime = RecordObjArray[i].selectSingleNode("BeginTime").text;
        var EndTime = RecordObjArray[i].selectSingleNode("EndTime").text;
        AddList(FileListObj, StartTime + '~' + EndTime, FileName);
    }

}

function DoQueryRecord2() {
    var cameraId = document.getElementById("CamIDText").value;
    var startQueryTime = document.getElementById("StartTimeText2").value;
    var endQueryTime = document.getElementById("EndTimeText2").value;
    var retStr = g_imosActivePlayer.IMOSAX_QuerySlaveRecord(cameraId, startQueryTime, endQueryTime);
    alert(retStr);
    var recordListObj = loadXML(retStr);
    var RecordObjArray = recordListObj.documentElement.selectNodes("//RESLIST/item");

    var FileListObj = document.all.FileList;
    FileListObj.innerHTML = '';
    for (i = 0; i < RecordObjArray.length; i++) {
        var FileName = RecordObjArray[i].selectSingleNode("FileName").text;
        var StartTime = RecordObjArray[i].selectSingleNode("StartTime").text;
        var EndTime = RecordObjArray[i].selectSingleNode("EndTime").text;
        AddList(FileListObj, StartTime + '~' + EndTime, FileName);
    }

}

function DoPlayRecord() {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var cameraId = document.getElementById("CamIDText").value;
    var FileListObj = document.all.FileList;
    var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    for (var i = 0; i < FileListObj.options.length; i++) {
        if (FileListObj.options[i].value == FileListObj.value) {
            time_array = FileListObj.options[i].innerText.split("~");
            start_time = time_array[0];
            stop_time = time_array[1];
            DebugAlert(start_time + ":" + stop_time);
            break;
        }
    }
    if ("" == time_array) {
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartVodReplay(frameNum, cameraId, start_time, stop_time);
    if (0 != flag) {
        alert("回放失败，错误码：" + flag);
    }
}

function DoPlayRecord2() {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var cameraId = document.getElementById("CamIDText").value;
    var FileListObj = document.all.FileList;
    var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    for (var i = 0; i < FileListObj.options.length; i++) {
        if (FileListObj.options[i].value == FileListObj.value) {
            time_array = FileListObj.options[i].innerText.split("~");
            start_time = time_array[0];
            stop_time = time_array[1];
            DebugAlert(start_time + ":" + stop_time);
            break;
        }
    }
    if ("" == time_array) {
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartSlaveVodReplay(frameNum, cameraId, start_time, stop_time);
    if (0 != flag) {
        alert("回放失败，错误码：" + flag);
    }
}

function DoPlayRecord1() {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var cameraId = document.getElementById("CamIDText").value;
    var FileListObj = document.all.FileList;
    var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    for (var i = 0; i < FileListObj.options.length; i++) {
        if (FileListObj.options[i].value == FileListObj.value) {
            time_array = FileListObj.options[i].innerText.split("~");
            start_time = time_array[0];
            stop_time = time_array[1];
            DebugAlert(start_time + ":" + stop_time);
            break;
        }
    }
    if ("" == time_array) {
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StartBakVodReplay(frameNum, cameraId, start_time, stop_time);
    if (0 != flag) {
        alert("回放失败，错误码：" + flag);
    }
}
//停止录像回放

function DoStopPlayRecord() {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopReplay(frameNum);
    if (0 != flag) {
        alert("关闭回放失败，错误码：" + flag);
    }

}

function DoBakDownload() {

    var cameraId = document.getElementById("CamIDText").value;
    var FileListObj = document.all.FileList;
    var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    for (var i = 0; i < FileListObj.options.length; i++) {
        if (FileListObj.options[i].value == FileListObj.value) {
            time_array = FileListObj.options[i].innerText.split("~");
            start_time = time_array[0];
            stop_time = time_array[1];
            DebugAlert(start_time + ":" + stop_time);
            break;
        }
    }
    if ("" == time_array) {
        return;
    }
    gdownloadID = g_imosActivePlayer.IMOSAX_StartDownBakMediaStream(cameraId, start_time, stop_time);
    alert(gdownloadID);
    document.getElementById("downloadID").value = gdownloadID;

}

function DoSlaveDownload() {

    var cameraId = document.getElementById("CamIDText").value;
    var FileListObj = document.all.FileList;
    var file_name = FileListObj.value;
    var start_time;
    var stop_time;
    var time_array;
    // 从列表中获取当前选择的文件起止时间
    for (var i = 0; i < FileListObj.options.length; i++) {
        if (FileListObj.options[i].value == FileListObj.value) {
            time_array = FileListObj.options[i].innerText.split("~");
            start_time = time_array[0];
            stop_time = time_array[1];
            DebugAlert(start_time + ":" + stop_time);
            break;
        }
    }
    if ("" == time_array) {
        return;
    }
    gdownloadID2 = g_imosActivePlayer.IMOSAX_StartDownSlaveMediaStream(cameraId, start_time, stop_time);
    alert(gdownloadID2);
    document.getElementById("downloadID2").value = gdownloadID2;

}

function DoBakDownloadStop() {

    var downloadId = document.getElementById("downloadID").value;
    var flag = g_imosActivePlayer.IMOSAX_StopDownMediaStream(downloadId);
    alert(flag);
}
function DoSlaveDownloadStop() {

    var downloadId = document.getElementById("downloadID2").value;
    var flag = g_imosActivePlayer.IMOSAX_StopDownMediaStream(downloadId);
    alert(flag);
}
//获取播放进度

function GetReplayPos() {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var posTime = g_imosActivePlayer.IMOSAX_GetReplayPos(frameNum);
    alert(posTime);
}



//回放控制

function ReplayControl(cmd) {

}


//回放跳转

function SetPos() {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var pos = document.all.PosText.value;   //时间格式为：yyyy-mm-dd hh:mm:ss
    var flag = g_imosActivePlayer.IMOSAX_DragPlay(frameNum, pos)
    if (0 != flag) {
        alert("拖动回放失败，错误码：" + flag);
    }

}





//抓拍

//抓拍图片将保存在指定路径下, 以当天日期为名称的文件夹内

function DoSnatch() {

    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_SnatchOnce(frameNum);
    if (0 != flag) {
        alert("抓拍失败，错误码：" + flag);
    }
}


//================>功能函数---end

//================>事件处理函数event---start

/**
* 窗格单击事件的处理函数
*/
function dealEventClickFrame(ulFrameNum, pcFrameInfo) {
    //当前窗格
    g_curFrameNum = ulFrameNum;
    pcFrameInfo = pcFrameInfo.replaceAll("\"", "\'");
    var tmpXmlDoc = loadXML(pcFrameInfo);
    if (!tmpXmlDoc) {
        return;
    }
    //将需要的数据从xml中获取，方便后续使用
}
///历史视频播放
function PlayHistoryVideo(rescode, frameNum, start_time, stop_time) {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    //播放主用录像
    var flag = g_imosActivePlayer.IMOSAX_StartVodReplay(frameNum, rescode, start_time, stop_time);
    if (0 != flag) {
        alert("播放录像失败，错误码：" + flag);
    }

}
//拖动完之后播放一组视频2013.12.7-LD
function PlayArrHisVideo(rescode, frameNum, start_time, stop_time) {
    //播放主用录像
    var flag = g_imosActivePlayer.IMOSAX_StartVodReplay(frameNum, rescode, start_time, stop_time);
}
//停止播放视频--刘冬
function StopPlayHistoryVideo() {
    var flag = g_imosActivePlayer.IMOSAX_StopReplay(frameNum);
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_StopReplay(frameNum);
    if (0 != flag) {
        //        alert("停止播放失败，错误码：" + flag);
    }
    //从第二个窗格开始停止播放视频
}
//暂停播放视频-刘冬
function VideoPlayPause() {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_PauseReplay(frameNum);
    if (0 != flag) {
        //alert("暂停播放失败，错误码：" + flag);
        return flag;
    }
}
function VideoResumeReplay() {
    var frameNum = g_curFrameNum;
    frameNum = parseInt(frameNum, 10);
    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
        alert("请先选择一个窗格");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_ResumeReplay(frameNum);
    if (0 != flag) {
        //alert("继续播放失败，错误码：" + flag);
        return flag;
    }

}

//从第二个窗口开始停止所有窗口的播放，包括实时和历史的播放
function StopAllFramePlay() {
    for (i = 2; i < 26; i++) {
        g_imosActivePlayer.IMOSAX_StopFramelive(i);
        g_imosActivePlayer.IMOSAX_StopReplay(i);
    }
}

function SetFrameNumber(number) {
    g_imosActivePlayer.IMOSAX_ChangeLayout(number);
}
//开始下载录像
function StartDownloadVideoRecord(CameraID) {
    var RecordStartTime = $("#RecordStartTime").val();
    var RecordEndTime = $("#RecordEndTime").val();

    var flag = g_imosActivePlayer.IMOSAX_StartDownMediaStream(CameraID, RecordStartTime, RecordEndTime);

    if (0 != flag) {
        var DownloadInterval = setInterval("GetDownloadPos('" + CameraID + "')", 4000);
    }
    else {
        //        $("#DownloadRecord").text("停止下载").attr("onclick", "StopDownloadVideoRecord()").attr("name",rescode);
        //        alert("开始下载");
        alert("下载录像失败，错误码：" + g_imosActivePlayer.IMOSAX_GetLastError());
    }
}
//停止下载
function StopDownloadVideoRecord(CameraID) {
    var flag = g_imosActivePlayer.IMOSAX_StopDownMediaStream(CameraID);
    $("#RecordStartTime").val("");
    $("#RecordEndTime").val("");
    $(".DownLoadDiv").hide();

}
//获取录像下载进度
function GetDownloadPos(CameraID) {
    var DownloadSeconds = g_imosActivePlayer.IMOSAX_GetDownloadPos(CameraID);
    alert(DownloadSeconds);
}
//抓拍
//function SnatchImg() {
//    var frameNum = g_curFrameNum;
//    frameNum = parseInt(frameNum, 10);
//    if (isNaN(frameNum) || frameNum < 1 || frameNum > 25) {
//        alert("请先选择一个窗格");
//        return;
//    }
////    browseFolder(path) = browseFolder(path).replace("/", "\\");
////    alert(browseFolder(path));
//    var configXML = "<?xml version='1.0'?>";
//    configXML += "<data>";
//    configXML += "<SnatchPath>" + browseFolder(path) +"</SnatchPath>";
//    configXML += "</data>";


//    var flag = g_imosActivePlayer.IMOSAX_SnatchOnce(1);
//    if (0 != flag) {
//        alert("抓拍失败，错误码：" + flag);
//    }
//    else {
//        alert("抓拍成功");
//    }
//}
//设置保存路径
function browseFolder(path) {
    try {
        var Message = "/u8bf7/u9009/u62e9/u6587/u4ef6/u5939"; //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
        //var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
        if (Folder != null) {
            Folder = Folder.items(); // 返回 FolderItems 对象
            Folder = Folder.item(); // 返回 Folderitem 对象
            Folder = Folder.Path; // 返回路径
            if (Folder.charAt(Folder.length - 1) != "") {
                Folder = Folder + "";
            }
            document.getElementById(path).value = Folder;
            return Folder;
        }
    }
    catch (e) {
        alert(e.message);
    }
}

function ConfigPara(path) {
    browseFolder(path);
}
function QueDingXiuGai() {
    var SnatchPath = $("#SnapUrlText").val() + "\\";
    var StoragePath = $("#RecordUrlText").val() + "\\";
    //向xml中写入数据
    $.ajax({
        url: "/Setting/WriteXml",
        type: "post",
        dataType: "text",
        data: "SnatchPath=" + SnatchPath + "&StoragePath=" + StoragePath,
        success: function (data) {
            SetConfig(data);
        }
    });
}
//用xml进行配置
function SetConfig(data) {
    var flag = g_imosActivePlayer.IMOSAX_SetAllCfgParam(data.toString());

}


function GetUserIdInfo(strQuery) {
    //获取登录用户信息
    //返回值:XML登录用户信息
    //参数:
    //OrgCode：用户所在域编码

    if (!g_imosActivePlayer) {
        alert("未安装控件，请先安装后再使用本页面");
        return;
    }
    var flag = g_imosActivePlayer.IMOSAX_GetUserLoginInfo();
    if (flag != "") {
        flag = loadXML(flag);
        flag = flag.getElementsByTagName(strQuery)[0].text;
        return flag;
    }
    else {
        return flag;
    }

}
//================>事件处理函数event---end


/*txn 2014-11-29 修改轮切*/

//获取轮切资源列表
//返回值"Object数组(roundname：轮切组名称,roundcode：轮切资源编码)"
GetLunqie = function () {
    var arry = [];
    var Xml = g_imosActivePlayer.IMOSAX_QueryOrgResList('iccsid', 2001, 0, 0, 0);
    var Name = loadXML(Xml);
    var x = Name.documentElement.childNodes;
    if (x.length == 0) {
        alert("没有轮切资源");
        return;
    }
    for (var i = 0; i < x.length; i++) {
        var obj = new Object();
        obj.roundname = Name.documentElement.selectNodes("//item/ResName")[i].text;
        obj.roundcode = Name.documentElement.selectNodes("//item/ResCode")[i].text;
        arry.push(obj);
    }
    return arry;
}

//添加轮切
//参数:轮切轮切组名称,轮切数据(DeviceCode,Interval)
//返回值:轮切编码
AddLunQie = function (time, roundname, rounddata) {
    var lunqieSetting = "<?xml version='1.0'?>"
    + "<data>"
    + "<SwitchBaseInfo>"
    + "<SwitchName>" + roundname + "</SwitchName>"
    + "<SwitchDesc />"
    + "</SwitchBaseInfo>"
    + "<SwitchResNum>" + rounddata.length + "</SwitchResNum>"
    + "<SwitchUnitList count='" + rounddata.length + "'>";
    for (var i = 0; i < rounddata.length; i++) {
        var name = rounddata[i].split("|")[0];
        var code = rounddata[i].split("|")[1];
        lunqieSetting += "<item>"
        + "<CameraCode>" + code + "</CameraCode>"
        + "<CameraName>" + name + "</CameraName>"
        + "<Sequence>" + (i + 1) + "</Sequence>"
        + "<Interval>" + time + "</Interval>"
        + "</item>";
    }
    lunqieSetting += "</SwitchUnitList>"
    + "</data>";
    var flag = g_imosActivePlayer.IMOSAX_AddSwitchResource('iccsid', lunqieSetting);
    return flag;
}

//播放轮切
//参数：播放窗格INT类型（1-25）,轮切Code
//返回值:正常=0
DoStartLunQie = function (windownum, lunqieCode) {
    var flag = g_imosActivePlayer.IMOSAX_StartFrameSwitch(windownum, lunqieCode);
    return flag;
}