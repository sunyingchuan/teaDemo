/**
 * Created by kaixiang.xu on 18/10/08.
 */

var $table = $('#points');
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
    $('#points').bootstrapTable('hideLoading');
})

//初始化Table
function tableInit() {
    //先销毁表格
    $table.bootstrapTable('destroy');

    $table.bootstrapTable({
        //请求地址
        url: 'point/list.do',
        //请求方式
        method: 'post',
        //请求内容类型
        contentType: "application/x-www-form-urlencoded",
        //数据类型
        dataType: "json",
        //table高度，
        //如果没有设置height属性，
        //表格自动根据记录条数觉得表格高度
        height: '750',
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
        //最少要显示的列数
        minimumCountColumns: 2,
        //cell没有值时显示
        undefinedText: '-',
        //分页方式：client客户端分页，server服务端分页
        sidePagination: "server",
        //每页的记录行数
        pageSize: 15,
        //初始化加载第1页，默认第1页
        pageNumber: 1,
        //可供选择的每页的行数
        pageList: "[10,15,50,100]",
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
        }, columns: [/*{
            field: 'state',
            checkbox: true,
            align: 'center',
            valign: 'middle'
        }, */{
            title: '序号',
            field: 'index',
            align: 'center',
            valign: 'middle',
            formatter: genderIndex
        }, {
            title: '积分记录id',
            field: 'id',
            align: 'center',
            valign: 'middle',
            visible: false
        }, {
            title: '客户名称',
            field: 'username',
            align: 'center',
            valign: 'middle'
        }, {
            title: '订单编号',
            field: 'orderNumber',
            align: 'center',
            valign: 'middle'
        }, {
            title: '订单金额',
            field: 'money',
            align: 'center',
            valign: 'middle'
        }, {
            title: '积分',
            field: 'point',
            align: 'center',
            valign: 'middle'
        },  {
            title: '创建时间',
            field: 'createTime',
            formatter: dateFormat,
            align: 'center',
            valign: 'middle'
        }, {
            title: '创建人id',
            field: 'createEmp',
            align: 'center',
            valign: 'middle',
            visible: false
        }, {
            title: '创建人',
            field: 'createEmpName',
            align: 'center',
            valign: 'middle'
        },{
            title: '描述',
            field: 'description',
            align: 'center',
            valign: 'middle',
        }],
        responseHandler: function (res) {
            if (res.state === 1) {
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

//生成序号
function genderIndex(value, row, index) {
    return index + 1;
}


//替换时间数据为文字
function dateFormat(value, row, index) {
    console.log("=======================>" + value);
    if (value == undefined || value == "" || null == value) {
        return value;
    }
    return new Date(value).toLocaleString();
    //return new Date(value).format("yyyy-MM-dd hh:mm:ss");
}

//自定义列内容事件
window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        editData(row);
    },
    'click #remove': function (e, value, row, index) {
        delData(row.id);
    }
};

//查询条件与分页数据
function queryParams(params) {
    //排序方式
    params.order = "id desc";
    //第几页
    params.pageCurrent = this.pageNumber;
    //第页条数
    params.pageSize = this.pageSize;
    //订单编号
    params.orderNumber = $("#orderNumber").val();
    //积分
    params.point =  $("#point").val();
    //客户名称
    params.username = $("#username").val();
    //startDate
    params.startDate = $("#datetimeStart").val();
    //endDate
    params.endDate = $("#datetimeEnd").val();
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
    $('#username').val("");
    $('#orderNumber').val("");
    $('#datetimeStart').val("");
    $("#datetimeEnd").val("");
    refresh();
});

//新增按钮点击事件
$("#btn_add").on("click", function () {
    $('#addAndUpdateLabel').text("新增用户信息");
    $('#txt_password').attr("readonly", false);
    $('#txt_type').val("save");
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
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

//清除弹窗原数据
$("#addAndUpdate").on("hidden.bs.modal", function () {
    $('#txt_customer_id').val("");
    $('#txt_point').val("");
    $('#txt_description').val("");
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {
    var id = $('#txt_id').val(),
        customer_id = $('#txt_customer_id').val(),
        point = $('#txt_point').val(),
        description = $('#txt_description').val();

    //验证数据
    if (!customer_id) {
        layer.msg('请填写客户id!', {icon: 2, time: 1500});
        return false;
    }
    if (!point) {
        layer.msg('请填写积分!', {icon: 2, time: 1500});
        return false;
    }
    if (!description) {
        layer.msg('请填写描述!', {icon: 2, time: 1500});
        return false;
    }
    if (description.length > 128) {
        layer.msg('描述字符长度不能超过128!', {icon: 2, time: 1500});
        return false;
    }

    $.ajax({
        url: '/point/save.do',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        data: {
            customerId: customer_id,
            point: point,
            description: description
        },
        //阻止深度序列化，向后台传送数组
        traditional: true,
        success: function (msg) {
            if (msg.state === 1) {
                layer.msg(msg.message, {icon: 1, time: 1500});
            } else {
                layer.msg(msg.message, {icon: 2, time: 1500});
            }
            refresh();
        }
    })
});


