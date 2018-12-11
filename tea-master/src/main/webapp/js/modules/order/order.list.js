/**
 * Created by kaixiang.xu on 18/10/08.
 */

var $table = $('#orders');
//bootstrapTable使用中文
$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

//datePicker初始化配置
$("#datetimeStart").datepicker({
    format: 'yyyy-mm-dd',
    minView:'month',
    language: 'zh-CN',
    autoclose:true,
    startDate:new Date(2018,9,1)//输入9，是10月
}).on("click",function(){
    $("#datetimeStart").datepicker("setEndDate",$("#datetimeEnd").val())
});
$("#datetimeEnd").datepicker({
    format: 'yyyy-mm-dd',
    minView:'month',
    language: 'zh-CN',
    autoclose:true,
    startDate:new Date(2018,9,1)
}).on("click",function(){
    $("#datetimeEnd").datepicker("setStartDate",$("#datetimeStart").val())
});

//防止表头与表格不对齐
$(window).resize(function () {
    $table.bootstrapTable('resetView');
});

$(function () {
    //使用严格模式
    "use strict";
    tableInit();
    $('#orders').bootstrapTable('hideLoading');
})

//初始化Table
function tableInit() {
    //先销毁表格
    $table.bootstrapTable('destroy');

    $table.bootstrapTable({
        //请求地址
        url: 'order/queryAllUserOrder',
        //请求方式
        method: 'post',
        //请求内容类型
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        //数据类型
        dataType: "json",
        //table高度，
        //如果没有设置height属性，
        //表格自动根据记录条数觉得表格高度
        //是否显示行间隔色
        striped: true,
        //是否启用排序
        sortable: false,
        //排序方式
        sortOrder: "asc",
        //是否使用缓存
        cache: false,
        //每行的唯一标识
        uniqueId: "id",
        //指定工具栏
        toolbar: "#toolbar",
        //显示隐藏列
        showColumns: true,
        //显示刷新按钮
        showRefresh: false,
        //切换显示样式
        showToggle: false,
        //显示Table脚部
        showFooter: false,
        //是否显示详细视图
        cardView: false,
        //是否显示父子表
        detailView: false,
        //detail格式化
        detailFormatter: genderDetail,
        //是否显示分页
        pagination: true,
        //是否显示分页按钮
        showPaginationSwitch: false,
        //是否启用点击选中行
        clickToSelect: false,
        //开启单选
        singleSelect: true,
        //最少要显示的列数
        minimumCountColumns: 2,
        //cell没有值时显示
        undefinedText: '-',
        //分页方式：client客户端分页，server服务端分页
        sidePagination: "server",
        //每页的记录行数
        pageSize: 10,
        //初始化加载第1页，默认第1页
        pageNumber: 1,
        //可供选择的每页的行数
        pageList: "[10,20,50，100]",
        paginationFirstText: "首页",
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        paginationLastText: "末页",
        buttonsClass: 'default',
        iconsPrefix: 'glyphicon',
        queryParams: queryParams,
        icons: {
            paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',
            paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            detailOpen: 'glyphicon-plus icon-plus',
            detailClose: 'glyphicon-minus icon-minus'
        }, columns: [{
            title: '序号',
            field: 'index',
            align: 'center',
            valign: 'middle',
            formatter: genderIndex
        },
            {
            title: '订单号',
            field: 'orderNumber',
            align: 'center',
            valign: 'middle',
        }, {
            title: '订单id',
            field: 'orderId',
            align: 'center',
            valign: 'middle',
            visible: false
        }, {
            title: '客户名称',
            field: 'userName',
            align: 'center',
            valign: 'middle'
        },{
            title:'购买商品',
                field:'goodsOrderVos',
                align:'center',
                valign:'middle',
                formatter: getGoodsName
            },{
            title:'商品数量',
                field:'goodsOrderVos',
                align:'center',
                valign:'middle',
                formatter:getGoodsNum
            },{
            title:'订单总价',
                field:'price',
                align:'center',
                valign:'middle'

            },{
            title:'下单时间',
                field:'createTime',
                align:'center',
                valign:'middle',
                formatter:dateFormat
            },{
            title:'订单状态',
                field:'orderStatus',
                align:'center',
                valign:'middle',
                formatter:getOrderStatus
            },{
                title: '操作',
                field: 'operate',
                align: 'center',
                valign:'middle',
                events: operateEvents,
                formatter: operator
            }],
        responseHandler: function (res) {
             // console.log(res);
            if (res.state == 1) {
                var obj = {
                    "total": res.total,
                    "rows": res.records,
                };
            } else {
                var obj = {
                    "total": 0,
                    "rows": [],
                }
            }
            return obj;
        }, onLoadSuccess: function () {
            //加载成功时执行
            console.log("加载成功!");
        }, onLoadError: function () {
            //加载失败时执行
            layer.msg("加载失败!", {icon: 2, time: 2000});
        }, formatLoadingMessage: function () {
            //正在加载
            return "请稍等，正在加载中...";
        }, formatNoMatches: function () {
            //没有匹配的结果
            return '无符合条件的记录';
        }
    })
}

