package amin.mhd.hasan.tictactoe.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class StorageUtils {

    public static final String DARK = "dark";
    public static final String LIGHT = "light";
    private static final String TAG = "StorageUtils";
    private static final String SHARED_PREFERENCES_KEY = "TIC_TAC_TOE_shared_preferences_key";
    private static final String PLAYER_NAME = "player_name";
    private static final String THEME = "theme";

    public static void clearCache(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public static void setPlayerName(Context context, String playerName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_NAME, playerName);
        editor.apply();
    }

    public static String getPlayerName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PLAYER_NAME, "");

    }

    public static void setTheme(Context context, String theme) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(THEME, theme);
        editor.apply();
    }

    public static String getTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getString(THEME, DARK);

    }
}
