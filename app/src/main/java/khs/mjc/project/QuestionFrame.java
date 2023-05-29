package khs.mjc.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class QuestionFrame extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_frame,container,false);
        int server_day=4; //서버에서 받아오는 일 수
        Question quest=new Question(server_day);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                quest.question);
        ListView list=view.findViewById(R.id.listView1);
        list.setAdapter(adapter);
        return view;
    }
}
class Question{
    String[] man; //남자 질문지 내용
    String[] woman;//여자 질문지 내용
    String[] question;//질문
    Question(int day){
        man=new String[day]; //day값 만큼 배열 생성
        woman=new String[day]; //day값 만큼 배열 생성
        question=new String[day]; //day값 만큼 배열 생성
        for(int i=0;day>0;day--){
            man[i]=Integer.toString(day); //day값 만큼 디비에서 불러와서 저장
            woman[i]=Integer.toString(day); //역순출력 어케할지 고민좀 해봐야할듯
            question[i]=Integer.toString(day);
            i++;
        }
    }
}