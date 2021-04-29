package markus.wieland.trackmaniacotdapp;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import markus.wieland.defaultappelements.uielements.activities.DefaultActivity;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTDStandingsPlayer;
import markus.wieland.trackmaniacotdapp.api.models.totd.TOTD;
import markus.wieland.trackmaniacotdapp.ui.about.AboutFragment;
import markus.wieland.trackmaniacotdapp.ui.leaderboard.LeaderBoardGlobalFragment;
import markus.wieland.trackmaniacotdapp.ui.leaderboard.LeaderBoardMonthFragment;
import markus.wieland.trackmaniacotdapp.ui.leaderboard.summary.OnClickStandingsPlayer;
import markus.wieland.trackmaniacotdapp.ui.leaderboard.summary.PlayerSummaryFragment;
import markus.wieland.trackmaniacotdapp.ui.totd.TOTDMainFragment;
import markus.wieland.trackmaniacotdapp.ui.totd.childs.TOTDFragment;

public class LeaderboardActivity extends DefaultActivity implements OnClickStandingsPlayer, BottomNavigationView.OnNavigationItemSelectedListener {

    private LeaderBoardGlobalFragment globalStandings;
    private LeaderBoardMonthFragment monthStandings;
    private TOTDMainFragment totdFragment;
    private AboutFragment aboutFragment;

    private BottomNavigationView bottomNavigationView;

    private Fragment currentFragment;

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
        monthStandings = new LeaderBoardMonthFragment();
        totdFragment = new TOTDMainFragment();
        aboutFragment = new AboutFragment();

        globalStandings.setCotdStandingsPlayerOnItemInteractListener(this);
        monthStandings.setCotdStandingsPlayerOnItemInteractListener(this);
        totdFragment.setTotdOnClickListener(this::onClick);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public void onClick(TOTD totd) {
        TOTDFragment totdDetailFragment = new TOTDFragment();
        totdDetailFragment.load(totd);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_leaderboard_frame_layout, totdDetailFragment)
                .addToBackStack(totdFragment.getTag())
                .commit();
    }

    @Override
    public void execute() {
        showFragment(totdFragment);
    }

    @Override
    public void onClick(COTDStandingsPlayer cotdStandingsPlayer, int year, int month) {
        PlayerSummaryFragment playerSummaryFragment = new PlayerSummaryFragment(cotdStandingsPlayer, year, month);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_leaderboard_frame_layout, playerSummaryFragment)
                .addToBackStack(currentFragment.getTag())
                .commit();
    }

    private void showFragment(Fragment fragment) {
        currentFragment = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_leaderboard_frame_layout, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_activity_leaderboard_bottom_global) {
            showFragment(globalStandings);
        } else if (item.getItemId() == R.id.menu_activity_leaderboard_bottom_monthly) {
            showFragment(monthStandings);
        } else if (item.getItemId() == R.id.menu_activity_leaderboard_bottom_totd) {
            showFragment(totdFragment);
        } else {
            showFragment(aboutFragment);
        }
        return true;
    }

}