package com.takisjoe.waktu.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.takisjoe.waktu.R;

public class WaktuServiceImpl implements WaktuService {
    private final WaktuDialog waktuDialog;

    private final MutableLiveData<Integer> hour = new MutableLiveData<>();
    private final MutableLiveData<Integer> minute = new MutableLiveData<>();
    private final MutableLiveData<Long> timestamp = new MutableLiveData<>();


    public WaktuServiceImpl() {
        waktuDialog = new WaktuDialog();
    }

    @Override
    public void createDialog(Activity activity) {
        WaktuDialog waktuDialog = new WaktuDialog();
        if (activity != null) {
            waktuDialog.createDialog(activity);
            waktuDialog.getIsSaveWaktu().observe((LifecycleOwner) activity, aBoolean -> {
                if (aBoolean) {
                    hour.setValue(waktuDialog.getHour());
                    minute.setValue(waktuDialog.getMinute());
                    timestamp.setValue((((long) waktuDialog.getHour() * 60 * 60) + (waktuDialog.getMinute() * 60L)) * 1000);
                }
            });
        }
    }

    @Override
    public Dialog dialog() {
        return waktuDialog.dialog();
    }

    @Override
    public MutableLiveData<Integer> getHour() {
        return hour;
    }

    @Override
    public MutableLiveData<Integer> getMinute() {
        return minute;
    }

    @Override
    public MutableLiveData<Long> getTimestamp() {
        return timestamp;
    }
}

class WaktuDialog {
    private AlertDialog builder;
    private TimePicker timePicker;

    private int hour = 0;
    private int minute = 0;
    private final MutableLiveData<Boolean> isSaveWaktu = new MutableLiveData<>();


    public void createDialog(Activity activity) {
        builder = new AlertDialog.Builder(activity).create();

        setupDialogUI();
        controlTimePicker();
        builder.show();
    }

    private void setupDialogUI() {
        LayoutInflater inflater = builder.getLayoutInflater();
        View customDialog = inflater.inflate(R.layout.dialog_waktu, null);
        builder.setView(customDialog);
        timePicker = customDialog.findViewById(R.id.tpTime);
    }

    private void controlTimePicker() {
        isSaveWaktu.setValue(false);
        timePicker.setOnTimeChangedListener((view, hourOfDay, menit) -> {
            hour = hourOfDay;
            minute = menit;
            isSaveWaktu.setValue(true);
        });
    }

    public TimePicker setupTimePicker() {
        return timePicker;
    }

    public Dialog dialog() {
        return builder;
    }

    public void cancel() {
        builder.setCancelable(true);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public MutableLiveData<Boolean> getIsSaveWaktu() {
        return isSaveWaktu;
    }


}