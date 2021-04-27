package markus.wieland.unofficalcupoftheleaderboard.ui.totd;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
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
import markus.wieland.unofficalcupoftheleaderboard.R;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.TOTD;

public class TOTDFragment extends DefaultFragment {

    private ImageView imageViewThumbnail;
    private RecyclerView recyclerViewTotdTimes;
    private TextView textViewMapName;
    private TextView textViewMapAuthor;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private final MapScoreAdapter mapScoreAdapter;

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

        List<ViewPageAdapterItem> viewPageAdapterItems = new ArrayList<>();
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getChildFragmentManager(), viewPageAdapterItems);
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void execute() {
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
    }


}
