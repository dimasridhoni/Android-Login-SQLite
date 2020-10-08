/*
 * *
 *  * Created by Dimas Ridhoni on 10/6/20 10:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/6/20 10:51 PM
 *
 */

package com.dimasridhoni.javatestk24.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dimasridhoni.javatestk24.R;
import com.dimasridhoni.javatestk24.activity.InputMemberActivity;
import com.dimasridhoni.javatestk24.activity.MainActivity;
import com.dimasridhoni.javatestk24.preferences.Preferences;

import org.w3c.dom.Text;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    TextView tvUsernameIsi, tvRoleIsi;
    ImageButton btnLogout;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        tvUsernameIsi = (TextView) root.findViewById(R.id.tvUsernameIsi);
        tvRoleIsi = (TextView) root.findViewById(R.id.tvRoleIsi);

        tvUsernameIsi.setText(Preferences.getUsernameUser(getContext()));
        tvRoleIsi.setText(Preferences.getRoleUser(getContext()));

        btnLogout = (ImageButton) root.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.clearLoggedInUser(getContext());
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        return root;
    }
}