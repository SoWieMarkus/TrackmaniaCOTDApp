package markus.wieland.trackmaniacotdapp.api.models.cotd;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class COTDLeaderBoard {

    @SerializedName("id")
    private String id;

    @SerializedName("year")
    private int year;

    @SerializedName("month")
    private int month;

    @SerializedName("leaderBoardPlayers")
    private List<COTDStandingsPlayer> standings;

    public String getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public List<COTDStandingsPlayer> getStandings() {
        return standings;
    }
}
