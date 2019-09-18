package servlet;

import dao.CourseSelectDAO;
import dao.InstituteDAO;
import dao.ScoreDAO;
import dao.TeacherDAO;
import entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ScoreManagementServlet")
public class ScoreManagementServlet extends HttpServlet {
    private CourseSelectDAO courseSelectDAO;
    private TeacherDAO teacherDAO;
    private ScoreDAO scoreDAO;
    private InstituteDAO instituteDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 获取学生成绩信息
        if (param.contains("queryScore")) {
            List<ScoreFront> queryResults = new ArrayList<>();
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            List<Course> queryCourses = courseSelectDAO.queryAllCourseIdInfoByStuId(stu_id);
            for (Course course : queryCourses) {
                Score score = scoreDAO.queryScoreById(stu_id, course.getCoz_id());
                Teacher teacher = teacherDAO.queryTchInfoById(course.getTch_id());
                Institute institute = instituteDAO.queryInstInfoById(course.getInst_id());
                ScoreFront scoreFront = new ScoreFront(stu_id, course, score, teacher, institute);
                queryResults.add(scoreFront);
            }
            request.setAttribute("queryResult", queryResults);
        }

        // 获取学生某学期的成绩信息
        if (param.contains("queryScoreBySemester")) {
            List<ScoreFront> queryResults = new ArrayList<>();
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            int coz_year = Integer.parseInt(request.getParameter("coz_year"));
            int coz_semester = Integer.parseInt(request.getParameter("coz_semester"));
            List<Course> queryCourses = courseSelectDAO.queryAllCurrentCourseIdInfoByStuId(stu_id, coz_year, coz_semester);
            for (Course course : queryCourses) {
                Score score = scoreDAO.queryScoreById(stu_id, course.getCoz_id());
                Teacher teacher = teacherDAO.queryTchInfoById(course.getTch_id());
                Institute institute = instituteDAO.queryInstInfoById(course.getInst_id());
                ScoreFront scoreFront = new ScoreFront(stu_id, course, score, teacher, institute);
                queryResults.add(scoreFront);
            }
            request.setAttribute("queryResult", queryResults);
        }

        // 添加成绩
        if (param.contains("addScore")){
            Score score = (Score) getScoreInfo(request);
            boolean flag = courseSelectDAO.addCourseSelect(score);
            request.setAttribute("flag", flag);
            request.getRequestDispatcher(".jsp").forward(request, response);
            
        }

        // 修改成绩
        if (param.contains("modifyScore")) {
            Score score = (Score) getScoreInfo(request);
            boolean flag = scoreDAO.modifyScoreSelect(score);
            request.setAttribute("flag", flag);
        }

        // 删除学生成绩信息
        if (param.contains("deleteScore")) {
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            int coz_id = Integer.parseInt(request.getParameter("coz_id"));
            boolean flag = scoreDAO.deleteScoreSelectById(stu_id, coz_id);
            request.setAttribute("flag", flag);
        }

        request.getRequestDispatcher("student-score.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    private Score getScoreInfo(HttpServletRequest request) {
        int stu_id = Integer.parseInt(request.getParameter("stu_id"));
        int coz_id = Integer.parseInt(request.getParameter("coz_id"));
        float score = Float.parseFloat(request.getParameter("score"));

        return new Score(stu_id, coz_id, score);
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        courseSelectDAO = new CourseSelectDAO();
        teacherDAO = new TeacherDAO();
        scoreDAO = new ScoreDAO();
        instituteDAO = new InstituteDAO();
    }
}
