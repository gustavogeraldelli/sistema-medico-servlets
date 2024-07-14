package br.ufscar.dc.dsw.sistemamedicoservlets.controllers.crud.medico;

import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.CrmJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.EmailJaExisteException;
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

@WebServlet("/admin/medicos/criar")
public class CriarMedicoServlet extends HttpServlet {

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
        if (camposVazios(req)) {
            System.out.println("[!] Tentativa de criar um Médico com campos vazios");
            req.setAttribute("erro", "Todos os campos devem ser preenchidos!");
            req.getRequestDispatcher("/WEB-INF/admin/form-medico.jsp").forward(req, resp);
            return;
        }

        MedicoService medicoService = new MedicoService();
        Medico m = instanciarMedico(req);

        try {
            medicoService.salvar(m);
        }
        catch (CrmJaExisteException | EmailJaExisteException e) {
            System.out.println("[!] " + e.getMessage());
            req.setAttribute("erro", e.getMessage() + "!");
            req.getRequestDispatcher("/WEB-INF/admin/form-medico.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/admin/medicos");
    }

    private boolean camposVazios(HttpServletRequest req) {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String crm = req.getParameter("crm");
        String nome = req.getParameter("nome");
        String especialidade = req.getParameter("especialidade");

        return email == null || senha == null || crm == null || nome == null || especialidade == null ||
                email.isEmpty() || senha.isEmpty() || crm.isEmpty() || nome.isEmpty();
    }

    private Medico instanciarMedico(HttpServletRequest req) {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String crm = req.getParameter("crm");
        String nome = req.getParameter("nome");
        String especialidade = req.getParameter("especialidade");
        return new Medico(new Usuario(email, senha, TipoUsuario.MEDICO),
                crm, nome, especialidade);
    }
}
