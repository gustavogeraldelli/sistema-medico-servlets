package br.ufscar.dc.dsw.sistemamedicoservlets.daos;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao extends DAO {

    public void insert(Medico m) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO medicos "
                            + "(id_usuario, crm, nome, especialidade) "
                            + "VALUES (?, ?, ?, ?)"
            );
            st.setInt(1, m.getUsuario().getId());
            st.setString(2, m.getCrm());
            st.setString(3, m.getNome());
            st.setString(4, m.getEspecialidade());

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Medico m) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "UPDATE medicos SET "
                            + "crm = ?, nome = ?, "
                            + "especialidade = ? "
                            + "WHERE id = ?"
            );
            st.setString(1, m.getCrm());
            st.setString(2, m.getNome());
            st.setString(3, m.getEspecialidade());
            st.setInt(4, m.getId());

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
                    "DELETE FROM medicos WHERE id = ?"
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

    public Medico findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM medicos WHERE id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            Medico m = null;

            if (rs.next())
                m = instanciarMedico(rs);

            st.close();
            rs.close();
            conn.close();

            return m;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Medico findByCrm(String crm) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM medicos WHERE crm = ?"
            );
            st.setString(1, crm);
            rs = st.executeQuery();

            Medico m = null;

            if (rs.next())
                m = instanciarMedico(rs);

            st.close();
            rs.close();
            conn.close();

            return m;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Medico findByIdUsuario(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM medicos WHERE id_usuario = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            Medico m = null;

            if (rs.next())
                m = instanciarMedico(rs);

            st.close();
            rs.close();
            conn.close();

            return m;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medico> findByEspecialidade(String especialidade) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM medicos WHERE especialidade = ? ORDER BY nome"
            );
            st.setString(1, especialidade);
            rs = st.executeQuery();

            List<Medico> medicos = new ArrayList<>();

            while (rs.next())
                medicos.add(instanciarMedico(rs));

            st.close();
            rs.close();
            conn.close();

            return medicos;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medico> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM medicos ORDER BY nome"
            );
            rs = st.executeQuery();

            List<Medico> medicos = new ArrayList<>();

            while (rs.next())
                medicos.add(instanciarMedico(rs));

            st.close();
            rs.close();
            conn.close();

            return medicos;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Medico instanciarMedico(ResultSet rs) throws SQLException {
        UsuarioDao usuarioDao = new UsuarioDao();
        return new Medico(
                rs.getInt("id"),
                usuarioDao.findById(rs.getInt("id_usuario")),
                rs.getString("crm"),
                rs.getString("nome"),
                rs.getString("especialidade"));
    }

    public List<String> findDistinctEspecialidade() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT DISTINCT especialidade FROM medicos"
            );
            rs = st.executeQuery();

            List<String> especialidades = new ArrayList<>();

            while (rs.next())
                especialidades.add(rs.getString("especialidade"));

            st.close();
            rs.close();
            conn.close();

            return especialidades;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
