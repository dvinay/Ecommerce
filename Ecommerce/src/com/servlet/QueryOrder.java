package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GoodsDao;
import com.dao.GoodsService;
import com.dao.OderDao;
import com.dao.OderService;
import com.entity.Goods;
import com.entity.Oder;
import com.entity.Oder_list;
import com.entity.Users;

public class QueryOrder extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public QueryOrder() {
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
        Users user = (Users) request.getSession().getAttribute("user");
        OderDao orderDao = new OderService();
        List<Oder> list = orderDao.QueryOder(Integer.parseInt(user.id));

        StringBuffer sb = new StringBuffer();
        for (Oder oder : list) {
            double total = 0;
            sb.append("<ul><h3><strong>Order Number:" + oder.id + "</strong></h3>\n");
            sb.append("time:" + oder.time + "<br>\n");
            sb.append("address:" + user.address + "<br>\n");
            boolean isPay = oder.isset.equals("0");
            String statu = isPay ? " <span style=\"color:red;display:inline-block; \">waiting for payment.</span>" : "<span style=\"color:green;display:inline-block;\"> Payment successful, Order placed.</span>";
            sb.append("statu:" + statu + "<br><br>\n");
            List<Oder_list> list1 = orderDao.Query(Integer.parseInt(oder.id));
            for (Oder_list oder_list : list1) {
                GoodsDao goodsDao = new GoodsService();
                Goods good = goodsDao.QueryOne(oder_list.goodsid);
                total += Double.parseDouble(good.price) * Integer.parseInt(oder_list.goodsnumber);
                sb.append("<ul style=\"margin-bottom: 82px;\">");
                sb.append("<li id=\"" + oder_list.id + "\"><img src=\"" + good.picture + "\" width=\"100\" height=\"100\" style=\"float: left;\"><span style=\"margin-top: 0px;\">" + oder_list.goodsnumber + " <a href=\"servlet/QueryGood?id=" + good.id + "\" target=\"_Blank\">" + good.name + "</a><br>of $" + good.price + "&nbsp;&nbsp;&nbsp;&nbsp; ");
                if (isPay) {
                    sb.append("<a href=\"javascript:void(0)\" onclick=\"deleteO('" + oder_list.id + "')\">Delete Gadget</a>");
                }
                sb.append("</span></li></ul>\n");
                goodsDao.closeCon();
            }
            sb.append("<h4><strong>total:$" + total + "</strong>");
            if (isPay) {
                sb.append("<button id=\"set\" onclick=\"set('" + oder.id + "')\"></button>");
            }
            sb.append("</h4></ul><br>\n");
        }
        orderDao.closeCon();

        response.setContentType("text/html");
        response.getWriter().write(sb.toString());;
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
