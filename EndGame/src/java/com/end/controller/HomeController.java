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
import com.end.entity.Role;
import com.end.entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tran Tung
 */
@Controller
public class HomeController {

    private UserDao userDao;
    private CourseDao courseDao;
    private OrderDao orderDao;

    public HomeController() {
        userDao = new UserDao();
        courseDao = new CourseDao();
        orderDao = new OrderDao();
    }

    @RequestMapping(value = "initLogin")
    public String initBackendLogin(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            return "redirect: homeBackend.htm";
        }

    }

    @RequestMapping(value = "loginAdmin")
    public String loginBackend(@ModelAttribute("user") User user, Model model, HttpSession session) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getUsername())) {
            model.addAttribute("message", "Hãy nhập thông tin cần thiết.");
            return "login";
        }
        User us = userDao.checkLoginAdminPage(user);
        if (us != null) {
            session.setAttribute("id", us.getId());
            session.setAttribute("username", us.getUsername());
            session.setAttribute("displayName", us.getDisplayName());
            session.setAttribute("lastName", us.getLastName());
            session.setAttribute("roleId", us.getRoleId());
            return "redirect: homeBackend.htm";
        } else {
            model.addAttribute("message", "Sai thông tin đăng nhập.");
            return "login";
        }
    }

    @RequestMapping(value = "homeBackend")
    public String homeBackend(HttpSession session, Model model) {
        if (session.getAttribute("username") != null) {
            List<User> listUser = userDao.getAllUserLimit();
            List<Role> listRole = userDao.getAllRole();
            model.addAttribute("userList", listUser);
            model.addAttribute("roleList", listRole);
            
            List<User> listAUser = userDao.getAllUser("");
            List<Order> listOrder = orderDao.getAllOrderLimit();
            model.addAttribute("ListUser", listAUser);
            model.addAttribute("OrderList", listOrder);
            
            List<Course> list = courseDao.getAllCourse();
            model.addAttribute("courseList", list);
        } else {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        }
        return "homePage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/initLogin.htm";
    }

}
