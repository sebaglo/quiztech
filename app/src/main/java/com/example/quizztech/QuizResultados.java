package com.example.quizztech;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class QuizResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_resultados);

        // Inicializar los componentes de la interfaz
        final AppCompatButton startNewBtn = findViewById(R.id.btnVolverjugar);
        final Button exitButton = findViewById(R.id.btnSalir);
        final TextView correctAnswer = findViewById(R.id.respuestaCorrecta);
        final TextView incorrectAnswers = findViewById(R.id.respuestaIncorrecta);
        final TextView finalScore = findViewById(R.id.textPuntajeFinal);

        // Obtener respuestas correctas e incorrectas del Intent
        final int getCorrectAnswer = getIntent().getIntExtra("Respuestas Correctas", 0);
        final int getIncorrectAnswer = getIntent().getIntExtra("Respuestas Incorrectas", 0);
        final int totalQuestions = getCorrectAnswer + getIncorrectAnswer;

        // Calcular el puntaje final
        int finalScorePercentage = (int) (((double) getCorrectAnswer / totalQuestions) * 100);

        // Mostrar las respuestas y el puntaje final en los TextViews
        correctAnswer.setText("Respuestas Correctas: " + getCorrectAnswer);
        incorrectAnswers.setText("Respuestas Incorrectas: " + getIncorrectAnswer);
        finalScore.setText("Puntaje Final: " + finalScorePercentage + "%");

        // Configurar el botón para reiniciar el quiz
        startNewBtn.setOnClickListener(v -> {
            startActivity(new Intent(QuizResultados.this, MainActivity.class));
            finish();
        });

        // Configurar el botón para salir de la aplicación
        exitButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Salir")
                    .setMessage("¿Estás seguro de que deseas salir de la aplicación?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        finishAffinity(); // Cierra todas las actividades y sale de la app
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(QuizResultados.this, MainActivity.class));
        finish();
    }
}
