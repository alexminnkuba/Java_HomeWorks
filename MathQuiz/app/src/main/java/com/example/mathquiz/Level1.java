package com.example.mathquiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog, dialogEnd;
    public int numLeft, numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView textLevels = findViewById(R.id.text_level);
        ImageView imgLeft = findViewById(R.id.img_left);
        ImageView imgRight = findViewById(R.id.img_right);
        TextView textLeft = findViewById(R.id.text_left);
        TextView textRight = findViewById(R.id.text_right);

        textLevels.setText(R.string.level_1);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.preview_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        Button btnContinue = dialog.findViewById(R.id.button_continue);
        btnContinue.setOnClickListener(v -> dialog.dismiss());

        TextView btnClose = dialog.findViewById(R.id.button_close);
        btnClose.setOnClickListener(v -> {
            startActivity(new Intent(Level1.this, GameLevels.class));
            dialog.dismiss();
        });

        dialog.show();

        dialogEnd = new Dialog(this);
        dialogEnd.setContentView(R.layout.dialog_end);
        Objects.requireNonNull(dialogEnd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        Button btnContinue2 = dialogEnd.findViewById(R.id.button_continue);
        btnContinue2.setOnClickListener(v -> {
            startActivity(new Intent(Level1.this, GameLevels.class));
            dialogEnd.dismiss();
        });

        TextView btnClose2 = dialogEnd.findViewById(R.id.button_close);
        btnClose2.setOnClickListener(v -> {
            startActivity(new Intent(Level1.this, GameLevels.class));
            dialogEnd.dismiss();
        });

        Button btnBack = findViewById(R.id.button_back_level);
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(Level1.this, GameLevels.class));
        });

        numLeft = random.nextInt(10);
        imgLeft.setImageResource(array.images1[numLeft]);
        textLeft.setText(array.text1[numLeft]);

        numRight = random.nextInt(10);
        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }
        imgRight.setImageResource(array.images1[numRight]);
        textRight.setText(array.text1[numRight]);

        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };

        imgLeft.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                imgRight.setEnabled(false);
                if (numLeft > numRight) {
                    imgLeft.setImageResource(R.drawable.img_true);
                } else {
                    imgLeft.setImageResource(R.drawable.img_false);
                }
            }
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (numLeft > numRight) {
                    count = Math.min(count + 1, 20);
                } else {
                    if (count > 0) {
                        count = (count == 1) ? 0 : count - 2;
                    }
                }

                updateProgress(progress);

                if (count == 20) {
                    dialogEnd.show();
                } else {
                    numLeft = random.nextInt(10);
                    imgLeft.setImageResource(array.images1[numLeft]);
                    textLeft.setText(array.text1[numLeft]);
                    imgLeft.startAnimation(animation);

                    numRight = random.nextInt(10);
                    while (numLeft == numRight) numRight = random.nextInt(10);
                    imgRight.setImageResource(array.images1[numRight]);
                    textRight.setText(array.text1[numRight]);
                    imgRight.startAnimation(animation);

                    imgRight.setEnabled(true);
                }
            }
            return true;
        });

        imgRight.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                imgLeft.setEnabled(false);
                if (numLeft < numRight) {
                    imgRight.setImageResource(R.drawable.img_true);
                } else {
                    imgRight.setImageResource(R.drawable.img_false);
                }
            }
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (numLeft < numRight) {
                    count = Math.min(count + 1, 20);
                } else {
                    if (count > 0) {
                        count = (count == 1) ? 0 : count - 2;
                    }
                }

                updateProgress(progress);

                if (count == 20) {
                    dialogEnd.show();
                } else {
                    numLeft = random.nextInt(10);
                    imgLeft.setImageResource(array.images1[numLeft]);
                    textLeft.setText(array.text1[numLeft]);
                    imgLeft.startAnimation(animation);

                    numRight = random.nextInt(10);
                    while (numLeft == numRight) numRight = random.nextInt(10);
                    imgRight.setImageResource(array.images1[numRight]);
                    textRight.setText(array.text1[numRight]);
                    imgRight.startAnimation(animation);

                    imgLeft.setEnabled(true);
                }
            }
            return true;
        });
    }

    private void updateProgress(int[] progress) {
        for (int id : progress) {
            findViewById(id).setBackgroundResource(R.drawable.style_points);
        }
        for (int i = 0; i < count; i++) {
            findViewById(progress[i]).setBackgroundResource(R.drawable.style_points_green);
        }
    }
}