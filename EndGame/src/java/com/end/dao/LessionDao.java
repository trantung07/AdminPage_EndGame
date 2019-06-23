/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.dao;

import com.end.entity.Lession;
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
public class LessionDao {

    public List<Lession> getAllLession(String key) {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<Lession> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getActiveLession(?)}");
            stm.setString(1, key);
            rs = stm.executeQuery();

            while (rs.next()) {
                Lession lession = new Lession();
                lession.setId(rs.getInt("id"));
                lession.setName(rs.getString("name"));
                lession.setSubjectId(rs.getInt("subject_id"));
                list.add(lession);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }

        return list;
    }

    public boolean insertLession(Lession lession) {
        Connection con = null;
        CallableStatement stm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            con = DBConnection.openConnection();
            stm = con.prepareCall("{ call insertNewLession(?,?,?,?,?)}");
            stm.setString(1, lession.getName());
            stm.setInt(2, lession.getSubjectId());
            stm.setString(3, lession.getDescription());
            stm.setInt(4, lession.getCreatedBy());
            stm.setInt(5, lession.getUpdatedBy());
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(LessionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return result;
    }
    
    public HashMap getDataPagination(int page, int pageSize, String keyword) {
        HashMap map1 = Pagination.getDataForPagination(page, pageSize, "tbl_lesson", "id", "getAllLession.htm", keyword);
        return map1;
    }
}
