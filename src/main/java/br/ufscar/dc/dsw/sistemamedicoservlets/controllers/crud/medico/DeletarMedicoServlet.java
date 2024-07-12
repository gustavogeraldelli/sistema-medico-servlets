package br.ufscar.dc.dsw.sistemamedicoservlets.controllers.crud.medico;

import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.MedicoNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.MedicoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/medicos/deletar")
public class DeletarMedicoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // assumindo que esse endpoint será utilizado apenas através do botão no formulário
        MedicoService medicoService = new MedicoService();
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            medicoService.deletar(id);
        }
        catch (MedicoNaoExisteException e) {
            System.out.println("[!] Tentativa de excluir um médico com ID inexistente (ID: " + id + "): " + e.getMessage());
        }
        resp.sendRedirect("/admin/medicos");
    }
}
