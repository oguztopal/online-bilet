package com.oguztopal.onlinebilet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class VTUtil {

    public static final String strDateFormat = "dd/MM/yyyy";
    public static final String strDateFormatVademecum = "yyyy-mm-dd";

    public static Integer reqGetInteger(String reqStr, Integer defaultVal) {
        if (reqStr != null && !reqStr.equals("") && !reqStr.equals("null")) {
            return Integer.valueOf(reqStr);
        }
        return defaultVal;
    }

    public static Long reqGetLong(String reqStr, Long defaultVal) {
        if (reqStr != null && !reqStr.equals("")) {
            return Long.valueOf(reqStr);
        }
        return defaultVal;
    }

    public static String reqGetString(String reqStr, String defaultVal) {
        if (reqStr != null && !reqStr.equals("")) {
            return reqStr;// .replaceAll("Ã", "Ü").replaceAll("Ä°", "İ").replaceAll("A¼", "ü").replaceAll("Ä", "Ğ");
        }
        return defaultVal;
    }

    public static Date reqGetDate(String reqStr, Date defaultVal, String format) {
        if (reqStr != null && !reqStr.equals("")) {
            SimpleDateFormat sdf;
            if (format == null) {
                sdf = new SimpleDateFormat(strDateFormat);
            } else {
                sdf = new SimpleDateFormat(format);
            }
            try {
                return sdf.parse(reqStr);
            } catch (ParseException e) {
                return null;
            }
        }
        return defaultVal;
    }
    public static Boolean reqGetBoolean(String reqStr, Boolean defaultVal) {
        try {
            if (reqStr != null && !reqStr.equals("")) {
                return Boolean.valueOf(reqStr);
            }
        } catch (Exception e) {
            return null;
        }
        return defaultVal;
    }


    public static Date saatEkle(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
    public static long dakikaFarki(Date eski) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date1= sdf.format(new Date());
        String date2= sdf.format(eski);
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        long fark = d2.getTime() - d1.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(fark);
    }

    public static Date dakikaEkle(Date date , long dakika) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE,(int)dakika);
        String format = sdf.format(cal.getTime());
        date =  sdf.parse(format);
        return date;
    }
}
