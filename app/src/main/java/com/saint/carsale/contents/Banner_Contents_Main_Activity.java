package com.saint.carsale.contents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.saint.carsale.BaseActivity;
import com.saint.carsale.R;
import com.saint.carsale.utils.Constant;

/**
 * Created by zzh on 16-1-12.
 */
public class Banner_Contents_Main_Activity extends BaseActivity {
    private WebView webContent;

    @Override
    protected void setUpActionBar() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_banner_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        String  getTitle=intent.getStringExtra("inTitle");
        String getContents=intent.getStringExtra("inContents");

        webContent= (WebView) findViewById(R.id.web);
        WebSettings set = webContent.getSettings();
        set.setDefaultTextEncodingName("UTF -8");
        webContent.loadData(Constant.HTML_CSS + getContents, "text/html;charset=UTF-8", null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
