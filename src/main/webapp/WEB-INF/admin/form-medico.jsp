<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${param.id == null ? "Criar " : "Atualizar "} médico</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-3xl font-bold mb-6">${param.id == null ? "Criar " : "Atualizar "} médico</h1>

    <%@ include file="../componentes/btn-login-logout.jsp" %>

    <p style="color: red;">
        ${erro}
    </p>

    <form action="${param.id == null ? "/admin/medicos/criar" : "/admin/medicos/atualizar"}" method="post" class="bg-gray-800 p-8 rounded shadow-lg max-w-md w-full">
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
            <label for="crm" class="block text-sm font-medium text-gray-400 mb-1">CRM:</label>
            <input type="text" id="crm" name="crm" placeholder="CRM" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-4">
            <label for="nome" class="block text-sm font-medium text-gray-400 mb-1">Nome:</label>
            <input type="text" id="nome" name="nome" placeholder="Nome" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-6">
            <label for="especialidade" class="block text-sm font-medium text-gray-400 mb-1">Especialidade:</label>
            <input type="text" id="especialidade" name="especialidade" placeholder="Especialidade" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div>
            <button type="submit" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded w-full">Confirmar</button>
        </div>
    </form>

    <a href="${pageContext.request.contextPath}/admin/medicos"
       class="mt-4 inline-block bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded">Voltar</a>

</body>
</html>