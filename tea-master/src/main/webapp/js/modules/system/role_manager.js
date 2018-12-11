var pageContext = document.getElementById("PageContext").getAttribute("value");


// -----------------------------显示以及查询开始-------------------

/**
 * @Author yufeng.lim@ucarinc.com
 * @Description  获取搜索条件对象
 **/
function getQueryObj() {
    var roleName = $("#search_roleName").val();
    var description = $("#search_role_desc").val();
    console.log(" tow parms :" + roleName + ":" + description);
    return {
        "roleName": roleName,
        "description": description
    }
}

/**
 * 清空查询条件
 */
function enptySearchCondition() {
    $("#search_roleName").val(null);
    $("#search_role_desc").val(null);
}


/**
 * 初始化用户表格函数
 **/
function init_role_info_tab() {
    //销毁原先
    $('#tab_role_list').bootstrapTable('destroy');

    $('#tab_role_list').bootstrapTable({
        contentType: "application/json;charset=UTF-8",
        url: pageContext + '/role/list.do',//要请求数据的文件路径
        dataType: "json",
        dataField: "records",   //对应返回存储list的字段名
        locale: 'zh-CN',//中文支持,
        method: 'post',
        responseHandler: function (res) {
            //在ajax获取到数据之后，渲染表格之前，修改数据源
            //暂时这样设置总记录数
            console.log("res.total" + res.total)
            res.total = res.total;
            return res;
        },
        toolbar: '#role_toolbar',
        selectItemName: "btSelectItem",
        clickToSelect: true,//是否启用点击选中行
        cache: false,
        striped: true,
        pagination: true,
        pageSize: 10,
        pageNumber: 1,
        pageList: [5, 10, 15, 20, 25, 30],
        showColumns: true,
        showRefresh: false,
        showExport: false,
        exportTypes: ['csv', 'txt', 'xml'],
        search: false,
        clickToSelect: true,
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        queryParams: function (params) {
            var cu_page_num = params.offset / params.limit + 1;
            console.log(cu_page_num);
            console.log("params:" + params);
            var roleParms = getQueryObj();
            console.log("roleParms:" + roleParms.roleName);
            var quarmObj = {
                name: roleParms.roleName,
                description: roleParms.description,
                pageLimit: params.limit,
                pageOffset: params.offset
            }
            return JSON.stringify(quarmObj);
        },
        columns:
            [{
                title: '全选',
                field: 'select',
                //复选框
                checkbox: true,
                width: 25,
                align: 'center',
                valign: 'middle'
            },
                {field: "id", title: "序号", align: "center", valign: "middle", sortable: "true"},
                {field: "name", title: "角色名称", align: "center", valign: "middle", sortable: "true"},
                {field: "description", title: "描述", align: "center", valign: "middle", sortable: "true"},
                {
                    field: "createTime", title: "创建时间", align: "center", valign: "middle", sortable: "true",
                    formatter: function (value, row, index) {
                        var value = getMyDate(value);
                        return value;
                    }
                },
                {field: "createEmp", title: "创建人", align: "center", valign: "middle", sortable: "true"},
                {field: "modifyEmp", title: "修改人", align: "center", valign: "middle", sortable: "true"},
                {
                    field: "modifyTime", title: "修改时间", align: "center", valign: "middle", sortable: "true",
                    formatter: function (value, row, index) {
                        var value = getMyDate(value);
                        return value;
                    }
                },
                {field: "remark", title: "备注", align: "center", valign: "middle", sortable: "true"}
            ]
    });

}


$("#btn_role_search").click(function () {
    console.log("开始查询")
    $("#tab_role_list").bootstrapTable('refresh', {silent: true});

});
$("#btn_role_clean_search").click(function () {
    console.log("清空查询")
    enptySearchCondition();
    $("#tab_role_list").bootstrapTable('refresh', {silent: true});
});

//-----------------------------显示以及查询结束--------------------------------------------
/**
 * @Author yufeng.lim@ucarinc.com
 * @Description  文档加载完成后执行
 * @Date 16:42 2018/9/30
 * @Param
 * @return
 **/
