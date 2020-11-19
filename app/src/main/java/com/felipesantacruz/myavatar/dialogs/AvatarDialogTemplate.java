package com.felipesantacruz.myavatar.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.felipesantacruz.myavatar.R;
import com.felipesantacruz.myavatar.avatar.Avatar;

public abstract class AvatarDialogTemplate extends DialogFragment implements View.OnClickListener {
    private Avatar.Builder avatarBuilder;
    private Activity activity;
    private int positiveButtonId;
    private AlertDialog dialog;

    public AvatarDialogTemplate(Avatar.Builder avatarBuilder) {
        this.avatarBuilder = avatarBuilder;
    }

    protected Avatar.Builder getAvatarBuilder() {
        return avatarBuilder;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View thisView = createDialogView();
        initializeInnerViews(thisView);
        AlertDialog.Builder dialogBuilder = setUpDialogBuilder(thisView);
        dialog = dialogBuilder.create();
        dialog.setOnShowListener(this::setDialogButtonsListeners);
        return dialog;
    }

    private View createDialogView() {
        LayoutInflater inflater = activity.getLayoutInflater();
        View thisView = inflater.inflate(getDialogLayoutToInflate(), null);
        return thisView;
    }

    protected abstract void initializeInnerViews(View thisView);

    private AlertDialog.Builder setUpDialogBuilder(View thisView) {
        return new AlertDialog.Builder(activity)
                .setView(thisView)
                .setTitle(getDialogTitle())
                .setPositiveButton(R.string.dialog_ok_button, null)
                .setNegativeButton(R.string.dialog_cancel_button, null);
    }

    private void setDialogButtonsListeners(DialogInterface dialog) {
        Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButtonId = positiveButton.getId();
        positiveButton.setOnClickListener(this);
        Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(this);
    }

    protected AlertDialog getAlertDialog() {
        return dialog;
    }

    protected abstract int getDialogLayoutToInflate();

    protected abstract String getDialogTitle();

    @Override
    public void onClick(View v) {
        if (isPositiveButton(v.getId()))
            onPositiveButtonClick();
        else
            dialog.dismiss();
    }

    private boolean isPositiveButton(int which) {
        return which == positiveButtonId;
    }

    protected abstract void onPositiveButtonClick();
}
