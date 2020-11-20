package com.felipesantacruz.myavatar.dialogs;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.felipesantacruz.myavatar.R;
import com.felipesantacruz.myavatar.avatar.Avatar;
import com.felipesantacruz.myavatar.avatar.Race;
import com.felipesantacruz.myavatar.utils.StringResourcesUtils;

public class DialogRace extends AvatarDialogTemplate {
    private RadioGroup groupRace;

    public DialogRace(Avatar.Builder avatarBuilder) {
        super(avatarBuilder);
    }

    @Override
    protected void initializeInnerViews(View thisView) {
        groupRace = thisView.findViewById(R.id.groupRaces);
    }

    @Override
    protected int getDialogLayoutToInflate() {
        return R.layout.dialog_race;
    }

    @Override
    protected String getDialogTitle() {
        return getString(R.string.dialog_race_title);
    }

    @Override
    protected void onPositiveButtonClick() {
        int raceSelectionId = groupRace.getCheckedRadioButtonId();
        if (isValid(raceSelectionId))
            setRaceAndGoNext(raceSelectionId);
        else
            displayToastWithText(getString(R.string.race_empty_error_message));
    }

    private boolean isValid(int raceSelectionId) {
        return raceSelectionId != -1;
    }

    private void setRaceAndGoNext(int raceSelectionId) {

        getAvatarBuilder()
                .withRace(new Race(getRaceResourceId(raceSelectionId)));
        getAlertDialog().dismiss();
        AvatarDialogTemplate dialogProfession = new DialogProfession(getAvatarBuilder());
        dialogProfession.show(
                getActivity().getSupportFragmentManager(),
                getString(R.string.dialog_profession_title));
    }

    private int getRaceResourceId(int raceSelectionId) {
        if (raceSelectionId == R.id.radioButtonHuman)
            return R.string.race_human;
        else if (raceSelectionId == R.id.radioButtonElf)
            return R.string.race_elf;
        else if (raceSelectionId == R.id.radioButtonHobbit)
            return R.string.race_hobbit;
        else if (raceSelectionId == R.id.radioButtonDwarf)
            return R.string.race_dwarf;
        return 0;
    }
}
