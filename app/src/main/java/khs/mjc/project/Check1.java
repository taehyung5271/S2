package khs.mjc.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Check1 extends AppCompatActivity {
    Boolean check;

    SharedPreferences sharedPreferences;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference =database.getReference();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check1);

        int connectcode;
        TextView code = findViewById(R.id.connectCode);
        Button connect = findViewById(R.id.connect);

        connectcode = (int) (Math.random() * 10000000);
        code.setText(String.valueOf(connectcode));
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        check = sharedPreferences.getBoolean("isMainScreenShown", false);

        checkAppStatus();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("connectCode").push().setValue(connectcode);
                Intent intent = new Intent(Check1.this, Check2.class);
                intent.putExtra("code", connectcode);
                startActivity(intent);
            }
        });
    }
    private void checkAppStatus() {
        if (check) {
            // 메인 화면으로 이동
            startActivity(new Intent(Check1.this, Main.class));
            finish();
        }
    }

}
