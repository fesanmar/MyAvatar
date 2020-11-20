package com.felipesantacruz.myavatar.dialogs;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.felipesantacruz.myavatar.R;
import com.felipesantacruz.myavatar.avatar.Avatar;

public class DialogName extends AvatarDialogTemplate {

    private EditText editTextName;

    public DialogName(Avatar.Builder avatarBuilder) {
        super(avatarBuilder);
    }

    @Override
    protected void initializeInnerViews(View thisView) {
        editTextName = thisView.findViewById(R.id.editTextName);
    }

    @Override
    protected int getDialogLayoutToInflate() {
        return R.layout.dialog_name;
    }

    @Override
    protected String getDialogTitle() {
        return getString(R.string.dialog_name_title);
    }

    @Override
    protected void onPositiveButtonClick() {
        String name = editTextName.getText().toString().trim();
        if (!name.isEmpty())
            fillAndGoNext(name);
        else
            Toast.makeText(getContext(), getString(R.string.name_empty_error_message), Toast.LENGTH_SHORT).show();
    }

    private void fillAndGoNext(String name) {
        getAvatarBuilder().withName(name);
        getAlertDialog().dismiss();
        AvatarDialogTemplate dialogGender = new DialogGender(getAvatarBuilder());
        dialogGender.show(getActivity().getSupportFragmentManager(), getString(R.string.dialog_gender_title));
    }
}
