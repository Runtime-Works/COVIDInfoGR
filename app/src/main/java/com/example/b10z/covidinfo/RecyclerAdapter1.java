package com.example.b10z.covidinfo;

import android.content.Context;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter1  extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars

    private ArrayList<String[]> content = new ArrayList<String[]>();
    private Context mContext;

    public RecyclerAdapter1(Context context, ArrayList<String[]> rows) {

        content = rows;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_world_stats_adapter, parent, false);

        return new ViewHolder(view);
    }





    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Log.d(TAG, String.valueOf(position));
        //get the persons information
        String rank = content.get(position)[0];
        String country = content.get(position)[1];
        String total_cases = content.get(position)[2];
        String new_cases = content.get(position)[3];
        String total_deaths = content.get(position)[4];
        String new_deaths = content.get(position)[5];
        String total_recovered = content.get(position)[6];
        String new_recovered = content.get(position)[7];
        String active_cases = content.get(position)[8];
        String active_seriousCritical = content.get(position)[9];
        String TotCases1Mpop = content.get(position)[10];
        String Deaths1Mpop = content.get(position)[11];
        String TotalTests = content.get(position)[12];
        String Tests1Mpop = content.get(position)[13];
        String Population = content.get(position)[14];
        String Continent = content.get(position)[15];
        String CaseeveryXppl = content.get(position)[16];
        String DeatheveryXppl = content.get(position)[17];
        String TesteveryXppl = content.get(position)[18];

        holder.txtViewRank.setText(rank);
        holder.txtViewCountry.setText(country);
        holder.txtViewTotalCases.setText(total_cases);
        holder.txtViewNewCases.setText(new_cases);
        holder.txtViewTotalDeaths.setText(total_deaths);
        holder.txtViewNewDeaths.setText(new_deaths);
        holder.txtViewTotalRecovered.setText(total_recovered);
        holder.txtViewNewRecovered.setText(new_recovered);
        holder.txtViewActiveCases.setText(active_cases);
        holder.txtViewActiveSeriousCritical.setText(active_seriousCritical);
        holder.txtViewTotCases1Mpop.setText(TotCases1Mpop);
        holder.txtViewDeaths1Mpop.setText(Deaths1Mpop);
        holder.txtViewTotalTests.setText(TotalTests);
        holder.txtViewTests1Mpop.setText(Tests1Mpop);
        holder.txtViewPopulation.setText(Population);
        holder.txtViewContinent.setText(Continent);
        holder.txtView1CaseeveryXppl.setText(CaseeveryXppl);
        holder.txtView1DeatheveryXppl.setText(DeatheveryXppl);
        holder.txtView1TesteveryXppl.setText(TesteveryXppl);

    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView txtViewRank,txtViewCountry,txtViewTotalCases, txtViewNewCases, txtViewTotalDeaths, txtViewNewDeaths, txtViewTotalRecovered, txtViewNewRecovered, txtViewActiveCases, txtViewActiveSeriousCritical,txtViewTotCases1Mpop,txtViewDeaths1Mpop,txtViewTotalTests,txtViewTests1Mpop,txtViewPopulation,txtViewContinent,txtView1CaseeveryXppl,txtView1DeatheveryXppl,txtView1TesteveryXppl;

        public ViewHolder(View itemView) {
            super(itemView);
            txtViewRank = (TextView)itemView.findViewById(R.id.txtViewRank);
            txtViewCountry = (TextView)itemView.findViewById(R.id.txtViewCountry);
            txtViewTotalCases  = (TextView)itemView.findViewById(R.id.txtViewTotalCases);
            txtViewNewCases= (TextView)itemView.findViewById(R.id.txtViewNewCases);
            txtViewTotalDeaths = (TextView)itemView.findViewById(R.id.txtViewTotalDeaths);
            txtViewNewDeaths = (TextView)itemView.findViewById(R.id.txtViewNewDeaths);
            txtViewTotalRecovered= (TextView)itemView.findViewById(R.id.txtViewTotalRecovered);
            txtViewNewRecovered= (TextView)itemView.findViewById(R.id.txtViewNewRecovered);
            txtViewActiveCases= (TextView)itemView.findViewById(R.id.txtViewActiveCases);
            txtViewActiveSeriousCritical= (TextView)itemView.findViewById(R.id.txtViewActiveSeriousCritical);
            txtViewTotCases1Mpop= (TextView)itemView.findViewById(R.id.txtViewTotCases1Mpop);
            txtViewDeaths1Mpop= (TextView)itemView.findViewById(R.id.txtViewDeaths1Mpop);
            txtViewTotalTests= (TextView)itemView.findViewById(R.id.txtViewTotalTests);
            txtViewTests1Mpop= (TextView)itemView.findViewById(R.id.txtViewTests1Mpop);
            txtViewPopulation= (TextView)itemView.findViewById(R.id.txtViewPopulation);
            txtViewContinent= (TextView)itemView.findViewById(R.id.txtViewContinent);
            txtView1CaseeveryXppl= (TextView)itemView.findViewById(R.id.txtView1CaseeveryXppl);
            txtView1DeatheveryXppl= (TextView)itemView.findViewById(R.id.txtView1DeatheveryXppl);
            txtView1TesteveryXppl= (TextView)itemView.findViewById(R.id.txtView1TesteveryXppl);

        }
    }
}