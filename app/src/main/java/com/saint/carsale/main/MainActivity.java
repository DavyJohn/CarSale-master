package com.saint.carsale.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saint.carsale.BaseActivity;
import com.saint.carsale.R;
import com.saint.carsale.fragment.HomeFragment;
import com.saint.carsale.fragment.InterrogationFargment;
import com.saint.carsale.fragment.ShoppingFragment;
import com.saint.carsale.fragment.UserFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private  static final String TAG="MainActivity";
    LinearLayout home,user,shopping,interrogation;
    TextView text_home,text_user,text_shopping,text_interrogation;
    ImageView image_home,image_user,image_shopping,image_interrogation;

    private FragmentManager fragmentManager=getFragmentManager();
    //四个界面
    private HomeFragment home_fragment;
    private InterrogationFargment interrogation_fragmnet;
    private ShoppingFragment shop_farment;
    private UserFragment user_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        setTabSelection(0);

        if (savedInstanceState!=null){
            home_fragment= (HomeFragment) fragmentManager.findFragmentByTag("home");
            interrogation_fragmnet= (InterrogationFargment) fragmentManager.findFragmentByTag("interrogation");
            shop_farment= (ShoppingFragment) fragmentManager.findFragmentByTag("shop");
            user_fragment= (UserFragment) fragmentManager.findFragmentByTag("user");
        }

    }

    @Override
    protected void setUpActionBar() {

    }

    private void initData(){
        home= (LinearLayout) findViewById(R.id.home);
        user= (LinearLayout) findViewById(R.id.user);
        shopping= (LinearLayout) findViewById(R.id.shopping);
        interrogation= (LinearLayout) findViewById(R.id.interrogation);

        text_home= (TextView) findViewById(R.id.text_home);
        text_interrogation= (TextView) findViewById(R.id.text_interrogation);
        text_shopping= (TextView) findViewById(R.id.text_shopping);
        text_user= (TextView) findViewById(R.id.text_user);

        image_home= (ImageView) findViewById(R.id.image_home);
        image_interrogation= (ImageView) findViewById(R.id.image_interrogation);
        image_shopping= (ImageView) findViewById(R.id.image_shopping);
        image_user= (ImageView) findViewById(R.id.image_user);


        home.setOnClickListener(this);
        user.setOnClickListener(this);
        shopping.setOnClickListener(this);
        interrogation.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                setTabSelection(0);
                break;
            case R.id.interrogation:
                setTabSelection(1);
                break;
            case R.id.shopping:
                setTabSelection(2);
                break;
            case R.id.user:
                setTabSelection(3);
                break;
        }
    }

    private void setTabSelection(int index){
        clean();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index){
            case 0:
                image_home.setImageResource(R.mipmap.grab_treasure_icon_not_selected);
                text_home.setTextColor(Color.RED);
                if (home_fragment==null){
                    Log.e("fragment===================>","home");
                    home_fragment=new HomeFragment();
                    transaction.add(R.id.framelayout_main, home_fragment, "home");
                }else
                    transaction.show(home_fragment);
                break;
            case 1:
                image_interrogation.setImageResource(R.mipmap.find_the_icon_not_selected);
                text_interrogation.setTextColor(Color.RED);
                if (interrogation_fragmnet==null){
                    Log.e("fragment===================>","home");
                    interrogation_fragmnet=new InterrogationFargment();
                    transaction.add(R.id.framelayout_main,interrogation_fragmnet,"interrogation");
                }else {
                    transaction.show(interrogation_fragmnet);
                }
                break;
            case 2:
                image_shopping.setImageResource(R.mipmap.list_icon_not_selected);
                text_shopping.setTextColor(Color.RED);
                if (shop_farment==null){
                    Log.e("fragment===================>","home");
                    shop_farment=new ShoppingFragment();
                    transaction.add(R.id.framelayout_main,shop_farment,"shop");

                }else {
                    transaction.show(shop_farment);
                }

                break;
            case 3:
                image_user.setImageResource(R.mipmap.grab_treasure_icon_not_selected);
                text_user.setTextColor(Color.RED);
                if (user_fragment==null){
                    Log.e("fragment===================>","home");
                    user_fragment=new UserFragment();
                    transaction.add(R.id.framelayout_main,user_fragment,"user");
                }else {
                    transaction.show(user_fragment);
                }
                break;
        }
        transaction.commit();
    }
    private void clean(){
        image_user.setImageResource(R.mipmap.my_icon);
        text_user.setTextColor(Color.BLACK);

        image_shopping.setImageResource(R.mipmap.shopping_cart_icon);
        text_shopping.setTextColor(Color.BLACK);

        image_interrogation.setImageResource(R.mipmap.interrogation_icon);
        text_interrogation.setTextColor(Color.BLACK);

        image_home.setImageResource(R.mipmap.home_page_icon);
        text_home.setTextColor(Color.BLACK);
    }
    private void hideFragment(FragmentTransaction transaction){
        if (home_fragment!=null){
            transaction.hide(home_fragment);
        }
        if (interrogation_fragmnet!=null){
            transaction.hide(interrogation_fragmnet);
        }
        if (shop_farment!=null){
            transaction.hide(shop_farment);
        }
        if (user_fragment!=null){
            transaction.hide(user_fragment);
        }
    }
}
