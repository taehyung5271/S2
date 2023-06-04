package khs.mjc.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;

//import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private static final int GET_GALLERY_IMAGE = 200;
    private Context context;
    private List<String> p_bucketlist;
    private HashMap<String, List<String>> c_galleryBtn;
    private int RESULT_OK;

    public ExpandableListViewAdapter(Context context, List<String> bucketlist, HashMap<String, List<String>> galleryBtn) {
        this.context = context;
        this.p_bucketlist = bucketlist;
        this.c_galleryBtn = galleryBtn;
    }

    @Override
    public int getGroupCount() {
        return this.p_bucketlist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.c_galleryBtn.get(this.p_bucketlist.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.p_bucketlist.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.c_galleryBtn.get(this.p_bucketlist.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String bucketTitle = (String) getGroup(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.bucket_gallery, null);
        }
        TextView bucketlist = view.findViewById(R.id.list);
        bucketlist.setTypeface(null, Typeface.BOLD);
        bucketlist.setText(bucketTitle);

        EditText editText = view.findViewById(R.id.dlgEdt1); // 다이얼로그 프래그먼트의 EditText

//        ((AlertDialog) getDialog()).setPositiveButton("등록하기", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String editTextData = editText.getText().toString();
//                addParentItem(editTextData);
//            }
//        });

        String groupName = p_bucketlist.get(i);
        bucketlist.setText(groupName);


        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String galleryTitle = (String) getChild(i, i1);


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_listview, null);
        }

        ImageButton galleryButton = view.findViewById(R.id.imgBtn);
        galleryButton.setImageResource(R.drawable.plus);

        return view;
    }



/*
    public void addParentItem(String item) {
        p_bucketlist.add(item);
        notifyDataSetChanged(); // 데이터 변경을 알림
    }
*/

    @Override
    public boolean isChildSelectable ( int i, int i1){
        return true;
    }
}
