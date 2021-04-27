package markus.wieland.unofficalcupoftheleaderboard.ui.totd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.TOTD;
import markus.wieland.unofficalcupoftheleaderboard.helper.StyleConverter;

public class TOTDAdapter extends DefaultAdapter<TOTD, TOTDAdapter.TOTDViewHolder> {

    public interface TOTDItemInteractListener extends OnItemInteractListener<TOTD> {
        void onClick(TOTD totd);
    }

    public TOTDAdapter(TOTDItemInteractListener onItemInteractListener) {
        super(onItemInteractListener);
    }

    @NonNull
    @Override
    public TOTDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TOTDViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_totd, parent, false));
    }

    @Override
    public TOTDItemInteractListener getOnItemInteractListener() {
        return (TOTDItemInteractListener) super.getOnItemInteractListener();
    }

    public class TOTDViewHolder extends DefaultViewHolder<TOTD> {

        private ImageView itemTotdThumbnail;
        private TextView itemTotdAuthor;
        private TextView itemTotdName;
        private TextView itemTotdScore;

        public TOTDViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            itemTotdAuthor = findViewById(R.id.item_totd_author);
            itemTotdName = findViewById(R.id.item_totd_name);
            itemTotdScore = findViewById(R.id.item_totd_score);
            itemTotdThumbnail = findViewById(R.id.item_totd_thumbnail);
        }

        @Override
        public void bindItemToViewHolder(TOTD totd) {
            Glide.with(itemView.getContext()).load(totd.getMap().getThumbnailUrl()).into(itemTotdThumbnail);
            itemTotdName.setText(totd.getMap().getColoredMapName());
            itemTotdAuthor.setText(totd.getMap().getAuthorDisplayName());
            itemTotdScore.setText(StyleConverter.buildAsTimeString(totd.getMap().getAuthorScore()));
            itemView.setOnClickListener(view -> getOnItemInteractListener().onClick(totd));
        }
    }
}
