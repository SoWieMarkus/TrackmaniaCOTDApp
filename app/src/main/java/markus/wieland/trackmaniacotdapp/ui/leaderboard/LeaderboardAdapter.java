package markus.wieland.trackmaniacotdapp.ui.leaderboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTDStandingsPlayer;
import markus.wieland.trackmaniacotdapp.helper.OnClickListener;

public class LeaderboardAdapter extends QueryableAdapter<String, COTDStandingsPlayer, LeaderboardAdapter.LeaderboardViewHolder> {

    public LeaderboardAdapter(OnClickListener<COTDStandingsPlayer> cotdStandingsPlayerOnItemInteractListener) {
        super(cotdStandingsPlayerOnItemInteractListener);
    }

    @Override
    public OnClickListener<COTDStandingsPlayer> getOnItemInteractListener() {
        return (OnClickListener<COTDStandingsPlayer>) super.getOnItemInteractListener();
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
        private ImageView itemLeaderBoardFlag;

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            itemLeaderBoardDisplayName = findViewById(R.id.item_leader_board_name);
            itemLeaderBoardPoints = findViewById(R.id.item_leader_board_points);
            itemLeaderBoardRank = findViewById(R.id.item_leader_board_rank);
            itemLeaderBoardTrophies = findViewById(R.id.item_leader_board_trophies);
            itemLeaderBoardFlag = findViewById(R.id.item_leader_board_player_flag);
        }

        @Override
        public void bindItemToViewHolder(COTDStandingsPlayer cotdStandingsPlayer) {
            itemLeaderBoardDisplayName.setText(cotdStandingsPlayer.getDisplayName());
            itemLeaderBoardPoints.setText(cotdStandingsPlayer.getPointsAsString());
            itemLeaderBoardRank.setText(cotdStandingsPlayer.getPositionAsString());
            itemLeaderBoardTrophies.setText(cotdStandingsPlayer.buildTrophyString(itemView.getContext()));
            if (cotdStandingsPlayer.getZone() != null) {
                Glide.with(itemView.getContext()).load(cotdStandingsPlayer.getZone().getFlagUrl()).into(itemLeaderBoardFlag);
            }
            itemView.setOnClickListener(view -> getOnItemInteractListener().onClick(cotdStandingsPlayer));
        }

    }
}
