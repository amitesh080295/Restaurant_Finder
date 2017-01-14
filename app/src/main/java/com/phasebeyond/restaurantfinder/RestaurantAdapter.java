package com.phasebeyond.restaurantfinder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Restaurant>{
    public RestaurantAdapter(Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_restaurant, parent, false);
        }

        //ImageView restaurantPhotoImagineView = (ImageView) convertView.findViewById(R.id.restaurantPhotoImageView);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView costTextView = (TextView) convertView.findViewById(R.id.costTextView);
        TextView hoursTextView = (TextView) convertView.findViewById(R.id.hoursTextView);

        Restaurant restaurant = getItem(position);

        /*Glide.with(restaurantPhotoImagineView.getContext())
                .load(restaurant.getPhotoUrl())
                .into(restaurantPhotoImagineView);*/

        nameTextView.setText(restaurant.getName());
        costTextView.setText(restaurant.getCost());
        hoursTextView.setText(restaurant.getHours());

        return convertView;
    }
}




