<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="A student management system">
		<meta name="author" content="zhengxiaoying">
		
		<title>Student Management System</title>
		
		<!-- Bootstrap core CSS -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Custom styles for this page -->
		<link href="css/index.css" rel="stylesheet">
		
		<!-- Custom scripts for this page -->
		<script>window.jQuery || document.write('<script src="bootstrap/js/vendor/jquery.min.js"><\/script>')</script>
		<script type="text/javascript">
        $(document).ready(function () {
            var flag = "${requestScope.flag}";
            if (flag!==""){
                if (flag === "true"){
                    alert("删除成功！");
                    window.location.href='course-management.jsp';
                    <%--var inst_id = "${sessionScope.inst_id}";--%>
                    <%--var maj_id = "${sessionScope.maj_id}";--%>
                    <%--var requery = function () {--%>
                        <%--var url = 'courseManagement?param=queryCourse&inst_id=' + inst_id + '&maj_id' + maj_id;--%>
                        <%--// 使用get提交，将url和想要传递到后台的参数进行拼接，便于后台获取数据--%>
                        <%--var request = new XMLHttpRequest();  // 新建XMLHttpRequest对象--%>
                        <%--// 发送请求:--%>
                        <%--request.open("GET", url);--%>
                        <%--request.send();--%>
                    <%--}();--%>
                    <%--// window.refresh();--%>
                }
                else {
                    alert("删除失败！");
                }
            }
        });
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				
				<div class="col-sm-3 col-md-2 sidebar">
					<div class="navbar-header">
						<a class="navbar-brand" id="nav-name" href="index.jsp">学生管理系统</a>
					</div>
					<ul class="nav nav-sidebar">
						<li><a href="index.jsp">学生管理</a></li>
						<li><a href="instituteManagement?param=queryInstitute">教学管理</a></li>
						<li class="active"><a href="#">课程管理 <span class="sr-only">(current)</span></a></li>
					</ul>
				</div>
				
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 my-nav-container">
					
					<nav class="col-sm-12 col-md-12 navbar my-top-nav">
						<div class="container-fluid" style="text-align: center">
		  
		              <span class="nav navbar-nav navbar-left">
		                <button type="button" class="btn btn-default btn-lg" onclick="location='course-management.jsp'">
		                  <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		                </button>
		              </span >
							
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
									<span class="sr-only">Toggle navigation</span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
								</button>
							</div>
							
							<h4 class="nav-title">课程列表</h4>
							
							<span class="nav navbar-nav navbar-right">
		                <button type="button" class="btn btn-default btn-lg" onclick="location='course-info.jsp'">
		                  <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
		                </button>
		              </span>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>课程号</th>
										<th>课程名</th>
										<th>教师名</th>
										<th>学分</th>
										<th>上课地点</th>
										<th>上课时间</th>
										<th>学年</th>
										<th>学期</th>
										<th>修改</th>
										<th>删除</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.queryResult}" var="course">
										<tr>
											<td>${course.coz_id}</td>
											<td>${course.coz_name}</td>
											<td>${course.tch_name}</td>
											<td>${course.coz_credit}</td>
											<td>${course.coz_place}</td>
											<td>${course.coz_time}</td>
											<td>${course.coz_year}</td>
											<td>${course.coz_semester}</td>
											<td><button class="btn btn-default" onclick="location='course-info.jsp?coz_id=${course.coz_id}'">修改</button></td>
											<td>
												<form action="courseManagement">
													<input type="hidden" name="coz_id" value="${course.coz_id}">
													<input type="hidden" name="inst_id" value="${course.inst_id}">
													<input type="hidden" name="maj_id" value="${course.maj_id}">
													<button class="btn btn-default"
													        type="submit" name="param" value="deleteCourse">删除</button>
												</form>
											</td>
											
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				
				</div>
			</div>
		</div>
	
		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script>window.jQuery || document.write('<script src="bootstrap/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
