package br.ufscar.dc.dsw.sistemamedicoservlets.services;

import br.ufscar.dc.dsw.sistemamedicoservlets.daos.MedicoDao;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.CrmJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.EmailJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.MedicoNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.UsuarioNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;

import java.util.List;

public class MedicoService {

    private final MedicoDao medicoDao;
    private final UsuarioService usuarioService;

    public MedicoService() {
        this.medicoDao = new MedicoDao();
        this.usuarioService = new UsuarioService();
    }

    public void salvar(Medico m) throws CrmJaExisteException, EmailJaExisteException {
        Medico medicoBanco = medicoDao.findByCrm(m.getCrm());

        if (medicoBanco != null)
            throw new CrmJaExisteException("Médico com CRM " + m.getCrm() + " já existe");

        usuarioService.salvar(m.getUsuario());
        Usuario usuarioSalvo = usuarioService.buscarPorEmail(m.getUsuario().getEmail());

        m.setUsuario(usuarioSalvo);
        medicoDao.insert(m);
    }

    public void atualizar(Medico m) throws MedicoNaoExisteException, CrmJaExisteException,
            UsuarioNaoExisteException, EmailJaExisteException {
        Medico medicoBanco = medicoDao.findById(m.getId());

        if (medicoBanco == null)
            throw new MedicoNaoExisteException("Médico com ID " + m.getId() + " não existe");

        medicoBanco = medicoDao.findByCrm(m.getCrm());

        if (medicoBanco != null && medicoBanco.getId() != m.getId())
            throw new CrmJaExisteException("Médico com CRM " + m.getCrm() + " já existe");

        usuarioService.atualizar(m.getUsuario());
        medicoDao.update(m);
    }

    public void deletar(int id) throws MedicoNaoExisteException, UsuarioNaoExisteException {
        Medico medicoBanco = medicoDao.findById(id);

        if (medicoBanco == null)
            throw new MedicoNaoExisteException("Médico com ID " + id + " não existe");

        usuarioService.deletar(medicoBanco.getUsuario().getId());
        medicoDao.deleteById(id);
        // deletar consultas em cascata
    }

    public Medico buscarPorId(int id) {
        return medicoDao.findById(id);
    }

    public Medico buscarPorCrm(String crm) {
        return medicoDao.findByCrm(crm);
    }

    public Medico buscarPorUsuario(Usuario u) {
        return medicoDao.findByIdUsuario(u.getId());
    }

    public List<Medico> buscarPorEspecialidade(String especialidade) {
        return medicoDao.findByEspecialidade(especialidade);
    }

    public List<Medico> listar() {
        return medicoDao.findAll();
    }

    public List<String> listarEspecialidades() {
        return medicoDao.findDistinctEspecialidade();
    }
}
