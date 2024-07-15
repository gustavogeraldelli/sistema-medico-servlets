<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gerenciar médicos</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-3xl font-bold mb-6">Gerenciar médicos</h1>

    <%@ include file="../componentes/btn-login-logout.jsp" %>

    <table class="bg-gray-800 text-white border border-gray-600 rounded w-full max-w-4xl">
        <thead>
        <tr>
            <th class="py-2 px-4">ID</th>
            <th class="py-2 px-4">Usuario</th>
            <th class="py-2 px-4">Email</th>
            <th class="py-2 px-4">CRM</th>
            <th class="py-2 px-4">Nome</th>
            <th class="py-2 px-4">Especialidade</th>
            <th class="py-2 px-4">Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medico" items="${medicos}">
            <tr>
                <td class="py-2 px-4">${medico.getId()}</td>
                <td class="py-2 px-4">${medico.getUsuario().getId()}</td>
                <td class="py-2 px-4">${medico.getUsuario().getEmail()}</td>
                <td class="py-2 px-4">${medico.getCrm()}</td>
                <td class="py-2 px-4">${medico.getNome()}</td>
                <td class="py-2 px-4">${medico.getEspecialidade()}</td>
                <td class="py-2 px-4 space-x-2">
                    <form action="medicos/atualizar" method="get" class="inline">
                        <input type="hidden" name="id" value="${medico.getId()}">
                        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Atualizar</button>
                    </form>
                    <form action="medicos/deletar" method="post" class="inline">
                        <input type="hidden" name="id" value="${medico.getId()}">
                        <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Deletar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="medicos/criar" method="get" class="mt-4">
        <button type="submit" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">Criar novo...</button>
    </form>

    <a href="${pageContext.request.contextPath}/admin"
       class="inline-block bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded mt-4">Voltar</a>

</body>
</html>