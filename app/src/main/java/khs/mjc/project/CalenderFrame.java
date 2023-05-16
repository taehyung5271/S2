package khs.mjc.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalenderFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalenderFrame extends Fragment {
    private TextView monthTextView;
    private CalendarView calendarView;
    TextView monthname;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalenderFrame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalenderFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static CalenderFrame newInstance(String param1, String param2) {
        CalenderFrame fragment = new CalenderFrame();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calender_frame, container, false);
        monthTextView = v.findViewById(R.id.mon_text);
        calendarView = v.findViewById(R.id.calendarView);
        monthname= v.findViewById(R.id.mon_nametext);

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


        return v;
    }
}