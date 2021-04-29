package markus.wieland.trackmaniacotdapp.api.models.totd.leaderboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TOTDLeaderBoard {

    @SerializedName("tops")
    private List<TOTDLeaderBoardPlayer> tops;

    public List<TOTDLeaderBoardPlayer> getTops() {
        return tops;
    }
}
