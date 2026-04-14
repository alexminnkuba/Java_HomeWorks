package com.example.mathquiz;

public class Level1 extends BaseLevelActivity {

    @Override
    protected void setupLevel() {
        images = array.images1;
        texts = array.text1;
        levelTitle = getString(R.string.level_1);
        levelEndText = getString(R.string.level_one_end);
        levelDescription = getString(R.string.level_one);
        backgroundRes = R.drawable.prev_lev_background_one;
        previewImageRes = R.drawable.number_lev_one;
        previewBackgroundRes = R.drawable.prev_lev_background_one;
    }
    @Override
    protected int getCurrentLevelNumber() {
        return 1;
    }
}