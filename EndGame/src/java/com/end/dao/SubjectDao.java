/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.dao;

import com.end.entity.Course;
import com.end.entity.Subject;
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
}
