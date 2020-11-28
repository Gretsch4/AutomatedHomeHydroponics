package com.example.automatedhomehydroponics.ui.Notes;

import android.content.Context;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.RealmHelper.Manager;
import com.example.automatedhomehydroponics.ui.Plant_Search.CustomPlantAdapter;
import com.example.automatedhomehydroponics.ui.Plant_Search.PlantSearch;
import com.example.automatedhomehydroponics.wifi.WifiModule;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.mongodb.App;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class NotesFragment extends Fragment {

    private NotesViewModel notesViewModel;
    RecyclerView rv;
    Button deleteNote;
    Button addNote;
    Context context;
    static ArrayList<Notes> notesArrayList;
    private static ArrayList<Notes> notesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notesViewModel =
                ViewModelProviders.of(this).get(NotesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.noteRv);
        deleteNote = view.findViewById(R.id.delete_note);
        addNote = view.findViewById(R.id.addNote);
        //pA =  view.findViewById(R.id.navigation_plant_search_add);

        addNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_notes_to_navigation_notes_add);
            }
        });

        context = getActivity();
        //User user = new WifiModule().getUser();
        User user = new Manager().getUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
        MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("Notes");

        RealmResultTask<MongoCursor<Document>> findTask = mongoCollection.find().iterator();
        notesArrayList = new ArrayList<Notes>();
        findTask.getAsync(new App.Callback<MongoCursor<Document>>() {
            @Override
            public void onResult(App.Result<MongoCursor<Document>> task) {
                if (task.isSuccess()) {
                    MongoCursor<Document> results = task.get();
                    Log.v("EXAMPLE", "successfully found all plants for Store 42:");
                    while (results.hasNext()) {
                        Document docu = results.next();
                        String text = docu.toString();
                        Log.v("EXAMPLE", text);
                        try {
                            JSONObject test = new JSONObject(docu.toJson());
                            String name = test.getString("title");
                            String body = test.getString("Notes");

                            Notes note = new Notes();
                            note.setTitle(name);
                            note.setNotes(body);

                            notesArrayList.add(note);
                            Log.e("EXAMPLE", "failed to find documents with: "+ notesArrayList.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    notesList = notesArrayList;
                    final CustomNotesAdapter adapter = new CustomNotesAdapter(context, notesArrayList);
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(new LinearLayoutManager(context));
                    rv.setAdapter(adapter);
                } else {
                    Log.e("EXAMPLE", "failed to find documents with: ", task.getError());
                    final CustomNotesAdapter adapter = new CustomNotesAdapter(context, notesList);
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(new LinearLayoutManager(context));
                    rv.setAdapter(adapter);

                }
                Log.e("EXAMPLE", "failed to find documents with:  FIANLLLL0"+ notesArrayList.size());
            }
        });
    }
}
