/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.fgbg.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 日期处理
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 时间格式(yyyy年MM月dd日 HH:mm) */
	public final static String MY_DARE_TIME_PATTERN = "yyyy年MM月dd日 HH:mm";


	/**
	 * 根据currentDate, 返回yyyy年MM月dd日 HH:mm类型的时间
	 * @param currentDate 时间
	 * @return
	 */
    public static String myDateFormat(LocalDateTime currentDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MY_DARE_TIME_PATTERN);
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }

	/**
	 * 以yyyy年MM月dd日 HH:mm形式返回当前时间
	 * @return
	 */
	public static String getMyCurrentTime() {
		return myDateFormat(LocalDateTime.now());
	}
}
