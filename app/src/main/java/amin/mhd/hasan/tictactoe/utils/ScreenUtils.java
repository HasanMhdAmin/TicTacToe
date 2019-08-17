package amin.mhd.hasan.tictactoe.utils;

import android.app.Activity;
import android.view.WindowManager;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class ScreenUtils {

    public static void setStatusBarTranslucent(Activity context, boolean makeTranslucent) {
        if (makeTranslucent) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
