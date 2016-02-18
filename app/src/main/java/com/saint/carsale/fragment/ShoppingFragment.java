package com.saint.carsale.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saint.carsale.R;
import com.saint.carsale.ZListViewAdapter;
import com.saint.carsale.utils.ZListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzh on 16-1-9.
 */
public class ShoppingFragment extends Fragment {
    private ZListView zListView;
    private CheckBox ck;
    private Button btnSettlemen;
    private TextView textSettlemen;
    private List<Map<String,Object>> list=new ArrayList<>();
    private ZListViewAdapter adapter;

    OnNumChangeListener onNumChangeListener;
    int num;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.shoppingfragment,container,false);
        zListView= (ZListView) view.findViewById(R.id.xlistview);
//        ck= (CheckBox) view.findViewById(R.id.ck);
//        btnSettlemen= (Button) view.findViewById(R.id.btnsettlemen);
//        textSettlemen= (TextView) view.findViewById(R.id.textsettlemen);

        zListView= (ZListView) view.findViewById(R.id.xlistview);

        for (int i=0;i<10;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("key","nihao"+i);
            list.add(map);
        }
        adapter=new ZListViewAdapter(getActivity(),list);
        zListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        zListView.setPullLoadEnable(true);
        zListView.setPullRefreshEnable(true);
        zListView.setXListViewListener(new ZListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoad();
                    }
                }, 2000);
            }
        });
        zListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final EditText etNumber= (EditText) view.findViewById(R.id.textNumber);
                Button btnAdd= (Button) view.findViewById(R.id.btn_add);
                Button btnSub= (Button) view.findViewById(R.id.btn_sub);

                String numString = etNumber.getText().toString();
                if (numString == null || numString.equals("")) {
                    num = 0;
                    etNumber.setText("0");
                }
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"add",Toast.LENGTH_SHORT).show();
                        if (++num < 0)  //先加，再判断
                        {
                            num--;

                        } else
                        {
                            etNumber.setText(String.valueOf(num));

                            if (onNumChangeListener != null)
                            {

                                onNumChangeListener.onNumChange(view, num);
                            }
                        }
                    }
                });
                btnSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"sub",Toast.LENGTH_SHORT).show();

                        if (--num < 0)  //先减，再判断
                        {
                            num++;
                        } else
                        {
                            Toast.makeText(getActivity(),"sub",Toast.LENGTH_SHORT).show();
                            etNumber.setText(String.valueOf(num));
                            if (onNumChangeListener != null)
                            {
                                onNumChangeListener.onNumChange(view, num);
                            }
                        }
                    }
                });
            }
        });
        return view;
    }
    private void onLoad(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
        String time=dateFormat.format(new java.util.Date());
        zListView.stopRefresh();
        zListView.stopLoadMore();
        zListView.setRefreshTime(time);
    }

    public interface OnNumChangeListener
    {
        /**
         * 输入框中的数值改变事件
         * @param view 整个AddAndSubView
         * @param num 输入框的数值
         */
        public void onNumChange(View view, int num);
    }
}
