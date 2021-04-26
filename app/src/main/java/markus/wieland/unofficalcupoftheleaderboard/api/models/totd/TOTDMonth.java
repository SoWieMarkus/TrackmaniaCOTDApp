package markus.wieland.unofficalcupoftheleaderboard.api.models.totd;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TOTDMonth {

    @SerializedName("year")
    private int year;
    @SerializedName("month")
    private int month;
    @SerializedName("lastday")
    private int lastDay;
    @SerializedName("monthoffset")
    private int monthOffset;
    @SerializedName("monthcount")
    private int monthCount;
    @SerializedName("days")
    private List<TOTD> days;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getLastDay() {
        return lastDay;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }

    public int getMonthOffset() {
        return monthOffset;
    }

    public void setMonthOffset(int monthOffset) {
        this.monthOffset = monthOffset;
    }

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public List<TOTD> getDays() {
        return days;
    }

    public void setDays(List<TOTD> days) {
        this.days = days;
    }
}
