/*
 * *
 *  * Created by Dimas Ridhoni on 10/6/20 10:51 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/6/20 10:51 PM
 *
 */

package com.dimasridhoni.javatestk24.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}