package com.example.mathquiz;

import android.content.Context;
import android.content.SharedPreferences;

public class ProgressManager {
    private static final String PREF_NAME = "GameProgress";
    private static final String KEY_MAX_LEVEL = "max_unlocked_level";

    private final SharedPreferences preferences;

    public ProgressManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void unlockLevel(int level) {
        int currentMax = getMaxUnlockedLevel();
        if (level > currentMax) {
            preferences.edit().putInt(KEY_MAX_LEVEL, level).apply();
        }
    }

    public int getMaxUnlockedLevel() {
        return preferences.getInt(KEY_MAX_LEVEL, 1);
    }

    public boolean isLevelUnlocked(int level) {
        return level <= getMaxUnlockedLevel();
    }
}
