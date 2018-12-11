/**
 * Created by Henry on 18/10/08.
 */
$(document).ready(function(){
    $("#confirmUpdateUserPwd").click(function () {
        updatePassword();
    });
});

function updatePassword() {
    if($("#newPassword").val()!==$("#confirmPassword").val()){
        // 提示错误信息
        layer.alert('两次输入的密码不一致！', {icon: 2,title: '错误'});
        // 新密码和确认密码输入框清空数据
        $('#newPassword').val("");
        $('#confirmPassword').val("");
        return false;
    }
    else{
        if($("#newPassword").val().length<6){
            layer.alert('密码长度应不小于6位！', {icon: 2,title: '错误'});

            // 新密码和确认密码输入框清空数据
            $('#newPassword').val("");
            $('#confirmPassword').val("");
            $('#newPassword').focus();
            return false;
        }
        if($("#newPassword").val().length>20){
            layer.alert('密码长度应不大于20位！', {icon: 2,title: '错误'});
            // 新密码和确认密码输入框清空数据
            $('#newPassword').val("");
            $('#confirmPassword').val("");
            $('#newPassword').focus();
            return false;
        }
    }

    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/user/updateUserPwd.do",//url
        async : false,
        data: {
            oldPassword:$('#oldPassword').val(),
            newPassword:$('#newPassword').val(),
            confirmPassword:$('#confirmPassword').val()
        },
        success: function (result) {
            //console.log(data.old_password);//打印服务端返回的数据(调试用)
            if(result.state==1){
                //layer.alert('密码已修改，系统将自动注销，请重新登录！', {icon: 1});
                alert('密码已修改，系统将自动注销，请重新登录！');
                location.href="logout.do";
            }

            //用户身份验证失败
            if(result.state==2){
                // 显示错误信息
                layer.alert('用户身份验证失败，请重试！！', {icon: 2,title: '错误'});
                $('#newPassword').val("");
                $('#oldPassword').val("");
                $('#confirmPassword').val("");
                return false;
            }

        },
        fail : function() {
            alert("错误");
        }
    });
}

$("#updateUserPwdModal").on("hidden.bs.modal", function () {
    $('#newPassword').val("");
    $('#oldPassword').val("");
    $('#confirmPassword').val("");

});
