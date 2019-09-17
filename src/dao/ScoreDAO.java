package dao;

import utils.DBUtils;
import entity.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {
    private DBUtils dBUtils;
    private CourseDAO courseDAO;

    public ScoreDAO() {
        dBUtils = new DBUtils();
        courseDAO = new CourseDAO();
    }

    /**
     * 获得某学生所有的成绩信息
     * @param stu_id：学生id
     * @return List<Score>
     */
    public List<Score> queryAllScoreInfoByStuId(int stu_id){
        List<Score> scoreList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add("stu_id");
        List<Object> values = new ArrayList<>();
        values.add(stu_id);

        try {
            scoreList = dBUtils.querySubsetRef("score_table", params, values, Score.class);
            System.out.print(scoreList);
            System.out.println("获得某学生所有的成绩信息成功");
        } catch (Exception e) {
            System.out.println("获得某学生所有的成绩信息失败");
            e.printStackTrace();
        }

        return scoreList;
    }

    /**
     * 获得某学生某学期的成绩信息
     * @param stu_Id：学号
     * @param coz_year：学年
     * @param coz_semester：学期
     * @return List<Score>
     */
    public List<Score> queryScoreBySemester(int stu_Id, int coz_year, int coz_semester){
        List<Score> allScoreList = queryAllScoreInfoByStuId(stu_Id);
        List<Score> scoreList = new ArrayList<>();

        for (Score score : allScoreList){
            int coz_id = score.getCoz_id();
            if (courseDAO.checkCourseSemester(coz_id, coz_year, coz_semester)) {
                scoreList.add(score);
            }
        }
        return scoreList;
    }

    /**
     * 获得某学生某课程的成绩信息
     * @param stu_id：学号
     * @param coz_id：课程号
     * @return List<Score>
     */
    public Score queryScoreById(int stu_id, int coz_id){
        Score score = new Score();
        List<Object> params = new ArrayList<>();
        params.add("stu_id");
        params.add("coz_id");
        List<Object> values = new ArrayList<>();
        values.add(stu_id);
        values.add(coz_id);
        try {
            score = dBUtils.queryOneRef("score_table", params, values, Score.class);
            System.out.print(score);
            System.out.println("获得某学生某课程的成绩信息成功");
        } catch (Exception e) {
            System.out.println("获得某学生某课程的成绩信息失败");
            e.printStackTrace();
        }
        return score;
    }

    /**
     * 通过id删除课程信息
     * @param stu_id：学生id号
     * @param coz_id：课程id号
     * @return flag
     */
    public boolean deleteScoreSelectById(int stu_id, int coz_id) {
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

    /**
     * 通过id修改课程信息
     * @param score：成绩对象
     * @return flag
     */
    public boolean modifyScoreSelect(Score score) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("score");
        params.add("stu_id");
        params.add("coz_id");

        List<Object> values = new ArrayList<Object>();
        values.add(score.getScore());
        values.add(score.getStu_id());
        values.add(score.getCoz_id());

        if (dBUtils.updateWithTwoKey("score_table", params, values)) {
            System.out.println("修改课程信息成功");
            flag = true;
        }
        else
            System.out.println("修改课程信息失败");
        return flag;
    }

}
