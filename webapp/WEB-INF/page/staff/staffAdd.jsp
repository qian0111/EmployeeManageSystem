<%@ page language="java" 
contentType="text/html; charset=utf-8" 
pageEncoding="utf-8" %> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link href="${pageContext.request.contextPath}/css/fontawesome-all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/themify-icons.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/nixon.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    
	<title></title>
</head>
<body>
	<div class="login-form" style="height:450px">
		<form>
			<div class="form-group">
				<label>员工姓名<span style="color:red">*</span></label>
				<input type="text" class="form-control" name="staffName"> 
			</div>
			<div class="form-group">
				<label>手机号<span style="color:red">*</span></label>
				<input type="text" class="form-control" name="phone"> 
			</div>
			
			<div class="form-group">
				<label>所属部门</label>
				<select class="form-control"  name="deptId">	
					<option value="0">请选择部门</option>									
					<c:forEach items="${deptList}" var="dept">
						<option value="${dept.id}">${dept.deptName}
						</option>
					</c:forEach>								
				</select>				
			</div>		
		</form>
		<button class="btn btn-primary btn-flat m-b-30 m-t-30" onclick="push();">新增员工</button>	
	</div>	
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
	function push(){
		$.ajax({
			url: "s?method=addStaffInfo",
			data: $("form").serialize(),
			type: "POST",
			success:function(str){
				if(str == "1"){
					alert("更新成功");
				}
				else{
					alert("更新失败");
				}
			},
			error:function(){
				
			}
		});
		setTimeout("reloadAgain()", 100);		
	}
	function reloadAgain(){
		//关闭弹出层
		var index = parent.layer.getFrameIndex(window.name);
		//关闭当前页
		parent.layer.close(index);
		//父页面刷新
		parent.location.reload();
	}
</script>			
</html>