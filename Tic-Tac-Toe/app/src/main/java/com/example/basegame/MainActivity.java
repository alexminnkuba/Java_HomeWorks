package com.example.basegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText playerNameEdit = findViewById(R.id.playerName);
        Spinner difficultySpinner = findViewById(R.id.difficultySpinner);
        Button startGameButton = findViewById(R.id.startGameButton);

        String[] difficulties = {"Легкий", "Сложный"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = playerNameEdit.getText().toString();
                if(playerName.isEmpty()){
                    Toast.makeText(MainActivity.this, "Пожалуйста, введите ваше имя!", Toast.LENGTH_SHORT).show();
                } else{
                    int difficulty = difficultySpinner.getSelectedItemPosition();

                    Intent intent = new Intent(MainActivity.this, PlayingField.class);
                    intent.putExtra("playerName", playerName);
                    intent.putExtra("difficulty", difficulty);
                    startActivity(intent);
                }
            }
        });
    }
}