package com.asr2.gurukula.commons.api;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by abhijith.nagarajan on 7/21/15.
 */
public class DateUtils {

    public static ZonedDateTime getCurrentDateTimeInGMT() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    public static Date getCurrentDate() {
        return Date.from(getCurrentDateTimeInGMT().toInstant());
    }

    public static String getDateFormat() {
        return "yyyy-MMM-dd HH:mm:ss z";
    }
}
