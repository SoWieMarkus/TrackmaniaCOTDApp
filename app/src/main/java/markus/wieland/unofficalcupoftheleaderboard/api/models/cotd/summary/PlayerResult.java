package markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.summary;

import com.google.gson.annotations.SerializedName;

import markus.wieland.defaultappelements.uielements.adapter.QueryableEntity;
import markus.wieland.unofficalcupoftheleaderboard.helper.DateManager;
import markus.wieland.unofficalcupoftheleaderboard.helper.StyleConverter;

public class PlayerResult implements QueryableEntity<String> {

    @SerializedName("position")
    private int position;

    @SerializedName("year")
    private int year;

    @SerializedName("month")
    private int month;

    @SerializedName("day")
    private int day;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDate() {
        return DateManager.getDateWithDayName(year, month, day);
    }

    public String getPositionAsString() {
        return StyleConverter.getStringFromPosition(position);
    }

    @Override
    public String getId() {
        return year+"-"+month+"-"+day;
    }

    @Override
    public String getStringToApplyQuery() {
        return getLabel();
    }

    public String getLabel(){
        return "Top"+category();
    }

    public int category(){
        if (position == 0) return 64;
        for (int i = 1; i <= 8; i++) {
            if (position <= i*8) {
                return i*8;
            }
        }
        return 64;
    }
}
