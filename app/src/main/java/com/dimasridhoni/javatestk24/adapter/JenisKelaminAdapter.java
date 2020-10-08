/*
 * *
 *  * Created by Dimas Ridhoni on 10/7/20 4:57 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/7/20 4:57 PM
 *
 */

package com.dimasridhoni.javatestk24.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dimasridhoni.javatestk24.R;
import com.dimasridhoni.javatestk24.model.JenisKelamin;

import java.util.List;

public class JenisKelaminAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<JenisKelamin> item;

    public JenisKelaminAdapter(Activity activity, List<JenisKelamin> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_jenis_kelamin, null);

        TextView tvJenisKelamin = (TextView) convertView.findViewById(R.id.jenisKelamin);

        JenisKelamin jenisKelamin;
        jenisKelamin = item.get(position);

        tvJenisKelamin.setText(jenisKelamin.getJenisKelamin());

        return convertView;
    }

}
