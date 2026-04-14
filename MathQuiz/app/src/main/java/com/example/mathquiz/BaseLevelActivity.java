package com.example.mathquiz;

import static kotlinx.coroutines.internal.LocalAtomics_commonKt.getValue;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Objects;
import java.util.Random;

public abstract class BaseLevelActivity extends AppCompatActivity {

    protected Dialog dialog, dialogEnd;
    protected int numLeft, numRight;
    protected int count = 0;
    protected Array array = new Array();
    protected Random random = new Random();
    protected ImageView imgLeft, imgRight;
    protected TextView textLeft, textRight;
    protected TextView textLevel;
    protected Button btnBack;
    protected Animation animation;
    protected int[] images;
    protected int[] texts;
    protected String[] examples;
    protected int[] values;
    protected String levelTitle;
    protected String levelDescription;
    protected String levelEndText;
    protected int backgroundRes;
    protected int previewImageRes;
    protected int previewBackgroundRes;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        animation = AnimationUtils.loadAnimation(this, R.anim.alpha);

        textLevel = findViewById(R.id.text_level);
        imgLeft = findViewById(R.id.img_left);
        imgRight = findViewById(R.id.img_right);
        textLeft = findViewById(R.id.text_left);
        textRight = findViewById(R.id.text_right);
        btnBack = findViewById(R.id.button_back_level);

        setupLevel();


        ConstraintLayout mainLayout = findViewById(R.id.main);
        if (mainLayout != null && backgroundRes != 0) {
            mainLayout.setBackgroundResource(backgroundRes);
        } else if (mainLayout != null) {
            mainLayout.setBackgroundResource(R.drawable.prev_lev_background_one);
        }

        textLevel.setText(levelTitle);

        createPreviewDialog();
        createEndDialog();

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(this, GameLevels.class));
            finish();
        });

        generateNewPair();

        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };

        imgLeft.setOnTouchListener((v, event) -> handleTouch(event, true, progress));
        imgRight.setOnTouchListener((v, event) -> handleTouch(event, false, progress));
    }

    protected abstract void setupLevel();

    private void createPreviewDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.preview_dialog);

        LinearLayout dialogFon = dialog.findViewById(R.id.dialog_fon);
        if (dialogFon != null && previewBackgroundRes != 0) {
            dialogFon.setBackgroundResource(previewBackgroundRes);
        }

        ImageView previewImg = dialog.findViewById(R.id.preview_img);
        TextView description = dialog.findViewById(R.id.text_description);

        if (previewImg != null && previewImageRes != 0) {
            previewImg.setImageResource(previewImageRes);
        }

        if (description != null) {
            description.setText(levelDescription);
        }

        dialog.findViewById(R.id.button_continue).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.button_close).setOnClickListener(v -> {
            startActivity(new Intent(this, GameLevels.class));
            finish();
        });

        dialog.show();
    }

    private void createEndDialog() {
        dialogEnd = new Dialog(this);
        dialogEnd.setContentView(R.layout.dialog_end);
        Objects.requireNonNull(dialogEnd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        TextView endText = dialogEnd.findViewById(R.id.text_description_end);
        if (endText != null) {
            endText.setText(levelEndText);
        }

        dialogEnd.findViewById(R.id.button_continue).setOnClickListener(v -> {
            startActivity(new Intent(this, GameLevels.class));
            finish();
        });

        dialogEnd.findViewById(R.id.button_close).setOnClickListener(v -> {
            startActivity(new Intent(this, GameLevels.class));
            finish();
        });

    }

    protected boolean handleTouch(MotionEvent event, boolean isLeft, int[] progress) {
        ImageView current = isLeft ? imgLeft : imgRight;
        ImageView other = isLeft ? imgRight : imgLeft;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            other.setEnabled(false);

            boolean isCorrect;
            if (values != null) {
                int leftValue = values[numLeft];
                int rightValue = values[numRight];
                isCorrect = isLeft ? (leftValue > rightValue) : (leftValue < rightValue);
            } else {
                isCorrect = isLeft ? (numLeft > numRight) : (numLeft < numRight);
            }

            current.setImageResource(isCorrect ? R.drawable.img_true : R.drawable.img_false);
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            boolean isCorrect;
            if (values != null) {
                int leftValue = values[numLeft];
                int rightValue = values[numRight];
                isCorrect = isLeft ? (leftValue > rightValue) : (leftValue < rightValue);
            } else {
                isCorrect = isLeft ? (numLeft > numRight) : (numLeft < numRight);
            }

            if (isCorrect) {
                count = Math.min(count + 1, 20);
            } else {
                if (count > 0) {
                    count = (count == 1) ? 0 : count - 2;
                }
            }

            updateProgress(progress);

            if (count == 20) {
                GameLevels.unlockNextLevel(this, getCurrentLevelNumber());
                dialogEnd.show();
            } else {
                generateNewPair();
                other.setEnabled(true);
            }
        }
        return true;
    }

    protected void generateNewPair() {
        do {
            numLeft = random.nextInt(images.length);
            numRight = random.nextInt(images.length);
        }
        while (numLeft == numRight || getValue(numLeft) == getValue(numRight));

        imgLeft.setImageResource(images[numLeft]);
        imgRight.setImageResource(images[numRight]);
        imgLeft.startAnimation(animation);
        imgRight.startAnimation(animation);

        if (examples != null && texts == null) {
            textLeft.setText(examples[numLeft]);
            textRight.setText(examples[numRight]);
        } else if (texts != null) {
            textLeft.setText(texts[numLeft]);
            textRight.setText(texts[numRight]);
        }
    }

    private Object getValue(int index) {
        if (values != null) {
            return values[index];
        } else {
            return index;
        }
    }

    protected void updateProgress(int[] progress) {
        for (int id : progress) {
            findViewById(id).setBackgroundResource(R.drawable.style_points);
        }
        for (int i = 0; i < count && i < progress.length; i++) {
            findViewById(progress[i]).setBackgroundResource(R.drawable.style_points_green);
        }
    }

    protected int getCurrentLevelNumber() {
        return 1;
    }

}