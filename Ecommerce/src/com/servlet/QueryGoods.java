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
import com.entity.Goods;

public class QueryGoods extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public QueryGoods() {
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
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        System.out.println("in Query  Goods");
//        GoodsDao goodsDao = new GoodsService();
//        List<Goods> list = goodsDao.Query();
//        StringBuffer sb = new StringBuffer();
//        int count = 0;
//        sb.append("<table>\n");
//        sb.append("<tr>\n");
//
//        for (Goods good : list) {
//            sb.append("<td>\n");
//            sb.append("<table>\n");
//            sb.append("<tr>\n<td><img name=\"\" src=\"" + good.picture + "\" width=\"90\" height=\"103\" alt=\"\" align=\"left\" onclick=\"detail('" + good.id + "')\" / ></td>\n");
//            sb.append("<td>\n");
//            sb.append("<table>\n<tr>\n<td><strong>Name:</strong></td>\n</tr>\n");
//            sb.append("<tr>\n<td>" + good.name + "</td>\n</tr>\n");
//            sb.append("<tr>\n<td><strong>price:</strong></td>\n</tr>\n");
//            sb.append("<tr>\n<td>$" + good.price + "</td>\n</tr>\n");
//            sb.append("<tr>\n<td><strong>number:</strong></td>\n</tr>\n");
//            sb.append("<tr>\n<td>" + good.number + "</td>\n</tr>\n");
//            sb.append("</table>\n");
//            sb.append("</td>\n");
//            sb.append("</tr>\n");
//            sb.append("</table>\n");
//            sb.append("</td>\n");
//            
//            count++;
//            if(count%3==0)
//            {
//                sb.append("</tr>\n");
//                sb.append("<tr>\n");
//            }
//        }
//        sb.append("</tr>\n");
//        sb.append("</table>\n");
//        goodsDao.closeCon();
//        response.setContentType("text/html");
//        System.out.println("sb===="+sb.toString());
//        response.getWriter().write(sb.toString());
//    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GoodsDao goodsDao = new GoodsService();
        List<Goods> list = goodsDao.Query();
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (Goods good : list) {
            sb.append("<div id=\"content\" style=\"display: inline-block;\">\n");
            sb.append("<div style=\"background-image:url("+good.picture+"); background-size: auto 70%; background-position: center; height: 300px; width: 300px; background-repeat: no-repeat;\"></div>\n");
            sb.append("<dl>\n");
            sb.append("<dt ><strong>Name:</strong></dt>\n");
            sb.append("<dd class=\"ddname\" style= \"width: 270px; text-decoration: underline; word-wrap: break-word; cursor: pointer;\" onclick=\"detail('" + good.id + "')\">" + good.name + "</dd>\n");
            sb.append("<dt><strong>Price:</strong></dt>\n");
            sb.append("	<dd>$" + good.price + "</dd>\n");
            sb.append("<dt><strong>In Stock:</strong></dt>\n");
            sb.append("<dd>" + good.number + "</dd>\n");
            sb.append("</dl>\n");
            sb.append("</div>\n");
            count++;
            if (count % 3 == 0) {
                sb.append("<br>\n");
            }
        }
        goodsDao.closeCon();
        response.setContentType("text/html");
        response.getWriter().write(sb.toString());
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
