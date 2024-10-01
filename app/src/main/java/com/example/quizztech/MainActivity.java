package com.example.quizztech;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String seleccionNombreTema = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout java = findViewById(R.id.javaLayout);
        final LinearLayout php = findViewById(R.id.phpLayout);
        final LinearLayout html = findViewById(R.id.htmlLayout);
        final LinearLayout android = findViewById(R.id.androidLayout);

        final Button startBtn = findViewById(R.id.startQuizBtn);

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionNombreTema = "java";
                updateBackgrounds(java, php, html, android);
            }
        });

        php.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionNombreTema = "php";
                updateBackgrounds(php, java, html, android);
            }
        });

        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionNombreTema = "html";
                updateBackgrounds(html, php, java, android);
            }
        });

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionNombreTema = "android";
                updateBackgrounds(android, php, java, html);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccionNombreTema.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Seleccione un Tema", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("seleccioneTema", seleccionNombreTema);
                    startActivity(intent);
                }
            }
        });
    }

    private void updateBackgrounds(LinearLayout selected, LinearLayout... others) {
        selected.setBackgroundResource(R.drawable.round_back_white_stroke10);
        for (LinearLayout layout : others) {
            layout.setBackgroundResource(R.drawable.round_back_white_stroke10);
        }
    }
}
