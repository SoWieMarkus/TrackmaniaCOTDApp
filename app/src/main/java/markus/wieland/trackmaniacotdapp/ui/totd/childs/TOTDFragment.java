package markus.wieland.trackmaniacotdapp.ui.totd.childs;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;
import markus.wieland.defaultappelements.uielements.viewpager.ViewPageAdapter;
import markus.wieland.defaultappelements.uielements.viewpager.ViewPageAdapterItem;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.TrackmaniaCOTDApi;
import markus.wieland.trackmaniacotdapp.api.TrackmaniaioAPI;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTD;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTDPlayerResult;
import markus.wieland.trackmaniacotdapp.api.models.totd.TOTD;
import markus.wieland.trackmaniacotdapp.api.models.totd.leaderboard.TOTDLeaderBoard;
import markus.wieland.trackmaniacotdapp.api.models.totd.leaderboard.TOTDLeaderBoardPlayer;
import markus.wieland.trackmaniacotdapp.helper.ListFragment;
import markus.wieland.trackmaniacotdapp.ui.totd.childs.cotd_standings.COTDPlayerResultAdapter;
import markus.wieland.trackmaniacotdapp.ui.totd.childs.cotd_standings.COTDStandingsFragment;
import markus.wieland.trackmaniacotdapp.ui.totd.childs.totd_standings.TOTDLeaderBoardAdapter;
import markus.wieland.trackmaniacotdapp.ui.totd.childs.totd_standings.TOTDStandingsFragment;

public class TOTDFragment extends DefaultFragment {

    private ImageView imageViewThumbnail;
    private RecyclerView recyclerViewTotdTimes;
    private TextView textViewMapName;
    private TextView textViewMapAuthor;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TrackmaniaioAPI trackmaniaioAPI;
    private TrackmaniaCOTDApi trackmaniaCOTDApi;

    private final MapScoreAdapter mapScoreAdapter;

    private ListFragment<TOTDLeaderBoardPlayer, TOTDLeaderBoardAdapter.TOTDLeaderBoardViewHolder> totdStandings;
    private ListFragment<COTDPlayerResult, COTDPlayerResultAdapter.COTDPlayerResultViewHolder> cotdStandings;

    private TOTD currentTOTD;

    public TOTDFragment() {
        super(R.layout.fragment_totd);
        mapScoreAdapter = new MapScoreAdapter();
    }

    @Override
    public void bindViews() {
        imageViewThumbnail = findViewById(R.id.fragment_totd_thumbnail);
        recyclerViewTotdTimes = findViewById(R.id.fragment_totd_recycler_view);
        textViewMapName = findViewById(R.id.fragment_totd_map);
        textViewMapAuthor = findViewById(R.id.fragment_totd_author);
        tabLayout = findViewById(R.id.fragment_totd_tab_layout);
        viewPager = findViewById(R.id.fragment_totd_view_pager);
    }

    @Override
    public void initializeViews() {
        recyclerViewTotdTimes.setAdapter(mapScoreAdapter);
        recyclerViewTotdTimes.setHasFixedSize(true);
        recyclerViewTotdTimes.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        totdStandings = new TOTDStandingsFragment();
        cotdStandings = new COTDStandingsFragment();

        List<ViewPageAdapterItem> viewPageAdapterItems = new ArrayList<>();
        viewPageAdapterItems.add(new ViewPageAdapterItem(getString(R.string.fragment_totd_title_totd_standings), totdStandings));
        viewPageAdapterItems.add(new ViewPageAdapterItem(getString(R.string.fragment_totd_title_cotd_results), cotdStandings));

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getChildFragmentManager(), viewPageAdapterItems);
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void execute() {
        trackmaniaioAPI = new TrackmaniaioAPI(getActivity());
        trackmaniaCOTDApi = new TrackmaniaCOTDApi(getActivity());
        if (currentTOTD == null) return;
        updateViews();
    }

    public void load(TOTD totd) {
        this.currentTOTD = totd;
        this.mapScoreAdapter.submitList(totd.getMap());
        if (imageViewThumbnail == null) return;
        updateViews();
    }

    private void updateViews(){
        if (getActivity() == null) return;
        Glide.with(getActivity()).load(currentTOTD.getMap().getThumbnailUrl()).into(imageViewThumbnail);
        textViewMapName.setText(currentTOTD.getMap().getColoredMapName());
        textViewMapAuthor.setText(currentTOTD.getMap().getAuthorDisplayName());
        trackmaniaioAPI.getTOTDLeaderBoard(this::onLoad,  currentTOTD.getLeaderBoardUid(),currentTOTD.getMap().getMapUid());
        trackmaniaCOTDApi.getCOTDResult(this::onLoad, currentTOTD.getYear(), currentTOTD.getMonth(), currentTOTD.getDayOfMonth());
    }

    public void onLoad(TOTDLeaderBoard totdLeaderBoard) {
        if (totdLeaderBoard == null){
            totdStandings.update(new ArrayList<>(),true);
            return;
        }
        totdStandings.update(totdLeaderBoard.getTops(), true);
    }

    public void onLoad(COTD cotd) {
        if (cotd == null){
            cotdStandings.update(new ArrayList<>(),true);
            return;
        }
        cotdStandings.update(cotd.getStandings(), true);
    }


}
