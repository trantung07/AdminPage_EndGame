<%-- 
    Document   : userAdd
    Created on : 08-Jun-2019, 16:46:14
    Author     : Tran Tung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Thêm mới người dùng</title>
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
                            <div class="options">
                                <div class="btn-toolbar">
                                    <button class="btn btn-default" id="daterangepicker2">
                                        <i class="fa fa-calendar-o"></i> 
                                        <span class="hidden-xs hidden-sm">October 2, 2015 - November 1, 2015</span> <b class="caret"></b>
                                    </button>
                                    <div class="btn-group hidden-xs">
                                        <a href='#' class="btn btn-default dropdown-toggle" data-toggle='dropdown'><i class="fa fa-cloud-download"></i><span class="hidden-xs hidden-sm hidden-md"> Export as</span> <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">Text File (*.txt)</a></li>
                                            <li><a href="#">Excel File (*.xlsx)</a></li>
                                            <li><a href="#">PDF File (*.pdf)</a></li>
                                        </ul>
                                    </div>
                                    <a href="#" class="btn btn-default hidden-xs"><i class="fa fa-cog"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="container">
                            <div class="panel panel-green">
                                <div class="panel-heading">
                                    <h4>Thêm mới người dùng</h4>
                                </div>
                                <div class="panel-body">

                                <f:form class="form-horizontal" action="insertUser.htm" commandName="newUser">
                                    <div id="infoRequired" style="size: 10px; color: red; font-style: italic;">Các trường có dấu * là các trường bắt buộc</div>
                                    <div style="color: red">
                                        ${message}
                                    </div>
                                    <div class="form-group ${errorClass}">
                                        <label for="username" class="col-sm-3 control-label">Tài khoản</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="username" placeholder="Nhập TK" class="form-control col-md-7 col-xs-12" path="username" />
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>
                                    <div class="form-group ${errPassword}" id="form-password" >
                                        <label for="password" class="col-sm-3 control-label">Mật khẩu</label>
                                        <div class="col-sm-6">
                                            <f:input type="password" id="password" placeholder="Nhập mật khẩu" class="form-control col-md-7 col-xs-12" path="password" />
                                        </div>
                                        <div class="col-md-3">
                                            <p class="help-block"><i class="fa fa-asterisk"></i></p>
                                        </div>
                                    </div>

                                    <div class="form-group ${errFirtName}" id="form-firstName" >
                                        <label for="firstName" class="col-sm-3 control-label">Họ</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="firstName" placeholder="Nhập tên họ" class="form-control col-md-7 col-xs-12" path="firstName" />
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>
                                    <div class="form-group ${errLastName}" id="form-lastName" >
                                        <label for="lastName" class="col-sm-3 control-label">Tên</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="lastName" placeholder="Nhập tên" class="form-control col-md-7 col-xs-12" path="lastName" />
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>
                                    <div class="form-group ${errDisplayName}" id="form-displayName" >
                                        <label for="lastName" class="col-sm-3 control-label">Tên hiển thị</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="displayName" placeholder="Nhập tên hiển thị" class="form-control col-md-7 col-xs-12" path="displayName" />
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>
                                    <div class="form-group">
                                        <label for="roleId" class="col-sm-3 control-label">Role</label>
                                        <div class="col-sm-2">
                                            <f:select id="roleId" class="form-control col-md-7 col-xs-12" path="roleId" >
                                                <c:forEach items="${listRole}" var="roles">
                                                    <f:option value="${roles.id}" >${roles.roleName}</f:option>
                                                </c:forEach>
                                            </f:select>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="sex" class="col-sm-3 control-label">Sex</label>
                                        <div class="col-sm-2">
                                            <f:select id="sex" class="form-control col-md-7 col-xs-12" path="sex" >
                                                <f:option value="1" label="Nam"/>
                                                <f:option value="0" label="Nữ"/>
                                                <f:option value="2" label="Không xác định"/>
                                            </f:select>
                                        </div>
                                    </div>

                                    <div class="form-group ${errEmail}" id="form-email" >
                                        <label for="email" class="col-sm-3 control-label">Email</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="email" placeholder="Nhập email" class="form-control col-md-7 col-xs-12" path="email" />
                                        </div>
                                    </div>
                                    <div class="form-group ${errDOB}" id="form-dob" >
                                        <label for="birthday" class="col-sm-3 control-label">Ngày sinh</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="birthday" placeholder="Chọn ngày sinh" class="datepicker form-control col-md-7 col-xs-12 " path="birthday" readonly="readonly" />
                                        </div>
                                    </div>    

                                    <div class="form-group ${errPhone}" id="form-phone" >
                                        <label for="phone" class="col-sm-3 control-label">Số điện thoại</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="phone" maxlength="20" placeholder="Nhập số điện thoại" class="form-control col-md-7 col-xs-12" path="phone" />
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
