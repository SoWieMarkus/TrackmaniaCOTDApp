package markus.wieland.unofficalcupoftheleaderboard.ui.totd.childs.cotd_standings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDPlayerResult;

public class COTDPlayerResultAdapter extends QueryableAdapter<String, COTDPlayerResult, COTDPlayerResultAdapter.COTDPlayerResultViewHolder> {


    public COTDPlayerResultAdapter(OnItemInteractListener<COTDPlayerResult> onItemInteractListener) {
        super(onItemInteractListener);
    }

    @NonNull
    @Override
    public COTDPlayerResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new COTDPlayerResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_standings_player, parent, false));
    }

    public static class COTDPlayerResultViewHolder extends DefaultViewHolder<COTDPlayerResult> {

        private TextView itemStandingsName;
        private TextView itemStandingsRank;
        private TextView itemStandingsScore;
        private ImageView itemStandingsFlag;

        public COTDPlayerResultViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            itemStandingsFlag = findViewById(R.id.item_standings_flag);
            itemStandingsRank = findViewById(R.id.item_standings_rank);
            itemStandingsScore = findViewById(R.id.item_standings_score);
            itemStandingsName = findViewById(R.id.item_standings_name);
        }

        @Override
        public void bindItemToViewHolder(COTDPlayerResult cotdPlayerResult) {
            itemStandingsName.setText(cotdPlayerResult.getDisplayName());
            itemStandingsScore.setText(cotdPlayerResult.getPoints());
            itemStandingsRank.setText(cotdPlayerResult.getPositionAsString());
            Glide.with(itemView.getContext()).load(cotdPlayerResult.getZone().getFlagUrl()).into(itemStandingsFlag);
        }
    }

}
