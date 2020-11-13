package com.example.b10z.covidinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphsActivity extends AppCompatActivity {

    private ConstraintLayout relativeLayout; //this is ConstraintLayout xD
    private ViewPager viewPager;
    private DiagramSwipe diagramSwipe;
    private Button home_btn_graph;
    private Button drawer_bringer;
    private TextView txtViewTotalCases, txtViewNewCases, txtViewTotalDeaths, txtViewNewDeaths;
    private static final String TAG = "GraphsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        Intent in = getIntent();
        final ArrayList<String[]> Timelinelist =(ArrayList<String[]>) in.getSerializableExtra("TimelineData");
        final ArrayList<String[]> Caseslist = (ArrayList<String[]>) in.getSerializableExtra("CasesData");


        viewPager = findViewById(R.id.pager);
        ListView mListView = (ListView) findViewById(R.id.listView);
        txtViewTotalCases = (TextView) findViewById(R.id.txtViewTotalCases);
        txtViewNewCases = (TextView) findViewById(R.id.txtViewNewCases);
        txtViewTotalDeaths = (TextView) findViewById(R.id.txtViewTotalDeaths);
        txtViewNewDeaths = (TextView) findViewById(R.id.txtViewNewDeaths);

        diagramSwipe = new DiagramSwipe(getSupportFragmentManager());

        diagramSwipe.storeData(Timelinelist);
        viewPager.setAdapter(diagramSwipe);

        home_btn_graph = (Button)findViewById(R.id.home_btn_graph);
        home_btn_graph.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });



        relativeLayout = (ConstraintLayout) findViewById(R.id.chartLayout);
        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(GraphsActivity.this) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {

            }
            public void onSwipeLeft() {

            }
            public void onSwipeBottom() {
                finish();
            }

        });


        ArrayList<GreeceData> greeceList  = new ArrayList<>();
        GreeceData[] obj = new GreeceData[Caseslist.size()];
        int totalCases = 0, newCases = 0, totalDeaths = 0, newDeaths = 0;
        for (int i = 0 ; i<Caseslist.size() ; i ++){
            System.out.println("String : "+ Arrays.toString(Caseslist.get(i)));
            System.out.println("Length : " + Caseslist.get(i).length);
            obj[i] = new GreeceData(Caseslist.get(i)[0], Caseslist.get(i)[1], Caseslist.get(i)[3], Caseslist.get(i)[Caseslist.get(i).length - 2], Caseslist.get(i)[4],Caseslist.get(i)[Caseslist.get(i).length - 1]);
            greeceList.add(obj[i]);
            if(!Caseslist.get(i)[3].isEmpty())
                totalCases = totalCases + (int)Float.parseFloat(Caseslist.get(i)[3]);
            if(!Caseslist.get(i)[Caseslist.get(i).length - 2].isEmpty())
                newCases = newCases + (int)Float.parseFloat(Caseslist.get(i)[Caseslist.get(i).length - 2]);
            if(!Caseslist.get(i)[4].isEmpty())
                totalDeaths = totalDeaths + (int)Float.parseFloat(Caseslist.get(i)[4]);
            if(!Caseslist.get(i)[Caseslist.get(i).length - 1].isEmpty())
                newDeaths = newDeaths + (int)Float.parseFloat(Caseslist.get(i)[Caseslist.get(i).length - 1]);
        }
        Log.d("HEYHEY Total Cases: ",Integer.toString(totalCases) );
        txtViewTotalCases.setText(Integer.toString(totalCases));
        txtViewNewCases.setText(Integer.toString(newCases));
        txtViewTotalDeaths.setText(Integer.toString(totalDeaths));
        txtViewNewDeaths.setText(Integer.toString(newDeaths));
        ListAdapter adapter = new ListAdapter(this, R.layout.custom_list, greeceList);
        mListView.setAdapter(adapter);

    }


    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
