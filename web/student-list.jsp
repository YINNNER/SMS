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
		
		<script>window.jQuery || document.write('<script src="bootstrap/js/vendor/jquery.min.js"><\/script>')</script>
		<!-- Custom styles for this page -->
		<link href="css/index.css" rel="stylesheet">
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				
				<div class="col-sm-3 col-md-2 sidebar">
					<div class="navbar-header">
						<a class="navbar-brand" id="nav-name" href="index.jsp">学生管理系统</a>
					</div>
					<ul class="nav nav-sidebar">
						<li class="active"><a href="#">学生管理 <span class="sr-only">(current)</span></a></li>
						<li><a href="instituteManagement?param=queryInstitute">教学管理</a></li>
						<li><a href="course-management.jsp">课程管理</a></li>
					</ul>
				</div>
				
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 my-nav-container">
					
					<nav class="col-sm-12 col-md-12 navbar my-top-nav">
						<div class="container-fluid" style="text-align: center">
		  
							<span class="nav navbar-nav navbar-left">
		                <button type="button" class="btn btn-default btn-lg" onclick="location='index.jsp'">
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
							
							<h4 class="nav-title">学生列表</h4>
							
							<span class="nav navbar-nav navbar-right">
		                <button type="button" class="btn btn-default btn-lg" onclick="location='student-info-add.jsp'">
		                  <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
		                </button>
		              </span>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						<%--判断是否删除成功--%>
						<c:if test="${not empty requestScope.flag}">
							<c:if test="${requestScope.flag == true}">
								<script>
                    alert("删除成功！");
										var class_id = "${sessionScope.class_id}";
                    window.location.href='queryStudent?param=querySubmit&class_id='+class_id;
								</script>
							</c:if>
							<c:if test="${requestScope.flag == false}">
								<script>
										alert("删除失败！");
								</script>
							</c:if>
						</c:if>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>学号</th>
										<th>姓名</th>
										<th>性别</th>
										<th>学院</th>
										<th>专业</th>
										<th>班级</th>
										<th>查看</th>
										<th>删除</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.queryResult}" var="student">
											<tr>
												<td>${student.stu_id}</td>
												<td>${student.stu_name}</td>
												<td>${student.stu_sex}</td>
												<td>${student.inst_name}</td>
												<td>${student.maj_name}</td>
												<td>${student.class_name}</td>
												<td><button class="btn btn-default">查看</button></td>
												<td>
													<form action="studentManagement">
														<input type="hidden" name="stu_id" value="${student.stu_id}">
														<input type="hidden" name="class_id" value="${student.class_id}">
														<button class="btn btn-default"
														        type="submit" name="type" value="deleteStudent">删除</button>
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
		<script src="bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
