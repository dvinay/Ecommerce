package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GoodsDao;
import com.dao.GoodsService;
import com.dao.OderDao;
import com.dao.OderService;

public class SetOrder extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public SetOrder() {
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
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            GoodsDao goodsDao = new GoodsService();
            String result = goodsDao.Update(id);
            if (result == "") {
                OderDao orderDao = new OderService();
                int a = orderDao.setOrder(Integer.parseInt(id));
                goodsDao.closeCon();
                response.setContentType("text/html");
                if (a > 0) {
                    response.getWriter().write("Your Order has been confirmed");
                } else {
                    response.getWriter().write("failed");
                }
            } else {
                response.getWriter().write(result);
            }
        } catch (Exception ex) {
            response.getWriter().write("failed");
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}
