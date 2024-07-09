package br.ufscar.dc.dsw.sistemamedicoservlets.daos;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao extends DAO {

    public void insert(Usuario u) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO usuarios "
                            + "(email, senha, tipo) "
                            + "VALUES (?, ?, ?)"
            );
            st.setString(1, u.getEmail());
            st.setString(2, u.getSenha());
            st.setInt(3, u.getTipoUsuario().getTipo());

            st.executeUpdate();
            st.close();
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Usuario u) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "UPDATE usuarios SET "
                            + "email = ?, senha = ?, "
                            + "tipo = ? "
                            + "WHERE id = ?"
            );
            st.setString(1, u.getEmail());
            st.setString(2, u.getSenha());
            st.setInt(3, u.getTipoUsuario().getTipo());
            st.setInt(4, u.getId());

            st.executeUpdate();
            st.close();
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "DELETE FROM usuarios WHERE id = ?"
            );
            st.setInt(1, id);
            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Usuario findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM usuarios WHERE id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            Usuario u = null;

            if (rs.next())
                u = instanciarUsuario(rs);

            st.close();
            rs.close();
            conn.close();

            return u;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario findByEmail(String email) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM usuarios WHERE email = ?"
            );
            st.setString(1, email);
            rs = st.executeQuery();

            Usuario u = null;

            if (rs.next())
                u = instanciarUsuario(rs);

            st.close();
            rs.close();
            conn.close();

            return u;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario instanciarUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario(
                rs.getString("email"),
                rs.getString("senha"),
                TipoUsuario.fromTipo(rs.getInt("tipo"))
        );
        u.setId(rs.getInt("id"));
        return u;
    }
}
