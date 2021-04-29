package markus.wieland.unofficalcupoftheleaderboard.helper;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;

public abstract class ListFragment<T, H extends DefaultViewHolder<T>> extends DefaultFragment {

    protected RecyclerView recyclerView;
    protected ProgressBar progressBar;

    @IdRes
    private final int recyclerViewId;

    @IdRes
    private final int progressBarId;

    @IdRes
    private final int emptyMessageId;

    protected DefaultAdapter<T, H> adapter;

    protected List<T> currentList;

    protected LinearLayout emptyMessage;

    public ListFragment(int layout, int recyclerViewId, int progressBarId, int emptyMessageId) {
        super(layout);
        this.recyclerViewId = recyclerViewId;
        this.progressBarId = progressBarId;
        this.emptyMessageId = emptyMessageId;
        adapter = getAdapter();
    }

    @Override
    public void bindViews() {
        progressBar = findViewById(progressBarId);
        recyclerView = findViewById(recyclerViewId);
        emptyMessage = findViewById(emptyMessageId);
    }

    public abstract DefaultAdapter<T, H> getAdapter();

    public void update(List<T> tList, boolean update) {
        adapter.submitList(tList == null ? new ArrayList<>() : tList);
        if (update) currentList = tList;
        if (recyclerView != null) updateViews();
    }

    @Override
    public void initializeViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        updateViews();
    }

    public void updateViews() {
        progressBar.setVisibility(View.GONE);
        emptyMessage.setVisibility(View.GONE);
        if (currentList == null) {
            progressBar.setVisibility(View.VISIBLE);
            return;
        }
        if (adapter.getList().isEmpty()) {
            emptyMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void execute() {

    }
}
