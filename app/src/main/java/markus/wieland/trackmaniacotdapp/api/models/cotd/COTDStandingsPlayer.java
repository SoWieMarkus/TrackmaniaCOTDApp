package markus.wieland.trackmaniacotdapp.api.models.cotd;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import markus.wieland.defaultappelements.uielements.adapter.QueryableEntity;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.general.Zone;
import markus.wieland.trackmaniacotdapp.helper.StyleConverter;

public class COTDStandingsPlayer implements QueryableEntity<String> {

    private static final String GOLD = "\uD83E\uDD47";
    private static final String SILVER = "\uD83E\uDD48";
    private static final String BRONZE = "\uD83E\uDD49";

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

    @SerializedName("bestResult")
    private int bestResult;

    @SerializedName("averagePosition")
    private int averagePosition;

    @SerializedName("position")
    private int position;

    public int getBestResult() {
        return bestResult;
    }

    public int getAveragePosition() {
        return averagePosition;
    }

    public int getPosition() {
        return position;
    }

    public String getPositionAsString() {
        return position + ".";
    }

    public Zone getZone() {
        return new Gson().fromJson(zone, Zone.class);
    }

    public String getId() {
        return id;
    }

    @Override
    public String getStringToApplyQuery() {
        return getDisplayName();
    }

    public String getUrl() {
        return "https://trackmania.io/#/player/" + accountId;
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

    public String getAveragePositionAsString(Context context){
        return context.getString(R.string.average_position) + " " + StyleConverter.getStringFromPosition(averagePosition);
    }

    public String buildTrophyString(Context context) {
        String trophies = "";
        if (amountFirst != 0)
            trophies += GOLD + " " + amountFirst + " ";
        if (amountSecond != 0)
            trophies += SILVER + " " + amountSecond + " ";
        if (amountThird != 0)
            trophies += BRONZE + " " + amountThird + " ";
        if (trophies.length() == 0)
            trophies = context.getString(R.string.best_finish) + " " + bestResult + ".";
        return trophies.trim();
    }

    public String getPointsAsString() {
        return String.valueOf(points);
    }
}
