package amin.mhd.hasan.tictactoe.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import androidx.annotation.Nullable;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class StringUtils {

    public static Spannable spannableString(CharSequence allString,
                                            String specificString,
                                            boolean isBold,
                                            boolean isColored,
                                            @Nullable String colorHEX) {
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
                    if (colorHEX != null)
                        wordToSpan.setSpan(new ForegroundColorSpan(Color.parseColor(colorHEX)), startIndex, LastIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    else
                        wordToSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), startIndex, LastIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

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
