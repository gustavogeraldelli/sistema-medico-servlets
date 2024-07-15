<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${param.id == null ? "Criar " : "Atualizar "} paciente</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>|
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-3xl font-bold mb-6">${param.id == null ? "Criar " : "Atualizar "} paciente</h1>

    <%@ include file="../componentes/btn-login-logout.jsp" %>

    <p style="color: red;">
        ${erro}
    </p>

    <form action="${param.id == null ? "/admin/pacientes/criar" : "/admin/pacientes/atualizar"}" method="post" class="bg-gray-800 p-8 rounded shadow-lg max-w-md w-full">
        <c:if test="${param.id != null}">
            <input type="hidden" name="id" value="${param.id}"/>
        </c:if>
        <div class="mb-4">
            <label for="email" class="block text-sm font-medium text-gray-400 mb-1">Email:</label>
            <input type="text" id="email" name="email" placeholder="Email" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-4">
            <label for="senha" class="block text-sm font-medium text-gray-400 mb-1">Senha:</label>
            <input type="password" id="senha" name="senha" placeholder="Senha" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-4">
            <label for="cpf" class="block text-sm font-medium text-gray-400 mb-1">CPF:</label>
            <input type="text" id="cpf" name="cpf" placeholder="CPF" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-4">
            <label for="nome" class="block text-sm font-medium text-gray-400 mb-1">Nome:</label>
            <input type="text" id="nome" name="nome" placeholder="Nome" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-4">
            <label for="telefone" class="block text-sm font-medium text-gray-400 mb-1">Telefone:</label>
            <input type="text" id="telefone" name="telefone" placeholder="Telefone" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-4">
            <label for="sexo" class="block text-sm font-medium text-gray-400 mb-1">Sexo:</label>
            <select id="sexo" name="sexo" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
                <option value="M">Masculino</option>
                <option value="F">Feminino</option>
            </select>
        </div>
        <div class="mb-6">
            <label for="dataNascimento" class="block text-sm font-medium text-gray-400 mb-1">Data de nascimento:</label>
            <input type="date" id="dataNascimento" name="dataNascimento" placeholder="Data de nascimento" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div>
            <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full">Confirmar</button>
        </div>
    </form>

    <a href="${pageContext.request.contextPath}/admin/pacientes"
       class="inline-block bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded mt-4">Voltar</a>

</body>
</html>