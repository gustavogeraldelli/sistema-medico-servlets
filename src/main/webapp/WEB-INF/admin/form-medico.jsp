<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${param.id == null ? "Criar " : "Atualizar "} médico</title>
</head>
<body>

    <h1>${param.id == null ? "Criar " : "Atualizar "} médico</h1>
    <a href="${pageContext.request.contextPath}/logout"><button>Logout</button></a>

    <p style="color:red;">
        <%= request.getAttribute("erro") != null ?
                request.getAttribute("erro") : "" %>
    </p>

    <form action="${param.id == null ? "/admin/medicos/criar" : "/admin/medicos/atualizar"}" method="post">
        <c:if test="${param.id != null}">
            <input type="hidden" name="id" value="${param.id}"/>
        </c:if>
        Email: <input type="text" name="email" placeholder="Email" />
        Senha: <input type="password" name="senha" placeholder="Senha" />
        CRM: <input type="text" name="crm" placeholder="CRM" />
        Nome: <input type="text" name="nome" placeholder="Nome" />
        Especialidade: <input type="text" name="especialidade" placeholder="Especialidade" />

        <button type="submit">Confirmar</button>
    </form>

    <a href="${pageContext.request.contextPath}/admin/medicos"><button>Voltar</button></a>

</body>
</html>