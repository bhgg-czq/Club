package com.czq.club;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{



    private TextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;

    //wcc
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //wcc
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        // Set up the login form.
        mEmailView = (TextView) findViewById(R.id.email);
    //    populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
             //   if () {
            //        attemptLogin();
               //     return true;
                //}
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.login_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //wcc
                String account = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                editor = pref.edit();
                if (rememberPass.isChecked()) {
                    //检查复选框是否被选中
                    editor.putBoolean("remember_password", true);
                    editor.putString("account", account);
                    editor.putString("password", password);
                } else {
                    editor.clear();
                }
                editor.commit();


            }
        });


        //wcc
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            //将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            mEmailView.setText(account);
            mPasswordView.setText(password);
            rememberPass.setChecked(true);

        }
    }
}