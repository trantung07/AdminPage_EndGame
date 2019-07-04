/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.dao;

import com.end.entity.Course;
import com.end.util.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Tung
 */
public class CourseDao {
    public List<Course> getAllCourse(){
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getActiveCourse()}");
            rs = stm.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setStartDate(rs.getString("start_date"));
                course.setEndDate(rs.getString("end_date"));
                course.setPrice(rs.getInt("course_price"));
                list.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        
        return list;
    }
    
    public boolean insertCourse(Course cou) {
        Connection con = null;
        CallableStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBConnection.openConnection();
            stm = con.prepareCall("{ call insertNewCourse(?,?,?,?,?,?,?)}");
            stm.setString(1, cou.getName());
            stm.setString(2, cou.getStartDate());
            stm.setString(3, cou.getEndDate());
            stm.setInt(4, cou.getPrice());
            stm.setString(5, cou.getDescription());
            stm.setInt(6, cou.getCreatedBy());
            stm.setInt(7, cou.getUpdatedBy());
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return result;
    }
    
    public Course getCourseById(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs = null;
        Course course = new Course();
        try {
            conn = DBConnection.openConnection();
            calla = conn.prepareCall("{call getCourseByID(?)}");
            calla.setInt(1, id);
            rs = calla.executeQuery();
            while (rs.next()) {
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDescription(rs.getString("description"));
                course.setStartDate(rs.getString("start_date"));
                course.setEndDate(rs.getString("end_date"));
                course.setPrice(rs.getInt("course_price"));
                course.setCreatedBy(rs.getInt("create_by"));
                course.setCreatedAt(rs.getString("create_at"));
                course.setUpdatedBy(rs.getInt("update_by"));
                course.setUpdatedAt(rs.getString("update_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(conn, calla, rs);
        }
        return course;
    }
    
    public boolean updateCourse(Course course){
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs = null;
        boolean result = false;
        
        try {
            conn = DBConnection.openConnection();
            calla = conn.prepareCall("{call updateCourse(?,?,?,?,?,?,?)}");
            calla.setInt(1, course.getId());
            calla.setString(2, course.getName());
            calla.setString(3, course.getStartDate());
            calla.setString(4, course.getEndDate());
            calla.setInt(5, course.getPrice());
            calla.setString(6, course.getDescription()); 
            calla.setInt(7, course.getUpdatedBy());

            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DBConnection.closeConnection(conn, calla, rs);
        }
        return result;
    }
    
    public boolean deleteCourse(int id, int userId) {
        Connection con = null;
        CallableStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBConnection.openConnection();
            stm = con.prepareCall("{ call deleteCourse(?,?)}");
            stm.setInt(1, id);
            stm.setInt(2, userId);
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return result;
    }
    
    public List<Course> getAllCourseLimit(){
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getLimitActiveCourse()}");
            rs = stm.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setStartDate(rs.getString("start_date"));
                course.setEndDate(rs.getString("end_date"));
                course.setPrice(rs.getInt("course_price"));
                list.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        
        return list;
    }
}
