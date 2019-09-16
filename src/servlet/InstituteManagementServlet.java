package servlet;

import dao.InstituteDAO;
import dao.MajorDAO;
import dao.StudentDAO;
import entity.Institute;
import entity.InstituteFront;
import entity.Major;

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
    InstituteDAO instituteDAO;
    MajorDAO majorDAO;
    StudentDAO studentDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String param = request.getParameter("param");

        // 查询专业下的课程
        if (param.contains("queryInstitute")) {
            List<InstituteFront> queryResults = new ArrayList<>();
            int inst_id = Integer.parseInt(request.getParameter("inst_id"));
            int maj_id = Integer.parseInt(request.getParameter("maj_id"));
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

        // 添加课程
        if (param.contains("addInstitute")){
            Institute institute = (Institute) request.getAttribute("addInstitute");
            boolean flag = instituteDAO.addInstInfo(institute);
        }

        // 修改课程
        if (param.contains("modifiedInstitute")){
            Institute institute = (Institute) request.getAttribute("modifiedInstitute");
            boolean flag = instituteDAO.modifyInstInfoById(institute);
        }

        // 删除课程
        if (param.contains("deleteInstitute")){
            int inst_id = Integer.parseInt(request.getParameter("inst_id"));
            boolean flag = instituteDAO.deleteInstInfoById(inst_id);
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
        instituteDAO = new InstituteDAO();
        studentDAO = new StudentDAO();
        majorDAO = new MajorDAO();
    }
}
