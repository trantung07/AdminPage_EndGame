/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.dao;

import com.end.entity.Role;
import com.end.entity.User;
import com.end.util.CommonFunc;
import com.end.util.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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
    
    public List<User> getAllUser() {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getActiveUsers()}");
            rs = stm.executeQuery();
            
            while (rs.next()) {
                User us = new User();
                us.setId(rs.getInt("id"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                us.setFirstName(rs.getString("first_name"));
                us.setLastName(rs.getString("last_name"));
                us.setDisplayName(rs.getString("display_name"));
                us.setEmail(rs.getString("email"));
                us.setBirthday(CommonFunc.convertDateSqlToUtil(rs.getDate("birthday")));
                us.setSex(rs.getInt("sex"));
                
                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
//                us.setRole(role);
                us.setRoleId(rs.getInt("role_id"));
                us.setStatus(rs.getBoolean("status"));
                list.add(us);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return list;
    }
    
    public List<User> getUsetByUsername(String username) {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getUsetByUsername(?)}");
            stm.setString(1, username);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                User us = new User();
                us.setId(rs.getInt("id"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                us.setFirstName(rs.getString("first_name"));
                us.setLastName(rs.getString("last_name"));
                us.setDisplayName(rs.getString("display_name"));
                us.setEmail(rs.getString("email"));
                us.setBirthday(CommonFunc.convertDateSqlToUtil(rs.getDate("birthday")));
                us.setSex(rs.getInt("sex"));
                us.setRoleId(rs.getInt("role_id"));
                us.setStatus(rs.getBoolean("status"));
                list.add(us);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return list;
    }
    public List<Role> getAllRole() {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<Role> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getAllRole()}");
            rs = stm.executeQuery();
            
            while (rs.next()) {
                
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleName(rs.getString("role_name"));

                list.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return list;
    }
    
    public boolean insertUser(User user){
        Connection con = null;
        CallableStatement stm = null;
        boolean result = false;    
        ResultSet rs = null;
        try {
            con = DBConnection.openConnection();
            stm = con.prepareCall("{ call insertUser(?,?,?,?,?,?,?,?,?,?,?,?)}");
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setInt(3, user.getRoleId());
            stm.setString(4, user.getFirstName());
            stm.setString(5, user.getLastName());
            stm.setString(6, user.getDisplayName());
            
            stm.setString(7, user.getEmail());
            stm.setDate(8, CommonFunc.convertDateUtilToSql(user.getBirthday()));
            stm.setString(9, user.getPhone());
            stm.setInt(10, user.getSex());
            stm.setInt(11, user.getCreatedBy());
            stm.setInt(12, user.getUpdatedBy());
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DBConnection.closeConnection(con, stm, rs);
        }
        return result;
    }
}
