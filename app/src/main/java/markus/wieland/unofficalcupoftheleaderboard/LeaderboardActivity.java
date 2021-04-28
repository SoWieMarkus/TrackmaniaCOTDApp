package markus.wieland.unofficalcupoftheleaderboard;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.unofficalcupoftheleaderboard.api.TrackmaniaCOTDApi;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDLeaderBoard;
import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDStandingsPlayer;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.TOTD;
import markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard.LeaderBoardGlobalFragment;
import markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard.LeaderBoardMonthFragment;
import markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard.LeaderboardAdapter;
import markus.wieland.unofficalcupoftheleaderboard.ui.totd.TOTDFragment;
import markus.wieland.unofficalcupoftheleaderboard.ui.totd.TOTDMainFragment;

public class LeaderboardActivity extends DefaultActivity implements LeaderboardAdapter.COTDStandingsPlayerOnItemInteractListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private LeaderBoardGlobalFragment globalStandings;
    private LeaderBoardMonthFragment monthStandings;
    private TOTDMainFragment totdFragment;

    private BottomNavigationView bottomNavigationView;

    public LeaderboardActivity() {
        super(R.layout.activity_leaderboard);
    }

    @Override
    public void bindViews() {
        bottomNavigationView = findViewById(R.id.activity_leaderboard_bottom_navigation_view);
    }

    @Override
    public void initializeViews() {
        globalStandings = new LeaderBoardGlobalFragment();
        globalStandings.setCotdStandingsPlayerOnItemInteractListener(this);
        monthStandings = new LeaderBoardMonthFragment();
        monthStandings.setCotdStandingsPlayerOnItemInteractListener(this);
        totdFragment = new TOTDMainFragment();
        totdFragment.setTotdItemInteractListener(this::onClick);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public void onClick(TOTD totd) {
        TOTDFragment totdDetailFragment = new TOTDFragment();
        totdDetailFragment.load(totd);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_leaderboard_frame_layout
                        , totdDetailFragment)
                .addToBackStack(totdFragment.getTag())
                .commit();
    }

    @Override
    public void execute() {
        TrackmaniaCOTDApi trackmaniaCOTDApi = new TrackmaniaCOTDApi(this);
        trackmaniaCOTDApi.getGlobalLeaderboard(this::onLoad);

        showFragment(totdFragment);
    }

    private void onLoad(COTDLeaderBoard globalLeaderBoard) {
        globalStandings.update(globalLeaderBoard == null ? new ArrayList<>() : globalLeaderBoard.getStandings(), true);
    }

    @Override
    public void onClick(COTDStandingsPlayer cotdStandingsPlayer) {
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(cotdStandingsPlayer.getUrl())));
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_leaderboard_frame_layout
                        , fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_activity_leaderboard_bottom_global) {
            showFragment(globalStandings);
        } else if (item.getItemId() == R.id.menu_activity_leaderboard_bottom_monthly) {
            showFragment(monthStandings);
        } else {
            showFragment(totdFragment);
        }
        return true;
    }


}