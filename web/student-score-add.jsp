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
						<li class="active"><a href="#">成绩</a></li>
						<li><a href="gpaAnalysis?stu_id=${sessionScope.stu_id}">统计</a></li>
						<li><a href="studentManagement?type=querySingleStudent&stu_id=${sessionScope.stu_id}">信息</a></li>
					</ul>
				</div>
				
				<div class="col-sm-7 col-sm-offset-5 col-md-9 col-md-offset-3 my-nav-container">
					
					<nav class="col-sm-12 col-md-12 navbar my-top-nav">
						<div class="container-fluid" style="text-align: center">
							
							<span class="nav navbar-nav navbar-left">
                <button type="button" class="btn btn-default btn-lg" onclick="location='scoreManagement?' +
				                'param=queryScore&stu_id=${sessionScope.stu_id}'">
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
							
							<h4 class="nav-title">成绩列表</h4>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<%--判断是否添加成功--%>
						<c:if test="${not empty requestScope.flag}">
							<c:if test="${requestScope.flag == true}">
								<script>
                    alert("添加成功！");
                    window.location.href='scoreManagement?param=queryScore&stu_id=${sessionScope.stu_id}';
								</script>
							</c:if>
							<c:if test="${requestScope.flag == false}">
								<script>
                    alert("添加失败！");
								</script>
							</c:if>
						</c:if>
						
						<h4 class="sub-header" style="margin-top: 15%;">选择课程</h4>
						<form class="form-horizontal" action="scoreManagement">
							<div class="form-group">
								<label for="chooseCourse" class="col-sm-2 col-sm-offset-1 control-label">课程名</label>
								<div class="col-sm-6">
									<select class="form-control" id="chooseCourse" name="coz_id">
										<option>6</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="inputScore" class="col-sm-2 col-sm-offset-1 control-label">成绩</label>
								<div class="col-sm-6">
									<input type="number" class="form-control" id="inputScore" name="score" >
								</div>
							</div>
							<div class="form-group">
								<div style="text-align: center;">
									<input type="hidden" name="stu_id" class="stu_id" value="${sessionScope.stu_id}">
									<button type="submit" class="btn btn-default" name="param" value="addScore">确定</button>
								</div>
							</div>
						</form>
						
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
