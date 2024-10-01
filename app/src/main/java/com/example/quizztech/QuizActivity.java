package com.example.quizztech;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private TextView questions;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;

    private Timer quizTimer;
    private int totalTimeInMins = 1;
    private int seconds = 0;
    private List<QuestionList> questionLists = new ArrayList<>();
    private int curretQuestionPosition = 0;
    private String seleccioneOpcionByUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView tiempo = findViewById(R.id.Tiempo);
        final TextView seleccioneNombreTema = findViewById(R.id.seleccionTema);

        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.opcion1);
        option2 = findViewById(R.id.opcion2);
        option3 = findViewById(R.id.opcion3);
        option4 = findViewById(R.id.opcion4);
        nextBtn = findViewById(R.id.nextBtn);

        // Obtener el nombre del tema de la intención
        String getSeleccioneNombreTema = getIntent().getStringExtra("seleccioneTema");
        seleccioneNombreTema.setText(getSeleccioneNombreTema);

        // Obtener las preguntas relacionadas con el tema
        questionLists = QuestionBank.getQuestions(getSeleccioneNombreTema);
        if (questionLists == null || questionLists.isEmpty()) {
            Toast.makeText(this, "No hay preguntas disponibles para este tema.", Toast.LENGTH_SHORT).show();
            finish();
            return; // Termina la actividad si no hay preguntas
        }

        startTimer(tiempo);
        setQuestionData();

        // Configurar los botones de opciones
        option1.setOnClickListener(v -> selectOption(option1));
        option2.setOnClickListener(v -> selectOption(option2));
        option3.setOnClickListener(v -> selectOption(option3));
        option4.setOnClickListener(v -> selectOption(option4));

        nextBtn.setOnClickListener(v -> {
            if (seleccioneOpcionByUsuario.isEmpty()) {
                Toast.makeText(QuizActivity.this, "Por favor seleccione una opción", Toast.LENGTH_SHORT).show();
            } else {
                changeToNextQuestion();
            }
        });

        backBtn.setOnClickListener(v -> {
            stopTimer();
            startActivity(new Intent(QuizActivity.this, MainActivity.class));
            finish();
        });
    }

    private void changeToNextQuestion() {
        curretQuestionPosition++;
        if (curretQuestionPosition >= questionLists.size()) {

            // Si hemos alcanzado el final, ir a los resultados
            Intent intent = new Intent(QuizActivity.this, QuizResultados.class);
            intent.putExtra("Correcto", getCorrectAnswer());
            intent.putExtra("Incorrecto", getInCorrectAnswer());
            startActivity(intent);
            finish();
        } else {
            resetOptions();
            setQuestionData();
            if (curretQuestionPosition == questionLists.size() - 1) {
                nextBtn.setText("Enviar");
            } else {
                nextBtn.setText("Siguiente");
            }
        }
    }

    private void resetOptions() {
        seleccioneOpcionByUsuario = "";
        option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option1.setTextColor(Color.parseColor("#1F6BB8"));
        option2.setTextColor(Color.parseColor("#1F6BB8"));
        option3.setTextColor(Color.parseColor("#1F6BB8"));
        option4.setTextColor(Color.parseColor("#1F6BB8"));
        questions.setText((curretQuestionPosition + 1) + "/" + questionLists.size());
    }

    private void setQuestionData() {
        question.setText(questionLists.get(curretQuestionPosition).getQuestion());
        option1.setText(questionLists.get(curretQuestionPosition).getOpcion1());
        option2.setText(questionLists.get(curretQuestionPosition).getOpcion2());
        option3.setText(questionLists.get(curretQuestionPosition).getOpcion3());
        option4.setText(questionLists.get(curretQuestionPosition).getOpcion4());
    }

    private void startTimer(TextView timerTextView) {
        quizTimer = new Timer();
        quizTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (seconds == 0) {
                    if (totalTimeInMins > 0) {
                        totalTimeInMins--;
                        seconds = 59;
                    } else {
                        stopTimer();
                        showTimeUp();
                    }
                } else {
                    seconds--;
                }
                runOnUiThread(() -> updateTimer(timerTextView));
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        if (quizTimer != null) {
            quizTimer.purge();
            quizTimer.cancel();
            quizTimer = null;
        }
    }

    private void updateTimer(TextView timerTextView) {
        String finalMinutes = totalTimeInMins < 10 ? "0" + totalTimeInMins : String.valueOf(totalTimeInMins);
        String finalSeconds = seconds < 10 ? "0" + seconds : String.valueOf(seconds);
        timerTextView.setText(finalMinutes + ":" + finalSeconds);
    }

    private void showTimeUp() {
        Toast.makeText(QuizActivity.this, "Se acabó el tiempo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(QuizActivity.this, QuizResultados.class);
        intent.putExtra("Correcto", getCorrectAnswer());
        intent.putExtra("Incorrecto", getInCorrectAnswer());
        startActivity(intent);
        finish();
    }

    private int getCorrectAnswer() {
        int correctAnswer = 0;
        for (QuestionList questionList : questionLists) {
            final String geUserSelectAnswer = questionList.getUsuarioSeleccionadoAnswer();
            final String getAnswer = questionList.getAnswer();
            if (geUserSelectAnswer != null && geUserSelectAnswer.equals(getAnswer)) {
                correctAnswer++;
            }
        }
        return correctAnswer;
    }

    private int getInCorrectAnswer() {
        int incorrectAnswer = 0;
        for (QuestionList questionList : questionLists) {
            final String geUserSelectAnswer = questionList.getUsuarioSeleccionadoAnswer();
            final String getAnswer = questionList.getAnswer();
            if (geUserSelectAnswer != null && !geUserSelectAnswer.equals(getAnswer)) {
                incorrectAnswer++;
            }
        }
        return incorrectAnswer;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopTimer();
        startActivity(new Intent(QuizActivity.this, MainActivity.class));
        finish();
    }

    private void revealAnswer() {
        final String getAnswer = questionLists.get(curretQuestionPosition).getAnswer();
        if (option1.getText().toString().equals(getAnswer)) {
            option1.setBackgroundResource(R.drawable.round_back_red10);
            option1.setTextColor(Color.WHITE);
        } else if (option2.getText().toString().equals(getAnswer)) {
            option2.setBackgroundResource(R.drawable.round_back_red10);
            option2.setTextColor(Color.WHITE);
        } else if (option3.getText().toString().equals(getAnswer)) {
            option3.setBackgroundResource(R.drawable.round_back_red10);
            option3.setTextColor(Color.WHITE);
        } else if (option4.getText().toString().equals(getAnswer)) {
            option4.setBackgroundResource(R.drawable.round_back_red10);
            option4.setTextColor(Color.WHITE);
        }
    }

    private void selectOption(AppCompatButton selectedOption) {
        if (seleccioneOpcionByUsuario.isEmpty()) {
            seleccioneOpcionByUsuario = selectedOption.getText().toString();
            selectedOption.setBackgroundResource(R.drawable.round_back_red10);
            selectedOption.setTextColor(Color.WHITE);
            revealAnswer();
            questionLists.get(curretQuestionPosition).setUsuarioSeleccionadoAnswer(seleccioneOpcionByUsuario);
        }
    }
}
