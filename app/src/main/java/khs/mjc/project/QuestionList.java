package khs.mjc.project;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class QuestionList extends AppCompatActivity {
    Dialog dia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        TextView q_title, q_subtitle, man_name, woman_name;
        TextView man_text, woman_text;
        q_title=findViewById(R.id.q_title);
        q_subtitle=findViewById(R.id.q_subtitle);
        man_name=findViewById(R.id.man_name);
        woman_name=findViewById(R.id.woman_name);

        man_text=findViewById(R.id.man_text);
        woman_text=findViewById(R.id.woman_text);

        dia=new Dialog(QuestionList.this);
        dia.setContentView(R.layout.question_answer_dialog);
        String[] q_list={
                "오늘이 두 분의 첫 만남이라면 당신을 어떻게 소개하실 건가요?",
                "그 사람을 처음 마주했을 때 당신의 감정은 어땠나요?",
                "그 사람을 생각하면 떠오르는 노래가 있나요?",
                "난 사실 너의 마음을 얻기 위해 했어.",
                "그 사람과 닮은 동물은 무엇인가요?",
                "당신은 상대방의 어떤 모습에 반하게 되었나요?",
                "당신이 가장 좋아하는 아이스크림은? 사소한 것도 궁금해요.",
                "우리 운명인 것 같아! 두 사람의 닮은 점이 있다면?",
                "당신이 가장 좋아하는 스킨십은 무엇인가요?",
                "사랑에 빠지기 시작할 때 당신이 가장 두려워 하는 것은 무엇인가요?",
                "오늘이 당신의 마지막 날이라면 어떤 하루를 보내고 싶은가요? ",
                "그 사람과 처음 대화를 나눈 날 어떤 이야기를 주고받았나요? ",
                "집이 무너진다면, 가족들을 구한 후 가장 먼저 챙겨 나올 물건 3가지는? ",
                "상대방과 함께 보고 싶은 당신의 인생 영화 top3를 알려주세요! ",
                "일주일 동안 뽀뽀 금지령이 내려진다면?",
                "당신이 연애 상대를 택할 때 가장 중요하게 생각하는 것은 무엇인가요? ",
                "오늘 같은 날씨에 함께 요리해서 먹고 싶은 음식은 무엇인가요? ",
                "고백했던, 혹은 고백을 받았던 날 기분은 어땠나요?",
                "그 사람이 토라졌을 때 한방에 풀리게 하는 비법이 있다면?",
                "처음엔 네가 '이런' 사람 일 것이라 생각했어.",
                "달라도 너무 달라! 우리 이것만큼은 정 반대인 것 같아.",
                "당신이 가장 당신다워지는 순간은 언제인가요?",
                "그 사람은 어떤 목소리와 눈빛을 지니고 있는 사람인가요? ",
                "당신이 발견한 그 사람의 귀여운 버릇이 있다면?",
                "당신이 아무리 노력해도 고치기 어려운 단점이 있나요? ",
                "나와 연애를 하며 '이것' 만큼은 꼭 지켜주면 좋겠어! ",
                "올해가 가기 전에 상대방과 꼭 함께 이루고 싶은 것이 있나요?",
                "그 사람과 눈을 마주치면 어떤 생각이 드나요?",
                "당신이 생각하는 행복을 위한 부의 기준은 어느정도인가요? ",
                "그 사람이 했던 말 중 오래도록 머릿속에 남았던 말은?",
                "서로 양보할 수 없는 갈등이 생긴다면 어떻게 해결하면 좋을까요? ",
                "당신은 그 사람에게 어떤 존재가 되어주고 싶은가요? ",
                "상대방에게 섹시함을 느꼈던 순간이 있다면 언제인가요? ",
                "누구나 각자의 고민이 있어요. 서로의 고민을 털어놔 보세요! ",
                "사랑을 의미하는 단어를 나열해 보세요. 단, 사랑이란 단어는 빼고요. ",
                "당신의 소비패턴에서 가장 많은 지출을 차지하는 것은?",
                "상대방은 어떤 향기가 나는 사람인가요?",
                "매일 너와 - 하고싶어♥",
                "당신이 사랑하는 그 사람을 자랑해보세요. 최대한 뻔뻔하게요!! ",
                "살면서 가장 당황했던 순간은 언제인가요?",
                "당신이 온전히 그 사람만을 위해서 하는 행동이 있다면 무엇인가요?",
                "오늘은 아무 말 대잔치의 날이에요 상대방에게 하고싶은 아무 말은?",
                "당신은 주변 사람들에게 어떤 사람으로 남고 싶나요?",
                "당신이 연인이 당신에게 서운함을 느낄 때는 언제인가요? ",
                "당신이 생각하는 가장 이상적인 연인 관계는 어떤 모습인가요?",
                "최근에 당신이 바보같이 느껴졌던 순간은 언제인가요?",
                "지금 그 사람과 어디든 떠날 수 있다면, 함께 가고 싶은 곳은 어디인가요? ",
                "멀리서 그 사람이 걸어 볼 때 어떤 생각이 드나요?",
                "두 분의 연애 스토리를 영화로 만든다면 어떤 장르일까요?"};

        Intent intent = getIntent();
        String man=intent.getStringExtra("man");
        String woman=intent.getStringExtra("woman");
        //String question=intent.getStringExtra("question");

        int number=intent.getIntExtra("number",0);
        q_title.setText(q_list[number-1]);
        man_name.setText("나");
        woman_name.setText("상대");
        q_subtitle.setText(number+"번째 질문");
        if(man.equals("Null")){
            man_text.setText("답변을 등록해주세요.");
        }
        else{
            man_text.setText(man);
        }
        if(woman.equals("Null")){
            woman_text.setText("상대방이 아직 입력을 하지 않았어요.");
        }
        else{
            woman_text.setText(woman);
        }
        ImageView close = findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionList.this,Main.class);
                startActivity(intent);
            }
        });
        man_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.getInstance(dia.getContext()).showDefaultDialog();
            }
        });

    }
}


