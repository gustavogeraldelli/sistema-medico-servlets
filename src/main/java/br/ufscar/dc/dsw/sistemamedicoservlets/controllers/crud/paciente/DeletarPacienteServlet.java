package br.ufscar.dc.dsw.sistemamedicoservlets.controllers.crud.paciente;

import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.PacienteNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.UsuarioNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.PacienteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/pacientes/deletar")
public class DeletarPacienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // assumindo que esse endpoint será utilizado apenas através do botão no formulário
        PacienteService pacienteService = new PacienteService();
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            pacienteService.deletar(id);
        }
        catch (PacienteNaoExisteException e) {
            System.out.println("[!] Tentativa de excluir um paciente com ID inexistente (ID: " + id + "): " + e.getMessage());
        }
        catch (UsuarioNaoExisteException e) {
            System.out.println("[!] Tentativa de excluir um usuário com ID inexistente (ID: " + id + "): " + e.getMessage());
        }
        
        resp.sendRedirect("/admin/pacientes");
    }
}
