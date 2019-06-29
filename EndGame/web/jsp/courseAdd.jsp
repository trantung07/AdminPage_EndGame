<%-- 
    Document   : courseAdd
    Created on : 16-Jun-2019, 21:48:40
    Author     : Tran Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Thêm mới khóa học</title>
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
                            <h1>Quản lý khóa học</h1>

                        </div>
                        <div class="container">
                            <div class="panel panel-success">
                                <div class="panel-heading">
                                    <h4>Thêm mới khóa học</h4>
                                </div>
                                <div class="panel-body">

                                <f:form class="form-horizontal" action="insertCourse.htm" commandName="newCourse">

                                    <div style="color: red">
                                        ${message}
                                    </div>
                                    <div class="form-group ${errorName}">
                                        <label for="name" class="col-sm-3 control-label">Tên khóa học</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="name" placeholder="Nhập tên Khóa học" class="form-control col-md-7 col-xs-12" path="name" />
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>
                                    <div class="form-group ${errDate}">
                                        <label for="startDate" class="col-sm-3 control-label">Ngày bắt đầu</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="startDate" placeholder="Chọn ngày bắt đầu" class="datepicker form-control col-md-7 col-xs-12 " path="startDate" readonly="readonly" />
                                        </div>
                                    </div>

                                    <div class="form-group ${errDate}">
                                        <label for="endDate" class="col-sm-3 control-label">Ngày kết thúc</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="endDate" placeholder="Chọn ngày kết thúc" class="datepicker form-control col-md-7 col-xs-12 " path="endDate" readonly="readonly" />
                                        </div>
                                    </div>
                                    <div class="form-group ${errPrice}">
                                        <label for="price" class="col-sm-3 control-label">Giá</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="price" placeholder="Nhập giá" class="form-control col-md-7 col-xs-12" path="price"/>
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
            $(document).ready(function () {
                $("#startDate").datepicker();
                $("#startDate").datepicker("setDate", new Date());
                $("#startDate").datepicker("option", "dateFormat", "dd/mm/yy");
                $("#startDate").datepicker("option", "changeMonth", true);
                $("#startDate").datepicker("option", "changeYear", true);
                $("#startDate").datepicker("option", "showAnim", "slideDown");

                $("#endDate").datepicker();
                $("#endDate").datepicker("setDate", new Date());
                $("#endDate").datepicker("option", "dateFormat", "dd/mm/yy");
                $("#endDate").datepicker("option", "changeMonth", true);
                $("#endDate").datepicker("option", "changeYear", true);
                $("#endDate").datepicker("option", "showAnim", "slideDown");

                $("#price").on("keypress keyup blur", function (event) {
                    $(this).val($(this).val().replace(/[^\d].+/, ""));
                    if ((event.which < 48 || event.which > 57)) {
                        event.preventDefault();
                    }
                });
            });

        </script>
    </body>
</html>
