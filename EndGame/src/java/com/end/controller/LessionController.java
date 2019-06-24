/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.controller;

import com.end.dao.LessionDao;
import com.end.dao.SubjectDao;
import com.end.dao.UserDao;
import com.end.entity.Lession;
import com.end.entity.Subject;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Tran Tung
 */
@Controller
public class LessionController {

    private SubjectDao subjectDao;
    private LessionDao lessionDao;
    private UserDao userDao;

    public LessionController() {
        subjectDao = new SubjectDao();
        lessionDao = new LessionDao();
        userDao = new UserDao();
    }

    @RequestMapping(value = "getAllLession")
    public String getAllLession(Model model, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            String page = request.getParameter("page") != null ? request.getParameter("page") : "1";
            String keyword = request.getParameter("name") != null ? request.getParameter("name") : "";
            int pageInt = Integer.parseInt(page);
            int sizeRowofPage = 4;// số sản phẩm trên 1 trang
            HashMap map1 = lessionDao.getDataPagination(pageInt, sizeRowofPage, keyword);

            String url = (String) map1.get("url");
            List<Lession> listLession = new ArrayList<>();
            ResultSet rs = null;
            try {
                rs = (ResultSet) map1.get("rs");
                while (rs.next()) {
                    Lession lession = new Lession();
                    lession.setId(rs.getInt("id"));
                    lession.setName(rs.getString("name"));
                    lession.setSubjectId(rs.getInt("subject_id"));

                    listLession.add(lession);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Connection conn = (Connection) map1.get("conn");
                CallableStatement callSt = (CallableStatement) map1.get("callSt");
                DBConnection.closeConnection(conn, callSt, rs);
            }
            List<Lession> list = lessionDao.getAllLession(keyword);
            List<Subject> listSub = subjectDao.getAllSubject();
            int startIndex = 1 + sizeRowofPage * (pageInt - 1);
            if(listLession.isEmpty()){
                startIndex = 0;
            }
            int endIndex = sizeRowofPage * pageInt;

            if (endIndex > list.size()) {
                 endIndex = list.size();
            }
            Lession les = new Lession();
            les.setName(keyword);
            model.addAttribute("searchLession", les);
            model.addAttribute("startIndex", startIndex);
            model.addAttribute("endIndex", endIndex);
            model.addAttribute("url", url);
            model.addAttribute("size", list.size());
            model.addAttribute("lessionList", listLession);
            model.addAttribute("subjectList", listSub);
            
            if (keyword.length() > 0) {
                String text = "với từ khóa tìm kiếm <span style=\"font-weight: bold\">" + keyword + "</span>";
                model.addAttribute("keySearch", text);
            }
            return "lessionList";
        }
    }

    @RequestMapping(value = "initInsertLession")
    public String initInsertLession(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            Lession lession = new Lession();
            model.addAttribute("newLession", lession);
            List<Subject> list = subjectDao.getAllSubject();
            model.addAttribute("listSubject", list);
            return "lessionAdd";
        }
    }

    @RequestMapping(value = "insertLession", method = RequestMethod.POST)
    public String insertLession(@ModelAttribute("newLession") Lession lession, Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        }
        if (StringUtils.isEmpty(lession.getName())) {
            model.addAttribute("message", "Hãy nhập tên khóa học");
            model.addAttribute("errorName", "has-error");
            return "lessionAdd";
        }

        int id = (int) session.getAttribute("id");
        lession.setUpdatedBy(id);
        lession.setCreatedBy(id);
        boolean result = lessionDao.insertLession(lession);
        if (result) {
            return "redirect:getAllLession.htm";
        } else {
            model.addAttribute("message", "Thêm mới không thành công.");
            return "lessionAdd";
        }
    }
    
    @RequestMapping(value = "initUpdateLession")
    public String initUpdateLession(@RequestParam("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            Lession lession = lessionDao.getLessionById(id);
            model.addAttribute("lessionUpdate", lession);
            List<Subject> list = subjectDao.getAllSubject();
            model.addAttribute("listSubject", list);
            return "lessionUpdate";
        }
    }
    
    @RequestMapping(value = "updateLession", method = RequestMethod.POST)
    public String updateLession(@ModelAttribute("lessionUpdate") Lession lession, Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        }
        if (StringUtils.isEmpty(lession.getName())) {
            model.addAttribute("message", "Hãy nhập tên khóa học");
            model.addAttribute("errorName", "has-error");
            List<Subject> list = subjectDao.getAllSubject();
            model.addAttribute("listSubject", list);
            return "lessionUpdate";
        }

        int id = (int) session.getAttribute("id");
        lession.setUpdatedBy(id);
        boolean result = lessionDao.updateLession(lession);
        if (result) {
            return "redirect:getAllLession.htm";
        } else {
            model.addAttribute("message", "Thêm mới không thành công.");
            return "lessionUpdate";
        }
    }
    
    @RequestMapping(value = "initDetailLession")
    public String initDetailLession(@RequestParam("id") int id, Model model) {
        Lession lession =  lessionDao.getLessionById(id);
        if(lession.getName() == null){
            return "404-page";
        }

        model.addAttribute("lession", lession);
        User userCreated =  userDao.getUserById(lession.getCreatedBy());
        User userUpdated =  userDao.getUserById(lession.getUpdatedBy());
        model.addAttribute("userCreated", userCreated);
        model.addAttribute("userUpdated", userUpdated);
        return "lessionDetail";
    }
    
    @RequestMapping(value = "deleteLession")
    public String deleteLession(@RequestParam("id") int id, HttpSession session) {
        int userId = (int) session.getAttribute("id");
        boolean result = lessionDao.deleteLession(id, userId);
        if (result) {
            return "redirect:getAllLession.htm";
        } else {
            return "500-page";
        }
    }
}
