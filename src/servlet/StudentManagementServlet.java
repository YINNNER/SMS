package servlet;

import dao.StudentDAO;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StudentManagementServlet")
public class StudentManagementServlet extends HttpServlet {
    private StudentDAO studentDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String type = request.getParameter("type");
        System.out.println(type);

        //查询单个学生信息
        if (type.equals("querySingleStudent")){
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            Student student = studentDAO.queryStuInfoById(stu_id);
            request.setAttribute("studentInfo", student);
            request.getRequestDispatcher(".jsp").forward(request, response);
        }

        //添加学生信息
        if (type.contains("addStudent")){
            Student student = (Student) request.getAttribute("myStudent");
            boolean flag = studentDAO.addStuInfo(student);
        }

        //修改学生信息
        if (type.contains("modifyStudent")){
            Student student = (Student) request.getAttribute("modifiedStudent");
            boolean flag = studentDAO.modifyStuInfoById(student);
        }

        //删除学生
        if (type.contains("deleteStudent")){
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
//            boolean flag = studentDAO.deleteStuInfoById(stu_id);
            boolean flag = true;
            request.setAttribute("flag", flag);
            HttpSession session = request.getSession();
            session.setAttribute("class_id", request.getParameter("class_id"));
            request.getRequestDispatcher("student-list.jsp").forward(request, response);
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
        studentDAO = new StudentDAO();
    }
}
