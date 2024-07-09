package br.ufscar.dc.dsw.sistemamedicoservlets.daos;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.Sexo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao extends DAO {

    public void insert(Paciente p) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO pacientes "
                            + "(id_usuario, cpf, nome, telefone, sexo, data_nasc) "
                            + "VALUES (?, ?, ?, ?, ?, ?)"
            );
            st.setInt(1, p.getUsuario().getId());
            st.setString(2, p.getCpf());
            st.setString(3, p.getNome());
            st.setString(4, p.getTelefone());
            st.setString(5, Character.toString(p.getSexo().getLetra()));
            st.setDate(6, java.sql.Date.valueOf(p.getDataNascimento()));

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Paciente p) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "UPDATE pacientes SET "
                            + "cpf = ?, nome = ?, "
                            + "telefone = ?, sexo = ?, "
                            + "data_nasc = ? "
                            + "WHERE id = ?"
            );
            st.setString(1, p.getCpf());
            st.setString(2, p.getNome());
            st.setString(3, p.getTelefone());
            st.setString(4, Character.toString(p.getSexo().getLetra()));
            st.setDate(5, java.sql.Date.valueOf(p.getDataNascimento()));
            st.setInt(6, p.getId());

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
                    "DELETE FROM pacientes WHERE id = ?"
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

    public Paciente findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM pacientes WHERE id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            Paciente p = null;

            if (rs.next())
                p = instanciarPaciente(rs);

            st.close();
            rs.close();
            conn.close();

            return p;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente findByCpf(String cpf) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM pacientes WHERE cpf = ?"
            );
            st.setString(1, cpf);
            rs = st.executeQuery();

            Paciente p = null;

            if (rs.next())
                p = instanciarPaciente(rs);

            st.close();
            rs.close();
            conn.close();

            return p;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente findByIdUsuario(int idUsuario) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM pacientes WHERE id_usuario = ?"
            );
            st.setInt(1, idUsuario);
            rs = st.executeQuery();

            Paciente p = null;

            if (rs.next())
                p = instanciarPaciente(rs);

            st.close();
            rs.close();
            conn.close();

            return p;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paciente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM pacientes ORDER BY nome"
            );
            rs = st.executeQuery();

            List<Paciente> pacientes = new ArrayList<>();

            while (rs.next())
                pacientes.add(instanciarPaciente(rs));

            st.close();
            rs.close();
            conn.close();

            return pacientes;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Paciente instanciarPaciente(ResultSet rs) throws SQLException {
        Paciente p = new Paciente(
                rs.getString("cpf"),
                rs.getString("nome"),
                rs.getString("telefone"),
                Sexo.fromLetra(rs.getString("sexo").charAt(0)),
                rs.getDate("data_nasc").toLocalDate());
        p.setId(rs.getInt("id"));
        return p;
    }
}
