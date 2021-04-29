package markus.wieland.trackmaniacotdapp.ui.leaderboard;

import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.TrackmaniaCOTDApi;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTDLeaderBoard;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTDStandingsPlayer;
import markus.wieland.trackmaniacotdapp.api.models.cotd.overview.MonthOverView;
import markus.wieland.trackmaniacotdapp.api.models.cotd.overview.Overview;
import markus.wieland.trackmaniacotdapp.helper.DateManager;
import markus.wieland.trackmaniacotdapp.helper.ListFragment;
import markus.wieland.trackmaniacotdapp.helper.OnClickListener;
import markus.wieland.trackmaniacotdapp.ui.leaderboard.LeaderboardAdapter.LeaderboardViewHolder;
import markus.wieland.trackmaniacotdapp.ui.leaderboard.summary.OnClickStandingsPlayer;

public class LeaderBoardMonthFragment extends ListFragment<COTDStandingsPlayer, LeaderboardViewHolder> implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener, OnClickListener<COTDStandingsPlayer> {

    private OnClickStandingsPlayer onClickStandingsPlayer;

    private TrackmaniaCOTDApi trackmaniaCOTDApi;

    private Overview overview;
    private int overViewIndex;

    private Button buttonLeftMonth;
    private Button buttonRightMonth;

    private SearchView searchView;

    private TextView textViewMonthName;

    private SwipeRefreshLayout swipeRefreshLayout;

    public LeaderBoardMonthFragment() {
        super(R.layout.fragment_leaderboard_month,
                R.id.fragment_leader_board_monthly_recycler_view,
                R.id.fragment_leader_board_progress_bar,
                R.id.fragment_leader_board_empty_message);
        overViewIndex = 0;
    }

    public void setCotdStandingsPlayerOnItemInteractListener(OnClickStandingsPlayer onClickStandingsPlayer) {
        this.onClickStandingsPlayer = onClickStandingsPlayer;
    }

    @Override
    public DefaultAdapter<COTDStandingsPlayer, LeaderboardViewHolder> getAdapter() {
        return new LeaderboardAdapter(this);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        buttonLeftMonth = findViewById(R.id.fragment_leader_board_monthly_left);
        buttonRightMonth = findViewById(R.id.fragment_leader_board_monthly_right);
        textViewMonthName = findViewById(R.id.item_leader_board_name);
        searchView = findViewById(R.id.fragment_leader_board_monthly_search_view);
        swipeRefreshLayout = findViewById(R.id.fragment_leader_board_monthly_swipe_refresh);
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        buttonLeftMonth.setOnClickListener(view -> {
            overViewIndex--;
            search();
        });
        buttonRightMonth.setOnClickListener(view -> {
            overViewIndex++;
            search();
        });

        if (overview == null) {
            overview = getDefaultOverview();
            overViewIndex = 0;
        }
        searchView.setOnQueryTextListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        updateButtons();

    }

    private void updateButtons() {
        buttonLeftMonth.setEnabled(overViewIndex - 1 >= 0);
        buttonRightMonth.setEnabled(overViewIndex + 1 < overview.getMonths().size());
        buttonLeftMonth.setText(DateManager.getNameOfMonth(overview.getMonths().get(overViewIndex).getMonth() - 1, TextStyle.SHORT_STANDALONE));
        buttonRightMonth.setText(DateManager.getNameOfMonth(overview.getMonths().get(overViewIndex).getMonth() + 1, TextStyle.SHORT_STANDALONE));
        textViewMonthName.setText(DateManager.getStringFromMonthAndYear(overview.getMonths().get(overViewIndex).getYear(),
                overview.getMonths().get(overViewIndex).getMonth()));
    }

    @Override
    public void execute() {
        super.execute();
        update(null, true);
        trackmaniaCOTDApi = new TrackmaniaCOTDApi(getActivity());
        trackmaniaCOTDApi.getOverview(this::onLoad);
    }

    private Overview getDefaultOverview() {
        Overview defaultOverview = new Overview();
        MonthOverView monthOverView = new MonthOverView();
        monthOverView.setMonth(LocalDateTime.now().getMonthValue());
        monthOverView.setYear(LocalDateTime.now().getYear());
        defaultOverview.setMonths(new ArrayList<>(Collections.singletonList(monthOverView)));
        return defaultOverview;
    }

    private void onLoad(Overview overview) {
        if (overview == null) {
            overview = getDefaultOverview();
        }

        if (overview.getMonths().size() != this.overview.getMonths().size())
            overViewIndex = overview.getMonths().size() - 1;
        this.overview = overview;
        search();
    }


    private void search() {
        MonthOverView monthOverView = overview.getMonths().get(overViewIndex);
        updateButtons();
        buttonLeftMonth.setEnabled(false);
        buttonRightMonth.setEnabled(false);
        update(null, true);
        trackmaniaCOTDApi.getLeaderboard(this::onLoad, monthOverView.getYear(), monthOverView.getMonth());
    }

    private void onLoad(COTDLeaderBoard cotdLeaderBoard) {
        swipeRefreshLayout.setRefreshing(false);
        updateButtons();
        if (cotdLeaderBoard == null)
            update(new ArrayList<>(), true);
        else if (cotdLeaderBoard.getMonth() == overview.getMonths().get(overViewIndex).getMonth())
            update(cotdLeaderBoard.getStandings(), true);
    }

    @Override
    public void onClick(COTDStandingsPlayer cotdStandingsPlayer) {
        if (onClickStandingsPlayer != null)
            onClickStandingsPlayer.onClick(cotdStandingsPlayer,
                    overview.getMonths().get(overViewIndex).getYear(),
                    overview.getMonths().get(overViewIndex).getMonth());
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (currentList == null) return false;
        List<COTDStandingsPlayer> standingsPlayers = new ArrayList<>();
        for (COTDStandingsPlayer standingsPlayer : currentList) {
            if (standingsPlayer.getStringToApplyQuery().toLowerCase().contains(s.toLowerCase())) {
                standingsPlayers.add(standingsPlayer);
            }
        }
        update(standingsPlayers, false);
        if (!standingsPlayers.isEmpty()) recyclerView.scrollToPosition(0);
        return true;
    }

    @Override
    public void onRefresh() {
        search();
    }
}
