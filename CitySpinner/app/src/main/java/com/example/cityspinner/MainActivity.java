package com.example.cityspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerCities;
    private ImageView imageCity;
    private TextView tvCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCities = findViewById(R.id.spinner_cities);
        imageCity = findViewById(R.id.image_city);
        tvCityName = findViewById(R.id.tv_city_name);

        tvCityName.setText("Москва");

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cityName = parent.getItemAtPosition(position).toString();
                int imageRes = getCityImageResource(position);

                imageCity.setImageResource(imageRes);

                String description = getCityDescription(position);
                tvCityName.setText(description);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private String getCityDescription(int position) {
        String[] descriptions = getResources().getStringArray(R.array.description_of_cities);
        return descriptions[position];
    }

    private int getCityImageResource(int position) {
        switch (position) {
            case 0: return R.drawable.moscow;
            case 1: return R.drawable.paris;
            case 2: return R.drawable.newyork;
            case 3: return R.drawable.tokyo;
            case 4: return R.drawable.london;
            case 5: return R.drawable.rome;
            case 6: return R.drawable.sydney;
            case 7: return R.drawable.dubai;
            default: return R.drawable.moscow;
        }
    }
}