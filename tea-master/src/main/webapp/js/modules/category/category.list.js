$(function () {
    $.ajax({
       type: "Post",
        url: "/categoryTree/list.do",
        dataType: "json",
        success: function (result) {
            $('#tree').treeview({
                color: "#428bca",
                data: result,
                emptyIcon: '',
              /*  showCheckbox: true,   //是否显示复选框
                highlightSelected: true,    //是否高亮选中
                //nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
                nodeIcon: 'glyphicon glyphicon-globe',
                emptyIcon: '',    //没有子节点的节点图标
                multiSelect: false,    //多选*!/*/
                onNodeChecked: function (event,data) {
                    alert(data);
                },
                onNodeSelected: function (event, data) {
                    /*alert(data.nodeId);*/
                    var categoryId=data.id;
                    $.ajax({
                        type: "Post",
                        url: "/categoryTree/findCategoryById.do",
                        data: {
                            categoryId:categoryId
                        },
                        success: function (data) {

                            $('#txt_id').val(data.data.entity.id);
                            $('#categoryName').val(data.data.entity.categoryName);
                            var optionstring = "";
                            for (var j = 0; j < data.data.entity.topCategory.length;j++) {
                                if(data.data.entity.topCategory[j].id==data.data.entity.pid) {
                                    optionstring += "<option selected = \"selected\" value=\"" + data.data.entity.topCategory[j].id + "\" >" + data.data.entity.topCategory[j].categoryName + "</option>";
                                }else{
                                    optionstring += "<option value=\"" + data.data.entity.topCategory[j].id + "\" >" + data.data.entity.topCategory[j].categoryName + "</option>";
                                }
                                if(data.data.entity.pid!=0) {
                                    $("#txt_category").html(optionstring);
                                }
                                else{
                                    $("#txt_category").html("<option value='0'>===已经是父节点，无需选择===</option> " );
                                }
                            }

                            $('#sort').val(data.data.entity.sort);
                            $('#code').val(data.data.entity.code);
                            $('#txt_type').val("update");
                        }
                        }
                    );
                }
            });
        },
        error: function () {
            alert("树形结构加载失败！")
        }
    });
})

//编辑按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {
    var id = $('#txt_id').val(),
        categoryName = $('#categoryName').val(),
        sort = $('#sort').val(),
        pid=$('#txt_category option:selected').val(),
        code = $('#code').val(),
        type =$('#txt_type').val();


    //验证数据
    if (!categoryName) {
        layer.msg('请填写商品分类名称!', {icon: 2, time: 1500});
        return false;
    }
    if (!sort) {
        layer.msg('请填写商品分类排序!', {icon: 2, time: 1500});
        return false;
    }
    if (!pid) {
        layer.msg('请填写商品分类父节点!', {icon: 2, time: 1500});
        return false;
    }
    if (!code) {
        layer.msg('请填写商品分类编号!', {icon: 2, time: 1500});
        return false;
    }

    $.ajax({
        url: '/categoryTree/' + type + '.do',
        method: 'post',
        data: {
            id :id,
            categoryName :categoryName,
            sort : sort,
            pid :pid,
            code : code
        },
        success: function (msg) {
            if (msg.state == 1) {
                layer.msg(msg.message, {icon: 1, time: 1500});
            } else {
                layer.msg(msg.message, {icon: 2, time: 1500});
            }
            var url = "/category/index.do?t=" + Math.random(1000);
            $(".content").load(url);
        }
    })
});
//新增按钮点击事件
$("#btn_add_submit").off().on('click', function () {
    $('#addSon').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
    var categoryName = $('#s_categoryName').val(),
        sort = $('#s_sort').val(),
        pid=$('#txt_s_category option:selected').val(),
        code = $('#s_code').val();


    //验证数据
    if (!categoryName) {
        layer.msg('请填写商品分类名称!', {icon: 2, time: 1500});
        return false;
    }
    if (!sort) {
        layer.msg('请填写商品分类排序!', {icon: 2, time: 1500});
        return false;
    }
    if (!pid) {
        layer.msg('请填写商品分类父节点!', {icon: 2, time: 1500});
        return false;
    }
    if (!code) {
        layer.msg('请填写商品分类编号!', {icon: 2, time: 1500});
        return false;
    }


    $.ajax({
        url: '/categoryTree/save.do',
        method: 'post',
        data: {
            categoryName :categoryName,
            sort : sort,
            pid :pid,
            code : code
        },
        success: function (msg) {
            if (msg.state == 1) {
                layer.msg(msg.message, {icon: 1, time: 1500});
            } else {
                layer.msg(msg.message, {icon: 2, time: 1500});
                }
            $('#addSon').modal('hide')
        }
    })
});
//关闭modal后的事件
$('#addSon').on('hidden.bs.modal', function () {
    // 执行一些动作...
    var url = "/category/index.do?t=" + Math.random(1000);
    $(".content").load(url);
})

