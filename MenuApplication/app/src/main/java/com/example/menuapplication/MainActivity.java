package com.example.menuapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar materialToolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(materialToolbar);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, materialToolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              FragmentManager fragment = getSupportFragmentManager();
              int itemId = item.getItemId();

                if (itemId == R.id.first_page) {
                    fragment.beginTransaction().replace(R.id.frame_layout, new FirstPage()).commit();
                } else if (itemId == R.id.second_page) {
                    fragment.beginTransaction().replace(R.id.frame_layout, new SecondPage()).commit();
                } else if (itemId == R.id.third_page) {
                    fragment.beginTransaction().replace(R.id.frame_layout, new ThirdPage()).commit();
                } else if (itemId == R.id.fourth_page) {
                    fragment.beginTransaction().replace(R.id.frame_layout, new FourthPage()).commit();
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FirstPage()).commit();
            navigationView.setCheckedItem(R.id.first_page);
        }
    }
}