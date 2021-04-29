package markus.wieland.trackmaniacotdapp.api.models.cotd;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class COTD {

    @SerializedName("id")
    private int id;

    @SerializedName("year")
    private int year;

    @SerializedName("month")
    private int month;

    @SerializedName("day")
    private int day;

    @SerializedName("startTime")
    private long startTime;

    @SerializedName("endTime")
    private long endTime;

    @SerializedName("leaderBoardId")
    private int leaderBoardId;

    @SerializedName("players")
    private int players;

    @SerializedName("playerResult")
    private List<COTDPlayerResult> standings;

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public int getLeaderBoardId() {
        return leaderBoardId;
    }

    public int getPlayers() {
        return players;
    }

    public List<COTDPlayerResult> getStandings() {
        return standings;
    }
}
