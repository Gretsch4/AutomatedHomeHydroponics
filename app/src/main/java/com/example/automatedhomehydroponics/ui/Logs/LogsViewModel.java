package com.example.automatedhomehydroponics.ui.Logs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LogsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Logs");
    }

    public LiveData<String> getText() {
        return mText;
    }
}