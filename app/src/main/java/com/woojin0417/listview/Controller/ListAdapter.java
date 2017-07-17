package com.woojin0417.listview.Controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.woojin0417.listview.VO.JSONObject;
import com.woojin0417.listview.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<JSONObject> {
    ArrayList<JSONObject> products;
    Context context;
    int resource;


    public ListAdapter(Context context, int resource, ArrayList<JSONObject> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);
        }

        JSONObject product = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.url);
        Picasso.with(context).load(product.getUrl()).into(imageView);
        TextView txtRank = (TextView) convertView.findViewById(R.id.txtRank);
        txtRank.setText(product.getRank());

        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtName.setText(product.getNm());

        return convertView;
    }

}