package br.ufscar.dc.dsw.sistemamedicoservlets.controllers;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.MedicoService;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.PacienteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/medicos", "/admin/pacientes"})
public class MostrarTabelasAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

        if (usuario == null) {
            System.out.println("[!] Tentativa de entrar em " + path + " sem autenticação");
            req.setAttribute("erro", "Você precisa estar autenticado para acessar essa página");
            req.getRequestDispatcher("/login").forward(req, resp);
        }
        else if (usuario.getTipoUsuario() == TipoUsuario.ADMIN) {
            if (path.equals("/admin/medicos")) {
                carregarMedicos(req);
                req.getRequestDispatcher("/WEB-INF/admin/lista-medicos.jsp").forward(req, resp);
            }
            else {
                carregarPacientes(req);
                req.getRequestDispatcher("/WEB-INF/admin/lista-pacientes.jsp").forward(req, resp);
            }
        }
        else {
            System.out.println("[!] Tentativa de entrar em " + path + " sem autorização (ID: " + usuario.getId() + ")");
            resp.sendRedirect("sem-autorizacao");
        }
    }

    private void carregarMedicos(HttpServletRequest req) {
        MedicoService medicoService = new MedicoService();
        List<Medico> medicos = medicoService.listar();
        req.setAttribute("medicos", medicos);
    }

    private void carregarPacientes(HttpServletRequest req) {
        PacienteService pacienteService = new PacienteService();
        List<Paciente> pacientes = pacienteService.listar();
        req.setAttribute("pacientes", pacientes);
    }
}
