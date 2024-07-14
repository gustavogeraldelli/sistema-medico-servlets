package br.ufscar.dc.dsw.sistemamedicoservlets.controllers;

import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.MedicoJaTemConsultaException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.PacienteJaTemConsultaException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Consulta;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.ConsultaService;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.MedicoService;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.PacienteService;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@WebServlet("/agendar")
public class AgendarConsultaServlet extends HttpServlet {

    private UsuarioService usuarioService;
    private MedicoService medicoService;
    private PacienteService pacienteService;
    private ConsultaService consultaService;

    @Override
    public void init() throws ServletException {
        this.usuarioService = new UsuarioService();
        this.medicoService = new MedicoService();
        this.pacienteService = new PacienteService();
        this.consultaService = new ConsultaService();
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
            int idMedico = Integer.parseInt(req.getParameter("idMedico"));
            Medico m = medicoService.buscarPorId(idMedico);
            req.setAttribute("nomeMedico", m.getNome());
            req.getRequestDispatcher("/WEB-INF/paciente/agendar.jsp").forward(req, resp);
        }
        else {
            System.out.println("[!] Tentativa de entrar em /paciente sem autorização (ID: " + usuario.getId() + ")");
            resp.sendRedirect("sem-autorizacao");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (camposVazios(req)) {
            System.out.println("[!] Tentativa de agendar uma Consulta com campos vazios");
            req.setAttribute("erro", "Todos os campos devem ser preenchidos!");
            req.getRequestDispatcher("/WEB-INF/paciente/agendar.jsp").forward(req, resp);
            return;
        }

        String dataStr = req.getParameter("data");
        String horaStr = req.getParameter("hora");
        LocalDateTime dataConsulta = LocalDateTime.of(
                LocalDate.parse(dataStr), LocalTime.parse(horaStr));

        int idMedico = Integer.parseInt(req.getParameter("idMedico"));
        Medico m = medicoService.buscarPorId(idMedico);

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        Paciente p = pacienteService.buscarPorUsuario(usuario);

        Consulta consulta = new Consulta(m, p, dataConsulta);

        try {
            consultaService.salvar(consulta);
        }
        catch (MedicoJaTemConsultaException | PacienteJaTemConsultaException e) {
            System.out.println("[!] " + e.getMessage());
            req.setAttribute("erro", e.getMessage() + "!");
            req.setAttribute("nomeMedico", m.getNome());
            req.getRequestDispatcher("/WEB-INF/paciente/agendar.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/paciente");
    }

    private boolean camposVazios(HttpServletRequest req) {
        String dataStr = req.getParameter("data");
        String horaStr = req.getParameter("hora");
        return dataStr == null || horaStr == null || dataStr.isEmpty() || horaStr.isEmpty();
    }
}
