package com.example.b10z.covidinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class WorldStatsAdapter extends ArrayAdapter<WorldStats> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;


    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView txtViewRank,txtViewCountry,txtViewTotalCases, txtViewNewCases, txtViewTotalDeaths, txtViewNewDeaths, txtViewTotalRecovered, txtViewNewRecovered, txtViewActiveCases, txtViewActiveSeriousCritical,txtViewTotCases1Mpop,txtViewDeaths1Mpop,txtViewTotalTests,txtViewTests1Mpop,txtViewPopulation,txtViewContinent,txtView1CaseeveryXppl,txtView1DeatheveryXppl,txtView1TesteveryXppl;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public WorldStatsAdapter(Context context, int resource, ArrayList<WorldStats> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String country = getItem(position).getCountry();
        String total_cases = getItem(position).getTotal_cases();
        String new_cases = getItem(position).getNew_cases();
        String new_recovered = getItem(position).getNew_recovered();
        String active_cases = getItem(position).getActive_cases();
        String active_seriousCritical = getItem(position).getActive_seriousCritical();
        String TotCases1Mpop = getItem(position).getTotCases1Mpop();
        String Deaths1Mpop = getItem(position).getDeaths1Mpop();
        String TotalTests = getItem(position).getTotalTests();
        String Tests1Mpop = getItem(position).getTests1Mpop();
        String Population = getItem(position).getPopulation();
        String Continent = getItem(position).getContinent();
        String CaseeveryXppl = getItem(position).getCaseeveryXppl();
        String DeatheveryXppl = getItem(position).getDeatheveryXppl();
        String TesteveryXppl = getItem(position).getTesteveryXppl();
        String total_recovered = getItem(position).getTotal_recovered();
        String total_deaths = getItem(position).getTotal_deaths();
        String new_deaths = getItem(position).getNew_deaths();


        //Create the person object with the information
        //WorldStats all = new WorldStats(nameGR,nameEN,total_cases , new_cases , total_deaths, new_deaths);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.txtViewCountry= (TextView) convertView.findViewById(R.id.txtViewCountry);
            holder.txtViewTotalCases = (TextView) convertView.findViewById(R.id.txtViewTotalCases);
            holder.txtViewNewCases = (TextView) convertView.findViewById(R.id.txtViewNewCases);
            holder.txtViewTotalDeaths = (TextView) convertView.findViewById(R.id.txtViewTotalDeaths);
            holder.txtViewNewDeaths = (TextView) convertView.findViewById(R.id.txtViewNewDeaths);
            holder.txtViewTotalRecovered = (TextView) convertView.findViewById(R.id.txtViewTotalRecovered);
            holder.txtViewNewRecovered = (TextView) convertView.findViewById(R.id.txtViewNewRecovered);
            holder.txtViewActiveCases = (TextView) convertView.findViewById(R.id.txtViewActiveCases) ;
            holder.txtViewActiveSeriousCritical = (TextView) convertView.findViewById(R.id.txtViewActiveSeriousCritical);
            holder.txtViewTotCases1Mpop = (TextView) convertView.findViewById(R.id.txtViewTotCases1Mpop) ;
            holder.txtViewDeaths1Mpop = (TextView) convertView.findViewById(R.id.txtViewDeaths1Mpop);
            holder.txtViewTotalTests = (TextView) convertView.findViewById(R.id.txtViewTotalTests);;
            holder.txtViewTests1Mpop= (TextView) convertView.findViewById(R.id.txtViewTests1Mpop);
            holder.txtViewPopulation = (TextView) convertView.findViewById(R.id.txtViewPopulation);;
            holder.txtViewContinent = (TextView) convertView.findViewById(R.id.txtViewContinent);;
            holder.txtView1CaseeveryXppl = (TextView) convertView.findViewById(R.id.txtView1CaseeveryXppl);;
            holder.txtView1DeatheveryXppl = (TextView) convertView.findViewById(R.id.txtView1DeatheveryXppl);
            holder.txtView1TesteveryXppl= (TextView) convertView.findViewById(R.id.txtView1TesteveryXppl);;


            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

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


        return convertView;
    }
}

