package br.ufscar.dc.dsw.sistemamedicoservlets.controllers.crud.medico;

import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.CrmJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.EmailJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.MedicoNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.UsuarioNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.MedicoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/medicos/atualizar")
public class AtualizarMedicoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

        if (usuario == null) {
            System.out.println("[!] Tentativa de entrar em /admin sem autenticação");
            req.setAttribute("erro", "Você precisa estar autenticado para acessar essa página");
            req.getRequestDispatcher("/login").forward(req, resp);
        }
        else if (usuario.getTipoUsuario() == TipoUsuario.ADMIN)
            req.getRequestDispatcher("/WEB-INF/admin/form-medico.jsp").forward(req, resp);
        else {
            System.out.println("[!] Tentativa de entrar em /admin sem autorização (ID: " + usuario.getId() + ")");
            resp.sendRedirect("sem-autorizacao");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // assumindo que esse endpoint será utilizado apenas através do botão no formulário
        MedicoService medicoService = new MedicoService();
        int id = Integer.parseInt(req.getParameter("id"));

        Medico m = medicoService.buscarPorId(id);
        atualizarDados(m, req);

        try {
            medicoService.atualizar(m);
        }
        catch (CrmJaExisteException | EmailJaExisteException e) {
            System.out.println("[!] " + e.getMessage());
            req.setAttribute("erro", e.getMessage() + "!");
            req.getRequestDispatcher("/WEB-INF/admin/form-medico.jsp").forward(req, resp);
            return;
        }
        catch (MedicoNaoExisteException e) {
            System.out.println("[!] Tentativa de atualizar um médico com ID inexistente (ID: " + id + ")");
        }
        catch (UsuarioNaoExisteException e) {
            System.out.println("[!] Tentativa de atualizar um usuário com ID inexistente (ID: " + id + ")");
        }
        resp.sendRedirect("/admin/medicos");
    }

    private void atualizarDados(Medico m, HttpServletRequest req) {
        String email = req.getParameter("email");
        if (email != null && !email.isEmpty())
            m.getUsuario().setEmail(email);

        String senha = req.getParameter("senha");
        if (senha != null && !senha.isEmpty())
            m.getUsuario().setSenha(senha);

        String crm = req.getParameter("crm");
        if (crm != null && !crm.isEmpty())
            m.setCrm(crm);

        String nome = req.getParameter("nome");
        if (nome != null && !nome.isEmpty())
            m.setNome(nome);

        String especialidade = req.getParameter("especialidade");
        if (especialidade != null && !especialidade.isEmpty())
            m.setEspecialidade(especialidade);
    }
}
