package markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDStandingsPlayer;

public class LeaderboardAdapter extends QueryableAdapter<String, COTDStandingsPlayer, LeaderboardAdapter.LeaderboardViewHolder> {

    public interface COTDStandingsPlayerOnItemInteractListener extends OnItemInteractListener<COTDStandingsPlayer> {
        void onClick(COTDStandingsPlayer cotdStandingsPlayer);
    }

    public LeaderboardAdapter(COTDStandingsPlayerOnItemInteractListener cotdStandingsPlayerOnItemInteractListener) {
        super(cotdStandingsPlayerOnItemInteractListener);
    }

    @Override
    public COTDStandingsPlayerOnItemInteractListener getOnItemInteractListener() {
        return (COTDStandingsPlayerOnItemInteractListener) super.getOnItemInteractListener();
    }

    @NonNull
    @Override
    public LeaderboardAdapter.LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leader_board_player, parent, false));
    }

    public class LeaderboardViewHolder extends DefaultViewHolder<COTDStandingsPlayer> {

        private TextView itemLeaderBoardPoints;
        private TextView itemLeaderBoardRank;
        private TextView itemLeaderBoardDisplayName;
        private TextView itemLeaderBoardTrophies;

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            itemLeaderBoardDisplayName = findViewById(R.id.item_leader_board_name);
            itemLeaderBoardPoints = findViewById(R.id.item_leader_board_points);
            itemLeaderBoardRank = findViewById(R.id.item_leader_board_rank);
            itemLeaderBoardTrophies = findViewById(R.id.item_leader_board_trophies);
        }

        @Override
        public void bindItemToViewHolder(COTDStandingsPlayer cotdStandingsPlayer) {
            itemLeaderBoardDisplayName.setText(cotdStandingsPlayer.getDisplayName());
            itemLeaderBoardPoints.setText(cotdStandingsPlayer.getPointsAsString());
            itemLeaderBoardRank.setText(cotdStandingsPlayer.getPositionAsString());
            itemLeaderBoardTrophies.setText(cotdStandingsPlayer.buildTrophyString());
            itemView.setOnClickListener(view -> getOnItemInteractListener().onClick(cotdStandingsPlayer));
        }

    }
}
