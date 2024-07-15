<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Painel ADMIN</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-3xl font-bold mb-6">Painel ADM</h1>

    <%@ include file="../componentes/btn-login-logout.jsp" %>

    <div class="flex flex-col space-y-4">
        <a href="admin/medicos" class="bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-6 rounded text-center w-64">Gerenciar médicos</a>
        <a href="admin/pacientes" class="bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-6 rounded text-center w-64">Gerenciar pacientes</a>
    </div>

    <%@ include file="../componentes/btn-voltar-index.jsp" %>

</body>
</html>