/**
 * Created by jacen on 18/09/21.
 */

var $table = $('#goods');
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
    $('#goods').bootstrapTable('hideLoading');
})

//初始化Table
function tableInit() {
    //先销毁表格
    $table.bootstrapTable('destroy');

    $table.bootstrapTable({
        //请求地址
        url: 'goods/list.do',
        //请求方式
        method: 'post',
        //请求内容类型
        contentType: "application/x-www-form-urlencoded",
        //数据类型
        dataType: "json",
        //table高度，
        //如果没有设置height属性，
        //表格自动根据记录条数觉得表格高度
        height: '520',
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
            field: 'state',
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
            title: '商品ID',
            field: 'id',
            align: 'center',
            valign: 'middle',
        },
            {
                title: '商品分类',
                field: 'categoryName',
                align: 'center',
                valign: 'middle',
            },
            {
                title: '商品名称',
                field: 'goodsName',
                align: 'center',
                valign: 'middle'
            }, {
                title: '缩略图',
                field: 'picUrl',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return '<img  src="' + value + '" class="img-rounded" style="width: 80px;height: 70px" >';
                }
            }, {
                title: '商品价格',
                field: 'price',
                align: 'center',
                valign: 'middle'
            }, {
                title: '库存',
                field: 'stock',
                align: 'center',
                valign: 'middle'
            }, {
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
            },
            {
                title: '创建人id',
                field: 'createEmp',
                align: 'center',
                valign: 'middle',
                visible: false
            },
            {
                title: '创建人id',
                field: 'modifyEmp',
                align: 'center',
                valign: 'middle',
                visible: false
            },
            {
                title: '状态',
                field: 'status',
                align: 'center',
                valign: 'middle',
                formatter: genderStatus
            },
            {
                title: '描述',
                field: 'description',
                align: 'center',
                valign: 'middle',
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

//替换delete数据为文字
function genderStatus(value, row, index) {
    if (value == null || value == undefined) {
        return "-";
    } else if (value == 1) {
        return "已上架";
    } else if (value == 0) {
        return "已下架";
    }
}


//替换sex数据为文字
function dateFormat(value, row, index) {
    console.log("=======================>" + value);
    if (value == undefined || value == "" || null == value) {
        return value;
    }
    return new Date(value).toLocaleString();
    //return new Date(value).format("yyyy-MM-dd hh:mm:ss");
}

//替换delete数据为文字
function genderDel(value, row, index) {
    if (value == null || value == undefined) {
        return "-";
    } else if (value == 0) {
        return "已禁用";
    } else if (value == 1) {
        return "正常";
    }
}

//自定义列内容
function genderOpt() {
    return [
        '<a id="edit" href="javascript:void(0)" title="编辑">',
        '<i class="glyphicon glyphicon-pencil"></i>',
        '</a>  ',
        '<a id="remove" href="javascript:void(0)" title="下架">',
        '<i class="glyphicon glyphicon-sort"></i>',
        '</a>'
    ].join('');
}

//自定义列内容事件
window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        editData(row);
    },
    'click #remove': function (e, value, row, index) {
        validData(row, index + 1);
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
    //商品id
    params.id = $("#id").val();

    //商品名称
    params.goodsName = $("#goodsName").val();
    //价格区间
    params.priceStart = $("#priceStart").val();
    params.priceEnd = $("#priceEnd").val();

    params.status = $("#status").val();
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
    $('#goodsName').val("");
    $('#priceStart').val("");
    $('#priceEnd').val("");
    $('#status').val("");
    refresh();
});

//新增按钮点击事件
$("#btn_add").on("click", function () {
    $('#addAndUpdateLabel').text("新增商品信息");
    findSecondCategory();
    $('#txt_type').val("save");
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });

});

