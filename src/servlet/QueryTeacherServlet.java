package servlet;

import dao.TeacherDAO;
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

@WebServlet(name = "QueryTeacherServlet")
public class QueryTeacherServlet extends HttpServlet {
    private TeacherDAO teacherDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 查询所有老师
        if (param.contains("queryAllTeacher")) {
            List<Teacher> queryResults = teacherDAO.queryAllTch();;

            JSONArray jsonArray = new JSONArray(queryResults);
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
        teacherDAO = new TeacherDAO();
    }
}
