package br.ufscar.dc.dsw.sistemamedicoservlets.services;

import br.ufscar.dc.dsw.sistemamedicoservlets.daos.UsuarioDao;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.EmailJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.UsuarioNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;

public class UsuarioService {

    private final UsuarioDao usuarioDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
    }

    public void salvar(Usuario u) throws EmailJaExisteException {
        Usuario usuarioBanco = usuarioDao.findByEmail(u.getEmail());

        if (usuarioBanco != null)
            throw new EmailJaExisteException("Usuário com email " + u.getEmail() + " já existe");

        usuarioDao.insert(u);
    }

    public void atualizar(Usuario u) throws UsuarioNaoExisteException, EmailJaExisteException {
        Usuario usuarioBanco = usuarioDao.findById(u.getId());

        if (usuarioBanco == null)
            throw new UsuarioNaoExisteException("Usuário com ID " + u.getId() + " não existe");

        usuarioBanco = usuarioDao.findByEmail(u.getEmail());

        if (usuarioBanco != null)
            throw new EmailJaExisteException("Usuário com email " + u.getEmail() + " já existe");

        usuarioDao.update(u);
    }

    public void deletar(int id) throws UsuarioNaoExisteException {
        Usuario usuarioBanco = usuarioDao.findById(id);

        if (usuarioBanco == null)
            throw new UsuarioNaoExisteException("Usuário com ID " + id + " não existe");

        usuarioDao.deleteById(id);
    }

    public Usuario buscarPorId(int id) {
        return usuarioDao.findById(id);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioDao.findByEmail(email);
    }
}
