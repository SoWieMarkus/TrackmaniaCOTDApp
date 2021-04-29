package markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard.summary;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.TrackmaniaCOTDApi;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDStandingsPlayer;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.summary.PlayerResult;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.summary.PlayerSummary;
import markus.wieland.unofficalcupoftheleaderboard.helper.DateManager;
import markus.wieland.unofficalcupoftheleaderboard.helper.ListFragment;

public class PlayerSummaryFragment extends ListFragment<PlayerResult, PlayerResultAdapter.PlayerResultViewHolder> implements SwipeRefreshLayout.OnRefreshListener,OnChartValueSelectedListener {

    private TrackmaniaCOTDApi trackmaniaCOTDApi;

    private PieChart barChart;

    private TextView textViewName;
    private TextView textViewTrophy;
    private TextView textViewTitle;
    private TextView textViewAverage;

    private ImageView imageViewFlag;

    private SwipeRefreshLayout swipeRefreshLayout;

    private final int year;
    private final int month;
    private final COTDStandingsPlayer cotdStandingsPlayer;

    public PlayerSummaryFragment(COTDStandingsPlayer cotdStandingsPlayer, int year, int month) {
        super(R.layout.fragment_player_summary, R.id.fragment_player_summary_recycler_view, R.id.fragment_player_summary_progress_bar, R.id.fragment_player_summary_empty_message);
        this.cotdStandingsPlayer = cotdStandingsPlayer;
        this.year = year;
        this.month = month;
    }

    public PlayerSummaryFragment() {
        this(null, 0, 0);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        barChart = findViewById(R.id.fragment_player_summary_position_chart);
        textViewName = findViewById(R.id.fragment_player_summary_name);
        textViewTrophy = findViewById(R.id.fragment_player_summary_trophies);
        imageViewFlag = findViewById(R.id.fragment_player_summary_flag);
        textViewTitle = findViewById(R.id.fragment_player_summary_title);
        textViewAverage = findViewById(R.id.fragment_player_summary_average);
        swipeRefreshLayout = findViewById(R.id.fragment_player_summary_swipe_refresh);
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        if (getActivity() == null) return;
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
        textViewName.setText(cotdStandingsPlayer.getDisplayName());
        textViewTrophy.setText(cotdStandingsPlayer.buildTrophyString(getActivity()));
        textViewAverage.setText(cotdStandingsPlayer.getAveragePositionAsString(getActivity()));
        textViewTitle.setText(isGlobal() ? getString(R.string.global_leaderboard) : DateManager.getNameOfMonth(month) + " " + year);
        Glide.with(getActivity()).load(cotdStandingsPlayer.getZone().getFlagUrl()).into(imageViewFlag);
    }

    @Override
    public PlayerResultAdapter getAdapter() {
        return new PlayerResultAdapter();
    }

    @Override
    public void execute() {
        super.execute();
        trackmaniaCOTDApi = new TrackmaniaCOTDApi(getActivity());
        search();
    }

    private boolean isGlobal() {
        return year == 0 && month == 0;
    }

    private void search() {

        if (isGlobal())
            trackmaniaCOTDApi.getGlobalSummary(this::onLoad, cotdStandingsPlayer.getAccountId());
        else
            trackmaniaCOTDApi.getMonthlySummary(this::onLoad, cotdStandingsPlayer.getAccountId(), year, month);
    }

    private void onLoad(PlayerSummary playerSummary) {
        swipeRefreshLayout.setRefreshing(false);
        if (playerSummary == null) {
            update(new ArrayList<>(), true);
            return;
        }
        Collections.reverse(playerSummary.getPlayerResults());
        update(playerSummary.getPlayerResults(), true);
        barChart.setData(playerSummary.getPositionDataSet(getActivity()));
        barChart.animateY(1000);
        barChart.setOnChartValueSelectedListener(this);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        PieEntry entry = (PieEntry) e;
        List<PlayerResult> filtered = new ArrayList<>();

        for (PlayerResult playerResult : currentList) {
            if (playerResult.getStringToApplyQuery().toLowerCase().contains(entry.getLabel().toLowerCase())) {
                filtered.add(playerResult);
            }
        }
        update(filtered, false);
    }

    @Override
    public void onNothingSelected() {
        update(currentList, false);
    }

    @Override
    public void onRefresh() {
        search();
    }
}
