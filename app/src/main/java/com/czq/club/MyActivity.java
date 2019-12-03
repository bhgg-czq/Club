package com.czq.club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {
    private List<my_listitem> listitems=new ArrayList<>();//未登录
    private List<my_listitem> listitems2=new ArrayList<>();//登陆
    int n=1;//判断是否登陆
    int s=1;//判断是否是社长
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //未登录下的我的界面的list view
        if (n==0) {
            setContentView(R.layout.activity_my);
            initItems();
            my_listitem_adapter myListitemAdapter = new my_listitem_adapter(MyActivity.this, R.layout.my_notlogin_listitem, listitems);
            ListView notloginlistView = (ListView) findViewById(R.id.my_notlogin_listview);
            notloginlistView.setAdapter(myListitemAdapter);

            Button button=(Button)findViewById(R.id.img_head_login);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MyActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });

        }
        else {
            setContentView(R.layout.activity_my2);
            //登陆界面下的我的list view
            initItems2();
            my_listitem_adapter myListitemAdapter2 = new my_listitem_adapter(MyActivity.this, R.layout.my_notlogin_listitem, listitems2);
            ListView alloginlistView = (ListView) findViewById(R.id.my_allogin_listview);
            alloginlistView.setAdapter(myListitemAdapter2);
            alloginlistView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    if (position==0){
                        Intent intent=new Intent(MyActivity.this,PersonalInformation.class);
                        startActivity(intent);
                    }
                    else if(position==1){
                        Intent intent=new Intent(MyActivity.this,ChangePwd.class);
                        startActivity(intent);
                    }
                    else if (s==1&&position==2){
                        Intent intent=new Intent(MyActivity.this,ClubManage.class);
                        startActivity(intent);
                    }

                }

            });
        }

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
                    Intent intent1=new Intent(MyActivity.this,ClubActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_dashboard:
                    //  viewpager_launch.setCurrentItem(1);
                    Intent intent2=new Intent(MyActivity.this,MainActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:
                    // viewpager_launch.setCurrentItem(2);
//                    Intent intent=new Intent(MyActivity.this,MyActivity.class);
//                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };


    private void initItems(){
        my_listitem item1=new my_listitem("使用帮助");
        my_listitem item2=new my_listitem("关于社团助手");
        listitems.add(item1);
        listitems.add(item2);
    }
    private void initItems2(){
        my_listitem item1=new my_listitem("个人信息");
        my_listitem item2=new my_listitem("修改密码");
        my_listitem item3=new my_listitem("使用帮助");
        my_listitem item4=new my_listitem("关于社团助手");
        listitems2.add(item1);
        listitems2.add(item2);


        if (s==1){
            my_listitem item5=new my_listitem("管理社团");
            listitems2.add(item5);
        }

        listitems2.add(item3);
        listitems2.add(item4);
    }

}
