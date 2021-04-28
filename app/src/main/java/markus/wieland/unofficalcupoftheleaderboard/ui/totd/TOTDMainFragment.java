package markus.wieland.unofficalcupoftheleaderboard.ui.totd;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Collections;

import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.TrackmaniaioAPI;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.TOTD;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.TOTDMonth;
import markus.wieland.unofficalcupoftheleaderboard.helper.DateManager;
import markus.wieland.unofficalcupoftheleaderboard.ui.ListFragment;

public class TOTDMainFragment extends ListFragment<TOTD, TOTDAdapter.TOTDViewHolder> implements TOTDAdapter.TOTDItemInteractListener {

    private int index;

    private Button buttonLeftMonth;
    private Button buttonRightMonth;

    private TextView textViewMonthName;

    private TrackmaniaioAPI trackmaniaioAPI;

    public TOTDMainFragment() {
        super(R.layout.fragment_totd_main, R.id.fragment_totd_main_recycler_view, R.id.fragment_totd_main_progress_bar, R.id.fragment_totd_main_empty);
        this.index = 0;
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
    public void bindViews() {
        super.bindViews();
        textViewMonthName = findViewById(R.id.fragment_totd_main_name);
        buttonLeftMonth = findViewById(R.id.fragment_totd_main_left);
        buttonRightMonth = findViewById(R.id.fragment_totd_main_right);
    }

    @Override
    public void execute() {
        super.execute();

        trackmaniaioAPI = new TrackmaniaioAPI(getActivity());

        updateButtons();
        search();
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        buttonLeftMonth.setOnClickListener(view -> {
            index++;
            search();
        });
        buttonRightMonth.setOnClickListener(view -> {
            index--;
            search();
        });
    }

    private void search(){
        buttonLeftMonth.setEnabled(false);
        buttonRightMonth.setEnabled(false);
        update(null, true);
        trackmaniaioAPI.getTOTDMonth(this::onLoad, index);
    }

    private void updateButtons(){
        LocalDateTime now = LocalDateTime.now();
        textViewMonthName.setText(DateManager.getNameOfMonth(now.minusMonths(index).getMonthValue()) + " " + now.getYear());
        buttonRightMonth.setEnabled(index > 0);
        buttonLeftMonth.setEnabled(!isLastPossibleMonth());
        buttonLeftMonth.setText(DateManager.getNameOfMonth(now.minusMonths(index + (long)1).getMonthValue(), TextStyle.SHORT_STANDALONE));
        buttonRightMonth.setText(DateManager.getNameOfMonth(now.minusMonths(index - (long)1).getMonthValue(), TextStyle.SHORT_STANDALONE));
    }

    private void onLoad(TOTDMonth totdMonth) {
        ((TOTDAdapter)adapter).setTotdMonth(totdMonth);
        Collections.reverse(totdMonth.getDays());
        update(totdMonth.getDays(), true);
        updateButtons();
    }

    @Override
    public void onClick(TOTD totd) {
        if (totdItemInteractListener != null)
            totdItemInteractListener.onClick(totd);
    }

    public boolean isLastPossibleMonth(){
        LocalDateTime now = LocalDateTime.now();
        now = now.minusMonths(index);
        return now.getYear() == 2020 && now.getMonthValue() == 7;
    }
}
