package markus.wieland.trackmaniacotdapp.ui.leaderboard.summary;

import markus.wieland.trackmaniacotdapp.api.models.cotd.COTDStandingsPlayer;

public interface OnClickStandingsPlayer {
    void onClick(COTDStandingsPlayer cotdStandingsPlayer, int year, int month);
}
