<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<HTML>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Tea Admin</title>
    <link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/bootstrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${basePath}/bootstrap/css/all-skins.min.css">
    <link rel="stylesheet" href="${basePath}/bootstrap/css/main.css">
    <script src="${basePath}/js/jquery-3.2.1.min.js"></script>
    <script src="${basePath}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="hold-transition login-page">
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal">
                <p class="login-box-msg"><b>用户登录</b></p>
                <div class="alert alert-danger alert-dismissible" style="display:none">
                    <h4 style="margin-bottom: 0px;"><i class="fa fa-exclamation-triangle" id="errorMessage"></i></h4>
                </div>
                <div class="form-group has-feedback">
                    <input type="text" class="form-control" id="username" placeholder="账号" value="admin">
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input type="password" class="form-control" id="userpwd" placeholder="密码" value="123456">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="row">
                    <div>
                        <button type="button" class="btn btn-primary btn-block btn-flat" id="btn-login">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</HTML>

<script type="text/javascript">
    $(document).ready(function () {
        //回车按钮
        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                //触发btn-login绑定的submit事件
                $("#btn-login").trigger("click");
            }
        });
        //点击登录按钮
        $('#btn-login').click(doLogin);
    })

    function doLogin() {
        var userName = $('#username').val();
        var userPwd = $('#userpwd').val();
        if (userName == '') {
            $('#errorMessage').parent().parent().css('display', 'block');
            $('#errorMessage').text('用户名不能为空！');
            $('#username').focus();
            return false;
        }
        if (userPwd == '') {
            $('#errorMessage').parent().parent().css('display', 'block');
            $('#errorMessage').text('密码不能为空！');
            $('#userpwd').focus();
            return false;
        }
        //判断此用户是否存在于数据库中
        var url = 'login.do';
        var params = {'username': userName, 'password': userPwd};
        $.post(url, params, function (result) {
            if (result.state == 1) {   //用户校验成功，跳转到主页面
                location.href = 'index.do';
            } else {
                $('#errorMessage').parent().parent().css('display', 'block');
                $('#errorMessage').text(result.message);
                if(result.message=="认证失败"){
                    $('#username').focus();
                }
                else{
                    $('#userpwd').focus();
                }
            }
        })
    }
</script>
