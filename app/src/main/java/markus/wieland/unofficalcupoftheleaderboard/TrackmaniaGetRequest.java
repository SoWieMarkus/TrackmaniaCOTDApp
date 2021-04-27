package markus.wieland.unofficalcupoftheleaderboard;

import java.net.URL;

import markus.wieland.defaultappelements.api.GetRequest;
import markus.wieland.defaultappelements.api.RequestResultListener;

public class TrackmaniaGetRequest<T> extends GetRequest<T> {

    public TrackmaniaGetRequest(Class<T> type, String url, RequestResultListener<T> requestResultListener) {
        super(type, url, requestResultListener);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //TODO header einfügen
        /*try {
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            String result = parseResult(connection);
            requestResultListener.onLoad(parse(result));
        } catch (Exception e) {
            requestResultListener.onError(e);
        }
        return null;*/
        return null;
    }
}
