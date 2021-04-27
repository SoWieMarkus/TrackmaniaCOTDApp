package markus.wieland.unofficalcupoftheleaderboard.ui.totd;

import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.TrackmaniaioAPI;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.TOTD;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.TOTDMonth;
import markus.wieland.unofficalcupoftheleaderboard.ui.ListFragment;

public class TOTDMainFragment extends ListFragment<TOTD, TOTDAdapter.TOTDViewHolder> implements TOTDAdapter.TOTDItemInteractListener {

    public TOTDMainFragment() {
        super(R.layout.fragment_totd_main, R.id.fragment_totd_main_recycler_view, R.id.fragment_totd_main_progress_bar, R.id.fragment_totd_main_empty);
    }

    @Override
    public TOTDAdapter getAdapter() {
        return new TOTDAdapter(this);
    }

    private TOTDAdapter.TOTDItemInteractListener totdItemInteractListener;

    public void setTotdItemInteractListener(TOTDAdapter.TOTDItemInteractListener totdItemInteractListener) {
        this.totdItemInteractListener = totdItemInteractListener;
    }

    @Override
    public void execute() {
        super.execute();

        TrackmaniaioAPI trackmaniaioAPI = new TrackmaniaioAPI(getActivity());
        trackmaniaioAPI.getTOTDMonth(this::onLoad, 0);

    }

    private void onLoad(TOTDMonth totdMonth) {
        update(totdMonth.getDays(), true);
    }

    @Override
    public void onClick(TOTD totd) {
        if (totdItemInteractListener != null)
            totdItemInteractListener.onClick(totd);
    }
}