//生成详细视图
function genderDetail(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}

//得到商品名
function getGoodsName(value,row) {
    var goodsOrderVos=eval(value);
    var  html=[];
    for (var i=0;i<goodsOrderVos.length;i++){
        html.push(goodsOrderVos[i].goodsName+'<br/>');
    }
    // console.log(goodsName)
    return html.join('');
}
//得到购买商品数量
function getGoodsNum(value,row) {
    var  goodsOrderVos=eval(value);
    var html=[];
    for (var i=0;i<goodsOrderVos.length;i++){
        html.push(goodsOrderVos[i].quantity+'<br/>');
    }
    // console.log(goodsName)
    return html.join('');
}


//生成序号
function genderIndex(value, row, index) {
    return index + 1;
}
//得到订单状态
function getOrderStatus(value,row,index){
    // console.log(value);
    if (value==undefined||value==null){
        return "-";
    } else if (value==0) {
        // console.log(value)
        return "未支付";
    }else if (value==1){
        return "待发货"
    } else if (value==2){
        return "已发货";
    } else if (value==3){
        return "订单完成";
    }else if (value==4){
        return "申请取消订单";
    }else if (value==5){
        return "待退款";
    }else if (value==6){
        return "已取消";
    }
}
//替换时间数据为文字
function dateFormat(value, row, index) {
    // console.log("=======================>" + value);
    if (value == undefined || value == "" || null == value) {
        return value;
    }
    // console.log(Date(value).toString());
    // console.log(Date(value).toLocaleString());
    return new Date(value).toLocaleString();
    //return new Date(value).format("yyyy-MM-dd hh:mm:ss");
}

//操作
function operator(){
    return [
        '<a id="management" href="javascript:void(0)" title="管理订单">',
        '<i class="glyphicon glyphicon-chevron-right"></i>',
        '</a>  '
    ].join('');
}

//自定义列内容事件
window.operateEvents = {
    'click #management': function (e, value, row, index) {
        manageMData(row);
    },
    'click #balance-record':function (e,value,row,index) {
      orderRecord(row);
    }
};

