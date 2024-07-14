package br.ufscar.dc.dsw.sistemamedicoservlets.controllers;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.MedicoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/medicos")
public class ListaMedicosServlet extends HttpServlet {

    private MedicoService medicoService;

    @Override
    public void init() {
        medicoService = new MedicoService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String especialidade = req.getParameter("especialidade");
        List<String> especialidades = medicoService.listarEspecialidades();
        List<Medico> medicos;

        if (especialidade != null && !especialidade.isEmpty())
            medicos = medicoService.buscarPorEspecialidade(especialidade);
        else
            medicos = medicoService.listar();

        req.setAttribute("especialidade", especialidade);
        req.setAttribute("especialidades", especialidades);
        req.setAttribute("medicos", medicos);
        req.getRequestDispatcher("/WEB-INF/medico/lista-medicos.jsp").forward(req, resp);
    }
}
