package markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard;

import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDStandingsPlayer;
import markus.wieland.unofficalcupoftheleaderboard.ui.ListFragment;

public class LeaderBoardGlobalFragment extends ListFragment<COTDStandingsPlayer, LeaderboardAdapter.LeaderboardViewHolder> implements SearchView.OnQueryTextListener,LeaderboardAdapter.COTDStandingsPlayerOnItemInteractListener {

    private LeaderboardAdapter.COTDStandingsPlayerOnItemInteractListener cotdStandingsPlayerOnItemInteractListener;

    private SearchView searchView;

    public LeaderBoardGlobalFragment() {
        super(R.layout.fragment_leaderboard_global,
                R.id.fragment_leaderboard_global_recycler_view,
                R.id.fragment_leader_board_progress_bar,
                R.id.fragment_leader_board_empty_message);
    }

    public void setCotdStandingsPlayerOnItemInteractListener(LeaderboardAdapter.COTDStandingsPlayerOnItemInteractListener cotdStandingsPlayerOnItemInteractListener) {
        this.cotdStandingsPlayerOnItemInteractListener = cotdStandingsPlayerOnItemInteractListener;
    }

    @Override
    public void bindViews() {
        super.bindViews();
        searchView = findViewById(R.id.fragment_leader_board_global_search_view);
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public DefaultAdapter<COTDStandingsPlayer, LeaderboardAdapter.LeaderboardViewHolder> getAdapter() {
        return new LeaderboardAdapter(this);
    }

    @Override
    public void onClick(COTDStandingsPlayer cotdStandingsPlayer) {
        if (cotdStandingsPlayerOnItemInteractListener != null)
            cotdStandingsPlayerOnItemInteractListener.onClick(cotdStandingsPlayer);
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
}
