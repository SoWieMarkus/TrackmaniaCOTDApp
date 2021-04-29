package markus.wieland.unofficalcupoftheleaderboard.ui.totd.childs.totd_standings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.unofficalcupoftheleaderboard.helper.OnClickListener;
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.leaderboard.TOTDLeaderBoardPlayer;
import markus.wieland.unofficalcupoftheleaderboard.helper.StyleConverter;

public class TOTDLeaderBoardAdapter extends QueryableAdapter<String, TOTDLeaderBoardPlayer, TOTDLeaderBoardAdapter.TOTDLeaderBoardViewHolder> {

    public TOTDLeaderBoardAdapter(OnClickListener<TOTDLeaderBoardPlayer> onClickListener) {
        super(onClickListener);
    }

    @Override
    public OnClickListener<TOTDLeaderBoardPlayer> getOnItemInteractListener() {
        return (OnClickListener<TOTDLeaderBoardPlayer>) super.getOnItemInteractListener();
    }

    @NonNull
    @Override
    public TOTDLeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TOTDLeaderBoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_standings_player, parent, false));
    }

    public class TOTDLeaderBoardViewHolder extends DefaultViewHolder<TOTDLeaderBoardPlayer> {

        private TextView itemStandingsName;
        private TextView itemStandingsRank;
        private TextView itemStandingsScore;
        private ImageView itemStandingsFlag;

        public TOTDLeaderBoardViewHolder(@NonNull View itemView) {
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
        public void bindItemToViewHolder(TOTDLeaderBoardPlayer totdLeaderBoardPlayer) {
            Glide.with(itemView.getContext()).load(totdLeaderBoardPlayer.getZone().getFlagUrl()).into(itemStandingsFlag);
            itemStandingsRank.setText(totdLeaderBoardPlayer.getPositionAsString());
            itemStandingsScore.setText(StyleConverter.buildAsTimeString(totdLeaderBoardPlayer.getTime()));
            itemStandingsName.setText(totdLeaderBoardPlayer.getDisplayName());
            itemView.setOnClickListener(v -> getOnItemInteractListener().onClick(totdLeaderBoardPlayer));
        }
    }

}
