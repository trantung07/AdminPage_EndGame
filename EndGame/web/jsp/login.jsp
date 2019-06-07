<%-- 
    Document   : login
    Created on : 05-Jun-2019, 21:36:53
    Author     : Tran Tung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- Nulled by http://www.baobinh.net by tieulonglanh -->
    <head>
        <meta charset="utf-8">
        <title>Avant</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Avant">
        <meta name="author" content="The Red Team">

        <link rel="stylesheet" href="/EndGame/jsp/assets/css/styles.minc726.css?=140">
        <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600' rel='stylesheet' type='text/css'>

    </head>
    <body class="focusedform">
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '../www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-44426473-2', 'auto');
            ga('send', 'pageview');

        </script>
        <div class="verticalcenter">
            <a href="index.html"><img src="/EndGame/jsp/assets/img/logo-big.png" alt="Logo" class="brand" /></a>
            <div class="panel panel-primary">
                <div class="panel-body">
                    <h4 class="text-center" style="margin-bottom: 25px;">Log in to get started</h4>
                    <f:form action="loginAdmin.htm" commandName="user" class="form-horizontal">
                        <div style="color: red">
                                                        ${message}
                                                    </div>
                        <div class="form-group">
                            <label for="email" class="control-label col-sm-4" style="text-align: left;">Email</label>
                            <div class="col-sm-8">
                                <f:input type="text" class="form-control" placeholder="Tên đăng nhập" path="username" id="username" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label col-sm-4" style="text-align: left;">Password</label>
                            <div class="col-sm-8">
                                <f:input type="password" class="form-control" placeholder="Mật khẩu" path="password" id="password" />
                            </div>
                        </div>
                        <div class="clearfix">
                            <div class="pull-right"><label><input type="checkbox" checked> Remember Me</label></div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block"><span>Đăng nhập</span>
                        </button>
                    </f:form>

                </div>
                <div class="panel-footer">
                    <a href="extras-forgotpassword.html" class="pull-left btn btn-link" style="padding-left:0">Forgot password?</a>

                    <div class="pull-right">
                        <a href="#" class="btn btn-default">Reset</a>
                    </div>
                </div>
            </div>
        </div>


    </body>
</html>
