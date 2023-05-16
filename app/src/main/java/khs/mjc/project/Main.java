package khs.mjc.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationBarView;


public class Main extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();
    MainFrame mainFrame = new MainFrame();
    QuestionFrame questionFrame = new QuestionFrame();
    BucketFrame bucketFrame = new BucketFrame();
    CalenderFrame calenderFrame = new CalenderFrame();
    ProfileFrame profileFrame =new ProfileFrame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_ac, mainFrame).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_ac, mainFrame).commit();
                        return true;
                    case R.id.messages:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_ac, questionFrame).commit();
                        return true;
                    case R.id.calendar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_ac, bucketFrame).commit();
                        return true;
                    case R.id.notifications:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_ac, calenderFrame).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_ac, profileFrame).commit();
                        return true;
                }return false;
            }
        });

        ImageView closet = findViewById(R.id.shop);
        ImageView rb=findViewById(R.id.main_rb);
        ImageView al=findViewById(R.id.main_al);
        ImageView p=findViewById(R.id.main_p);
        ImageView gps=findViewById(R.id.main_gps);


        closet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,Main_Closet.class);
                startActivity(intent);
            }
        });
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,Main_Shop.class);
                startActivity(intent);
            }
        });
        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this,Main_alarm.class);
                startActivity(intent);
            }
        });
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Main_gps.class);
                startActivity(intent);
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Gallery.class);
                startActivity(intent);
            }
        });
    }
}