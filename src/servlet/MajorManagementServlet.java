package servlet;

import dao.ClassDAO;
import dao.InstituteDAO;
import dao.MajorDAO;
import dao.StudentDAO;
import entity.Major;
import entity.MajorFront;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MajorManagementServlet")
public class MajorManagementServlet extends HttpServlet {
    private MajorDAO majorDAO;
    private StudentDAO studentDAO;
    private ClassDAO classDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 获取某学院的专业信息
        if (param.contains("queryMajor")) {
            List<MajorFront> queryResults = new ArrayList<>();
            int inst_id = Integer.parseInt(request.getParameter("inst_id"));
            List<Major> majors = majorDAO.queryAllMajByInstId(inst_id);
            for (Major major:majors) {
                int class_num = classDAO.queryAllClassInfoByMaj(major.getMaj_id()).size();
                int stu_num = studentDAO.queryStuInfoByMaj(major.getMaj_id()).size();
                MajorFront majorFront = new MajorFront(major, class_num, stu_num);
                queryResults.add(majorFront);
            }

            request.setAttribute("queryResult", queryResults);
        }

        // 添加学院
        if (param.contains("addMajor")){
            Major major = getMajorInfo(request);
            boolean flag = majorDAO.addMajInfo(major);
            request.setAttribute("add_flag", flag);
        }

        // 修改学院信息
        if (param.contains("modifiedMajor")){
            Major major = getMajorInfo(request);
            boolean flag = majorDAO.modifyMajorInfo(major);
            request.setAttribute("modify_flag", flag);
        }

        // 删除学院
        if (param.contains("deleteMajor")){
            int maj_id = Integer.parseInt(request.getParameter("maj_id"));
            boolean flag = majorDAO.deleteMajInfoById(maj_id);
            request.setAttribute("delete_flag", flag);
        }

        HttpSession session = request.getSession();
        session.setAttribute("inst_id", request.getParameter("inst_id"));
        request.getRequestDispatcher("teaching-major.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    private Major getMajorInfo(HttpServletRequest request) {
        int maj_id = Integer.parseInt(request.getParameter("maj_id"));
        String maj_name = request.getParameter("maj_name");
        int inst_id = Integer.parseInt(request.getParameter("inst_id"));

        return new Major(maj_id, maj_name, inst_id);
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
        majorDAO = new MajorDAO();
        classDAO = new ClassDAO();
    }
}
