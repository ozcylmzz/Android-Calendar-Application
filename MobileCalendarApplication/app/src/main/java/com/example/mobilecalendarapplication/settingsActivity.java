package com.example.mobilecalendarapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class settingsActivity extends AppCompatActivity {
    private Button switch_btn,save_btn;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switch_btn = findViewById(R.id.switch_btn);
        spinner = findViewById(R.id.spinner2);
        save_btn = findViewById(R.id.button);

        final SharedPreferences sharedPreferences = getSharedPreferences("0",MODE_PRIVATE);
        final SharedPreferences.Editor editor1 = sharedPreferences.edit();
        final boolean isNightModeOn = sharedPreferences.getBoolean("NightMode",false);
        int sound = sharedPreferences.getInt("sound",1);
        //spinner.setOnItemSelectedListener(this);
        List<String> options = new ArrayList<String>();
        options.add("Default");
        options.add("juntos");
        options.add("doneforyou");
        options.add("inflicted");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        if(sound == 1)
            spinner.setSelection(0);
        else if(sound == 2)
            spinner.setSelection(1);
        else if(sound == 3)
            spinner.setSelection(2);
        else if(sound == 4)
            spinner.setSelection(3);

        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switch_btn.setText("Dark Modu Kapat");
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            switch_btn.setText("Dark Modu Aç");
        }

        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNightModeOn){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor1.putBoolean("NightMode", false);
                    editor1.apply();

                    switch_btn.setText("Dark Modu Kapat");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor1.putBoolean("NightMode", true);
                    editor1.apply();

                    switch_btn.setText("Dark Modu Aç");
                }

            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int option = spinner.getSelectedItemPosition()+1;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("sound", option);
                editor.apply();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ayarlar Kayıt Edildi.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                finish();

            }
        });
    }
}
