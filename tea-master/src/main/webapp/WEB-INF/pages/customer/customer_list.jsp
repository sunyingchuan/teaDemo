<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group form-inline" style="border: 1px solid #ccc;padding: 10px; border-radius: 3px;">
                <div class="input-group input-group-sm">
                    <input id="username" type="text" placeholder="用户名">
                </div>
                <div class="input-group input-group-sm">
                    <input id="mobile" type="text" placeholder="手机号">
                </div>
                <div class="input-group input-group-sm">
                    <input id="email" type="text" placeholder="邮箱">
                </div>
                <div class="input-group input-group-sm">
                    <label for="sex">性别</label>
                    <select id="sex" style="padding: 3px; font-size: 15px; ">
                        <option value="">全部</option>
                        <option value="2">未选择</option>
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                </div>
                <div class="input-group input-group-sm">
                    <label >创建日期</label>
                    <input size="16" type="text" id="datetimeStart"  class="form_datetime">
                    --
                    <input size="16" type="text" id="datetimeEnd"  class="form_datetime">
                </div>
                <button id="btn_search" type="button" class="btn btn-primary btn-sm">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                </button>
            </div>
        </div>
    </div>

    <div id="toolbar" class="btn-group">
        <!-- 工具栏 -->
        <button id="btn_add" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
        <button id="btn_refresh" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
        </button>
        <button id="btn_add_point" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span>操作积分
        </button>
    </div>

    <!-- 表格 -->
    <table id="users" class="table table-hover"></table>

    <!-- 新增或修改弹框 -->
    <div class="modal fade" id="addAndUpdate" tabindex="-1" role="dialog" aria-labelledby="addAndUpdateLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="addAndUpdateLabel">新增用户信息</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="txt_type" class="form-control" id="txt_type" placeholder="操作类型">
                    <input type="hidden" name="txt_id" class="form-control" id="txt_id" placeholder="编号">
                    <div class="form-group">
                        <label for="txt_username">用户名</label>
                        <input type="text" name="txt_username" class="form-control" id="txt_username" placeholder="名称">
                    </div>
                    <div class="form-group">
                        <label for="txt_password">密码</label>
                        <input type="password" name="txt_password" class="form-control" id="txt_password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <label for="check_password">确认密码</label>
                        <input type="password" name="check_password" class="form-control" id="check_password" placeholder="确认密码">
                    </div>
                    <div class="form-group">
                        <label class="control-label">性别</label>
                        <div class="controls">
                            <input id="p_undefined" type="radio" name="sex" value="2" checked/>
                            <label for="p_man" style="margin-right: 30px;">保密</label>
                            <input id="p_man" type="radio" name="sex" value="1"/>
                            <label for="p_man" style="margin-right: 30px;">男</label>
                            <input id="p_woman" type="radio" name="sex" value="0"/>
                            <label for="p_woman">女</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="txt_email">邮箱</label>
                        <input type="text" name="txt_email" class="form-control" id="txt_email" placeholder="邮箱">
                    </div>
                    <div class="form-group">
                        <label for="txt_mobile">手机</label>
                        <input type="text" name="txt_mobile" class="form-control" id="txt_mobile" placeholder="手机">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="button" id="btn_add_update_submit" class="btn btn-primary btn-sm"
                            data-dismiss="modal"><span
                            class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- 查看账单 -->
    <div class="modal fade" id="balanceRecordDialog" tabindex="-1" role="dialog" aria-labelledby="balanceRecordLabel">
        <div class="modal-dialog" style="width: 60%;" role="document">
            <div class="modal-content">
                <table id="records" class="table table-hover"></table>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- 积分操作弹框 -->
    <div class="modal fade" id="add_point" tabindex="-1" role="dialog" aria-labelledby="addAndUpdateLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="pointAddLabel">新增积分记录</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="txt_customer_list">用户</label>
                        <input type="text" name="txt_customer_list" class="form-control" id="txt_customer_list" readonly placeholder="用户名称">
                    </div>
                    <div class="form-group">
                        <label for="txt_point">积分</label>
                        <input type="number" name="txt_point" class="form-control" id="txt_point" placeholder="积分">
                    </div>
                    <div class="form-group">
                        <label for="txt_description">描述</label>
                        <input type="text" name="txt_description" class="form-control" id="txt_description" placeholder="描述">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="button" id="btn_add_point_save" class="btn btn-primary btn-sm"
                            data-dismiss="modal"><span
                            class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/modules/customer/customer.list.js"></script>