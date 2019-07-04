/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.controller;

import com.end.dao.CoursewareDao;
import com.end.dao.LessionDao;
import com.end.dao.UserDao;
import com.end.entity.Courseware;
import com.end.entity.FileType;
import com.end.entity.Lession;
import com.end.entity.User;
import com.end.util.DBConnection;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
public class CoursewareController {

    private CoursewareDao wareDao;
    private LessionDao lessionDao;
    private UserDao userDao;

    public CoursewareController() {
        wareDao = new CoursewareDao();
        lessionDao = new LessionDao();
        userDao = new UserDao();
    }

    @RequestMapping(value = "getAllCourseware")
    public String getAllCourseware(Model model, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            String page = request.getParameter("page") != null ? request.getParameter("page") : "1";
            String keyword = request.getParameter("name") != null ? request.getParameter("name") : "";
            int pageInt = Integer.parseInt(page);
            int sizeRowofPage = 4;// số sản phẩm trên 1 trang
            HashMap map1 = wareDao.getDataPagination(pageInt, sizeRowofPage, keyword);

            String url = (String) map1.get("url");
            List<Courseware> listCourseware = new ArrayList<>();
            ResultSet rs = null;
            try {
                rs = (ResultSet) map1.get("rs");
                while (rs.next()) {
                    Courseware courseware = new Courseware();
                    courseware.setId(rs.getInt("id"));
                    courseware.setName(rs.getString("name"));
                    courseware.setLessionId(rs.getInt("lesson_id"));
                    courseware.setLink(rs.getString("link"));
                    courseware.setType(rs.getInt("type"));
                    listCourseware.add(courseware);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Connection conn = (Connection) map1.get("conn");
                CallableStatement callSt = (CallableStatement) map1.get("callSt");
                DBConnection.closeConnection(conn, callSt, rs);
            }

            List<Courseware> list = wareDao.getAllCourseware(keyword);
            List<Lession> listLes = lessionDao.getAllLession("");
            int startIndex = 1 + sizeRowofPage * (pageInt - 1);
            if (listCourseware.isEmpty()) {
                startIndex = 0;
            }
            int endIndex = sizeRowofPage * pageInt;

            if (endIndex > list.size()) {
                endIndex = list.size();
            }
            Courseware ware = new Courseware();
            ware.setName(keyword);
            List<FileType> listType = wareDao.getAllType();
            model.addAttribute("listType", listType);
            model.addAttribute("searchCourseware", ware);
            model.addAttribute("startIndex", startIndex);
            model.addAttribute("endIndex", endIndex);
            model.addAttribute("url", url);
            model.addAttribute("size", list.size());
            model.addAttribute("coursewareList", listCourseware);
            model.addAttribute("lessionList", listLes);

            if (keyword.length() > 0) {
                String text = "với từ khóa tìm kiếm <span style=\"font-weight: bold\">" + keyword + "</span>";
                model.addAttribute("keySearch", text);
            }
            return "coursewareList";
        }
    }

