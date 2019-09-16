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
		<script type="text/javascript" src="js/query-student-option.js"></script>
		<script type="text/javascript">
        getInst();
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
						<li><a href="teaching-institute.jsp">教学管理</a></li>
						<li class="active"><a href="#">课程管理 <span class="sr-only">(current)</span></a></li>
					</ul>
				</div>
				
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 my-nav-container">
					
					<nav class="col-sm-12 col-md-12 navbar my-top-nav">
						<div class="container-fluid" style="text-align: center">
							
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
									<span class="sr-only">Toggle navigation</span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
								</button>
							</div>
							
							<h4 class="nav-title">课程查询</h4>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<h4 class="sub-header">选择开设院系</h4>
						
						<form class="form-horizontal" action="queryCourse">
							<div class="form-group">
								<label for="chooseInstitute" class="col-sm-2 col-sm-offset-1 control-label">学院</label>
								<div class="col-sm-6">
									<select class="form-control" id="chooseInstitute" name="inst_id" onchange="getMajor()">

									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label for="chooseMajor" class="col-sm-2 col-sm-offset-1 control-label">专业</label>
								<div class="col-sm-6">
									<select class="form-control" id="chooseMajor" name="maj_id">

									</select>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-5 col-sm-2">
									<button type="submit" class="btn btn-default" name="param" value="queryCourse">查询</button>
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
