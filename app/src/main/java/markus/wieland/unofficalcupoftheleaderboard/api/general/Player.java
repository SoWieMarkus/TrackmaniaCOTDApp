package markus.wieland.unofficalcupoftheleaderboard.api.general;

import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("accountid")
    private String accountId;
    @SerializedName("displayname")
    private String displayName;
    @SerializedName("zone")
    private Zone zone;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public String getUrl() {
        return "https://trackmania.io/#/player/" + accountId;
    }
}
