package com.czq.club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.core.content.ContextCompat;

public class ChangePwd extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd);
        //未登录下的我的界面的list view
        final EditText oldpwd=(EditText)findViewById(R.id.change_pwd_old);
         oldpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldpwd.setCursorVisible(true);
            }
        });
         //toolbar
         final Toolbar toolbar=(Toolbar)findViewById(R.id.change_pwd_toorbar);
        // setTitleCenter(toolbar);

         final ImageView imgback=(ImageView)findViewById(R.id.change_pwd_imgback);
         imgback.setOnClickListener(new View.OnClickListener(){
             public void onClick(View v){
                 Intent intent=new Intent(ChangePwd.this,MyActivity.class);
                 startActivity(intent);
             }
         });
    }

    public void setTitleCenter(Toolbar toolbar) {
        String title = "title";
        final CharSequence originalTitle = toolbar.getTitle();
        toolbar.setTitle(title);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (title.equals(textView.getText())) {
                    textView.setGravity(Gravity.CENTER);
                    Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.MATCH_PARENT);
                    params.gravity = Gravity.CENTER;
                    textView.setLayoutParams(params);
                }
            }
            toolbar.setTitle(originalTitle);
        }
    }


}
