var pageContext = document.getElementById("PageContext").getAttribute("value");


var commonUtil = {
    initTable: function (obj) {
        var id = obj.id || "table";
        var table = $('#' + id).bootstrapTable({
            treeShowField: obj.treeShowField,
            parentIdField:obj.parentIdField,
            url: obj.url,
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            // striped: true,
            search: obj.search,
            cache:false,
            height: obj.height,
            toolbar: obj.toolbar,
            showRefresh: false,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: obj.detailView,
            detailFormatter: commonUtil.detailFormatter,
            pagination: obj.pagination,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'id',
            maintainSelected: true,
            pageSize: obj.pageSize,
            pageList: obj.pageList,
            responseHandler: function (resp) {
                if (resp.state == 0) {
                    layer.msg(resp.msg);
                    return;
                }
                return {
                    "total": resp.total, // 总页数
                    "rows": resp.records // 数据
                };
            },
            columns: obj.columns,
            onClickCell: obj.onClickCell,
            onExpandRow: obj.onExpandRow
        });
        return table;
    },
    // 数据表格展开内容
    detailFormatter: function (index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });

        return html.join('');
    }
}



$( function (ev) {
    init_tab_list();

});


function init_tab_list () {
    $("#permission_list_id").innerHTML = "";
    $("#permission_list_id").bootstrapTable('destroy');
    // console.log("执行了一次");
    // 查询数据
    var tableObj = commonUtil.initTable({
        id: "permission_list_id",
        treeShowField: 'name',
        parentIdField: 'pid',
        url: pageContext + "/permission/list.do",
        search: false,
        toolbar: '#permission_toolbar',
        detailView: true,
        pagination: true,
        pageSize: 20,
        pageList: [20, 40, 60, 80],
        columns: [
            {checkbox: true},
            {field: 'id', visible: false, title: 'ID'},
            {field: 'name', align: 'center', title: '名称', width: 350},
            {field: 'parentName', align: 'center', title: '父级名称', width: 300},
            {
                field: 'type',
                title: '类型',
                align: 'center',
                width: 200,
                valign: 'middle',
                formatter: function (type, index) {
                    if (type === 1) {
                        return '<span class="label label-primary">目录</span>';
                    }
                    if (type === 2) {
                        return '<span class="label label-success">菜单</span>';
                    }
                    if (type === 3) {
                        return '<span class="label label-warning">按钮</span>';
                    }
                }
            },
            {field: 'url', align: 'center', title: 'URL', width: 400},
            {field: 'code', title: '权限代码', align: 'center', width: 200}
        ],
        onExpandRow: function (index, row, $detail) {
//        	if (row.type != 3) {
//        		console.log($detail)
//        	}
        }
    });

};

/**
 * @Author yufeng.lim@ucarinc.com
 * @Description  提交验证
 * @Date 15:43 2018/10/9
 **/
function validatorPermissionCommit(permissionObj) {
    // console.log(typeof (permissionObj ));
    // console.log(permissionObj.name)

    if (permissionObj["name"] == null || permissionObj["name"] == '') {

        layer.msg("权限名不能为空", {
            offset: 'auto',
            anim: 6
        });
        return false;
    }
    if (permissionObj.pid == null || permissionObj.pid == 0 || permissionObj.pid == -1) {
        layer.msg("父节点不能为空", {
            offset: 'auto',
            anim: 6
        });
        return false;
    }
    if (permissionObj.type == null || permissionObj.type == 0 || permissionObj.type == '') {
        layer.msg("类型不能为空", {
            offset: 'auto',
            anim: 6
        });
        return false;
    }
    return true;
}

//-------------新增开始--------------
$("#btn_permission_add").on("click", function () {
    $("#permission_insert_form").resetForm();
    renderPidAndPTypeOptions();
    $("#permission_modal_add").modal('show');

});

/*
 * @Author yufeng.lim@ucarinc.com
 * @Description  渲染新增的2个option
 **/
