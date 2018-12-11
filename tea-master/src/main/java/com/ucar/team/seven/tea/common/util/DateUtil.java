package com.ucar.team.seven.tea.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author Jacen(liangjie.cai @ ucarinc.com)
 * @Date 2018/9/27 15:43
 * @Version 1.0
 * @Description 日期时间操作的实用类
 */
public class DateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static final String SIMPLE_FORMAT = "yyyy-MM-dd";
    public static final String SIMPLE_FORMAT2 = "yyyy_MM_dd";
    public static final String NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String NOT_FORMAT = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * 获取当前时间格式化表示
     *
     * @param format 格式化字符串
     * @return
     */
    public static String getNowFormatTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 当前时间
     *
     * @return Date
     */
    public static Date getNowTime() {
        return new Date();
    }

    /**
     * 当前时间
     *
     * @return Timestamp
     */
    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取某个基准时间往前或往后的x年的时间，往前设置x为负数
     *
     * @param date 基准时间
     * @param x    调校的年数
     * @return 调校后的时间
     */
    public static Date getNextYear(Date date, int x) {
        return getNextDate(date, x, Calendar.MONTH);
    }

    /**
     * 获取某个基准时间往前或往后的x月的时间，往前设置x为负数
     *
     * @param date 基准时间
     * @param x    调校的月数
     * @return 调校后的时间
     */
    public static Date getNextMonth(Date date, int x) {
        return getNextDate(date, x, Calendar.MONTH);
    }

    /**
     * 获取某个基准时间往前或往后的x天的时间，往前设置x为负数
     *
     * @param date 基准时间
     * @param x    调校的天数
     * @return 调校后的时间
     */
    public static Date getNextDay(Date date, int x) {
        return getNextDate(date, x, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某个基准时间往前或往后的x小时的时间，往前设置x为负数
     *
     * @param date 基准时间
     * @param x    调校的小时数
     * @return 调校后的时间
     */
    public static Date getNextHour(Date date, int x) {
        return getNextDate(date, x, Calendar.HOUR);
    }

    /**
     * 获取某个基准时间往前或往后的x分钟的时间，往前设置x为负数
     *
     * @param date 基准时间
     * @param x    调校的分钟数
     * @return 调校后的时间
     */
    public static Date getNextMinute(Date date, int x) {
        return getNextDate(date, x, Calendar.MINUTE);
    }

    /**
     * 获取某个基准时间往前或往后的x秒的时间，往前设置x为负数
     *
     * @param date 基准时间
     * @param x    调校的秒数
     * @return 调校后的时间
     */
    public static Date getNextSecond(Date date, int x) {
        return getNextDate(date, x, Calendar.SECOND);
    }

    /**
     * 获取下一个日期时间
     *
     * @param dateStart 开始的日期时间
     * @param field     Calendar字段类别，DAY_OF_YEAR、DAY_OF_MONTH、HOUR等
     * @param amount    增量
     * @return 日期时间
     */
    public static Date getNextDate(Date dateStart, int amount, int field) {
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(dateStart);
        calStart.add(field, amount);
        return calStart.getTime();
    }

    /**
     * 字符串转化成时间
     *
     * @param dateString 日期字符串
     * @param dataFormat 日期格式
     * @return Date
     */
    public static Date str2Date(String dateString, String dataFormat) {
        Date date = null;
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
            date = sdf.parse(dateString);
            return date;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return date;
    }

    /**
     * 日期格式化为字符串
     *
     * @param date   日期时间
     * @param format 格式
     * @return 格式化后的日期时间
     */
    public static String date2Str(Date date, String format) {
        String result = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);
        }
        return result;
    }

    /**
     * timestamp转成字符串，带毫秒
     *
     * @param time 日期时间
     * @return 带毫秒的日期时间字符串
     */
    public static String timestamp2Str(Timestamp time) {
        if (time == null) {
            return null;
        }
        return time.toString();
    }

    /**
     * timestamp转成字符串，不带毫秒
     *
     * @param time   日期时间
     * @param format 日期字符串比宝石格式
     * @return 日期字符串
     */
    public static String timestamp2Str(Timestamp time, String format) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(time);
    }

    /**
     * str转化为timestamp
     *
     * @param str    日期的字符串
     * @param format 日期字符串格式
     * @return 转化后的timestamp
     */
    public static Timestamp str2Timestamp(String str, String format) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 判断realTime是否在regularTime的minites误差范围内
     *
     * @param realTime    需要判断的时间
     * @param regularTime 基准的时间
     * @param minites     误差的分钟范围
     * @return 布尔值
     */
    public static boolean timeInRange(Date realTime, Date regularTime, int minites) {
        long realMilli = realTime.getTime();
        long regularMilli = regularTime.getTime();
        long errorRange = minites * 60 * 1000;

        return (realMilli >= (regularMilli - errorRange) && realMilli <= (regularMilli + errorRange));
    }

    /**
     * 按照指定格式获取连续几天的日期字符串，包含起止时间
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param dateFormat 日期格式
     * @return String[]
     */
    public static String[] getSeqDay(Date startTime, Date endTime, String dateFormat) {
        if (endTime.getTime() < startTime.getTime()) {
            throw new RuntimeException("开始时间必须小于结束时间");
        }

        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        startCalendar.setTime(startTime);
        endCalendar.setTime(endTime);

        Calendar startCalendar2 = Calendar.getInstance();
        Calendar endCalendar2 = Calendar.getInstance();

        startCalendar2.set(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH),
                startCalendar.get(Calendar.DAY_OF_MONTH));
        endCalendar2.set(endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH),
                endCalendar.get(Calendar.DAY_OF_MONTH));

        double diff = ((double) endCalendar2.getTimeInMillis() - (double) startCalendar2.getTimeInMillis())
                / (24 * 60 * 60 * 1000);
        int days = (int) Math.ceil(diff);

        String[] strDays = new String[days + 1];
        strDays[0] = date2Str(startTime, dateFormat);

        Calendar calStart = Calendar.getInstance();
        calStart.setTime(startTime);
        for (int i = 1; i <= days; i++) {
            calStart.add(Calendar.DAY_OF_YEAR, 1);
            strDays[i] = date2Str(calStart.getTime(), dateFormat);
        }

        return strDays;
    }

    /**
     * 获取两个时间之间的间隔小时数
     *
     * @param dateStart 开始时间
     * @param dateEnd   结束时间
     * @return 间隔的小时数
     */
    public static int getBetweenHours(Date dateStart, Date dateEnd) {
        long range = dateEnd.getTime() - dateStart.getTime();
        return (int) ((range / 1000) / 60 / 60);
    }

    /**
     * 获取两个时间之间的间隔分钟数
     *
     * @param dateStart 开始时间
     * @param dateEnd   结束时间
     * @return 间隔的分钟数
     */
    public static int getBetweenMinutes(Date dateStart, Date dateEnd) {
        long range = dateEnd.getTime() - dateStart.getTime();
        return (int) ((range / 1000) / 60);
    }

    /**
     * 获取两个时间之间的间隔秒数
     *
     * @param dateStart 开始时间
     * @param dateEnd   结束时间
     * @return 间隔的秒数
     */
    public static int getBetweenSeconds(Date dateStart, Date dateEnd) {
        long range = dateEnd.getTime() - dateStart.getTime();
        return (int) (range / 1000);
    }


    /**
     * 获取该天开始时间
     *
     * @param date 日期时间
     * @return Date
     */
    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取该天开始时间String
     *
     * @param date 日期时间
     * @return String
     */
    public static String getDateStartStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        return date2Str(cal.getTime(), format);
    }

    /**
     * 获取该天结束时间
     *
     * @param date 日期时间
     * @return Date
     */
    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取该天结束时间
     *
     * @param date 日期时间
     * @return String
     */
    public static String getDateEndStr(Date date, String format) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        return date2Str(cal.getTime(), format);
    }

}