package markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.overview;

import com.google.gson.annotations.SerializedName;

public class MonthOverView {

    @SerializedName("year")
    private int year;

    @SerializedName("month")
    private int month;

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

    @Override
    public String toString() {
        return year + "-" + (month < 10 ? "0" + month : month);
    }
}

