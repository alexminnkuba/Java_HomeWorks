package com.example.mathquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.mathquiz.Level1;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameLevels extends AppCompatActivity {
    private ProgressManager progressManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        progressManager = new ProgressManager(this);

        Button btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameLevels.this, MainActivity.class));
                finish();
            }
        });

        setupLevelButton(R.id.textView1, 1, Level1.class);
        setupLevelButton(R.id.textView2, 2, Level2.class);
        setupLevelButton(R.id.textView3, 3, Level3.class);
        setupLevelButton(R.id.textView4, 4, Level4.class);
        setupLevelButton(R.id.textView5, 5, Level5.class);
    }

    private void setupLevelButton(int textViewId, int levelNumber, Class<?> levelClass) {
        TextView tv = findViewById(textViewId);

        boolean isUnlocked = progressManager.isLevelUnlocked(levelNumber);

        if (isUnlocked) {
            tv.setAlpha(1.0f);
            tv.setClickable(true);
            tv.setOnClickListener(v -> {
                Intent intent = new Intent(GameLevels.this, levelClass);
                startActivity(intent);
            });
        } else {
            tv.setAlpha(0.4f);
            tv.setClickable(false);
        }
    }

    public static void unlockNextLevel(Context context, int currentLevel) {
        ProgressManager pm = new ProgressManager(context);
        pm.unlockLevel(currentLevel + 1);
    }
}