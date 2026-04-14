package com.example.mathquiz;

public class Level2 extends BaseLevelActivity{
    @Override
    protected void setupLevel() {
        images = array.images2;
        texts = array.text2;
        levelTitle = getString(R.string.level_2);
        levelEndText = getString(R.string.level_two_end);
        levelDescription = getString(R.string.level_two);
        backgroundRes = R.drawable.prev_lev_background_one;
        previewImageRes = R.drawable.number_lev_two;
        previewBackgroundRes = R.drawable.prev_lev_background_one;
    }
    @Override
    protected int getCurrentLevelNumber() {
        return 2;
    }
}
