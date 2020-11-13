package com.example.b10z.covidinfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<GreeceData> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView nameGR, nameEN, total_cases, new_cases;
        TextView total_deaths, new_deaths;

    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ListAdapter(Context context, int resource, ArrayList<GreeceData> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String nameGR = getItem(position).getNameGR();
        String nameEN = getItem(position).getNameEN();
        String total_cases = getItem(position).getTotal_cases();
        String new_cases = getItem(position).getNew_cases();
        String total_deaths = getItem(position).getTotal_deaths();
        String new_deaths = getItem(position).getNew_deaths();


        //Create the person object with the information
        GreeceData gr = new GreeceData(nameGR,nameEN,total_cases , new_cases , total_deaths, new_deaths);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.nameGR = (TextView) convertView.findViewById(R.id.txtViewNameGR);
            holder.total_cases = (TextView) convertView.findViewById(R.id.txtViewTotal_Cases);
            holder.new_cases = (TextView) convertView.findViewById(R.id.txtViewNew_Cases);
            holder.total_deaths = (TextView) convertView.findViewById(R.id.txtViewTotal_Deaths);
            holder.new_deaths = (TextView) convertView.findViewById(R.id.txtViewNew_Deaths);


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
        //holder.total_deaths.setText(gr.getTotal_deaths());
        Log.d("HEYHEY",gr.getTotal_deaths());
        holder.nameGR.setText(gr.getNameGR());
        holder.new_cases.setText("New Cases: " + gr.getNew_cases());
        holder.total_cases.setText("Total Cases: " +gr.getTotal_cases());
        holder.new_deaths.setText("New Deaths: " +gr.getNew_deaths());
        holder.total_deaths.setText("Total Deaths: " +gr.getTotal_deaths());


        return convertView;
    }
}