//tr中管理订单按钮事件
function manageMData(row){
    var orderNumber=row.orderNumber;
    $('#balanceRecordLabel').text("订单管理");
    $('#balanceRecordDialog').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
    $("#records").bootstrapTable('destroy');
    $("#records").bootstrapTable({
        //请求地址
        url: 'order/orderDetail',
        //请求方式
        method: 'post',
        //请求内容类型
        contentType: "application/x-www-form-urlencoded",
        //数据类型
        dataType: "json",
        //table高度，
        //如果没有设置height属性，
        //表格自动根据记录条数觉得表格高度
        //是否显示行间隔色
        striped: true,
        //是否启用排序
        sortable: false,
        //排序方式
        sortOrder: "desc",
        //是否使用缓存
        cache: false,
        //每行的唯一标识
        uniqueId: "orderId",
        //指定工具栏
        // toolbar: "#toolbar",
        //显示隐藏列
        showColumns: false,
        //显示刷新按钮
        showRefresh: false,
        //切换显示样式
        showToggle: false,
        //显示Table脚部
        showFooter: false,
        //是否显示详细视图
        cardView: false,
        //是否显示父子表
        detailView: false,
        //detail格式化
        detailFormatter: genderDetail,
        //是否显示分页
        pagination: true,
        //是否显示分页按钮
        showPaginationSwitch: false,
        //是否启用点击选中行
        clickToSelect: false,
        //最少要显示的列数
        minimumCountColumns: 1,
            //cell没有值时显示
            undefinedText: '-',
            //分页方式：client客户端分页，server服务端分页
            sidePagination: "server",
            //每页的记录行数
            pageSize: 10,
            //初始化加载第1页，默认第1页
            pageNumber: 1,
            //可供选择的每页的行数
            pageList: "[10,20,50,100]",
            paginationFirstText: "首页",
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            paginationLastText: "末页",
            buttonsClass: 'default',
            iconsPrefix: 'glyphicon',
            queryParams:  function(params){
            // console.log(orderNumber);
            params.orderNumber = orderNumber;
            return params;
        },
        icons: {
            paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',
            paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            detailOpen: 'glyphicon-plus icon-plus',
            detailClose: 'glyphicon-minus icon-minus'
        }, columns: [{
            title: '订单号',
            field: 'orderNumber',
            align: 'center',
            valign: 'middle',
            }, {
            title: '订单id',
            field: 'orderId',
            align: 'center',
            valign: 'middle',
            visible: false
        }, {
            title: '客户名称',
            field: 'userName',
            align: 'center',
            valign: 'middle'
        },{
            title:'购买商品',
            field:'goodsOrderVos',
            align:'center',
            valign:'middle',
            formatter: getGoodsName
        },{
            title:'商品数量',
            field:'goodsOrderVos',
            align:'center',
            valign:'middle',
            formatter:getGoodsNum
        },{
            title:'订单总价',
            field:'price',
            align:'center',
            valign:'middle'

        },{
            title:'下单时间',
            field:'createTime',
            align:'center',
            valign:'middle',
            formatter:dateFormat
        },{
            title:'订单状态',
            field:'orderStatus',
            align:'center',
            valign:'middle',
            formatter:getOrderStatus
        },{
            title: '收货人',
            field: 'receiptName',
            align: 'center',
            valign:'middle'
        },{
            title: '收货人联系方式',
            field: 'receiptPhone',
            align: 'center',
            valign:'middle'
        },{
            title: '订单备注',
            field: 'orderRemark',
            align: 'center',
            valign:'middle'
        },{
            title: '查看订单操作记录详细',
            field: 'operate',
            align: 'center',
            valign:'middle',
            events: operateEvents,
            formatter: recorde
        }],

        responseHandler: function (res) {
            // console.log(res);
            if (res.state == 1) {
                // console.log(res);
                var obj = {
                    "total": res.total,
                    "rows": res.records,
                };
            } else {
                var obj = {
                    "total": 0,
                    "rows": [],
                }
            }
            // console.log("obj:"+obj);
            return obj;
        }, onLoadSuccess: function () {
            //加载成功时执行
            console.log("加载成功!");
        }, onLoadError: function () {
            //加载失败时执行
            layer.msg("加载失败!", {icon: 2, time: 2000});
        }, formatLoadingMessage: function () {
            //正在加载
            return "请稍等，正在加载中...";
        }, formatNoMatches: function () {
            //没有匹配的结果
            return '无符合条件的记录';
        }
    })
}

//tr中查看订单操作记录详细
function orderRecord(row) {
    var orderNumber=row.orderNumber;
    $('#orderRecordLabel').text("查看订单记录详细");
    $('#orderRecordDialog').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
    $("#orderRecords").bootstrapTable('destroy');
    $("#orderRecords").bootstrapTable({
        //请求地址
        url: 'orderRecord/queryByOrderNumber',
        //请求方式
        method: 'post',
        //请求内容类型
        contentType: "application/x-www-form-urlencoded",
        //数据类型
        dataType: "json",
        //table高度，
        //如果没有设置height属性，
        //表格自动根据记录条数觉得表格高度
        height: '680',
        //是否显示行间隔色
        striped: true,
        //是否启用排序
        sortable: false,
        //排序方式
        sortOrder: "desc",
        //是否使用缓存
        cache: false,
        //每行的唯一标识
        uniqueId: "orderId",
        //指定工具栏
        // toolbar: "#toolbar",
        //显示隐藏列
        showColumns: false,
        //显示刷新按钮
        showRefresh: false,
        //切换显示样式
        showToggle: false,
        //显示Table脚部
        showFooter: false,
        //是否显示详细视图
        cardView: false,
        //是否显示父子表
        detailView: false,
        //detail格式化
        detailFormatter: genderDetail,
        //是否显示分页
        pagination: true,
        //是否显示分页按钮
        showPaginationSwitch: false,
        //是否启用点击选中行
        clickToSelect: false,
        //最少要显示的列数
        minimumCountColumns: 1,
        //cell没有值时显示
        undefinedText: '-',
        //分页方式：client客户端分页，server服务端分页
        sidePagination: "server",
        //每页的记录行数
        pageSize: 10,
        //初始化加载第1页，默认第1页
        pageNumber: 1,
        //可供选择的每页的行数
        pageList: "[10,20,50,100]",
        paginationFirstText: "首页",
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        paginationLastText: "末页",
        buttonsClass: 'default',
        iconsPrefix: 'glyphicon',
        queryParams:  function(params){
            // console.log(orderNumber);
            params.orderNumber = orderNumber;
            return params;
        },
        icons: {
            paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',
            paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            detailOpen: 'glyphicon-plus icon-plus',
            detailClose: 'glyphicon-minus icon-minus'
        }, columns: [{
            title: '操作时间',
            field: 'operatorTime',
            align: 'center',
            valign: 'middle',
            formatter:dateFormat
        }, {
            title: '操作描述',
            field: 'operator',
            align: 'center',
            valign: 'middle'
        },{
            title: '操作人',
            field: 'modifyName',
            align: 'center',
            valign: 'middle'
        }],
        responseHandler: function (res) {
            console.log(res);
            if (res.state == 1) {
                // console.log(res);
                var obj = {
                    "total": res.total,
                    "rows": res.records,
                };
            } else {
                var obj = {
                    "total": 0,
                    "rows": [],
                }
            }
            // console.log("obj:"+obj);
            return obj;
        }, onLoadSuccess: function () {
            //加载成功时执行
            console.log("加载成功!");
        }, onLoadError: function () {
            //加载失败时执行
            layer.msg("加载失败!", {icon: 2, time: 2000});
        }, formatLoadingMessage: function () {
            //正在加载
            return "请稍等，正在加载中...";
        }, formatNoMatches: function () {
            //没有匹配的结果
            return '无符合条件的记录';
        }
    })
}


