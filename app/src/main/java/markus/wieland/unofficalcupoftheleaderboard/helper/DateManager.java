package markus.wieland.unofficalcupoftheleaderboard.helper;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateManager {

    private DateManager(){}

    public static LocalDateTime getCurrentCOTDDate() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
        TimeZone tz = c.getTimeZone();
        ZoneId zid = tz.toZoneId();
        LocalDateTime today = LocalDateTime.ofInstant(c.toInstant(), zid);
        if (today.getHour() < 19) today = today.minusDays(1);
        return today;
    }

    public static String getNameOfMonth(int month){
        return getNameOfMonth(month, TextStyle.FULL_STANDALONE);
    }

    public static String getNameOfMonth(int month, TextStyle style){
        if (month < 1) month = 12;
        if (month > 12) month = 1;
        Month day = Month.of(month);
        return day.getDisplayName(style, Locale.getDefault());
    }

}
