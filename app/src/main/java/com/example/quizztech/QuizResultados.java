package com.example.quizztech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_resultados);


        final AppCompatButton startNewBtn = findViewById(R.id.btnVolverjugar);
        final TextView correctAnswer = findViewById(R.id.respuestaCorrecta);
        final TextView IncorrectAnswer = findViewById(R.id.respuestaIncorrecta);

        final int getCorrectAnswer = getIntent().getIntExtra("Respuestas Correctas", 0);
        final  int getIncorrectAnswer = getIntent().getIntExtra("Respuestas Incorrectas", 0);

        correctAnswer.setText(String.valueOf(getCorrectAnswer));
        IncorrectAnswer.setText(String.valueOf(getIncorrectAnswer));

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