function recorde() {
    return [
        '<a id="balance-record" href="javascript:void(0)" title="订单记录" >',
        '<i class="glyphicon glyphicon-list-alt" style="margin-right: 5px;"></i>',
        '</a>  '
    ].join('');
}

//显示订单操作记录
function showOperator(value,row) {
    var orderRecordVos=eval(value);
     // console.log(orderRecordVos[1].operator);
     console.log(orderRecordVos);
    var  html=[];
    for (var i=0;i<orderRecordVos.length;i++){
        html.push(dateFormat(orderRecordVos[i].operatorTime)+'<br/>'+"操作状态:"+orderRecordVos[i].operator +'<br/>');
    }
    return html.join('');
}

//查询条件与分页数据
function queryParams(params) {
    // var unpaid=$("#btn_unpaid").val();
    // var paid=$("#btn_paid").val();
    // var receipt=$("#btn_receipt").val();
    // var success=$("#btn_success").val();
    params.orderStatus = $('#orderStuas option:selected').val();
    console.log(params.orderStatus);
    //排序方式
    params.orderId = "id";
    //第几页
    params.pageCurrent = this.pageNumber;
    //console.log(params.pageCurrent);
    //第页条数
    params.pageSize = this.pageSize;
    //订单编号
    params.orderNumber = $("#orderNumber").val();
    //客户名称
    params.userName = $("#userName").val();
    //console.log($("#userName").val());
    // //商品名称
    // params.goodsName = $("#goodsName").val();
    return params;
}

//----------------------------crud---------------------------------------
//刷新页面
function refresh() {
    $table.bootstrapTable('refresh', {pageNumber: 1});
}

//查询按钮点击事件
$("#btn_search").on("click", function () {
    refresh();
});

//清空条件按钮点击事件
$("#btn_clean_search").on("click", function () {

    $('#orderStuas').val("");

    // console.log("清空"+$('#userName').val());
    refresh();
});

//点击发货订单
$("#btn_ship").off().on("click", function () {
    //得到订单号和订单状态
    var orderStatus = $("#records").find("td").eq(6).text();
    var orderNumber = $("#records").find("td").eq(0).text();
    return ship(orderNumber,orderStatus);
});

//点击同意取消订单
$('#btn_accept').off().on("click",function(){
    var orderStatus = $("#records").find("td").eq(6).text();
    var orderNumber = $("#records").find("td").eq(0).text();
    return acceptOrder(orderNumber,orderStatus);
});
//拒绝取消订单
$('#btn_refuse').off().on("click",function(){
    var orderStatus = $("#records").find("td").eq(6).text();
    var orderNumber = $("#records").find("td").eq(0).text();
    return refuseOrder(orderNumber,orderStatus);
});
//点击退款
$('#btn_refund').off().on("click",function(){
    var orderStatus = $("#records").find("td").eq(6).text();
    var orderNumber = $("#records").find("td").eq(0).text();
    return refundOrder(orderNumber,orderStatus);
});

