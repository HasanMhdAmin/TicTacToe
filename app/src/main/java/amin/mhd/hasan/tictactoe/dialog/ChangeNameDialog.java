package amin.mhd.hasan.tictactoe.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import amin.mhd.hasan.tictactoe.R;
import amin.mhd.hasan.tictactoe.callback.ChangeNameListener;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class ChangeNameDialog extends DialogFragment implements View.OnClickListener {
    private static final String PLAYER_NAME = "player_name";
    private ChangeNameListener changeNameListener;

    private String name;
    private EditText nameEditText;
    private Button save;

    public ChangeNameDialog() {
        // Required empty public constructor
    }

    public static ChangeNameDialog newInstance(String name) {
        ChangeNameDialog fragment = new ChangeNameDialog();
        Bundle args = new Bundle();
        args.putString(PLAYER_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.name = getArguments().getString(PLAYER_NAME);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_change_name, container, false);

        findViews(view);

        nameEditText.setText(name);

        return view;
    }

    private void findViews(View view) {
        nameEditText = view.findViewById(R.id.name);
        save = view.findViewById(R.id.save);

        save.setOnClickListener(this);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog.getWindow() != null) {
            // request a window without the title
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
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

    private void onSaveClicked(String name) {
        if (changeNameListener != null) {
            changeNameListener.onChangeName(name);
            dismiss();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChangeNameListener) {
            changeNameListener = (ChangeNameListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ChangeNameListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        changeNameListener = null;
    }

    @Override
    public void onClick(View view) {
        if (view == save) {
            onSaveClicked(nameEditText.getText().toString());
        }
    }
}
