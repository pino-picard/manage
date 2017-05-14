package com.Util;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caoxiao on 2017/5/14.
 */
@Service("convert_util")
public class DataConvert {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Date convertStringToDate(String date) {
        Date targetDate = null;
        try {
            targetDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetDate;
    }

    public String convertDateToString(Date date) {
        String targetDate = null;
        targetDate = dateFormat.format(date);
        return targetDate;
    }

    public boolean convertSex (String sex) {
        if ("female".equals(sex)) {
            return false;
        } else {
            return true;
        }
    }
}
