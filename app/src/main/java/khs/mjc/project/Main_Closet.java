package khs.mjc.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Main_Closet extends AppCompatActivity {
    String[] name = {"도서관", "학교", "달", "모던시티", "문나잇", "블루조아하우스", "크리스마스", "자취방", "아지트"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_closet);
        GridView gv = findViewById(R.id.gridview);
        TextView tv2 = findViewById(R.id.title);


        Main_Closet.MyGridAdapter gAdapter = new Main_Closet.MyGridAdapter(this);
        gv.setAdapter(gAdapter);
        tv2.setText("Closet");
    }
    public  class  MyGridAdapter extends BaseAdapter {
        Context context;

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(new ViewGroup.LayoutParams(400, 300));

            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.shape2);
            tv.setText(name[position]);
            tv.setPadding(5, 5, 5, 5);
            return tv;
        }

        public MyGridAdapter(Context context) {
            this.context = context;
        }
    }
}
