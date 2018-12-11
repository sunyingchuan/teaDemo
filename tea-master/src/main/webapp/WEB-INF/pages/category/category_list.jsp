<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
</div>
<div>
    <a href="" class="btn btn-danger" data-toggle="modal" data-target="#deleteP"><i class="glyphicon glyphicon-trash" ></i> 删除所选分类</a>
    <a href="" class="btn btn-info" data-toggle="modal" data-target="#addSon"><i
            class="glyphicon glyphicon-plus"></i> 添加子级分类</a>
    <a href="" class="btn btn-info" data-toggle="modal" data-target="#addParent"><i
            class="glyphicon glyphicon-plus"></i> 添加根节点分类</a>
</div>
<table class="table" id="category">
    <td style="padding-left: 4vw;" width="350px">
        <div id="tree"></div>
    </td>
    <td>
        <div class="page-container">
            <form class="form-horizontal" role="form">
                <input type="hidden" name="txt_id" class="form-control" id="txt_id" placeholder="编号">
                <input type="hidden" name="txt_type" class="form-control" id="txt_type" placeholder="操作类型">
                <div class="form-group">
                    <label for="categoryName" class="col-sm-2 control-label"><span style="color: red">*</span>分类名称：</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="categoryName" name="categoryName" placeholder="请输入商品分类名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="txt_category" class="col-sm-2 control-label"><span
                            style="color: red">*</span>选择父节点：</label>
                    <div class="col-sm-3">
                        <select id="txt_category" class="form-control">
                            <%--        <option value="" selected>=========请选择========</option>
                                    <option value="txt_categoryId"><span value="txt_categoryName"></span></option>--%>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                <label for="sort" class="col-sm-2 control-label"><span style="color: red">*</span>排序优先值：</label>
                <div class="col-sm-8">
                    <input type="text" class="col-sm-5 form-control" id="sort"
                           placeholder="请输入0~999，值越小排序越前">
                </div>
            </div>

                <div class="form-group">
                    <label for="sort" class="col-sm-2 control-label"><span style="color: red">*</span>编号：</label>
                    <div class="col-sm-8">
                        <input type="text" class="col-sm-5 form-control" id="code"
                               placeholder="请输入01、02...">
                    </div>
                </div>

                <div class="form-group">
                    <label for="sort" class="col-sm-2 control-label"></label>
                    <div class="col-sm-8">
                        <input id="btn_add_update_submit" class="btn btn-primary" type="button" value="&nbsp;&nbsp;提交修改&nbsp;&nbsp;">
                    </div>
                </div>

            </form>
        </div>
    </td>
</table>

<!-- 模态框（Modal） -->
<div class="modal fade" id="addSon" tabindex="-1" role="dialog" aria-labelledby="myAddSon" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myAddSon">添加子级分类</h4>
            </div>
            <div class="modal-body">
                <div class="page-container">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="s_categoryName"><span
                                    style="color: red">*</span>分类名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="s_categoryName" name="s_categoryName" placeholder="请输入商品分类名称">
                            </div>

                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label"><span
                                    style="color: red">*</span>选择父节点：</label>
                            <div class="col-sm-6">
                                <select id="txt_s_category" class="form-control">

                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="s_sort" class="col-sm-3 control-label"><span style="color: red">*</span>排序优先值：</label>
                            <div class="col-sm-8">
                                <input type="text" class="col-sm-5 form-control" id="s_sort"
                                       placeholder="请输入0~999，值越大排序越前">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="s_code" class="col-sm-3 control-label"><span style="color: red">*</span>编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="col-sm-5 form-control" name="s_code" id="s_code"
                                       placeholder="请输入01、02...">
                            </div>
                        </div>


                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="btn_add_submit" class="btn btn-primary">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>


<div class="modal fade" id="addParent" tabindex="-1" role="dialog" aria-labelledby="myAddParent" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myAddParent">添加根分类</h4>
            </div>
            <div class="modal-body">
                <div class="page-container">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label  class="col-sm-3 control-label" for="p_categoryName"><span
                                    style="color: red">*</span>分类名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="p_categoryName" name="p_categoryName">
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="p_sort" class="col-sm-3 control-label" ><span style="color: red">*</span>排序优先值：</label>
                            <div class="col-sm-8">
                                <input type="text" class="col-sm-5 form-control" id="p_sort"
                                       placeholder="请输入0~999，值越大排序越前">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="p_code" class="col-sm-3 control-label"><span style="color: red">*</span>编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="col-sm-5 form-control" name="p_code" id="p_code"
                                       placeholder="请输入01、02...">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="btn_add_parent_submit" class="btn btn-primary">提交更改</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>

<div class="modal fade" id="deleteP" tabindex="-1" role="dialog" aria-labelledby="deleteP" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    操作提示
                </h4>
            </div>
            <div class="modal-body">
                确认要删除所选的数据吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" class="btn btn-primary" id="btn_del_parent_submit">
                    确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

    <script type="text/javascript" src="${basePath}/js/modules/category/category.list.js"></script>
    <script src="${basePath}/bootstrap/js/bootstrap-treeview.js"></script>
