package com.asr2.gurukula.web.bean;

import com.asr2.gurukula.commons.api.DateUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Created by abhijith.nagarajan on 7/22/15.
 */
@Named
@ApplicationScoped
public class AppSettings {

    public String getDateFormat() {
        return DateUtils.getDateFormat();
    }

    public String getTimeZone() {
        return ZoneId.SHORT_IDS.get("IST");
    }

}
