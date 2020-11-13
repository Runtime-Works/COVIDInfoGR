package com.example.b10z.covidinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class DiagramSwipe extends FragmentStatePagerAdapter {


    ArrayList<String[]> fresh_data;


    public DiagramSwipe(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("Current Slide is :" + position);
        Bundle new_b = new Bundle();
        new_b.putSerializable("datasets", fresh_data);
        if (position == 0) {
            ScrollerFragment new_fragment = new ScrollerFragment();
            new_fragment.setArguments(new_b);
            return new_fragment;
        }else if (position == 1){
            DeathsTimeGraph new_graph = new DeathsTimeGraph();
            new_graph.setArguments(new_b);
            return new_graph;
        }else {
            CasesTimeGraph new_graph = new CasesTimeGraph();
            new_graph.setArguments(new_b);
            return new_graph;
        }



    }

    @Override
    public int getCount() {
        return 3;
    }

    public void storeData(ArrayList<String[]> array){
        fresh_data = array;
    }
}
