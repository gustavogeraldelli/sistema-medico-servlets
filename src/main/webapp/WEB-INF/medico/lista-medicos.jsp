<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Médicos</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-3xl font-bold mb-6">Lista de médicos</h1>

    <%@ include file="../componentes/btn-login-logout.jsp" %>

    <form action="medicos" method="get" class="mb-6">
        <label for="especialidade" class="mr-2">Filtrar por Especialidade:</label>
        <select name="especialidade" id="especialidade" class="bg-gray-800 text-white border border-gray-600 rounded py-2 px-4 mr-2">
            <option value="">Todas</option>
            <c:forEach var="esp" items="${especialidades}">
                <option value="${esp}" ${esp == especialidade ? 'selected' : ''}>${esp}</option>
            </c:forEach>
        </select>
        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Filtrar</button>
    </form>

    <table class="bg-gray-800 text-white border border-gray-600 rounded mx-auto">
        <thead>
        <tr>
            <th class="py-2 px-4">Nome</th>
            <th class="py-2 px-4">Especialidade</th>
            <th class="py-2 px-4">Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medico" items="${medicos}">
            <tr>
                <td class="py-2 px-4">${medico.getNome()}</td>
                <td class="py-2 px-4">${medico.getEspecialidade()}</td>
                <td class="py-2 px-4">
                    <form action="agendar" method="get">
                        <input type="hidden" name="idMedico" value="${medico.getId()}">
                        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Agendar consulta</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="index" class="mt-6 inline-block bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded">Voltar</a>

</body>
</html>