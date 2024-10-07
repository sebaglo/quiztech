package com.example.quizztech;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

    private boolean isMusicEnable;
    private MediaPlayer mp;
    private TextView questions;
    private TextView question;
    private CheckBox musicCheckBox;

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

        musicCheckBox = findViewById(R.id.musicaCheckBox);
        mp = MediaPlayer.create(this, R.raw.emociones);
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
        final String getSeleccioneNombreTema = getIntent().getStringExtra("seleccioneTema");

        seleccioneNombreTema.setText(getSeleccioneNombreTema);

        questionLists = QuestionBank.getQuestions(getSeleccioneNombreTema);

        startTimer(tiempo);

        // Load the first question
        loadQuestion();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionClick(option1);
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionClick(option2);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionClick(option3);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionClick(option4);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccioneOpcionByUsuario.isEmpty()) {
                    Toast.makeText(QuizActivity.this, "Por favor seleccione una opción", Toast.LENGTH_SHORT).show();
                } else {
                    changeToNextQuestion();
                }
            }
        });

        // Musica en la Activity del Quiz
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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimerAndReturn();
            }
        });
    }

    private void loadQuestion() {
        questions.setText((curretQuestionPosition + 1) + "/" + questionLists.size());
        question.setText(questionLists.get(curretQuestionPosition).getQuestion());
        option1.setText(questionLists.get(curretQuestionPosition).getOpcion1());
        option2.setText(questionLists.get(curretQuestionPosition).getOpcion2());
        option3.setText(questionLists.get(curretQuestionPosition).getOpcion3());
        option4.setText(questionLists.get(curretQuestionPosition).getOpcion4());
    }

    private void handleOptionClick(AppCompatButton selectedOption) {
        if (seleccioneOpcionByUsuario.isEmpty()) {
            seleccioneOpcionByUsuario = selectedOption.getText().toString();

            selectedOption.setBackgroundResource(R.drawable.round_back_red10);
            selectedOption.setTextColor(Color.WHITE);

            revealAnswer();
            questionLists.get(curretQuestionPosition).setUsuarioSeleccionadoAnswer(seleccioneOpcionByUsuario);
        }
    }

    private void changeToNextQuestion() {
        curretQuestionPosition++;

        if ((curretQuestionPosition + 1) == questionLists.size()) {
            nextBtn.setText("Enviar");
        }

        if (curretQuestionPosition < questionLists.size()) {
            resetOptions();
            loadQuestion(); // Load the next question
        } else {
            // Last question reached, navigate to results
            Intent intent = new Intent(QuizActivity.this, QuizResultados.class);
            intent.putExtra("Respuestas Correctas", getCorrectAnswer());
            intent.putExtra("Respuetas Incorrectas", getInCorrectAnswer());
            startActivity(intent);
            finish();
        }
    }

    private void resetOptions() {
        seleccioneOpcionByUsuario = "";

        option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option1.setTextColor(Color.parseColor("#1F6BB8"));

        option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option2.setTextColor(Color.parseColor("#1F6BB8"));

        option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option3.setTextColor(Color.parseColor("#1F6BB8"));

        option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
        option4.setTextColor(Color.parseColor("#1F6BB8"));
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
                        quizTimer.cancel();
                        showTimeUp();
                    }
                } else {
                    seconds--;
                }

                runOnUiThread(() -> updateTimer(timerTextView));
            }
        }, 0, 1000);
    }

    private void updateTimer(TextView timerTextView) {
        String finalMinutes = totalTimeInMins < 10 ? "0" + totalTimeInMins : String.valueOf(totalTimeInMins);
        String finalSeconds = seconds < 10 ? "0" + seconds : String.valueOf(seconds);
        timerTextView.setText(finalMinutes + ":" + finalSeconds);
    }

    private void showTimeUp() {
        Toast.makeText(QuizActivity.this, "Se acabó el tiempo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(QuizActivity.this, QuizResultados.class);
        intent.putExtra("Respuestas Correctas", getCorrectAnswer());
        intent.putExtra("Respuestas Incorrectas", getInCorrectAnswer());
        startActivity(intent);
        finish();
    }

    private int getCorrectAnswer() {
        int correctAnswer = 0;
        for (QuestionList question : questionLists) {
            if (question.getUsuarioSeleccionadoAnswer() != null && question.getUsuarioSeleccionadoAnswer().equals(question.getAnswer())) {
                correctAnswer++;
            }
        }
        return correctAnswer;
    }

    private int getInCorrectAnswer() {
        int incorrectAnswer = 0;
        for (QuestionList question : questionLists) {
            if (question.getUsuarioSeleccionadoAnswer() != null && !question.getUsuarioSeleccionadoAnswer().equals(question.getAnswer())) {
                incorrectAnswer++;
            }
        }
        return incorrectAnswer;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
        }
        cancelTimerAndReturn();
    }

    private void cancelTimerAndReturn() {
        if (quizTimer != null) {
            quizTimer.cancel();
        }
        startActivity(new Intent(QuizActivity.this, MainActivity.class));
        finish();
    }

    private void revealAnswer() {
        final String getAnswer = questionLists.get(curretQuestionPosition).getAnswer();

        if (option1.getText().toString().equals(getAnswer)) {
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        } else if (option2.getText().toString().equals(getAnswer)) {
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        } else if (option3.getText().toString().equals(getAnswer)) {
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        } else if (option4.getText().toString().equals(getAnswer)) {
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detenemos la música si está sonando y liberamos los recursos
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            mp = null;
        }
    }


}

