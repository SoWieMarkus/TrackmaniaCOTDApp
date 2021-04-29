package markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.summary.PlayerResult;

public class PlayerResultAdapter extends QueryableAdapter<String, PlayerResult, PlayerResultAdapter.PlayerResultViewHolder> {

    public PlayerResultAdapter() {
        super(null);
    }

    @NonNull
    @Override
    public PlayerResultAdapter.PlayerResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlayerResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_summary_result, parent, false));
    }

    public static class PlayerResultViewHolder extends DefaultViewHolder<PlayerResult> {

        private TextView itemSummaryResultPosition;
        private TextView itemSummaryResultDate;

        public PlayerResultViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            itemSummaryResultDate = findViewById(R.id.item_summary_result_date);
            itemSummaryResultPosition = findViewById(R.id.item_summary_result_position);
        }

        @Override
        public void bindItemToViewHolder(PlayerResult playerResult) {
            itemSummaryResultDate.setText(playerResult.getDate());
            itemSummaryResultPosition.setText(playerResult.getPositionAsString());
        }
    }

}
