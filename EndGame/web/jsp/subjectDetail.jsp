<%-- 
    Document   : subjectDetail
    Created on : Jun 25, 2019, 1:17:51 AM
    Author     : hung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Thông tin môn học</title>
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
                            <h1>Quản lý môn học</h1>
                        </div>
                        <div class="container">
                            <div class="panel panel-danger">
                                <div class="panel-heading">
                                    <h4>Thông tin môn học</h4>
                                </div>
                                <div class="panel-body">
                                    <a onclick="history.go(-1)" class="btn btn-primary" style="margin-bottom: 10px; margin-right: 20px; width: 100px;"><span><i class="fa fa-arrow-left"></i></span> Quay lại</a>
                                    <a href="getAllSubject.htm" class="btn btn-sky" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-list-ul"></i></span> Danh sách</a>
                                    <a href="initUpdateSubject.htm?id=${subject.id}" class="btn btn-green" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-pencil-square-o"></i></span> Chỉnh sửa</a>
                                    <a href="deleteSubject.htm?id=${subject.id}" class="btn btn-brown" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-trash-o"></i></span> Xóa</a>
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
                                                <th>Tên môn</th>
                                                <td>${subject.name}</td>
                                            </tr>
                                            <tr>
                                                <th>Mô tả</th>
                                                <td>${subject.description}</td>
                                            </tr>
                                            <tr>
                                                <th>Khóa học</th>
                                                    <c:forEach items="${listCourse}" var="course">
                                                        <c:if test="${course.id == subject.courseId}">
                                                        <td>${course.name}</td>
                                                    </c:if>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th>Ngày tạo</th>
                                                <td>${subjectCreated.createdAt} </td>
                                            </tr>
                                            <tr>
                                                <th>Ngày sửa</th>
                                                <td>${subjectUpdated.updatedAt}</td>
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
