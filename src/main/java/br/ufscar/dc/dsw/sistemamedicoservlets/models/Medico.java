package br.ufscar.dc.dsw.sistemamedicoservlets.models;

public class Medico {

    private int id;
    private Usuario usuario;
    private String crm;
    private String nome;
    private String especialidade;

    public Medico() {
    }

    public Medico(Usuario usuario, String crm, String nome, String especialidade) {
        this.usuario = usuario;
        this.crm = crm;
        this.nome = nome;
        this.especialidade = especialidade;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
