<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <meta HTTP-EQUIV="Expires" CONTENT="0">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Tea Admin</title>

    <link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap-datepicker.min.css"/>
    <link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="${basePath}/bootstrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/dist/css/ionicons.min.css">
    <link rel="stylesheet" href="${basePath}/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${basePath}/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${basePath}/bootstrap/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${basePath}/bootstrap/plugins/treegrid/jquery.treegrid.css">
    <link type="text/css" rel="stylesheet" href="${basePath}/bootstrap/plugins/ztree/css/zTreeStyle/zTreeStyle.css" />
    <link type="text/css" rel="stylesheet" href="${basePath}/bootstrap/plugins/tree-column/bootstrap-table-tree-column.css" />

    <script src="${basePath}/js/jquery-3.2.1.min.js"></script>
    <script src="${basePath}/js/jquery-ui.min.js"></script>
    <script src="${basePath}/js/jquery.validate.min.js"></script>
    <script src="${basePath}/js/jquery.form.js"></script>
    <script src="${basePath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${basePath}/bootstrap/js/bootstrap-datepicker.min.js"></script>
    <script src="${basePath}/bootstrap/js/bootstrap-table.min.js"></script>
    <script src="${basePath}/bootstrap/plugins/layer/layer.js"></script>
    <script src="${basePath}/bootstrap/plugins/treegrid/jquery.treegrid.min.js"></script>
    <script src="${basePath}/bootstrap/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
    <script src="${basePath}/bootstrap/plugins/treegrid/jquery.treegrid.extension.js"></script>
    <script src="${basePath}/bootstrap/plugins/treegrid/tree.table.js"></script>
    <script src="${basePath}/bootstrap/plugins/ztree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="${basePath}/bootstrap/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript" src="${basePath}/bootstrap/plugins/tree-column/bootstrap-table-tree-column.js"></script>

    <!-- AdminLTE App -->
    <script src="${basePath}/dist/js/app.min.js"></script>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
<div class="wrapper">
    <%@include file="header.jsp" %>
    <%@include file="left.jsp" %>
    <div class="content-wrapper">

        <section class="content">

        </section>
        <!-- /.content -->
    </div>
</div>

<!-- Modal(模态框) -->
<div class="modal fade" id="modal-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <!-- 此位置放置具体页面的位置 -->
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary ok">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- modal -->
</body>
</html>





