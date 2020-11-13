package com.example.b10z.covidinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class StatsActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private int mScrollState = -1;
    private ListView mListView;
    private RecyclerView recyclerView;
    private Button home_btn_stats;
    private RecyclerView.LayoutManager layoutManager;
    protected ArrayList<String[]> statsArray;
    private static final String TAG = "StatsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Intent in = getIntent();
        final ArrayList<String[]> worldStatsList = (ArrayList<String[]>) in.getSerializableExtra("GlobalStatsData");

        recyclerView = (RecyclerView) findViewById(R.id.statsListView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);

        home_btn_stats = (Button)findViewById(R.id.home_btn_stats);
        home_btn_stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


        constraintLayout = (ConstraintLayout) findViewById(R.id.conLayout_stats);
        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(StatsActivity.this) {
            public void onSwipeTop() {
                finish();
            }

            public void onSwipeRight() {
            }

            public void onSwipeLeft() {
            }

            public void onSwipeBottom() {
            }
        });


        RecyclerAdapter1 adapter = new RecyclerAdapter1(this, worldStatsList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);

        layoutManager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}