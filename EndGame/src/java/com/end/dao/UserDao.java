/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.dao;

import com.end.entity.User;
import com.end.util.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Tung
 */
public class UserDao {

    public User checkLoginAdminPage(User user) {
        boolean result = false;
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        User us = new User();
                
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call checkLogin(?,?)}");
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            rs = stm.executeQuery();
            while (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                us.setFirstName(rs.getString("first_name"));
                us.setLastName(rs.getString("last_name"));
                us.setDisplayName(rs.getString("display_name"));
                us.setAge(rs.getInt("age"));
                us.setEmail(rs.getString("email"));
                us.setSex(rs.getInt("sex"));
                us.setStatus(rs.getBoolean("status"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return us;
    }
}
