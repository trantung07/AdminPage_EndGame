/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.controller;

import com.end.dao.CourseDao;
import com.end.dao.OrderDao;
import com.end.dao.UserDao;
import com.end.entity.Course;
import com.end.entity.Order;
import com.end.entity.OrderDetail;
import com.end.entity.User;
import com.end.util.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hung
 */
@Controller
public class OrderController {
    private OrderDao orderDao;
    
    public OrderController(){
        orderDao = new OrderDao();
    }
    @RequestMapping(value = "getAllOrder")
    public String showAllUser(Model model, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            String page = request.getParameter("page") != null ? request.getParameter("page") : "1";
            String keyword = request.getParameter("username") != null ? request.getParameter("username") : "";
            int pageInt = Integer.parseInt(page);
            int sizeRowofPage = 4;// số sản phẩm trên 1 trang
            HashMap map1 = orderDao.getDataPagination(pageInt, sizeRowofPage, keyword);

            String url = (String) map1.get("url");
            List<Order> orderPaging = new ArrayList<>();
            ResultSet rs = null;
            try {
                rs = (ResultSet) map1.get("rs");
                while (rs.next()) {
                    Order o = new Order();
                    o.setId(rs.getInt("id"));
                    o.setCreateAt(rs.getString("create_at"));
                    o.setCreateBy(rs.getInt("create_by"));
                    o.setDeleted(rs.getBoolean("deleted"));
                    o.setStatus(rs.getBoolean("status"));
                    o.setTotalCost(rs.getInt("total_cost"));
                    o.setUpdateAt(rs.getString("update_at"));
                    o.setUpdateBy(rs.getInt("update_by"));
                    o.setUsersId(rs.getInt("user_id"));
                    orderPaging.add(o);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Connection conn = (Connection) map1.get("conn");
                CallableStatement callSt = (CallableStatement) map1.get("callSt");
                DBConnection.closeConnection(conn, callSt, rs);
            }

            List<User> listUser = new UserDao().getAllUser(keyword);
            List<Order> list = orderDao.getAllOrder();
            int startIndex = 1 + sizeRowofPage * (pageInt -1);
            
            if(orderPaging.isEmpty()){
                startIndex = 0;
            }
            int endIndex = sizeRowofPage * pageInt;
            if(endIndex > list.size()){
                endIndex = list.size();
            }
            
            model.addAttribute("startIndex", startIndex);
            model.addAttribute("endIndex", endIndex);
            model.addAttribute("url", url);
            model.addAttribute("size", list.size());
            model.addAttribute("ListUser", listUser);
            model.addAttribute("OrderList", orderPaging);
            
            return "orderList";
        }
    }
    
    @RequestMapping(value = "initDetailOrder")
    public String initDetailUser(@RequestParam("id") int id, Model model) {
        List<OrderDetail> o = orderDao.getAllOrderDetail(id);
        if (o == null) {
            return "404-page";
        }

        model.addAttribute("ListOrderDetail", o);
        List<Course> listCourse = new CourseDao().getAllCourse();
        model.addAttribute("listCourse", listCourse);
        List<Order> listOrder = orderDao.getAllOrder();
        model.addAttribute("listOrderDetail", listOrder);
        return "orderDetailList";
    }
}
