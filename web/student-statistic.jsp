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
		<link href="css/student-statistic.css" rel="stylesheet">
		
		<!-- Custom scripts for this page -->
		<script>window.jQuery || document.write('<script src="bootstrap/js/vendor/jquery.min.js"><\/script>')</script>
		<script type="text/javascript">
        $(document).ready(function () {
            var stu_name = decodeURI("${sessionScope.stu_name}");  // 使用decodeURI解决中文编码问题
            $('#sub-nav-name').text(stu_name);
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
						<li class="active"><a href="#">学生管理 <span class="sr-only">(current)</span></a></li>
						<li><a href="instituteManagement?param=queryInstitute">教学管理</a></li>
						<li><a href="course-management.jsp">课程管理</a></li>
					</ul>
				</div>
				
				<div class="col-sm-2 col-sm-offset-3 col-md-1 col-md-offset-2 sidebar sub-bar">
					<div class="navbar-header">
						<span class="navbar-brand" id="sub-nav-name" style="font-size: 14px; padding: 5px;"></span>
					</div>
					<ul class="nav nav-sidebar">
						<li><a href="courseSelection?param=queryCourseSelection&stu_id=${sessionScope.stu_id}" style="border-top: 2px solid #eee;">选课</a></li>
						<li><a href="scoreManagement?param=queryScore&stu_id=${sessionScope.stu_id}">成绩</a></li>
						<li class="active"><a href="#">统计</a></li>
						<li><a href="studentManagement?type=querySingleStudent&stu_id=${sessionScope.stu_id}">信息</a></li>
					</ul>
				</div>
				
				<div class="col-sm-7 col-sm-offset-5 col-md-9 col-md-offset-3 my-nav-container">
					
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
							
							<h4 class="nav-title">数据统计</h4>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<h2 class="sub-header">学分和GPA统计</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>学年</th>
										<th>学期</th>
										<th>学分</th>
										<th>GPA</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.analysisResult}" var="item">
										<tr>
											<td>${item.year}</td>
											<td>${item.semester}</td>
											<td>${item.credit}</td>
											<td>${item.gpa}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						
						<div id="sum-area">
							<span>
								<label>总学分</label>
								<label>${requestScope.creditSum}</label>
							</span>
							<span>
								<label>总GPA</label>
								<label>${requestScope.gpaSum}</label>
							</span>
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
