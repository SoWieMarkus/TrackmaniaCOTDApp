package markus.wieland.trackmaniacotdapp.ui.totd;

import android.widget.Button;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;

import markus.wieland.trackmaniacotdapp.helper.OnClickListener;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.TrackmaniaioAPI;
import markus.wieland.trackmaniacotdapp.api.models.totd.TOTD;
import markus.wieland.trackmaniacotdapp.api.models.totd.TOTDMonth;
import markus.wieland.trackmaniacotdapp.helper.DateManager;
import markus.wieland.trackmaniacotdapp.helper.ListFragment;

public class TOTDMainFragment extends ListFragment<TOTD, TOTDAdapter.TOTDViewHolder> implements OnClickListener<TOTD>, SwipeRefreshLayout.OnRefreshListener  {

    private OnClickListener<TOTD> totdOnClickListener;

    private int index;

    private Button buttonLeftMonth;
    private Button buttonRightMonth;

    private TextView textViewMonthName;

    private TrackmaniaioAPI trackmaniaioAPI;

    private SwipeRefreshLayout swipeRefreshLayout;

    public TOTDMainFragment() {
        super(R.layout.fragment_totd_main, R.id.fragment_totd_main_recycler_view, R.id.fragment_totd_main_progress_bar, R.id.fragment_totd_main_empty);
        this.index = 0;
    }

    @Override
    public TOTDAdapter getAdapter() {
        return new TOTDAdapter(this);
    }

    public void setTotdOnClickListener(OnClickListener<TOTD> totdOnClickListener) {
        this.totdOnClickListener = totdOnClickListener;
    }

    @Override
    public void bindViews() {
        super.bindViews();
        textViewMonthName = findViewById(R.id.fragment_totd_main_name);
        buttonLeftMonth = findViewById(R.id.fragment_totd_main_left);
        buttonRightMonth = findViewById(R.id.fragment_totd_main_right);
        swipeRefreshLayout = findViewById(R.id.fragment_totd_main_swipe_refresh);
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
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void search() {
        buttonLeftMonth.setEnabled(false);
        buttonRightMonth.setEnabled(false);
        update(null, true);
        trackmaniaioAPI.getTOTDMonth(this::onLoad, index);
    }

    private void updateButtons() {
        LocalDateTime now = DateManager.getCurrentCOTDDate();

        textViewMonthName.setText(DateManager.getStringFromMonthAndYear(now.minusMonths(index).getYear(), now.minusMonths(index).getMonthValue()));
        buttonRightMonth.setEnabled(index > 0);
        buttonLeftMonth.setEnabled(!isLastPossibleMonth());
        buttonLeftMonth.setText(DateManager.getNameOfMonth(now.minusMonths(index + (long) 1).getMonthValue(), TextStyle.SHORT_STANDALONE));
        buttonRightMonth.setText(DateManager.getNameOfMonth(now.minusMonths(index - (long) 1).getMonthValue(), TextStyle.SHORT_STANDALONE));
    }

    private void onLoad(TOTDMonth totdMonth) {
        if (totdMonth == null){
            update(new ArrayList<>(), true);
            updateButtons();
            return;
        }
        swipeRefreshLayout.setRefreshing(false);

        ((TOTDAdapter) adapter).setTotdMonth(totdMonth);
        Collections.reverse(totdMonth.getDays());
        update(totdMonth.getDays(), true);
        updateButtons();
    }

    @Override
    public void onClick(TOTD totd) {
        LocalDateTime currentMonth = getCurrentMonth();
        totd.setMonth(currentMonth.getMonthValue());
        totd.setYear(currentMonth.getYear());
        if (totdOnClickListener != null)
            totdOnClickListener.onClick(totd);
    }

    private LocalDateTime getCurrentMonth() {
        return DateManager.getCurrentCOTDDate().minusMonths(index);
    }

    public boolean isLastPossibleMonth() {
        LocalDateTime now = DateManager.getCurrentCOTDDate();
        now = now.minusMonths(index);
        return now.getYear() == 2020 && now.getMonthValue() == 7;
    }

    @Override
    public void onRefresh() {
        search();
    }
}
