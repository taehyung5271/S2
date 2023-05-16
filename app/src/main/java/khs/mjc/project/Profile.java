package khs.mjc.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {
    TextView text_gz;
    TextView text_if;
   // TextView text3;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile_frame);

        title=(TextView)findViewById(R.id.pf_title);
        title.setText("Couple");

        text_gz = (TextView) findViewById(R.id.pf_t);
        text_if=(TextView)findViewById(R.id.pf_t2);
       // text3=(TextView)findViewById(R.id.textView10);

         text_gz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Profile_gongzi.class);
                startActivity(intent);
            }
        });
         text_if.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(Profile.this,Profile_menuui.class);
                 startActivity(intent);
             }
         });
    }
}