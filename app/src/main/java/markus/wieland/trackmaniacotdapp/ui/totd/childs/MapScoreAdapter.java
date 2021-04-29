package markus.wieland.trackmaniacotdapp.ui.totd.childs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.models.totd.map.Map;
import markus.wieland.trackmaniacotdapp.helper.StyleConverter;

public class MapScoreAdapter extends DefaultAdapter<Long, MapScoreAdapter.MapScoreViewHolder> {

    public MapScoreAdapter() {
        super(null);
    }

    @NonNull
    @Override
    public MapScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MapScoreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time, parent, false));
    }

    public void submitList(Map map) {
        Long[] scores = new Long[]{map.getAuthorScore(), map.getGoldScore(), map.getSilverScore(), map.getBronzeScore()};
        submitList(scores);
    }

    public static class MapScoreViewHolder extends DefaultViewHolder<Long> {

        private ImageView itemTimeMedal;
        private TextView itemTimeScore;

        public MapScoreViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            itemTimeMedal = findViewById(R.id.item_time_medal);
            itemTimeScore = findViewById(R.id.item_time_score);
        }

        @Override
        public void bindItemToViewHolder(Long score) {
            itemTimeMedal.setImageResource(getImageResource());
            itemTimeScore.setText(StyleConverter.buildAsTimeString(score));
        }

        @DrawableRes
        private int getImageResource() {
            switch (getAdapterPosition()) {
                case 0:
                    return R.drawable.medal_author;
                case 1:
                    return R.drawable.medal_gold;
                case 2:
                    return R.drawable.medal_silver;
                default:
                    return R.drawable.medal_bronze;
            }
        }
    }

}
