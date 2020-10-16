package com.example.automatedhomehydroponics.ui.User_Profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UserProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("User Profile");
    }

    public LiveData<String> getText() {
        return mText;
    }
}