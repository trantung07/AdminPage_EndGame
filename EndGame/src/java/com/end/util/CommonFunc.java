/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.end.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
 *
 * @author Tran Tung
 */
public class CommonFunc {

    final static String DATE_FORMAT = "dd/MM/yyyy";
    final static String EMAIL_FORMAT = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Date convertDateSqlToUtil(java.sql.Date sqlDate) {
        Date utilDate = null;
        if (sqlDate != null) {
            utilDate = new Date(sqlDate.getTime());
        }
        return utilDate;
    }

    public static java.sql.Date convertDateUtilToSql(Date utilDate) {
        java.sql.Date sqlDate = null;
        if (utilDate != null) {
            sqlDate = new java.sql.Date(utilDate.getTime());
        }
        return sqlDate;
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = EMAIL_FORMAT;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String convertDateToString(Date date) {
        Format formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }

    public static Date convertStringToDate(String dateInString) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        Date startDate = null;
        try {
            startDate = df.parse(dateInString);
            String newDateString = df.format(startDate);
            System.out.println(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public static String convertStringDateUKUS(String dateInString) {
        if(StringUtils.isEmpty(dateInString)){
            return "";
        }
        String[] dateParts = dateInString.split("/");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        return month + "/" + day + "/" + year;
    }

    public static String convertStringDateUSUK(String dateInString) {
        if(StringUtils.isEmpty(dateInString)){
            return "";
        }
        String[] dateParts = dateInString.split("/");
        String month = dateParts[0];
        String day = dateParts[1];
        String year = dateParts[2];

        return day + "/" + month + "/" + year;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static String encodeBase64(String video){
        File tempFile = new File(video);
        String encodedString = null;

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(tempFile);
        } catch (Exception e) {
            // TODO: handle exception
        }
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        encodedString = Base64.getEncoder().encodeToString(bytes);
        return encodedString;
    }
    
    public static void  decodeBase64(String encodedString, String video){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());
        String a = new String(decodedBytes);
        try {

            FileOutputStream out = new FileOutputStream("E:\\Videos\\coverr-workers-in-field-1561486234623.mp4");
            out.write(decodedBytes);
            out.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
    }
}
