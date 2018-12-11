<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<style>
    .table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
        vertical-align: middle;
    }
</style>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group form-inline" style="border: 1px solid #ccc;padding: 10px; border-radius: 3px;">
                <div class="input-group input-group-sm">
                    <label for="id">商品ID：</label>
                    <input id="id" type="text">
                </div>
                <div class="input-group input-group-sm">
                    <label for="goodsName">商品名称：</label>
                    <input id="goodsName" type="text">
                </div>
                <div class="input-group input-group-sm">
                    <label for="priceStart">价格区间：</label>
                    <label for="priceEnd"></label>
                    <input id="priceStart" type="text" style="width:120px;">-<input id="priceEnd" type="text" style="width:120px;">
                </div>
                <div class="input-group input-group-sm">
                    <label for="status">状态</label>
                    <select id="status">
                        <option value="">全部</option>
                        <option value="1">已上架</option>
                        <option value="0">已下架</option>
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
        <button id="btn_add" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增
        </button>
        <button id="btn_status_1" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>上架
        </button>
        <button id="btn_status_0" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>下架
        </button>
        <button id="btn_refresh" type="button" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
        </button>
    </div>

    <!-- 表格 -->
    <table id="goods" class="table table-hover"></table>

    <!-- 新增或修改弹框 -->
    <div class="modal fade" id="addAndUpdate" tabindex="-1" role="dialog" aria-labelledby="addAndUpdateLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="addAndUpdateLabel">新增商品信息</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="txt_type" class="form-control" id="txt_type" placeholder="操作类型">
                    <input type="hidden" name="txt_id" class="form-control" id="txt_id" placeholder="编号">
                    <div class="form-group">
                        <label for="txt_goodsName">商品名称</label>
                        <input type="text" name="txt_goodsName" class="form-control" id="txt_goodsName" placeholder="请输入商品名称">
                    </div>
                    <div class="form-group">
                        <label for="txt_category">商品类别</label><br>
                        <select id="txt_category" class="form-control">
                    <%--        <option value="" selected>=========请选择========</option>
                            <option value="txt_categoryId"><span value="txt_categoryName"></span></option>--%>
                        </select>
                    </div>
                   <%-- <div class="form-group">
                        <form method="post"  enctype="multipart/form-data">
                        <label class="control-label" for="txt_picUrl" >上传图片</label>
                        <input id="txt_picUrl" name="txt_picUrl" class="file" type="file" data-show-preview="false">
                        </form>
                    </div>--%>
                    <div class="form-group" style="display:inline-block;">
                        <label for="txt_picUrl">上传图片</label><br>
                        <input type="text" name="txt_picUrl" id="txt_picUrl" placeholder="   请输入以http://开头的网络图片URL"style="height: 34px;width: 300px;">
                        <input type="button" onclick="checkImgType()" class="btn btn-info" value="检测" style="width: 100px;"><span style="color: #9e9e9e">&nbsp;&nbsp;或&nbsp;&nbsp; </span>
                        <input type="button" class="btn btn-primary" value="本地上传" style="width: 100px;"><br>
                        <span style="color: #A4B0BC">图片文件类型支持PNG、JPG、JPEG，图片大小不超过2M</span>
                    </div>
                    <div class="form-group">
                        <label class="control-label">状态</label>
                        <div class="controls">
                            <input id="p_dowm" type="radio" name="status" value="0" checked/>
                            <label for="p_dowm" style="margin-right: 30px;">下架</label>
                            <input id="p_up" type="radio" name="status" value="1"/>
                            <label for="p_up">上架</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="txt_price">价格</label>
                        <input type="text" name="txt_price" class="form-control" id="txt_price" placeholder="价格">
                    </div>
                    <div class="form-group">
                        <label for="txt_stock">库存</label>
                        <input type="text" name="txt_stock" class="form-control" id="txt_stock" placeholder="库存">
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

<script type="text/javascript" src="${basePath}/js/modules/goods/goods.list.js"></script>
<script type="text/javascript" src="${basePath}/bootstrap/js/fileinput.min.js"></script>
<script type="text/javascript" src="${basePath}/bootstrap/css/fileinput.min.css"></script>
<script type="text/javascript" src="${basePath}/bootstrap/js/zh.js"></script>

<script>
  /*  $('#txt_picUrl').fileinput({
        theme: 'fa',
        language: 'zh',
        uploadAsync: true,//异步上传
        uploadUrl: 'goods/upload.do',
        allowedFileExtensions: ['jpg', 'png', 'gif','mp4'],
        maxFileSize:0,
        maxFileCount:10
    }).on("fileuploaded", function(event,data) { //异步上传成功结果处理
        alert(data.response.src);
        $("#txt_picUrl").val(data.response.src);
    })*/

  function checkImgType() {
      var ths=$("#txt_picUrl").val();
      if (ths == "") {
          layer.msg("请上传图片", {icon: 2, time: 1500});
          return false;
      }
      if(!(ths.indexOf("http://")==0||ths.indexOf("https://")==0)){
          layer.msg("请上传以http://或https://开头的图片", {icon: 2, time: 1500});
          return false;
      }
      else {
          if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(ths)) {
              layer.msg("图片类型必须是.gif,jpeg,jpg,png中的一种", {icon: 2, time: 1500});
              this.value="";
              return false;
          }
      }
      layer.msg("上传图片格式正确", {icon: 1, time: 1500});
      return true;
  }

</script>

