package com.example.visualmemorytraining;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = this.getSharedPreferences("Scores", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);


    }

    public static SharedPreferences getPref() {
        return pref;
    }
}
