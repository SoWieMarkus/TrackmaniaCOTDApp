package markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.overview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Overview {

    @SerializedName("overView")
    private List<MonthOverView> months;

    public List<MonthOverView> getMonths() {
        months.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));
        return months;
    }

    public void setMonths(List<MonthOverView> months) {
        this.months = months;
    }
}
