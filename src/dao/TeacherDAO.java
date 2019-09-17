package dao;

import dbutils.DBUtils;
import entity.Student;
import entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private DBUtils dBUtils;

    public TeacherDAO() {
        dBUtils = new DBUtils();
    }

    /**
     * 通过id查找老师
     * @param id：id号
     * @return 老师对象
     */
    public Teacher queryTchInfoById(int id){
        Teacher teacher = new Teacher();
        List<Object> params = new ArrayList<Object>();
        params.add("tch_id");
        List<Object> values = new ArrayList<>();
        values.add(id);
        try {
            teacher = dBUtils.queryOneRef("teacher_table", params, values, Teacher.class);
            System.out.print(teacher);
            System.out.println(id + " 通过id查找老师成功");
        } catch (Exception e) {
            System.out.println(id + " 通过id查找老师失败");
            e.printStackTrace();
        }

        return teacher;
    }

    /**
     * 查找所有老师
     * @return List<Teacher>
     */
    public List<Teacher> queryAllTch(){
        List<Teacher> teacherList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        List<Object> values = new ArrayList<>();
        try {
            teacherList = dBUtils.querySubsetRef("teacher_table", params, values, Teacher.class);
            System.out.print(teacherList);
            System.out.println("查找所有老师老师成功");
        } catch (Exception e) {
            System.out.println("查找所有老师失败");
            e.printStackTrace();
        }
        return teacherList;
    }
}
