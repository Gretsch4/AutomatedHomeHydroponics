package com.example.automatedhomehydroponics.ui.Logs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LogsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> logsListData;
    private ArrayList<String> logsList;

    public LogsViewModel() {
        logsListData = new MutableLiveData<>();
        logsListData.setValue(logsList);
    }

    public MutableLiveData<ArrayList<String>> getLogList()
    {
        return logsListData;
    }
}