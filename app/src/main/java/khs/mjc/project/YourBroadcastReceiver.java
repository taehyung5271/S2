package khs.mjc.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class YourBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 일수 증가 및 SharedPreferences 업데이트 작업 수행
        increaseDayAndUploadToSharedPreferences(context);
        Log.d("Alarm", "되었습니다."); // 로그 메시지 추가
        Toast.makeText(context, "알람이 울렸습니다.", Toast.LENGTH_SHORT).show();
    }

    private void increaseDayAndUploadToSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // 현재 일수 가져오기
        int currentDay = sharedPreferences.getInt("day", 1);
        int newDay = currentDay + 1;

        // SharedPreferences에 일수 저장
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("day", newDay);
        editor.apply();
    }
}