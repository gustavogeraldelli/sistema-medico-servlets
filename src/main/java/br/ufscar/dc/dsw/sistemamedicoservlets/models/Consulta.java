package br.ufscar.dc.dsw.sistemamedicoservlets.models;

import java.time.LocalDateTime;

public class Consulta {

    private int id;
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataConsulta;

    public Consulta() {
    }

    public Consulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Consulta(Medico medico, Paciente paciente, LocalDateTime dataConsulta) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}
