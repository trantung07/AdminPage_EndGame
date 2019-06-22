/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.controller;

import com.end.dao.CourseDao;
import com.end.dao.UserDao;
import com.end.entity.Course;
import com.end.entity.User;
import com.end.util.CommonFunc;
import java.util.Date;
import java.util.List;
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
public class CourseController {

    private CourseDao courseDao;
    private UserDao userDao;

    public CourseController() {
        courseDao = new CourseDao();
        userDao = new UserDao();
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
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } 
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
                model.addAttribute("message", "Sai định dạng ngày tháng");
                model.addAttribute("errDate", "has-error");
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

    @RequestMapping(value = "initUpdateCourse")
    public String initUpdateCourse(@RequestParam("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        }  
        Course course = courseDao.getCourseById(id);
        if (course.getName() == null) {
            return "404-page";
        }
        
        String startDateUS = CommonFunc.convertStringDateUKUS(course.getStartDate());
        course.setStartDate(startDateUS);
        
        String endDateUS = CommonFunc.convertStringDateUKUS(course.getEndDate());
        course.setEndDate(endDateUS);

        model.addAttribute("updateCourse", course);
        return "courseUpdate";
    }

    @RequestMapping(value = "updateCourse", method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute("updateCourse") Course course, Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            User user = new User();
            model.addAttribute("user", user);
            return "login";
        } 
        if (StringUtils.isEmpty(course.getName())) {
            model.addAttribute("message", "Hãy nhập tên khóa học");
            model.addAttribute("errorName", "has-error");
            model.addAttribute("updateCourse", course);
            return "courseUpdate";
        }

        if (!(StringUtils.isEmpty(course.getStartDate()) && StringUtils.isEmpty(course.getEndDate()))) {
            try {
                Date startDate = CommonFunc.convertStringToDate(course.getStartDate());
                Date endDate = CommonFunc.convertStringToDate(course.getEndDate());
                if (startDate.compareTo(endDate) > 0) {
                    model.addAttribute("message", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
                    model.addAttribute("updateCourse", course);
                    model.addAttribute("errDate", "has-error");
                    return "courseUpdate";
                }
            } catch (Exception e) {
                model.addAttribute("message", "Sai định dạng ngày tháng");
                model.addAttribute("errDate", "has-error");
                model.addAttribute("updateCourse", course);
                return "courseUpdate";
            }
        }

        int id = (int) session.getAttribute("id");
        course.setUpdatedBy(id);

        boolean result = courseDao.updateCourse(course);
        if (result) {
            return "redirect:getAllCourse.htm";
        } else {
            model.addAttribute("message", "Chỉnh sửa không thành công.");
            return "courseUpdate";
        }
    }
    
    @RequestMapping(value = "initDetailCourse")
    public String initDetailCourse(@RequestParam("id") int id, Model model) {
        Course course =  courseDao.getCourseById(id);
        if(course.getName() == null){
            return "404-page";
        }

        model.addAttribute("course", course);
        User userCreated =  userDao.getUserById(course.getCreatedBy());
        User userUpdated =  userDao.getUserById(course.getUpdatedBy());
        model.addAttribute("userCreated", userCreated);
        model.addAttribute("userUpdated", userUpdated);
        return "courseDetail";
    }
    
    @RequestMapping(value = "deleteCourse")
    public String deleteCourse(@RequestParam("id") int id, HttpSession session) {
        int userId = (int) session.getAttribute("id");
        boolean result = courseDao.deleteCourse(id, userId);
        if (result) {
            return "redirect:getAllCourse.htm";
        } else {
            return "500-page";
        }
    }
}
