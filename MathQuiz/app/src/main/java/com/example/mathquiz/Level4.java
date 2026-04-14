package com.example.mathquiz;

public class Level4 extends BaseLevelActivity{
    @Override
    protected void setupLevel() {
        images = array.images4;
        texts = null;
        examples = array.examples4;
        values = array.sums4;

        levelTitle = getString(R.string.level_4);
        levelDescription = getString(R.string.level_four);
        levelEndText = getString(R.string.level_four_end);
        backgroundRes = R.drawable.level_4;
        previewImageRes = R.drawable.preview_img_4;
        previewBackgroundRes = R.drawable.preview_background_4;
    }
    @Override
    protected int getCurrentLevelNumber() {
        return 4;
    }
}
