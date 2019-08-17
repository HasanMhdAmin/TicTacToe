package amin.mhd.hasan.tictactoe.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import amin.mhd.hasan.tictactoe.R;
import amin.mhd.hasan.tictactoe.callback.OnTileClickListener;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class TileView extends FrameLayout implements View.OnClickListener {

    private int value;
    private FrameLayout tile;
    private ImageView tileImage;
    private Context context;

    private OnTileClickListener onTileClickListener;

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.Options, 0, 0);

        try {
            value = typedArray.getInt(R.styleable.Options_value, -1);
        } finally {
            typedArray.recycle();
        }

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.tile_item, this, true);

        tile = v.findViewById(R.id.tile);
        tileImage = v.findViewById(R.id.tileImage);

        tile.setOnClickListener(this);

        onTileClickListener = null;

    }

    public void click() {
        tile.performClick();
    }

    public void setOnTileClickListener(OnTileClickListener listener) {
        this.onTileClickListener = listener;
    }


    @Override
    public void onClick(View view) {
        onTileClickListener.onSelectValue(this, tileImage, getValue());
    }

    public int getValue() {
        return value;
    }
}
