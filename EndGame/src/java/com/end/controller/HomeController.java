/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.controller;

import com.end.dao.UserDao;
import com.end.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Tran Tung
 */
@Controller
public class HomeController {

    private UserDao userDao;

    public HomeController() {
        userDao = new UserDao();
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
            return "redirect: homeBackend.htm";
        } else {
            model.addAttribute("message", "Sai thông tin đăng nhập.");
            return "login";
        }
    }

    @RequestMapping(value = "homeBackend")
    public ModelAndView homeBackend(HttpSession session) {
        ModelAndView model;
        if (session.getAttribute("username") != null) {
            model = new ModelAndView("homePage");
            return model;
        } else {
            model = new ModelAndView("login");
            User user = new User();
            model.getModelMap().put("user", user);
        }
        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/initLogin.htm";
    }

}
