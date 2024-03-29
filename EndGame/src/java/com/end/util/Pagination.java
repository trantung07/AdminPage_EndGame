/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;

/**
 *
 * @author Tran Tung
 */
public class Pagination {
    public static HashMap getDataForPagination(int page, int pageSize, String tableName, String tableId,
            String actionName, String keyword) {
        HashMap map = new HashMap();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = DBConnection.openConnection();
            callSt = conn.prepareCall("{call displayPageNo(?,?,?,?,?,?)}");
            callSt.setInt(1, page);
            callSt.setInt(2, pageSize);
            callSt.setString(3, tableName);
            callSt.setString(4, actionName);
            callSt.setString(5, keyword);
            callSt.registerOutParameter(6, Types.NVARCHAR);
            callSt.execute();
            String url = callSt.getString(6);
            map.put("url", url);
            callSt = conn.prepareCall("{call paging(?,?,?,?,?)}");
            callSt.setInt(1, page);
            callSt.setInt(2, pageSize);
            callSt.setString(3, tableName);
            callSt.setString(4, tableId);
            callSt.setString(5, keyword);
            ResultSet rs = callSt.executeQuery();
            map.put("rs", rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("conn", conn);
        map.put("callSt", callSt);
        return map;
    }
}
