package markus.wieland.unofficalcupoftheleaderboard.ui.leaderboard.summary;

import markus.wieland.unofficalcupoftheleaderboard.api.models.cotd.COTDStandingsPlayer;

public interface OnClickStandingsPlayer {
    void onClick(COTDStandingsPlayer cotdStandingsPlayer, int year, int month);
}
