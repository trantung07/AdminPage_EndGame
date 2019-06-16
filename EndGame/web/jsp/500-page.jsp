<%-- 
    Document   : 500-page
    Created on : 15-Jun-2019, 23:55:12
    Author     : Tran Tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <title>Danh sách người dùng</title>
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
                            <h1>500</h1>
                            <div class="options">
                                <a href="#" class="btn btn-default"><i class="fa fa-cog"></i></a>
                            </div>
                        </div>
                        <div class="container">

                            <div class="row">
                                <div class="col-md-12">
                                    <p class="text-center">
                                        <span class="text-danger" style="font-size:4em;">Oops!</span>
                                    </p>
                                    <p class="text-center">Something went terribly wrong.</p>
                                    <p class="text-center">We are fixing it. Please try again later.</p>
                                </div>
                            </div>

                        </div> <!-- container -->
                    </div> <!--wrap -->
                </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

        </div> <!-- page-container -->
    </body>
</html>
