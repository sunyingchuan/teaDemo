<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>项目管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a id="load-customer-id"><i class="fa fa-circle-o"></i>客户管理</a></li>
                    <li><a id="load-order-id"><i class="fa fa-circle-o"></i>订单管理</a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>系统管理</span>
                    <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                </a>
                <ul class="treeview-menu">
                    <shiro:hasPermission name="user:listUI">
                        <li><a id="sys_user_manager_id"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="role:listUI">
                        <li><a id="sys_role_manager_id"><i class="glyphicon glyphicon-star"></i>角色管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="permission:listUI">
                        <li><a id="sys_permissino_manager_id"><i class="glyphicon glyphicon-cog"></i>权限管理</a></li>
                    </shiro:hasPermission>
                </ul>
            </li>
            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>积分管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a id="load-point-id"><i class="fa fa-circle-o"></i>积分管理</a></li>
                    <li><a id="load-balance-id"><i class="fa fa-circle-o"></i>账户明细</a></li>
                </ul>
            </li>

            <li class="active treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>商品管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a id="load-goods-category-id"><i class="glyphicon glyphicon-user"></i>分类管理</a></li>
                    <li><a id="load-goods-id"><i class="glyphicon glyphicon-user"></i>商品列表</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
<script type="text/javascript">
    $('#load-project-id').click(function () {
        var url = "project/list.do?t=" + Math.random(1000);
        $(".content").load(url);
    });

    $('#load-customer-id').click(function () {
        var url = "customer/list.do?t=" + Math.random(1000);
        $(".content").load(url);
    });

    $('#load-order-id').click(function () {
        var url = "order/list.do?t=" + Math.random(1000);
        $(".content").load(url);
    });

    $('#sys_user_manager_id').click(function () {
        var url = "user/list.do?t=" + Math.random(1000);
        $(".content").load(url);
    });

    $('#sys_role_manager_id').click(function () {
        console.log("sys_role_manager");
        var url = "role/listUI.do?t=" + Math.random(1000);
        $(".content").load(url);
    });

    $('#sys_permissino_manager_id').click(function () {
        console.log("sys_permissino_manager");
        var url = "permission/listUI.do?t=" + Math.random(1000);
        $(".content").load(url);
    });

    $('#load-point-id').click(function () {
        var url = "point/list.do?t=" + Math.random(1000);
        $(".content").load(url);
    });

    $('#load-balance-id').click(function () {
        var url = "balance/list.do?t=" + Math.random(1000);
        $(".content").load(url);
    });

    $('#load-goods-category-id').click(function () {
        var url = "category/index.do?t=" + Math.random(1000);
        $(".content").load(url);
    })

    $('#load-goods-id').click(function () {
        var url = "goods/index.do?t=" + Math.random(1000);
        $(".content").load(url);
    })
</script>