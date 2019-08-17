package amin.mhd.hasan.tictactoe.callback;

import android.widget.ImageView;

import amin.mhd.hasan.tictactoe.customView.TileView;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public interface OnTileClickListener {
    void onSelectValue(TileView tileView, ImageView imageView, int value);
}