    @RequestMapping(value = "initInsertCourseware")
    public String initInsertCourseware(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            Courseware ware = new Courseware();
            model.addAttribute("newCourseware", ware);
            List<Lession> list = lessionDao.getAllLession("");
            model.addAttribute("listLession", list);
            List<FileType> listType = wareDao.getAllType();
            model.addAttribute("listType", listType);
            return "coursewareAdd";
        }
    }

    @RequestMapping(value = "insertCourseware", method = RequestMethod.POST)
    public String insertCourseware(@ModelAttribute("newCourseware") Courseware ware, Model model, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        }

        List<String> file = new ArrayList<>();
        String path = request.getSession().getServletContext().getRealPath("/jsp/file/");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\jsp\\file";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload uploader = new ServletFileUpload(factory);
        try {
            List<FileItem> lst = uploader.parseRequest(request);
            for (FileItem fileItem : lst) {
                if (fileItem.isFormField() == false) {
                    if (StringUtils.isEmpty(fileItem.getName())) {
                        model.addAttribute("message", "Hãy nhập file");
                        model.addAttribute("errorFile", "has-error");
                        List<Lession> list = lessionDao.getAllLession("");
                        model.addAttribute("listLession", list);
                        List<FileType> listType = wareDao.getAllType();
                        model.addAttribute("listType", listType);
                        return "coursewareAdd";
                    }
                    ware.setLink(fileItem.getName());
                    //upload to folder
                    file.add(fileItem.getName());
                    fileItem.write(new File(path + "/" + fileItem.getName()));
                } else {
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    if (name.equals("name")) {
                        ware.setName(value);
                    } else if (name.equals("lessionId")) {
                        ware.setLessionId(Integer.parseInt(value));
                    } else if (name.equals("description")) {
                        ware.setDescription(value);
                    } else if (name.equals("type")) {
                        ware.setType(Integer.parseInt(value));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("message", "Thêm mới không thành công.");
            return "coursewareAdd";
        }

        if (StringUtils.isEmpty(ware.getName())) {
            model.addAttribute("message", "Hãy nhập tên học liệu");
            model.addAttribute("errorName", "has-error");
            List<Lession> list = lessionDao.getAllLession("");
            model.addAttribute("listLession", list);
            List<FileType> listType = wareDao.getAllType();
            model.addAttribute("listType", listType);
            return "coursewareUpdate";
        }

        if (!checkFileTypeInput(ware.getLink(), ware.getType())) {
            model.addAttribute("message", "Hãy nhập đúng định dạng file");
            model.addAttribute("errorFile", "has-error");
            List<Lession> list = lessionDao.getAllLession("");
            model.addAttribute("listLession", list);
            List<FileType> listType = wareDao.getAllType();
            model.addAttribute("listType", listType);
            return "coursewareAdd";
        }

        int id = (int) session.getAttribute("id");
        ware.setUpdatedBy(id);
        ware.setCreatedBy(id);
        boolean result = wareDao.insertCourseware(ware);
        if (result) {

            return "redirect:getAllCourseware.htm";
        } else {
            model.addAttribute("message", "Thêm mới không thành công.");
            return "coursewareAdd";
        }
    }

    @RequestMapping(value = "initUpdateCourseware")
    public String initUpdateCourseware(@RequestParam("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            Courseware ware = wareDao.getCoursewareById(id);
            model.addAttribute("coursewareUpdate", ware);
            List<Lession> list = lessionDao.getAllLession("");
            model.addAttribute("listLession", list);
            List<FileType> listType = wareDao.getAllType();
            model.addAttribute("listType", listType);
            return "coursewareUpdate";
        }
    }

    @RequestMapping(value = "updateCourseware", method = RequestMethod.POST)
    public String updateCourseware(@ModelAttribute("coursewareUpdate") Courseware ware, Model model, HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        }

        List<String> file = new ArrayList<>();
        int wareId = 0;
        String path = request.getSession().getServletContext().getRealPath("/jsp/file/");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\jsp\\file";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload uploader = new ServletFileUpload(factory);
        try {
            List<FileItem> lst = uploader.parseRequest(request);
            for (FileItem fileItem : lst) {
                if (fileItem.isFormField() == false) {
                    if (StringUtils.isEmpty(fileItem.getName())) {
                        model.addAttribute("message", "Hãy nhập file");
                        model.addAttribute("errorFile", "has-error");
                        List<Lession> list = lessionDao.getAllLession("");
                        model.addAttribute("listLession", list);
                        List<FileType> listType = wareDao.getAllType();
                        model.addAttribute("listType", listType);
                        return "coursewareUpdate";
                    }
                    ware.setLink(fileItem.getName());

                    //upload to folder
                    file.add(fileItem.getName());
                    fileItem.write(new File(path + "/" + fileItem.getName()));
                } else {
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    if (name.equals("id")) {
                        wareId = Integer.parseInt(value);
                        ware.setId(wareId);
                    } else if (name.equals("name")) {
                        ware.setName(value);
                    } else if (name.equals("lessionId")) {
                        ware.setLessionId(Integer.parseInt(value));
                    } else if (name.equals("description")) {
                        ware.setDescription(value);
                    } else if (name.equals("type")) {
                        ware.setType(Integer.parseInt(value));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            List<Lession> list = lessionDao.getAllLession("");
            model.addAttribute("listLession", list);
            List<FileType> listType = wareDao.getAllType();
            model.addAttribute("listType", listType);
            model.addAttribute("message", "Chỉnh sửa không thành công.");
            return "coursewareUpdate";
        }

        if (StringUtils.isEmpty(ware.getName())) {
            model.addAttribute("message", "Hãy nhập tên học liệu");
            model.addAttribute("errorName", "has-error");
            List<Lession> list = lessionDao.getAllLession("");
            model.addAttribute("listLession", list);
            List<FileType> listType = wareDao.getAllType();
            model.addAttribute("listType", listType);
            return "coursewareUpdate";
        }
        
        if (!checkFileTypeInput(ware.getLink(), ware.getType())) {
            model.addAttribute("message", "Hãy nhập đúng định dạng file");
            model.addAttribute("errorFile", "has-error");
            List<Lession> list = lessionDao.getAllLession("");
            model.addAttribute("listLession", list);
            List<FileType> listType = wareDao.getAllType();
            model.addAttribute("listType", listType);
            return "coursewareUpdate";
        }

        int id = (int) session.getAttribute("id");
        ware.setUpdatedBy(id);
        boolean result = wareDao.updateCourseware(ware);
        if (result) {
            return "redirect:getAllCourseware.htm";
        } else {
            model.addAttribute("message", "Thêm mới không thành công.");
            return "coursewareUpdate";
        }
    }

    @RequestMapping(value = "initDetailCourseware")
    public String initDetailCourseware(@RequestParam("id") int id, Model model) {
        Courseware ware = wareDao.getCoursewareById(id);
        if (ware.getName() == null) {
            return "404-page";
        }

        model.addAttribute("ware", ware);
        List<Lession> list = lessionDao.getAllLession("");
        model.addAttribute("listLession", list);
        List<FileType> listType = wareDao.getAllType();
        model.addAttribute("listType", listType);
        User userCreated = userDao.getUserById(ware.getCreatedBy());
        User userUpdated = userDao.getUserById(ware.getUpdatedBy());
        model.addAttribute("userCreated", userCreated);
        model.addAttribute("userUpdated", userUpdated);
        return "coursewareDetail";
    }

    @RequestMapping(value = "deleteCourseware")
    public String deleteCourseware(@RequestParam("id") int id, HttpSession session) {
        int userId = (int) session.getAttribute("id");
        boolean result = wareDao.deleteCourseware(id, userId);
        if (result) {
            return "redirect:getAllCourseware.htm";
        } else {
            return "500-page";
        }
    }

    private boolean checkFileTypeInput(String fileName, int type) {
        if (type == 1 && fileName.substring(fileName.length() - 4).toLowerCase().contains("mp4")) {
            return true;
        }

        if (type == 2 && fileName.substring(fileName.length() - 4).toLowerCase().equals("docx")) {
            return true;
        }

        if (type == 3 && fileName.substring(fileName.length() - 4).toLowerCase().equals("xlxs")) {
            return true;
        }

        if (type == 4 && fileName.substring(fileName.length() - 4).toLowerCase().equals("pptx")) {
            return true;
        }

        return false;
    }

}
