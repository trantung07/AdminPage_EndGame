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
                            <div class="options">
                                <div class="btn-toolbar">
                                    <div class="btn-group hidden-xs">
                                        <a href='#' class="btn btn-default dropdown-toggle" data-toggle='dropdown'><i class="fa fa-cloud-download"></i><span class="hidden-xs hidden-sm hidden-md"> Export as</span> <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Text File (*.txt)</a></li>
                                            <li><a href="#">Excel File (*.xlsx)</a></li>
                                            <li><a href="#">PDF File (*.pdf)</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
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
                                                                    <a title="Xem chi tiết về ${user.lastName}" class="blue" href="#">
                                                                        <i class=" fa fa-search-plus bigger-250"></i>
                                                                    </a>
                                                                    

                                                                    <a title="Chỉnh sửa" class="green" style="padding-left: 10px" href="initUpdateUser.htm?id=${user.id}">
                                                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                                    </a>

                                                                    <a title="Xóa" class="red" style="padding-left: 10px" href="deleteUser.htm?id=${user.id}">
                                                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                                                    </a>
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
                </div> <!--wrap -->
            </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

        </div> <!-- page-container -->
    </body>
</html>
