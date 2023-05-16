package khs.mjc.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main_alarm extends AppCompatActivity {
    String Time_return(int day, int hour) {
        //서버에서 값 받아왔다고 침 ㅇㅇ server= 서버에서 보낸 시간임 (알림 보낸 '시')
        long now = System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh");
        String getTime=dateFormat.format(date);
        int time=Integer.parseInt(getTime)-hour;
        if(day>1){
            return Integer.toString(day-1)+"일";
        }
        if(time<=0){
            time+=24;

        }
        return Integer.toString(time)+"시간";
    }

    int server_alarm=4; //서버에서 값 받아오기
    String[] alarm=new String[server_alarm];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_alarm);
        ListView list=findViewById(R.id.listView1);
        for(int i=0;i<server_alarm;i++){
            alarm[i]=Time_return(1,5)+"전 서버에서 받아올 이유 이건 나중에 할래";
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alarm);
        list.setAdapter(adapter);
        ImageView close = findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_alarm.this,Main.class);
                startActivity(intent);
            }
        });
    }

}