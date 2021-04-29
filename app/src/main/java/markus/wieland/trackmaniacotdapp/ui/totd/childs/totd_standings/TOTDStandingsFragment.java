package markus.wieland.trackmaniacotdapp.ui.totd.childs.totd_standings;

import android.content.Intent;
import android.net.Uri;

import markus.wieland.trackmaniacotdapp.helper.OnClickListener;
import markus.wieland.trackmaniacotdapp.R;
import markus.wieland.trackmaniacotdapp.api.models.totd.leaderboard.TOTDLeaderBoardPlayer;
import markus.wieland.trackmaniacotdapp.helper.ListFragment;

public class TOTDStandingsFragment extends ListFragment<TOTDLeaderBoardPlayer, TOTDLeaderBoardAdapter.TOTDLeaderBoardViewHolder> implements OnClickListener<TOTDLeaderBoardPlayer> {

    public TOTDStandingsFragment() {
        super(R.layout.fragment_list, R.id.fragment_list_recycler_view, R.id.fragment_list_progress_bar, R.id.fragment_list_empty_message);
    }

    @Override
    public TOTDLeaderBoardAdapter getAdapter() {
        return new TOTDLeaderBoardAdapter(this);
    }

    @Override
    public void onClick(TOTDLeaderBoardPlayer totdLeaderBoardPlayer) {
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(totdLeaderBoardPlayer.getUrl())));
    }
}
