package com.felipesantacruz.myavatar.dialogs;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.felipesantacruz.myavatar.R;
import com.felipesantacruz.myavatar.avatar.Avatar;

public class DialogGender extends AvatarDialogTemplate {

    private RadioGroup groupGender;

    public DialogGender(Avatar.Builder avatarBuilder) {
        super(avatarBuilder);
    }

    @Override
    protected void initializeInnerViews(View thisView) {
        groupGender = thisView.findViewById(R.id.groupGender);
    }

    @Override
    protected int getDialogLayoutToInflate() {
        return R.layout.dialog_gender;
    }

    @Override
    protected String getDialogTitle() {
        return getString(R.string.dialog_gender_title);
    }

    @Override
    protected void onPositiveButtonClick() {
        int selectionId = groupGender.getCheckedRadioButtonId();
        if (isValid(selectionId))
            buildGenderAndGoNext(selectionId);
        else
            Toast.makeText(getContext(), getString(R.string.gender_empty_error_message), Toast.LENGTH_SHORT).show();
    }

    private boolean isValid(int selectionId) {
        return selectionId != -1;
    }

    private void buildGenderAndGoNext(int selectedRadioButtonId) {
        if (isMale(selectedRadioButtonId))
            getAvatarBuilder().male();
        else
            getAvatarBuilder().female();
        getAlertDialog().dismiss();
    }

    private boolean isMale(int selectedRadioButtonId) {
        return selectedRadioButtonId == R.id.radioButtonMale;
    }
}