//查找一级分类
function findTopCategory() {
    $.ajax({
        url: '/categoryTree/findTopCategory.do',
        type: "post",
        data: {},
        success: function (data) {
            var optionstring = "";
            for (var j = 0; j < data.length;j++) {
                optionstring += "<option value=\"" + data[j].id + "\" >" + data[j].categoryName + "</option>";
                $("#txt_s_category").html("<option value=''>=========请选择========</option> "+optionstring);
            }
        },
    })
}

//清除弹窗原数据
$("#addSon").on("show.bs.modal", function () {
    findTopCategory();
});

//新增根结点
$("#btn_add_parent_submit").off().on('click', function () {
    $('#addParent').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
    var categoryName = $('#p_categoryName').val(),
        sort = $('#p_sort').val(),
        code = $('#p_code').val();


    //验证数据
    if (!categoryName) {
        layer.msg('请填写商品分类名称!', {icon: 2, time: 1500});
        return false;
    }
    if (!sort) {
        layer.msg('请填写商品分类排序!', {icon: 2, time: 1500});
        return false;
    }
    if (!code) {
        layer.msg('请填写商品分类编号!', {icon: 2, time: 1500});
        return false;
    }


    $.ajax({
        url: '/categoryTree/saveParent.do',
        method: 'post',
        data: {
            categoryName :categoryName,
            sort : sort,
            code : code
        },
        success: function (msg) {
            if (msg.state == 1) {
                layer.msg(msg.message, {icon: 1, time: 1500});
            } else {
                layer.msg(msg.message, {icon: 2, time: 1500});
            }
            $('#addParent').modal('hide');
        }
    })
});
//关闭modal后的事件
$('#addParent').on('hidden.bs.modal', function () {
    // 执行一些动作...
    var url = "/category/index.do?t=" + Math.random(1000);
    $(".content").load(url);
})


//删除结点方法
$("#btn_del_parent_submit").off().on('click', function () {
    var id = $('#txt_id').val();
    if(id.trim() == ""){
        layer.msg("请先选择分类", {icon: 2, time: 1500});
        return false
    }
    $.ajax({
        url: '/categoryTree/deletePre.do',
        method: 'post',
        data: {
            id :id
        },
        success: function (msg) {
            if (msg.state == 1) {
                layer.msg(msg.message, {icon: 1, time: 1500});
                $('#deleteP').modal('hide')
            } else {
                layer.msg(msg.message, {icon: 2, time: 1500});
            }
        }
    })
});

$('#deleteP').on('hidden.bs.modal', function () {
    // 执行一些动作...
    var url = "/category/index.do?t=" + Math.random(1000);
    $(".content").load(url);
})


/*
function getTree() {
    // Some logic to retrieve, or generate tree structure
    var data = [
        {

            text: "北乌龙",
            nodes: [
                {
                    text: "武夷岩茶"
                },
                {
                    text: "水仙"
                },
            ]
        },
        {
            text: "南乌龙",
            nodes: [
                {
                    text: "铁观音"
                },
                {
                    text: "奇兰"
                },
                {
                    text: "黄金桂"
                },
            ]
        }
    ]
    return data;
}*/
