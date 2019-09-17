function addInst() {
    $('tbody').append('<tr></tr>');
    var tr = $('tbody tr:last');
    var td_id = '<td><input type="text" class="form-control" id="inputId" name="inst_id" placeholder="学院编号"></td>';
    var td_name = '<td><input type="text" class="form-control" id="inputName" name="inst_name" placeholder="学院名"></td>';
    tr.append(td_id);
    tr.append(td_name);
    tr.append('<td>0</td><td>0</td>');
    var td_confirm = '<td><button type="submit" class="btn btn-default" onclick="confirmAddInst()">确定</button></td>';
    tr.append(td_confirm);
}

function confirmAddInst() {
    window.location.href = 'instituteManagement?param=addInstitute&inst_id=' +
        $('#inputId').val() + '&inst_name=' + $('#inputName').val();
}

function addMajor() {
    $('tbody').append('<tr></tr>');
    var tr = $('tbody tr:last');
    var td_id = '<td><input type="text" class="form-control" id="inputId" name="maj_id" placeholder="专业编号"></td>';
    var td_name = '<td><input type="text" class="form-control" id="inputName" name="maj_name" placeholder="专业名"></td>';
    tr.append(td_id);
    tr.append(td_name);
    tr.append('<td>0</td><td>0</td>');
    var td_confirm = '<td><button type="submit" class="btn btn-default" onclick="confirmAddMajor()">确定</button></td>';
    tr.append(td_confirm);
}

function confirmAddMajor() {
    window.location.href = 'majorManagement?param=addMajor&maj_id=' +
        $('#inputId').val() + '&maj_name=' + $('#inputName').val() + '&inst_id=' + $('#chooseInstitute').val();
}

function addClass() {
    $('tbody').append('<tr></tr>');
    var tr = $('tbody tr:last');
    var td_id = '<td><input type="text" class="form-control" id="inputId" name="class_id" placeholder="班级编号"></td>';
    var td_name = '<td><input type="text" class="form-control" id="inputName" name="class_name" placeholder="班级名"></td>';
    tr.append(td_id);
    tr.append(td_name);
    tr.append('<td>0</td>');
    var td_confirm = '<td><button type="submit" class="btn btn-default" onclick="confirmAddClass()">确定</button></td>';
    tr.append(td_confirm);
}

function confirmAddClass() {
    window.location.href = 'classManagement?param=addClass&class_id=' +
        $('#inputId').val() + '&class_name=' + $('#inputName').val() +
        '&inst_id=' + $('#chooseInstitute').val() + '&maj_id=' + $('#chooseMajor').val();
}



/**
 * 点击修改后，表格中对应行的样式改变
 * @param i: 表示点击了第i个tr, i从1开始索引
 * @param area_type: 0表示学院，1表示专业，2表示班级
 */
function modify(i, area_type) {  //
    var modify_tr = $('tbody tr')[i-1];  //需要i-1获得对应的tr
    var old_name = modify_tr.children[1];
    var old_btn = modify_tr.children[4];
    var td_name = $('<td><input type="text" class="form-control" id="inputName" name="inst_name" value=\"' + old_name.textContent + '\"></td>');
    var td_btn = $('<td><button type="submit" class="btn btn-default confirm-btn" onclick="confirmModify(this, ' + area_type + ')">确定</button></td>');
    modify_tr.replaceChild(td_name[0], old_name);
    modify_tr.replaceChild(td_btn[0], old_btn);
}

/**
 * 确认修改后重新发查询请求，刷新页面
 * @param btn: 被点击的按钮
 * @param type: 0表示学院，1表示专业，2表示班级
 */
function confirmModify(btn, type) {
    var modify_tr = btn.parentNode.parentNode;
    var td_id = modify_tr.children[0].textContent;
    var td_name = modify_tr.children[1].children[0].value;
    var url = '';
    switch (type){
        case 0:
            url = 'instituteManagement?param=modifiedInstitute&inst_id=' + td_id + '&inst_name=' + td_name;
            break;
        case 1:
            url = 'majorManagement?param=modifiedMajor&maj_id=' + td_id + '&maj_name=' + td_name
                + '&inst_id=' + $('#chooseInstitute').val();
            break;
        default:
            url = 'classManagement?param=modifiedClass&class_id=' + td_id + '&class_name=' + td_name
                + '&inst_id=' + $('#chooseInstitute').val()  + '&maj_id=' + $('#chooseMajor').val();
            break;

    }
    window.location.href = url;
}

function addOpt(flag, url) {
    if (flag === "true"){
        alert("添加成功！");
    }
    else {
        alert("添加失败！");
    }
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

function deleteOpt(flag, url) {
    if (flag === "true"){
        alert("删除成功！");
    }
    else {
        alert("删除失败！");
    }
    window.location.href = url;
}