<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="meta_Data.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}"/>

<div class="right_container">
    <%--搜索区域--%>
    <div class="role_seacher_area">
        <div class="row-fluid">
            <div class="span12">
                <div class="control-group form-inline"
                     style="border: 1px solid #ccc;padding: 10px; border-radius: 3px;">
                    <div class="input-group input-group-sm">
                        <label for="search_roleName">角色名称</label>
                        <input id="search_roleName" name="search_roleName" type="text">
                    </div>
                    <div class="input-group input-group-sm">
                        <label for="search_role_desc">描述</label>
                        <input id="search_role_desc" name="search_role_desc" type="text">
                    </div>
                    <button id="btn_role_search" type="button" class="btn btn-primary btn-sm">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                    </button>
                    <button id="btn_role_clean_search" type="button" class="btn btn-danger btn-sm">清空条件</button>
                </div>
            </div>
        </div>

        <div id="role_toolbar" class="btn-group">
            <!-- 工具栏 -->
            <shiro:hasPermission name="role:setPermission">
                <button id="btn_role_setPermission" type="button" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>设置权限
                </button>
            </shiro:hasPermission>
            <shiro:hasPermission name="role:add">
                <button id="btn_role_add" type="button" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
            </shiro:hasPermission>
            <shiro:hasPermission name="role:delete">
                <button id="btn_role_delete" type="button" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                </button>
            </shiro:hasPermission>
            <shiro:hasPermission name="role:update">
                <button id="btn_role_update" type="button" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasPermission>
            <button id="btn_role_refresh" type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
            </button>
        </div>
        <%--表格--%>
        <div>
            <table id="tab_role_list"></table>
        </div>
        <%--新增模态框--%>
        <!-- 模态框（Modal）新增 -->
        <div class="modal fade" id="role_modal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            添加角色
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" method="post" id="role_insert_form"
                              action="${pageContext.request.contextPath}/" enctype="application/json">

                            <div class="form-group">
                                <label for="roleName" class="col-sm-2 control-label">角色名</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="roleName" name="name"
                                           placeholder="请输入角色名" required>
                                    <span id="roleName.info" style="color:red"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="description" class="col-sm-2 control-label">角色描述</label>
                                <div class="col-sm-6">
                                    <textarea  name="description" type="text" class="form-control" id="description"
                                            placeholder="请输入描述" required />
                                    <span id="description.info" style="color:red"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" class="btn btn-primary" id="btn_save_new_role">新增</button>
                                    <button type="button" class="btn btn-default" id="closeModal" data-dismiss="modal">取消
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div><!-- /.-content -->
            </div><!-- /.modal -->
        </div>


        <!-- 模态框（Modal）修改 -->
        <div class="modal fade" id="role_modal_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabe2" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabe2">
                            修改角色
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" method="post" id="role_update_form"
                              action="${pageContext.request.contextPath}/" enctype="application/json">

                            <input type="hidden" id="update_id" name="id" value=""/>
                            <div class="form-group">
                                <label for="roleName" class="col-sm-2 control-label">角色名</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="update_name" name="name"
                                           placeholder="请输入角色名" required>
                                    <span id="role_update_name.info" style="color:red"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="description" class="col-sm-2 control-label">角色描述</label>
                                <div class="col-sm-6">
                                    <textarea  name="description" type="text" class="form-control" id="update_description"
                                               placeholder="请输入描述" required />
                                    <span id="update_description.info" style="color:red"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" class="btn btn-primary" id="btn_update_role">修改</button>
                                    <button type="button" class="btn btn-default" id="closeModa2" data-dismiss="modal">取消
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div><!-- /.-content -->
            </div><!-- /.modal -->
        </div>

        <div id="permissionUI" class="col-xs-12 col-sm-12" style="display: none">
            <label for="form-field-select-2">选择权限</label>
            <ul id="permissionTree" class="ztree" style="width:100%; overflow:hidden;"></ul>
        </div>

    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/modules/system/role_manager.js"></script>











