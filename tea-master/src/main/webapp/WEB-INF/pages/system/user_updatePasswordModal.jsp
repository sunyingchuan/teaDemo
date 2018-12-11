
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 2018/10/9
  Time: 10:24
--%>


<!-- 修改密码模态框（Modal） -->
<div class="modal fade" id="updateUserPwdModal" tabindex="-1" role="dialog" aria-labelledby="updateUserPwdModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="updateUserPwdModalLabel">
                    修改密码
                </h4>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="oldPassword">原密码</label>
                    <input type="password" name="oldPassword" class="form-control" id="oldPassword" placeholder="原密码">
                </div>

                <div class="form-group">
                    <label for="newPassword">新密码</label>
                    <input type="password" name="newPassword" class="form-control" id="newPassword" placeholder="新密码">
                </div>

                <div class="form-group">
                    <label for="confirmPassword">确认密码</label>
                    <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="确认密码">
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" id="confirmUpdateUserPwd">
                    <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确认更改
                </button>
            </div>
        </div>
    </div>
</div>



