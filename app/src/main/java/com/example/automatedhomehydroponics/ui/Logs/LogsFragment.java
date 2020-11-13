package com.example.automatedhomehydroponics.ui.Logs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.RealmHelper.RealmManager;
import com.example.automatedhomehydroponics.ui.Plant_Search.Plant;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class LogsFragment extends Fragment {

    private LogsViewModel logsViewModel;
    Realm realm;
    RecyclerView rv;
    RealmManager manager;
    Context context = getActivity();
    RealmChangeListener realmChangeListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logsViewModel =
                ViewModelProviders.of(this).get(LogsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_logs, container, false);

        rv = root.findViewById(R.id.log_RV);
        manager = new RealmManager(realm);
        manager.selectFromDB();

        CustomAdapter adapter = new CustomAdapter(context, manager.justRefresh());
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(adapter);

        update();
        return root;
    }

    private void saveData(){
        realm.executeTransactionAsync(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                Number maxID = realm.where(Plant.class).max("plantId");

                int newKey = (maxID == null) ? 1 : maxID.intValue()+1;

                Plant plant = realm.createObject(Plant.class,newKey);
                plant.setAirTemp(651.365);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess(){
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }

        }, new Realm.Transaction.OnError() {
            @Override
            public  void onError(Throwable error) {
                Toast.makeText(context, "Input Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(){
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object O){
                CustomAdapter adapter = new CustomAdapter(context, manager.justRefresh());
            }
        };
        realm.addChangeListener(realmChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}
