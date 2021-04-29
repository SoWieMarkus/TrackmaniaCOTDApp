package markus.wieland.trackmaniacotdapp.api.general;

import com.google.gson.annotations.SerializedName;

public class Zone {

    @SerializedName("name")
    private String name;
    @SerializedName("flag")
    private String flag;
    @SerializedName("parent")
    private Zone parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Zone getParent() {
        return parent;
    }

    public void setParent(Zone parent) {
        this.parent = parent;
    }

    public String getFlagUrl() {
        return "https://trackmania.io/img/flags/" + getShownFlag() + ".jpg";
    }

    public String getShownFlag(){
        return flag.matches("[A-Z][A-Z][A-Z]") ? flag : parent.getShownFlag();
    }
}
