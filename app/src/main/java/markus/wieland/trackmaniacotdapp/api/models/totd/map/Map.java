package markus.wieland.trackmaniacotdapp.api.models.totd.map;

import android.text.SpannableStringBuilder;

import com.google.gson.annotations.SerializedName;

import markus.wieland.trackmaniacotdapp.api.general.TMioPlayer;

public class Map {

    @SerializedName("author")
    private String author;
    @SerializedName("name")
    private String name;
    @SerializedName("authorScore")
    private long authorScore;
    @SerializedName("goldScore")
    private long goldScore;
    @SerializedName("silverScore")
    private long silverScore;
    @SerializedName("bronzeScore")
    private long bronzeScore;
    @SerializedName("mapId")
    private String mapId;
    @SerializedName("mapUid")
    private String mapUid;
    @SerializedName("authordisplayname")
    private String authorDisplayName;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;
    @SerializedName("authorplayer")
    private TMioPlayer authorPlayer;

    public TMioPlayer getAuthorPlayer() {
        return authorPlayer;
    }

    public void setAuthorPlayer(TMioPlayer authorPlayer) {
        this.authorPlayer = authorPlayer;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAuthorScore() {
        return authorScore;
    }

    public void setAuthorScore(long authorScore) {
        this.authorScore = authorScore;
    }

    public long getGoldScore() {
        return goldScore;
    }

    public void setGoldScore(long goldScore) {
        this.goldScore = goldScore;
    }

    public long getSilverScore() {
        return silverScore;
    }

    public void setSilverScore(long silverScore) {
        this.silverScore = silverScore;
    }

    public long getBronzeScore() {
        return bronzeScore;
    }

    public void setBronzeScore(long bronzeScore) {
        this.bronzeScore = bronzeScore;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getMapUid() {
        return mapUid;
    }

    public void setMapUid(String mapUid) {
        this.mapUid = mapUid;
    }

    public String getAuthorDisplayName() {
        return authorDisplayName;
    }

    public void setAuthorDisplayName(String authorDisplayName) {
        this.authorDisplayName = authorDisplayName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public SpannableStringBuilder getColoredMapName () {
        return new MapName(name).build();
    }
}
