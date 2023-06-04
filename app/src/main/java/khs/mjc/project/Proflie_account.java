package khs.mjc.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Proflie_account extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_account);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        TextView code=  findViewById(R.id.code);
        TextView name = findViewById(R.id.name);
        TextView date = findViewById(R.id.date);

        code.setText(Integer.toString(sharedPreferences.getInt("code", 0)));
        name.setText(sharedPreferences.getString("name", "길동이"));
        date.setText(sharedPreferences.getString("date", "0000년00월00일"));
    }
}
