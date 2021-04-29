package markus.wieland.trackmaniacotdapp.ui.totd.childs.cotd_standings;

import android.content.Intent;
import android.net.Uri;

import markus.wieland.trackmaniacotdapp.helper.OnClickListener;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTDPlayerResult;
import markus.wieland.trackmaniacotdapp.helper.ListFragment;

public class COTDStandingsFragment extends ListFragment<COTDPlayerResult, COTDPlayerResultAdapter.COTDPlayerResultViewHolder> implements OnClickListener<COTDPlayerResult> {

    public COTDStandingsFragment() {
        super(R.layout.fragment_list, R.id.fragment_list_recycler_view, R.id.fragment_list_progress_bar, R.id.fragment_list_empty_message);
    }

    @Override
    public COTDPlayerResultAdapter getAdapter() {
        return new COTDPlayerResultAdapter(null);
    }

    @Override
    public void onClick(COTDPlayerResult cotdPlayerResult) {
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(cotdPlayerResult.getUrl())));
    }
}
