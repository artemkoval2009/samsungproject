package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);finish();
                }catch (Exception e) {

                }
            }
        });

        //Кнопки-перебросчики - начало
        Button button_jazz = (Button)findViewById(R.id.button_jazz);
        button_jazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Jazz.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button button_rock = (Button)findViewById(R.id.button_rock);
        button_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Rock.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button button_hiphop = (Button)findViewById(R.id.button_hip_hop);
        button_hiphop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, HipHop.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button button_pop = (Button)findViewById(R.id.button_pop);
        button_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Pop.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //Кнопки-перебросчики - конец
    }
    // Системная для выхода по двойному нажатию
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}