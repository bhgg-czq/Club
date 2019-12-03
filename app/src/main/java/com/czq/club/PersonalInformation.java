package com.czq.club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;

public class PersonalInformation extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information);

        //Edittext设置一开始的时候光标隐藏，当点击的时候光标出现
        final EditText phone=(EditText)findViewById(R.id.personal_informaton_phonenumber);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setCursorVisible(true);
            }
        });

        final Toolbar toolbar=(Toolbar)findViewById(R.id.personal_informaton_toorbar);



        final ImageView imgback=(ImageView)findViewById(R.id.personal_informaton_imgback);
        imgback.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(PersonalInformation.this,MyActivity.class);
                startActivity(intent);
            }
        });
    }
}
