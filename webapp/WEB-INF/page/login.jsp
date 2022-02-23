<%@ page language="java" 
contentType="text/html; charset=utf-8" 
pageEncoding="utf-8" %> 

<html>
	<head>
		<title>员工管理系统</title>
		
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	
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
	    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath}/css/nixon.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	    
		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script>
			
			function login(){
				var staffName = $("#staffName").val();
				var pass = $("#pass").val();
				if(staffName == ''){
					alert("用户名/手机号为空！");
					$("#staffName").attr("placeholder","请输入用户名/手机号");
					$("#staffName").focus();
					return;
				}
				if(pass == ''){
					alert("密码为空！");
					$("#pass").attr("placeholder","请输入密码");
					$("#pass").focus();
					return;
				}
				var data;
				var regx = /1[3456789]\d{9}/;
				if(regx.test(staffName)){
					data = {"phone":staffName,"pass":pass};
				} else {
					data = {"staffName":staffName,"pass":pass};
				}
				console.info(data);
				
				//ajax请求
				$.ajax({
					url: "p?method=loginCheck",//请求地址
					type:"POST",//请求方式GET/POST
					//data:{"addr":"山东青岛","userNo":"1001","phone":"18312341234"},//请求参数
					data: data,
					success:function(str){//请求成功之后的回调函数，接收服务端响应的数据
						if(str == "1"){
							$("#loginButton").text("正在跳转...");
							setTimeout(
								function(){
									window.location.href="s?method=mainPage";
							    }
								,800
							);
						} else {	
							alert("登录失败");
							$("#staffName").val("");
							$("#staffName").attr("placeholder","请输入用户名");
							$("#pass").val("");
							$("#pass").attr("placeholder","请输入密码");
						}
					},
					error:function(){//请求失败之后的回调函数
						
					}
				});
			}
		</script>
	</head>

	<body class="bg-primary">
		<div class="Nixon-login">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-lg-offset-3">
						<div class="login-content">
							<div class="login-logo">
								<span>员工管理系统</span>
							</div>
							<div class="login-form">
								<h4>用户登录</h4>
								<form>
									<div class="form-group">
										<label>用户名/手机号</label>
										<input type="text" class="form-control" aria-describedby="usernameHelp" id="staffName" placeholder="请输入用户名/手机号"> 
									</div>
									<div class="form-group">
										<label>密码</label>
										<input type="password" class="form-control" id="pass" placeholder="请输入密码">
									</div>
									<!-- <div class="checkbox">
										<label>
											<input type="checkbox"> Remember Me
										</label> -->
										<!-- <label class="pull-right">
											<a href="staff?method=registerPage">注 册<a>
										</label> 
										
									</div>-->
									<button type="button" class="btn btn-primary btn-flat m-b-30 m-t-30" id="loginButton" onclick="login();">登录</button>
									<div class="register-link m-t-15 text-center">
										<p>如果您没有账户? <a href="p?method=registerPage">点击注册<a></p>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>