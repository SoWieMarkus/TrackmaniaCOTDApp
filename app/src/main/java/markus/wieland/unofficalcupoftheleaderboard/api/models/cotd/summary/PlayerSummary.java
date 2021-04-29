package markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.summary;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerSummary {

    @SerializedName("month")
    private int month;

    @SerializedName("year")
    private int year;

    @SerializedName("playerResults")
    private List<PlayerResult> playerResults;

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }

    public PieData getPositionDataSet(Context context) {
        HashMap<String, List<PlayerResult>> positions = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            positions.put("Top" + (i * 8), new ArrayList<>());
        }

        for (PlayerResult playerResult : playerResults) {
            positions.get(playerResult.getLabel()).add(playerResult);
        }

        List<PieEntry> entries = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            entries.add(new PieEntry(positions.get("Top" + (i * 8)).size(), "Top" + (i * 8)));
        }

        PieDataSet barDataSet = new PieDataSet(entries, "test");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(14f);

        return new PieData(barDataSet);
    }

}
