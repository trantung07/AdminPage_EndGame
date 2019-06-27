<%-- 
    Document   : coursewareList
    Created on : 26-Jun-2019, 21:34:42
    Author     : Tran Tung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <title>Danh sách học liệu</title>
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
                                <li class='active'><a href="#">Quản lý học liệu</a></li>
                            </ol>
                            <h1>Danh sách học liệu</h1>
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
                                                <a href="initInsertCourseware.htm" class="btn btn-success" style="margin-bottom: 10px; width: 100px;"><span><i class="fa fa-plus-circle"></i></span> Thêm mới</a>

                                                <f:form class="form-horizontal" action="getAllCourseware.htm" commandName="searchCourseware" method="get">
                                                    <label for="name" class="col-sm-3 control-label" style="font-weight: bold">Tìm kiếm</label>
                                                    <div class="col-sm-6">
                                                        <f:input type="text" id="name" placeholder="Nhập tên học liệu để tìm kiếm" class="form-control col-md-7 col-xs-12" path="name" />
                                                    </div>
                                                    <span><button id="btnSave" type="submit" class="btn btn-success">Tìm kiếm</button></span>
                                                </f:form>

                                                <p><div class="pagination1">${url}</div><span style="float: right; font-size: 13px;">Hiển thị từ 
                                                <span style="font-weight: bold">${startIndex}</span> - <span style="font-weight: bold">${endIndex}</span> trên tổng số <span style="font-weight: bold">${size}</span> học liệu ${keySearch}</span></p>
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th style="padding-right:60px">Tên học liệu</th>
                                                        <th>Bài học</th>
                                                        <th>Loại</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${coursewareList}" var="ware">
                                                        <tr>

                                                            <td align="left"><i>${ware.name}</i></td>
                                                            <c:forEach items="${lessionList}" var="less">
                                                                <c:if test="${ware.lessionId == less.id}">
                                                                    <td>${less.name}</td>
                                                                </c:if>
                                                            </c:forEach>
                                                            <c:forEach items="${listType}" var="typ">
                                                                <c:if test="${ware.type == typ.id}">
                                                                    <td>${typ.typeName}</td>
                                                                </c:if>
                                                            </c:forEach>
                                                            <td>
                                                                <div class="">
                                                                    <a title="Xem chi tiết về học liệu ${ware.name}" class="blue" href="initDetailCourseware.htm?id=${ware.id}">
                                                                        <i class=" fa fa-search-plus bigger-250"></i>
                                                                    </a>
                                                                    <a title="Chỉnh sửa" class="green" style="padding-left: 10px" href="initUpdateCourseware.htm?id=${ware.id}">
                                                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                                    </a>
                                                                    <a title="Xóa" id="delBtn" class="red" style="padding-left: 10px" href="deleteCourseware.htm?id=${ware.id}" >
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
