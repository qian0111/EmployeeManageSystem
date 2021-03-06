<%@ page language="java" 
contentType="text/html; charset=utf-8" 
pageEncoding="utf-8" %> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
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
     <style>
		::-webkit-scrollbar {display:none}
	</style>
</head>
<body>
	<div class="main">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="card alert col-lg-12">				
                    	<div class="card-body">
                    		<form action="s?method=deptListPage" method="POST">
                    			<div class="col-lg-3 p-0">
				        			<div class="page-header">
				        				<div class="page-title">                            
				        					<h1><input type="text" placeholder="?????????????????????" class="form-control" name="id" value="${queryDemo.id}"></h1>
				        				</div>
				        			</div>
				    			</div>
				    			<div class="col-lg-3 p-0">
				        			<div class="page-header">
				        				<div class="page-title">                            
				        					<h1><input type="text"placeholder="?????????????????????" class="form-control" name="deptName"  value="${queryDemo.deptName}"></h1>
				        				</div>
									</div>
								</div>
								<div class="col-lg-3 p-0">
									<div class="page-header">
				    					<div class="page-title">                            
				        					<h1><input type="text" placeholder="?????????????????????" class="form-control" name="managerName" value="${queryDemo.managerName}"></h1>
				        				</div>
				    				</div>
								</div>			    				
			   					<div class="col-lg-1 p-0">
					        		<div class="page-header">
					           			<div class="page-title">                            
					                		<h1>
					                		<button class="form-control btn-primary">??????</button>
					               			</h1>
					          			</div>
					        		</div>
			    				</div> 
			    				<div class="col-lg-2 p-0">
			       					
			    				</div>                   			
                    		</form>                   		
						</div>		
					</div>				
				</div>
			</div><!-- row -->
			<div class="row">
               <div class="col-lg-12">              
                  <div class="card alert">
                      <div class="card-header">
                          <h4>???????????? </h4>                                                   
                      </div>
                     <!--  <div class="card-body" style="height:400px;overflow-y: scroll;" > -->
                      <div class="card-body" style="height:410px;" >
                          <table class="table table-responsive table-hover">
                            <colgroup>
								<col width="20%">
							    <col width="25%">
							    <col width="25%">
								<col width="20%">
								<col width="10%">
							</colgroup>
                              <thead>
                                  <tr>
                                      <th>????????????</th>
									  <th>????????????</th>
									  <th>??????</th>
									  <th>??????</th>
									  <th>??????</th>
                                  </tr>
                              </thead>
                              <tbody>
                                <c:forEach items="${deptList}" var="t">
							  	  	  <tr>	
										  <td>${t.id }</td>
										  <td>${t.deptName }</td>
										  <td>${t.deptNumber }</td>
										  <td>${t.managerName }</td>
										  <td><button class="btn btn-primary" onclick="openWin('${t.id }');">??????</button></td>										 
							   		  </tr>
							  	</c:forEach>                             
                             </tbody>
                           </table>                          
                     	</div>
                     	<div class="row">
                     		<div class="col-lg-1">
                     			<button class="btn btn-primary" onclick="addDept();">??????</button>
                     		</div>
                     		<div class="col-lg-10" id="lp" align="center">
                     		
                     		</div>
                     	</div>
                  </div>                 
               </div>
            </div>
		</div>
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
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
	//????????????????????????
	layui.use(['layer','laypage'], function(){
		//?????????????????????
		var layer = layui.layer;
		//??????????????????
		var laypage = layui.laypage;
		//????????????laypage??????
		laypage.render({
			elem: 'lp' //??????????????????lp???id????????????#???
		    ,count: ${totalCount} //?????????????????????????????????
			,limit: ${pageCount} //????????????????????????laypage???????????? count ??? limit ?????????????????????
			,curr: ${pageNo} //????????????
			,jump: function(obj, first){
				//obj????????????????????????????????????????????????
				console.log(obj.curr); //???????????????????????????????????????????????????????????????
				console.log(obj.limit); //???????????????????????????
			
				var pageNo = obj.curr;
				var pageCount = obj.limit;
				
				var url = "s?method=deptListPage&pageNo=" + pageNo;
				url += "&pageCount="+ pageCount;
				url += "&"+$("form").serialize();
				
				console.info(url);
				//???????????????
				if(!first){
	  				window.location.href = url;
	  				//ajax????????????????????????????????????????????????????????????
				}
			}
		});
	});
	
	function openWin(id){		
		layer.open({
			type: 2,
			title: "????????????",
			area: ['450px', '500px'],
			content: "s?method=deptUpPage&id="+id
		});
	}
	
	function addDept(){		
		layer.open({
			type: 2,
			title: "????????????",
			area: ['450px', '400px'],
			content: "s?method=deptAddPage"
		});
	}
	
</script>
</html>