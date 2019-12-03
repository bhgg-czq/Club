package com.czq.club;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ClubActivity1 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_describtion);

        //返回的点击事件
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(ClubActivity1.this,ClubActivity.class);
                startActivity(intent);
            }
        });

        //对对应点击社团信息赋值
        BeanMyclub myclub=(BeanMyclub) getIntent().getSerializableExtra("myclub");
        Log.d("MainActivity1",myclub.getDescribtion());
        ImageView club_logo=(ImageView) findViewById(R.id.club_logo);
        TextView member_count=(TextView) findViewById(R.id.member_count);
        TextView club_des=(TextView) findViewById(R.id.club_describtion);
        TextView club_name=(TextView) findViewById(R.id.club_name);
        TextView club_thingd=(TextView) findViewById(R.id.club_things);

        club_logo.setImageResource(myclub.getMyclub_logo());
        member_count.setText(Integer.toString(myclub.getMember_count()));
        club_name.setText(myclub.getMyclub_name());
        club_des.setText(myclub.getDescribtion());
        club_thingd.setText(myclub.getThings());

    }
}
