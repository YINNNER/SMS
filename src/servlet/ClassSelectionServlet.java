package servlet;

import dao.*;
import entity.Course;
import entity.Score;
import entity.ScoreFront;
import entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ClassSelectionServlet")
public class ClassSelectionServlet extends HttpServlet {
    private CourseSelectDAO courseSelectDAO;
    private TeacherDAO teacherDAO;
    private ScoreDAO scoreDAO;
    private static final int CURRENTCOZYEAR = 2019;
    private static final int CURRENTCOZSEMSETER = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 获取学生选课信息
        if (param.contains("queryCourseSelection")) {
            List<ScoreFront> queryResults = new ArrayList<>();
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            List<Course> queryCourses = courseSelectDAO.queryAllCurrentCourseIdInfoByStuId(stu_id, CURRENTCOZYEAR, CURRENTCOZSEMSETER);
            for (Course course : queryCourses) {
                Score score = scoreDAO.queryScoreById(stu_id, course.getCoz_id());
                Teacher teacher = teacherDAO.queryTchInfoById(course.getTch_id());
                ScoreFront scoreFront = new ScoreFront(stu_id, course, score, teacher);
                queryResults.add(scoreFront);
            }

            request.setAttribute("queryResult", queryResults);
            request.getRequestDispatcher(".jsp").forward(request, response);
        }

        // 删除学生选课信息
        if (param.contains("deleteCourseSelection")) {
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            int coz_id = Integer.parseInt(request.getParameter("coz_id"));
            boolean flag = scoreDAO.deleteScoreSelectById(stu_id, coz_id);
            request.setAttribute("flag", flag);
            request.getRequestDispatcher(".jsp").forward(request, response);
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
        courseSelectDAO = new CourseSelectDAO();
        teacherDAO = new TeacherDAO();
        scoreDAO = new ScoreDAO();
    }
}
