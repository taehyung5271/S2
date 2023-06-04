package khs.mjc.project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    private DialogFragmentListener listener;
    private ExpandableListView expandableListView;
    private EditText bucketlist;
    EditText editText;
    Button registerBtn, returnBtn;
    View dialogView;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog1, null);

        //    registerBtn=dialogView.findViewById(R.id.registerBtn);
        //    returnBtn=dialogView.findViewById(R.id.returnBtn);
        //   editText=dialogView.findViewById(R.id.dlgEdt1);
        //   String inputData = editText.getText().toString();

        // Dialog에 레이아웃 설정
        builder.setView(dialogView);
        builder.setTitle("리스트 추가하기");
//        registerBtn.setOnClickListener(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog,int which) {
//                // 확인 버튼 클릭 이벤트 처리
//                // 선택된 데이터를 부모 아이템으로 전달
////                int selectedPosition = expandableListView.getCheckedItemPosition();
////                if (selectedPosition != ListView.INVALID_POSITION) {
////                    ExpandableListAdapter adapter = expandableListView.getExpandableListAdapter();
////                    long packedPosition = expandableListView.getExpandableListPosition(selectedPosition);
////                    int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
////                    String selectedData = (String) adapter.getGroup(groupPosition);
////                    listener.onDataPass(selectedData);
// //               }
//
//
//                if (listener != null) {
//                    listener.onDataPass(inputData);
//                }
//                dismiss(); // 다이얼로그 닫기
//            }
//        });

        builder.setPositiveButton("등록하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 등록하기 버튼 클릭 이벤트 처리
                EditText editText = dialogView.findViewById(R.id.dlgEdt1);
                String inputData = editText.getText().toString();

                // 데이터 전달
                if (listener != null) {
                    listener.onDataPass(inputData);
                }



                // dismiss(); // 다이얼로그 닫기
            }
        });
        builder.setNegativeButton("돌아가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 취소 버튼 클릭 이벤트 처리
                dismiss();
            }
        });


        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }


    //        registerBtn = view.findViewById(R.id.registerBtn);
//        returnBtn = view.findViewById(R.id.returnBtn);
//        registerBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // 선택된 데이터를 부모 아이템으로 전달
//                int selectedPosition = expandableListView.getCheckedItemPosition();
//                if (selectedPosition != ListView.INVALID_POSITION) {
//                    ExpandableListAdapter adapter = expandableListView.getExpandableListAdapter();
//                    long packedPosition = expandableListView.getExpandableListPosition(selectedPosition);
//                    int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
//                    String selectedData = (String) adapter.getGroup(groupPosition);
//                    listener.onDataPass(selectedData);
//                }
//                dismiss();
//            }
//        });
//    }
    public void setDataPassListener(DialogFragmentListener listener) {
        this.listener= listener;
    }
}