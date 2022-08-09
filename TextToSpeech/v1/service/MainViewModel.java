package com.takisjoe.texttospeech.ui.main;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.util.Locale;


public class MainViewModel extends ViewModel {
    private TextToSpeech toSpeech;
    private TextToSpeech.OnInitListener listener;
    // TODO: Implement the ViewModel

    public void root(Context context) {
        toSpeech = new TextToSpeech(context, listener);
        Voice voice = new Voice("Vin", Locale.getDefault(),Voice.QUALITY_VERY_LOW,Voice.LATENCY_NORMAL,true,null);
        toSpeech.setVoice(voice);
    }

    public void setText(@NonNull String toString) {
        if (toString.isEmpty()) {
            toSpeech.speak("Maaf, saya tidak bisa baca pikiranmu", TextToSpeech.QUEUE_FLUSH, null);

        } else {
            toSpeech.speak(toString, TextToSpeech.QUEUE_ADD, null);

        }
    }


}