package khs.mjc.project;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {

    private static CustomDialog customDialog;

    private CustomDialog(@NonNull Context context) {
        super(context);
    }

    public static CustomDialog getInstance(Context context) {
        customDialog = new CustomDialog(context);

        return customDialog;
    }

    //다이얼로그 생성하기
    public void showDefaultDialog() {
        //참조할 다이얼로그 화면을 연결한다.
        customDialog.setContentView(R.layout.question_answer_dialog);

        //다이얼로그의 구성요소들이 동작할 코드작성
        Button save = findViewById(R.id.saveButton);
        Button back = findViewById(R.id.previousButton);
        EditText q_text = findViewById(R.id.answer);
        TextView q_title_text = findViewById(R.id.question);
        TextView name = findViewById(R.id.name);
        name.setText("일단 루시안");
        String[] text = new String[1];
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text[0] = q_text.getText().toString();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text[0] = "Null";
                return;
            }
        });
        customDialog.show();
    }

    //취소버튼을 눌렀을때 일반적인 클릭리스너
    View.OnClickListener clickCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "취소 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
            customDialog.dismiss();
        }
    };

}
