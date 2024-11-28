package com.example.quizztech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {

    private EditText txtNombre, txtContrasena;
    private Button btnSesion, btnRegistroUsuario, btnRegresar;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializamos Firebase
        FirebaseApp.initializeApp(this);
        mfirestore = FirebaseFirestore.getInstance();

        // Referencias a los campos de entrada
        txtNombre = findViewById(R.id.txtNombre);
        txtContrasena = findViewById(R.id.txtContrasena);
        btnSesion = findViewById(R.id.btnSesion);
        btnRegistroUsuario = findViewById(R.id.btnRegistroUsuario);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Acción del botón registrar
        btnRegistroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad de registro
                startActivity(new Intent(ActivityLogin.this, RegistroActivity.class));
            }
        });

        // Acción del botón iniciar sesión
        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        // Acción del botón regresar
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void iniciarSesion() {
        String nombre = txtNombre.getText().toString().trim();
        String contraseña = txtContrasena.getText().toString().trim();

        if (nombre.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa ambos campos", Toast.LENGTH_SHORT).show();
        } else {
            // Consultamos los usuarios en Firestore para verificar las credenciales
            mfirestore.collection("usuarios")
                    .whereEqualTo("Nombre", nombre)
                    .whereEqualTo("Contraseña", contraseña)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            // Si encontramos un usuario que coincida
                            QuerySnapshot result = task.getResult();
                            for (QueryDocumentSnapshot document : result) {
                                // Acceder a los datos guardados en la base de datos
                                String nombreUsuario = document.getString("Nombre");
                                String emailUsuario = document.getString("Email");

                                // Si es correcto, navegar a MainActivity y pasar los datos
                                Toast.makeText(ActivityLogin.this, "Bienvenido, " + nombreUsuario, Toast.LENGTH_SHORT).show();

                                // Pasar datos adicionales (como el correo electrónico)
                                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                                intent.putExtra("nombreUsuario", nombreUsuario);
                                intent.putExtra("emailUsuario", emailUsuario);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            // Si no se encuentra el usuario
                            Toast.makeText(ActivityLogin.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        // En caso de error con la consulta
                        Toast.makeText(ActivityLogin.this, "Error de conexión: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
