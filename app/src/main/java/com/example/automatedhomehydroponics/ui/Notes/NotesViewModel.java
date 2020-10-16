package com.example.automatedhomehydroponics.ui.Notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Notes");
    }

    public LiveData<String> getText() {
        return mText;
    }
}