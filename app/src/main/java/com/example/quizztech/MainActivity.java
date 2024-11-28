package com.example.quizztech;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    private String seleccionNombreTema = "";
    private CheckBox musicCheckBox; // Nuevo CheckBox para música

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                finish();
            }
        },3000);

        // Mostrar la bienvenida en la activity de menú
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombreUsuario = extras.getString("nombreUsuario");
            String emailUsuario = extras.getString("emailUsuario");

            Toast.makeText(this, "Bienvenido: " + nombreUsuario + "\nEmail: " + emailUsuario, Toast.LENGTH_SHORT).show();
        }

        // Encontrar las vistas
        final LinearLayout java = findViewById(R.id.javaLayout);
        final LinearLayout php = findViewById(R.id.phpLayout);
        final LinearLayout html = findViewById(R.id.htmlLayout);
        final LinearLayout android = findViewById(R.id.androidLayout);
        final Button startBtn = findViewById(R.id.startQuizBtn);
        musicCheckBox = findViewById(R.id.musicaCheckBox);

        // Inicializar el MediaPlayer
        mp = MediaPlayer.create(this, R.raw.emociones);

        // Controlar el CheckBox de música
        musicCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicCheckBox.isChecked()) {
                    mp.start();
                } else {
                    if (mp.isPlaying()) {
                        mp.pause();
                        mp.seekTo(0);
                    }
                }
            }
        });

        // Manejar la selección de temas
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

        // Cuando se presiona el botón para empezar el quiz
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccionNombreTema.isEmpty()) {
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

    // Mostrar el cuadro de confirmación
    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar salida")
                .setMessage("¿Estás seguro de que quieres salir de la aplicación?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();  // Cerrar la actividad y salir de la app
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    // Método para seleccionar un tema
    private void updateThemeSelection(LinearLayout selectedLayout) {
        // Restablece el fondo de todos los temas antes de aplicar la selección actual
        resetAllLayouts();

        // Aplica el estilo solo al tema seleccionado
        selectedLayout.setBackgroundResource(R.drawable.round_back_green10);
    }

    // Método para restablecer todos los temas al estado no seleccionado
    private void resetAllLayouts() {
        findViewById(R.id.javaLayout).setBackgroundResource(R.drawable.round_back_white_stroke10);
        findViewById(R.id.phpLayout).setBackgroundResource(R.drawable.round_back_white_stroke10);
        findViewById(R.id.htmlLayout).setBackgroundResource(R.drawable.round_back_white_stroke10);
        findViewById(R.id.androidLayout).setBackgroundResource(R.drawable.round_back_white_stroke10);
    }

    // Detener la música cuando se cambie de Activity
    @Override
    protected void onPause() {
        super.onPause();
        // Si el CheckBox no está marcado, detener la música
        if (mp != null && mp.isPlaying() && !musicCheckBox.isChecked()) {
            mp.pause();
            mp.seekTo(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
            mp = null;
        }
    }

    // Detener la música cuando se presiona el botón de retroceso
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
