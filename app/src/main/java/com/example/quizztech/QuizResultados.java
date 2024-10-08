package com.example.quizztech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class QuizResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_resultados);

        // Inicializar los componentes de la interfaz
        final AppCompatButton startNewBtn = findViewById(R.id.btnVolverjugar);
        final TextView correctAnswer = findViewById(R.id.respuestaCorrecta);
        final TextView incorrectAnswers = findViewById(R.id.respuestaIncorrecta);

        // Obtener respuestas correctas e incorrectas del Intent
        final int getCorrectAnswer = getIntent().getIntExtra("Respuestas Correctas", 0);
        final int getIncorrectAnswer = getIntent().getIntExtra("Respuestas Incorrectas", 0);

        // Mostrar las respuestas en los TextViews
        correctAnswer.setText(String.valueOf(getCorrectAnswer));
        incorrectAnswers.setText(String.valueOf(getIncorrectAnswer));

        // Configurar el botón para reiniciar el quiz
        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResultados.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(QuizResultados.this, MainActivity.class));
        finish();
    }
}
