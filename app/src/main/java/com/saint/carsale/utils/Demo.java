package com.saint.carsale.utils;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saint.carsale.R;

/**
 * Created by zzh on 16-1-14.
 */
public class Demo extends Activity implements View.OnClickListener {
    EditText text;
    Button btnAdd,btnSub;
    OnNumChangeListener onNumChangeListener;
    int num;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopfragment_layout);
        btnAdd= (Button) findViewById(R.id.btn_add);
        text= (EditText) findViewById(R.id.text);
        btnSub= (Button) findViewById(R.id.sub);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String numString = text.getText().toString();
        if (numString == null || numString.equals(""))
        {
            num = 0;
            text.setText("0");
        } else
        {
            if (v.getId()==R.id.btn_add)
            {
                if (++num < 0)  //先加，再判断
                {
                    num--;

                } else
                {
                    text.setText(String.valueOf(num));

                    if (onNumChangeListener != null)
                    {
                        onNumChangeListener.onNumChange(Demo.this, num);
                    }
                }
            } else if (v.getId()==R.id.sub)
            {

                if (--num < 0)  //先减，再判断
                {
                    num++;
                } else
                {
                    Toast.makeText(Demo.this,"sub",Toast.LENGTH_SHORT).show();
                    text.setText(String.valueOf(num));
                    if (onNumChangeListener != null)
                    {
                        onNumChangeListener.onNumChange(Demo.this, num);
                    }
                }
            }
        }
    }


    public interface OnNumChangeListener
    {
        /**
         * 输入框中的数值改变事件
         * @param view 整个AddAndSubView
         * @param num 输入框的数值
         */
        public void onNumChange(Demo view, int num);
    }
    TextWatcher mTextWatcher=new TextWatcher() {
        private  CharSequence charSequence;
        private int editStart,editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                charSequence=s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
                    editStart=text.getSelectionStart();
                    editEnd=text.getSelectionEnd();
        }
    };
}
