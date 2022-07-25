package com.takisjoe.waktu.service;

import android.app.Activity;
import android.app.Dialog;

import androidx.lifecycle.MutableLiveData;

public interface WaktuService {

    void createDialog(Activity activity);

    Dialog dialog();

    MutableLiveData<Integer> getHour();

    MutableLiveData<Integer> getMinute();

    MutableLiveData<Long> getTimestamp();
}