$(function () {
    init_role_info_tab();
});


//-------------------新增开始------------
/**
 * @Author yufeng.lim@ucarinc.com
 * @Description  点击新增按钮
 * @Date 17:45 2018/9/30
 **/
$("#btn_role_add").click(function () {
    console.log("add begin");
    $("#role_modal_add").modal('show');
});

function validatorRoleCommit(roleObj) {
    if (roleObj.name == null || roleObj.name == '') {
        layer.msg("角色名不能为空", {
            offset: 'auto',
            anim: 6
        });
        return false;
    }
    if (roleObj.description == null || roleObj.description == '') {
        layer.msg("描述不能为空", {
            offset: 'auto',
            anim: 6
        });
        return false;
    }
    return true;
}


/**
 * 新增点击操作,验证后提交表单
 */
$("#btn_save_new_role").click(function () {
    //获取数据转json
    var entity = $("#role_insert_form").serializeArray();
    var obj = arrayToJsonObject(entity);
    if (!validatorRoleCommit(arrayToObject(entity))) {
        return;
    }
    ;
    console.log(" 即将提交新增的数据：" + obj);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        contentType: "application/json;charset=UTF-8",
        url: pageContext + "/role/add.do",//url
        data: obj,
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.state == 0) {
                layer.msg(result.message);
            } else if (result.state == 1) {
                layer.msg(result.message);
                //关闭模态框，刷新数据
                $("#role_modal_add").modal('hide');
                $("#tab_role_list").bootstrapTable('refresh', {silent: true});
            }
        },
        error: function () {
            layer.msg("异常！");
        }
    });

});
//----------------------------新增结束


//------------------------删除开始-------------------
$("#btn_role_delete").click(function () {
    console.log("删除操作");
    dele_role_fun();
});

/**
 * 判断是否删除超级管理员
 */
function jugeHasAdminToDel(arrs) {
    for (var i = 0; i < arrs.length; i++) {
        if (arrs[i].name == '超级管理员' || arrs[i].id == 1) {
            return true;
        }
    }
    return false;
}

/**
 * 获取对象的id 数组
 */
function getObjArrsIds(arrs) {

    var ids_str = "";
    for (var i = 0; i < arrs.length; i++) {
        ids_str = ids_str + arrs[i].id + ",";
    }
    ids_str = ids_str.substring(0, ids_str.lastIndexOf(','));
    console.log(ids_str);
    return ids_str;
}


/**
 * 删除角色函数
 */
function dele_role_fun() {
    var getSelectRows = $("#tab_role_list").bootstrapTable('getSelections', function (row) {
        return row;
    });
    console.log(getSelectRows);
    //判断是否选择超级管理员
    if (jugeHasAdminToDel(getSelectRows)) {
        layer.msg("超级管理员不可删除");
        return;
    }
    ;

    var msg = "您真的确定要删除吗？请确认！";
    if (getSelectRows.length == 0) {
        layer.msg("必须选中才能删除");
        return null;
    }
    if (confirm(msg) == true) {
        //获取数据转json
        var strs = getObjArrsIds(getSelectRows);
        var jsonStr = JSON.stringify(strs);
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            contentType: "application/json;charset=UTF-8",
            url: pageContext + "/role/delete.do",//url
            data: jsonStr,
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.state == 0) {
                    layer.msg(result.message);
                } else if (result.state == 1) {
                    layer.msg(result.message);
                    //关闭模态框，刷新数据
                    $("#tab_role_list").bootstrapTable('refresh', {silent: true});
                }
            },
            error: function () {
                layer.msg("异常！");
            }
        });

    }

}

//------------------------删除结束--------------------

//-------------------------修改开始-------------------


$("#btn_role_update").click(function () {
    console.log("修改开始")
    update_role_begin();
});

/**
 * 数据回填函数
 */
function set_data_back(data_old) {
    var selectedRow = data_old[0];
    $("#update_id").val(selectedRow.id);
    $("#update_name").val(selectedRow.name);
    $("#update_description").val(selectedRow.description);
}

