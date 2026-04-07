package com.example.calcdiscount;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText amount;
    private Button btnCalculate;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.etAmount);
        btnCalculate = findViewById(R.id.btnCalculate);
        result = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDiscount();
                amount.setText("");
            }
        });
    }

    private void calculateDiscount() {
        String input = amount.getText().toString();

        if(input.isEmpty()){
            result.setText("Введите сумму покупки!");
            return;
        }

            double amount = Double.parseDouble(input);

            if(amount <= 0){
                result.setText("Сумма должна быть больше 0 руб.");
                return;
            }

            double discountPercent = 0;
            String discountText = "";

            if(amount > 1000){
                discountPercent = 5;
                discountText = "5%";
            } else if(amount > 500){
                discountPercent = 3;
                discountText = "3%";
            }

            double discountAmount = amount * (discountPercent / 100);
            double totalAmount = amount - discountAmount;

            @SuppressLint("DefaultLocale")
            String textResult = String.format(
                    "Сумма покупки: %.2f ₽\n" +
                            "Скидка: %s (%.2f ₽)\n" +
                            "Итого к оплате: %.2f ₽",
                    amount, discountText, discountAmount, totalAmount
            );

           result.setText(textResult);
    }
}