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
                <li><a href="tables-basic.html">Tables</a></li>
                <li><a href="form-layout.html">Forms</a></li>
                <li><a href="ui-panels.html">Panels</a></li>
                <li><a href="ui-images.html">Images</a></li>
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-tasks"></i> <span>UI Components</span> <span class="badge badge-info">12</span></a>
            <ul class="acc-menu">
                <li><a href="ui-tiles.html">Tiles</a></li>
                <li><a href="ui-modals.html">Modals &amp; Bootbox</a></li>
                <li><a href="ui-progressbars.html">Progress Bars</a></li>
                <li><a href="ui-paginations.html">Pagers &amp; Paginations</a></li>
                <li><a href="ui-breadcrumbs.html">Breadcrumbs</a></li>
                <li><a href="ui-labelsbadges.html">Labels &amp; Badges</a></li>
                <li><a href="ui-alerts.html">Alerts &amp; Notificiations</a></li>
                <li><a href="ui-sliders.html">Sliders &amp; Ranges</a></li>
                <li><a href="ui-tabs.html">Tabs &amp; Accordions</a></li>
                <li><a href="ui-carousel.html">Carousel</a></li>
                <li><a href="ui-nestable.html">Nestable Lists</a></li>
                <li><a href="ui-wells.html">Wells</a></li>
                <li><a href="ui-tour.html">Tour</a></li>
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-table"></i> <span>Advanced Tables</span></a>
            <ul class="acc-menu">
                <li><a href="tables-data.html">Data Tables</a></li>
                <li><a href="tables-responsive.html">Responsive Tables</a></li>
                <li><a href="tables-editable.html">Editable Tables</a></li>
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-pencil"></i> <span>Advanced Forms</span><span class="badge badge-primary">5</span></a>
            <ul class="acc-menu">
                <li><a href="form-components.html">Components</a></li>
                <li><a href="form-wizard.html">Wizards</a></li>
                <li><a href="form-validation.html">Validation</a></li>
                <li><a href="form-masks.html">Masks</a></li>
                <li><a href="form-fileupload.html">Multiple File Uploads</a></li>
                <li><a href="form-dropzone.html">Dropzone File Uploads</a></li>
                <li><a href="form-ckeditor.html">WYSIWYG Editor</a></li>
                <li><a href="form-xeditable.html">Inline Editor</a></li>
                <li><a href="form-imagecrop.html">Image Cropping</a></li>
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-map-marker"></i> <span>Maps</span></a>
            <ul class="acc-menu">
                <li><a href="maps-google.html">Google Maps</a></li>
                <li><a href="maps-vector.html">Vector Maps</a></li>
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-bar-chart-o"></i> <span>Charts</span></a>
            <ul class="acc-menu">
                <li><a href="charts-flot.html">Extensible</a></li>
                <li><a href="charts-svg.html">Interactive</a></li>
                <li><a href="charts-canvas.html">Lightweight</a></li>
                <li><a href="charts-inline.html">Inline</a></li>
            </ul>
        </li>
        <li><a href="calendar.html"><i class="fa fa-calendar"></i> <span>Calendar</span></a></li>
        <li><a href="gallery.html"><i class="fa fa-camera"></i> <span> Gallery</span> </a></li>
        <li><a href="javascript:;"><i class="fa fa-flag"></i> <span>Icons</span> <span class="badge badge-orange">2</span></a>
            <ul class="acc-menu">
                <li><a href="icons-fontawesome.html">Font Awesome</a></li>
                <li><a href="icons-glyphicons.html">Glyphicons</a></li>
            </ul>
        </li>
        <li class="divider"></li>
        <li><a href="javascript:;"><i class="fa fa-briefcase"></i> <span>Extras</span> <span class="badge badge-danger">1</span></a>
            <ul class="acc-menu">
                <li><a href="extras-timeline.html">Timeline</a></li>
                <li><a href="extras-profile.html">Profile</a></li>
                <li><a href="extras-inbox.html">Inbox</a></li>
                <li><a href="extras-search.html">Search Page</a></li>
                <li><a href="extras-chatroom.html">Chat Room</a></li>
                <li><a href="extras-invoice.html">Invoice</a></li>
                <li><a href="extras-registration.html">Registration</a></li>
                <li><a href="extras-signupform.html">Sign Up</a></li>
                <li><a href="extras-forgotpassword.html">Password Reset</a></li>
                <li><a href="extras-login.html">Login 1</a></li>
                <li><a href="extras-login2.html">Login 2</a></li>
                <li><a href="extras-404.html">404 Page</a></li>
                <li><a href="extras-500.html">500 Page</a></li>
            </ul>
        </li>
        <li><a href="javascript:;"><i class="fa fa-sitemap"></i> <span>Unlimited Level Menu</span></a>
            <ul class="acc-menu">
                <li><a href="javascript:;">Menu Item 1</a></li>
                <li><a href="javascript:;">Menu Item 2</a>
                    <ul class="acc-menu">
                        <li><a href="javascript:;">Menu Item 2.1</a></li>
                        <li><a href="javascript:;">Menu Item 2.2</a>
                            <ul class="acc-menu">
                                <li><a href="javascript:;">Menu Item 2.2.1</a></li>
                                <li><a href="javascript:;">Menu Item 2.2.2</a>
                                    <ul class="acc-menu">
                                        <li><a href="javascript:;">And deeper yet!</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
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