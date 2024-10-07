package com.example.quizztech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtNombreRegistro, txtEmailRegistro, txtContrasenaRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Referencias a los campos de entrada
        txtNombreRegistro = findViewById(R.id.txtNombreRegistro);
        txtEmailRegistro = findViewById(R.id.txtEmailRegistro);
        txtContrasenaRegistro = findViewById(R.id.txtContrasenaRegistro);
    }



    // Método para manejar el botón de registro
    public void registrarUsuario(View view) {
        String nombre = txtNombreRegistro.getText().toString();
        String email = txtEmailRegistro.getText().toString();
        String contraseña = txtContrasenaRegistro.getText().toString();

        if (nombre.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            // Aquí puedes agregar la lógica para registrar al usuario (guardar en base de datos, Firebase, etc.)
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }
    }
}

