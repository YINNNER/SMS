package servlet;

import dao.ClassDAO;
import dao.InstituteDAO;
import dao.MajorDAO;
import dao.StudentDAO;
import entity.*;
import entity.Class;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "StudentManagementServlet")
public class StudentManagementServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private InstituteDAO instituteDAO;
    private MajorDAO majorDAO;
    private ClassDAO classDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String type = request.getParameter("type");
        System.out.println(type);

        //查询单个学生信息
        if (type.equals("querySingleStudent")){
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            Student student = studentDAO.queryStuInfoById(stu_id);
            Institute institute = instituteDAO.queryInstInfoById(student.getInst_id());
            Major major = majorDAO.queryMajInfoById(student.getMaj_id());
            Class cls = classDAO.queryClassInfoById(student.getClass_id());
            StudentFront studentFront = new StudentFront(student, institute, major, cls);

            JSONObject jsonObject = new JSONObject(studentFront);
            System.out.println(jsonObject.toString());
            //发送响应
            response.getWriter().print(jsonObject.toString());
            request.setAttribute("studentInfo", studentFront);
            request.getRequestDispatcher("student-info.jsp").forward(request, response);
        }

        //查询单个学生信息
        if (type.equals("querySingleJsonStudent")) {
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            Student student = studentDAO.queryStuInfoById(stu_id);
            Institute institute = instituteDAO.queryInstInfoById(student.getInst_id());
            Major major = majorDAO.queryMajInfoById(student.getMaj_id());
            Class cls = classDAO.queryClassInfoById(student.getClass_id());

            StudentFront studentFront = new StudentFront(student, institute, major, cls);

            JSONObject jsonObject = new JSONObject(studentFront);
            System.out.println(jsonObject.toString());
            //发送响应
            response.getWriter().print(jsonObject.toString());
        }

        //添加学生信息
        if (type.contains("addStudent")){
            Student student = getStudentInfo(request);
            boolean flag = studentDAO.addStuInfo(student);
            request.setAttribute("add_flag", flag);
            request.getRequestDispatcher("student-info-add.jsp").forward(request, response);
        }

        //修改学生信息
        if (type.contains("modifyStudent")){
            Student student = getStudentInfo(request);
            boolean flag = studentDAO.modifyStuInfoById(student);
            request.setAttribute("modify_flag", flag);
            request.getRequestDispatcher("student-info-add.jsp").forward(request, response);
        }

        //删除学生
        if (type.contains("deleteStudent")){
            int stu_id = Integer.parseInt(request.getParameter("stu_id"));
            boolean flag = studentDAO.deleteStuInfoById(stu_id);
            //boolean flag = true;
            request.setAttribute("flag", flag);
            HttpSession session = request.getSession();
            session.setAttribute("class_id", request.getParameter("class_id"));
            request.getRequestDispatcher("student-list.jsp").forward(request, response);

        }
    }

    private Student getStudentInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("class_id", request.getParameter("class_id"));
        int stu_id = Integer.parseInt(request.getParameter("stu_id"));
        String stu_name = request.getParameter("stu_name");
        String stu_sex = request.getParameter("stu_sex");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date stu_birth_date = null;
        try {
            stu_birth_date = dateFormat.parse(request.getParameter("stu_birth_date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String stu_birth_place = request.getParameter("stu_birth_place");
        String stu_political = request.getParameter("stu_political");
        int inst_id = Integer.parseInt(request.getParameter("inst_id"));
        int maj_id = Integer.parseInt(request.getParameter("maj_id"));
        int class_id = Integer.parseInt(request.getParameter("class_id"));
        return new Student(stu_id, inst_id, maj_id, class_id, stu_name, stu_sex, stu_birth_date, stu_birth_place, stu_political);
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
