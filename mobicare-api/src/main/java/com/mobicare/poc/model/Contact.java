package com.mobicare.poc.model;

/**
 * Created by vinicius.martins on 06/06/2016.
 */
public class Contact {

    private String nome;
    private String email;

    public Contact(String nome, String email) {
        this.nome = nome;
        this.email = email;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
