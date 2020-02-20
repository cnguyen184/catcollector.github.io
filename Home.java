package com.yiuwin.catcollection;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity {

    // Initialize popup dialogs
    Dialog welcomeDialog;
    Dialog catDialog;
    Dialog retakeDialog;
    Dialog settingsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Welcome dialog
        welcomeDialog = new Dialog(this);
        welcomeDialog.setContentView(R.layout.collectionpopup);
        welcomeDialog.show();

        // Timed cat appeared dialog
        catDialog = new Dialog(this);

        // Retake/Keep dialog
        retakeDialog = new Dialog(this);

        // Settings dialog
        settingsDialog = new Dialog(this);

        // Randomly set timer to demonstrate cat appearance
        // Cat will appear 15 seconds after entering the home screen
        new Handler().postDelayed(new Runnable() {
            public void run() {

                ImageView img= (ImageView) findViewById(R.id.imgAR);
                img.setVisibility(View.VISIBLE);

                // Show FIRST TIME cat photo dialog
                catDialog.setContentView(R.layout.catappearedpopup);
                catDialog.show();

            }
        }, 15000);
    }


    // TODO: "Cat Collection Album" Activity -> edit the files: Album.java and activity_album.xml
    public void showCollection(View v) {
        Intent intent = new Intent (this, Album.class); // when the user clicks the album icon, they are directed to the Album activity (Album.java and activity_album.xml)
        startActivity(intent);
    }

    // TODO: Settings popup dialog -> edit the files: settingspopup.xml (for layout) and this file for functions added to the layout
    public void showSettingsDialog(View v) {
        settingsDialog.setContentView(R.layout.settingspopup); // when the user clicks on the settings icon, "settingspopup.xml" will be shown
        settingsDialog.show();
    }

    // Helper function to create user feedback alerts
    public void displayAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(231,221,206)));
        new Handler().postDelayed(new Runnable() { // close alert after 2 seconds
            public void run() { dialog.dismiss(); }
        }, 2000);
    }

    // Function called when the user attempts to pet/feed the cat
    public void petFeed(View v) { // heart animation on pet/feed

        ImageView img = (ImageView) findViewById(R.id.imgAR);

        // random chance of cat running away
        Random rand = new Random();
        int chance = rand.nextInt((10) + 1);

        if (img.getVisibility() == View.VISIBLE) {
            if (chance > 3) { // 30% chance that the cat has run away
                ImageView heart = (ImageView) findViewById(R.id.imgHeart);

                Animation startFadeOutAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_out);
                heart.startAnimation(startFadeOutAnimation);
            }
            else {
                displayAlert("Oh no, looks like the cat ran away! Maybe next time.");
                img.setVisibility(View.INVISIBLE);
            }
        }
    }

    // Function called when user attempts to take a picture of the cat
    public void showRetakeDialog(View v) {
        ImageView img= (ImageView) findViewById(R.id.imgAR);

        // random chance of cat running away
        Random rand = new Random();
        int chance = rand.nextInt((10) + 1);

        if (img.getVisibility() == View.VISIBLE) {
            if (chance > 3) { // 30% chance that the cat has run away
                retakeDialog.setContentView(R.layout.retakepopup);
                retakeDialog.show();
            }
            else {
                displayAlert("Oh no, looks like the cat ran away! Maybe next time.");
                img.setVisibility(View.INVISIBLE);
            }
        }
        else {
            displayAlert("You can only take pictures of cats when there is a cat!!");
        }
    }

    // Function called when the user attempts to save the photo to his/her photos
    public void keepPhoto(View v) {
        displayAlert("Picture has been saved to your photos!");
        retakeDialog.dismiss();
    }

    public void closeCollection(View v) {
        welcomeDialog.dismiss();
    }
    public void closeCat(View v) {
        catDialog.dismiss();
    }
    public void closeRetake(View v) {
        retakeDialog.dismiss();
    }

}
