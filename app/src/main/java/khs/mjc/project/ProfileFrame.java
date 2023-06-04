package khs.mjc.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFrame extends Fragment{
    TextView text_gz, text_if, text_t3, text_t4;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFrame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFrame newInstance(String param1, String param2) {
        ProfileFrame fragment = new ProfileFrame();
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
        View v = inflater.inflate(R.layout.fragment_profile_frame, container, false);
        text_gz = v.findViewById(R.id.pf_t);
        text_if=v.findViewById(R.id.pf_t2);
        text_t3 = v.findViewById(R.id.pf_t3);
        text_t4 = v.findViewById(R.id.pf_t4);

        text_gz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Profile_gongzi.class);
                startActivity(intent);
            }
        });
        text_if.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Profile_menuui.class);
                startActivity(intent);
            }
        });
        text_t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Proflie_account.class);
                startActivity(intent);

            }
        });
        text_t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =  getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                AlertDialog dialog = builder.setMessage("탈퇴시 모든 정보가 사라지고 연인과의 연결이 끊깁니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putBoolean("isMainScreenShown", false);
                        editor.apply();
                        Intent intent = new Intent(getActivity(), Check1.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("취소", null).create();
                dialog.show();
            }
        });

        return v;
    }
}
