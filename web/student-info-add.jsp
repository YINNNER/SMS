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
						<li><a href="queryInstitute?param=queryInstitute">教学管理</a></li>
						<li><a href="lesson-management.jsp">课程管理</a></li>
					</ul>
				</div>
				
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 my-nav-container">
					
					<nav class="col-sm-12 col-md-12 navbar my-top-nav">
						<div class="container-fluid" style="text-align: center">
		  
							<span class="nav navbar-nav navbar-left">
		                <button type="button" class="btn btn-default btn-lg"  onclick="history.back()">
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
							
							<h4 class="nav-title">添加学生信息</h4>
							
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<form class="form-horizontal" action="studentManagement">
							
							<div class="form-group">
								<label for="inputId" class="col-sm-2 col-sm-offset-1 control-label">学号</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputId" name="stu_id" placeholder="学号">
								</div>
							</div>
							<div class="form-group">
								<label for="inputName" class="col-sm-2 col-sm-offset-1 control-label">姓名</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputName" name="stu_name" placeholder="姓名">
								</div>
							</div>
							<div class="form-group">
								<label for="inputSex" class="col-sm-2 col-sm-offset-1 control-label">性别</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputSex" name="stu_sex" placeholder="性别">
								</div>
							</div>
							<div class="form-group">
								<label for="inputInstitute" class="col-sm-2 col-sm-offset-1 control-label">学院</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputInstitute" name="inst_id"  placeholder="学院">
								</div>
							</div>
							<div class="form-group">
								<label for="inputMajor" class="col-sm-2 col-sm-offset-1 control-label">专业</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputMajor" name="maj_id"  placeholder="专业">
								</div>
							</div>
							<div class="form-group">
								
								<label for="inputClass" class="col-sm-2 col-sm-offset-1 control-label">班级</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputClass" name="class_id"  placeholder="班级">
								</div>
							</div>
							<div class="form-group">
								<label for="inputBirth" class="col-sm-2 col-sm-offset-1 control-label">出生年月</label>
								<div class="col-sm-6">
									<input type="date" class="form-control" id="inputBirth" name="stu_birth_date"  placeholder="年/月/日">
								</div>
							</div>
							<div class="form-group">
								<label for="inputBirthPlace" class="col-sm-2 col-sm-offset-1 control-label">籍贯</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputBirthPlace" name="stu_birth_place"  placeholder="籍贯">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPolitical" class="col-sm-2 col-sm-offset-1 control-label">政治面貌</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputPolitical" name="stu_political" placeholder="政治面貌">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-5 col-sm-2">
									<button type="submit" class="btn btn-default" name="type" value="addStudent">确定</button>
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
