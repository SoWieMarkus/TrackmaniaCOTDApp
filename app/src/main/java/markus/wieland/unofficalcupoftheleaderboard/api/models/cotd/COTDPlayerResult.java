package markus.wieland.unofficalcupoftheleaderboard.api.models.cotd;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import markus.wieland.unofficalcupoftheleaderboard.api.general.Zone;

public class COTDPlayerResult {

    @SerializedName("id")
    private String id;

    @SerializedName("accountId")
    private String accountId;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("zone")
    private String zone;

    @SerializedName("position")
    private int position;

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Zone getZone() {
        return new Gson().fromJson(zone, Zone.class);
    }

    public int getPosition() {
        return position;
    }

    public String getUrl() {
        return "https://trackmania.io/#/player/" + accountId;
    }
}
