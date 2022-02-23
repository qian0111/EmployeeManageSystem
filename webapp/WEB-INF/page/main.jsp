<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>员工管理系统</title>
    <!-- ================= Favicon ================== -->
    <!-- Standard -->
    <link rel="shortcut icon" href="http://placehold.it/64.png/000/fff">
    <!-- Retina iPad Touch Icon-->
    <link rel="apple-touch-icon" sizes="144x144" href="http://placehold.it/144.png/000/fff">
    <!-- Retina iPhone Touch Icon-->
    <link rel="apple-touch-icon" sizes="114x114" href="http://placehold.it/114.png/000/fff">
    <!-- Standard iPad Touch Icon-->
    <link rel="apple-touch-icon" sizes="72x72" href="http://placehold.it/72.png/000/fff">
    <!-- Standard iPhone Touch Icon-->
    <link rel="apple-touch-icon" sizes="57x57" href="http://placehold.it/57.png/000/fff">
    <!-- Styles -->
    <link href="${pageContext.request.contextPath}/css/fontawesome-all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/themify-icons.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/mmc-chat.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/sidebar.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/nixon.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/lobipanel.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>

<body>
    <div class="sidebar sidebar-hide-to-small sidebar-shrink sidebar-gestures" >
        <div class="nano">
            <div class="nano-content">
                <ul>
                    <li class="active">
                   		<a class="sidebar-sub-toggle"><i class="ti-user"></i> 员工管理<span class="sidebar-collapse-icon ti-angle-down"></span></a>
                   		<ul>
                            <li><a href="s?method=listPage" target="main">员工列表</a></li>
                             <!-- <li><a id="staffAdd">新增员工</a></li> -->
                        </ul>
                    </li>
                    <li>
				<a class="sidebar-sub-toggle"><i class="ti-home"></i> 部门管理 <span class="sidebar-collapse-icon ti-angle-down"></span></a>
                        <ul>
                            <li><a href="s?method=deptListPage" target="main">部门列表</a></li>
                             <!-- <li><a id="departAdd">新增部门</a></li> -->
                        </ul>
                    </li>
                    <li><a href="p?method=loginPage"><i class="ti-close"></i>退出</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="header">
        <div class="pull-left">
            <div class="logo">
                <a href="s?method=mainPage">
                    <img id="logoImg" src="images/welcome.png" data-logo_big="images/welcome.png" data-logo_small="images/welcomeSmall.png" alt="员工管理系统" />
                </a>
            </div>
            <div class="hamburger sidebar-toggle">
                <span class="ti-menu"></span>
            </div>
        </div>
        <div class="pull-right p-r-15">
            <ul>
                <li class="header-icon dib">
                    <img class="avatar-img" src="images/avatar/1.jpg" alt="" /> 
                    <span class="user-avatar" id="userNameShow">${sessionScope.staff.staffName}</span>
                </li>
            </ul>
        </div>
   	</div>
    <div id="departBox" class="content-wrap">                                 	
        <iframe name="main" 
			src="s?method=listPage" 
			frameborder="0" 
			style="width: 100%; height: 700px;">
		</iframe>                                                     	            	  
   </div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<!-- /# content wrap -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- jquery vendor -->
<script src="${pageContext.request.contextPath}/js/jquery.nanoscroller.min.js"></script>
<!-- nano scroller -->
<script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
<!-- sidebar -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- bootstrap -->
<script src="${pageContext.request.contextPath}/js/mmc-common.js"></script>
<script src="${pageContext.request.contextPath}/js/mmc-chat.js"></script>
<!--  Chart js -->
<script src="${pageContext.request.contextPath}/js/Chart.bundle.js"></script>
<script src="${pageContext.request.contextPath}/js/chartjs-init.js"></script>
<!-- // Chart js -->
<!--  Datamap -->
<script src="${pageContext.request.contextPath}/js/d3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/topojson.js"></script>
<script src="${pageContext.request.contextPath}/js/datamaps.world.min.js"></script>
<script src="${pageContext.request.contextPath}/js/datamap-init.js"></script>
<script src="${pageContext.request.contextPath}/js/lobipanel.js"></script>
<!-- // Datamap -->
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<!-- scripit init-->
<script>
	//如果当前窗口不是top窗口，则top窗口页面跳转到当前页面
/* 	if(window != top)
		top.location.href = location.href; */
	
   $(document).ready(function() {
       $('#lobipanel-custom-control').lobiPanel({
           reload: false,
           close: false,
           editTitle: false
       });
   });
</script>
</html>