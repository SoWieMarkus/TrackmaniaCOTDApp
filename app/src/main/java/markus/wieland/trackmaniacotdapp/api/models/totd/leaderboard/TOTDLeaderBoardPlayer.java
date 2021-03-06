package markus.wieland.trackmaniacotdapp.api.models.totd.leaderboard;

import com.google.gson.annotations.SerializedName;

import markus.wieland.trackmaniacotdapp.api.general.Player;
import markus.wieland.trackmaniacotdapp.api.general.TMioPlayer;
import markus.wieland.trackmaniacotdapp.helper.StyleConverter;

public class TOTDLeaderBoardPlayer extends Player {

    @SerializedName("position")
    private int position;
    @SerializedName("time")
    private long time;
    @SerializedName("timestamp")
    private String timeStamp;

    @SerializedName("player")
    private TMioPlayer tmioPlayer;

    public TMioPlayer getTmioPlayer() {
        return tmioPlayer;
    }

    public void setTmioPlayer(TMioPlayer tmioPlayer) {
        this.tmioPlayer = tmioPlayer;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPositionAsString(){
        return StyleConverter.getStringFromPosition(position);
    }

    @Override
    public String getUrl() {
        return "https://trackmania.io/#/player/" + tmioPlayer.getId();
    }
}
