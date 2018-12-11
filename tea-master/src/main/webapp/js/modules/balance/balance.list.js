/**
 * Created by Grant on 18/10/09.
 */

//datePicker初始化配置
$("#datetimeStart").datepicker({
    format: 'yyyy-mm-dd',
    minView:'month',
    language: 'zh-CN',
    autoclose:true,
    endDate:new Date()
}).on('changeDate', function(ev){
    $("#datetimeEnd").datepicker("setStartDate",$("#datetimeStart").val());
});

$("#datetimeEnd").datepicker({
    format: 'yyyy-mm-dd',
    minView:'month',
    language: 'zh-CN',
    autoclose:true,
    endDate:new Date()
}).on('changeDate', function(ev){
    $("#datetimeStart").datepicker("setEndDate",$("#datetimeEnd").val());
});

var $table = $('#balanceRecords');
//bootstrapTable使用中文
$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

//防止表头与表格不对齐
$(window).resize(function () {
    $table.bootstrapTable('resetView');
});

$(function () {
    //使用严格模式
    "use strict";

    tableInit();
    $('#balanceRecords').bootstrapTable('hideLoading');
})

//初始化Table
function tableInit() {
    //先销毁表格
    $table.bootstrapTable('destroy');

    $table.bootstrapTable({
        //请求地址
        url: 'balance/getBalanceRecordList.do',
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
        uniqueId: "id",
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
        pageList: "[10,15,20,50]",
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
        },{
            title: '记录编号',
            field: 'id',
            align: 'center',
            valign: 'middle',
            visible: false
        },{
            title: '用户编号',
            field: 'customerId',
            align: 'center',
            valign: 'middle',
            visible: false
        },{
            title: '用户名',
            field: 'username',
            align: 'center',
            valign: 'middle',
        }, {
            title: '类型',
            field: 'type',
            align: 'center',
            formatter: genderType,
            valign: 'middle'
        }, {
            title: '金额',
            field: 'amount',
            align: 'center',
            valign: 'middle'
        }, {
            title: '订单编号',
            field: 'orderNumber',
            align: 'center',
            valign: 'middle'
        }, {
            title: '旧余额',
            field: 'balanceOld',
            align: 'center',
            valign: 'middle'
        },{
            title: '新余额',
            field: 'balanceNew',
            align: 'center',
            valign: 'middle'
        },{
            title: '创建人',
            field: 'createEmpName',
            align: 'center',
            valign: 'middle'
        },{
            title: '创建时间',
            field: 'createTime',
            formatter: dateFormat,
            align: 'center',
            valign: 'middle'
        },{
            title: '修改人',
            field: 'modifyEmpName',
            align: 'center',
            valign: 'middle'
        },{
            title: '修改时间',
            field: 'modifyTime',
            formatter: dateFormat,
            align: 'center',
            valign: 'middle'
        }],
        responseHandler: function (res) {
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

//查询条件与分页数据
function queryParams(params) {
    //排序方式
    params.order = "id desc";
    //第几页
    params.pageCurrent = this.pageNumber;
    //第页条数
    params.pageSize = this.pageSize;
    //customerId
    params.customerId = $("#customerId").val();
    //userName
    params.username = $("#username").val();
    //recordType
    params.type = $('#recordType option:selected').val();
    //startDate
    params.startDate = $("#datetimeStart").val();
    //endDate
    params.endDate = $("#datetimeEnd").val();
    return params;
}

//生成详细视图
function genderDetail(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}

//生成序号
function genderIndex(value, row, index) {
    return index + 1;
}

//日期格式化
function dateFormat(value, row, index) {
    if (value == undefined || value == "" || null == value) {
        return value;
    }
    return new Date(value).toLocaleString();
    //return new Date(value).format("yyyy-MM-dd hh:mm:ss");
}

//替换type数据为文字 记录类型:1-充值;2-消费;3-退款;
function genderType(value, row, index) {
    if (value == null || value == undefined) {
        return "-";
    } else if (value == 1) {
        return "充值";
    } else if (value == 2) {
        return "消费";
    } else if (value == 3) {
        return "退款";
    }
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
