<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
				<li><a href="courseSelection?param=queryCourseSelection&stu_id=${sessionScope.stu_id}"
				       style="border-top: 2px solid #eee;">选课</a></li>
				<li><a href="scoreManagement?param=queryScore&stu_id=${sessionScope.stu_id}">成绩</a></li>
				<li><a href="gpaAnalysis?stu_id=${sessionScope.stu_id}">统计</a></li>
				<li class="active"><a href="#">信息</a></li>
			</ul>
		</div>
		
		<div class="col-sm-7 col-sm-offset-5 col-md-9 col-md-offset-3 my-nav-container">
			
			<nav class="col-sm-12 col-md-12 navbar my-top-nav">
				<div class="container-fluid" style="text-align: center">
	  
          <span class="nav navbar-nav navbar-left">
            <button type="button" class="btn btn-default btn-lg" onclick="location='index.jsp'">
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            </button>
          </span>
					
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
						        aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
					
					<h4 class="nav-title">学生信息</h4>
				
				</div>
			</nav>
			
			<div class="col-sm-12 col-md-12 main">
				
				<div class="table-responsive col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3">
					<table class="table table-striped table-bordered">
						<tbody>
							<tr>
								<th>学号</th>
								<td>${requestScope.studentInfo.stu_id}</td>
							</tr>
							<tr>
								<th>姓名</th>
								<td>${requestScope.studentInfo.stu_name}</td>
							</tr>
							<tr>
								<th>性别</th>
								<td>${requestScope.studentInfo.stu_sex}</td>
							</tr>
							<tr>
								<th>学院</th>
								<td>${requestScope.studentInfo.inst_name}</td>
							</tr>
							<tr>
								<th>专业</th>
								<td>${requestScope.studentInfo.maj_name}</td>
							</tr>
							<tr>
								<th>班级</th>
								<td>${requestScope.studentInfo.class_name}</td>
							</tr>
							<tr>
								<th>出生年月</th>
								<td>${requestScope.studentInfo.stu_birth_date}</td>
							</tr>
							<tr>
								<th>籍贯</th>
								<td>${requestScope.studentInfo.stu_birth_place}</td>
							</tr>
							<tr>
								<th>政治面貌</th>
								<td>${requestScope.studentInfo.stu_political}</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2" style="text-align: center;">
					<button class="btn btn-default" onclick="location='student-info-add.jsp?stu_id=${sessionScope.stu_id}'">修改</button>
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
