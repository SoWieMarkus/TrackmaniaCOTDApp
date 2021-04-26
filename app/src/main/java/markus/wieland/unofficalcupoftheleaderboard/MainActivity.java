package markus.wieland.unofficalcupoftheleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import markus.wieland.defaultappelements.api.APIResult;
import markus.wieland.unofficalcupoftheleaderboard.api.TrackmaniaioAPI;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.TOTDMonth;
import markus.wieland.unofficalcupoftheleaderboard.api.models.totd.leaderboard.TOTDLeaderBoard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TrackmaniaioAPI trackmaniaIOApi = new TrackmaniaioAPI(this);


        trackmaniaIOApi.getTOTDLeaderBoard(new APIResult<TOTDLeaderBoard>() {
            @Override
            public void onLoad(TOTDLeaderBoard totdLeaderBoard) {
                int x = 0;
            }
        },"8af6c696-4355-43b2-92f0-288a2c8f92cb","T7SlpfkFoWe3moK4lZPY_6qZFZm");

        trackmaniaIOApi.getTOTDMonth(new APIResult<TOTDMonth>() {
            @Override
            public void onLoad(TOTDMonth totdMonth) {
                int x = 0;
            }
        }, 0);

        trackmaniaIOApi.getTOTDMonth(new APIResult<TOTDMonth>() {
            @Override
            public void onLoad(TOTDMonth totdMonth) {
                int x = 1;
            }
        }, 1);

    }
}