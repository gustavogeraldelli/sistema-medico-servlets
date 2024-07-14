package br.ufscar.dc.dsw.sistemamedicoservlets.controllers;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Consulta;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.ConsultaService;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.PacienteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/paciente")
public class PacienteServlet extends HttpServlet {

    private ConsultaService consultaService;
    private PacienteService pacienteService;

    @Override
    public void init() {
        consultaService = new ConsultaService();
        pacienteService = new PacienteService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

        if (usuario == null) {
            System.out.println("[!] Tentativa de entrar em /paciente sem autenticação");
            req.setAttribute("erro", "Você precisa estar autenticado para acessar essa página");
            req.getRequestDispatcher("login").forward(req, resp);
        }
        else if (usuario.getTipoUsuario() == TipoUsuario.PACIENTE) {
            carregarConsultasDoPaciente(req);
            req.getRequestDispatcher("/WEB-INF/consultas.jsp").forward(req, resp);
        }
        else {
            System.out.println("[!] Tentativa de entrar em /paciente sem autorização (ID: " + usuario.getId() + ")");
            resp.sendRedirect("sem-autorizacao");
        }
    }

    private void carregarConsultasDoPaciente(HttpServletRequest req) {
        Usuario u = (Usuario) req.getSession().getAttribute("usuario");
        Paciente p = pacienteService.buscarPorUsuario(u);
        List<Consulta> consultas = consultaService.listarPorPaciente(p);
        req.setAttribute("consultas", consultas);
    }
}
