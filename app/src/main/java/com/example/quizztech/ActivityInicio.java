package com.example.quizztech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class ActivityInicio extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inicio);



        Button playButton = findViewById(R.id.playBtnSesion);
        Button btnsesionLogin = findViewById(R.id.btnsesionLogin);

        btnsesionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInicio.this, ActivityLogin.class);
                startActivity(intent);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes definir lo que hará el botón, por ejemplo, iniciar otra actividad.
                Intent intent = new Intent(ActivityInicio.this, MainActivity.class);
                startActivity(intent);

                // Por ahora, solo mostramos un mensaje en consola
                System.out.println("Botón INICIO presionado");
            }
        });
    }
}
