
//日期转换
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
}

//array转json字符串
function arrayToJsonObject(arr) {
    var json = {};
    for (var i = 0; i < arr.length; i++) {
        var single = arr[i];
        json[single.name] = single.value;
    }
    var objectToJson = JSON.stringify(json);//JSON.stringify() 方法用于将 JavaScript 值转换为 JSON 字符串。
    return objectToJson;
}

//array转object
function arrayToObject(arr) {
    var json = {};
    for (var i = 0; i < arr.length; i++) {
        var single = arr[i];
        json[single.name] = single.value;
    }
    return json;
}


//获取地址栏参数函数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}


//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}
/**
 * 去除null 字符
 * @param strOrObj
 * @returns {*}
 */
function removeShowNull(strOrObj) {
    if (strOrObj == null ){
        return "";
    }
    return strOrObj;

}
/**
 * 删除 strng[] 转string
 * eg:[1],[2] [3]  ==> "1,2,3"
 */
function arrayToStrings( arrs ) {

    var ids_str = "";
    for (var i = 0 ; i < arrs.length ; i++){
        ids_str = ids_str +  arrs[i].id + ",";
    }
    ids_str = ids_str.substring(0, ids_str.lastIndexOf(','));
    console.log("ids：" + ids_str);
    return ids_str;
}






//---common
//-------------bootstrap tab 封装


// $.ajaxSetup({
//     dataType: "json",
//     cache: false,
//     xhrFields: {withCredentials: true},//设置后，请求会携带cookie
//     crossDomain: true,
//     complete: function (xhr) {
//         if (xhr.responseJSON) {
//             if (xhr.responseJSON.code != 200) {
//                 layer.msg(xhr.responseJSON.msg);
//             }
//         } else {
//             console.log(xhr.status != 200)
//             layer.msg(xhr.responseText);
//         }
//     }
// });

//common--end

