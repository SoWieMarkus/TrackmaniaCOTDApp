package markus.wieland.unofficalcupoftheleaderboard.api.models.cotd;

import com.google.gson.Gson;

import markus.wieland.unofficalcupoftheleaderboard.api.general.Zone;

public class COTDPlayerResult {

    private String id;

    private String accountId;

    private String displayName;

    private String zone;

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
}
