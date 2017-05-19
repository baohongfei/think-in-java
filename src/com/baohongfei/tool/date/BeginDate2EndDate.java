package com.baohongfei.tool.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by terry on 6/14/16.
 */
public class BeginDate2EndDate {

    private static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static void main(String[] args) {
        String beginDateStr = "2016-04-29";
        String endDateStr = "2016-05-30";

        List<Map<String, Object>> allDays = new ArrayList();

        String currentDay = endDateStr;

        while (currentDay.compareTo(beginDateStr)>=0){
            Map<String,Object> entry = new HashMap<String, Object>();
            entry.put("date",currentDay);
            allDays.add(entry);
            currentDay = getDayOff(currentDay,-1);
        }
        System.out.println(allDays);
    }


    public static String getDayOff(String dateStr, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        try {
            Date date = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, day);
            return new SimpleDateFormat(FORMAT_YYYY_MM_DD).format(cal.getTime());
        } catch (ParseException e) {
        }
        return null;
    }


}
