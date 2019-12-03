package com.czq.club;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ClubActivity extends AppCompatActivity {

    private List<BeanMyclub> myclubList=new ArrayList<>();
    private List<BeanMyclub_task> myclub_taskList=new ArrayList<>();
    private List<BeanMyclub_passage> myclub_passageList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club);
        for(int i=0;i<5;i++){
            BeanMyclub club=new BeanMyclub("轮滑社",R.drawable.star,"收到货发开始的发力科技发达了肯定放上来拿开放空间可烂地方可拉出了你的",6,"冻死了科技发生了宫颈癌老人婆我AV科目的处女梦想成真， ");
            myclubList.add(club);
        }
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.club_logo_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyclubAdapter adapter=new MyclubAdapter(myclubList);
        recyclerView.setAdapter(adapter);

        for(int i=0;i<5;i++){
            BeanMyclub_task myclub_task=new BeanMyclub_task(R.drawable.star,"轮滑社","2小时前","刷卡缴费分红方式开发均可今年擦分红诶换个快递费巨卡几十年的恐惧可接受的加咖啡红安徽foe很丰富的恐惧包括");
            myclub_taskList.add(myclub_task);
        }
        RecyclerView recyclerView1=(RecyclerView) findViewById(R.id.club_task_view);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        //linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        Myclub_taskAdapter adapter1=new Myclub_taskAdapter(myclub_taskList);
        recyclerView1.setAdapter(adapter1);


        for(int i=0;i<5;i++){
            BeanMyclub_passage myclub_passage=new BeanMyclub_passage(R.drawable.star,"轮滑社","2小时前",R.drawable.star,"安居房哈克斯发单号爱空间划分的快递发");
            myclub_passageList.add(myclub_passage);
        }
        RecyclerView recyclerView2=(RecyclerView) findViewById(R.id.club_passage_view);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this);
        //linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        Myclub_passageAdapter adapter2=new Myclub_passageAdapter(myclub_passageList);
        recyclerView2.setAdapter(adapter2);

        //点击“我关注的”出现社团推送
        final Button attention=(Button) findViewById(R.id.attention);
        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setFocusable(true);
                view.requestFocus();
                view.requestFocusFromTouch();
                RecyclerView recyclerView1=(RecyclerView) findViewById(R.id.club_task_view);
                recyclerView1.setVisibility(View.GONE);
                RecyclerView recyclerView2=(RecyclerView) findViewById(R.id.club_passage_view);
                recyclerView2.setVisibility(View.VISIBLE);

            }
        });
        //点击“我加入的”出现社团推送
        Button join=(Button) findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setFocusable(true);
                view.requestFocus();
                view.requestFocusFromTouch();
                RecyclerView recyclerView1=(RecyclerView) findViewById(R.id.club_task_view);
                recyclerView1.setVisibility(View.VISIBLE);
                RecyclerView recyclerView2=(RecyclerView) findViewById(R.id.club_passage_view);
                recyclerView2.setVisibility(View.GONE);

                Button cancel_save=(Button) LayoutInflater.from(ClubActivity.this).inflate(R.layout.club_describtion, null).findViewById(R.id.cancel_save);
                cancel_save.setVisibility(View.GONE);
            }
        });

        //底部的菜单栏的监听
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //   viewpager_launch.setCurrentItem(0);
//                    Intent intent1=new Intent(ClubActivity.this,ClubActivity.class);
//                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                    //  viewpager_launch.setCurrentItem(1);
                    Intent intent2=new Intent(ClubActivity.this,MainActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:
                    // viewpager_launch.setCurrentItem(2);
                    Intent intent=new Intent(ClubActivity.this,MyActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

}
