package com.example.automatedhomehydroponics.ui.Notes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.RealmHelper.Manager;

import org.bson.BsonObjectId;
import org.bson.Document;

import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.InsertOneResult;

public class NotesAddFragment extends Fragment {
    public NotesAddFragment(){}
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //plantSearchViewModel =
        //ViewModelProviders.of(this).get(PlantSearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notes_add, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView title = view.findViewById(R.id.editTextTextPersonName);
        final TextView body = view.findViewById(R.id.editTextTextMultiLine);
        final Button add = view.findViewById(R.id.button_note_add);


        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                User user = new Manager().getUser();
                MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
                MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
                MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("Notes");

                Document Note = new Document("title", title.getText().toString())
                        .append("Notes", body.getText().toString());


                mongoCollection.insertOne(Note).getAsync(new App.Callback<InsertOneResult>() {
                    @Override
                    public void onResult(App.Result<InsertOneResult> task) {
                        if (task.isSuccess()) {
                            BsonObjectId insertedId = task.get().getInsertedId().asObjectId();
                            Log.v("EXAMPLE", "successfully inserted a document with id " + insertedId);
                            Navigation.findNavController(v).navigate(R.id.action_navigation_notes_add_to_navigation_notes);
                        } else {
                            Log.e("EXAMPLE", "failed to insert document with: ", task.getError());
                        }
                    }
                });
            }
        });
    }
}
