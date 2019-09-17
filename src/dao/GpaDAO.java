package dao;

import entity.AnalysisFront;
import entity.Course;
import entity.Score;
import utils.GPAUtils;
import utils.StringKeyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GpaDAO {
    private ScoreDAO scoreDAO;
    private CourseDAO courseDAO;

    public GpaDAO() {
        this.scoreDAO = new ScoreDAO();
        this.courseDAO = new CourseDAO();
    }

    public int getTotalCredit(int stu_id) {
        int credit = 0;
        List<Score> scores = scoreDAO.queryAllScoreInfoByStuId(stu_id);
        for (Score score : scores) {
            Course course = courseDAO.queryCourseInfoByCourseId(score.getCoz_id());
            credit += course.getCoz_credit();
        }
        return credit;
    }

    public int getTotalCreditBySemester(int stu_id, int year, int semester) {
        int credit = 0;
        List<Score> scores = scoreDAO.queryScoreBySemester(stu_id, year, semester);
        for (Score score : scores) {
            Course course = courseDAO.queryCourseInfoByCourseId(score.getCoz_id());
            credit += course.getCoz_credit();
        }
        return credit;
    }

    public List<AnalysisFront> getAnalysisResult(int stu_id) {
        List<AnalysisFront> analysisFronts = new ArrayList<>();
        Map<String, List<Course>> courseMap = new HashMap<>();
        List<Score> scores = scoreDAO.queryAllScoreInfoByStuId(stu_id);
        for (Score score : scores) {
            Course course = courseDAO.queryCourseInfoByCourseId(score.getCoz_id());
            int year = course.getCoz_year();
            int semester = course.getCoz_semester();
            String key = StringKeyUtils.encode(year, semester);
            if (courseMap.containsKey(key)) {
                courseMap.get(key).add(course);
            }
            else {
                List<Course> courseList = new ArrayList<>();
                courseList.add(course);
                courseMap.put(key,courseList);
            }
        }

        for(Map.Entry<String, List<Course>> map : courseMap.entrySet()){
            String mapKey = map.getKey();
            List<Course> mapValue = map.getValue();
            List<Integer> key = StringKeyUtils.decode(mapKey);
            int year = key.get(0);
            int semester = key.get(1);
            int credit = getTotalCreditBySemester(stu_id, year, semester);;
            float totalGPA = 0;
            for (Course course : mapValue) {
                float score = scoreDAO.queryScoreById(stu_id, course.getCoz_id()).getScore();
                totalGPA += GPAUtils.getGPAByScore(score) * course.getCoz_credit();
            }
            float gpa = totalGPA / credit;
            AnalysisFront analysisFront = new AnalysisFront(year, semester, credit, gpa);
            analysisFronts.add(analysisFront);
        }
        return analysisFronts;
    }

    public static void main(String[] args) {
        GpaDAO gpaDAO = new GpaDAO();
        int totalCredit = gpaDAO.getTotalCredit(1);
        int gpa = gpaDAO.getTotalCreditBySemester(1, 2019, 1);
        List<AnalysisFront> analysisFronts = gpaDAO.getAnalysisResult(1);

        System.out.println(totalCredit);
        System.out.println(gpa);
        System.out.println(analysisFronts);
    }
}
