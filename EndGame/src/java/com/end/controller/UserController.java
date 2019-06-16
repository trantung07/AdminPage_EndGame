/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.controller;

import com.end.dao.UserDao;
import com.end.entity.Role;
import com.end.entity.User;
import com.end.util.CommonFunc;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Tran Tung
 */
@Controller
public class UserController {
    
    private UserDao userDao;
    
    public UserController() {
        userDao = new UserDao();
    }
    
    @RequestMapping(value = "getAllUser")
    public String showAllUser(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            List<Role> listRole = userDao.getAllRole();
            List<User> list = userDao.getAllUser();
            model.addAttribute("roleList", listRole);
            model.addAttribute("userList", list);
            return "userList";
        }
    }
    
    @RequestMapping(value = "initInsertUser")
    public String initInsertUser(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            User user = new User();
            List<Role> listRole = userDao.getAllRole();
            model.addAttribute("newUser", user);
            model.addAttribute("listRole", listRole);
            return "userAdd";
        }
    }
    
    @RequestMapping(value = "insertUser", method = RequestMethod.POST)
    public String insertUser(@ModelAttribute("newUser") User user, Model model, HttpSession session) {
        List<Role> listRole = userDao.getAllRole();
        model.addAttribute("listRole", listRole);
        model.addAttribute("message", "");
        if (StringUtils.isEmpty(user.getUsername())) {
            model.addAttribute("message", "Hãy nhập tài khoản");
            model.addAttribute("errorClass", "has-error");
            return "userAdd";
        }
        List<User> listUser = userDao.getUsetByUsername(user.getUsername());
        if (!listUser.isEmpty()) {
            model.addAttribute("message", "Tên tài khoản đã tồn tại");
            model.addAttribute("errorClass", "has-error");
            return "userAdd";
        }
        
        if (StringUtils.isEmpty(user.getFirstName())) {
            model.addAttribute("message", "Hãy nhập tên họ");
            model.addAttribute("errFirtName", "has-error");
            return "userAdd";
        }
        
        if (StringUtils.isEmpty(user.getLastName())) {
            model.addAttribute("message", "Hãy nhập tên");
            model.addAttribute("errLastName", "has-error");
            return "userAdd";
        }
        
        if (StringUtils.isEmpty(user.getDisplayName())) {
            model.addAttribute("message", "Hãy nhập tên hiển thị");
            model.addAttribute("errDisplayName", "has-error");
            return "userAdd";
        }
        
        if (!CommonFunc.isValidEmailAddress(user.getEmail())) {
            model.addAttribute("message", "Email bạn nhập không đúng định dạng");
            model.addAttribute("errEmail", "has-error");
            return "userAdd";
        }
        
        Date date = new Date();
        try {
            if (date.compareTo(CommonFunc.convertStringToDate(user.getBirthday())) < 0) {
                model.addAttribute("message", "Ngày sinh bạn nhập lớn hơn ngày hiện tại");
                model.addAttribute("errDOB", "has-error");
                return "userAdd";
            }
        } catch (Exception e) {
            model.addAttribute("message", "SAi định dạng ngày tháng");
            model.addAttribute("errDOB", "has-error");
            return "userAdd";
        }
        
        int id = (int) session.getAttribute("id");
        user.setUpdatedBy(id);
        user.setCreatedBy(id);
        boolean result = userDao.insertUser(user);
        if (result) {
            return "redirect:getAllUser.htm";
        } else {
            model.addAttribute("message", "Thêm mới không thành công.");
            model.addAttribute("listRole", listRole);
            return "userAdd";
        }
    }
    
    @RequestMapping(value = "initUpdateUser")
    public String initUpdateUser(@RequestParam("id") int id, Model model) {
        User user =  userDao.getUserById(id);
        if(user.getUsername() == null){
            return "404-page";
        }
        String birthdayUS = CommonFunc.convertStringDateUKUS(user.getBirthday());
        user.setBirthday(birthdayUS);
        model.addAttribute("updateUser", user);
        List<Role> listRole = userDao.getAllRole();
        model.addAttribute("listRole", listRole);
        return "userUpdate";
    }
    
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("updateUser") User user, Model model, HttpSession session) {
        List<Role> listRole = userDao.getAllRole();
        model.addAttribute("listRole", listRole);
        model.addAttribute("message", "");
        
        if(!user.getPassword().equals(user.getRePassword())){
            model.addAttribute("message", "Mật khẩu xác nhận phải khớp với mật khẩu đã nhập");
            model.addAttribute("errRePassword", "has-error");
            return "userUpdate";
        }
        
        if (StringUtils.isEmpty(user.getFirstName())) {
            model.addAttribute("message", "Hãy nhập tên họ");
            model.addAttribute("errFirtName", "has-error");
            return "userUpdate";
        }
        
        if (StringUtils.isEmpty(user.getLastName())) {
            model.addAttribute("message", "Hãy nhập tên");
            model.addAttribute("errLastName", "has-error");
            return "userUpdate";
        }
        
        if (StringUtils.isEmpty(user.getDisplayName())) {
            model.addAttribute("message", "Hãy nhập tên hiển thị");
            model.addAttribute("errDisplayName", "has-error");
            return "userUpdate";
        }
        
        if (!CommonFunc.isValidEmailAddress(user.getEmail())) {
            model.addAttribute("message", "Email bạn nhập không đúng định dạng");
            model.addAttribute("errEmail", "has-error");
            return "userUpdate";
        }
        
        Date date = new Date();
        try {
            if (date.compareTo(CommonFunc.convertStringToDate(CommonFunc.convertStringDateUSUK(user.getBirthday()))) < 0) {
                model.addAttribute("message", "Ngày sinh bạn nhập lớn hơn ngày hiện tại");
                model.addAttribute("errDOB", "has-error");
                return "userUpdate";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Sai định dạng ngày tháng");
            model.addAttribute("errDOB", "has-error");
            return "userUpdate";
        }
        
        int id = (int) session.getAttribute("id");
        user.setUpdatedBy(id);

        boolean result = userDao.updateUser(user);
        if (result) {
            return "redirect:getAllUser.htm";
        } else {
            model.addAttribute("message", "Chỉnh sửa không thành công.");
            model.addAttribute("listRole", listRole);
            return "userUpdate";
        }
    }
    
    @RequestMapping(value = "initDetailUser")
    public String initDetailUser(@RequestParam("id") int id, Model model) {
        User user =  userDao.getUserById(id);
        if(user.getUsername() == null){
            return "404-page";
        }

        model.addAttribute("user", user);
        User userCreated =  userDao.getUserById(user.getCreatedBy());
        User userUpdated =  userDao.getUserById(user.getUpdatedBy());
        model.addAttribute("userCreated", userCreated);
        model.addAttribute("userUpdated", userUpdated);
        List<Role> listRole = userDao.getAllRole();
        model.addAttribute("listRole", listRole);
        return "userDetail";
    }
    
    @RequestMapping(value = "deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        boolean result = userDao.deleteUser(id);
        if (result) {
            return "redirect:getAllUser.htm";
        } else {
            return "500-page";
        }
    }
}
