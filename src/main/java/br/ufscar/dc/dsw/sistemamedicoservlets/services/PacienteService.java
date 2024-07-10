package br.ufscar.dc.dsw.sistemamedicoservlets.services;

import br.ufscar.dc.dsw.sistemamedicoservlets.daos.PacienteDao;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.CpfJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.EmailJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.PacienteNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.UsuarioNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;

import java.util.List;

public class PacienteService {

    private final PacienteDao pacienteDao;
    private final UsuarioService usuarioService;

    public PacienteService() {
        this.pacienteDao = new PacienteDao();
        this.usuarioService = new UsuarioService();
    }

    public void salvar(Paciente p) throws CpfJaExisteException, EmailJaExisteException {
        Paciente pacienteBanco = pacienteDao.findByCpf(p.getCpf());

        if (pacienteBanco != null)
            throw new CpfJaExisteException("Paciente com CPF " + p.getCpf() + " já existe");

        usuarioService.salvar(p.getUsuario());
        pacienteDao.insert(p);
    }

    public void atualizar(Paciente p) throws PacienteNaoExisteException, CpfJaExisteException,
            UsuarioNaoExisteException, EmailJaExisteException {
        Paciente pacienteBanco = pacienteDao.findById(p.getId());

        if (pacienteBanco == null)
            throw new PacienteNaoExisteException("Paciente com ID " + p.getId() + " não existe");

        pacienteBanco = pacienteDao.findByCpf(p.getCpf());

        if (pacienteBanco != null)
            throw new CpfJaExisteException("Paciente com CPF " + p.getCpf() + " já existe");

        usuarioService.atualizar(p.getUsuario());
        pacienteDao.update(p);
    }

    public void deletar(Paciente p) throws PacienteNaoExisteException {
        Paciente pacienteBanco = pacienteDao.findById(p.getId());

        if (pacienteBanco == null)
            throw new PacienteNaoExisteException("Paciente com ID " + p.getId() + " não existe");

        pacienteDao.deleteById(p.getId());
        // deletar consultas em cascata
    }

    public Paciente buscarPorId(int id) {
        return pacienteDao.findByIdUsuario(id);
    }

    public Paciente buscarPorCpf(String cpf) {
        return pacienteDao.findByCpf(cpf);
    }

    public Paciente buscarPorUsuario(Usuario u) {
        return pacienteDao.findByIdUsuario(u.getId());
    }

    public List<Paciente> listar() {
        return pacienteDao.findAll();
    }
}
