<%-- 
    Document   : userDetail
    Created on : 15-Jun-2019, 22:39:27
    Author     : Tran Tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Thông tin người dùng</title>
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
                                <li class='active'><a href="#">Dashboard</a></li>
                            </ol>
                            <h1>Dashboard</h1>
                        </div>
                        <div class="container">
                            <div class="panel panel-danger">
                                <div class="panel-heading">
                                    <h4>Thông tin người dùng</h4>
                                </div>
                                <div class="panel-body">
                                    <a onclick="history.go(-1)" class="btn btn-primary" style="margin-bottom: 10px; margin-right: 20px; width: 100px;"><span><i class="fa fa-arrow-left"></i></span> Quay lại</a>
                                    <a href="getAllUser.htm" class="btn btn-sky" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-list-ul"></i></span> Danh sách</a>
                                    <c:if test="${sessionScope.roleId == 1 || (sessionScope.roleId == 2 && user.roleId == 3) }">
                                        <a href="initUpdateUser.htm?id=${user.id}" class="btn btn-green" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-pencil-square-o"></i></span> Chỉnh sửa</a>
                                        <c:if test="${sessionScope.id != user.id}">
                                            <a href="deleteUser.htm?id=${user.id}" class="btn btn-brown" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-trash-o"></i></span> Xóa</a>
                                        </c:if>
                                    </c:if>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>
                                                    Thông tin
                                                </th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th>Tên tài khoản</th>
                                                <td>${user.username}</td>
                                            </tr>
                                            <tr>
                                                <th>Họ tên</th>
                                                <td>${user.firstName} ${user.lastName}</td>
                                            </tr>
                                            <tr>
                                                <th>Tên hiển thị</th>
                                                <td><code>${user.displayName}</code></td>
                                            </tr>
                                            <tr>
                                                <th>Role</th>
                                                    <c:forEach items="${listRole}" var="role">
                                                        <c:if test="${role.id == user.roleId}">
                                                        <td>${role.roleName}</td>
                                                    </c:if>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th>Ngày sinh</th>
                                                <td>${user.birthday}</td>
                                            </tr>
                                            <tr>
                                                <th>Giới tính</th>
                                                    <c:if test="${user.sex == 1}">
                                                    <td>Nam</td>
                                                </c:if>
                                                <c:if test="${user.sex == 0}">
                                                    <td>Nữ</td>
                                                </c:if>
                                                <c:if test="${user.sex == 2}">
                                                    <td>Không xác định</td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <th>Email</th>
                                                <td>${user.email}</td>
                                            </tr>
                                            <tr>
                                                <th>SĐT</th>
                                                <td>${user.phone}</td>
                                            </tr>
                                            <tr>
                                                <th>Ngày tạo - Người tạo</th>
                                                <td>${user.createdDate} - <a href="initDetailUser.htm?id=${userCreated.id}" title="Xem chi tiết về ${userCreated.username}" > ${userCreated.username}</a></td>
                                            </tr>
                                            <tr>
                                                <th>Ngày sửa - Người sửa</th>
                                                <td>${user.updatedDate} - <a href="initDetailUser.htm?id=${userUpdated.id}" title="Xem chi tiết về ${userUpdated.username}" > ${userUpdated.username}</a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div> <!-- container -->
                </div> <!--wrap -->
            </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

        </div> <!-- page-container -->

    </body>
</html>
