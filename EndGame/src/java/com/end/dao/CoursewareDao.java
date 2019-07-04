/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.dao;

import com.end.entity.Courseware;
import com.end.entity.FileType;
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
public class CoursewareDao {

    public HashMap getDataPagination(int page, int pageSize, String keyword) {
        HashMap map1 = Pagination.getDataForPagination(page, pageSize, "tbl_courseware", "id", "getAllCourseware.htm", keyword);
        return map1;
    }

    public List<Courseware> getAllCourseware(String key) {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<Courseware> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getActiveCourseware(?)}");
            stm.setString(1, key);
            rs = stm.executeQuery();

            while (rs.next()) {
                Courseware courseware = new Courseware();
                courseware.setId(rs.getInt("id"));
                courseware.setName(rs.getString("name"));
                courseware.setLessionId(rs.getInt("lesson_id"));
                courseware.setLink(rs.getString("link"));
                courseware.setType(rs.getInt("type"));
                list.add(courseware);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursewareDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }

        return list;
    }
    
    public List<FileType> getAllType() {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<FileType> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getAllType()}");
            rs = stm.executeQuery();

            while (rs.next()) {
                FileType type = new FileType();
                type.setId(rs.getInt("id"));
                type.setTypeName(rs.getString("typeName"));
                list.add(type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursewareDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }

        return list;
    }

    public boolean insertCourseware(Courseware cou) {
        Connection con = null;
        CallableStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBConnection.openConnection();
            stm = con.prepareCall("{ call insertNewCourseware(?,?,?,?,?,?,?,?)}");
            stm.setString(1, cou.getName());
            stm.setInt(2, cou.getLessionId());
            stm.setString(3, cou.getLink());
            stm.setString(4, cou.getFileName());
            stm.setInt(5, cou.getType());
            stm.setString(6, cou.getDescription());
            stm.setInt(7, cou.getCreatedBy());
            stm.setInt(8, cou.getUpdatedBy());
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CoursewareDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return result;
    }
    
    public Courseware getCoursewareById(int id) {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        con = DBConnection.openConnection();
        Courseware ware = new Courseware();
        try {
            stm = con.prepareCall("{ call getCourewareByID(?)}");
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                ware.setId(rs.getInt("id"));
                ware.setName(rs.getString("name"));
                ware.setLessionId(rs.getInt("lesson_id"));
                ware.setDescription(rs.getString("description"));
                ware.setLink(rs.getString("link"));
                ware.setType(rs.getInt("type"));
                ware.setCreatedBy(rs.getInt("create_by"));
                ware.setCreatedAt(rs.getString("create_at"));
                ware.setUpdatedBy(rs.getInt("update_by"));
                ware.setUpdatedAt(rs.getString("update_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursewareDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }

        return ware;
    }
    
    public boolean updateCourseware(Courseware ware) {
        Connection con = null;
        CallableStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBConnection.openConnection();
            stm = con.prepareCall("{ call updateCoureware(?,?,?,?,?,?,?,?)}");
            stm.setInt(1, ware.getId());
            stm.setString(2, ware.getName());
            stm.setInt(3, ware.getLessionId());
            stm.setString(4, ware.getLink());
            stm.setString(5, ware.getFileName());
            stm.setString(6, ware.getDescription());
            stm.setInt(7, ware.getType());
            stm.setInt(8, ware.getUpdatedBy());
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CoursewareDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return result;
    }
    
    public boolean deleteCourseware(int id, int userId) {
        Connection con = null;
        CallableStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBConnection.openConnection();
            stm = con.prepareCall("{ call deleteCoureware(?,?)}");
            stm.setInt(1, id);
            stm.setInt(2, userId);
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CoursewareDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return result;
    }
}
