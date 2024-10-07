package com.example.quizztech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizztech.RegistroActivity;

public class ActivityLogin extends AppCompatActivity {

    private EditText txtNombre, txtContrasena;
    private Button btnSesion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciar los campos de texto y el textView
        txtNombre = findViewById(R.id.txtNombre);
        txtContrasena = findViewById(R.id.txtContrasena);
        btnSesion = findViewById(R.id.btnSesion);


        // Escuchar el clic en el TextView de registro
        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes navegar a una nueva actividad de registro
                Intent intent = new Intent(ActivityLogin.this, ActivityInicio.class);
                startActivity(intent);
            }
        });
    }

    // Método para manejar el evento de inicio de sesión (por ejemplo, cuando un botón de iniciar sesión sea añadido)
    public void iniciarSesion(View view) {
        String nombre = txtNombre.getText().toString();
        String contraseña = txtContrasena.getText().toString();

        // Validación básica
        if (nombre.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa ambos campos", Toast.LENGTH_SHORT).show();
        } else {
            // Aquí puedes agregar la lógica de autenticación
            Toast.makeText(this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();

            // Por ejemplo, si la autenticación es exitosa, puedes navegar a la siguiente pantalla
            // Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            // startActivity(intent);
        }
    }
}


