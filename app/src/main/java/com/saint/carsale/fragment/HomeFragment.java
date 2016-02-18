package com.saint.carsale.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.saint.carsale.R;
import com.saint.carsale.fragment.adapter.HomeAdapter;
import com.saint.carsale.utils.BannerData;
import com.saint.carsale.utils.BannerView;
import com.saint.carsale.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zzh on 16-1-9.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private List<Map<String,Object>> list=new ArrayList<>();
    private List listSimple=new ArrayList<>();
    public  List<BannerData> data=new ArrayList<BannerData>();
    private ProgressDialog loadingDialog;
    public  HomeAdapter adapter;
    public  BannerData d;
    public  BannerView bannerView;
    private SearchView mSearchView;
    LinearLayout linearLeft,linearRight,linear1,linear2,linear3,linear4,linear5,linear6,linear7,linear8,linear9,linear10,linear11,linear12;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.homefragment,container,false);

        mSearchView= (SearchView) view.findViewById(R.id.seach);
        linear1= (LinearLayout) view.findViewById(R.id.linear1);
        linear2= (LinearLayout) view.findViewById(R.id.linear2);
        linear3= (LinearLayout) view.findViewById(R.id.linear3);
        linear4= (LinearLayout) view.findViewById(R.id.linear4);
        linear5= (LinearLayout) view.findViewById(R.id.linear5);
        linear6= (LinearLayout) view.findViewById(R.id.linear6);
        linear7= (LinearLayout) view.findViewById(R.id.linear7);
        linear8= (LinearLayout) view.findViewById(R.id.linear8);
        linear9= (LinearLayout) view.findViewById(R.id.linear9);
        linear10= (LinearLayout) view.findViewById(R.id.linear10);
        linear11= (LinearLayout) view.findViewById(R.id.linear11);
        linear12= (LinearLayout) view.findViewById(R.id.linear12);
        linearRight= (LinearLayout) view.findViewById(R.id.linearRight);
        linearLeft= (LinearLayout) view.findViewById(R.id.linearLeft);

        linearLeft.setOnClickListener(this);
        linearRight.setOnClickListener(this);
        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);
        linear5.setOnClickListener(this);
        linear6.setOnClickListener(this);
        linear7.setOnClickListener(this);
        linear8.setOnClickListener(this);
        linear9.setOnClickListener(this);
        linear10.setOnClickListener(this);
        linear11.setOnClickListener(this);
        linear12.setOnClickListener(this);

        //如果有延迟加个progressdialog
        loadingDialog=new ProgressDialog(getActivity(),ProgressDialog.STYLE_SPINNER);
        loadingDialog.setMessage("正在努力加载中...");
        loadingDialog.setCancelable(false);
        loadingDialog.setIndeterminate(true);
//        loadingDialog.show();


        bannerView= (BannerView) view.findViewById(R.id.home_banner);
//        getImageJSON();
//        bannerView.setCallback(new BannerView.ClickCallback() {
//            @Override
//            public void perform(int id, int position) {
//                Intent intent = new Intent(getActivity(), Banner_Contents_Main_Activity.class);
//                String inTitle = data.get(position).getTitle();
//                String inContents = data.get(position).getContents();
//                intent.putExtra("inTitle", inTitle);
//                intent.putExtra("inContents", inContents);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                getActivity().finish();
//
//            }
//        });
        bannerView.setCallback(new BannerView.ClickCallback() {
            @Override
            public void perform(int id, int position) {
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        data = new ArrayList<BannerData>();
        String[] urls = new String[]{
                "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu1.jpg",
                "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu2.jpg",
                "http://7xlwwd.com1.z0.glb.clouddn.com/yanwushu3.jpg"
        };
        for (int i = 0; i < urls.length; i++) {
            BannerData d = new BannerData();
            d.setImage(urls[i]);
            d.setTitle("i帮tile" + i);
            d.setId(i);
            data.add(d);
        }
        bannerView.startup(data);


    TagJson();
        return view;
    }
    /**
     * 获取轮播图*/
    private void getImageJSON(){
        Log.e("getImageView:"+"====================>","开始");
        String url= Constant.SERVER_URL+"/wp/v2/posts/?filter[category_name]=slider";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.print(response);
                Log.e("response:" + "====================>", "开始");
//                loadingDialog.dismiss();
                int len=response.length();
                for (int i=0;i<len;i++){
                    JSONObject object=response.optJSONObject(i);
                    String image=object.optString("featured_image");
                    String title=object.optString("title");
                    String contents=object.optString("content");
                    try {
                        JSONObject js_title=new JSONObject(title);
                        JSONObject js_content=new JSONObject(contents);
                        String rendered=js_title.optString("rendered");
                        String content=js_content.optString("rendered");
                        d=new BannerData();
                        d.setTitle(rendered);
                        d.setContents(content);
                        d.setImage(image);
                        data.add(d);
                        System.out.print(data);
                        bannerView.startup(data);
                    } catch (JSONException e) {
                        Log.e("Home===============>","JSONException");
                    }

                }
            }
        });

    }
    private void TagJson(){
        String url=Constant.SERVER_URL+"/wc/v1/products/tags";
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.print(response);

            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear1:
                Toast.makeText(getActivity(),"点击1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear2:
                Toast.makeText(getActivity(),"点击2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear3:
                Toast.makeText(getActivity(),"点击3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear4:
                Toast.makeText(getActivity(),"点击4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear5:
                Toast.makeText(getActivity(),"点击5",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear6:
                Toast.makeText(getActivity(),"点击6",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear7:
                Toast.makeText(getActivity(),"点击7",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear8:
                Toast.makeText(getActivity(),"点击8",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear9:
                Toast.makeText(getActivity(),"点击9",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear10:
                Toast.makeText(getActivity(),"点击10",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear11:
                Toast.makeText(getActivity(),"点击11",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear12:
                Toast.makeText(getActivity(),"点击12",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linearLeft:

                break;
            case R.id.linearRight:
                break;

        }
    }




}
