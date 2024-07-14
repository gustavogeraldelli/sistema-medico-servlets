<%@ page import="br.ufscar.dc.dsw.sistemamedicoservlets.models.enums.TipoUsuario" %>
<%@ page import="br.ufscar.dc.dsw.sistemamedicoservlets.models.Usuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Suas consultas</title>
</head>
<body>

    <h1>Suas consultas</h1>
    <a href="logout"><button>Logout</button></a>

    <table>
        <thead>
        <tr>
            <th>Data</th>
            <th>Hora</th>
            <th>${sessionScope.usuario.getTipoUsuario() == TipoUsuario.MEDICO ? "Paciente" : "Médico"}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="consulta" items="${consultas}">
            <tr>
                <td>${consulta.getDataConsulta().toLocalDate()}</td>
                <td>${consulta.getDataConsulta().toLocalTime()}</td>
                <td>${sessionScope.usuario.getTipoUsuario() == TipoUsuario.MEDICO ?
                    consulta.getPaciente().getNome() : consulta.getMedico().getNome()}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="index"><button>Voltar</button></a>

</body>
</html>