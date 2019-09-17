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
		<script type="text/javascript" src="js/util.js"></script>
		<script type="text/javascript">
        $(document).ready(function () {
            // var result = urlSearch();
            var stu_id = "${requestScope.stu_id}";
            var stu_name = decodeURI("${sessionScope.stu_name}");  // 使用decodeURI解决中文编码问题
            // var a_list = $(".sub-bar .nav-sidebar a");
            // for(var i = 0; i < a_list.length; i++) {
            //     var a = a_list[i];
            //     if (a.href===location.href + "#") continue;
            //     var base_url = a.href.split("?")[0];
            //     var url = base_url + "?stu_id=" + stu_id + "&stu_name=" + stu_name;
            //     a.href = url;
            // }

            $('#sub-nav-name').text(stu_name);
            // if ($('#select-class') != null) {
            //     $('.stu_id').val(stu_id);
            // }
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
						<li><a href="student-selection.jsp" style="border-top: 2px solid #eee;">选课</a></li>
						<li class="active"><a href="#">成绩</a></li>
						<li><a href="student-statistic.jsp">统计</a></li>
						<li><a href="student-info.jsp">信息</a></li>
					</ul>
				</div>
				
				<div class="col-sm-7 col-sm-offset-5 col-md-9 col-md-offset-3 my-nav-container">
					
					<nav class="col-sm-12 col-md-12 navbar my-top-nav">
						<div class="container-fluid" style="text-align: center">、
							
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
									<span class="sr-only">Toggle navigation</span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
								</button>
							</div>
							
							<h4 class="nav-title">成绩列表</h4>
							
							<span class="nav navbar-nav navbar-right">
	                <button type="button" class="btn btn-default btn-lg">
	                  <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
	                </button>
							</span>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<form class="form-horizontal" action="scoreManagement">
							<div class="form-group">
								<label for="chooseYear" class="col-sm-2 control-label">学年</label>
								<div class="col-sm-3">
									<input type="number" class="form-control" id="chooseYear" name="coz_year">
								</div>
								
								<label for="chooseTerm" class="col-sm-2 control-label">学期</label>
								<div class="col-sm-3">
									<select class="form-control" id="chooseTerm" name="coz_semester">
										<option>1</option>
										<option>2</option>
										<option>3</option>
									</select>
								</div>
								
								<div class="col-sm-2">
									<input type="hidden" name="stu_id" value="${sessionScope.stu_id}">
									<button type="submit" class="btn btn-default" name="param" value="queryScoreBySemester">查询</button>
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
