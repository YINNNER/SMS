package servlet;

import dao.ClassDAO;
import dao.InstituteDAO;
import dao.MajorDAO;
import dao.StudentDAO;
import entity.Class;
import entity.Institute;
import entity.Major;
import entity.Student;
import org.json.JSONArray;

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
    private InstituteDAO instituteDAO;
    private MajorDAO majorDAO;
    private ClassDAO classDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 查询所有的学院
        if (param.contains("queryInst")) {
            List<Institute> instituteList = instituteDAO.queryAllInstInfo();
            JSONArray jsonArray = new JSONArray(instituteList);
            System.out.println(jsonArray.toString());
            //发送响应
            response.getWriter().print(jsonArray.toString());
        }

        // 根据学院查找专业
        if (param.contains("queryMaj")) {
            int inst_id = Integer.parseInt(request.getParameter("inst_id"));
            List<Major> majorList = majorDAO.queryAllMajByInstId(inst_id);
            JSONArray jsonArray = new JSONArray(majorList);
            System.out.println(jsonArray.toString());
            //发送响应
            response.getWriter().print(jsonArray.toString());
        }

        // 根据专业查找班级
        if (param.contains("queryClass")) {
            int maj_id = Integer.parseInt(request.getParameter("maj_id"));
            List<Class> classList = classDAO.queryAllClassInfoByMaj(maj_id);
            JSONArray jsonArray = new JSONArray(classList);
            System.out.println(jsonArray.toString());
            //发送响应
            response.getWriter().print(jsonArray.toString());
        }

        //提交查询
        if (param.contains("querySubmit")) {
            int inst_id = Integer.parseInt(request.getParameter("inst_id"));
            int maj_id = Integer.parseInt(request.getParameter("maj_id"));
            int class_id = Integer.parseInt(request.getParameter("class_id"));

            List<Student> queryResults = studentDAO.queryStuInfoByClass(inst_id, maj_id, class_id);

            request.setAttribute("queryResult", queryResults);
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
        instituteDAO = new InstituteDAO();
        majorDAO = new MajorDAO();
        classDAO = new ClassDAO();
    }
}
