package amin.mhd.hasan.tictactoe.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class DateUtils {

    public static String convertTimestampToReadableDuration(long timestampDuration){

        int Seconds, Minutes, MilliSeconds ;

        Seconds = (int) (timestampDuration / 1000);
        Minutes = Seconds / 60;
        Seconds = Seconds % 60;
        MilliSeconds = (int) (timestampDuration % 1000);


        String readableTime =  Minutes + ":"
                + String.format("%02d", Seconds) + ":"
                + String.format("%03d", MilliSeconds);

        return readableTime;
    }
}
