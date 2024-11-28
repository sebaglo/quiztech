package com.example.quizztech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtNombreRegistro, txtEmailRegistro, txtContrasenaRegistro;
    private Button btnRegistrar;
    private Button btnRegresar;
    private FirebaseFirestore mfirestore;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializamos Firebase
        mfirestore = FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(this);

        // Referencias a los campos de entrada
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegresar = findViewById(R.id.btnRegresar);
        txtNombreRegistro = findViewById(R.id.txtNombreRegistro);
        txtEmailRegistro = findViewById(R.id.txtEmailRegistro);
        txtContrasenaRegistro = findViewById(R.id.txtContrasenaRegistro);

        // Acción del botón registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NombreUsuario = txtNombreRegistro.getText().toString().trim();
                String EmailUsuario = txtEmailRegistro.getText().toString().trim();
                String ContraseñaUsuario = txtContrasenaRegistro.getText().toString().trim();

                // Validaciones
                if (NombreUsuario.isEmpty() || EmailUsuario.isEmpty() || ContraseñaUsuario.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, ingresa todos los campos", Toast.LENGTH_SHORT).show();
                }
                // Validación del formato del correo electrónico
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(EmailUsuario).matches()) {
                    Toast.makeText(getApplicationContext(), "Por favor, ingresa un correo válido", Toast.LENGTH_SHORT).show();
                }
                // Validación de la contraseña
                else if (!validarContrasena(ContraseñaUsuario)) {
                    Toast.makeText(getApplicationContext(), "La contraseña debe tener al menos 8 caracteres, incluir una mayúscula, un número y un carácter especial.", Toast.LENGTH_LONG).show();
                }
                // Si todas las validaciones son correctas
                else {
                    postUsuario(NombreUsuario, EmailUsuario, ContraseñaUsuario);
                }
            }
        });

        // Acción del botón regresar
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Finaliza la actividad actual y regresa a la anterior
            }
        });
    }

    // Método para validar la contraseña
    private boolean validarContrasena(String contrasena) {
        // Patrón de contraseña: al menos 8 caracteres, una mayúscula, un número y un carácter especial
        String patron = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        return contrasena.matches(patron);
    }

    // Método para registrar usuario y mostrar los datos
    private void postUsuario(String nombreUsuario, String emailUsuario, String contraseñaUsuario) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", nombreUsuario);
        map.put("Email", emailUsuario);
        map.put("Contraseña", contraseñaUsuario);

        // Enviamos los datos a la colección "usuarios" en Firestore
        mfirestore.collection("usuarios").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // Mostrar mensaje con los datos del usuario
                Toast.makeText(getApplicationContext(), "Usuario registrado correctamente:\nNombre: " + nombreUsuario +
                        "\nEmail: " + emailUsuario, Toast.LENGTH_LONG).show();

                // Pasar los datos a MainActivity
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                intent.putExtra("nombreUsuario", nombreUsuario);
                intent.putExtra("emailUsuario", emailUsuario);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(e -> {
            // Mostrar mensaje de error
            Toast.makeText(getApplicationContext(), "Error al registrar usuario: " + e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }
}
