package amin.mhd.hasan.tictactoe.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
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
        setCancelable(false);
        findViews(view);

        nameEditText.setText(name);
        nameEditText.setSelection(nameEditText.getText().length());// but the cursor in the end
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ColorStateList colorStateList;
                if (charSequence.length() > 0)
                    colorStateList = ColorStateList.valueOf(Color.BLACK);
                else
                    colorStateList = ColorStateList.valueOf(Color.RED);
                ViewCompat.setBackgroundTintList(nameEditText, colorStateList);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
            if (isInputValid())
                onSaveClicked(nameEditText.getText().toString());
        }
    }

    private boolean isInputValid() {
        if (nameEditText.getText().length() == 0) {
            ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
            ViewCompat.setBackgroundTintList(nameEditText, colorStateList);

            Vibrator vib = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 200 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (vib != null) {
                    vib.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                }
            } else {
                //deprecated in API 26
                if (vib != null) {
                    vib.vibrate(100);
                }
            }

            return false;
        }
        return true;
    }
}
