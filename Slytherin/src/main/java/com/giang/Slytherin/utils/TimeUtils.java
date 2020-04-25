package com.giang.Slytherin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT_2 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_3 = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_FORMAT_4 = "dd/MM/yyyy HH:mm";
    public static final String DATE_FORMAT_5 = "dd/MM HH:mm";
    public static final String DATE_FORMAT_6 = "HH:mm";
    public static final String DATE_FORMAT_7 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_8 = "dd/MM";
    public static final String DATE_FORMAT_9 = "dd-MMM-yy";
    public static final String DATE_FORMAT_10 = "dd-MM-yyyy";
    public static final String DATE_FORMAT_11 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_12 = "HH";
    public static final String DATE_FORMAT_14 = "mm";
    public static final String DATE_FORMAT_18 = "MM/dd/yyyy hh:mm:ss aa";
    public static final String DATE_FORMAT_19 = "hh:mm dd/MM/yyyy";
    public static final String DATE_FORMAT_20 = "ddMMyyyy";
    public static final String DATE_FORMAT_21 = "HHmmssddMMyyyy";
    public static final String DATE_FORMAT_22 = "MMyyyy";
    public static final String DATE_FORMAT_23 = "EEE";
    public static final String DATE_FORMAT_24 = "yyyyMMdd";
    public static final String DATE_FORMAT_25 = "yyyy";
    public static final String DATE_FORMAT_26 = "MM";

    public static Calendar convertStringToCalendar(String input, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        try {
            Date d = sdf.parse(input);
            calendar.setTime(d);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return calendar;
    }

    public static Date convertStringToDate(String input, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return sdf.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static long convertStringToTimeStamp(String input, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date d = sdf.parse(input);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String convertStringToString(String input, String formatInput, String formatOutPut) {
        if (TextUtils.isEmpty(input)) return input;
        SimpleDateFormat sdf = new SimpleDateFormat(formatInput, Locale.getDefault());
        SimpleDateFormat sdfOut = new SimpleDateFormat(formatOutPut, Locale.getDefault());
        try {
            Date d = sdf.parse(input);
            return sdfOut.format(d);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return input;
    }

    public static String convertTimeStampToString(long timeStamp) {
        Date d = new Date(timeStamp);
        return convertDateToString(d, DATE_FORMAT_2);
    }

    public static String convertTimeStampToString(long timeStamp, String format) {
        Date d = new Date(timeStamp);
        return convertDateToString(d, format);
    }

    public static String convertDateToString(Date date, String format) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    public static String convertCalendarToString(Calendar calendar, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

    public static String getCurrentDateDetail() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());
        String date = format.format(Calendar.getInstance().getTime());
        return date;
    }

    public static boolean checkIsYesterday(Calendar from, Calendar to) {
        to.set(Calendar.HOUR_OF_DAY, 0);
        to.set(Calendar.MINUTE, 0);
        to.set(Calendar.SECOND, 0);
        to.set(Calendar.MILLISECOND, 0);
        if (from.before(to)) {
            to.add(Calendar.DATE, -1);
            if (from.before(to)) {
                return false;
            }
            return true;
        }
        return false;
    }

    // se lay time la khoang 10 20 30 40 50 cua gio
    public static long convertDownTime(long time) {
        time -= time % 300000;
        return time;
    }

    public static long convertDownTimeDay(long time) {
        time -= time % 86400000;
        return time;
    }

    public static long convertDownTimeDayGMT7(long time) {
        String timeStr = TimeUtils.convertTimeStampToString(time, TimeUtils.DATE_FORMAT_2);
        return TimeUtils.convertStringToTimeStamp(timeStr, TimeUtils.DATE_FORMAT_2);
    }

    public static long get6MonthLaterFromNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        return calendar.getTimeInMillis();
    }

    public static long get6MonthLaterFromTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.add(Calendar.MONTH, -6);
        return calendar.getTimeInMillis();
    }

    public static long getTimeWithSub(long time, int sub) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.add(Calendar.DAY_OF_MONTH, sub);
        return calendar.getTimeInMillis();
    }

    public static int subTimeToMonday(String time) {
        time = time.toLowerCase();
        switch (time) {
            case "mon":
                return 0;
            case "tue":
                return 1;
            case "wed":
                return 2;
            case "thu":
                return 3;
            case "fri":
                return 4;
            case "sat":
                return 5;
            case "sun":
                return 6;
        }
        return 0;
    }

    public static int subTimeToSunday(String time) {
        time = time.toLowerCase();
        switch (time) {
            case "sun":
                return 0;
            case "mon":
                return 1;
            case "tue":
                return 2;
            case "wed":
                return 3;
            case "thu":
                return 4;
            case "fri":
                return 5;
            case "sat":
                return 6;
        }
        return 0;
    }
}
