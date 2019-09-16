package servlet;

import dao.ClassDAO;
import dao.StudentDAO;
import entity.Class;
import entity.ClassFront;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ClassManagementServlet")
public class ClassManagementServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private ClassDAO classDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 获取某学院某专业的班级信息
        if (param.contains("queryClass")) {
            List<ClassFront> queryResults = new ArrayList<>();
            int maj_id = Integer.parseInt(request.getParameter("maj_id"));
            List<Class> classes = classDAO.queryAllClassInfoByMaj(maj_id);
            for (Class cls:classes) {
                int stu_num = studentDAO.queryStuInfoByClass(cls.getClass_id()).size();
                ClassFront classFront = new ClassFront(cls, stu_num);
                queryResults.add(classFront);
            }

            request.setAttribute("queryResult", queryResults);
            request.getRequestDispatcher("teaching-class.jsp").forward(request, response);
        }

        // 添加学院
        if (param.contains("addClass")){
            Class cls = getClassInfo(request);
            boolean flag = classDAO.addClassInfo(cls);
            request.setAttribute("flag", flag);
            if (flag) {
                request.getRequestDispatcher(".jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher(".jsp").forward(request, response);
            }
        }

        // 修改学院信息
        if (param.contains("modifiedClass")){
            Class cls = (Class) request.getAttribute("myModifiedClass");
            boolean flag = classDAO.modifyClassInfo(cls);
            request.setAttribute("flag", flag);
            if (flag) {
                request.getRequestDispatcher(".jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher(".jsp").forward(request, response);
            }
        }

        // 删除学院
        if (param.contains("deleteClass")){
            int class_id = Integer.parseInt(request.getParameter("class_id"));
            boolean flag = classDAO.deleteClassInfoById(class_id);
            request.setAttribute("flag", flag);
            request.getRequestDispatcher(".jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    private Class getClassInfo(HttpServletRequest request) {
        int class_id = Integer.parseInt(request.getParameter("class_id"));
        String class_name = request.getParameter("class_name");
        int maj_id = Integer.parseInt(request.getParameter("maj_id"));
        int inst_id = Integer.parseInt(request.getParameter("inst_id"));

        return new Class(class_id, class_name, maj_id, inst_id);
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
        classDAO = new ClassDAO();
    }
}
