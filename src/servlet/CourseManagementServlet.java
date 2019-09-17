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

            JSONArray jsonArray = new JSONArray(queryResults);
            System.out.println(jsonArray.toString());
            //发送响应
            response.getWriter().print(jsonArray.toString());

            request.setAttribute("queryResult", queryResults);
            request.getRequestDispatcher("lesson-list.jsp").forward(request, response);
        }

        // 添加课程
        if (param.contains("addCourse")){
            Course course = getCourseInfo(request);
            boolean flag = courseDAO.addCourseInfo(course);
            request.setAttribute("flag", flag);
            if (flag) {
                request.getRequestDispatcher(".jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher(".jsp").forward(request, response);
            }
        }

        // 修改课程
        if (param.contains("modifiedCourse")){
            Course course = getCourseInfo(request);
            boolean flag = courseDAO.modifyCourseInfoById(course);
            request.setAttribute("flag", flag);
            if (flag) {
                request.getRequestDispatcher(".jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher(".jsp").forward(request, response);
            }
        }

        // 删除课程
        if (param.contains("deleteCourse")){
            int coz_id = Integer.parseInt(request.getParameter("coz_id"));
            boolean flag = courseDAO.deleteCourseInfoById(coz_id);
            request.setAttribute("flag", flag);
            request.getRequestDispatcher(".jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    private Course getCourseInfo(HttpServletRequest request) {
        int coz_id = Integer.parseInt(request.getParameter("coz_id"));
        String coz_name = request.getParameter("coz_name");
        String coz_place = request.getParameter("coz_place");
        int coz_credit = Integer.parseInt(request.getParameter("coz_credit"));
        int inst_id = Integer.parseInt(request.getParameter("inst_id"));
        int maj_id = Integer.parseInt(request.getParameter("maj_id"));
        int tch_id = Integer.parseInt(request.getParameter("tch_id"));
        String coz_time = request.getParameter("coz_time");
        int coz_year = Integer.parseInt(request.getParameter("coz_year"));
        int coz_semester = Integer.parseInt(request.getParameter("coz_semester"));

        return new Course(coz_id, coz_name, coz_place, coz_credit, inst_id, maj_id, tch_id, coz_time, coz_year, coz_semester);
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