//将文字订单状态变为数字并控制按钮
function getOrderStatusNum(orderStatus){
    if (orderStatus=="未支付"){
        return 0;
    }else if(orderStatus=="待发货"){
        return 1;
    }else if(orderStatus=="已发货"){
        return 2;
    }else if(orderStatus=="订单完成"){
        return 3;
    }else if(orderStatus=="申请取消订单"){
        return 4;
    }else if(orderStatus=="待退款"){
        return 5;
    }else if(orderStatus=="已取消"){
        return 6;
    }else {
        alert("获取订单错误");
        return null;
    }
}

//点击发货按钮
function ship(orderNumber,orderStatus){
    // console.log("发货"+$("#btn_ship").val());
    var ship=$("#btn_ship").val();
    var Status=getOrderStatusNum(orderStatus);
    console.log(orderNumber);
    console.log(Status);
    if (Status==1){
        $.ajax({
            url: '../updateOrder/orderShip',
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            //阻止深度序列化，向后台传送数组
            traditional: true,
            data:{
                orderNumber:orderNumber
            },
            success:function (msg) {
                if (msg.state == 1) {
                    layer.msg(msg.message, {icon: 1, time: 1500});
                } else {
                    layer.msg(msg.message, {icon: 2, time: 1500});
                }
                refresh();
            }
        });
    } else {
        alert("用户已发货或用户未支付!无法进行操作");
        console.log("禁用！")
    }
}
//点击同意取消订单
function acceptOrder(orderNumber,orderStatus){
    var accept=$("#btn_accept").val();
    var Status=getOrderStatusNum(orderStatus);
    console.log(orderNumber);
    console.log(Status);
    if (Status==4){
        $.ajax({
            url: '../updateOrder/agreeOrderCancel',
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            //阻止深度序列化，向后台传送数组
            traditional: true,
            data:{
                orderNumber:orderNumber
            },
            success:function (msg) {
                console.log(msg);
                if (msg.state == 1) {
                    layer.msg(msg.message, {icon: 1, time: 1500});
                } else {
                    layer.msg(msg.message, {icon: 2, time: 1500});
                }
                refresh();
            }
        });
    } else {
        alert("用户已发货或用户未支付!无法进行操作");
        console.log("禁用！")
    }
}
//拒绝申请取消订单
function refuseOrder(orderNumber,orderStatus){
    var Status=getOrderStatusNum(orderStatus);
    console.log(orderNumber);
    console.log(Status);
    if (Status==4){
        $.ajax({
            url: '../updateOrder/refuseOrder',
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            //阻止深度序列化，向后台传送数组
            traditional: true,
            data:{
                orderNumber:orderNumber
            },
            success:function (msg) {
                console.log(msg);
                if (msg.state == 1) {
                    layer.msg(msg.message, {icon: 1, time: 1500});
                } else {
                    layer.msg(msg.message, {icon: 2, time: 1500});
                }
                refresh();
            }
        });
    } else {
        alert("用户已发货或用户未支付!无法进行操作");
        console.log("禁用！")
    }
}
//订单退款
function refundOrder(orderNumber,orderStatus){
    var Status=getOrderStatusNum(orderStatus);
    console.log(orderNumber);
    console.log(Status);
    if (Status==5){
        $.ajax({
            url: '../updateOrder/refuseOrder',
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            //阻止深度序列化，向后台传送数组
            traditional: true,
            data:{
                orderNumber:orderNumber
            },
            success:function (msg) {
                console.log(msg);
                if (msg.state == 1) {
                    layer.msg(msg.message, {icon: 1, time: 1500});
                } else {
                    layer.msg(msg.message, {icon: 2, time: 1500});
                }
                refresh();
            }
        });
    } else {
        alert("用户已发货或用户未支付!无法进行操作");
        console.log("禁用！")
    }
}

//刷新按钮点击事件
$("#btn_refresh").on("click", function () {
    refresh();
});

//显隐分页按钮点击事件
$("#btn_togglepage").on("click", function () {
    $table.bootstrapTable('togglePagination');
});

//跳转按钮点击事件
$("#btn_selectpage").on("click", function () {
    var pageNum = 3;
    $table.bootstrapTable('selectPage', pageNum);
});

//清除弹窗原数据
$("#addAndUpdate").on("hidden.bs.modal", function () {
    $('#txt_customer_id').val("");
    $('#txt_point').val("");
    $('#txt_description').val("");
});


