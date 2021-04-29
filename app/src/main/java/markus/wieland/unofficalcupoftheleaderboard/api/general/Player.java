package markus.wieland.unofficalcupoftheleaderboard.api.general;

import com.google.gson.annotations.SerializedName;

import markus.wieland.defaultappelements.uielements.adapter.QueryableEntity;

public class Player implements QueryableEntity<String> {

    @SerializedName("accountid")
    private String accountId;
    @SerializedName("displayname")
    private String displayName;
    @SerializedName("zone")
    private Zone zone;

    public String getAccountId() {
        return accountId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Zone getZone() {
        return zone;
    }

    public String getUrl() {
        return "https://trackmania.io/#/player/" + accountId;
    }

    @Override
    public String getId() {
        return getAccountId();
    }

    @Override
    public String getStringToApplyQuery() {
        return getDisplayName();
    }
}
