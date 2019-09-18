function getAllCourseList() {
    //使用get提交，将url和想要传递到后台的参数进行拼接，便于后台获取数据
    var request = new XMLHttpRequest(); // 新建XMLHttpRequest对象
    // 发送请求:
    request.open("GET", "queryCourse?param=queryAllCourse");
    request.send();
    //监听Ajax的状态变化
    var current_inst_id;
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            var chooseCourse = document.getElementById("chooseCourse");
            if (chooseCourse != null)
                chooseCourse.innerHTML = "";
            //当Ajax对象状态为4，并且status为200时，responseText接收数据
            var data = request.responseText;
            //将接收到的字符串转换成json格式
            var courseList = JSON.parse(data);
            //循环得到的json数组，将值添加到select中
            for (var i = 0; i < courseList.length; i++) {
                //创建一个option
                var opt = document.createElement("option");
                var select = document.getElementById("chooseCourse");
                //给option的value属性和具体内容赋值
                opt.value = courseList[i].coz_id;
                opt.innerHTML = courseList[i].coz_name;
                //将option添加到select中
                select.append(opt);
            }
        }
    }

}

function getAllCourseListWithoutScore(stu_id) {
    //使用get提交，将url和想要传递到后台的参数进行拼接，便于后台获取数据
    var request = new XMLHttpRequest(); // 新建XMLHttpRequest对象
    // 发送请求:
    request.open("GET", "queryCourse?param=queryCourseWithoutScore&stu_id="+stu_id);
    request.send();
    //监听Ajax的状态变化
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            var chooseCourse = document.getElementById("chooseCourse");
            if (chooseCourse != null)
                chooseCourse.innerHTML = "";
            //当Ajax对象状态为4，并且status为200时，responseText接收数据
            var data = request.responseText;
            //将接收到的字符串转换成json格式
            var courseList = JSON.parse(data);
            //循环得到的json数组，将值添加到select中
            for (var i = 0; i < courseList.length; i++) {
                //创建一个option
                var opt = document.createElement("option");
                var select = document.getElementById("chooseCourse");
                //给option的value属性和具体内容赋值
                opt.value = courseList[i].coz_id;
                opt.innerHTML = courseList[i].coz_name;
                //将option添加到select中
                select.append(opt);
            }
        }
    }

}