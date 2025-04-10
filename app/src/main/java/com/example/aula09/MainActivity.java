package com.example.aula09;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editCod, editNome, editEmail, editTelefone;

    Button btnSalvar, btnExcluir, btnLimpar;

    ListView lista;
    PessoasDataBase bd = new PessoasDataBase(this);
    ArrayAdapter <String> adapter;
    ArrayList <String> arrayList;

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
        listaPessoas();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String)lista.getItemAtPosition(position);
                String codigo = conteudo.substring(0, conteudo.indexOf("-")).trim();
                Pessoas pessoas = bd.selecionarPessoa(Integer.parseInt(codigo));
                Intent intent = new Intent(MainActivity.this, EditarPessoa.class);
                intent.putExtra("cod", pessoas.getCod());
                intent.putExtra("nome", pessoas.getNome());
                intent.putExtra("telefone", pessoas.getTelefone());
                intent.putExtra("email", pessoas.getEmail());
                startActivityForResult(intent, 1);
                editCod.setText(String.valueOf(pessoas.getCod()));
                editNome.setText(pessoas.getNome());
                editTelefone.setText(pessoas.getTelefone());
                editEmail.setText(pessoas.getEmail());

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampo();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCod.getText().toString();
                String nome = editNome.getText().toString();
                String telefone = editTelefone.getText().toString();
                String email = editEmail.getText().toString();
                if (nome.isEmpty()) editNome.setError("Campo Obrigatório!");
                else if (codigo.isEmpty()) {
                    bd.AddPessoas(new Pessoas(nome, telefone, email));
                    Toast.makeText(MainActivity.this,
                            "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampo();
                    listaPessoas();
                }else{
                    bd.atualizaPessoa(new Pessoas(Integer.parseInt(codigo), nome, telefone, email));
                    Toast.makeText(MainActivity.this,
                            "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampo();
                    listaPessoas();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCod.getText().toString();
                if (codigo.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Não há nada selecionado!", Toast.LENGTH_SHORT).show();
                }else{
                    Pessoas pessoas = new Pessoas();
                    pessoas.setCod(Integer.parseInt(codigo));
                    bd.DeletePessoas(pessoas);
                    Toast.makeText(MainActivity.this,
                            "Apagado com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampo();
                    listaPessoas();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            listaPessoas();
        }
    }


    public void limparCampo () {
        editCod.setText("");
        editEmail.setText("");
        editTelefone.setText("");
        editNome.setText("");
        editNome.requestFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPessoas();
        limparCampo();
    }


    public void listaPessoas(){
        List<Pessoas> pessoas = bd.listarTodos();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, arrayList);
        lista.setAdapter(adapter);
        for (Pessoas p:pessoas){
            arrayList.add(p.getCod()+ " - "+ p.getNome());
            adapter.notifyDataSetChanged();
        }
    }
}