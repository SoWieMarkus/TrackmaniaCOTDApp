package markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard;

import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.TrackmaniaCOTDApi;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDLeaderBoard;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDStandingsPlayer;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.overview.MonthOverView;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.overview.Overview;
import markus.wieland.unofficalcupoftheleaderboard.helper.DateManager;
import markus.wieland.unofficalcupoftheleaderboard.ui.ListFragment;
import markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard.LeaderboardAdapter.LeaderboardViewHolder;

public class LeaderBoardMonthFragment extends ListFragment<COTDStandingsPlayer, LeaderboardViewHolder> implements LeaderboardAdapter.COTDStandingsPlayerOnItemInteractListener {

    private LeaderboardAdapter.COTDStandingsPlayerOnItemInteractListener cotdStandingsPlayerOnItemInteractListener;

    private Overview overview;
    private TrackmaniaCOTDApi trackmaniaCOTDApi;
    private int overViewIndex;

    private Button buttonLeftMonth;
    private Button buttonRightMonth;

    private TextView textViewMonthName;

    public LeaderBoardMonthFragment() {
        super(R.layout.fragment_leaderboard_month,
                R.id.fragment_leader_board_monthly_recycler_view,
                R.id.fragment_leader_board_progress_bar,
                R.id.fragment_leader_board_empty_message);
        overViewIndex = 0;
    }

    public void setCotdStandingsPlayerOnItemInteractListener(LeaderboardAdapter.COTDStandingsPlayerOnItemInteractListener cotdStandingsPlayerOnItemInteractListener) {
        this.cotdStandingsPlayerOnItemInteractListener = cotdStandingsPlayerOnItemInteractListener;
    }

    @Override
    public DefaultAdapter<COTDStandingsPlayer, LeaderboardViewHolder> getAdapter() {
        return new LeaderboardAdapter(this);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        buttonLeftMonth = findViewById(R.id.fragment_leader_board_monthly_left);
        buttonRightMonth = findViewById(R.id.fragment_leader_board_monthly_right);
        textViewMonthName = findViewById(R.id.item_leader_board_name);
    }

    @Override
    public void initializeViews() {
        super.initializeViews();
        buttonLeftMonth.setOnClickListener(view -> {
            overViewIndex--;
            search();
        });
        buttonRightMonth.setOnClickListener(view -> {
            overViewIndex++;
            search();
        });

        if (overview == null) {
            overview = getDefaultOverview();
            overViewIndex = 0;
        }

        updateButtons();

    }

    private void updateButtons() {
        buttonLeftMonth.setEnabled(overViewIndex - 1 >= 0);
        buttonRightMonth.setEnabled(overViewIndex + 1 < overview.getOverView().size());
        buttonLeftMonth.setText(DateManager.getNameOfMonth(overview.getOverView().get(overViewIndex).getMonth() - 1, TextStyle.SHORT_STANDALONE));
        buttonRightMonth.setText(DateManager.getNameOfMonth(overview.getOverView().get(overViewIndex).getMonth() + 1, TextStyle.SHORT_STANDALONE));
        textViewMonthName.setText(DateManager.getNameOfMonth(overview.getOverView().get(overViewIndex).getMonth()) + " " + overview.getOverView().get(overViewIndex).getYear());
    }

    @Override
    public void execute() {
        super.execute();
        update(null, true);
        trackmaniaCOTDApi = new TrackmaniaCOTDApi(getActivity());
        trackmaniaCOTDApi.getOverview(this::onLoad);
    }

    private Overview getDefaultOverview() {
        Overview overview = new Overview();
        MonthOverView monthOverView = new MonthOverView();
        monthOverView.setMonth(LocalDateTime.now().getMonthValue());
        monthOverView.setYear(LocalDateTime.now().getYear());
        overview.setOverView(new ArrayList<>(Collections.singletonList(monthOverView)));
        return overview;
    }

    private void onLoad(Overview overview) {
        if (overview == null) {
            overview = getDefaultOverview();
        }

        if (overview.getOverView().size() != this.overview.getOverView().size())
            overViewIndex = overview.getOverView().size() - 1;
        this.overview = overview;
        search();
    }


    private void search() {
        MonthOverView monthOverView = overview.getOverView().get(overViewIndex);
        updateButtons();
        update(null, true);
        trackmaniaCOTDApi.getLeaderboard(this::onLoad, monthOverView.getYear(), monthOverView.getMonth());
    }

    private void onLoad(COTDLeaderBoard cotdLeaderBoard) {
        if (cotdLeaderBoard == null)
            update(new ArrayList<>(), true);
        else if (cotdLeaderBoard.getMonth() == overview.getOverView().get(overViewIndex).getMonth())
            update(cotdLeaderBoard.getStandings(), true);
    }

    @Override
    public void onClick(COTDStandingsPlayer cotdStandingsPlayer) {
        if (cotdStandingsPlayerOnItemInteractListener != null)
            cotdStandingsPlayerOnItemInteractListener.onClick(cotdStandingsPlayer);
    }
}
