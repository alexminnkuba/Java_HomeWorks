package com.example.mathquiz;

public class Level5 extends BaseLevelActivity{
    @Override
    protected void setupLevel() {
        images = array.images5;
        examples = array.examples5;
        values = array.products5;
        texts = null;

        levelTitle = getString(R.string.level_5);
        levelDescription = getString(R.string.level_five);
        levelEndText = getString(R.string.level_five_end);

        backgroundRes = R.drawable.level_5;
        previewImageRes = R.drawable.preview_background_5;
        previewBackgroundRes = R.drawable.prev_lev_background_one;
    }
}
