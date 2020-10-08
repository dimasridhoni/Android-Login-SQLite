/*
 * *
 *  * Created by Dimas Ridhoni on 10/6/20 10:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/6/20 10:51 PM
 *
 */

package com.dimasridhoni.javatestk24.ui.dashboard;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dimasridhoni.javatestk24.R;
import com.synnapps.carouselview.CarouselView;
import com.dimasridhoni.javatestk24.R;
import com.synnapps.carouselview.ImageListener;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.k24_1, R.drawable.k24_2, R.drawable.k24_3, R.drawable.k24_4,  R.drawable.bannerk24};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        return root;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}