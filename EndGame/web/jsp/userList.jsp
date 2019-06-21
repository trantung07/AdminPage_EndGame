<%-- 
    Document   : userList
    Created on : 08-Jun-2019, 10:25:42
    Author     : Tran Tung
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <title>Danh sách người dùng</title>
    <jsp:include page="library.jsp"></jsp:include>
        <body class="">
        <jsp:include page="header.jsp"></jsp:include>
            <div id="page-container">
                <!-- BEGIN SIDEBAR -->
            <jsp:include page="slideBar.jsp"></jsp:include>    
                <!-- END SIDEBAR MENU -->
                <div id="page-content">
                    <div id='wrap'>
                        <div id="page-heading">
                            <ol class="breadcrumb">
                                <li class='active'><a href="#">Quản lý người dùng</a></li>
                            </ol>
                            <h1>Danh sách người dùng</h1>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="panel panel-sky">
                                        <div class="panel-heading">
                                            <h4>Danh sách</h4>

                                        </div>
                                        <div class="panel-body">
                                            <div class="table-flipscroll">
                                                <a href="initInsertUser.htm" class="btn btn-success" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-plus-circle"></i></span> Thêm mới</a>
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th style="padding-right:60px">Họ tên</th>
                                                            <th>Tài khoản</th>
                                                            <th>Tên hiển thị</th>
                                                            <th>Role</th>
                                                            <th>Ngày sinh</th>
                                                            <th>Giới tính</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${userList}" var="user">
                                                        <tr>
                                                            <td align="left"><i>${user.firstName} ${user.lastName}</i></td>
                                                            <td>${user.username}</td>
                                                            <td>${user.displayName}</td>
                                                            <c:forEach items="${roleList}" var="role">
                                                                <c:if test="${role.id == user.roleId}">
                                                                    <td>${role.roleName}</td>
                                                                </c:if>
                                                            </c:forEach>

                                                            <td>${user.birthday}</td>

                                                            <c:if test="${user.sex == 1}">
                                                                <td>Nam</td>
                                                            </c:if>
                                                            <c:if test="${user.sex == 0}">
                                                                <td>Nữ</td>
                                                            </c:if>
                                                            <c:if test="${user.sex == 2}">
                                                                <td>Không xác định</td>
                                                            </c:if>
                                                            <td>
                                                                <div class="">

                                                                    <a title="Xem chi tiết về ${user.lastName}" class="blue" href="initDetailUser.htm?id=${user.id}">
                                                                        <i class=" fa fa-search-plus bigger-250"></i>
                                                                    </a>

                                                                    <c:if test="${sessionScope.roleId == 1 || (sessionScope.roleId == 2 && user.roleId == 3) }">
                                                                        <a title="Chỉnh sửa" class="green" style="padding-left: 10px" href="initUpdateUser.htm?id=${user.id}">
                                                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                                        </a>
                                                                        <c:if test="${sessionScope.id != user.id}">
                                                                            <a title="Xóa" id="delBtn" class="red" style="padding-left: 10px" href="deleteUser.htm?id=${user.id}" >
                                                                            <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                                                        </a>
                                                                        </c:if>
                                                                    </c:if>
                                                                </div>

                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> <!-- container -->
                    <div class="modal visiblemodal" id="openModal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                    <p>One fine body…</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" id="confirmBtn" class="btn btn-primary">Save changes</button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                </div> <!--wrap -->
            </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

            </div> <!-- page-container -->
            <script type="text/javascript">
                //                onclick="$('#openModal').show();"
                $(document).ready(function () {
                    $("#confirmBtn").submit(function (event) {
                        //stop submit the form, we will post it manually.
                        event.preventDefault();
                        fire_ajax_submit();
                    });
                });

                function fire_ajax_submit() {

                    var search = {}
                    search["username"] = $("#username").val();

                    $("#btn-search").prop("disabled", true);

                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "/deleteUser.htm?id=${user.id}",
                        data: JSON.stringify(search),
                        dataType: 'json',
                        cache: false,
                        timeout: 600000,
                        success: function (data) {

                            var json = "<h4>Ajax Response</h4><pre>"
                                    + JSON.stringify(data, null, 4) + "</pre>";
                            $('#feedback').html(json);

                            console.log("SUCCESS : ", data);
                            $("#btn-search").prop("disabled", false);

                        },
                        error: function (e) {

                            var json = "<h4>Ajax Response</h4><pre>"
                                    + e.responseText + "</pre>";
                            $('#feedback').html(json);

                            console.log("ERROR : ", e);
                            $("#btn-search").prop("disabled", false);

                        }
                    });

                }
        </script>
    </body>
</html>
