<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gerenciar pacientes</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-3xl font-bold mb-6">Gerenciar pacientes</h1>

    <%@ include file="../componentes/btn-login-logout.jsp" %>

    <table class="bg-gray-800 text-white border border-gray-600 rounded w-full max-w-4xl">
        <thead>
        <tr>
            <th class="py-2 px-4">ID</th>
            <th class="py-2 px-4">Usuario</th>
            <th class="py-2 px-4">Email</th>
            <th class="py-2 px-4">CPF</th>
            <th class="py-2 px-4">Nome</th>
            <th class="py-2 px-4">Telefone</th>
            <th class="py-2 px-4">Sexo</th>
            <th class="py-2 px-4">Data de nascimento</th>
            <th class="py-2 px-4">Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="paciente" items="${pacientes}">
            <tr>
                <td class="py-2 px-4">${paciente.getId()}</td>
                <td class="py-2 px-4">${paciente.getUsuario().getId()}</td>
                <td class="py-2 px-4">${paciente.getUsuario().getEmail()}</td>
                <td class="py-2 px-4">${paciente.getCpf()}</td>
                <td class="py-2 px-4">${paciente.getNome()}</td>
                <td class="py-2 px-4">${paciente.getTelefone()}</td>
                <td class="py-2 px-4">${paciente.getSexo().getLetra()}</td>
                <td class="py-2 px-4">${paciente.getDataNascimento()}</td>
                <td class="py-2 px-4">
                    <form action="pacientes/atualizar" method="get">
                        <input type="hidden" name="id" value="${paciente.getId()}">
                        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Atualizar</button>
                    </form>
                    <form action="pacientes/deletar" method="post">
                        <input type="hidden" name="id" value="${paciente.getId()}">
                        <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Deletar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="pacientes/criar" method="get" class="mt-4">
        <button type="submit" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">Criar novo...</button>
    </form>

    <a href="${pageContext.request.contextPath}/admin"
       class="inline-block bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded mt-4">Voltar</a>

</body>
</html>