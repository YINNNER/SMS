package servlet;

import dao.LoginDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "Login")
public class LoginServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDAO;

    /**
     * Constructor of the object.
     */
    public LoginServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();


        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("AdminName");
        String pswd = request.getParameter("AdminPwd");

        // 获得cookie缓存
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            String cookieName = null, cookiePwd = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("AdminName")) {
                    cookieName = cookie.getValue();
                }
                if (cookie.getName().equals("AdminPwd")) {
                    cookiePwd = cookie.getValue();
                }
            }
            if (username.equals(cookieName) && pswd.equals(cookiePwd)) {
                response.sendRedirect(path + "/index.jsp");
                return;
            }
        }
        List<Object> params = new ArrayList<Object>();
        params.add(username);
        params.add(pswd);
        User user = loginDAO.checkLogin(params);
        // 设置新cookie
        if (user != null) {
            Cookie usernameCookie = new Cookie("AdminName", username);
            //保证cookie存放的根目录相同
            usernameCookie.setPath("/");
            //设置cookie存活时间为1天
            usernameCookie.setMaxAge(60 * 60 * 24);
            //将cookie保存在客户端
            response.addCookie(usernameCookie);

            Cookie pwdCookie = new Cookie("AdminPwd", pswd);
            pwdCookie.setPath("/");
            pwdCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(pwdCookie);

            HttpSession session = request.getSession();
            session.setAttribute("admin", user);
            response.sendRedirect(path + "/index.jsp");
        } else {
            response.sendRedirect(path + "/login.jsp");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        loginDAO = new LoginDAO();
    }

}
