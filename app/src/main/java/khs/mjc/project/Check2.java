package khs.mjc.project;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
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

import java.util.Calendar;

public class Check2 extends AppCompatActivity {
    int connectcode;
    DatePickerDialog datePickerDialog;
    String conn;
    String gender;
    Boolean check = false;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference =database.getReference();
    Button datebtn;
    TextView dateText;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check2);

        Button connect = findViewById(R.id.connect);
        TextView code = findViewById(R.id.connectCode);
        EditText codeText = findViewById(R.id.opponentConnectCode);
        EditText name = findViewById(R.id.myNname);
        datebtn=findViewById(R.id.dateBtn);
        dateText=findViewById(R.id.startLoveDay);

        RadioGroup genderrg = findViewById(R.id.radio_group_gender);

        Intent inIntent = getIntent();
        connectcode = inIntent.getIntExtra("code", 0);
        code.setText(String.valueOf(connectcode));

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int pyear = calendar.get(Calendar.YEAR);
                int pmonth = calendar.get(Calendar.MONTH);
                int pday = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Check2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;

                        String date = year+ "년" + month + "월" + dayOfMonth + "일";
                        dateText.setText(date);
                    }
                }, pyear, pmonth, pday);
                datePickerDialog.show();
            }
        });

        genderrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_male:
                        gender="남";
                        break;
                    case R.id.radio_button_female:
                        gender="여";
                        break;
                }
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (codeText.getText().toString().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Check2.this);
                    AlertDialog dialog = builder.setMessage("코드를 입력하세요").setNegativeButton("확인", null).create();
                    dialog.show();
                }
                else if(name.getText().toString().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Check2.this);
                    AlertDialog dialog = builder.setMessage("이름을 입력하세요").setNegativeButton("확인", null).create();
                    dialog.show();
                }
                else if(dateText.getText().toString().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Check2.this);
                    AlertDialog dialog = builder.setMessage("연애시작 날짜를 입력하세요").setNegativeButton("확인", null).create();
                    dialog.show();
                }
                else if(gender.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Check2.this);
                    AlertDialog dialog = builder.setMessage("성별을 선택하세요").setNegativeButton("확인", null).create();
                    dialog.show();
                }
                else {
                    databaseReference.child("connectCode").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot codeSnapshot : snapshot.getChildren()) {
                                Integer code = codeSnapshot.getValue(int.class);
                                if (code.equals(Integer.parseInt(codeText.getText().toString()))) {
                                    check = true;
                                    conn=code.toString();
                                    adduser(name.getText().toString(), dateText.getText().toString(), gender);
                                    editor.putBoolean("isMainScreenShown", true);
                                    editor.putString("gender", gender);
                                    editor.putInt("code", code);
                                    editor.putString("name", name.getText().toString());
                                    editor.putString("date", dateText.getText().toString());

                                    editor.putInt("day", 1);
                                    editor.apply();

                                    Intent intent = new Intent(Check2.this, Main.class);
                                    startActivity(intent);
                                    break;
                                }
                            }
                            if (check == false) {
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
            }
        });

    }
    public void adduser(String name, String date, String gender){
        UserInfo user = new UserInfo(name, date, gender);
        databaseReference.child(conn).child(gender).setValue(user);
    }
}
