package markus.wieland.trackmaniacotdapp.api;

import android.app.Activity;

import markus.wieland.defaultappelements.api.API;
import markus.wieland.defaultappelements.api.APIResult;
import markus.wieland.defaultappelements.api.RequestResultListener;
import markus.wieland.trackmaniacotdapp.TrackmaniaGetRequest;
import markus.wieland.trackmaniacotdapp.api.models.totd.TOTDMonth;
import markus.wieland.trackmaniacotdapp.api.models.totd.leaderboard.TOTDLeaderBoard;

public class TrackmaniaioAPI extends API {

    private static final String BASE_URL = "https://trackmania.io/api/";

    private static final String TOTD = "totd/";
    private static final String LEADER_BOARD = "leaderboard/";

    public TrackmaniaioAPI(Activity context) {
        super(context);
    }

    public void getTOTDLeaderBoard(APIResult<TOTDLeaderBoard> result, String leaderBoardId, String mapUid) {
        String url = BASE_URL + LEADER_BOARD + leaderBoardId + "/" + mapUid;
        TrackmaniaGetRequest<TOTDLeaderBoard> routesGetRequest = new TrackmaniaGetRequest<>(TOTDLeaderBoard.class, url, new RequestResultListener<TOTDLeaderBoard>() {
            @Override
            public void onLoad(TOTDLeaderBoard response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        routesGetRequest.execute();
    }

    public void getTOTDMonth(APIResult<TOTDMonth> result, int monthOffset) {
        String url = BASE_URL + TOTD + monthOffset;
        TrackmaniaGetRequest<TOTDMonth> routesGetRequest = new TrackmaniaGetRequest<>(TOTDMonth.class, url, new RequestResultListener<TOTDMonth>() {
            @Override
            public void onLoad(TOTDMonth response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        routesGetRequest.execute();
    }


}
