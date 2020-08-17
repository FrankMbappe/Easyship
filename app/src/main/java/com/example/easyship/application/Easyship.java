package com.example.easyship.application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Easyship {
    public static String getDateFromCalendar(Calendar c){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(c.getTime());
    }
    public static String getTimeFromCalendar(Calendar c){
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(c.getTime());
    }
}
