<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Suas consultas</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-3xl font-bold mb-6">Suas consultas</h1>

    <%@ include file="componentes/btn-login-logout.jsp" %>

    <table class="bg-gray-800 text-white border border-gray-600 rounded mx-auto">
        <thead>
        <tr>
            <th class="py-2 px-4">Data</th>
            <th class="py-2 px-4">Hora</th>
            <th class="py-2 px-4">${sessionScope.usuario.getTipoUsuario().getTipo() == 2 ? "Paciente" : "Médico"}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="consulta" items="${consultas}">
            <tr>
                <td class="py-2 px-4">${consulta.getDataConsulta().toLocalDate()}</td>
                <td class="py-2 px-4">${consulta.getDataConsulta().toLocalTime()}</td>
                <td class="py-2 px-4">${sessionScope.usuario.getTipoUsuario().getTipo() == 2 ?
                        consulta.getPaciente().getNome() : consulta.getMedico().getNome()}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%@ include file="componentes/btn-voltar-index.jsp" %>

</body>
</html>
