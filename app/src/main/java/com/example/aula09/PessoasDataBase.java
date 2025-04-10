package com.example.aula09;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PessoasDataBase extends SQLiteOpenHelper {

    private static final int versao = 1;
    private static final String nomeBD = "bd_pessoas";

    private static final String tb_pessoas = "pessoas";
    private static final String c_cod = "cod";
    private static final String c_nome = "nome";
    private static final String c_email = "email";
    private static final String c_telefone = "telefone";

    public PessoasDataBase(Context context) {
        super(context, nomeBD, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + tb_pessoas + "(" +
                c_cod + " INTEGER PRIMARY KEY, " + c_nome + " TEXT, "+
                c_telefone + " TEXT, "+ c_email +" TEXT) ";
        db.execSQL(query);
    }

    public void AddPessoas (Pessoas pessoas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(c_nome, pessoas.getNome());
        values.put(c_telefone, pessoas.getTelefone());
        values.put(c_email, pessoas.getEmail());
        db.insert(tb_pessoas, null, values);
        db.close();
    }

    public void DeletePessoas (Pessoas pessoas) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_pessoas, c_cod+" = ?", new String[]{String.valueOf(pessoas.getCod())});
        db.close();
    }

    public Pessoas selecionarPessoa (int cod){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(tb_pessoas, new String[]{c_cod,c_nome,c_telefone,c_email},
                c_cod+" = ?", new String[]{String.valueOf(cod)},
                null, null, null);
        if (cursor!=null) cursor.moveToFirst();
        else return null;
        Pessoas pessoa1 = new Pessoas(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return pessoa1;
    }

    public List<Pessoas> listarTodos (){
        SQLiteDatabase db = this.getReadableDatabase();
        List <Pessoas> lista = new ArrayList<Pessoas>();
        String query = "SELECT * FROM "+tb_pessoas;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                Pessoas pessoas = new Pessoas();
                pessoas.setCod(Integer.parseInt(cursor.getString(0)));
                pessoas.setNome(cursor.getString(1));
                pessoas.setTelefone(cursor.getString(2));
                pessoas.setEmail(cursor.getString(3));
                lista.add(pessoas);
            } while (cursor.moveToNext());

    }
        return lista;
    }

    public void atualizaPessoa (Pessoas pessoas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(c_nome, pessoas.getNome());
        values.put(c_telefone, pessoas.getTelefone());
        values.put(c_email, pessoas.getEmail());
        db.update(tb_pessoas, values, c_cod + " = ?", new String[]{String.valueOf(pessoas.getCod())});
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
