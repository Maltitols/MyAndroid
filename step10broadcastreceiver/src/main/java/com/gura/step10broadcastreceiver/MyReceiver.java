package com.gura.step10broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
    [Broadcast Receiver 만드는법]

    - BroadcastReceiver 추상클래스 상속
    - onReceive 메소드 오버라이드해서 원하는 작업 진행
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isOn=intent.getBooleanExtra("state", false);
        MainActivity activity=(MainActivity)context;
        if(isOn){
            Toast.makeText(context, "비행모드가 켜졌습니다", Toast.LENGTH_LONG).show();
            activity.updateUI("AirplaneMode On");
        }else{
            Toast.makeText(context, "비행모드가 꺼졌습니다", Toast.LENGTH_LONG).show();
            activity.updateUI("AirplaneMode Off");
        }

    }
}
