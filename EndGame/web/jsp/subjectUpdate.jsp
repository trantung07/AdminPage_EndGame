<%-- 
    Document   : subjectUpdate
    Created on : Jun 25, 2019, 1:30:54 AM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Chỉnh sửa môn học</title>
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
                            <div class="panel panel-green">
                                <div class="panel-heading">
                                    <h4>Chỉnh sửa thông tin môn học</h4>
                                </div>
                                <div class="panel-body">

                                <f:form class="form-horizontal" action="updateSubject.htm" commandName="updateSubject">
                                    <div style="color: red">
                                        ${message}
                                    </div>
                                    <div class="form-group ${errorClass}">
                                        <input type="hidden" name="id" value="${updateSubject.id}"/>
                                        <label for="username" class="col-sm-3 control-label">Tên môn học</label>
                                        <div class="col-sm-6">
                                            <input type="text" id="name" placeholder="Tên môn học" class="form-control col-md-7 col-xs-12" value="${updateSubject.name}" name="name"/>
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>
                                  
                                    <div class="form-group ${errDisplayName}" id="form-displayName" >
                                        <label for="lastName" class="col-sm-3 control-label">Mô tả</label>
                                        <div class="col-sm-6">
                                            <input type="text" id="description" placeholder="mô tả" class="form-control col-md-7 col-xs-12" name="description"  value="${updateSubject.description}"/>

                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="roleId" class="col-sm-3 control-label">Khóa học</label>
                                        <div class="col-sm-2">
                                            <f:select id="courseId" class="form-control col-md-7 col-xs-12" path="courseId" >
                                                <c:forEach items="${listCourse}" var="course">
                                                    <f:option value="${course.id}">${course.name}</f:option>
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

        
    </body>
</html>

