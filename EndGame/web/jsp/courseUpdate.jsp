<%-- 
    Document   : courseUpdate
    Created on : 22-Jun-2019, 09:56:43
    Author     : Tran Tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>Chỉnh sửa khóa học</title>
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
                                    <h4>Chỉnh sửa thông tin khóa học</h4>
                                </div>
                                <div class="panel-body">

                                    <f:form class="form-horizontal" action="updateCourse.htm" commandName="updateCourse">
                                        <div style="color: red">
                                        ${message}
                                    </div>
                                    <div class="form-group ${errorClass}">
                                        <input type="hidden" name="id" value="${updateCourse.id}"/>
                                        <label for="username" class="col-sm-3 control-label">Tên khóa học</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="name" placeholder="Nhập tên Khóa học" class="form-control col-md-7 col-xs-12" path="name" />
                                        </div>
                                        <div class="col-md-3"><p class="help-block"><i class="fa fa-asterisk"></i></p></div>
                                    </div>

                                    <div class="form-group ${errDate}">
                                        <label for="startDate" class="col-sm-3 control-label">Ngày bắt đầu</label>
                                        <div class="col-sm-6">
                                            <input type="text" id="startDate" value="${updateCourse.startDate}" 
                                                   readonly="readonly" class="datepicker form-control col-md-7 col-xs-12 " 
                                                   name="startDate" 
                                                   onclick="$('#startDate').datepicker();$('#startDate').datepicker('show');" />
                                        </div>
                                    </div>

                                    <div class="form-group ${errDate}">
                                        <label for="endDate" class="col-sm-3 control-label">Ngày kết thúc</label>
                                        <div class="col-sm-6">
                                            <input type="text" id="endDate" value="${updateCourse.endDate}" 
                                                   readonly="readonly" class="datepicker form-control col-md-7 col-xs-12 " 
                                                   name="endDate" 
                                                   onclick="$('#endDate').datepicker();$('#endDate').datepicker('show');" />
                                        </div>
                                    </div>               

                                    <div class="form-group ${errDes}" id="form-phone" >
                                        <label for="description" class="col-sm-3 control-label">Nội dung khóa học</label>
                                        <div class="col-sm-6">
                                            <f:textarea type="text" id="description" placeholder="Nhập mô tả" class="form-control col-md-7 col-xs-12" path="description" />
                                        </div>
                                    </div>
                                    <div class="form-group ${errPrice}">
                                        <label for="price" class="col-sm-3 control-label">Giá</label>
                                        <div class="col-sm-6">
                                            <f:input type="text" id="price" placeholder="Nhập giá" class="form-control col-md-7 col-xs-12" path="price"/>
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
