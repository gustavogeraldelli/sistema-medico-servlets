package br.ufscar.dc.dsw.sistemamedicoservlets.models;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario;

public class Usuario {

    private int id;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(String email, String senha, TipoUsuario tipoUsuario) {
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(int id, String email, String senha, TipoUsuario tipoUsuario) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
