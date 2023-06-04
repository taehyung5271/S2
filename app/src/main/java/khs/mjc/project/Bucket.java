package khs.mjc.project;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

//import androidx.appcompat.app.AlertDialog;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bucket extends AppCompatActivity implements DialogFragmentListener{
    Button addlistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bucket_frame);
        addlistBtn=findViewById(R.id.addItemButton);
        addlistBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment dialogfragment=new DialogFragment();
                dialogfragment.setDataPassListener(Bucket.this);
                dialogfragment.show(getSupportFragmentManager(),"dialog");
            }
        });

    }
    @Override
    public void onDataPass(String data){
        //데이터 전달 처리 로직
        FragmentManager fragmentManager=getSupportFragmentManager();
        BucketFrame bucketfragment=(BucketFrame)fragmentManager.findFragmentByTag("bucketFragment");
        if(bucketfragment!=null){
            bucketfragment.onDataPass(data);
        }
    }

}

