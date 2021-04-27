package markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.overview;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.List;

public class Overview {

    @SerializedName("overView")
    private List<MonthOverView> overView;

    public List<MonthOverView> getOverView() {

        overView.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));

        return overView;
    }

    public void setOverView(List<MonthOverView> overView) {
        this.overView = overView;
    }
}
