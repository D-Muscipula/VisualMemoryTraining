package com.example.visualmemorytraining.data.repositories;

import com.example.visualmemorytraining.data.User;
import com.example.visualmemorytraining.ui.viewmodels.MainFragmentViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseRepository {
    static String USER_KEY = "User";
    //Обновляет базу данных
    public static void addDataBaseValue(int addValue) {
        int scores = MainFragmentViewModel.getScores();
        String email = MainFragmentViewModel.getEmail();
        DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        User user = new User(mDataBase.getKey(), email, scores + addValue);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDataBase.child(uid).setValue(user);
    }

}
