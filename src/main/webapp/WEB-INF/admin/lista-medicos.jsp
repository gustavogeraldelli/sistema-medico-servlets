<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gerenciar médicos</title>
</head>
<body>

    <h1>Gerenciar médicos</h1>
    <a href="${pageContext.request.contextPath}/logout"><button>Logout</button></a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Email</th>
            <th>CRM</th>
            <th>Nome</th>
            <th>Especialidade</th>
            <th>Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medico" items="${medicos}">
            <tr>
                <td>${medico.getId()}</td>
                <td>${medico.getUsuario().getId()}</td>
                <td>${medico.getUsuario().getEmail()}</td>
                <td>${medico.getCrm()}</td>
                <td>${medico.getNome()}</td>
                <td>${medico.getEspecialidade()}</td>
                <td>
                    <form action="medicos/atualizar" method="get">
                        <input type="hidden" name="id" value="${medico.getId()}">
                        <button type="submit">Atualizar</button>
                    </form>
                    <form action="medicos/deletar" method="post">
                        <input type="hidden" name="id" value="${medico.getId()}">
                        <button type="submit">Deletar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="medicos/criar" method="get">
        <button type="submit">Criar novo...</button>
    </form>
    <a href="${pageContext.request.contextPath}/admin"><button>Voltar</button></a>

</body>
</html>