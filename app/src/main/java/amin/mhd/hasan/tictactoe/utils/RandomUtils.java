package amin.mhd.hasan.tictactoe.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class RandomUtils {

    private static final String TAG = "RandomUtils";

    public static int getRandomIntInRange(int minIncluded, int maxIncluded) {
        Random random = new Random();
        return random.nextInt((maxIncluded + 1 - minIncluded)) + minIncluded;
    }


    public static int getRandomIntFromSet(ArrayList<Integer> integers) {


        for (Integer i : integers) {
            Log.d(TAG, "getRandomIntFromSet: i: " + i);
        }

        if (integers.size() > 0) {
            int minIndex = 0;
            int maxIndex = integers.size() - 1;
            Random random = new Random();
            int randomIndex = random.nextInt((maxIndex + 1 - minIndex)) + minIndex;
            return integers.get(randomIndex);
        } else {
            return -1;
        }
    }

}
