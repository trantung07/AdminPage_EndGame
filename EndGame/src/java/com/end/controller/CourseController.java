/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.controller;

import com.end.dao.CourseDao;
import com.end.entity.Course;
import com.end.entity.Role;
import com.end.entity.User;
import com.end.util.CommonFunc;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Tran Tung
 */
@Controller
public class CourseController {

    private CourseDao courseDao;

    public CourseController() {
        courseDao = new CourseDao();
    }

    @RequestMapping(value = "getAllCourse")
    public String getAllCourse(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            Course course = new Course();
            model.addAttribute("course", course);
            return "login";
        } else {
            List<Course> list = courseDao.getAllCourse();
            model.addAttribute("courseList", list);
            return "courseList";
        }
    }

    @RequestMapping(value = "initInsertCourse")
    public String initInsertCourse(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } else {
            Course course = new Course();
            model.addAttribute("newCourse", course);
            return "courseAdd";
        }
    }

    @RequestMapping(value = "insertCourse", method = RequestMethod.POST)
    public String insertCourse(@ModelAttribute("newCourse") Course course, Model model, HttpSession session) {

        if (StringUtils.isEmpty(course.getName())) {
            model.addAttribute("message", "Hãy nhập tên khóa học");
            model.addAttribute("errorName", "has-error");
            return "courseAdd";
        }

        if (!(StringUtils.isEmpty(course.getStartDate()) && StringUtils.isEmpty(course.getEndDate()))) {
            try {
                Date startDate = CommonFunc.convertStringToDate(course.getStartDate());
                Date endDate = CommonFunc.convertStringToDate(course.getEndDate());
                if (startDate.compareTo(endDate) > 0) {
                    model.addAttribute("message", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
                    model.addAttribute("errDate", "has-error");
                    return "courseAdd";
                }
            } catch (Exception e) {
                model.addAttribute("message", "SAi định dạng ngày tháng");
                model.addAttribute("errDOB", "has-error");
                return "courseAdd";
            }
        }

        int id = (int) session.getAttribute("id");
        course.setUpdatedBy(id);
        course.setCreatedBy(id);
        boolean result = courseDao.insertCourse(course);
        if (result) {
            return "redirect:getAllCourse.htm";
        } else {
            model.addAttribute("message", "Thêm mới không thành công.");
            return "courseAdd";
        }
    }
}
