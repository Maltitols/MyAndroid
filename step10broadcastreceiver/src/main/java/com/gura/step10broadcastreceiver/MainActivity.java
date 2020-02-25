package com.gura.step10broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    public static final String HUNGRY="com.gura.step10broadcastreceiver.HUNGRY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView 참조값 필드에 저장
        textView=findViewById(R.id.textView);
        //방송 수신자 객체 생성해서
        MyReceiver mr=new MyReceiver();
        //동작 가능하도록 등록
        registerReceiver(mr,new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));

        HungryReceiver hr=new HungryReceiver();
        registerReceiver(hr, new IntentFilter(HUNGRY));
    }
    //방송 수신자 객체가 호출할 메소드
    public void updateUI(String msg){
        textView.setText(msg);
    }
    //버튼눌렀을때 동작
    public void btnClicked(View v){
        //인텐트 객체 생성해서 액션명 지정
        Intent intent=new Intent();
        intent.setAction(HUNGRY);
        //인텐트 사용해서 방송
        sendBroadcast(intent);
    }
}
