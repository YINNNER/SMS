package servlet;

import dao.StudentDAO;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryStudentServlet")
public class QueryStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        int inst_id = Integer.parseInt(request.getParameter("inst_id"));
        int maj_id = Integer.parseInt(request.getParameter("maj_id"));
        int class_id = Integer.parseInt(request.getParameter("class_id"));

        List<Student> queryResults = studentDAO.queryStuInfoByClass(inst_id, maj_id, class_id);

        request.setAttribute("queryResult", queryResults);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        studentDAO = new StudentDAO();
    }
}
