package dao;

import dbutils.DBUtils;
import entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private DBUtils dBUtils;

    public CourseDAO() {
        dBUtils = new DBUtils();
    }

    /**
     * 获得某学院专业所有课程信息
     * @param inst_id：学院id
     * @param maj_id：专业id
     * @return List<Course>
     */
    public List<Course> queryAllCourseInfoByInstMajId(int inst_id, int maj_id){
        List<Course> courseList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add("inst_id");
        params.add("maj_id");
        List<Object> values = new ArrayList<>();
        values.add(inst_id);
        values.add(maj_id);
        try {
            courseList = dBUtils.querySubsetRef("course_table", params, values, Course.class);
            System.out.print(courseList);
            System.out.println("获得某学生所有课程信息成功");
        } catch (Exception e) {
            System.out.println("获得某学生所有课程信息失败");
            e.printStackTrace();
        }

        return courseList;
    }

    /**
     * 获得id对应的课程信息
     * @param id：课程id
     * @return Course
     */
    public Course queryCourseInfoByCourseId(int id){
        Course course = new Course();

        List<Object> params = new ArrayList<>();
        params.add("coz_id");
        List<Object> values = new ArrayList<>();
        values.add(id);
        try {
            course = dBUtils.queryOneRef("course_table", params, values, Course.class);
            System.out.print(course);
            System.out.println("获得id对应的课程信息成功");
        } catch (Exception e) {
            System.out.println("获得id对应的课程信息失败");
            e.printStackTrace();
        }

        return course;
    }

    /**
     * 获得某学生某学期的课程信息
     * @param stu_id：学院id
     * @param year：学年
     * @param semester：学期
     * @return List<Course>
     */
    public List<Course> queryAllCourseInfoByInstMajId(int stu_id, int year, int semester){
        List<Course> courseList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add("stu_id");
        params.add("year");
        params.add("semester");
        List<Object> values = new ArrayList<>();
        values.add(stu_id);
        values.add(year);
        values.add(semester);

        try {
            courseList = dBUtils.querySubsetRef("course_table", params, values, Course.class);
            System.out.print(courseList);
            System.out.println("获得某学生所有课程信息成功");
        } catch (Exception e) {
            System.out.println("获得某学生所有课程信息失败");
            e.printStackTrace();
        }

        return courseList;
    }

    /**
     * 判断是否已经存在课程id相同的课程
     * @param course: 课程对象
     * @return flag
     */
    public boolean checkCourseExist(Course course) {
        boolean flag = true;
        int id = course.getCoz_id();
        Course coz = queryCourseInfoByCourseId(id);
        if (coz == null)
            flag = false;
        return flag;
    }

    /**
     * 判断该课程是否属于某学期
     * @param courseId: 课程id
     * @return flag
     */
    public boolean checkCourseSemester(int courseId, int coz_year, int coz_semester) {
        boolean flag = false;
        Course coz = queryCourseInfoByCourseId(courseId);
        if (coz.getCoz_year() == coz_year && coz.getCoz_semester() == coz_semester)
            flag = true;
        return flag;
    }

    /**
     * 添加课程信息
     * @param course: 课程对象
     * @return flag
     */
    public boolean addCourseInfo(Course course){
        boolean flag = false;
        if (checkCourseExist(course)) {
            System.out.println("存在重复的id");
            return false;
        }
        List<Object> params = new ArrayList<Object>();
        params.add("coz_id");
        params.add("coz_name");
        params.add("coz_place");
        params.add("coz_credit");
        params.add("inst_id");
        params.add("maj_id");
        params.add("tch_id");
        params.add("coz_time");
        params.add("coz_year");
        params.add("coz_semester");

        List<Object> values = new ArrayList<Object>();
        values.add(course.getCoz_id());
        values.add(course.getCoz_name());
        values.add(course.getCoz_place());
        values.add(course.getCoz_credit());
        values.add(course.getInst_id());
        values.add(course.getMaj_id());
        values.add(course.getTch_id());
        values.add(course.getCoz_time());
        values.add(course.getCoz_year());
        values.add(course.getCoz_semester());

        if (dBUtils.add("course_table", params, values)) {
            System.out.println("添加成功");
            flag = true;
        }
        else
            System.out.println("添加失败");

        return flag;
    }

    /**
     * 根据id修改课程信息
     * @param course: 学生对象
     * @return flag
     */
    public boolean modifyCourseInfoById(Course course) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("coz_name");
        params.add("coz_place");
        params.add("coz_credit");
        params.add("inst_id");
        params.add("maj_id");
        params.add("tch_id");
        params.add("coz_time");
        params.add("coz_year");
        params.add("coz_semester");
        params.add("coz_id");

        List<Object> values = new ArrayList<Object>();
        values.add(course.getCoz_name());
        values.add(course.getCoz_place());
        values.add(course.getCoz_credit());
        values.add(course.getInst_id());
        values.add(course.getMaj_id());
        values.add(course.getTch_id());
        values.add(course.getCoz_time());
        values.add(course.getCoz_year());
        values.add(course.getCoz_semester());
        values.add(course.getCoz_id());

        if (dBUtils.update("course_table", params, values)) {
            System.out.println("修改成功");
            flag = true;
        }
        else
            System.out.println("修改失败");
        return flag;
    }

    /**
     * 通过id删除课程信息
     * @param id：课程id号
     * @return flag
     */
    public boolean deleteCourseInfoById(int id) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("coz_id");

        List<Object> values = new ArrayList<Object>();
        values.add(id);

        if (dBUtils.delete("course_table", params, values)) {
            System.out.println("删除成功");
            flag = true;
        }
        else
            System.out.println("删除失败");
        return flag;
    }
    public static void main(String[] args) {
        CourseDAO courseDAO = new CourseDAO();
        courseDAO.queryAllCourseInfoByInstMajId(1, 1);
        courseDAO.queryCourseInfoByCourseId(1);
        Course course = new Course(2, "数据结构与算法", "3区, 1-101", 2, 1, 1, 1, "周三:3-14周,每1周;11-13节", 2018, 1);
        courseDAO.addCourseInfo(course);
        //courseDAO.deleteCourseInfoById(2);
    }
}
