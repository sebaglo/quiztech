package com.example.quizztech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {

    private EditText txtNombre, txtContrasena;
    private Button btnSesion;
    private TextView txtRegistroL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referenciar los campos de texto y el textView
        txtNombre = findViewById(R.id.txtNombre);
        txtContrasena = findViewById(R.id.txtContrasena);
        btnSesion = findViewById(R.id.btnSesion);
        txtRegistroL = findViewById(R.id.txtRegistroL);

        // Acción para el texto "¿Aún no te has registrado?"
        txtRegistroL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navega a la actividad de registro
                Intent intent = new Intent(ActivityLogin.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        // Acción del botón de iniciar sesión
        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
    }

    // Método para manejar el evento de inicio de sesión
    public void iniciarSesion() {
        String nombre = txtNombre.getText().toString();
        String contraseña = txtContrasena.getText().toString();

        // Validación básica
        if (nombre.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa ambos campos", Toast.LENGTH_SHORT).show();
        } else {
            // Simulando autenticación exitosa para demostración
            if (nombre.equals("oscarito") && contraseña.equals("1234")) {
                // Si la autenticación es exitosa
                Toast.makeText(this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();

                // Navegar a la actividad principal
                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                startActivity(intent);
                finish();  // Cerrar la actividad actual
            } else {
                // Si el nombre de usuario o la contraseña no son correctos
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
