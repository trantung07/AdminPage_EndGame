<%-- 
    Document   : lessionAdd
    Created on : 23-Jun-2019, 10:14:48
    Author     : Tran Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Thêm mới bài học</title>
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
                                    <h4>Thêm mới bài học</h4>
                                </div>
                                <div class="panel-body">

                                <f:form class="form-horizontal" action="insertLession.htm" commandName="newLession">

                                    <div style="color: red">
                                        ${message}
                                    </div>
                                    <div class="form-group ${errorName}">
                                        <label for="name" class="col-sm-3 control-label">Tên bài học</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="name" placeholder="Nhập tên Khóa học" class="form-control col-md-7 col-xs-12" path="name" />
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>

                                    <div class="form-group">
                                        <label for="roleId" class="col-sm-3 control-label">Môn học</label>
                                        <div class="col-sm-2">
                                            <f:select id="subjectId" class="form-control col-md-7 col-xs-12" path="subjectId" >
                                                <c:forEach items="${listSubject}" var="sub">
                                                    <f:option value="${sub.id}" >${sub.name}</f:option>
                                                </c:forEach>
                                            </f:select>
                                        </div>

                                    </div>
                                    <div class="form-group ${errDescription}">
                                        <label for="description" class="col-sm-3 control-label">Mô tả</label>
                                        <div class="col-sm-6">
                                            <f:textarea type="text" id="description" placeholder="Nhập mô tả" class="form-control col-md-7 col-xs-12" path="description" />
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
        </script>
    </body>
</html>