/**
 * 修改起始函数
 * @returns {null}
 */
function update_role_begin() {
    var getSelectRows = $("#tab_role_list").bootstrapTable('getSelections', function (row) {
        return row;
    });
    if (getSelectRows.length != 1) {
        layer.msg("必须选中一行才能修改");
        return null;
    }
    $("#role_modal_update").modal('show');
    set_data_back(getSelectRows);

}

/**
 * 点击修改按钮
 */
$("#btn_update_role").click(function () {

    var entity = $("#role_update_form").serializeArray();
    var obj = arrayToJsonObject(entity);
    if (!validatorRoleCommit(arrayToObject(entity))) {
        return;
    }
    ;
    console.log(" 即将提交修改的数据：" + obj);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        contentType: "application/json;charset=UTF-8",
        url: pageContext + "/role/update.do",//url
        data: obj,
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.state == 0) {
                layer.msg(result.message);
            } else if (result.state == 1) {
                layer.msg(result.message);
                //关闭模态框，刷新数据
                $("#role_modal_update").modal('hide');
                $("#tab_role_list").bootstrapTable('refresh', {silent: true});
            }
        },
        error: function () {
            layer.msg("异常！");
        }
    });
});
//-----------------------修改结束----------------------------------
//----------------------刷新操作----------------
$("#btn_role_refresh").click(function () {
    init_role_info_tab();
    layer.msg("刷新成功", {
        offset: 'auto',
        anim: 6
    });

});
//---------------------刷新结束-----------------------

//---------------------设置角色开始--------------
$("#btn_role_setPermission").click(function () {
    setPermission();
});


var ztreeObj = null;
// 设置权限方法
var setPermission = function () {
    var list = $("#tab_role_list").bootstrapTable("getSelections");
    if (list.length == 0) {
        layer.msg("请选择一条记录进行编辑", {
            offset: 't',
            anim: 6
        });
        return;
    }
    if (list.length > 1) {
        layer.msg("一次只能编辑一条记录", {
            offset: 't',
            anim: 6
        });
        return;
    }

    $.ajax({
        "type": "GET",
        "url": pageContext + "/permission/getPermissionListWithChecked/" + list[0].id + ".do",
        "dataType": "json",
        "success": function (resp) {
            console.log(resp)
            if (resp.state == 1) {
                console.log("开始生成树");
                // 生成树
                ztreeObj = $.fn.zTree.init($("#permissionTree"), getZTreeSetting(), resp.data);

                layer.open({
                    title: "【" + list[0].name + '】关联权限',
                    type: 1,
                    content: $("#permissionUI"),
                    area: ['400px', '350px'],
                    btn: ['保存', '取消'],
                    yes: function (index, layero) {
                        var nodes = ztreeObj.getCheckedNodes(true);
                        var permissionIds = [];
                        if (nodes.length > 0) {
                            for (var i = 0; i < nodes.length; i++) {
                                permissionIds.push(nodes[i].id);
                            }
                        }
                        // 发送请求
                        $.ajax({
                            "type": "POST",
                            "url": pageContext + "/role/setPermission.do",
                            "data": {roleId: list[0].id, permissionIds: permissionIds.join(",")},
                            "dataType": "json",
                            "success": function (resp) {
                                if (resp.state == 1) {
                                    layer.msg("设置成功!")
                                    layer.close(index);
                                    $("#tab_role_list").bootstrapTable('refresh');
                                } else {
                                    layer.alert(resp.msg, {
                                        "icon": 2
                                    });
                                }
                            }
                        });
                    }
                });
            } else {
                layer.alert(resp.msg, {
                    "icon": 2
                });
                return;
            }
        }
    });

}

//ztree设置
function getZTreeSetting() {
    return {
        check: {
            enable: true,
            chkboxType: {"Y": "p", "N": "s"}
        },
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function (treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("permissionTree");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);
                }
                return false;
            }
        }
    };
}

//-------------设置角色结束---------------











































