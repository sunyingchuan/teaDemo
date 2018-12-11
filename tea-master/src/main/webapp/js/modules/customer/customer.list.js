/**
 * Created by Grant on 18/10/09.
 */

//datePicker初始化配置
$("#datetimeStart").datepicker({
    format: 'yyyy-mm-dd',
    minView: 'month',
    language: 'zh-CN',
    autoclose: true,
    endDate: new Date()
}).on('changeDate', function (ev) {
    $("#datetimeEnd").datepicker("setStartDate", $("#datetimeStart").val());
});

$("#datetimeEnd").datepicker({
    format: 'yyyy-mm-dd',
    minView: 'month',
    language: 'zh-CN',
    autoclose: true,
    endDate: new Date()
}).on('changeDate', function (ev) {
    $("#datetimeStart").datepicker("setEndDate", $("#datetimeEnd").val());
});

var $table = $('#users');
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
    $('#users').bootstrapTable('hideLoading');
})

//初始化Table
function tableInit() {
    //先销毁表格
    $table.bootstrapTable('destroy');

    $table.bootstrapTable({
        //请求地址
        url: 'customer/list.do',
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
        pageList: "[10,15,20,50,100]",
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
            field: 'checkbox',
            checkbox: true,
            align: 'center',
            valign: 'middle'
        }, {
            title: '序号',
            field: 'index',
            align: 'center',
            valign: 'middle',
            formatter: genderIndex
        }, {
            title: '用户编号',
            field: 'id',
            align: 'center',
            valign: 'middle',
            visible: false
        }, {
            title: '用户名称',
            field: 'username',
            align: 'center',
            valign: 'middle'
        }, {
            title: '性别',
            field: 'sex',
            formatter: genderSex,
            align: 'center',
            valign: 'middle'
        }, {
            title: '用户邮箱',
            field: 'email',
            align: 'center',
            valign: 'middle'
        }, {
            title: '用户手机',
            field: 'mobile',
            align: 'center',
            valign: 'middle'
        }, {
            title: '微信ID',
            field: 'wxID',
            align: 'center',
            valign: 'middle'
        }, {
            title: '余额',
            field: 'balance',
            align: 'center',
            valign: 'middle'
        }, {
            title: '积分',
            field: 'point',
            align: 'center',
            valign: 'middle'
        }, {
            title: '创建人',
            field: 'createEmpName',
            align: 'center',
            valign: 'middle'
        }, {
            title: '创建时间',
            field: 'createTime',
            formatter: dateFormat,
            align: 'center',
            valign: 'middle'
        }, {
            title: '修改人',
            field: 'modifyEmpName',
            align: 'center',
            valign: 'middle'
        }, {
            title: '修改时间',
            field: 'modifyTime',
            formatter: dateFormat,
            align: 'center',
            valign: 'middle'
        }, {
            title: '状态',
            field: 'status',
            formatter: genderStatus,
            align: 'center',
            valign: 'middle'
        }, {
            title: '操作',
            field: 'operate',
            align: 'center',
            valign: 'middle',
            events: operateEvents,
            formatter: genderOpt
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

//替换sex数据为文字
function genderSex(value, row, index) {
    if (value == null || value == undefined) {
        return "-";
    } else if (value == 1) {
        return "男";
    } else if (value == 0) {
        return "女";
    }
}

//格式化日期
function dateFormat(value, row, index) {
    if (value == undefined || value == "" || null == value) {
        return value;
    }
    return new Date(value).toLocaleString();
}

//替换status数据为文字
function genderStatus(value, row, index) {
    if (value == null || value == undefined) {
        return "-";
    } else if (value == 0) {
        return "已禁用";
    } else if (value == 1) {
        return "正常";
    }
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

//自定义列内容
function genderOpt() {
    return [
        '<a id="balance-record" href="javascript:void(0)" title="账单记录" >',
        '<i class="glyphicon glyphicon-list-alt" style="margin-right: 5px;"></i>',
        '</a>  ',
        '<a id="resetpwd" href="javascript:void(0)" title="重置密码" >',
        '<i class="glyphicon glyphicon-hourglass" style="margin-right: 5px;"></i>',
        '</a>  ',
        '<a id="valid" href="javascript:void(0)" title="禁用">',
        '<i class="glyphicon glyphicon-ban-circle"></i>',
        '</a>  '
    ].join('');
}

//自定义列内容事件
window.operateEvents = {
    'click #balance-record': function (e, value, row, index) {
        balanceRecord(row.id);
    },
    'click #valid': function (e, value, row, index) {
        valid(row, index + 1);
    },
    'click #resetpwd': function (e, value, row, index) {
        resetPwd(row.id);
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
    //userName
    params.userName = $("#username").val();
    //mobile
    params.mobile = $("#mobile").val();
    //sex
    params.sex = $('#sex option:selected').val();
    //startDate
    params.startDate = $("#datetimeStart").val();
    //endDate
    params.endDate = $("#datetimeEnd").val();
    return params;
}

//----------------------------crud---------------------------------------
//刷新页面
function refresh() {
    $table.bootstrapTable('refresh', {pageNumber: this.pageNumber});
}

//查询按钮点击事件
$("#btn_search").on("click", function () {
    refresh();
});

//新增按钮点击事件
$("#btn_add").on("click", function () {
    $('#addAndUpdateLabel').text("新增用户信息");
    $('#txt_password').attr("placeholder", "密码");
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
    $('#txt_username').val("");
    document.getElementById('p_man').checked = true;
    $('#txt_password').val("");
    $('#check_password').val("");
    $('#txt_email').val("");
    $('#txt_mobile').val("");
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {
    var id = $('#txt_id').val(),
        name = $('#txt_username').val(),
        sex = $('input:radio[name="sex"]:checked').val(),
        pwd = $('#txt_password').val(),
        checkPwd = $('#check_password').val(),
        email = $('#txt_email').val(),
        phone = $('#txt_mobile').val(),
        type = $('#txt_type').val();

    //验证数据
    if (!name) {
        layer.msg('请填写名称!', {icon: 2, time: 1500});
        return false;
    }
    if (type === "save" && !pwd) {
        layer.msg('请填写密码!', {icon: 2, time: 1500});
        return false;
    }
    if (pwd.length < 6 || pwd.length > 20) {
        layer.msg('密码长度应为6-20位！', {icon: 2, time: 1500});
        return false;
    }
    if (pwd !== checkPwd) {
        layer.msg('密码不一致！', {icon: 2, time: 1500});
        return false;
    }

    $.ajax({
        url: '../customer/' + type + '.do',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        data: {
            id: id,
            username: name,
            sex: sex,
            password: pwd,
            email: email,
            mobile: phone
        },
        //阻止深度序列化，向后台传送数组
        traditional: true,
        success: function (msg) {
            if (msg.state == 1) {
                layer.msg(msg.message, {icon: 1, time: 1500});
            } else {
                layer.msg(msg.message, {icon: 2, time: 1500});
            }
            refresh();
        }
    })
});

//tr中账单记录按钮点击事件
function balanceRecord(id) {
    $('#balanceRecordLabel').text("新增用户信息");
    $('#balanceRecordDialog').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
    $("#records").bootstrapTable('destroy');
    $("#records").bootstrapTable({
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
        queryParams: function (params) {
            //排序方式
            params.order = "id desc";
            //第几页
            params.pageCurrent = this.pageNumber;
            //第页条数
            params.pageSize = this.pageSize;
            //id
            params.customerId = id;
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
            title: '序号',
            field: 'index',
            align: 'center',
            valign: 'middle',
            formatter: genderIndex
        }, {
            title: '记录编号',
            field: 'id',
            align: 'center',
            valign: 'middle',
            visible: false
        }, {
            title: '用户编号',
            field: 'customerId',
            align: 'center',
            valign: 'middle',
            visible: false
        }, {
            title: '用户名',
            field: 'username',
            align: 'center',
            valign: 'middle',
        }, {
            title: '类型',
            field: 'type',
            formatter: genderType,
            align: 'center',
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
        }, {
            title: '新余额',
            field: 'balanceNew',
            align: 'center',
            valign: 'middle'
        }, {
            title: '创建人',
            field: 'createEmpName',
            align: 'center',
            valign: 'middle'
        }, {
            title: '创建时间',
            field: 'createTime',
            formatter: dateFormat,
            align: 'center',
            valign: 'middle'
        }, {
            title: '修改人',
            field: 'modifyEmpName',
            align: 'center',
            valign: 'middle'
        }, {
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

//tr中禁用按钮点击事件
function valid(row, index) {
    var id = row.id;
    var status = row.status;
    layer.confirm('确定要禁用（启用）序号为' + index + '的用户？', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: '../customer/valid.do',
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            //阻止深度序列化，向后台传送数组
            traditional: true,
            data: {id: id.toString(), status: status.toString()},
            success: function (msg) {
                if (msg.state == 1) {
                    layer.msg(msg.message, {icon: 1, time: 1500});
                } else {
                    layer.msg(msg.message, {icon: 2, time: 1500});
                }
                refresh();
            }
        })
    });
}

//tr中重置密码按钮点击事件
function resetPwd(id) {
    layer.confirm('确定要重置序号为' + id + '用户的密码?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: '../customer/resetPwd.do',
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            //阻止深度序列化，向后台传送数组
            traditional: true,
            data: {id: id.toString()},
            success: function (msg) {
                if (msg.state == 1) {
                    layer.msg(msg.message, {icon: 1, time: 1500});
                } else {
                    layer.msg(msg.message, {icon: 2, time: 1500});
                }
                refresh();
            }
        })
    });
}

//操作积分按钮点击事件
$("#btn_add_point").on("click", function () {
    var rows = $("#users").bootstrapTable('getSelections');
    if (rows.length <= 0) {
        alert("请选中至少一行数据。");
    } else {
        var names = [];
        for (var i = 0; i < rows.length; i++) {
            names.push(rows[i].username);
        }
        $('#txt_customer_list').val(names);

        $('#pointAddLabel').text("新增积分记录信息");
        $('#add_point').modal({
            //点击ESC键,模态窗口即会退出。
            keyboard: true
        });
    }
});

//清除弹窗原数据
$("#add_point").on("hidden.bs.modal", function () {
    $('#txt_point').val("");
    $('#txt_description').val("");
});

//操作积分弹窗保存
$("#btn_add_point_save").off().on("click", function () {
    return addPoint();
});

//操作积分时后台交互函数
function addPoint() {
    var rows = $("#users").bootstrapTable('getSelections');


    var point = $('#txt_point').val(),
        description = $('#txt_description').val();

    //验证数据
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

    var ids = [];
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].id);
    }
    $.ajax({
        url: '/point/batchInsert.do',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        //阻止深度序列化，向后台传送数组
        traditional: true,
        data: {
            ids: ids,
            point: point,
            description: description
        },
        success: function (msg) {
            if (msg.state === 1) {
                layer.msg(msg.message, {icon: 1, time: 1500});
            } else {
                layer.msg(msg.message, {icon: 2, time: 1500});
            }
            refresh();
        }
    });

}