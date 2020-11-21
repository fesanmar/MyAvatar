package com.felipesantacruz.myavatar.dialogs;

import android.view.View;
import android.widget.RadioGroup;

import com.felipesantacruz.myavatar.R;
import com.felipesantacruz.myavatar.avatar.Avatar;
import com.felipesantacruz.myavatar.avatar.Profession;

public class DialogProfession extends AbstractAvatarDialog {

    private RadioGroup groupProfessions;

    public DialogProfession(Avatar.Builder avatarBuilder) {
        super(avatarBuilder);
    }

    @Override
    protected void initializeInnerViews(View thisView) {
        groupProfessions = thisView.findViewById(R.id.groupProfessions);
    }

    @Override
    protected int getDialogLayoutToInflate() {
        return R.layout.dialog_profession;
    }

    @Override
    protected String getDialogTitle() {
        return getString(R.string.dialog_profession_title);
    }

    @Override
    protected void onPositiveButtonClick() {
        int selectionId = groupProfessions.getCheckedRadioButtonId();
        if (isValid(selectionId)) {
            setProfessionAndBuild(selectionId);
        } else
            displayToastWithText(getString(R.string.profession_empty_error_message));
    }

    private void setProfessionAndBuild(int selectionId) {
        getAvatarBuilder()
                .withProfession(new Profession(getProfessionResourceId(selectionId)))
                .build();
        getAlertDialog().dismiss();
    }

    private int getProfessionResourceId(int selectionId) {
        if (selectionId == R.id.radioButtonFighter)
            return R.string.profession_fighter;
        else if (selectionId == R.id.radioButtonRanger)
            return R.string.profession_ranger;
        else if (selectionId == R.id.radioButtonWizard)
            return R.string.profession_wizard;
        else if (selectionId == R.id.radioButtonMiner)
            return R.string.profession_miner;
        else if (selectionId == R.id.radioButtonSmithy)
            return R.string.profession_smithy;
        return 0;
    }

    private boolean isValid(int selectionId) {
        return selectionId != -1;
    }


}
