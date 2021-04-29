package markus.wieland.trackmaniacotdapp.api;

import android.app.Activity;

import markus.wieland.defaultappelements.api.API;
import markus.wieland.defaultappelements.api.APIResult;
import markus.wieland.defaultappelements.api.RequestResultListener;
import markus.wieland.trackmaniacotdapp.TrackmaniaGetRequest;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTD;
import markus.wieland.trackmaniacotdapp.api.models.cotd.COTDLeaderBoard;
import markus.wieland.trackmaniacotdapp.api.models.cotd.overview.Overview;
import markus.wieland.trackmaniacotdapp.api.models.cotd.summary.PlayerSummary;

public class TrackmaniaCOTDApi extends API {

    private static final String BASE_URL = "http://sowiemarkus.com:8080/cotd/";

    public TrackmaniaCOTDApi(Activity context) {
        super(context);
    }

    public void getCOTDResult(APIResult<COTD> result, int year, int month, int day) {
        String url = BASE_URL + year + "/" + month + "/" + day;
        TrackmaniaGetRequest<COTD> getRequest = new TrackmaniaGetRequest<>(COTD.class, url, new RequestResultListener<COTD>() {
            @Override
            public void onLoad(COTD response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        getRequest.execute();
    }

    public void getGlobalLeaderboard(APIResult<COTDLeaderBoard> result) {
        String url = BASE_URL + "global";
        TrackmaniaGetRequest<COTDLeaderBoard> getRequest = new TrackmaniaGetRequest<>(COTDLeaderBoard.class, url, new RequestResultListener<COTDLeaderBoard>() {
            @Override
            public void onLoad(COTDLeaderBoard response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        getRequest.execute();
    }

    public void getLeaderboard(APIResult<COTDLeaderBoard> result, int year, int month) {
        String url = BASE_URL + year + "/" + month;
        TrackmaniaGetRequest<COTDLeaderBoard> getRequest = new TrackmaniaGetRequest<>(COTDLeaderBoard.class, url, new RequestResultListener<COTDLeaderBoard>() {
            @Override
            public void onLoad(COTDLeaderBoard response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        getRequest.execute();
    }

    public void getOverview(APIResult<Overview> result) {
        String url = BASE_URL + "overview";
        TrackmaniaGetRequest<Overview> getRequest = new TrackmaniaGetRequest<>(Overview.class, url, new RequestResultListener<Overview>() {
            @Override
            public void onLoad(Overview response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        getRequest.execute();
    }

    public void getGlobalSummary(APIResult<PlayerSummary> result, String accountId) {
        String url = BASE_URL + "global/" + accountId;
        TrackmaniaGetRequest<PlayerSummary> getRequest = new TrackmaniaGetRequest<>(PlayerSummary.class, url, new RequestResultListener<PlayerSummary>() {
            @Override
            public void onLoad(PlayerSummary response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        getRequest.execute();
    }

    public void getMonthlySummary(APIResult<PlayerSummary> result, String accountId, int year, int month) {
        String url = BASE_URL + "summary/" + year + "/" + month + "/" + accountId;
        TrackmaniaGetRequest<PlayerSummary> getRequest = new TrackmaniaGetRequest<>(PlayerSummary.class, url, new RequestResultListener<PlayerSummary>() {
            @Override
            public void onLoad(PlayerSummary response) {
                notifyClient(response, result);
            }

            @Override
            public void onError(Exception e) {
                notifyClient(null, result);
            }
        });
        getRequest.execute();
    }


}
