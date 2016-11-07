package com.jorgereina.kayak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class KayakAdapter extends BaseAdapter {


    private static final String BASE_IMAGE_URL = "http://www.kayak.com";
    private Context context;
    private List<Airline> airlines;

    public KayakAdapter(Context context, List<Airline> airlines) {
        this.context = context;
        this.airlines = airlines;
    }

    @Override
    public int getCount() {
        return airlines.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Airline airline = airlines.get(i);
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.row, viewGroup, false);

            viewHolder.airlineTv = (TextView) view.findViewById(R.id.airline_name_tv);
            viewHolder.airlineLogo = (ImageView) view.findViewById(R.id.airline_logo_iv);
            view.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.airlineTv.setText(airline.getName());
        Picasso.with(context)
                .load(BASE_IMAGE_URL + pictureAvailable(airline))
                .resize(80,80)
                .into(viewHolder.airlineLogo);
        return view;
    }

    static class ViewHolder{
        TextView airlineTv;
        ImageView airlineLogo;
    }

    private String pictureAvailable(Airline airline){
        if (airline.getLogoURL()!=null){
            return airline.getLogoURL();
        }
        else return "R.mipmap.ic_launcher";
    }
}
