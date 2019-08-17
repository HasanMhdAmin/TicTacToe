package amin.mhd.hasan.tictactoe.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import amin.mhd.hasan.tictactoe.R;
import amin.mhd.hasan.tictactoe.callback.OnAiMakeDecisionListener;
import amin.mhd.hasan.tictactoe.utils.RandomUtils;
import amin.mhd.hasan.tictactoe.utils.StringUtils;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class CustomProgressDialog extends DialogFragment {

    private static final String TAG = "CustomProgressDialog";

    private static final String X_LIST = "x_list";
    private static final String O_LIST = "o_list";

    private OnAiMakeDecisionListener onAiMakeDecisionListener;

    private TextView progressText;
    private ArrayList<Integer> xValues;
    private ArrayList<Integer> oValues;

    public CustomProgressDialog() {
        // Required empty public constructor
    }


    public static CustomProgressDialog newInstance(ArrayList<Integer> xValues, ArrayList<Integer> oValues) {
        CustomProgressDialog fragment = new CustomProgressDialog();
        Bundle args = new Bundle();
        args.putIntegerArrayList(X_LIST, xValues);
        args.putIntegerArrayList(O_LIST, oValues);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.xValues = getArguments().getIntegerArrayList(X_LIST);
            this.oValues = getArguments().getIntegerArrayList(O_LIST);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_custom_progress, container, false);
        setCancelable(false);

        findViews(view);

        if (getContext() != null) {
            Spannable spannable = StringUtils.spannableString(progressText.getText(), "AI",
                    true, true, ContextCompat.getColor(getContext(), R.color.white));
            progressText.setText(spannable);
        }

        ArrayList<Integer> initList = initList();

        ArrayList<Integer> availablePlaces = getAvailablePlaces(initList, xValues, oValues);

        int stopAfter = RandomUtils.getRandomIntInRange(1, 3); // change it to (3, 8) as required.
        final int place = RandomUtils.getRandomIntFromSet(availablePlaces);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onAiMakeDecisionListener.onDecisionMaking(place);
                dismiss();
            }
        }, stopAfter * 1000);

        return view;
    }

    private ArrayList<Integer> getAvailablePlaces(ArrayList<Integer> initList, ArrayList<Integer> xValues, ArrayList<Integer> oValues) {
        initList.removeAll(xValues);
        initList.removeAll(oValues);


        for (Integer i : initList) {
            Log.d(TAG, "getAvailablePlaces: i: " + i);
        }


        return initList;
    }

    private ArrayList<Integer> initList() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            integers.add(i);
        }
        return integers;
    }

    private void findViews(View view) {
        progressText = view.findViewById(R.id.progressText);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            // request a window without the title
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        }
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow()
                    .getAttributes().windowAnimations = R.style.DialogAnimation;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAiMakeDecisionListener) {
            onAiMakeDecisionListener = (OnAiMakeDecisionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ChangeNameListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onAiMakeDecisionListener = null;
    }
}
