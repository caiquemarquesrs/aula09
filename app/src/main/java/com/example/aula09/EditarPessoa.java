package com.example.aula09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarPessoa extends AppCompatActivity {

    EditText editNome, editEmail, editTelefone;
    Button btnSalvar;
    PessoasDataBase bd;
    int codigoPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pessoa);

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editTelefone = findViewById(R.id.editTelefone);
        btnSalvar = findViewById(R.id.btnSalvarEdicao);
        bd = new PessoasDataBase(this);

        Intent intent = getIntent();
        codigoPessoa = intent.getIntExtra("cod", -1);
        String nome = intent.getStringExtra("nome");
        String email = intent.getStringExtra("email");
        String telefone = intent.getStringExtra("telefone");

        editNome.setText(nome);
        editEmail.setText(email);
        editTelefone.setText(telefone);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoas pessoa = new Pessoas(codigoPessoa,
                        editNome.getText().toString(),
                        editTelefone.getText().toString(),
                        editEmail.getText().toString());

                bd.atualizaPessoa(pessoa);
                Toast.makeText(EditarPessoa.this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}