function renderPidAndPTypeOptions() {
    $.ajax({
        "type": "GET",
        "url": pageContext + "/permission/getParentNameAndPermissionType.do",
        "dataType": "json",
        "async": false,
        "success": function (resp) {
            if (resp.state == 1) {
                var parentNameList = resp.data.parentNameList;
                var permissionTypes = resp.data.permissionTypes;
                $("#add_permission_type").empty();
                $("#add_pid").empty();
                $("#add_pid").append('<option ></option>');
                $("#add_permission_type").append('<option ></option>');

                //渲染父名称
                for (var i = 0; i < parentNameList.length; i++) {
                    var option_html = '<option value="' + parentNameList[i].id + '">' + parentNameList[i].name + '</option>';
                    $("#add_pid").append(option_html);
                }
                //    渲染权限类型
                for (var i = 0; i < permissionTypes.length; i++) {
                    var option_html = '<option value="' + permissionTypes[i].index + '">' + permissionTypes[i].name + '</option>';
                    $("#add_permission_type").append(option_html);
                }

            } else {
                console.log("接受的状态码出错");
                layer.msg("无法新增操作", {
                    offset: 'auto',
                    anim: 6
                });
                return;
            }
        }
    });

}

/**
 * @Author yufeng.lim@ucarinc.com
 * @Description  点击确定新增
 **/
$("#btn_save_new_permission").on("click", function () {

    //获取数据
    var entity = $("#permission_insert_form").serializeArray();
    var obj = arrayToJsonObject(entity);
    console.log(" 表单数据：" + obj);
    if ( !validatorPermissionCommit( arrayToObject(entity) ) ){
        return ;
    };

    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        contentType: "application/json;charset=UTF-8",
        url: pageContext + "/permission/add.do",//url
        data: obj,
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.state == 0) {
                layer.msg("操作失败");
            } else if (result.state == 1) {
                layer.msg("操作成功", {
                    offset: 't',
                    anim: 6
                });
                //关闭模态框，刷新数据
                $("#permission_modal_add").modal('hide');
                $("#permission_list_id").bootstrapTable('refresh', {silent: true});
            }
        },
        error: function () {
            layer.msg("异常！");
        }
    });
});


//----------------------新增结束---------------------
//-----------------------修改开始-----------------
$("#btn_permission_update").click(function () {
    var getSelectRows = $("#permission_list_id").bootstrapTable('getSelections', function (row) {
        return row;
    });
    if (getSelectRows.length != 1) {
        layer.msg("必须选中一行才能修改");
        return null;
    }

    //1.先渲染options
    renderPidAndPTypeOptions();
    // 2.显示模态框
    $("#permission_modal_add").modal('show');
    //3.数据回填
    setPermissionDataBack(getSelectRows[0]);


});

//数据回填
function setPermissionDataBack(old_data) {

    $("#permission_id").val(old_data.id);
    $("#permissionName").val(old_data.name);
    $("#add_pid option[value=" + old_data.pid + "]").attr("selected", true);
    $("#add_permission_type option[value=" + old_data.type + "]").attr("selected", true);
    $("#permissionURL").val(old_data.url);
    $("#permissionCode").val(old_data.code);

}

//---------------------修改结束---------------------

//-----------------------删除开始-------------------

$("#btn_permission_delete").click(function () {
    var getSelectRows = $("#permission_list_id").bootstrapTable('getSelections', function (row) {
        return row;
    });
    console.log("即将删除的id：" + getSelectRows);
    var msg = "您真的确定要删除吗？请确认！";
    if (getSelectRows.length == 0) {
        layer.msg("必须选中才能删除", {
            offset: 'auto',
            anim: 6
        });
        return null;
    }
    if (confirm(msg) == true) {
        //获取数据转json
        var strs = arrayToStrings(getSelectRows);
        var jsonStr = JSON.stringify(strs);
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: "application/json;charset=UTF-8",
            url: pageContext + "/permission/delete.do",//url
            data: jsonStr,
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.state == 0) {
                    layer.msg(result.message)
                } else if (result.state == 1) {
                    layer.msg(result.message, {
                        offset: 't',
                        anim: 6
                    });
                    //关闭模态框，刷新数据
                    $("#permission_list_id").bootstrapTable('refresh', {silent: true});
                }
            },
            error: function () {
                layer.msg("异常！");
            }
        });

    }

});

//-----------------------删除结束--------------------------
//-------------------刷新开始---------------
$("#btn_permission_refresh").click(function () {
    init_tab_list();
    layer.msg("刷新成功",{
        offset: 'auto',
        anim: 6
    });
})

//------------------刷新结束----------------






















