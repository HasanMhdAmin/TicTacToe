package amin.mhd.hasan.tictactoe.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class DialogUtils {

    private static ProgressDialog dialog;


    public static void showFakeProgressDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("asdasdasd");
        dialog.show();

        int stopAfter = RandomUtils.getRandomIntInRange(3, 8);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, stopAfter * 1000);
    }

}
