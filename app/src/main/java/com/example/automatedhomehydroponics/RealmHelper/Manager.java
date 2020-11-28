package com.example.automatedhomehydroponics.RealmHelper;

import com.example.automatedhomehydroponics.data.Result;
import com.example.automatedhomehydroponics.data.model.LoggedInUser;

import io.realm.mongodb.User;

public class Manager {
    private static User user;

    public Manager(User user)
    {
        this.setUser(user);
    }

    public Manager()
    {
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Manager.user = user;
    }

}
