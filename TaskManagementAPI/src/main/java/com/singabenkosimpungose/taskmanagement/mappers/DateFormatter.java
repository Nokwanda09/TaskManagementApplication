package com.singabenkosimpungose.taskmanagement.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public LocalDate toLocalDate(String date){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(date, dateFormatter);
    }
}
