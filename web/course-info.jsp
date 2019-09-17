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
		<script type="text/javascript">
        $(document).ready(function () {
            var coz_id = "${requestScope.coz_id}";
            if(coz_id!==""){
            
            }
            
            var add_flag = "${requestScope.add_flag}";
            if (add_flag!==""){
                if (add_flag === "true"){
                    alert("添加成功！");
                    window.location.href='course-management.jsp';
                }
                else {
                    alert("添加失败！");
                }
            }

            var modify_flag = "${requestScope.modify_flag}";
            if (modify_flag!==""){
                if (modify_flag === "true"){
                    alert("修改成功！");
                    window.location.href='course-management.jsp';
                }
                else {
                    alert("修改失败！");
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
							
							<h4 class="nav-title">课程信息</h4>
							
							<span class="nav navbar-nav navbar-right">
	                <button type="button" class="btn btn-default btn-lg">
	                  <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
	                </button>
	              </span>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<%--&lt;%&ndash;判断是否添加成功&ndash;%&gt;--%>
						<%--<c:if test="${not empty requestScope.flag}">--%>
							<%--<c:if test="${requestScope.flag == true}">--%>
								<%--<script>--%>
                    <%--alert("添加成功！");--%>
                    <%--window.location.href='index.jsp';--%>
								<%--</script>--%>
							<%--</c:if>--%>
							<%--<c:if test="${requestScope.flag == false}">--%>
								<%--<script>--%>
                    <%--alert("添加失败！");--%>
								<%--</script>--%>
							<%--</c:if>--%>
						<%--</c:if>--%>
						
						<form class="form-horizontal" action="courseManagement">
							
							<div class="form-group">
								<label for="inputId" class="col-sm-2 col-sm-offset-1 control-label">课程号</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputId" name="coz_id" placeholder="课程号">
								</div>
							</div>
							<div class="form-group">
								<label for="inputName" class="col-sm-2 col-sm-offset-1 control-label">课程名</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputName" name="coz_name" placeholder="课程名">
								</div>
							</div>
							<div class="form-group">
								<label for="inputCredit" class="col-sm-2 col-sm-offset-1 control-label">学分</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputCredit" name="coz_credit" placeholder="学分">
								</div>
							</div>
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
								<label for="chooseTeacher" class="col-sm-2 col-sm-offset-1 control-label">老师</label>
								<div class="col-sm-6">
									<select class="form-control" id="chooseTeacher" name="tch_id">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPlace" class="col-sm-2 col-sm-offset-1 control-label">上课地点</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputPlace" name="coz_place" placeholder="上课地点">
								</div>
							</div>
							<div class="form-group">
								<label for="inputTime" class="col-sm-2 col-sm-offset-1 control-label">上课时间</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="inputTime" name="coz_time" placeholder="上课时间">
								</div>
							</div>
							<div class="form-group">
								<label for="inputYear" class="col-sm-2 col-sm-offset-1 control-label">学年</label>
								<div class="col-sm-6">
									<input type="number" class="form-control" id="inputYear" name="coz_year"  placeholder="学年">
								</div>
							</div>
							<div class="form-group">
								<label for="chooseSemester" class="col-sm-2 col-sm-offset-1 control-label">学期</label>
								<div class="col-sm-6">
									<select class="form-control" id="chooseSemester" name="coz_semester">
										<option>1</option>
										<option>2</option>
										<option>3</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-5 col-sm-2">
									<c:if test="${not empty requestScope.type}">
										<button type="submit" class="btn btn-default" name="param" value="modifiedCourse">确定</button>
									</c:if>
									<c:if test="${empty requestScope.type}">
										<button type="submit" class="btn btn-default" name="param" value="addCourse">确定</button>
									</c:if>
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
		<script src="bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
