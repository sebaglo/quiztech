package com.example.quizztech;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;
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
    private List<QuestionList> questionLists;
    private int curretQuestionPosition = 0;
    private String seleccioneOpcionByUsuario = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
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
        final String getSeleccioneNombreTema = getIntent().getStringExtra("seleccioneTema");

        seleccioneNombreTema.setText(getSeleccioneNombreTema);

        questionLists = QuestionBank.getQuestions(getSeleccioneNombreTema);


        startTimer(tiempo);

        questions.setText((curretQuestionPosition+1)+"/"+questionLists.size());
        question.setText(questionLists.get(0).getQuestion());
        option1.setText(questionLists.get(0).getOpcion1());
        option2.setText(questionLists.get(0).getOpcion2());
        option3.setText(questionLists.get(0).getOpcion3());
        option4.setText(questionLists.get(0).getOpcion4());


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(seleccioneOpcionByUsuario.isEmpty()){

                    seleccioneOpcionByUsuario = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionLists.get(curretQuestionPosition).setUsuarioSeleccionadoAnswer(seleccioneOpcionByUsuario);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(seleccioneOpcionByUsuario.isEmpty()){

                    seleccioneOpcionByUsuario = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionLists.get(curretQuestionPosition).setUsuarioSeleccionadoAnswer(seleccioneOpcionByUsuario);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(seleccioneOpcionByUsuario.isEmpty()){

                    seleccioneOpcionByUsuario = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionLists.get(curretQuestionPosition).setUsuarioSeleccionadoAnswer(seleccioneOpcionByUsuario);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(seleccioneOpcionByUsuario.isEmpty()){

                    seleccioneOpcionByUsuario = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionLists.get(curretQuestionPosition).setUsuarioSeleccionadoAnswer(seleccioneOpcionByUsuario);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seleccioneOpcionByUsuario.isEmpty()){
                    Toast.makeText(QuizActivity.this,"Porfavor seleccione una opcion", Toast.LENGTH_SHORT).show();
                }
                else{
                    ChangeNextQuestion();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

    }
    private void ChangeNextQuestion(){

        curretQuestionPosition++;

        if((curretQuestionPosition+1) == questionLists.size()){
            nextBtn.setText("Enviar");
        }

        if(curretQuestionPosition < questionLists.size()){

            seleccioneOpcionByUsuario = "";

            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText((curretQuestionPosition+1)+"/"+questionLists.size());
            question.setText(questionLists.get(curretQuestionPosition).getQuestion());
            option1.setText(questionLists.get(curretQuestionPosition).getOpcion1());
            option2.setText(questionLists.get(curretQuestionPosition).getOpcion2());
            option3.setText(questionLists.get(curretQuestionPosition).getOpcion3());
            option4.setText(questionLists.get(curretQuestionPosition).getOpcion4());
        }
        else{

            Intent intent = new Intent(QuizActivity.this, QuizResultados.class);
            intent.putExtra("Correcto", getCorrectAnswer());
            intent.putExtra("Incorrecto", getInCorrectAnswer());
            startActivity(intent);

            finish();
        }
    }
    private void startTimer(TextView timerTextView){

        quizTimer = new Timer();

        quizTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(seconds == 0){
                    totalTimeInMins--;
                    seconds = 59;
                }
                else if(seconds == 0 && totalTimeInMins == 0){
                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(QuizActivity.this, "se acabo el tiempo", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this, QuizResultados.class);
                    intent.putExtra("Correcto", getCorrectAnswer());
                    intent.putExtra("Incorrecto", getInCorrectAnswer());
                    startActivity(intent);

                    finish();
                }
                else{
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length() == 1);{
                            finalMinutes = "0"+finalMinutes;
                        }

                        if(finalSeconds.length() == 1){
                            finalSeconds = "0"+finalSeconds;
                        }

                        timerTextView.setText(finalMinutes+":"+finalSeconds);
                    }
                });
            }
        },1000, 1000);
    }

    private int getCorrectAnswer(){

        int correctAnswer = 0;

        for(int i=0;i<questionLists.size();i++){

            final String getusuarioSeleccionadoAnswer = questionLists.get(i).getUsuarioSeleccionadoAnswer();
            final  String getAnswer = questionLists.get(i).getAnswer();

            if(getusuarioSeleccionadoAnswer.equals(getAnswer)){
                correctAnswer++;
            }

        }

        return correctAnswer;
    }

    private int getInCorrectAnswer(){

        int correctAnswer = 0;

        for(int i=0;i<questionLists.size();i++){

            final String getusuarioSeleccionadoAnswer = questionLists.get(i).getUsuarioSeleccionadoAnswer();
            final  String getAnswer = questionLists.get(i).getAnswer();

            if(!getusuarioSeleccionadoAnswer.equals(getAnswer)){
                correctAnswer++;
            }

        }

        return correctAnswer;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this, MainActivity.class));
        finish();
    }

    private void revealAnswer(){

        final String getAnswer = questionLists.get(curretQuestionPosition).getAnswer();

        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }

        else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
    }

        else if(option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }
}