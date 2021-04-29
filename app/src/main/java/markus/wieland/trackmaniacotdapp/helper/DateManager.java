package markus.wieland.trackmaniacotdapp.helper;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateManager {

    private DateManager() {
    }

    public static String getStringFromMonthAndYear(int year, int month){
        return getNameOfMonth(month) + " "+year;
    }

    public static String getNameOfMonth(int month) {
        return getNameOfMonth(month, TextStyle.FULL_STANDALONE);
    }

    public static String getNameOfMonth(int month, TextStyle style) {
        if (month < 1) month = 12;
        if (month > 12) month = 1;
        Month day = Month.of(month);
        return day.getDisplayName(style, Locale.getDefault());
    }

    public static String getNameOfDay(LocalDateTime dateTime) {
        return dateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault());
    }

    public static String getDateWithDayName(int year, int month, int day){
        return getNameOfDay(LocalDateTime.of(year, month, day, 0,0)) + "., " + StyleConverter.build(day, 2) + "."
                + StyleConverter.build(month, 2) + "."
                + year;
    }

}
