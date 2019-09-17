function getInst() {
    //使用get提交，将url和想要传递到后台的参数进行拼接，便于后台获取数据
    var request = new XMLHttpRequest(); // 新建XMLHttpRequest对象
    // 发送请求:
    request.open("GET", "queryStudent?param=queryInst");
    request.send();
    // ajax.open("GET", "servlet/queryStudent?param=queryInst", true);
    //监听Ajax的状态变化
    var current_inst_id;
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            //当Ajax对象状态为4，并且status为200时，responseText接收数据
            var data = request.responseText;
            //将接收到的字符串转换成json格式
            var instList = JSON.parse(data);
            current_inst_id = instList[0].inst_id;
            //循环得到的json数组，将值添加到select中
            for (var i = 0; i < instList.length; i++) {
                //创建一个option
                var opt = document.createElement("option");
                var select = document.getElementById("chooseInstitute");
                //给option的value属性和具体内容赋值
                opt.value = instList[i].inst_id;
                opt.innerHTML = instList[i].inst_name;
                //将option添加到select中
                select.append(opt);
            }
            getMajor();
        }
    }

}

function getMajor() {
    var select_inst = document.getElementById("chooseInstitute");
    var inst_id = select_inst.options[select_inst.selectedIndex].value; //获取option的value
    //使用get提交，将url和想要传递到后台的参数进行拼接，便于后台获取数据
    var request = new XMLHttpRequest(); // 新建XMLHttpRequest对象
    // 发送请求:
    request.open("GET", "queryStudent?param=queryMaj&inst_id=" + inst_id);
    request.send();
    //监听Ajax的状态变化
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            document.getElementById("chooseMajor").innerHTML = "";
            //当Ajax对象状态为4，并且status为200时，responseText接收数据
            var data = request.responseText;
            //将接收到的字符串转换成json格式
            var majList = JSON.parse(data);
            //循环得到的json数组，将值添加到select中
            for (var i = 0; i < majList.length; i++) {
                //创建一个option
                var opt = document.createElement("option");
                var select = document.getElementById("chooseMajor");
                //给option的value属性和具体内容赋值
                opt.value = majList[i].maj_id;
                opt.innerHTML = majList[i].maj_name;
                //将option添加到select中
                select.append(opt);
            }
            getClass();
        }
    }
}

function getClass() {
    var select_maj = document.getElementById("chooseMajor");
    var maj_id = select_maj.options[select_maj.selectedIndex].value; //获取option的value
    //使用get提交，将url和想要传递到后台的参数进行拼接，便于后台获取数据
    var request = new XMLHttpRequest(); // 新建XMLHttpRequest对象
    // 发送请求:
    request.open("GET", "queryStudent?param=queryClass&maj_id=" + maj_id);
    request.send();
    //监听Ajax的状态变化
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {
            var chooseClass = document.getElementById("chooseClass");
            if (chooseClass != null)
                chooseClass.innerHTML = "";
            //当Ajax对象状态为4，并且status为200时，responseText接收数据
            var data = request.responseText;
            //将接收到的字符串转换成json格式
            var classList = JSON.parse(data);
            //循环得到的json数组，将值添加到select中
            for (var i = 0; i < classList.length; i++) {
                //创建一个option
                var opt = document.createElement("option");
                var select = document.getElementById("chooseClass");
                //给option的value属性和具体内容赋值
                opt.value = classList[i].class_id;
                opt.innerHTML = classList[i].class_name;
                //将option添加到select中
                select.append(opt);
            }
        }
    }
}

