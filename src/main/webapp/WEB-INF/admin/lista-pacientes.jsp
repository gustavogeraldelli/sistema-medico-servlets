<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gerenciar pacientes</title>
</head>
<body>

    <h1>Gerenciar pacientes</h1>
    <a href="${pageContext.request.contextPath}/logout"><button>Logout</button></a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Email</th>
            <th>CPF</th>
            <th>Nome</th>
            <th>Telefone</th>
            <th>Sexo</th>
            <th>Data de nascimento</th>
            <th>Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="paciente" items="${pacientes}">
            <tr>
                <td>${paciente.getId()}</td>
                <td>${paciente.getUsuario().getId()}</td>
                <td>${paciente.getUsuario().getEmail()}</td>
                <td>${paciente.getCpf()}</td>
                <td>${paciente.getNome()}</td>
                <td>${paciente.getTelefone()}</td>
                <td>${paciente.getSexo().getLetra()}</td>
                <td>${paciente.getDataNascimento()}</td>
                <td>
                    <form action="pacientes/atualizar" method="get">
                        <input type="hidden" name="id" value="${paciente.getId()}">
                        <button type="submit">Atualizar</button>
                    </form>
                    <form action="pacientes/criar" method="post">
                        <input type="hidden" name="id" value="${paciente.getId()}">
                        <button type="submit">Deletar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="pacientes/criar" method="get">
        <button type="submit">Criar novo...</button>
    </form>
    <a href="${pageContext.request.contextPath}/admin"><button>Voltar</button></a>

</body>
</html>