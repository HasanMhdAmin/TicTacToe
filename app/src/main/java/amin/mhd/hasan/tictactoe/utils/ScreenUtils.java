package amin.mhd.hasan.tictactoe.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import amin.mhd.hasan.tictactoe.R;

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

    public static void createTheam(Context context){
        String theme = StorageUtils.getTheme(context);
        if (theme.equals(StorageUtils.LIGHT))
            context.setTheme(R.style.AppTheme_LIGHT);
        else
            context.setTheme(R.style.AppTheme);
    }

}
