package com.gura.step05asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //진행시작, 진행과정, 결과를 표시할 TextView
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView의 참조값을
        textView=findViewById(R.id.textView);
    }
    //작업하기 버튼을 눌렀을때 호출되는 메소드
    public void start(View v){
        /*
            버튼을 누르면 여기에 실행순서가 들어온다.
            그 스레드는 UI 스레드 (main 스레드) 이다.
            UI 스레드에서 시간이 오래 걸리거나 언제 끝날지 모르는
            불확실한 작업을 하면 안된다.
            UI의 업데이트는 UI 스레드에서만 가능하다.
         */
//        try{
//            Thread.sleep(1000*20);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        } // 이런짓하면 안된다. 앱 뻗음

        //비동기 작업의 시작은 객체를 생성하고
        CounterTask task=new CounterTask();
        //execute() 메소드를 호출하면 된다.
        task.execute("김구라", "해골", "원숭이");
    }
    /*
        extends AsyncTask<전달받는 type, 진행중 반환하는 type, 결과 type>

        type이 필요없으면 Void type 사용하면 된다.
        ex) extends AsyncTask<String, Void, Void>
     */
    public class CounterTask extends AsyncTask<String, Integer, String>{
        //publishProgress() 메소드를 호출하면 아래의 메소드가 호출됨
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //여기는 일회용 UI 같은 느낌이라 UI 업데이트 작업이 가능
            //publishProgress() 메소드에 전달된 인자가 이 메소드의 인자로 전달
            int count=values[0]; //Integer 배열의 0번 인자로 들어가있다.
            textView.setText(Integer.toString(count));
        }
        //doInBackground() 메소드가 리턴되면 아래의 메소드가 호출된다.
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
        //doInBackground() 메소드가 호출되기 직전에 호출된다.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //여기는 UI 스레드이기때문에 업데이트가능
            textView.setText("숫자 세기를 시작합니다.");
        }

        @Override
        protected String doInBackground(String... strings) {
            String name1=strings[0];
            String name2=strings[1];
            String name3=strings[2];


            int count=0;
            //백그라운드(새로운 스레드) 에서 작업할 내용을 여기에
            for(int i=0; i<20; i++){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){}
                count++;
                //count 값을 TextView 에 직접 출력하는건 불가능
                publishProgress(count);
            }
            String result="숫자 세기 성공!";

            return result;
        }
    }
}
