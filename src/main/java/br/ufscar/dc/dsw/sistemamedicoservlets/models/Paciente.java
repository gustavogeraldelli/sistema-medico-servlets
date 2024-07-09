package br.ufscar.dc.dsw.sistemamedicoservlets.models;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.Sexo;

import java.time.LocalDate;

public class Paciente {

    private int id;
    private Usuario usuario;
    private String cpf;
    private String nome;
    private String telefone;
    private Sexo sexo;
    private LocalDate dataNascimento;

    public Paciente() {
    }

    public Paciente(String cpf, String nome, String telefone, Sexo sexo, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Paciente(Usuario usuario, String cpf, String nome, String telefone, Sexo sexo, LocalDate dataNascimento) {
        this.usuario = usuario;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
