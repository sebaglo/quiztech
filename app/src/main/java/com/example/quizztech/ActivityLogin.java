package com.example.quizztech;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {

    private EditText txtNombre, txtContrasena;
    private Button btnSesion;
    private Button btnRegistroUsuario;
    private Button btnRegresar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referenciar los campos y botones
        txtNombre = findViewById(R.id.txtNombre);
        txtContrasena = findViewById(R.id.txtContrasena);
        btnSesion = findViewById(R.id.btnSesion);
        btnRegistroUsuario = findViewById(R.id.btnRegistroUsuario);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Configurar el botón de registro
        btnRegistroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad de registro
                Intent intent = new Intent(ActivityLogin.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        // Configurar el botón de iniciar sesión
        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        // Configurar el botón de regresar
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarConfirmacionSalir();
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

    // Método para mostrar una confirmación antes de salir
    private void mostrarConfirmacionSalir() {
        new AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Estás seguro de que deseas salir?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();  // Cierra la actividad actual
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();  // Cierra el cuadro de diálogo
                    }
                })
                .show();
    }
}
