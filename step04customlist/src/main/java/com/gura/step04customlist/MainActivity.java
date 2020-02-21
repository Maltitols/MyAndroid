package com.gura.step04customlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //필드 정의
    ListView listView;
    List<CountryDto> countries;
    CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);
        countries=new ArrayList<>();
        countries.add(new CountryDto(R.drawable.austria, "오스트리아", "어쩌구..."));
        countries.add(new CountryDto(R.drawable.belgium, "벨기에", "저쩌구..."));
        countries.add(new CountryDto(R.drawable.brazil, "브라질", "이러쿵..."));
        countries.add(new CountryDto(R.drawable.france, "프랑스", "저러쿵..."));
        countries.add(new CountryDto(R.drawable.korea, "한국", "덩기덕..."));
        countries.add(new CountryDto(R.drawable.israel, "이스라엘", "쿵기덕..."));
        //adapter 객체 생성
        adapter=new CountryAdapter(this, R.layout.listview_cell, countries);
        //adapter를 ListView에 연결
        listView.setAdapter(adapter);
        //listView에 아이템 클릭 리스너 등록
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //intent 객체 생성하기
        Intent intent=new Intent(this, DetailActivity.class);
        //클릭한 아이템에 해당하는 국가 정보를 얻어온다
        CountryDto dto=countries.get(i);
        //국가정보를 Intent객체에 "dto"라는 키값으로 담고싶다
        intent.putExtra("dto", dto);
        //액티비티 이동
        startActivity(intent);
    }
}
