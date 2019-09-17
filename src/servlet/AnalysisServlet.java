package servlet;

import dao.CourseDAO;
import dao.ScoreDAO;
import entity.AnalysisFront;
import entity.Course;
import entity.Score;
import entity.ScoreFront;
import utils.GPAUtils;
import utils.StringKeyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AnalysisServlet")
public class AnalysisServlet extends HttpServlet {
    private ScoreDAO scoreDAO;
    private CourseDAO courseDAO;

    private int getTotalCredit(int stu_id) {
        int credit = 0;
        List<Score> scores = scoreDAO.queryAllScoreInfoByStuId(stu_id);
        for (Score score : scores) {
            Course course = courseDAO.queryCourseInfoByCourseId(score.getCoz_id());
            credit += course.getCoz_credit();
        }
        return credit;
    }

    private int getTotalCreditBySemester(int stu_id, int year, int semester) {
        int credit = 0;
        List<Score> scores = scoreDAO.queryScoreBySemester(stu_id, year, semester);
        for (Score score : scores) {
            Course course = courseDAO.queryCourseInfoByCourseId(score.getCoz_id());
            credit += course.getCoz_credit();
        }
        return credit;
    }

    private List<AnalysisFront> getAnalysisResult(int stu_id) {
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
                totalGPA += GPAUtils.getGPAByScore(score) * credit;
            }
            float gpa = totalGPA / credit;
            AnalysisFront analysisFront = new AnalysisFront(year, semester, credit, gpa);
            analysisFronts.add(analysisFront);
        }
        return analysisFronts;
    }

    private float getTotalGPA(int stu_id) {
        float gpa = 0;
        int totalCredit = getTotalCredit(stu_id);
        float weightedGPA = 0;
        List<Score> scores = scoreDAO.queryAllScoreInfoByStuId(stu_id);
        for (Score score : scores) {
            Course course = courseDAO.queryCourseInfoByCourseId(score.getCoz_id());
            weightedGPA += course.getCoz_credit() * GPAUtils.getGPAByScore(score.getScore());
        }
        gpa = weightedGPA / totalCredit;
        return gpa;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 获得每学期的GPA统计信息
        if (param.contains("getAnalysis")) {
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            int credit = getTotalCredit(stu_id);
            float totalGPA = getTotalGPA(stu_id);
            List<AnalysisFront> analysisFronts = getAnalysisResult(stu_id);

            request.setAttribute("creditSum", credit);
            request.setAttribute("gpaSum", totalGPA);
            request.setAttribute("analysisResult", analysisFronts);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        scoreDAO = new ScoreDAO();
        courseDAO = new CourseDAO();
    }

}
