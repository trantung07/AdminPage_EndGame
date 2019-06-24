/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.controller;

import com.end.dao.CourseDao;
import com.end.dao.SubjectDao;
import com.end.entity.Course;
import com.end.entity.Role;
import com.end.entity.Subject;
import com.end.entity.User;
import com.end.util.CommonFunc;
import com.end.util.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hung
 */
@Controller
public class SubjectController {
    private SubjectDao subjectDao;

    public SubjectController() {
        subjectDao = new SubjectDao();
    }
    
    
    @RequestMapping(value = "getAllSubject")
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
            HashMap map1 = subjectDao.getDataPagination(pageInt, sizeRowofPage, null);

            String url = (String) map1.get("url");
            List<Subject> userPaging = new ArrayList<>();
            ResultSet rs = null;
            try {
                rs = (ResultSet) map1.get("rs");
                while (rs.next()) {
                    Subject s = new Subject();
                    s.setId(rs.getInt("id"));
                    s.setCreatedAt(rs.getString("create_at"));
                    s.setCourseId(rs.getInt("courseId"));
                    s.setCreatedBy(rs.getInt("create_by"));
                    s.setdelete(rs.getBoolean("deleted"));
                    s.setDescription(rs.getString("description"));
                    s.setName(rs.getString("name"));
                    s.setUpdatedAt(rs.getString("update_at"));
                    s.setUpdatedBy(rs.getInt("update_by"));
                    userPaging.add(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Connection conn = (Connection) map1.get("conn");
                CallableStatement callSt = (CallableStatement) map1.get("callSt");
                DBConnection.closeConnection(conn, callSt, rs);
            }

            List<Course> listCourse = new CourseDao().getAllCourse();
            List<Subject> list = subjectDao.getAllSubject();
            int startIndex = 1 + sizeRowofPage * (pageInt -1);
            
            if(userPaging.isEmpty()){
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
            model.addAttribute("CourseList", listCourse);
            model.addAttribute("SubjectList", userPaging);
            
            return "subjectList";
        }
    }
    
    
    @RequestMapping(value = "initDetailSubject")
    public String initDetailUser(@RequestParam("id") int id, Model model) {
        Subject subject = subjectDao.getSubjectById(id);
        if (subject.getName()== null) {
            return "404-page";
        }

        model.addAttribute("subject", subject);
        Subject subjectCreated = subjectDao.getSubjectById(subject.getCreatedBy());
        Subject subjectUpdated = subjectDao.getSubjectById(subject.getUpdatedBy());
        model.addAttribute("subjectCreated", subjectCreated);
        model.addAttribute("subjectUpdated", subjectUpdated);
        List<Course> listCourse = new CourseDao().getAllCourse();
        model.addAttribute("listCourse", listCourse);
        return "subjectDetail";
    }
    
    @RequestMapping(value = "initUpdateSubject")
    public String initUpdateUser(@RequestParam("id") int id, Model model) {
        Subject subject = subjectDao.getSubjectById(id);
        if (subject.getName()== null) {
            return "404-page";
        }
        
        
        model.addAttribute("updateSubject", subject);
        List<Course> listCourse = new CourseDao().getAllCourse();
        model.addAttribute("listCourse", listCourse);
        return "subjectUpdate";
    }
    
    @RequestMapping(value = "updateSubject", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("updateSubject") Subject subject, Model model, HttpSession session) {
        List<Course> listCourse = new CourseDao().getAllCourse();
        model.addAttribute("listCourse", listCourse);
        model.addAttribute("message", "");

        if (StringUtils.isEmpty(subject.getName())) {
            model.addAttribute("message", "Hãy nhập tên");
            model.addAttribute("errLastName", "has-error");
            return "subjectUpdate";
        }

        if (StringUtils.isEmpty(subject.getDescription())) {
            model.addAttribute("message", "Hãy nhập mô tả");
            model.addAttribute("errDisplayName", "has-error");
            return "subjectUpdate";
        }


        int id = (int) session.getAttribute("id");
        subject.setUpdatedBy(id);

        boolean result = subjectDao.updateSubject(subject);
        if (result) {
            return "redirect:getAllSubject.htm";
        } else {
            model.addAttribute("message", "Chỉnh sửa không thành công.");
            model.addAttribute("listCourse", listCourse);
            return "subjectUpdate";
        }
    }
    
    @RequestMapping(value = "deleteSubject")
    public String deleteSubject(@RequestParam("id") int id) {
        boolean result = subjectDao.deleteSubject(id);
        if (result) {
            return "redirect:getAllSubject.htm";
        } else {
            return "500-page";
        }
    }
}
