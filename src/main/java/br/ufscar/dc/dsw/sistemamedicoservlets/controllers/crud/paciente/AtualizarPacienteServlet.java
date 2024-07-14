package br.ufscar.dc.dsw.sistemamedicoservlets.controllers.crud.paciente;

import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.CpfJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.EmailJaExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.PacienteNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.exceptions.UsuarioNaoExisteException;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.Sexo;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.PacienteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/admin/pacientes/atualizar")
public class AtualizarPacienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/form-paciente.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // assumindo que esse endpoint será utilizado apenas através do botão no formulário
        PacienteService pacienteService = new PacienteService();
        int id = Integer.parseInt(req.getParameter("id"));

        Paciente p = pacienteService.buscarPorId(id);
        atualizarDados(p, req);

        try {
            pacienteService.atualizar(p);
        }
        catch (CpfJaExisteException | EmailJaExisteException e) {
            System.out.println("[!] " + e.getMessage());
            req.setAttribute("erro", e.getMessage() + "!");
            req.getRequestDispatcher("/WEB-INF/admin/form-paciente.jsp").forward(req, resp);
            return;
        }
        catch (PacienteNaoExisteException e) {
            System.out.println("[!] Tentativa de atualizar um paciente com ID inexistente (ID: " + id + ")");
        }
        catch (UsuarioNaoExisteException e) {
            System.out.println("[!] Tentativa de atualizar um usuário com ID inexistente (ID: " + id + ")");
        }
        resp.sendRedirect("/admin/pacientes");
    }

    private void atualizarDados(Paciente p, HttpServletRequest req) {
        String email = req.getParameter("email");
        if (email != null && !email.isEmpty())
            p.getUsuario().setEmail(email);

        String senha = req.getParameter("senha");
        if (senha != null && !senha.isEmpty())
            p.getUsuario().setSenha(senha);

        String cpf = req.getParameter("cpf");
        if (cpf != null && !cpf.isEmpty())
            p.setCpf(cpf);

        String nome = req.getParameter("nome");
        if (nome != null && !nome.isEmpty())
            p.setNome(nome);

        String telefone = req.getParameter("telefone");
        if (telefone != null && !telefone.isEmpty())
            p.setTelefone(telefone);

        String sexoStr = req.getParameter("sexo");
        if (sexoStr != null && !sexoStr.isEmpty())
            p.setSexo(Sexo.fromLetra(sexoStr.charAt(0)));

        String dataNascimentoStr = req.getParameter("dataNascimento");
        if (dataNascimentoStr != null && !dataNascimentoStr.isEmpty())
            p.setDataNascimento(LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
