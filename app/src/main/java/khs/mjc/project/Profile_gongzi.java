package khs.mjc.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profile_gongzi extends AppCompatActivity {
    TextView title;
    TextView textView13;
    TextView textView;
    TextView  textView14;
    TextView textView15;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_gongzi);

        title=(TextView)findViewById(R.id.title);
        textView13=(TextView)findViewById(R.id.textView13);
        textView=(TextView) findViewById(R.id.textView);
        textView14=(TextView)findViewById(R.id.textView14);
        textView15=(TextView)findViewById(R.id.textView15);

        title.setText("Couple");
        textView13.setText("04.01");
        textView.setText("먼저 본 사람이 임자(만우절 이벤트)");
        textView14.setText("03.14");
        textView15.setText("화이트데이 기념 달토끼 이모티콘 출시");

    }
}