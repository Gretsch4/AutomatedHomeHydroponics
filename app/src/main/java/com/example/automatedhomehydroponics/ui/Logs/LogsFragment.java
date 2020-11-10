package com.example.automatedhomehydroponics.ui.Logs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.automatedhomehydroponics.R;

public class LogsFragment extends Fragment {

    private LogsViewModel logsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logsViewModel =
                ViewModelProviders.of(this).get(LogsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logs, container, false);
        return root;
    }
}