package servlet;

import dao.CourseDAO;
import dao.GpaDAO;
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
    private GpaDAO gpaDAO;

    private float getTotalGPA(int stu_id) {
        float gpa = 0;
        int totalCredit = gpaDAO.getTotalCredit(stu_id);
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

        // 获得每学期的GPA统计信息
        int stu_id = Integer.parseInt(request.getParameter("stu_id"));
        int credit = gpaDAO.getTotalCredit(stu_id);
        float totalGPA = getTotalGPA(stu_id);
        List<AnalysisFront> analysisFronts = gpaDAO.getAnalysisResult(stu_id);

        request.setAttribute("creditSum", credit);
        request.setAttribute("gpaSum", totalGPA);
        request.setAttribute("analysisResult", analysisFronts);

        request.getRequestDispatcher("student-statistic.jsp").forward(request, response);
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
        gpaDAO = new GpaDAO();
    }

}
