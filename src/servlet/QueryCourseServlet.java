package servlet;

import dao.CourseDAO;
import dao.ScoreDAO;
import entity.Course;
import entity.Score;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QueryCourseServlet")
public class QueryCourseServlet extends HttpServlet {

    private ScoreDAO scoreDAO;
    private CourseDAO courseDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 获取所有未选的课程信息
        if (param.contains("queryAllCourse")) {
            List<Course> queryCourses = courseDAO.queryAllCourseInfo();
            JSONArray jsonArray = new JSONArray(queryCourses);
            System.out.println(jsonArray.toString());
            //发送响应
            response.getWriter().print(jsonArray.toString());
        }

        // 获取待给分的课程信息
        if (param.contains("queryCourseWithoutScore")) {
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            List<Course> result = new ArrayList<>();
            List<Course> queryCourses = courseDAO.queryAllCourseInfo();
            for (Course course : queryCourses) {
                int coz_id = course.getCoz_id();
                Score score = scoreDAO.queryScoreById(stu_id, coz_id);
                if (score != null && score.getScore() == null)
                    result.add(course);
            }
            JSONArray jsonArray = new JSONArray(result);
            System.out.println(jsonArray.toString());
            //发送响应
            response.getWriter().print(jsonArray.toString());
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
