<%-- 
    Document   : homePage
    Created on : 05-Jun-2019, 21:55:43
    Author     : Tran Tung
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
                            <h1>Trang chủ</h1>

                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="panel panel-sky">
                                        <div class="panel-heading">
                                            <h4>Danh sách người dùng</h4>
                                        </div>
                                        <div class="panel-body">
                                            <div class="table-flipscroll">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th style="padding-right:60px">Họ tên</th>
                                                            <th>Tài khoản</th>
                                                            <th>Tên hiển thị</th>
                                                            <th>Role</th>
                                                            <th>Ngày sinh</th>
                                                            <th>Giới tính</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${userList}" var="user">
                                                        <tr>
                                                            <td align="left"><i>${user.firstName} ${user.lastName}</i></td>
                                                            <td><a title="Xem chi tiết về người dùng ${user.username}" href="initDetailUser.htm?id=${user.id}">${user.username} </a></td>
                                                            <td>${user.displayName}</td>
                                                            <c:forEach items="${roleList}" var="role">
                                                                <c:if test="${role.id == user.roleId}">
                                                                    <td>${role.roleName}</td>
                                                                </c:if>
                                                            </c:forEach>

                                                            <td>${user.birthday}</td>

                                                            <c:if test="${user.sex == 1}">
                                                                <td>Nam</td>
                                                            </c:if>
                                                            <c:if test="${user.sex == 0}">
                                                                <td>Nữ</td>
                                                            </c:if>
                                                            <c:if test="${user.sex == 2}">
                                                                <td>Không xác định</td>
                                                            </c:if>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-green">
                                    <div class="panel-heading">
                                        <h4>Danh sách đơn hàng</h4>
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-flipscroll">      
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th class="col-sm-2">Mã đơn hàng</th>
                                                        <th style="padding-left: 40px;" class="col-sm-4">Họ tên</th>
                                                        <th style="padding-left: 50px;">Tổng tiền</th>
                                                        <th class="col-sm-2">Trạng thái</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${OrderList}" var="order">
                                                        <tr>
                                                            <td align="left" class="col-sm-2"><i><a title="Xem chi tiết về đơn hàng" class="blue" href="initDetailOrder.htm?id=${order.id}">${order.id}</a></i></td>
                                                                    <c:forEach items="${ListUser}" var="u">
                                                                        <c:if test="${u.id == order.usersId}">
                                                                    <td style="padding-left: 40px;" class="col-sm-4">${u.username}</td>
                                                                </c:if>
                                                            </c:forEach>
                                                            <td style="padding-left: 50px;">${order.totalCost}</td>
                                                            <c:if test="${order.status == true}">
                                                                <td class="col-sm-2">Thành công</td>
                                                            </c:if>
                                                            <c:if test="${order.status == false}">
                                                                <td class="col-sm-2">Đã hủy</td>
                                                            </c:if> 
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>

                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-danger">
                                    <div class="panel-heading">
                                        <h4>Danh sách khóa học</h4>

                                    </div>
                                    <div class="panel-body">
                                        <div class="table-flipscroll">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th class="col-sm-2">Khóa học</th>
                                                        <th style="padding-left: 40px;" class="col-sm-4">Ngày bắt đầu</th>
                                                        <th style="padding-left: 50px;">Ngày kết thúc</th>
                                                        <th class="col-sm-2">Giá</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${courseList}" var="cou">
                                                        <tr>
                                                            <td align="left" class="col-sm-2"><i><a title="Xem chi tiết về khóa học ${cou.name}" class="blue" href="initDetailCourse.htm?id=${cou.id}">${cou.name}</a></i></td>
                                                            <td style="padding-left: 40px;" class="col-sm-4">${cou.startDate}</td>
                                                            <td style="padding-left: 50px;">${cou.endDate}</td>
                                                            <td class="col-sm-2"><fmt:formatNumber type="number" maxFractionDigits = "3" value="${cou.price}" ></fmt:formatNumber> VNĐ</td>
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
                </div> <!--wrap -->
            </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

        </div> <!-- page-container -->
    </body>
</html>
