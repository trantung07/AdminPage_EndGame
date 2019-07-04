/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.dao;

import com.end.entity.Order;
import com.end.entity.OrderDetail;
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
 * @author hung
 */
public class OrderDao {

    public List<Order> getAllOrder() {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<Order> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getActiveOrder()}");
            rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setCreateAt(rs.getString("create_at"));
                o.setCreateBy(rs.getInt("create_by"));
                o.setDeleted(rs.getBoolean("deleted"));
                o.setStatus(rs.getBoolean("status"));
                o.setTotalCost(rs.getInt("total_cost"));
                o.setUpdateAt(rs.getString("update_at"));
                o.setUpdateBy(rs.getInt("update_by"));
                o.setUsersId(rs.getInt("user_id"));
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return list;
    }

    public List<OrderDetail> getAllOrderDetail(int orderId) {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<OrderDetail> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getActiveOrderDetail(?)}");
            stm.setInt(1, orderId);
            rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail o = new OrderDetail();
                o.setId(rs.getInt("id"));
                o.setOrdersId(rs.getInt("order_id"));
                o.setCoursesId(rs.getInt("course_id"));
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return list;
    }
    
    public List<Order> getAllOrderLimit() {
        Connection con = null;
        CallableStatement stm = null;
        ResultSet rs = null;
        List<Order> list = new ArrayList<>();
        con = DBConnection.openConnection();
        try {
            stm = con.prepareCall("{ call getLimitActiveOrder()}");
            rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setCreateAt(rs.getString("create_at"));
                o.setCreateBy(rs.getInt("create_by"));
                o.setDeleted(rs.getBoolean("deleted"));
                o.setStatus(rs.getBoolean("status"));
                o.setTotalCost(rs.getInt("total_cost"));
                o.setUpdateAt(rs.getString("update_at"));
                o.setUpdateBy(rs.getInt("update_by"));
                o.setUsersId(rs.getInt("user_id"));
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(con, stm, rs);
        }
        return list;
    }

    public HashMap getDataPagination(int page, int pageSize, String keyword) {
        HashMap map1 = Pagination.getDataForPagination(page, pageSize, "orders", "id", "getAllOrder.htm", keyword);
        return map1;
    }
}
