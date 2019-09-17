package dao;

import utils.DBUtils;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private DBUtils dBUtils;

    public StudentDAO() {
        dBUtils = new DBUtils();
    }

    /**
     * 获得所有学生信息
     * @return List<Student>
     */
    public List<Student> queryAllStuInfo(){
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = dBUtils.querySubsetRef("student_table", new ArrayList<>(), new ArrayList<>(), Student.class);
            System.out.print(studentList);
            System.out.println("查询所有学生信息成功");
        } catch (Exception e) {
            System.out.println("查询所有学生信息失败");
            e.printStackTrace();
        }

        return studentList;
    }

    /**
     * 通过id查找学生
     * @param id：id号
     * @return 学生对象
     */
    public Student queryStuInfoById(int id){
        Student student = new Student();
        List<Object> params = new ArrayList<Object>();
        params.add("stu_id");
        List<Object> values = new ArrayList<>();
        values.add(id);
        try {
            student = dBUtils.queryOneRef("student_table", params, values, Student.class);
            System.out.print(student);
            System.out.println(id + " 学生信息获取成功");
        } catch (Exception e) {
            System.out.println(id + " 学生信息获取失败");
            e.printStackTrace();
        }

        return student;
    }


    /**
     * 查询指定班级的学生
     * @param class_id：班级id号
     * @return list 学生列表
     */
    public List<Student> queryStuInfoByClass(int class_id){
        List<Student> studentList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        params.add("class_id");

        List<Object> values = new ArrayList<>();
        values.add(class_id);
        try {
            studentList = dBUtils.querySubsetRef("student_table", params, values, Student.class);
            System.out.print(studentList);
            System.out.println(class_id + " 查询指定班级的学生成功");
        } catch (Exception e) {
            System.out.println(class_id + " 查询指定班级的学生失败");
            e.printStackTrace();
        }

        return studentList;
    }

    /**
     * 查询指定专业的学生
     * @param maj_id：专业id号
     * @return list 学生列表
     */
    public List<Student> queryStuInfoByMaj(int maj_id){
        List<Student> studentList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        params.add("maj_id");

        List<Object> values = new ArrayList<>();
        values.add(maj_id);
        try {
            studentList = dBUtils.querySubsetRef("student_table", params, values, Student.class);
            System.out.print(studentList);
            System.out.println(maj_id + " 查询指定班级的学生成功");
        } catch (Exception e) {
            System.out.println(maj_id + " 查询指定班级的学生失败");
            e.printStackTrace();
        }

        return studentList;
    }

    /**
     * 查询指定学院的学生
     * @param inst_id：学院id号
     * @return list 学生列表
     */
    public List<Student> queryStuInfoByInst(int inst_id){
        List<Student> studentList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        params.add("inst_id");

        List<Object> values = new ArrayList<>();
        values.add(inst_id);
        try {
            studentList = dBUtils.querySubsetRef("student_table", params, values, Student.class);
            System.out.print(studentList);
            System.out.println(" 查询指定班级的学生成功");
        } catch (Exception e) {
            System.out.println(" 查询指定班级的学生失败");
            e.printStackTrace();
        }

        return studentList;
    }

    /**
     * 自定义查找满足条件的学生
     * @param params：条件名
     * @param values：值
     * @return list 学生列表
     */
    public List<Student> queryStuInfo(List<Object> params, List<Object> values){
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = dBUtils.querySubsetRef("student_table", params, values, Student.class);
            System.out.print(studentList);
            System.out.println("自定义查询学生信息成功");
        } catch (Exception e) {
            System.out.println("自定义查询学生信息失败");
            e.printStackTrace();
        }

        return studentList;
    }

    /**
     * 判断是否已经存在学号相同的学生
     * @param student: 学生对象
     * @return flag
     */
    public boolean checkStuExist(Student student) {
        boolean flag = true;
        int id = student.getStu_id();
        Student stu = queryStuInfoById(id);
        if (stu == null)
            flag = false;
        return flag;
    }

    /**
     * 添加学生信息
     * @param student: 学生对象
     * @return flag
     */
    public boolean addStuInfo(Student student){
        boolean flag = false;
        if (checkStuExist(student)) {
            System.out.println("存在重复的id");
            return false;
        }
        List<Object> params = new ArrayList<Object>();
        params.add("stu_id");
        params.add("inst_id");
        params.add("maj_id");
        params.add("class_id");
        params.add("stu_name");
        params.add("stu_sex");
        params.add("stu_birth_date");
        params.add("stu_birth_place");
        params.add("stu_political");

        List<Object> values = new ArrayList<Object>();
        values.add(student.getStu_id());
        values.add(student.getInst_id());
        values.add(student.getMaj_id());
        values.add(student.getClass_id());
        values.add(student.getStu_name());
        values.add(student.getStu_sex());
        values.add(student.getStu_birth_date());
        values.add(student.getStu_birth_place());
        values.add(student.getStu_political());

        if (dBUtils.add("student_table", params, values)) {
            System.out.println("添加成功");
            flag = true;
        }
        else
            System.out.println("添加失败");

        return flag;
    }

    /**
     * 通过id删除学生信息
     * @param id：学生id号
     * @return flag
     */
    public boolean deleteStuInfoById(int id) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("stu_id");

        List<Object> values = new ArrayList<Object>();
        values.add(id);

        if (dBUtils.delete("student_table", params, values)) {
            System.out.println("删除成功");
            flag = true;
        }
        else
            System.out.println("删除失败");
        return flag;
    }

    /**
     * 根据id修改学生信息
     * @param student: 学生对象
     * @return flag
     */
    public boolean modifyStuInfoById(Student student) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("inst_id");
        params.add("maj_id");
        params.add("class_id");
        params.add("stu_name");
        params.add("stu_sex");
        params.add("stu_birth_date");
        params.add("stu_birth_place");
        params.add("stu_political");
        params.add("stu_id");

        List<Object> values = new ArrayList<Object>();
        values.add(student.getInst_id());
        values.add(student.getMaj_id());
        values.add(student.getClass_id());
        values.add(student.getStu_name());
        values.add(student.getStu_sex());
        values.add(student.getStu_birth_date());
        values.add(student.getStu_birth_place());
        values.add(student.getStu_political());
        values.add(student.getStu_id());

        if (dBUtils.update("student_table", params, values)) {
            System.out.println("修改成功");
            flag = true;
        }
        else
            System.out.println("修改失败");
        return flag;
    }

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.queryAllStuInfo();
        studentDAO.queryStuInfoById(1);
        Student student = new Student(3);
        System.out.println(studentDAO.checkStuExist(student));

    }
}
