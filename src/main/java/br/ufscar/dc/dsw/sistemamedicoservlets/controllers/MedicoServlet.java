package br.ufscar.dc.dsw.sistemamedicoservlets.controllers;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Consulta;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.ConsultaService;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.MedicoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/medico")
public class MedicoServlet extends HttpServlet {

    private ConsultaService consultaService;
    private MedicoService medicoService;

    @Override
    public void init() {
        consultaService = new ConsultaService();
        medicoService = new MedicoService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

        if (usuario == null) {
            System.out.println("[!] Tentativa de entrar em /medico sem autenticação");
            req.setAttribute("erro", "Você precisa estar autenticado para acessar essa página");
            req.getRequestDispatcher("login").forward(req, resp);
        }
        else if (usuario.getTipoUsuario() == TipoUsuario.MEDICO) {
            carregarConsultasDoMedico(req);
            req.getRequestDispatcher("/WEB-INF/consultas.jsp").forward(req, resp);
        }
        else {
            System.out.println("[!] Tentativa de entrar em /medico sem autorização (ID: " + usuario.getId() + ")");
            resp.sendRedirect("sem-autorizacao");
        }
    }

    private void carregarConsultasDoMedico(HttpServletRequest req) {
        Usuario u = (Usuario) req.getSession().getAttribute("usuario");
        Medico m = medicoService.buscarPorUsuario(u);
        List<Consulta> consultas = consultaService.listarPorMedico(m);
        req.setAttribute("consultas", consultas);
    }
}
