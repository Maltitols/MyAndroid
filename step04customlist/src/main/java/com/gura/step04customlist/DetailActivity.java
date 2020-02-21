package com.gura.step04customlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        //DetailActivity가 활성화되는데 사용되었던 Intent 객체의 참조값 얻어오기
//        Intent intent=getIntent();
//        //"dto"라는 키값으로 담긴 Serializable type 객체의 참조값 얻어오기
//        Serializable serial=intent.getSerializableExtra("dto");
//        //원래타입으로 캐스팅
//        CountryDto dto=(CountryDto)serial;

        //위의 코드를 1줄로 바꾸면
        CountryDto dto=(CountryDto)getIntent().getSerializableExtra("dto");

        ImageView imageView=findViewById(R.id.imageView);
        TextView textView=findViewById(R.id.textView);
        Button confirmBtn=findViewById(R.id.confirmBtn);

        //이미지와 내용 출력하기
        imageView.setImageResource(dto.getResId());
        textView.setText(dto.getContent());

        //버튼에 리스너 등록하기
        confirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //액티비티 종료시키기
        finish();
    }
}
