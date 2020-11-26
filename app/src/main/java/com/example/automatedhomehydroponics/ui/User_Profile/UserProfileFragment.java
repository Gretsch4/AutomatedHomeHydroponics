package com.example.automatedhomehydroponics.ui.User_Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.wifi.WifiModule;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel userProfileViewModel;
    Button lightB;
    Button waterPumpB;
    Button airPumpB;
    static WifiModule plantData = new WifiModule();
    static int counterL = 0;
    static int counterW = 0;
    static int counterA = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                ViewModelProviders.of(this).get(UserProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user_profile, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lightB = view.findViewById(R.id.lighting_button);
        waterPumpB = view.findViewById(R.id.waterPump_button);
        airPumpB = view.findViewById(R.id.airPump_button);

        lightB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(counterL == 2)
                    counterL = 0;

                if(counterL%2 == 0)
                    plantData.sendCommand("0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0");
                else
                    plantData.sendCommand("0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0");
                counterL++;
            }
        });

        waterPumpB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(counterW == 2)
                    counterW = 0;

                if(counterW%2 == 0)
                    plantData.sendCommand("0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0");
                else
                    plantData.sendCommand("0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0");

                counterW++;
            }
        });

        airPumpB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(counterA == 2)
                    counterA = 0;

                if(counterA%2 == 0)
                    plantData.sendCommand("0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0");
                else
                    plantData.sendCommand("0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0");

                counterA++;
            }
        });
    }
}



