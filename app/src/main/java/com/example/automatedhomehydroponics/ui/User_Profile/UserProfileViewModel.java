package com.example.automatedhomehydroponics.ui.User_Profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.automatedhomehydroponics.wifi.WifiModule;

public class UserProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //private WifiModule qwe = new WifiModule();
    public UserProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("User Profile");
    }

    public LiveData<String> getText() {
        return mText;
    }
}