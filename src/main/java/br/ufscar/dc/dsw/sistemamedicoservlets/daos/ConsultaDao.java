package br.ufscar.dc.dsw.sistemamedicoservlets.daos;

import br.ufscar.dc.dsw.sistemamedicoservlets.models.Consulta;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Medico;
import br.ufscar.dc.dsw.sistemamedicoservlets.models.Paciente;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDao extends DAO {

    public void insert(Consulta c) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO consultas "
                            + "(id_paciente, id_medico, data_hora) "
                            + "VALUES (?, ?, ?)"
            );
            st.setInt(1, c.getPaciente().getId());
            st.setInt(2, c.getMedico().getId());
            st.setTimestamp(3, Timestamp.valueOf(c.getDataConsulta()));

            st.executeUpdate();

            st.close();
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Consulta c) {
        PreparedStatement st = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "UPDATE consultas SET "
                            + "id_paciente = ?, "
                            + "id_medico = ?, "
                            + "data_hora = ? "
                            + "WHERE id = ?"
            );
            st.setInt(1, c.getPaciente().getId());
            st.setInt(2, c.getMedico().getId());
            st.setTimestamp(3, Timestamp.valueOf(c.getDataConsulta()));
            st.setInt(4, c.getId());

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
                    "DELETE FROM consultas WHERE id = ?"
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

    public Consulta findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM consultas WHERE id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            Consulta c = null;

            if (rs.next())
                c = instanciarConsulta(rs);

            st.close();
            rs.close();
            conn.close();

            return c;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> findAllByMedico(Medico m) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM consultas WHERE id_medico = ?"
            );
            st.setInt(1, m.getId());
            rs = st.executeQuery();

            List<Consulta> consultas = new ArrayList<>();

            while (rs.next())
                consultas.add(instanciarConsulta(rs));

            st.close();
            rs.close();
            conn.close();

            return consultas;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> findAllByPaciente(Paciente p) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM consultas WHERE id_paciente = ?"
            );
            st.setInt(1, p.getId());
            rs = st.executeQuery();

            List<Consulta> consultas = new ArrayList<>();

            while (rs.next())
                consultas.add(instanciarConsulta(rs));

            st.close();
            rs.close();
            conn.close();

            return consultas;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Consulta findByMedicoAndDataConsulta(Medico m, LocalDateTime dt) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM consultas WHERE id_medico = ? AND data_hora = ?"
            );
            st.setInt(1, m.getId());
            st.setTimestamp(2, Timestamp.valueOf(dt));
            rs = st.executeQuery();

            Consulta c = null;

            if (rs.next())
                c = instanciarConsulta(rs);

            st.close();
            rs.close();
            conn.close();

            return c;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Consulta findByPacienteAndDataConsulta(Paciente p, LocalDateTime dt) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM consultas WHERE id_paciente = ? AND data_hora = ?"
            );
            st.setInt(1, p.getId());
            st.setTimestamp(2, Timestamp.valueOf(dt));
            rs = st.executeQuery();

            Consulta c = null;

            if (rs.next())
                c = instanciarConsulta(rs);

            st.close();
            rs.close();
            conn.close();

            return c;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            st = conn.prepareStatement(
                    "SELECT * FROM consultas"
            );
            rs = st.executeQuery();

            List<Consulta> consultas = new ArrayList<>();

            while (rs.next())
                consultas.add(instanciarConsulta(rs));

            st.close();
            rs.close();
            conn.close();

            return consultas;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Consulta instanciarConsulta(ResultSet rs) throws SQLException {
        MedicoDao medicoDao = new MedicoDao();
        PacienteDao pacienteDao = new PacienteDao();
        return new Consulta(
                rs.getInt("id"),
                medicoDao.findById(rs.getInt("id_medico")),
                pacienteDao.findById(rs.getInt("id_paciente")),
                rs.getTimestamp("data_hora").toLocalDateTime());
    }
}
