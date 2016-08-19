package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UsersDao;
import com.dao.UsersService;
import com.entity.Users;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to
     * post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsersDao usersDao = new UsersService();
        Users user = usersDao.check(username, password);
        String result = "";
        if (user != null) {
            try {
                System.out.println("in if");
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("../MyIndex.html");
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                System.out.println("in else ");
                result = "register failed";
                request.setAttribute("result", result);
                response.sendRedirect("../index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        usersDao.closeCon();
    }

}
