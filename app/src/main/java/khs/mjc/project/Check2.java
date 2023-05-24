package khs.mjc.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Check2 extends AppCompatActivity {
    Boolean check = false;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference =database.getReference("connectCode");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check2);

        Button connect = findViewById(R.id.connect);
        TextView code = findViewById(R.id.connectCode);
        EditText codeText = findViewById(R.id.opponentConnectCode);

        Intent inIntent = getIntent();
        int connectcode = inIntent.getIntExtra("code", 0);
        code.setText(String.valueOf(connectcode));

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot codeSnapshot : snapshot.getChildren()) {
                            Integer code = codeSnapshot.getValue(int.class);
                            if (code.equals(Integer.parseInt(codeText.getText().toString()))) {
                                check = true;
                                Intent intent = new Intent(Check2.this, Main.class);
                                startActivity(intent);
                                break;
                            }
                        }
                        if(check==false) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Check2.this);
                            AlertDialog dialog = builder.setMessage("잘못된 코드입니다").setNegativeButton("확인", null).create();
                            dialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}
