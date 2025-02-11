package com.felipesantacruz.myavatar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.felipesantacruz.myavatar.avatar.Avatar;
import com.felipesantacruz.myavatar.avatar.AvatarBuilderClient;
import com.felipesantacruz.myavatar.dialogs.DialogName;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements AvatarBuilderClient {

    private ImageView imageAvatar;
    private Avatar.Builder avatarBuilder;
    private TextView textViewStats;
    private TextView textViewName;
    private TextView textViewMonitor;
    private Avatar avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewFields();
    }

    private void initializeViewFields() {
        textViewStats = findViewById(R.id.textViewStats);
        textViewName = findViewById(R.id.textViewName);
        textViewMonitor = findViewById(R.id.textViewMonitor);
        avatarBuilder = new Avatar.Builder();
        avatarBuilder.addSubscriber(this);
        imageAvatar = findViewById(R.id.imageAvatar);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(this::showFirstAvatarDialog);
    }

    private void showFirstAvatarDialog(View view) {
        DialogName dialogName = new DialogName(avatarBuilder);
        dialogName.show(getSupportFragmentManager(), getString(R.string.dialog_name_title));
    }

    @Override
    public void receive(Avatar avatar) {
        this.avatar = avatar;
        String myDrawable = avatar.getImageName();
        int myDrawableId = getResources().getIdentifier(myDrawable, "drawable", getPackageName());
        if (myDrawableId != 0)
            displayNewAvatar(myDrawableId);
        else
            setInitialState();
    }

    private void displayNewAvatar(int myDrawableId) {
        textViewStats.setText(getString(R.string.stats,
                avatar.getLifePoints(),
                avatar.getManaPoints(),
                avatar.getStrengthPoints(),
                avatar.getSpeedPoints()));
        imageAvatar.setImageResource(myDrawableId);
        textViewName.setText(avatar.getName());
        textViewMonitor.setText(getString(R.string.enjoy_tour_avatar));
    }

    private void setInitialState() {
        textViewStats.setText("");
        imageAvatar.setImageResource(R.drawable.ic_human_female_wizard);
        textViewName.setText("");
        textViewMonitor.setText(R.string.create_new_avatar);
    }
}