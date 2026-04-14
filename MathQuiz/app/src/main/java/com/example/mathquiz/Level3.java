package com.example.mathquiz;

public class Level3 extends BaseLevelActivity{
    @Override
    protected void setupLevel() {
        images = array.images3;
        texts = array.text3;
        levelTitle = getString(R.string.level_3);
        levelDescription = getString(R.string.level_three);
        levelEndText = getString(R.string.level_three_end);
        backgroundRes = R.drawable.level_3;
        previewImageRes = R.drawable.preview_img_3;
        previewBackgroundRes = R.drawable.preview_background_3;
    }
    @Override
    protected int getCurrentLevelNumber() {
        return 3;
    }
}
