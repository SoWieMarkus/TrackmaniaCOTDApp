package markus.wieland.trackmaniacotdapp.ui.totd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.models.totd.TOTD;
import markus.wieland.trackmaniacotdapp.api.models.totd.TOTDMonth;
import markus.wieland.trackmaniacotdapp.helper.DateManager;
import markus.wieland.trackmaniacotdapp.helper.OnClickListener;
import markus.wieland.trackmaniacotdapp.helper.StyleConverter;

public class TOTDAdapter extends DefaultAdapter<TOTD, TOTDAdapter.TOTDViewHolder> {

    private TOTDMonth totdMonth;


    public TOTDAdapter(OnClickListener<TOTD> onClickListener) {
        super(onClickListener);
    }

    @NonNull
    @Override
    public TOTDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TOTDViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_totd, parent, false));
    }

    @Override
    public OnClickListener<TOTD> getOnItemInteractListener() {
        return (OnClickListener<TOTD>) super.getOnItemInteractListener();
    }

    public void setTotdMonth(TOTDMonth totdMonth) {
        this.totdMonth = totdMonth;
    }

    public class TOTDViewHolder extends DefaultViewHolder<TOTD> {

        private ImageView itemTotdThumbnail;
        private TextView itemTotdAuthor;
        private TextView itemTotdName;
        private TextView itemTotdScore;
        private TextView itemTotdDay;

        public TOTDViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            itemTotdAuthor = findViewById(R.id.item_totd_author);
            itemTotdName = findViewById(R.id.item_totd_name);
            itemTotdScore = findViewById(R.id.item_totd_score);
            itemTotdThumbnail = findViewById(R.id.item_totd_thumbnail);
            itemTotdDay = findViewById(R.id.item_totd_day);
        }

        @Override
        public void bindItemToViewHolder(TOTD totd) {
            Glide.with(itemView.getContext()).load(totd.getMap().getThumbnailUrl()).into(itemTotdThumbnail);
            itemTotdName.setText(totd.getMap().getColoredMapName());
            itemTotdAuthor.setText(totd.getMap().getAuthorPlayer().getName());
            itemTotdScore.setText(StyleConverter.buildAsTimeString(totd.getMap().getAuthorScore()));
            itemView.setOnClickListener(view -> getOnItemInteractListener().onClick(totd));
            itemTotdDay.setText(DateManager.getDateWithDayName(totdMonth.getYear(), totdMonth.getMonth(), totd.getDayOfMonth()));
        }
    }
}
