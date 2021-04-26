package markus.wieland.unofficalcupoftheleaderboard.api.models.totd;

import com.google.gson.annotations.SerializedName;

import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.map.Map;

public class TOTD {

    @SerializedName("campaignid")
    private int campaignId;

    @SerializedName("map")
    private Map map;

    @SerializedName("weekday")
    private int dayOfWeek;

    @SerializedName("monthday")
    private int dayOfMonth;

    @SerializedName("leaderboarduid")
    private String leaderBoardUid;

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getLeaderBoardUid() {
        return leaderBoardUid;
    }

    public void setLeaderBoardUid(String leaderBoardUid) {
        this.leaderBoardUid = leaderBoardUid;
    }
}
