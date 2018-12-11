/**
 * Created by jacen on 18/09/21.
 */
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
    $('#userName').val("");
    $('#sex').val("");
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

//清除弹窗原数据
$("#addAndUpdate").on("hidden.bs.modal", function () {
    $('#txt_username').val("");
    document.getElementById('p_man').checked = true;
    $('#txt_password').val("");
    $('#txt_email').val("");
    $('#txt_mobile').val("");
});

//弹框保存按钮点击事件
$("#btn_add_update_submit").off().on('click', function () {
    var id = $('#txt_id').val(),
        name = $('#txt_username').val(),
        sex = $('input:radio[name="sex"]:checked').val(),
        pwd = $('#txt_password').val(),
        email = $('#txt_email').val(),
        phone = $('#txt_mobile').val(),
        type = $('#txt_type').val();

    //验证数据
    if (!name) {
        layer.msg('请填写名称!', {icon: 2, time: 1500});
        return false;
    }
    if (!pwd) {
        layer.msg('请填写密码!', {icon: 2, time: 1500});
        return false;
    }
    if (!email) {
        layer.msg('请填写邮箱!', {icon: 2, time: 1500});
        return false;
    }
    if (!phone) {
        layer.msg('请填写手机!', {icon: 2, time: 1500});
        return false;
    }

    $.ajax({
        url: '../user/' + type + '.do',
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

//tr中编辑按钮点击事件
function editData(row) {
    //向模态框中传值
    $('#txt_id').val(row.id);
    $('#txt_username').val(row.username);
    $('#txt_password').val(row.password);
    $('#txt_password').attr("readonly", true);
    $('#txt_email').val(row.email);
    $('#txt_mobile').val(row.mobile);
    if (row.sex == 0) {
        document.getElementById('p_woman').checked = true;
    } else {
        document.getElementById('p_man').checked = true;
    }
    $('#txt_type').val("update");

    $('#addAndUpdateLabel').text("修改用户信息");

    //显示模态窗口
    $('#addAndUpdate').modal({
        //点击ESC键,模态窗口即会退出。
        keyboard: true
    });
}

//tr中删除按钮点击事件
function delData(id) {
    layer.confirm('确定要删除用户编号为' + id + '数据?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            url: '../user/delete.do',
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