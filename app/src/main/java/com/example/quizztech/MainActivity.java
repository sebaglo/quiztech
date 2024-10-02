package com.example.quizztech;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    private String seleccionNombreTema = "";
    private CheckBox musicCheckBox; // Nuevo CheckBox para música

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout java = findViewById(R.id.javaLayout);
        final LinearLayout php = findViewById(R.id.phpLayout);
        final LinearLayout html = findViewById(R.id.htmlLayout);
        final LinearLayout android = findViewById(R.id.androidLayout);

        final Button startBtn = findViewById(R.id.startQuizBtn);
        musicCheckBox = findViewById(R.id.musicaCheckBox);
        mp = MediaPlayer.create(this, R.raw.emociones);
        musicCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicCheckBox.isChecked()){
                    mp.start();
                } else {
                    if(mp.isPlaying()){
                        mp.pause();
                        mp.seekTo(0);
                    }
                }
            }
        });

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionNombreTema = "java";
                updateThemeSelection(java);
            }
        });

        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionNombreTema = "php";
                updateThemeSelection(php);
            }
        });

        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionNombreTema = "html";
                updateThemeSelection(html);
            }
        });

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionNombreTema = "android";
                updateThemeSelection(android);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seleccionNombreTema.isEmpty()){
                    Toast.makeText(MainActivity.this, "Seleccione un Tema", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isMusicEnabled = musicCheckBox.isChecked();
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("seleccioneTema", seleccionNombreTema);
                    intent.putExtra("musicEnabled", isMusicEnabled);
                    startActivity(intent);
                }
            }
        });
    }

    // selección de temas
    private void updateThemeSelection(LinearLayout selectedLayout) {
        selectedLayout.setBackgroundResource(R.drawable.round_back_white_stroke10);
        resetOtherLayouts(selectedLayout);
    }

    private void resetOtherLayouts(LinearLayout selectedLayout) {
        if (selectedLayout != findViewById(R.id.javaLayout)) {
            findViewById(R.id.javaLayout).setBackgroundResource(R.drawable.round_back_white_stroke10);
        }
        if (selectedLayout != findViewById(R.id.phpLayout)) {
            findViewById(R.id.phpLayout).setBackgroundResource(R.drawable.round_back_white_stroke10);
        }
        if (selectedLayout != findViewById(R.id.htmlLayout)) {
            findViewById(R.id.htmlLayout).setBackgroundResource(R.drawable.round_back_white_stroke10);
        }
        if (selectedLayout != findViewById(R.id.androidLayout)) {
            findViewById(R.id.androidLayout).setBackgroundResource(R.drawable.round_back_white_stroke10);
        }
    }
}
