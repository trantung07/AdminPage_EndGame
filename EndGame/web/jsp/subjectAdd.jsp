<%-- 
    Document   : subjectAdd
    Created on : Jun 25, 2019, 2:14:26 AM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Thêm mới môn học</title>
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
                            <div class="panel panel-success">
                                <div class="panel-heading">
                                    <h4>Thêm mới môn học</h4>
                                </div>
                                <div class="panel-body">

                                <f:form class="form-horizontal" action="insertSubject.htm" commandName="newSubject">
                                    <div id="infoRequired" style="size: 10px; color: red; font-style: italic;">Các trường có dấu * là các trường bắt buộc</div>
                                    <div style="color: red">
                                        ${message}
                                    </div>
                                    <div class="form-group ${errorClass}">
                                        <label for="username" class="col-sm-3 control-label">Tên môn học</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="name" placeholder="Nhập tên môn học" class="form-control col-md-7 col-xs-12" path="name" />
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>
                                    <div class="form-group ${errPassword}" id="form-password" >
                                        <label for="password" class="col-sm-3 control-label">Mô tả</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="description" name="description" placeholder="Nhập mô tả" class="form-control col-md-7 col-xs-12" path="description" />
                                        </div>
                                        <div class="col-md-3">
                                            <p class="help-block"><i class="fa fa-asterisk"></i></p>
                                        </div>
                                    </div>

                                   
                                    <div class="form-group">
                                        <label for="roleId" class="col-sm-3 control-label">Khóa học</label>
                                        <div class="col-sm-2">
                                            <f:select id="courseId" class="form-control col-md-7 col-xs-12" path="courseId" >
                                                <c:forEach items="${listCourse}" var="course">
                                                    <f:option value="${course.id}" >${course.name}</f:option>
                                                </c:forEach>
                                            </f:select>
                                        </div>

                                    </div>
                                    
                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">

                                            <button class="btn btn-primary" type="button" onclick="history.go(-1)">Quay lại</button>
                                            <button id="btnSave" type="submit" class="btn btn-success">Lưu</button>
                                        </div>
                                    </div>    
                                </f:form>
                            </div>
                        </div>

                    </div> <!-- container -->
                </div> <!--wrap -->
            </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

        </div> <!-- page-container -->

        <script>
            $(document).ready(function () {
                $("#sex").val(1);
                $("#birthday").datepicker();
                $("#birthday").datepicker("setDate", new Date());
                $("#birthday").datepicker("option", "dateFormat", "dd/mm/yy");
                $("#birthday").datepicker("option", "changeMonth", true);
                $("#birthday").datepicker("option", "changeYear", true);
                $("#birthday").datepicker("option", "maxDate", "+0m +0w");
                $("#birthday").datepicker("option", "showAnim", "slideDown");
            });

        </script>
    </body>
</html>
