package com.example.automatedhomehydroponics.data;

import android.util.Log;

import com.example.automatedhomehydroponics.RealmHelper.Manager;
import com.example.automatedhomehydroponics.data.model.LoggedInUser;

import org.bson.Document;

import java.io.IOException;

import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private boolean bool;
    public Result<LoggedInUser> resu;
    static int counter = 0;

    public Result<LoggedInUser> login(String username, String password) {
        try {
            User user = new Manager().getUser();
            MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
            MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
            MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("LoginInfo");

            Document Note = new Document("email", username)
                    .append("password", password);
            final LoggedInUser fakeUser = new LoggedInUser(username,password);
            mongoCollection.findOne(Note).getAsync(new App.Callback<Document>() {
                @Override
                public void onResult(App.Result<Document> task) {
                    if (task.isSuccess()) {
                        bool = true;
                        new Result.Success<>(fakeUser);
                        Log.v("EXAMPLE", "successfully found a document: " + task.get());
                    } else {
                        bool = false;
                        Log.e("EXAMPLE", "failed to find document with: ", task.getError());
                    }
                }

            });
            //while(counter++ < 10000000 || bool == false){}
            //Log.v("EXAMPLE", "successfully a document with id " + bool);
            if(bool != true)
                return new Result.Success<>(fakeUser);
            else
                return new Result.Error(new IOException("Error logging in"));
            
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }
    public void logout() {

    }
}