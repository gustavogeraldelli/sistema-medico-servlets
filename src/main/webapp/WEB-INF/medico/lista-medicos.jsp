<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Médicos</title>
</head>
<body>

    <h1>Lista de médicos</h1>
    <%@ include file="../componentes/btn-login-logout.jsp" %>

    <form action="medicos" method="get">
        Filtrar por Especialidade:
        <select name="especialidade">
            <option value="">Todas</option>
            <c:forEach var="esp" items="${especialidades}">
                <option value="${esp}" ${esp == especialidade ? 'selected' : ''}>${esp}</option>
            </c:forEach>
        </select>
        <button type="submit">Filtrar</button>
    </form>

    <table>
        <thead>
        <tr>
            <th>Nome</th>
            <th>Especialidade</th>
            <th>Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medico" items="${medicos}">
            <tr>
                <td>${medico.getNome()}</td>
                <td>${medico.getEspecialidade()}</td>
                <td>
                    <form action="agendar" method="get">
                        <input type="hidden" name="idMedico" value="${medico.getId()}">
                        <button type="submit">Agendar consulta</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="index"><button>Voltar</button></a>

</body>
</html>