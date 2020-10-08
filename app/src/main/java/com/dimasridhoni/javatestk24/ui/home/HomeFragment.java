/*
 * *
 *  * Created by Dimas Ridhoni on 10/6/20 10:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/6/20 10:51 PM
 *
 */

package com.dimasridhoni.javatestk24.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dimasridhoni.javatestk24.R;
import com.dimasridhoni.javatestk24.activity.HomeActivity;
import com.dimasridhoni.javatestk24.activity.InputMemberActivity;
import com.dimasridhoni.javatestk24.activity.MainActivity;
import com.dimasridhoni.javatestk24.activity.UbahPasswordActivity;
import com.dimasridhoni.javatestk24.activity.ViewMemberActivity;
import com.dimasridhoni.javatestk24.activity.ViewMemberLoginActivity;
import com.dimasridhoni.javatestk24.model.Membercard;
import com.dimasridhoni.javatestk24.preferences.Preferences;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ImageButton btnViewMember, btnInputMember;
    TextView tvViewMember, tvInputMember;
    Context context;
    String role;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btnViewMember = (ImageButton) root.findViewById(R.id.btnViewMember);
        btnInputMember = (ImageButton) root.findViewById(R.id.btnInputMember);
        tvViewMember = (TextView) root.findViewById(R.id.tvViewMember);
        tvInputMember = (TextView) root.findViewById(R.id.tvInputMember);

        role = Preferences.getRoleUser(getContext());
        if (role.equals("Admin")) {

            tvViewMember.setText("View \nData Member");
            btnViewMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent HomePage = new Intent(getActivity(), ViewMemberActivity.class);
                    startActivity(HomePage);
                }
            });

            tvInputMember.setText("Input \nData Member");
            btnInputMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent HomePage = new Intent(getActivity(), InputMemberActivity.class);
                    startActivity(HomePage);
                }
            });
        } else {
            tvViewMember.setText("View \nData Member");
            btnViewMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent HomePage = new Intent(getActivity(), ViewMemberLoginActivity.class);
                    startActivity(HomePage);
                }
            });

            tvInputMember.setText("Ubah \nPassword");
            //btnInputMember.setBackgroundDrawable(R.drawable.change_password);
            btnInputMember.setImageResource(R.drawable.ubahpassword);
            btnInputMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent HomePage = new Intent(getActivity(), UbahPasswordActivity.class);
                    startActivity(HomePage);
                }
            });
        }

        return root;
    }
}