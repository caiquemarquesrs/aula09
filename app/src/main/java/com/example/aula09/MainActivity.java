package com.example.aula09;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editCod, editNome, editEmail, editTelefone;

    Button btnSalvar, btnExcluir, btnLimpar;

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editCod = findViewById(R.id.PlainCod);
        editEmail = findViewById(R.id.PlainEmail);
        editNome = findViewById(R.id.PlainNome);
        editTelefone = findViewById(R.id.PlainTelefone);
        btnExcluir = findViewById(R.id.btn3);
        btnSalvar = findViewById(R.id.btn2);
        btnLimpar = findViewById(R.id.btn1);
        lista = findViewById(R.id.lista);
    }
}