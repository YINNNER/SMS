function deleteOpt(id, class_id) {
    var url = 'studentManagement?type=deleteStudent&stu_id=' + id;
    // 使用get提交，将url和想要传递到后台的参数进行拼接，便于后台获取数据
    var request = new XMLHttpRequest();  // 新建XMLHttpRequest对象
    // 发送请求:
    request.open("GET", url);
    request.send();

    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            // 当Ajax对象状态为4，并且status为200时，responseText接收数据
            var data = request.responseText;
            //将接收到的字符串转换成json格式
            var flag = data;
            if (flag) {  // 删除成功
               alert("删除成功！");
               var requery = function (class_id) {
                   var url = 'studentManagement?queryStudent?param=querySubmit&class_id=' + class_id;
                   // 使用get提交，将url和想要传递到后台的参数进行拼接，便于后台获取数据
                   var request = new XMLHttpRequest();  // 新建XMLHttpRequest对象
                   // 发送请求:
                   request.open("GET", url);
                   request.send();
               }();
            }
            else {
                alert("删除失败！");
            }

        }
    }
}