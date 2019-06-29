<%-- 
    Document   : courseList
    Created on : 16-Jun-2019, 19:39:28
    Author     : Tran Tung
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <title>Danh sách khóa học</title>
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
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="panel panel-sky">
                                        <div class="panel-heading">
                                            <h4>Danh sách</h4>

                                        </div>
                                        <div class="panel-body">
                                            <div class="table-flipscroll">
                                                <a href="initInsertCourse.htm" class="btn btn-success" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-plus-circle"></i></span> Thêm mới</a>
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th style="padding-right:60px">Khóa học</th>
                                                            <th>Ngày bắt đầu</th>
                                                            <th>Ngày kết thúc</th>
                                                            <th>Giá</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${courseList}" var="cou">
                                                        <tr>
                                                            <td align="left"><i>${cou.name}</i></td>
                                                            <td>${cou.startDate}</td>
                                                            <td>${cou.endDate}</td>
                                                            <td><fmt:formatNumber type="number" maxFractionDigits = "3" value="${cou.price}" ></fmt:formatNumber> VNĐ</td>
                                                            
                                                            <td>
                                                                <div class="">
                                                                    <a title="Xem chi tiết về khóa học ${cou.name}" class="blue" href="initDetailCourse.htm?id=${cou.id}">
                                                                        <i class=" fa fa-search-plus bigger-250"></i>
                                                                    </a>
                                                                    <a title="Chỉnh sửa" class="green" style="padding-left: 10px" href="initUpdateCourse.htm?id=${cou.id}">
                                                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                                    </a>
                                                                    <a title="Xóa" id="delBtn" class="red" style="padding-left: 10px" href="deleteCourse.htm?id=${cou.id}" >
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
                    <div class="modal visiblemodal" id="openModal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                    <p>One fine body…</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" id="confirmBtn" class="btn btn-primary">Save changes</button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                </div> <!--wrap -->
            </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

        </div> <!-- page-container -->
        <script type="text/javascript">

        </script>
    </body>
</html>
