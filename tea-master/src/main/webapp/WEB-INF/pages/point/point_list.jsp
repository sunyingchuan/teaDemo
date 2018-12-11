<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group form-inline" style="border: 1px solid #ccc;padding: 10px; border-radius: 3px;">
                <%--<div class="input-group input-group-sm">--%>
                    <%--<label for="customerId">客户id</label>--%>
                    <%--<input id="customerId" type="text">--%>
                <%--</div>--%>
                <div class="input-group input-group-sm">
                    <label for="username">客户名称</label>
                    <input id="username" type="text">
                </div>
                <div class="input-group input-group-sm">
                    <label for="orderNumber">订单编号</label>
                    <input id="orderNumber" type="text">
                </div>
                <%--<div class="input-group input-group-sm">--%>
                    <%--<label for="point">积分</label>--%>
                    <%--<input id="point" type="text">--%>
                <%--</div>--%>
                <div class="input-group input-group-sm">
                    <label for="datetimeStart">开始时间</label>
                    <input size="16" type="text" id="datetimeStart" class="form_datetime">
                    --
                    <label for="datetimeEnd">结束时间</label>
                    <input size="16" type="text" id="datetimeEnd" class="form_datetime">
                </div>

                <button id="btn_search" type="button" class="btn btn-primary btn-sm">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                </button>
                <button id="btn_clean_search" type="button" class="btn btn-danger btn-sm">清空条件</button>
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
    </div>

    <!-- 表格 -->
    <table id="points" class="table table-hover"></table>

    <!-- 新增或修改弹框 -->
    <div class="modal fade" id="addAndUpdate" tabindex="-1" role="dialog" aria-labelledby="addAndUpdateLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="addAndUpdateLabel">新增积分记录</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="txt_id" class="form-control" id="txt_id" placeholder="编号">
                    <div class="form-group">
                        <label for="txt_customer_id">客户id</label>
                        <input type="text" name="txt_customer_id" class="form-control" id="txt_customer_id" placeholder="客户id">
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
                    <button type="button" id="btn_add_update_submit" class="btn btn-primary btn-sm"
                            data-dismiss="modal"><span
                            class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/modules/point/point.list.js">
</script>
