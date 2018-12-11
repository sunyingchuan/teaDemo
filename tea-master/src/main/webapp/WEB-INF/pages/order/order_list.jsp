<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group form-inline" style="border: 1px solid #ccc;padding: 10px; border-radius: 3px;">
                <div class="input-group input-group-sm">
                    <input id="orderNumber" type="text" placeholder="订单号">
                </div>
                <div class="input-group input-group-sm">
                    <input id="userName" type="text" placeholder="用户名">
                </div>
                <%--<div class="input-group input-group-sm">--%>
                    <%--<input id="goodsName" type="text" placeholder="商品名">--%>
                <%--</div>--%>
                <div class="input-group input-group-sm">
                    <label for="orderStuas">订单状态</label>
                    <select id="orderStuas" style="padding: 3px; font-size: 15px; ">
                        <option value="">全部</option>
                        <option value="0">未支付</option>
                        <option value="1">待发货</option>
                        <option value="2">待收货</option>
                        <option value="3">订单完成</option>
                        <option value="4">申请取消订单</option>
                        <option value="5">待退款</option>
                        <option value="6">已取消订单</option>
                    </select>
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
        <button id="btn_refresh" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
        </button>
    </div>

    <!-- 表格 -->
    <table id="orders" class="table table-hover"></table>

    <!-- 订单管理 -->
    <div class="modal fade" id="balanceRecordDialog" tabindex="-1" role="dialog" aria-labelledby="balanceRecordLabel">
        <div class="modal-dialog" style="width: 80%;height: 40%" role="document">
            <div class="modal-content">
                <table id="records" class="table table-hover"></table>
                <div class="modal-footer text-center">
                    <button id="btn_ship" type="button" class="btn btn-warning" data-dismiss="modal"><span
                            class="glyphicon glyphicon-send" aria-hidden="true"></span>发货
                    </button>
                    <button id="btn_accept" type="button" class="btn btn-success" data-dismiss="modal"><span
                            class="glyphicon glyphicon-ok" aria-hidden="true"></span>同意退单
                    </button>
                    <button id="btn_refuse" type="button" class="btn btn-danger" data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"></span>拒绝退单
                    </button>
                    <%--<button id="btn_update" type="button" class="btn btn-danger" data-dismiss="modal"><span--%>
                            <%--class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改订单--%>
                    <%--</button>--%>
                    <button id="btn_refund" type="button" class="btn btn-danger" data-dismiss="modal"><span
                            class="glyphicon glyphicon-ok" aria-hidden="true"></span>订单退款
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- 订单记录 -->
    <div class="modal fade" id="orderRecordDialog" tabindex="-1" role="dialog" aria-labelledby="orderRecordLabel">
        <div class="modal-dialog" style="width: 60%;" role="document">
            <div class="modal-content">
                <table id="orderRecords" class="table table-hover"></table>
                <div class="modal-footer text-center">
                    <button type="button" class="btn btn-primary" data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
</script>
<script type="text/javascript" src="${basePath}/js/modules/order/order.list.js"></script>