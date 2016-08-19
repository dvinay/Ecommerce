package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OderDao;
import com.dao.OderService;
import com.entity.Users;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddOrder extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public AddOrder() {
        super();
    }

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

        try {
            String id = request.getParameter("id");
            String number = request.getParameter("number");
            System.out.println("id===" + id + " " + number);
            Users user = (Users) request.getSession().getAttribute("user");
            System.out.println("USEr=="+user);
            OderDao orderDao = new OderService();
            System.out.println("user id==="+user.id);
            Boolean isSuc = orderDao.add(Integer.parseInt(id), Integer.parseInt(number), Integer.parseInt(user.id));
            System.out.println(isSuc);
            
            String result = "";
            if (isSuc) {
                result = "Item Added Successfully to your Cart";
            } else {
                result = "add failed";
            }
            System.out.println("after else");
            
            orderDao.closeCon();
            response.setContentType("text/html");
            response.getWriter().write(result);
            System.out.println("result==" + result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    

}
