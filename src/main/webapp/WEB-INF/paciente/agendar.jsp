<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agendar consulta</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script>
        function horarios() {
            var select = document.getElementsByName("hora")[0]
            var times = [
                "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
                "11:00", "11:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"
            ]

            for (var i = 0; i < times.length; i++) {
                var option = document.createElement("option");
                option.value = times[i];
                option.text = times[i];
                select.appendChild(option);
            }
        }
        window.onload = horarios;
    </script>
</head>
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h1 class="text-3xl font-bold mb-6">Agendar consulta com ${nomeMedico}</h1>

    <%@ include file="../componentes/btn-login-logout.jsp" %>

    <p class="mb-4 text-red-500">
        ${erro}
    </p>

    <form action="agendar" method="post" class="mb-4">
        <input type="hidden" name="idMedico" value="${param.idMedico}"/>
        <label class="block mb-2">Data:</label>
        <input type="date" name="data" placeholder="Data da consulta" class="bg-gray-800 text-white border border-gray-600 rounded py-2 px-4 mb-2">

        <label class="block mb-2">Horário:</label>
        <select name="hora" class="bg-gray-800 text-white border border-gray-600 rounded py-2 px-4 mb-4"></select>

        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Confirmar</button>
    </form>

    <a href="${pageContext.request.contextPath}/medicos"
       class="inline-block bg-gray-600 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded mt-4">Voltar</a>

</body>
</html>