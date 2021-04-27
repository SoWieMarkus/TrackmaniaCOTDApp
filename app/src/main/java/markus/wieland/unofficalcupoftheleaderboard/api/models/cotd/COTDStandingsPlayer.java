package markus.wieland.unofficalcupoftheleaderboard.api.models.cotd;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import markus.wieland.unofficalcupoftheleaderboard.api.general.Zone;

public class COTDStandingsPlayer {

    @SerializedName("id")
    private String id;

    @SerializedName("amountFirst")
    private int amountFirst;

    @SerializedName("amountSecond")
    private int amountSecond;

    @SerializedName("amountThird")
    private int amountThird;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("accountId")
    private String accountId;

    @SerializedName("zone")
    private String zone;

    @SerializedName("points")
    private int points;

    public Zone getZone() {
        return new Gson().fromJson(zone, Zone.class);
    }

    public String getId() {
        return id;
    }

    public int getAmountFirst() {
        return amountFirst;
    }

    public int getAmountSecond() {
        return amountSecond;
    }

    public int getAmountThird() {
        return amountThird;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getPoints() {
        return points;
    }
}
