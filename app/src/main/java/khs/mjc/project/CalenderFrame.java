package khs.mjc.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalenderFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalenderFrame extends Fragment {
    public String fname = null;
    public String str = null;
    private TextView monthTextView;
    private CalendarView calendarView;
    String selectedDate;
    public Button cha_Btn, del_Btn, save_Btn;
    public TextView textView2, textView3, textView4, textView5;
    public EditText contextEditText;
    TextView monthname;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public CalenderFrame() {
        // Required empty public constructor
    }

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
        View v = inflater.inflate(R.layout.fragment_calender_frame, container, false);
        monthTextView = v.findViewById(R.id.mon_text);
        calendarView = v.findViewById(R.id.calendarView);
        monthname = v.findViewById(R.id.mon_nametext);
        save_Btn = v.findViewById(R.id.save_Btn);
        del_Btn = v.findViewById(R.id.del_Btn);
        cha_Btn = v.findViewById(R.id.cha_Btn);
        textView2 = v.findViewById(R.id.textView2);
        textView3=v.findViewById(R.id.textView3);
        textView4=v.findViewById(R.id.textView4);
        textView5=v.findViewById(R.id.textView5);
        contextEditText = v.findViewById(R.id.contextEditText);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference =database.getReference(Integer.toString(sharedPreferences.getInt("code", 0)));

        textView5.setText(sharedPreferences.getString("name", "몰루"));

        if(sharedPreferences.getString("gender", "mola").equals("남")) {
            databaseReference.child("여").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name= snapshot.child("name").getValue(String.class);
                    textView4.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            databaseReference.child("남").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name= snapshot.child("name").getValue(String.class);
                    textView4.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        //로그인 식별코드를 받아옴
        Intent intent = getActivity().getIntent();
        String name = intent.getStringExtra("userName");
        final String userID = intent.getStringExtra("userID");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendarView.getDate());
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        monthTextView.setText(String.valueOf(currentMonth));
        String month_[] = {"january", "february", "march",
                "april", "may", "june", "july", "august", "september",
                "october", "november", "december"};

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                Log.d("MainActivity", "Selected date: " + selectedDate);

                String selectedMonthStr = String.valueOf(month + 1);
                monthTextView.setText(selectedMonthStr);
                monthname.setText(month_[month]);

                save_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setText("");
                checkDay(year, month, dayOfMonth, userID);

                if(sharedPreferences.getString("gender", "mola").equals("남")){
                    databaseReference.child("여").child("calender").child(selectedDate).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue()!="null") {
                                String diary = snapshot.getValue(String.class);
                                textView3.setText(diary);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    databaseReference.child("남").child("calender").child(selectedDate).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue()!="null") {
                                String diary = snapshot.getValue(String.class);
                                textView3.setText(diary);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);
                str = contextEditText.getText().toString();
                textView2.setText(str);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);

                databaseReference.child(sharedPreferences.getString("gender", "mola")).child("calender").child(selectedDate).setValue(textView2.getText().toString());
                }
        });
//이후 버전

        return v;
    }

    public void checkDay(int cYear, int cMonth, int cDay, String userID) {
        fname = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt";
        FileInputStream fis = null;

        try {
            fis = getActivity().openFileInput(fname);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str = new String(fileData);

            contextEditText.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(str);

            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);

            cha_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText(str);

                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    textView2.setText(contextEditText.getText());
                }
            });

            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText("");
                    contextEditText.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    removeDiary(fname);
                }
            });

            if (textView2.getText() == null) {
                textView2.setVisibility(View.INVISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay) {
        FileOutputStream fos = null;

        try {
            fos = getActivity().openFileOutput(readDay, Context.MODE_PRIVATE);
            String content = "";
            fos.write(content.getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDiary(String readDay) {
        FileOutputStream fos = null;

        try {
            fos = getActivity().openFileOutput(readDay, Context.MODE_PRIVATE);
            String content = contextEditText.getText().toString();
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
