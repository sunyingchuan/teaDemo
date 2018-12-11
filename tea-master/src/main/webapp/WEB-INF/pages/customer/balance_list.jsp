<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group form-inline" style="border: 1px solid #ccc;padding: 10px; border-radius: 3px;">
                <div class="input-group input-group-sm">
                    <input id="customerId" type="text" placeholder="用户ID">
                </div>
                <div class="input-group input-group-sm">
                    <input id="username" type="text" placeholder="用户名">
                </div>
                <div class="input-group input-group-sm">
                    <label for="recordType">类型</label>
                    <select id="recordType" style="padding: 3px; font-size: 15px; ">
                        <option value="">全部</option>
                        <option value="1">充值</option>
                        <option value="2">消费</option>
                        <option value="3">退款</option>
                    </select>
                </div>
                <div class="input-group input-group-sm">
                    <label >创建日期</label>
                    <input size="16" type="text" id="datetimeStart" class="form_datetime">
                    --
                    <input size="16" type="text" id="datetimeEnd" class="form_datetime">
                </div>
                <button id="btn_search" type="button" class="btn btn-primary btn-sm">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                </button>
            </div>
        </div>
    </div>

    <div id="toolbar" class="btn-group">
        <!-- 工具栏 -->
        <button id="btn_refresh" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
        </button>
    </div>

    <!-- 表格 -->
    <table id="balanceRecords" class="table table-hover"></table>

</div>

<script type="text/javascript" src="${basePath}/js/modules/balance/balance.list.js"></script>