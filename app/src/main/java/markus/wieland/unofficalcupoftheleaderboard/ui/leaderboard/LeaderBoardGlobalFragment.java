package markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard;

import android.widget.SearchView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.TrackmaniaCOTDApi;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDLeaderBoard;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDStandingsPlayer;
import markus.wieland.unofficalcupoftheleaderboard.helper.ListFragment;
import markus.wieland.unofficalcupoftheleaderboard.helper.OnClickListener;
import markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard.summary.OnClickStandingsPlayer;

public class LeaderBoardGlobalFragment extends ListFragment<COTDStandingsPlayer, LeaderboardAdapter.LeaderboardViewHolder> implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener, OnClickListener<COTDStandingsPlayer> {

    private OnClickStandingsPlayer onClickStandingsPlayer;

    private SearchView searchView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private TrackmaniaCOTDApi trackmaniaCOTDApi;

    public LeaderBoardGlobalFragment() {
        super(R.layout.fragment_leaderboard_global,
                R.id.fragment_leaderboard_global_recycler_view,
                R.id.fragment_leader_board_progress_bar,
                R.id.fragment_leader_board_empty_message);
    }

    public void setCotdStandingsPlayerOnItemInteractListener(OnClickStandingsPlayer onClickStandingsPlayer) {
        this.onClickStandingsPlayer = onClickStandingsPlayer;
    }

    @Override
    public void bindViews() {
        super.bindViews();
        searchView = findViewById(R.id.fragment_leader_board_global_search_view);
        swipeRefreshLayout = findViewById(R.id.fragment_leader_board_global_swipe_to_refresh);
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        searchView.setOnQueryTextListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public DefaultAdapter<COTDStandingsPlayer, LeaderboardAdapter.LeaderboardViewHolder> getAdapter() {
        return new LeaderboardAdapter(this);
    }

    @Override
    public void execute() {
        super.execute();
        trackmaniaCOTDApi = new TrackmaniaCOTDApi(getActivity());
        search();
    }

    private void search(){
        trackmaniaCOTDApi.getGlobalLeaderboard(this::onLoad);
    }

    private void onLoad(COTDLeaderBoard globalLeaderBoard) {
        swipeRefreshLayout.setRefreshing(false);
        update(globalLeaderBoard == null ? new ArrayList<>() : globalLeaderBoard.getStandings(), true);
    }

    @Override
    public void onClick(COTDStandingsPlayer cotdStandingsPlayer) {
        if (onClickStandingsPlayer != null)
            onClickStandingsPlayer.onClick(cotdStandingsPlayer, 0, 0);
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
