<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>

<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-4xl font-bold mb-6">Sistema Médico</h1>

    <%@ include file="WEB-INF/componentes/btn-login-logout.jsp" %>

    <div class="flex flex-col space-y-4">
        <a href="medicos" class="bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-6 rounded text-center w-64">Ver médicos</a>
        <a href="medico" class="bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-6 rounded text-center w-64">Página do médico</a>
        <a href="paciente" class="bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-6 rounded text-center w-64">Página do paciente</a>
        <c:if test="${sessionScope.usuario != null && sessionScope.usuario.getTipoUsuario().getTipo() == 1}">
            <a href="admin" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-6 rounded text-center w-64">Painel ADMIN</a>
        </c:if>
    </div>

</body>

</html>