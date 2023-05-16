package khs.mjc.project;

import java.util.Calendar;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class Calendar_ extends AppCompatActivity {

    private TextView monthTextView;
    private CalendarView calendarView;
    TextView monthname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_calender_frame);

        monthTextView = findViewById(R.id.mon_text);

        calendarView = findViewById(R.id.calendarView);
        monthname= findViewById(R.id.mon_nametext);
        // 현재 월 텍스트뷰 초기화
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendarView.getDate());
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        monthTextView.setText(String.valueOf(currentMonth));
        String month_[] = {"january", "february","march",
                "april", "may", "june","july","august", "september",
                "october", "november", "december"}; //월 저장
        // 캘린더뷰에 날짜 선택 리스너 설정
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // 선택된 날짜 정보를 로그에 출력
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                Log.d("MainActivity", "Selected date: " + selectedDate);

                // 선택된 날짜의 월을 텍스트뷰에 표시
                String selectedMonthStr = String.valueOf(month + 1);
                monthTextView.setText(selectedMonthStr);
                monthname.setText(month_[month]);
            }
        });

    }
}
