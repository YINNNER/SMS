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
						
            // 确认修改是否成功
            var url = 'scoreManagement?param=queryScore&stu_id=${sessionScope.stu_id}';
            var modify_flag = "${requestScope.modify_flag}";
            if (modify_flag !=="") modifyOpt(modify_flag, url);
        });

        function modify(i) {  //
            var modify_tr = $('tbody tr')[i-1];  //需要i-1获得对应的tr
            var old_score = modify_tr.children[5];
            var old_btn = modify_tr.children[6];
            var td_score = $('<td><input type="text" class="form-control" id="inputScore" name="score" value=\"' + old_score.textContent + '\"></td>');
            var td_btn = $('<td><button type="submit" class="btn btn-default confirm-btn" onclick="confirmModify(this)">确定</button></td>');
            modify_tr.replaceChild(td_score[0], old_score);
            modify_tr.replaceChild(td_btn[0], old_btn);
        }

        function confirmModify(btn) {
            var modify_tr = btn.parentNode.parentNode;
            var td_id = modify_tr.children[0].textContent;
            var td_score = modify_tr.children[5].children[0].value;
            var url = 'scoreManagement?param=modifyScore&stu_id=${sessionScope.stu_id}&coz_id=' + td_id + '&score=' + td_score;
            window.location.href = url;
        }

        function modifyOpt(flag, url) {
            if (flag === "true"){
                alert("修改成功！");
            }
            else {
                alert("修改失败！");
            }
            window.location.href = url;
        }
        
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
							
							<h4 class="nav-title">成绩列表</h4>
							
							<span class="nav navbar-nav navbar-right">
                <button type="button" class="btn btn-default btn-lg" onclick="location='student-score-add.jsp'">
                  <span class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
                </button>
							</span>
						
						</div>
					</nav>
					
					<div class="col-sm-12 col-md-12 main">
						
						<%--判断是否删除成功--%>
						<c:if test="${not empty requestScope.delete_flag}">
							<c:if test="${requestScope.delete_flag == true}">
								<script>
                    alert("删除成功！");
                    window.location.href='scoreManagement?param=queryScore&stu_id=${sessionScope.stu_id}';
								</script>
							</c:if>
							<c:if test="${requestScope.delete_flag == false}">
								<script>
                    alert("删除失败！");
								</script>
							</c:if>
						</c:if>
						
						<form class="form-horizontal" action="scoreManagement">
							<div class="form-group">
								<label for="chooseYear" class="col-sm-2 control-label">学年</label>
								<div class="col-sm-3">
									<input type="number" class="form-control" id="chooseYear" name="coz_year" value="${requestScope.coz_year}">
								</div>
								
								<label for="chooseTerm" class="col-sm-2 control-label">学期</label>
								<div class="col-sm-3">
									<select class="form-control" id="chooseTerm" name="coz_semester">
										<option>1</option>
										<option>2</option>
										<option>3</option>
									</select>
									<%--查询后选择对应的学期选项--%>
									<script>
                      var i="${requestScope.coz_semester}";
                      if (i !== "") {
                          var opt_list = document.getElementById('chooseTerm').children;
                          opt_list[i-1].selected = true;
                      }
									</script>
								</div>
								
								<div class="col-sm-2">
									<input type="hidden" name="stu_id" value="${sessionScope.stu_id}">
									<button type="submit" class="btn btn-default" name="param" value="queryScoreBySemester">查询</button>
								</div>
							</div>
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>课程号</th>
										<th>课程名</th>
										<th>教师名</th>
										<th>授课学院</th>
										<th>学分</th>
										<th>成绩</th>
										<th>修改</th>
										<th>删除</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.queryResult}" var="item" varStatus="i">
										<tr>
											<td>${item.coz_id}</td>
											<td>${item.coz_name}</td>
											<td>${item.tch_name}</td>
											<td>${item.inst_name}</td>
											<td>${item.coz_credit}</td>
											<td>${item.score}</td>
											<td><button class="btn btn-default" onclick="modify(${i.count})">更新</button></td>
											<td>
												<form action="scoreManagement">
													<input type="hidden" name="coz_id" value="${item.coz_id}">
													<input type="hidden" name="stu_id" class="stu_id" value="${sessionScope.stu_id}">
													<button class="btn btn-default"
													        type="submit" name="param" value="deleteScore">删除</button>
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
