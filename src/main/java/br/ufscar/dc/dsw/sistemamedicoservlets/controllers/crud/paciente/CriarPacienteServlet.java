package br.ufscar.dc.dsw.sistemamedicoservlets.controllers.crud.paciente;

import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.CpfJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.EmailJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.Sexo;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.PacienteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/admin/pacientes/criar")
public class CriarPacienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

        if (usuario == null) {
            System.out.println("[!] Tentativa de entrar em /admin sem autenticação");
            req.setAttribute("erro", "Você precisa estar autenticado para acessar essa página");
            req.getRequestDispatcher("/login").forward(req, resp);
        }
        else if (usuario.getTipoUsuario() == TipoUsuario.ADMIN)
            req.getRequestDispatcher("/WEB-INF/admin/form-paciente.jsp").forward(req, resp);
        else {
            System.out.println("[!] Tentativa de entrar em /admin sem autorização (ID: " + usuario.getId() + ")");
            resp.sendRedirect("sem-autorizacao");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (camposVazios(req)) {
            System.out.println("[!] Tentativa de criar um Paciente com campos vazios");
            req.setAttribute("erro", "Todos os campos devem ser preenchidos!");
            req.getRequestDispatcher("/WEB-INF/admin/form-paciente.jsp").forward(req, resp);
            return;
        }

        PacienteService pacienteService = new PacienteService();
        Paciente p = instanciarPaciente(req);

        try {
            pacienteService.salvar(p);
        } catch (CpfJaExisteException | EmailJaExisteException e) {
            System.out.println("[!] " + e.getMessage());
            req.setAttribute("erro", e.getMessage() + "!");
            req.getRequestDispatcher("/WEB-INF/admin/form-paciente.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/admin/pacientes");
    }

    private boolean camposVazios(HttpServletRequest req) {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String cpf = req.getParameter("cpf");
        String nome = req.getParameter("nome");
        String telefone = req.getParameter("telefone");
        String sexoStr = req.getParameter("sexo");
        String dataNascimentoStr = req.getParameter("dataNascimento");

        return email == null || senha == null || cpf == null || nome == null || telefone == null ||
                sexoStr == null || dataNascimentoStr == null || email.isEmpty() || senha.isEmpty() ||
                cpf.isEmpty() || nome.isEmpty() || telefone.isEmpty() || sexoStr.isEmpty() ||
                dataNascimentoStr.isEmpty();
    }

    private Paciente instanciarPaciente(HttpServletRequest req) {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String cpf = req.getParameter("cpf");
        String nome = req.getParameter("nome");
        String telefone = req.getParameter("telefone");
        String sexoStr = req.getParameter("sexo");
        String dataNascimentoStr = req.getParameter("dataNascimento");
        return new Paciente(new Usuario(email, senha, TipoUsuario.PACIENTE),
                cpf, nome, telefone, Sexo.fromLetra(sexoStr.charAt(0)),
                LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
