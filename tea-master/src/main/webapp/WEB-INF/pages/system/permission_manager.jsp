<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="meta_Data.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<input id="PageContext" type="hidden" value="${pageContext.request.contextPath}"/>

<div style="margin-left: auto;margin-right: auto;width: 95%">
    <div class="row">
        <div class="col-xs-12">
            <div class="row">
                <div class="col-xs-12">
                    <div id="permission_toolbar" class="btn-group">
                        <shiro:hasPermission name="permission:add">
                            <button id="btn_permission_add" type="button" class="btn btn-default btn-sm">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="permission:update">
                            <button id="btn_permission_delete" type="button" class="btn btn-default btn-sm">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="permission:delete">
                            <button id="btn_permission_update" type="button" class="btn btn-default btn-sm">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                            </button>
                        </shiro:hasPermission>
                        <button id="btn_permission_refresh" type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
                        </button>
                    </div>
                    <table id="permission_list_id"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<%-----新增模态框----------%>
<div class="modal fade" id="permission_modal_add" tabindex="-1" permission="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    权限设置
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" permission="form" method="post" id="permission_insert_form"
                      action="${pageContext.request.contextPath}/" enctype="application/json">
                    <input type="hidden" id="permission_id" name="id" value=""/>
                    <div class="form-group">
                        <label for="permissionName" class="col-sm-2 control-label">权限名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="permissionName" name="name"
                                   placeholder="请输入权限名称" required>
                            <span id="permissionName.info" style="color:red"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="add_pid" class="col-sm-2 control-label">父级名称</label>
                        <div class="col-sm-6">
                            <select class="col-xs-10 col-sm-8" name="pid" id="add_pid">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_permission_type" class="col-sm-2 control-label">权限类型</label>
                        <div class="col-sm-6">
                            <select class="col-xs-10 col-sm-8 permission_type_selects" name="type" id="add_permission_type">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="permissionURL" class="col-sm-2 control-label">URL</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="permissionURL" name="url"
                                   placeholder="请输入URL" required>
                            <span id="permissionURL.info" style="color:red"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="permissionCode" class="col-sm-2 control-label">权限代码</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="permissionCode" name="code"
                                   placeholder="请输入权限代码" required>
                            <span id="permissionCode.info" style="color:red"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="btn btn-primary" id="btn_save_new_permission">保存</button>
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


<script type="text/javascript" src="${pageContext.request.contextPath}/js/modules/system/permission_manager.js"></script>
