package com.saint.carsale.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.saint.carsale.BaseActivity;
import com.saint.carsale.R;
import com.saint.carsale.main.MainActivity;

/**
 * Created by zzh on 16-1-9.
 */
public class Login_Main_Activity extends BaseActivity implements View.OnClickListener {
    EditText text_phone,text_code;
    ImageButton btn_code,btn_login;
    CheckBox ck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initdata();
    }

    @Override
    protected void setUpActionBar() {

    }
    private void initdata(){
        text_code= (EditText) findViewById(R.id.editcode);
        text_phone= (EditText) findViewById(R.id.edit_phone);
        btn_code= (ImageButton) findViewById(R.id.image_code);
        btn_login= (ImageButton) findViewById(R.id.btn_login);
        ck= (CheckBox) findViewById(R.id.checkBox);
        text_phone.setOnClickListener(this);
        text_code.setOnClickListener(this);
        btn_code.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Intent intent=new Intent(Login_Main_Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
