package com.example.quizztech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        musicCheckBox = findViewById(R.id.musicaCheckBox); // Inicializar el CheckBox

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
                    // Aquí puedes manejar la configuración de música o volumen
                    boolean isMusicEnabled = musicCheckBox.isChecked();
                    // Por ejemplo, puedes guardar este estado en preferencias compartidas
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("seleccioneTema", seleccionNombreTema);
                    intent.putExtra("musicEnabled", isMusicEnabled); // Pasar la configuración de música
                    startActivity(intent);
                }
            }
        });
    }

    // Método para actualizar la selección de temas
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
