package com.felipesantacruz.myavatar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.ImageView;

import com.felipesantacruz.myavatar.avatar.Avatar;
import com.felipesantacruz.myavatar.avatar.AvatarBuilderClient;
import com.felipesantacruz.myavatar.dialogs.DialogName;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements AvatarBuilderClient {

    private FloatingActionButton floatingActionButton;
    private ImageView imageAvatar;
    private Avatar.Builder avatarBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        avatarBuilder = new Avatar.Builder();
        imageAvatar = findViewById(R.id.imageAvatar);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
            DialogName dialogName = new DialogName(avatarBuilder);
            dialogName.setCancelable(false);
            dialogName.show(getSupportFragmentManager(), getString(R.string.dialog_name_title));
        });

    }

    @Override
    public void receive(Avatar avatar) {
        String myDrawable = avatar.getImageName();
        int myDrawableId = getResources().getIdentifier(myDrawable, "drawable", getPackageName());
        if (myDrawableId != 0)
            imageAvatar.setImageResource(myDrawableId);
    }
}