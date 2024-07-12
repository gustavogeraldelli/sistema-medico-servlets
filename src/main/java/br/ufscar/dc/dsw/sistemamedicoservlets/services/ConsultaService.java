package br.ufscar.dc.dsw.sistemamedicoservlets.services;

import br.ufscar.dc.dsw.sistemamedicoservlets.daos.ConsultaDao;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.ConsultaNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.MedicoJaTemConsultaException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.PacienteJaTemConsultaException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Consulta;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;

import java.util.List;

public class ConsultaService {

    private final ConsultaDao consultaDao;

    public ConsultaService() {
        this.consultaDao = new ConsultaDao();
    }

    public void salvar(Consulta c) throws MedicoJaTemConsultaException, PacienteJaTemConsultaException {
        Consulta consultaBanco = consultaDao.findByMedicoAndDataConsulta(c.getMedico(), c.getDataConsulta());

        if (consultaBanco != null)
            throw new MedicoJaTemConsultaException("Esse médico não está disponível nessa data");

        consultaBanco = consultaDao.findByPacienteAndDataConsulta(c.getPaciente(), c.getDataConsulta());

        if (consultaBanco != null)
            throw new PacienteJaTemConsultaException("Esse paciente já possui consulta marcada nessa data");

        consultaDao.insert(c);
    }

    public void atualizar(Consulta c) throws ConsultaNaoExisteException, MedicoJaTemConsultaException,
            PacienteJaTemConsultaException {
        Consulta consultaBanco = consultaDao.findById(c.getId());

        if (consultaBanco == null)
            throw new ConsultaNaoExisteException("Consulta com ID " + c.getId() + " não existe");

        consultaBanco = consultaDao.findByMedicoAndDataConsulta(c.getMedico(), c.getDataConsulta());

        if (consultaBanco != null)
            throw new MedicoJaTemConsultaException("Esse médico não está disponível nessa data");

        consultaBanco = consultaDao.findByPacienteAndDataConsulta(c.getPaciente(), c.getDataConsulta());

        if (consultaBanco != null)
            throw new PacienteJaTemConsultaException("Esse paciente já possui consulta marcada nessa data");

        consultaDao.update(c);
    }

    public void deletar(int id) throws ConsultaNaoExisteException {
        Consulta consultaBanco = consultaDao.findById(id);

        if (consultaBanco == null)
            throw new ConsultaNaoExisteException("Consulta com ID " + id + " não existe");

        consultaDao.deleteById(id);
    }

    public Consulta buscarPorId(int id) {
        return consultaDao.findById(id);
    }

    public List<Consulta> listarPorMedico(Medico m) {
        return consultaDao.findAllByMedico(m);
    }

    public List<Consulta> listarPorPaciente(Paciente p) {
        return consultaDao.findAllByPaciente(p);
    }

    public List<Consulta> listar() {
        return consultaDao.findAll();
    }
}
