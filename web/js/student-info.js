$(document).ready(function () {
    var result = urlSearch();
    var stu_id = result["stu_id"];
    var stu_name = decodeURI(result["stu_name"]);  // 使用decodeURI解决中文编码问题
    var a_list = $(".sub-bar .nav-sidebar a");
    for(var i = 0; i < a_list.length; i++) {
        var a = a_list[i];
        if (a.href===location.href + "#") continue;
        var base_url = a.href.split("?")[0];
        var url = base_url + "?stu_id=" + stu_id + "&stu_name=" + stu_name;
        a.href = url;
    }

    $('#sub-nav-name').text(stu_name);
    if ($('#select-class') != null) {
        $('.stu_id').val(stu_id);
    }
});

