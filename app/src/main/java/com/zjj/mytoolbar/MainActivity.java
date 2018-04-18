package com.zjj.mytoolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.zjj.mytoolbarlibrary.MyToolBar;

public class MainActivity extends AppCompatActivity implements MyToolBar.OnMyToolBarClickLisener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyToolBar toolbar = (MyToolBar) findViewById(R.id.toolbar);
//        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
//        toolbar1.setTitle(null);
        toolbar.setOnMyToolBarClickLisener(this);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public void onLeftClick() {
        Toast.makeText(this,"返回",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightClick() {
        Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTitleClick() {
        Toast.makeText(this,"标题",Toast.LENGTH_SHORT).show();
    }
}
