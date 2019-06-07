<%-- 
    Document   : homePage
    Created on : 05-Jun-2019, 21:55:43
    Author     : Tran Tung
--%>

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
                                <li class='active'><a href="index-2.html">Dashboard</a></li>
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
                    </div> <!--wrap -->
                </div> <!-- page-content -->

            <jsp:include page="footer.jsp"></jsp:include>

        </div> <!-- page-container -->
    </body>
</html>
