package servlet;

import dao.*;
import entity.Course;
import entity.CourseFront;
import entity.Institute;
import entity.Teacher;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CourseManagementServlet")
public class CourseManagementServlet extends HttpServlet {
    private CourseDAO courseDAO;
    private TeacherDAO teacherDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 查询专业下的课程
        if (param.contains("queryCourse")) {
            List<CourseFront> queryResults = new ArrayList<>();
            int inst_id = Integer.parseInt(request.getParameter("inst_id"));
            int maj_id = Integer.parseInt(request.getParameter("maj_id"));
            List<Course> courses = courseDAO.queryAllCourseInfoByInstMajId(inst_id, maj_id);
            for (Course course:courses) {
                Course query_course = courseDAO.queryCourseInfoByCourseId(course.getCoz_id());
                Teacher query_teacher = teacherDAO.queryTchInfoById(course.getTch_id());
                CourseFront courseFront = new CourseFront(query_course, query_teacher);
                queryResults.add(courseFront);
            }

            request.setAttribute("queryResult", queryResults);
            request.getRequestDispatcher("lesson-list.jsp").forward(request, response);
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
        courseDAO = new CourseDAO();
        teacherDAO = new TeacherDAO();
    }
}
