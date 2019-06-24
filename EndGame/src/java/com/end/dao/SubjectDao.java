/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.dao;

import com.end.entity.Subject;
import com.end.entity.User;
import com.end.util.CommonFunc;
import com.end.util.DBConnection;
import com.end.util.Pagination;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Tung
 */
public class SubjectDao {

    public List<Subject> getAllSubject() {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<Subject> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getActiveSubject()}");
            rs = stm.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("name"));
                subject.setDescription(rs.getString("description"));
                list.add(subject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        
        return list;
    }
    
    public Subject getSubjectById(int id) {
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs = null;
        Subject s = new Subject();
        try {
            conn = DBConnection.openConnection();
            calla = conn.prepareCall("{call getSubjectByID(?)}");
            calla.setInt(1, id);
            rs = calla.executeQuery();
            while (rs.next()) {
                s.setCourseId(rs.getInt("courseId"));
                s.setCreatedAt(rs.getString("create_at"));
                s.setCreatedBy(rs.getInt("create_by"));
                s.setDescription(rs.getString("description"));
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setUpdatedAt(rs.getString("update_at"));
                s.setUpdatedBy(rs.getInt("update_by"));
                s.setdelete(rs.getBoolean("deleted"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(conn, calla, rs);
        }
        return s;
    }
    
    public boolean updateSubject(Subject subject){
        Connection conn = null;
        CallableStatement calla = null;
        ResultSet rs = null;
        boolean result = false;
        
        try {
            conn = DBConnection.openConnection();
            calla = conn.prepareCall("{call updateSubject(?,?,?,?,?)}");
            calla.setInt(1, subject.getId());
            calla.setString(2, subject.getName());
            calla.setString(3, subject.getDescription());
            calla.setInt(4, subject.getCourseId());
            calla.setInt(5, subject.getUpdatedBy());
            calla.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DBConnection.closeConnection(conn, calla, rs);
        }
        return result;
    }
    
    
    public boolean deleteSubject(int id) {
        Connection con = null;
        CallableStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBConnection.openConnection();
            stm = con.prepareCall("{ call deleteSubject(?)}");
            stm.setInt(1, id);
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return result;
    }
    
    public HashMap getDataPagination(int page, int pageSize, String keyword) {
        HashMap map1 = Pagination.getDataForPagination(page, pageSize, "tbl_subject", "id", "getAllSubject.htm", keyword);
        return map1;
    }
}
