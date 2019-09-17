package servlet;

import dao.InstituteDAO;
import dao.MajorDAO;
import dao.StudentDAO;
import entity.Institute;
import entity.InstituteFront;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InstituteManagementServlet")
public class InstituteManagementServlet extends HttpServlet {
    private InstituteDAO instituteDAO;
    private MajorDAO majorDAO;
    private StudentDAO studentDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 获得学院信息
        if (param.contains("queryInstitute")) {
            List<InstituteFront> queryResults = new ArrayList<>();
            List<Institute> institutes = instituteDAO.queryAllInstInfo();
            for (Institute institute:institutes) {
                int maj_num = majorDAO.queryAllMajByInstId(institute.getInst_id()).size();
                int stu_num = studentDAO.queryStuInfoByInst(institute.getInst_id()).size();
                InstituteFront instituteFront = new InstituteFront(institute, maj_num, stu_num);
                queryResults.add(instituteFront);
            }

            request.setAttribute("queryResult", queryResults);
            request.getRequestDispatcher("teaching-institute.jsp").forward(request, response);
        }

        // 添加学院
        if (param.contains("addInstitute")){
            Institute institute = getInstInfo(request);
            boolean flag = instituteDAO.addInstInfo(institute);
            request.setAttribute("add_flag", flag);
            request.getRequestDispatcher("teaching-institute.jsp").forward(request, response);
        }

        // 修改学院信息
        if (param.contains("modifiedInstitute")){
            Institute institute = getInstInfo(request);
            boolean flag = instituteDAO.modifyInstInfo(institute);
            request.setAttribute("modify_flag", flag);
            request.getRequestDispatcher("teaching-institute.jsp").forward(request, response);
        }

        // 删除学院
        if (param.contains("deleteInstitute")){
            int inst_id = Integer.parseInt(request.getParameter("inst_id"));
            boolean flag = instituteDAO.deleteInstInfoById(inst_id);
            request.setAttribute("flag", flag);
            request.getRequestDispatcher(".jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    private Institute getInstInfo(HttpServletRequest request) {
        int inst_id = Integer.parseInt(request.getParameter("inst_id"));
        String inst_name = request.getParameter("inst_name");

        return new Institute(inst_id, inst_name);
    }


    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        instituteDAO = new InstituteDAO();
        studentDAO = new StudentDAO();
        majorDAO = new MajorDAO();
    }
}
