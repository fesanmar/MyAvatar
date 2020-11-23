package com.felipesantacruz.myavatar.dialogs;

import android.view.View;
import android.widget.RadioGroup;

import com.felipesantacruz.myavatar.R;
import com.felipesantacruz.myavatar.avatar.Avatar;

public class DialogGender extends AbstractAvatarDialog {

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
            setGenderAndGoNext(selectionId);
        else
            displayToastWithText(getString(R.string.gender_empty_error_message));
    }

    private boolean isValid(int selectionId) {
        return selectionId != -1;
    }

    private void setGenderAndGoNext(int selectedRadioButtonId) {
        Avatar.Builder builder = getAvatarBuilder();
        if (isMale(selectedRadioButtonId))
            builder.male();
        else
            builder.female();
        getAlertDialog().dismiss();
        AbstractAvatarDialog dialogRace = new DialogRace(builder);
        dialogRace.show(getActivity().getSupportFragmentManager(), getString(R.string.dialog_race_title));
    }

    private boolean isMale(int selectedRadioButtonId) {
        return selectedRadioButtonId == R.id.radioButtonMale;
    }
}
