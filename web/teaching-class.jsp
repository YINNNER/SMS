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
		<script type="text/javascript" src="js/query-student-option.js"></script>
		<script type="text/javascript">
        getInst();
		</script>
		<script>window.jQuery || document.write('<script src="bootstrap/js/vendor/jquery.min.js"><\/script>')</script>
		<script type="text/javascript" src="js/teaching-operation.js"></script>
		<script type="text/javascript">
        $(document).ready(function () {
            var inst_id = "${sessionScope.inst_id}";
            var maj_id = "${sessionScope.maj_id}";
            url = 'classManagement?param=queryClass&inst_id=' + inst_id + '&maj_id=' + maj_id;

            var add_flag = "${requestScope.add_flag}";
            if (add_flag !=="") addOpt(add_flag, url);
            var modify_flag = "${requestScope.modify_flag}";
            if (modify_flag !=="") modifyOpt(modify_flag, url);
            var delete_flag = "${requestScope.delete_flag}";
            if (delete_flag !=="") deleteOpt(delete_flag, url);
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
						<li class="active"><a href="#">教学管理</a></li>
						<li><a href="course-management.jsp">课程管理</a></li>
					</ul>
				</div>
				
				<div class="col-sm-2 col-sm-offset-3 col-md-1 col-md-offset-2 sidebar sub-bar">
					<div class="navbar-header">
						<span class="navbar-brand" id="sub-nav-name" style="font-size: 14px; padding: 5px;"></span>
					</div>
					<ul class="nav nav-sidebar">
						<li><a href="instituteManagement?param=queryInstitute" style="border-top: 2px solid #eee;">学院</a></li>
						<li><a href="teaching-major.jsp">专业</a></li>
						<li class="active"><a href="#">班级</a></li>
					</ul>
				</div>
				
				<div class="col-sm-7 col-sm-offset-5 col-md-9 col-md-offset-3 my-nav-container">
					
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
							
							<h4 class="nav-title">班级管理</h4>
							
							<span class="nav navbar-nav navbar-right">
			                <button type="button" class="btn btn-default btn-lg" onclick="addClass()">
			                  <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
			                </button>
			              </span>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<form class="form-horizontal" action="classManagement">
							<div class="form-group">
								<label for="chooseInstitute" class="col-sm-2 control-label">学院</label>
								<div class="col-sm-3">
									<select class="form-control" id="chooseInstitute" name="inst_id" onchange="getMajor()">

									</select>
								</div>
								
								<label for="chooseMajor" class="col-sm-2 control-label">专业</label>
								<div class="col-sm-3">
									<select class="form-control" id="chooseMajor" name="maj_id">

									</select>
								</div>
								
								<div class="col-sm-2">
									<button type="submit" class="btn btn-default" name="param" value="queryClass">查询</button>
								</div>
							</div>
						</form>
						
						<table class="table table-striped">
							<thead>
								<tr>
									<th>班级编号</th>
									<th>班级名</th>
									<th>学生人数</th>
									<th>修改</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty requestScope.queryResult}">
									<c:forEach items="${requestScope.queryResult}" var="myClass"  varStatus="i">
										<tr>
											<td>${myClass.class_id}</td>
											<td>${myClass.class_name}</td>
											<td>${myClass.stu_num}</td>
											<td><button class="btn btn-default"  onclick="modify(${i.count}, 2)">修改</button></td>
											<td>
												<form action="classManagement">
													<input type="hidden" name="class_id" id="class_id" value="${myClass.class_id}">
													<input type="hidden" name="maj_id" id="maj_id" value="${myClass.maj_id}">
													<input type="hidden" name="inst_id" value="${myClass.inst_id}">
													<button class="btn btn-default" type="submit" name="param" value="deleteClass">删除</button>
												</form>
											</td>
											
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
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
