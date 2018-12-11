<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="main-header">
    <!-- Logo -->
    <a href="/indexUI.do" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>Tea</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>Tea</b> Admin</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs">欢迎您,${currentUser.username}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                            <p>
                                ${currentUser.username}
                            </p>
                        </li>
                        <li class="user-footer">
                            <div class="pull-left">
                                <%--<a href="#" class="btn btn-default btn-flat">Profile</a>--%>
                            </div>
                            <div class="pull-right">
                                <button class="btn btn-default btn-flat" data-toggle="modal" data-target="#updateUserPwdModal">修改密码</button>
                                <a href="logout.do" class="btn btn-default btn-flat">Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>


<jsp:include page="/WEB-INF/pages/system/user_updatePasswordModal.jsp"/>
<script type="text/javascript" src="${basePath}/js/modules/system/user_updatePassword.js"></script>