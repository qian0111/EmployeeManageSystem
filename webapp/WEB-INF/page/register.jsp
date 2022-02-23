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
			function register(){
				var staffName = $("#staffName").val();
				var pass = $("#pass").val();
				var surePass = $("#surePass").val();
				var phone = $("#phone").val();
				
				if(staffName == ''){
					alert("用户名为空!");
					$("#staffName").focus();
					return;
				}
				if(phone == ''){
					alert("手机号为空");
					$("#phone").focus();
					return;
				}	
				var regx = /^1[3456789]\d{9}$/;
				if(!regx.test(phone)){
					alert("手机号不合法");
					$("#phone").focus();
					return;
				}
				if(pass == ''){
					alert("密码为空!");
					$("#pass").focus();
					return;
				}
				if(pass != surePass){
					alert("两次密码输入不一致，请重新输入！");
					$("#pass").val("");
					$("#pass").attr("placeholder","请输入密码");
					$("#surePass").val("");
					$("#surePass").attr("placeholder","请确认密码");
					$("#pass").focus();
					return;
				}				
				
				//ajax请求
				$.ajax({
					url: "p?method=register",//请求地址
					type:"POST",//请求方式GET/POST
					data:{"staffName":staffName, "pass":pass, "phone":phone},
					success:function(str){//请求成功之后的回调函数，接收服务端响应的数据
						if(str == "1"){
							$("#registerButton").text("正在跳转...");
							setTimeout(
								function(){
									window.location.href="s?method=mainPage";
							    }
								,800
							);
						} else {
							alert("用户已存在,请登录");
							$("#staffName").val("");
							$("#phone").val("");
							$("#pass").val("");
							$("#surePass").val("");
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
								<h4>用户注册</h4>
								<form>
									<div class="form-group">
										<label>用户名</label>
										<input type="text" class="form-control" aria-describedby="usernameHelp" id="staffName" placeholder="请输入用户名/手机号"> 
									</div>
									<div class="form-group">
										<label>手机号</label>
										<input type="text" class="form-control" id="phone" placeholder="请输入手机号">
									</div>
									<div class="form-group">
										<label>密码</label>
										<input type="password" class="form-control" id="pass" placeholder="请输入密码">
									</div>
									<div class="form-group">
										<label>确认密码</label>
										<input type="password" class="form-control" id="surePass" placeholder="请确认密码">
									</div>	
									<button type="button" class="btn btn-primary btn-flat m-b-30 m-t-30" id="registerButton" onclick="register();">注册</button>
									<div class="register-link m-t-15 text-center">
										<p>如果您没有账户? <a href="p?method=loginPage">点击登录<a></p>
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