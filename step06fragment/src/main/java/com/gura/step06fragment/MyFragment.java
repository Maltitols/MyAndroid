package com.gura.step06fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
/*
    Fragment 만드는 방법
    1. Fragment 상속
    2. onCreateView() 오버라이드

 */

public class MyFragment extends Fragment implements View.OnTouchListener{
    //터치 횟수를 관리할 필드
    private int touchCount;
    private TextView textView;
    //액티비티의 참조값을 MyFragmentListener type으로 사용하기
    private MyFragmentListener activity;

    //MyFragment를 사용할 액티비티가 구현할 인터페이스
    public interface MyFragmentListener{
        public void showMessage(int count);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //인자로 전달되는 레이아웃 전개자 객체를 이용해서 View 객체를 만들어서
        View view=inflater.inflate(R.layout.fragment_my, container);
        //TextView의 참조값 얻어오기
        textView=view.findViewById(R.id.textView);
        //TextView에 터치 리스너 등록
        textView.setOnTouchListener(this);
        //해당 프래그먼트를 관리하는 액티비티의 참조값
        FragmentActivity a=getActivity();
        //해당 액티비티가 MyFragmentListener type이 맞으면
        if(a instanceof MyFragmentListener){
            //MyFragmentListener type 으로 casting 한다.
            activity=(MyFragmentListener)a;
        }
        //리턴
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //1. 터치 카운트를 1 증가시키고
        touchCount++;
        //2. TextView에 출력
        textView.setText(Integer.toString(touchCount));
        //3. 액티비티 메소드 호출하면서 카운트 전달
        if(touchCount%10==0 && activity != null){
            activity.showMessage(touchCount);
        }
        return false;
    }
}
