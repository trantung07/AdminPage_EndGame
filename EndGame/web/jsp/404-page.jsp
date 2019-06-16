<%-- 
    Document   : 404-page
    Created on : 15-Jun-2019, 21:59:50
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
                            <ol class="breadcrumb">
                            </ol>

                            <h1>404</h1>
                            <div class="options">
                                <a href="#" class="btn btn-default"><i class="fa fa-cog"></i></a>
                            </div>
                        </div>
                        <div class="container">

                            <div class="row">
                                <div class="col-md-12">
                                    <p class="text-center">
                                        <span class="text-info" style="font-size:4em;">URL Not Found!!!</span>
                                    </p>
                                    <p class="text-center">It looks like you have taken a wrong turn</p>
                                    <p class="text-center">If you are in denial and think it's a conspiracy that cannot possibly be true,</br>try using the search bar below.</p>
                                </div>
                                <div class="col-md-4 col-md-offset-4">
                                    <form action="#" class="form-inline">
                                        <div class="input-group" style="width:100%">
                                            <label for="errsearch" class="sr-only">Search</label>
                                            <input type="text" class="form-control" id="errsearch" placeholder="Search...">
                                            <span class="input-group-btn" style="width: 1%;">
                                                <button class="btn btn-default" type="button">Search</button>
                                            </span>
                                        </div>
                                    </form>
                                    <p class="text-center"><small><a href="#">Report error?</a></small></p>
                                </div>
                            </div>

                        </div> <!-- container -->
                    </div> <!--wrap -->
                </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

        </div> <!-- page-container -->
    </body>
</html>
