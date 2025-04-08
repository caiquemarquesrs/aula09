package com.example.aula09;

public class Pessoas {
    int cod;
    String nome, telefone, email;
    public Pessoas (){
    }
    public Pessoas(int cod,String nome, String telefone, String email){
        this.cod=cod;
        this.telefone=telefone;
        this.nome=nome;
        this.email=email;
    }
    public Pessoas(String nome, String telefone, String email){
        this.nome=nome;
        this.telefone=telefone;
        this.email=email;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
