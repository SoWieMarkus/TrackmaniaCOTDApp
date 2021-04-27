package markus.wieland.unofficalcupoftheleaderboard.helper;

public class StyleConverter {

    private StyleConverter(){}

    public static String buildAsTimeString(long scoreInMillis) {
        long millis = scoreInMillis % 1000;
        long seconds = scoreInMillis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return build(minutes,2) + ":" + build(seconds,2) + "." + build(millis, 3);
    }

    public static String build(long value, long max) {
        if (max == 2) return value < 10 ? "0" + value : value + "";
        else return value < 10 ? "00" + value : value < 100 ? "0" + value : value + "";
    }

    public static String buildDifference(long scoreInMillis){
        long millis = scoreInMillis % 1000;
        long seconds = scoreInMillis / 1000;
        return "+" + seconds + "." + build(millis, 3);
    }

}
