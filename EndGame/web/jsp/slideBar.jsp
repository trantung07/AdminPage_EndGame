<%-- 
    Document   : slideBar
    Created on : 07-Jun-2019, 22:36:25
    Author     : Tran Tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav id="page-leftbar" role="navigation">
    <!-- BEGIN SIDEBAR MENU -->
    <ul class="acc-menu" id="sidebar">

        <li class="divider"></li>
        <li><a href="index.html"><i class="fa fa-home"></i> <span>Dashboard</span></a></li>
        <li><a href="javascript:;"><i class="fa fa-user"></i> <span>Quản lý người dùng</span> </a>
            <ul class="acc-menu">
                <li><a href="<%=request.getContextPath()%>/getAllUser.htm"><span>Danh sách</span></a></li>
                <li><a href="<%=request.getContextPath()%>/initInsertUser.htm"><span>Thêm mới</span></a></li>
            </ul>
        </li>
        
        <li><a href="javascript:;"><i class="fa fa-flag"></i> <span>Quản lý khóa học</span></a>
            <ul class='acc-menu'>
                <li><a href="getAllCourse.htm">Danh sách</a></li>
                <li><a href="initInsertCourse.htm">Thêm mới</a></li>
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-tasks"></i> <span>Quản lý môn học</span></a>
            <ul class="acc-menu">
                <li><a href="getAllSubject.htm">Danh sách</a></li>
                <li><a href="initInsertSubject.htm">Thêm mới</a></li>
                
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-table"></i> <span>Quản lý bài học</span></a>
            <ul class="acc-menu">
                <li><a href="getAllLession.htm">Danh sách</a></li>
                <li><a href="initInsertLession.htm">Thêm mới</a></li>
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-pencil"></i> <span>Quản lý học liệu</span></a>
            <ul class="acc-menu">
                <li><a href="getAllCourseware.htm">Danh sách</a></li>
                <li><a href="initInsertCourseware.htm">Thêm mới</a></li>
        <li><a href="javascript:;"><i class="fa fa-pencil"></i> <span>Quản lý đơn hàng</span></a>
            <ul class="acc-menu">
                <li><a href="getAllOrder.htm">Danh sách</a></li>
            </ul>
        </li>
        <li class="divider"></li>
    </ul>

</nav>
<div id="page-rightbar">

    <div id="chatarea">
        <div class="chatuser">
            <span class="pull-right">Jane Smith</span>
            <a id="hidechatbtn" class="btn btn-default btn-sm"><i class="fa fa-arrow-left"></i> Back</a>
        </div>
        <div class="chathistory">
            <div class="chatmsg">
                <p>Hey! How's it going?</p>
                <span class="timestamp">1:20:42 PM</span>
            </div>
            <div class="chatmsg sent">
                <p>Not bad... i guess. What about you? Haven't gotten any updates from you in a long time.</p>
                <span class="timestamp">1:20:46 PM</span>
            </div>
            <div class="chatmsg">
                <p>Yeah! I've been a bit busy lately. I'll get back to you soon enough.</p>
                <span class="timestamp">1:20:54 PM</span>
            </div>
            <div class="chatmsg sent">
                <p>Alright, take care then.</p>
                <span class="timestamp">1:21:01 PM</span>
            </div>
        </div>
        <div class="chatinput">
            <textarea name="" rows="2"></textarea>
        </div>
    </div>

    <div id="widgetarea">
        <div class="widget">
            <div class="widget-heading">
                <a href="javascript:;" data-toggle="collapse" data-target="#accsummary"><h4>Account Summary</h4></a>
            </div>
            <div class="widget-body collapse in" id="accsummary">
                <div class="widget-block" style="background: #7ccc2e; margin-top:10px;">
                    <div class="pull-left">
                        <small>Current Balance</small>
                        <h5>$71,182</h5>
                    </div>
                    <div class="pull-right"><div id="currentbalance"></div></div>
                </div>
                <div class="widget-block" style="background: #595f69;">
                    <div class="pull-left">
                        <small>Account Type</small>
                        <h5>Business Plan A</h5>
                    </div>
                    <div class="pull-right">
                        <small class="text-right">Monthly</small>
                        <h5>$19<small>.99</small></h5>
                    </div>
                </div>
                <span class="more"><a href="#">Upgrade Account</a></span>
            </div>
        </div>


    </div>
</div>