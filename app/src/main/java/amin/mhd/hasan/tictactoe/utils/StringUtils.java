package amin.mhd.hasan.tictactoe.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class StringUtils {

    public static Spannable spannableString(CharSequence allString,
                                            String specificString,
                                            boolean isBold,
                                            boolean isColored,
                                            int colorRes) {
        Spannable wordToSpan = null;
        try {
            wordToSpan = new SpannableString(allString);

            int startIndex = -1;
            int LastIndex = -1;

            startIndex = allString.toString().indexOf(specificString);
            LastIndex = startIndex + specificString.length();

            try {
                if (isBold) {
                    wordToSpan.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startIndex, LastIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (isColored) {
                    wordToSpan.setSpan(new ForegroundColorSpan(colorRes), startIndex, LastIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                }
                return wordToSpan;
            } catch (Exception ex) {
                System.out.println("StaticUtilities , spannableString 1: " + ex.getMessage());
                ex.printStackTrace();
                return wordToSpan;
            }
        } catch (Exception ex) {
            System.out.println("StaticUtilities , spannableString 2: " + ex.getMessage());
            ex.printStackTrace();
            return wordToSpan;
        }
    }


}
