<%-- 
    Document   : coursewareDetail
    Created on : 27-Jun-2019, 21:50:34
    Author     : Tran Tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Thông tin học liệu</title>
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
                                <li class='active'><a href="#">Trang chủ</a></li>
                            </ol>
                            <h1>Quản lý học liệu</h1>
                        </div>
                        <div class="container">
                            <div class="panel panel-danger">
                                <div class="panel-heading">
                                    <h4>Quản lý học liệu</h4>
                                </div>
                                <div class="panel-body">
                                    <a onclick="history.go(-1)" class="btn btn-primary" style="margin-bottom: 10px; margin-right: 20px; width: 100px;"><span><i class="fa fa-arrow-left"></i></span> Quay lại</a>
                                    <a href="getAllCourseware.htm" class="btn btn-sky" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-list-ul"></i></span> Danh sách</a>
                                    <a href="initUpdateCourseware.htm?id=${ware.id}" class="btn btn-green" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-pencil-square-o"></i></span> Chỉnh sửa</a>
                                <a href="deleteCourseware.htm?id=${ware.id}" class="btn btn-brown" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-trash-o"></i></span> Xóa</a>
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
                                                <th>Tên học liệu</th>
                                                <td>${ware.name}</td>
                                            </tr>
                                            <tr>
                                                <th>Nội dung bài học</th>
                                                <td>${ware.description}</td>
                                            </tr>
                                            <tr>
                                                <th>Thuộc bài học</th>
                                                <c:forEach items="${listLession}" var="less">
                                                    <c:if test="${less.id == ware.lessionId}">
                                                        <td>${less.name}</td>
                                                    </c:if>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th>Loại học liệu</th>
                                                <c:forEach items="${listType}" var="typ">
                                                    <c:if test="${ware.type == typ.id}">
                                                        <td>${typ.typeName}</td>
                                                    </c:if>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th>File học liệu</th>
                                                <td>
                                                    ${ware.link}
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Ngày tạo - Người tạo</th>
                                                <td>${ware.createdAt} - <a href="initDetailUser.htm?id=${userCreated.id}" title="Xem chi tiết về ${userCreated.username}" > ${userCreated.username}</a></td>
                                            </tr>
                                            <tr>
                                                <th>Ngày sửa - Người sửa</th>
                                                <td>${ware.updatedAt} - <a href="initDetailUser.htm?id=${userUpdated.id}" title="Xem chi tiết về ${userUpdated.username}" > ${userUpdated.username}</a></td>
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
