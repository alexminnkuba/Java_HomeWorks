package com.example.basegame;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayingField extends AppCompatActivity {

    private final int PLAYER_X = 1;
    private final int COMPUTER_O = 2;
    private TextView playerName, computerName;
    private TextView scorePlayer, scoreComputer;
    private ImageView[] boxes = new ImageView[9];
    private int[] boxPositions = new int[9];
    private int activePlayer = PLAYER_X;
    private int totalSelectedBoxes = 0;
    private int playerScore = 0;
    private int computerScore = 0;
    private boolean gameActive = true;
    private int difficulty;

    private final List<int[]> combinations = new ArrayList<>();
    private final Random random = new Random();
    private final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_field);

       String playerName = getIntent().getStringExtra("playerName");
       difficulty = getIntent().getIntExtra("difficulty", 0);

       initCombinations();
       initViews(playerName);

       View.OnClickListener cellClickListener = v -> {
           if(!gameActive){
               return;
           }
           int boxIndex = (int)v.getTag();

           if(boxPositions[boxIndex] == 0 && activePlayer == PLAYER_X) {
               makeMove(boxIndex, PLAYER_X);
           }
       };

        for (int i = 0; i < 9; i++) {
            boxes[i].setTag(i);
            boxes[i].setOnClickListener(cellClickListener);
        }

        resetGame();
    }
    private void initViews(String name) {
        playerName =   findViewById(R.id.playerOneName);
        computerName = findViewById(R.id.playerTwoName);
        scorePlayer = findViewById(R.id.scoreX);
        scoreComputer = findViewById(R.id.scoreY);

        playerName.setText(name);
        computerName.setText(R.string.computer);

        boxes[0] = findViewById(R.id.image1);
        boxes[1] = findViewById(R.id.image2);
        boxes[2] = findViewById(R.id.image3);
        boxes[3] = findViewById(R.id.image4);
        boxes[4] = findViewById(R.id.image5);
        boxes[5] = findViewById(R.id.image6);
        boxes[6] = findViewById(R.id.image7);
        boxes[7] = findViewById(R.id.image8);
        boxes[8] = findViewById(R.id.image9);
    }

    private void initCombinations() {
        combinations.add(new int[]{0,1,2});
        combinations.add(new int[]{3,4,5});
        combinations.add(new int[]{6,7,8});
        combinations.add(new int[]{0,3,6});
        combinations.add(new int[]{1,4,7});
        combinations.add(new int[]{2,5,8});
        combinations.add(new int[]{0,4,8});
        combinations.add(new int[]{2,4,6});
    }

    private void makeMove(int position, int player) {
        boxPositions[position] = player;
        totalSelectedBoxes++;
        ImageView currentBox = boxes[position];

        currentBox.setBackgroundResource(R.drawable.white_box);

        if (player == PLAYER_X) {
           currentBox.setImageResource(R.drawable.ximage);
        } else {
           currentBox.setImageResource(R.drawable.oimage);
        }

        currentBox.setScaleType(ImageView.ScaleType.CENTER);
        animateSymbolAppearance(currentBox);

        if(checkWinner(player)){
            handleWin(player);
        } else if(totalSelectedBoxes == 9){
            handleDraw();
        } else {
            activePlayer = (player == PLAYER_X) ? COMPUTER_O : PLAYER_X;
            updatePlayer();
        }

        if(activePlayer == COMPUTER_O){
            gameActive = false;
            handler.postDelayed(this :: computerMove, 700);
        }
    }

    private void computerMove(){
        int move = (difficulty == 0) ? getRandomeMove() : getBestMove();
        if(move != 1){
            makeMove(move, COMPUTER_O);
        }
        gameActive = true;
    }

    private boolean checkWinner(int player) {
        for(int[] combo : combinations){
            if(boxPositions[combo[0]] == player &&
               boxPositions[combo[1]] == player &&
               boxPositions[combo[2]] == player){
                return  true;
            }
        }
        return false;
    }

    private void handleWin(int player) {
        gameActive = false;

        if(player == PLAYER_X){
            playerScore++;
            scorePlayer.setText(String.valueOf(playerScore));
            showResultDialog(getString(R.string.you_won));
        } else {
            computerScore++;
            scoreComputer.setText(String.valueOf(computerScore));
            showResultDialog(getString(R.string.computer_won));
        }
    }

    private void handleDraw() {
        gameActive = false;
        showResultDialog(getString(R.string.draw));
    }

    private void showResultDialog(String message) {
        ResultDialog dialog = new ResultDialog(this, message, this);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void updatePlayer() {
        LinearLayout playerLayout = findViewById(R.id.playerOneLayout);
        LinearLayout computerLayout = findViewById(R.id.playerTwoLayout);

        if(activePlayer == PLAYER_X){
            playerLayout.setBackgroundResource(R.drawable.black_border);
            computerLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            computerLayout.setBackgroundResource(R.drawable.black_border);
            playerLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private int getRandomeMove() {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if(boxPositions[i] == 0){
                lists.add(i);
            }
        }
        if(lists.isEmpty()){
            return -1;
        } else {
           return lists.get(random.nextInt(lists.size()));
        }
    }

    private int getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int i = 0; i < 9; i++) {
            if(boxPositions[i] == 0){
                boxPositions[i] = COMPUTER_O;
                int score = minimax(0, false);
                boxPositions[i] = 0;
                if(score > bestScore){
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    private int minimax(int depth, boolean isMax) {
        if(checkWinner(COMPUTER_O)){
            return 10 - depth;
        }
        if(checkWinner(PLAYER_X)){
            return depth - 10;
        }
        if(isBoardFull()){
            return 0;
        }

        if(isMax){
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if(boxPositions[i] == 0){
                    boxPositions[i] = COMPUTER_O;
                    best = Math.max(best, minimax(depth + 1, false));
                    boxPositions[i] = 0;
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if(boxPositions[i] == 0){
                    boxPositions[i] = PLAYER_X;
                    best = Math.min(best, minimax(depth + 1, true));
                    boxPositions[i] = 0;
                }
            }
            return best;
        }
    }

    private boolean isBoardFull() {
        for(int pos : boxPositions){
            if(pos == 0){
                return  false;
            }
        }
        return true;
    }

    public void resetGame() {
        restartMath();
    }

    private void restartMath() {
        boxPositions = new int[9];
        totalSelectedBoxes = 0;
        activePlayer = PLAYER_X;
        gameActive = true;

        for (ImageView box : boxes) {
            box.setBackgroundResource(R.drawable.white_box);
            box.setImageResource(0);
            box.setScaleX(1f);
            box.setScaleY(1f);
            box.setAlpha(1f);
            box.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        updatePlayer();
    }

    private void animateSymbolAppearance(ImageView imageView) {
        imageView.setScaleX(0.3f);
        imageView.setScaleY(0.3f);
        imageView.setAlpha(0f);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.3f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.3f, 1.0f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1.0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, alpha);
        animatorSet.setDuration(280);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }
}