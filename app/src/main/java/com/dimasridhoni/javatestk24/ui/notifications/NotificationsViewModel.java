/*
 * *
 *  * Created by Dimas Ridhoni on 10/6/20 10:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/6/20 10:51 PM
 *
 */

package com.dimasridhoni.javatestk24.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}