package br.ufscar.dc.dsw.sistemamedicoservlets.controllers;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.services.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        usuarioService = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("usuario") == null)
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        else
            resp.sendRedirect("index");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            req.setAttribute("erro", "Preencha todos os campos!");
            System.out.println("[!] Tentativa de login com campos vazios");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        Usuario usuarioBanco = usuarioService.buscarPorEmail(email);

        if (usuarioBanco == null || !senha.equals(usuarioBanco.getSenha())) {
            req.setAttribute("erro", "Credenciais incorretas!");
            System.out.println("[!] Tentativa de login com credenciais inválidas");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("usuario", usuarioBanco);
        resp.sendRedirect(req.getContextPath() + "index");
    }
}
