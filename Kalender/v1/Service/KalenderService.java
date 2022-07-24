package com.takisjoe.kalender.service;

import android.app.Activity;
import android.app.Dialog;

import androidx.lifecycle.MutableLiveData;

public interface KalenderService {

    /**
     * Panggil jika ingin menggunakan kalender dalam bentuk tampilan mengambang
     * @param activity digunakan untuk mengakses Dialog Fragment
     */
    void createDialog(Activity activity);

    /**
     *
     * @return Dialog untuk control seluruh fungsi setelah memanggil createDialog(activity)
     */
    Dialog dialog();

    /**
     *
     * @return untuk mendapatkan bulan ex: Juli
     */
    MutableLiveData<String>  getsBulan();

    /**
     *
     * @return untuk mendapatkan tanggal ex: 25
     */
    MutableLiveData<String> getTanggal();

    /**
     *
     * @return untuk mendapatkan bulan ex: 7
     */
    MutableLiveData<String> getBulan();

    /**
     * @return untuk mendapatkan tahun ex: 2022
     */
    MutableLiveData<String> getTahun();

    /**
     *
     * @return untuk mendapatkan TimeInMillis ex: 1661376273071
     */
    Long getTimestamp();

    /**
     *
     * @return untuk mendapatkan Timezone ex: Waktu Indonesia Barat
     */
    String getTimeZone();

}
