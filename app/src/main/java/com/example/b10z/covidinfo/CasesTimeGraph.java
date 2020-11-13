package com.example.b10z.covidinfo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CasesTimeGraph extends Fragment {


    public CasesTimeGraph (){
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scroller_fragment,container,false);

        final ArrayList<String[]> Timelinelist = (ArrayList<String[]>)getArguments().getSerializable("datasets");


        BarChart chartSample = (BarChart) view.findViewById(R.id.chart);
        List<BarEntry> TimelineEntries = new ArrayList<BarEntry>();

        for (int i = 0 ; i<Timelinelist.size() - 1 ; i++){
            System.out.println(Arrays.toString(Timelinelist.get(i)));        }

        for (int i = 0; i<Timelinelist.get(0).length; i++ ) {
            if (i > 2) {
                TimelineEntries.add(new BarEntry((float)(i-2),Float.valueOf(Timelinelist.get(1)[i])));
            }
        }

        BarDataSet set = new BarDataSet(TimelineEntries, "Cases/Time");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width


        XAxis xAxis = chartSample.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chartSample.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(Timelinelist.get(0)));
        chartSample.getAxisLeft().setTextColor(Color.WHITE); // left y-axis
        chartSample.getXAxis().setTextColor(Color.WHITE);
        chartSample.getLegend().setTextColor(Color.WHITE);
        chartSample.setData(data);
        chartSample.setFitBars(true); // make the x-axis fit exactly all bars
        chartSample.invalidate(); // refresh


        return view;
    }



}

