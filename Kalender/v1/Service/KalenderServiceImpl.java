package com.takisjoe.kalender.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.takisjoe.kalender.R;

import java.util.Calendar;
import java.util.TimeZone;

public class KalenderServiceImpl implements KalenderService {
    private final KalenderDialog kalenderDialog;
    private final MutableLiveData<String> mTanggal = new MutableLiveData<>();
    private final MutableLiveData<String> mBulan = new MutableLiveData<>();
    private final MutableLiveData<String> msBulan = new MutableLiveData<>();
    private final MutableLiveData<String> mTahun = new MutableLiveData<>();

    public KalenderServiceImpl() {
        kalenderDialog = new KalenderDialog();
    }

    @Override
    public void createDialog(Activity activity) {
        kalenderDialog.createDialog(activity);
        if (activity != null) {
            kalenderDialog.getIsSave().observe((LifecycleOwner) activity, aBoolean -> {
                if (aBoolean) {
                    Log.i("LogShow", "isinya : " + kalenderDialog.getTahun());
                    mTanggal.setValue(kalenderDialog.getTanggal());
                    mBulan.setValue(kalenderDialog.getBulan());
                    mTahun.setValue(kalenderDialog.getTahun());
                }
            });
        }
    }

    @Override
    public Dialog dialog() {
        return kalenderDialog.dialog();
    }

    @Override
    public MutableLiveData<String> getTanggal() {
        return mTanggal;
    }

    @Override
    public MutableLiveData<String> getBulan() {
        return mBulan;
    }

    @Override
    public MutableLiveData<String> getsBulan() {
        String s;
        switch (kalenderDialog.getBulan()) {
            case "1":
                s = "Januari";
                break;
            case "2":
                s = "Februari";
                break;
            case "3":
                s = "Maret";
                break;
            case "4":
                s = "April";
                break;
            case "5":
                s = "Mei";
                break;
            case "6":
                s = "Juni";
                break;
            case "7":
                s = "Juli";
                break;
            case "8":
                s = "Agustus";
                break;
            case "9":
                s = "September";
                break;
            case "10":
                s = "Oktober";
                break;
            case "11":
                s = "November";
                break;
            case "12:":
                s = "Desember";
                break;
            default:
                s = null;
        }
        msBulan.setValue(s);
        return msBulan;
    }

    @Override
    public MutableLiveData<String> getTahun() {
        return mTahun;
    }

    @Override
    public Long getTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(kalenderDialog.getTahun()), Integer.parseInt(kalenderDialog.getBulan()), Integer.parseInt(kalenderDialog.getTanggal()));
        calendar.setTimeZone(TimeZone.getTimeZone("Indonesia"));
        return calendar.getTimeInMillis();
    }

    @Override
    public String getTimeZone() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(kalenderDialog.getTahun()), Integer.parseInt(kalenderDialog.getBulan()), Integer.parseInt(kalenderDialog.getTanggal()));
        return calendar.getTimeZone().getDisplayName();
    }
}

class KalenderDialog {

    private AlertDialog builder;
    private DatePicker datePicker;

    private String tahun;
    private String bulan;
    private String tanggal;

    private final MutableLiveData<Boolean> isSave = new MutableLiveData<>();


    public void createDialog(Activity activity) {
        builder = new AlertDialog.Builder(activity).create();
        setupDialogUI(builder);
        builder.show();
        controlDatePicker();
        cancel();
    }

    public Dialog dialog() {
        return builder;
    }

    private void setupDialogUI(@NonNull AlertDialog builder) {
        LayoutInflater inflater = builder.getLayoutInflater();
        View customDialog = inflater.inflate(R.layout.dialog_kalender, null);
        builder.setView(customDialog);
        datePicker = (DatePicker) customDialog.findViewById(R.id.dpDate);
    }

    public void controlDatePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
                tahun = String.valueOf(year);
                bulan = String.valueOf(monthOfYear + 1);
                tanggal = String.valueOf(dayOfMonth);

                isSave.setValue(true);
                dialog().dismiss();
            });
        }
    }

    public void cancel() {
        dialog().setCancelable(true);
    }

    public String getTahun() {
        return tahun;
    }

    public String getBulan() {
        return bulan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public MutableLiveData<Boolean> getIsSave() {
        return isSave;
    }
}