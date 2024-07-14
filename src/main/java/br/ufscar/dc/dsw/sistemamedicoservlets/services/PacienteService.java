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
        usuarioService.salvar(p.getUsuario());
        Usuario usuarioSalvo = usuarioService.buscarPorEmail(p.getUsuario().getEmail());

        Paciente pacienteBanco = pacienteDao.findByCpf(p.getCpf());

        if (pacienteBanco != null)
            throw new CpfJaExisteException("Paciente com CPF " + p.getCpf() + " já existe");

        p.setUsuario(usuarioSalvo);
        pacienteDao.insert(p);
    }

    public void atualizar(Paciente p) throws PacienteNaoExisteException, CpfJaExisteException,
            UsuarioNaoExisteException, EmailJaExisteException {
        Paciente pacienteBanco = pacienteDao.findById(p.getId());

        if (pacienteBanco == null)
            throw new PacienteNaoExisteException("Paciente com ID " + p.getId() + " não existe");

        pacienteBanco = pacienteDao.findByCpf(p.getCpf());

        if (pacienteBanco != null && pacienteBanco.getId() != p.getId())
            throw new CpfJaExisteException("Paciente com CPF " + p.getCpf() + " já existe");

        usuarioService.atualizar(p.getUsuario());
        pacienteDao.update(p);
    }

    public void deletar(int id) throws PacienteNaoExisteException, UsuarioNaoExisteException {
        Paciente pacienteBanco = pacienteDao.findById(id);

        if (pacienteBanco == null)
            throw new PacienteNaoExisteException("Paciente com ID " + id + " não existe");

        usuarioService.deletar(pacienteBanco.getUsuario().getId());
        pacienteDao.deleteById(id);
        // deletar consultas em cascata
    }

    public Paciente buscarPorId(int id) {
        return pacienteDao.findById(id);
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
