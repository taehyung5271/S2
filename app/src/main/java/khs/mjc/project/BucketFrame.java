package khs.mjc.project;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BucketFrame extends Fragment implements DialogFragmentListener {
    Button addItemBtn;
    EditText editText;
    View dialogView;
    ExpandableListViewAdapter listViewAdapter;
    ExpandableListView expandableListView;
    ArrayList p_bucketlist=new ArrayList<>();
    //List<String> childList;
    HashMap<String, List<String>> c_galleryBtn;
    ImageButton imageButton;
    @Override
    public void onDataPass(String data){
        //전달받은 데이터를 부모 아이템으로 처리
        //expandablelistview의 부모 아이템에 데이터 추가

        listViewAdapter = new ExpandableListViewAdapter(getActivity().getApplicationContext(), p_bucketlist, c_galleryBtn);
        p_bucketlist.add(data);
        listViewAdapter.notifyDataSetChanged();

        showChildList();
    }





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BucketFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static BucketFrame newInstance(String param1, String param2) {
        BucketFrame fragment = new BucketFrame();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bucket_frame, container, false);
        View view = inflater.inflate(R.layout.child_listview, container, false);


        addItemBtn = v.findViewById(R.id.addItemButton);
        ImageButton imageButton = view.findViewById(R.id.imgBtn);

        expandableListView = v.findViewById(R.id.eListView);
        listViewAdapter = new ExpandableListViewAdapter(getActivity().getApplicationContext(), p_bucketlist, c_galleryBtn);
        expandableListView.setAdapter(listViewAdapter);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment=new DialogFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "dialog");
            }
        });


        ImageView gallery = v.findViewById(R.id.bucket_icon);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Gallery.class);
                startActivity(intent);
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;

            @Override
            public void onGroupExpand(int i) {
                if (lastExpandedPosition != -1 && i != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                //이미지뷰 + 버튼 클릭시 갤러리에서 사진 불러오는 코드 작성

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doTakeMultiAlbumAction();
                    }
                });
                return true;
            }
        });

        //     showChildList();

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void showChildList() {
        //리스트가 추가되면 +버튼 생성
        HashMap<String, List<ImageButton>> c_galleryBtn = new HashMap<>();
        ImageButton plusbtn = getView().findViewById(R.id.imgBtn);
        List<ImageButton> galleryBtnList = new ArrayList<>();
        galleryBtnList.add(plusbtn);
        c_galleryBtn.put("p_bucketlist", galleryBtnList);

    }

    //갤러리 사진 저장
    public void doTakeMultiAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);  //여러장 선택 가능
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                ArrayList<Uri> uriList = new ArrayList<>();

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) { //어떤 이미지도 선택하지 않은 경우
                            Toast.makeText(getActivity().getApplicationContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
                        } else {   //이미지를 하나라도 선택한 경우
                            if (data.getClipData() == null) {  //이미지를 하나만 선택한 경우
                                Log.e("single choice: ", String.valueOf(data.getData()));
                                Uri imageUri = data.getData();
                                uriList.add(imageUri);
                            } else {  //이미지를 여러장 선택한 경우
                                ClipData clipData = data.getClipData();
                                Log.e("clipData", String.valueOf(clipData.getItemCount()));

                                if (clipData.getItemCount() > 12) {  //선택한 이미지가 12장 이상인 경우
                                    Toast.makeText(getActivity().getApplicationContext(), "사진은 12장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
                                } else {  //선택한 이미지가 1장이상 12장 이하인 경우
                                    // Log.e(TAG,"multiple choice");
                                    for (int i = 0; i < clipData.getItemCount(); i++) {
                                        Uri imageUri = clipData.getItemAt(i).getUri();  //선택한 이미지들의 uri를 가져옴.

                                        try {
                                            uriList.add(imageUri);  //uri를 list에 담음
                                        } catch (Exception e) {
                                            // Log.e(TAG,"File select error",e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

    );


}



