package khs.mjc.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile_menuui extends AppCompatActivity {
    TextView title;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_m);

        title=(TextView)findViewById(R.id.title);
        text1=(TextView)findViewById(R.id.textView17);
        text2=(TextView)findViewById(R.id.textView18);
        text3=(TextView)findViewById(R.id.textView19);
        text4=(TextView)findViewById(R.id.textView20);

        title.setText("S2에 문의하기");
        text1.setText("커플 연결 복구(재연결)");
        text2.setText("버그 및 신고");
        text3.setText("S2골드 구매문제");
        text4.setText("기타");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}