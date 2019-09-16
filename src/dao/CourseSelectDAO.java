package dao;

import dbutils.DBUtils;
import entity.Course;
import entity.Score;

import java.util.ArrayList;
import java.util.List;

public class CourseSelectDAO {
    private DBUtils dBUtils;
    private CourseDAO courseDAO;
    private static final int CURRENTCOZYEAR = 2019;
    private static final int CURRENTCOZSEMSETER = 1;

    public CourseSelectDAO() {
        dBUtils = new DBUtils();
        courseDAO = new CourseDAO();
    }

    /**
     * 获得某学生当前学期所有课程
     * @param stu_id：学生id
     * @return List<Course>
     */
    public List<Course> queryAllCurrentCourseIdInfoByStuId(int stu_id){
        List<Course> courseList = new ArrayList<>();
        List<Course> allCourseList = new ArrayList<>();
        List<Score> allCourseSelectList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add("stu_id");
        List<Object> values = new ArrayList<>();
        values.add(stu_id);
        try {
            // 得到学生所有的选课信息
            allCourseSelectList = dBUtils.querySubsetRef("score_table", params, values, Score.class);
            System.out.print(allCourseSelectList);
            System.out.println("获得某学生所有课程信息成功");
        } catch (Exception e) {
            System.out.println("获得某学生所有课程信息失败");
            e.printStackTrace();
        }

        for (Score score : allCourseSelectList) {
            allCourseList.add(courseDAO.queryCourseInfoByCourseId(score.getCoz_id()));
        }

        for (Course course : allCourseList) {
            if (courseDAO.checkCourseSemester(course.getCoz_id(), CURRENTCOZYEAR, CURRENTCOZSEMSETER)) {
                courseList.add(course);
            }
        }
        System.out.println(courseList);
        return courseList;
    }

    /**
     * 根据学号、课程号获得选课信息
     * @param stu_id：学生id
     * @return Score
     */
    public Score queryCourseSelectByCozIdStuId(int coz_id, int stu_id){
        Score score = new Score();
        List<Object> params = new ArrayList<>();
        params.add("coz_id");
        params.add("stu_id");
        List<Object> values = new ArrayList<>();
        values.add(coz_id);
        values.add(stu_id);
        try {
            // 得到学生所有的选课信息
            score = dBUtils.queryOneRef("score_table", params, values, Score.class);
            System.out.print(score);
            System.out.println("根据学号、课程号获得选课信息成功");
        } catch (Exception e) {
            System.out.println("根据学号、课程号获得选课信息失败");
            e.printStackTrace();
        }

        return score;
    }

    /**
     * 判断是否已经存在该选课信息
     * @param score: 选课对象
     * @return flag
     */
    public boolean checkCourseSelectExist(Score score) {
        boolean flag = true;
        int coz_id = score.getCoz_id();
        int stu_id = score.getStu_id();
        Score queryScore = queryCourseSelectByCozIdStuId(coz_id, stu_id);
        if (queryScore == null)
            flag = false;
        return flag;
    }

    /**
     * 添加选课
     * @param score：选课对象
     * @return flag
     */
    public boolean addCourseSelect(Score score) {
        boolean flag = false;
        if (checkCourseSelectExist(score)) {
            System.out.println("存在重复的id");
            return false;
        }
        List<Object> params = new ArrayList<Object>();
        params.add("stu_id");
        params.add("coz_id");
        params.add("score");

        List<Object> values = new ArrayList<Object>();
        values.add(score.getStu_id());
        values.add(score.getCoz_id());
        values.add(score.getScore());

        if (dBUtils.add("score_table", params, values)) {
            System.out.println("添加成功");
            flag = true;
        } else
            System.out.println("添加失败");

        return flag;
    }

    /**
     * 通过id删除课程信息
     * @param stu_id：学生id号
     * @param coz_id：课程id号
     * @return flag
     */
    public boolean deleteCourseSelectById(int stu_id, int coz_id) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("stu_id");
        params.add("coz_id");

        List<Object> values = new ArrayList<Object>();
        values.add(stu_id);
        values.add(coz_id);

        if (dBUtils.delete("score_table", params, values)) {
            System.out.println("删除成功");
            flag = true;
        }
        else
            System.out.println("删除失败");
        return flag;
    }

    public static void main(String[] args) {
        CourseSelectDAO courseSelectDAO = new CourseSelectDAO();
        courseSelectDAO.queryAllCurrentCourseIdInfoByStuId(1);
        courseSelectDAO.queryCourseSelectByCozIdStuId(1, 1);
        Score score = new Score(1, 2);
        courseSelectDAO.addCourseSelect(score);
        courseSelectDAO.deleteCourseSelectById(1, 2);
    }

}
