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

                if (NombreUsuario.isEmpty() || EmailUsuario.isEmpty() || ContraseñaUsuario.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Por favor, ingresa todos los campos", Toast.LENGTH_SHORT).show();
                } else {

                    postUsuario(NombreUsuario, EmailUsuario, ContraseñaUsuario);
                }
            }
        });
    }

    // agregar el usuario a Firebase
    private void postUsuario(String nombreUsuario, String emailUsuario, String contraseñaUsuario) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", nombreUsuario);
        map.put("Email", emailUsuario);
        map.put("Contraseña", contraseñaUsuario);

        // Enviamos los datos a la colección "usuarios" en Firestore
        mfirestore.collection("usuarios").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // Si el registro es exitoso, mostramos un mensaje y cerramos la actividad
                Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        }).addOnFailureListener(e -> {
            // Si ocurre un error al registrar, mostramos un mensaje de error
            Toast.makeText(getApplicationContext(), "Error al registrar usuario: " + e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }
}