//删除按钮点击事件
$("#btn_delete").on("click", function () {
    var datas = $table.bootstrapTable('getSelections');
    if (datas.length < 1) {
        layer.alert('请选择一条或多条数据进行删除！', {icon: 2});
    } else {
        var id = [];
        for (var i = 0; i < datas.length; i++) {
            id.push(datas[i].id);
        }
        delData(id);
    }
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

//批量上架点击事件
$("#btn_status_1").on("click", function () {
    updateStatusList(1);
});

//批量上架点击事件
$("#btn_status_0").on("click", function () {
    updateStatusList(0);
});

//清除弹窗原数据
$("#addAndUpdate").on("hidden.bs.modal", function () {
    findSecondCategory();
    $('#txt_id').val("");
    $('#txt_goodsName').val("");
    $('#txt_categoryId').val("");
    /*$('#txt_category').val("");*/
    $('#txt_picUrl').val("");
    $('#txt_price').val("");
    $('#txt_stock').val("");
    $('#txt_description').val("");
    document.getElementById('p_dowm').checked = true;
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {
    var id = $('#txt_id').val(),
        goodsName = $('#txt_goodsName').val(),
        goodsCategoryId = $('#txt_category').val(),
        status = $('input:radio[name="status"]:checked').val(),
        price = $('#txt_price').val(),
        picUrl = $('#txt_picUrl').val(),
        stock = $('#txt_stock').val(),
        description = $('#txt_description').val(),
        type = $('#txt_type').val();


    //验证数据
    if (!goodsName) {
        layer.msg('请填写商品名称!', {icon: 2, time: 1500});
        return false;
    }
    if (!goodsCategoryId) {
        layer.msg('请选择类别!', {icon: 2, time: 1500});
        return false;
    }
    if (!price) {
        layer.msg('请填写价格!', {icon: 2, time: 1500});
        return false;
    }
    if (!picUrl) {
        layer.msg('上传图片不能为空!', {icon: 2, time: 1500});
        return false;
    }
    if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(picUrl)) {
        layer.msg('请填写正确的图片类型!', {icon: 2, time: 1500});
        return false;
    }
    if (!(picUrl.indexOf("http://") == 0 || picUrl.indexOf("https://") == 0)) {
        layer.msg('请填写http://或https://的图片类型!', {icon: 2, time: 1500});
        return false;
    }
    if (!stock) {
        layer.msg('请填写库存!', {icon: 2, time: 1500});
        return false;
    }
    if (!description) {
        layer.msg('请填写描述!', {icon: 2, time: 1500});
        return false;
    }

    $.ajax({
        url: '/goods/' + type + '.do',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        data: {
            id: id,
            goodsName: goodsName,
            goodsCategoryId: goodsCategoryId,
            status: status,
            picUrl: picUrl,
            price: price,
            stock: stock,
            description: description
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

//tr中编辑按钮点击事件
function editData(row) {
    //向模态框中传值
    $('#txt_id').val(row.id);
    $('#txt_goodsName').val(row.goodsName);
    $('#txt_categoryId').val(row.goodsCategoryId);
   /* $('#txt_categoryName').val(row.categoryName);*/
    $('#txt_picUrl').val(row.picUrl);
    /*$('#status').val(row.status);*/
    $('#txt_price').val(row.price);
    $('#txt_stock').val(row.stock);
    $('#txt_description').val(row.description);
    if (row.status == 0) {
        document.getElementById('p_dowm').checked = true;
    }
    else {
        document.getElementById('p_up').checked = true;
    }
    $('#txt_type').val("update");
    findSecondCategoryByCategoryId(row.goodsCategoryId);
    $('#addAndUpdateLabel').text("修改商品信息");
    //显示模态窗口
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
}

//tr中上/下架按钮点击事件
function validData(row, index) {
    var id = row.id;
    var status = row.status;
    var stock = row.stock;
    if (stock == 0) {
        alert("商品编号为" + index + "的数据库存为0，无法上架");
        return false
    }
    layer.confirm('确定要上/下架商品编号为' + index + '数据?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: '/goods/status.do',
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

//批量上架
function updateStatusList(status) {
    //获取所有被选中的记录
    var rows = $("#goods").bootstrapTable('getSelections');
    if (rows.length == 0) {
        layer.msg("请先选择要上/下架的记录!", {icon: 2, time: 1500});
        return;
    }
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        if (rows[i].stock == 0&&status==1) {
            layer.msg("商品ID为" + rows[i].id + "的数据库存为0，无法上架", {icon: 2, time: 1500});
            return false
        }
        ids += rows[i]['id'] + ",";
    }
    ids = ids.substring(0, ids.length - 1);
    updateStatus(ids,status);
}

function updateStatus(ids,status) {
    var msg;
    if(status==1) {
         msg = "您真的确定要批量上架吗？";
    }
    else{
        msg = "您真的确定要批量下架吗？";
    }
    if (confirm(msg) == true) {
        $.ajax({
            url: '/goods/batchStatus.do',
            type: "post",
            data: {ids: ids, status: status},
            success: function (data) {
                if(status==1) {
                    layer.msg("批量上架成功", {icon: 1, time: 1500});
                }
                else {
                    layer.msg("批量下架成功", {icon: 1, time: 1500});
                }
                //重新加载记录
                // 重新加载数据
                refresh();
            }
        })
    }
}


function findSecondCategoryByCategoryId(rowid) {
    $.ajax({
        url: '/categoryTree/findSecondCategory.do',
        type: "post",
        data: {},
        success: function (data) {
            var optionstring = "";
            for (var j = 0; j < data.length;j++) {
                if(data[j].id==rowid) {
                    optionstring += "<option selected = \"selected\" value=\"" + data[j].id + "\" >" + data[j].categoryName + "</option>";
                }else{
                    optionstring += "<option value=\"" + data[j].id + "\" >" + data[j].categoryName + "</option>";
                }
                $("#txt_category").html(optionstring);
            }
        },
    })
}

function findSecondCategory() {
    $.ajax({
        url: '/categoryTree/findSecondCategory.do',
        type: "post",
        data: {},
        success: function (data) {
            var optionstring = "";
            for (var j = 0; j < data.length;j++) {
                optionstring += "<option value=\"" + data[j].id + "\" >" + data[j].categoryName + "</option>";
                $("#txt_category").html("<option value=''>=========请选择========</option> "+optionstring);
            }
        },
    })
}


