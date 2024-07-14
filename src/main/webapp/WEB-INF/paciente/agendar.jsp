<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agendar consulta</title>
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
<body>

    <h1>Agendar consulta com ${nomeMedico}</h1>
    <a href="${pageContext.request.contextPath}/logout"><button>Logout</button></a>


    <p style="color:red;">
        <%= request.getAttribute("erro") != null ?
                request.getAttribute("erro") : "" %>
    </p>

    <form action="agendar" method="post">
        <input type="hidden" name="idMedico" value="${param.idMedico}"/>
        Data: <input type="date" name="data" placeholder="Data da consulta" />
        Horário: <select name="hora"></select>

        <button type="submit">Confirmar</button>
    </form>

    <a href="${pageContext.request.contextPath}/medicos"><button>Voltar</button></a>

</body>
</html>