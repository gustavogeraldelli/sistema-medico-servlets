<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${param.id == null ? "Criar " : "Atualizar "} paciente</title>
</head>|
<body>

    <h1>${param.id == null ? "Criar " : "Atualizar "} paciente</h1>
    <a href="${pageContext.request.contextPath}/logout"><button>Logout</button></a>

    <p style="color:red;">
        <%= request.getAttribute("erro") != null ?
                request.getAttribute("erro") : "" %>
    </p>

    <form action="${param.id == null ? "/admin/pacientes/criar" : "/admin/pacientes/atualizar"}" method="post">
        <c:if test="${param.id != null}">
            <input type="hidden" name="id" value="${param.id}"/>
        </c:if>
        Email: <input type="text" name="email" placeholder="Email" />
        Senha: <input type="password" name="senha" placeholder="Senha" />
        CPF: <input type="text" name="cpf" placeholder="CPF" />
        Nome: <input type="text" name="nome" placeholder="Nome" />
        Telefone: <input type="text" name="telefone" placeholder="Telefone" />
        Sexo:
        <select name="sexo">
            <option value="M">Masculino</option>
            <option value="F">Feminino</option>
        </select>
        Data de nascimento: <input type="date" name="dataNascimento" placeholder="Data de nascimento" />

        <button type="submit">Confirmar</button>
    </form>

    <a href="${pageContext.request.contextPath}/admin/pacientes"><button>Voltar</button></a>

</body>
</html>