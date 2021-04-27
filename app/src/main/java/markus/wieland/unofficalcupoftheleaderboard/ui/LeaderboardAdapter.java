package markus.wieland.unofficalcupoftheleaderboard.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDStandingsPlayer;

public class LeaderboardAdapter extends QueryableAdapter<String, COTDStandingsPlayer, LeaderboardAdapter.LeaderboardViewHolder> {

    public LeaderboardAdapter(OnItemInteractListener onItemInteractListener) {
        super(onItemInteractListener);
    }

    @NonNull
    @Override
    public LeaderboardAdapter.LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class LeaderboardViewHolder extends DefaultViewHolder<COTDStandingsPlayer> {

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {

        }

        @Override
        public void bindItemToViewHolder(COTDStandingsPlayer cotdStandingsPlayer) {

        }
    }
}
