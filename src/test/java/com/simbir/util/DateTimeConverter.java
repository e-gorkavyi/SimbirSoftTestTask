package com.simbir.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeConverter {

    public static String parse(String src) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a"); // Jun 30, 2024 12:43:50 PM
        Date dateTime = formatter.parse(src);
        Format formatToPattern = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", new Locale("ru", "RU"));
        return formatToPattern.format(dateTime);
    }
}
