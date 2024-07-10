<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>

    <h1>Home</h1>
    <%@ include file="WEB-INF/componentes/btn-login-logout.jsp" %>
    <a href="medicos"><button>Ver médicos</button></a>
    <a href="medico"><button>Página do médico</button></a>
    <a href="paciente"><button>Página do paciente</button></a>
    <a href="admin"><button>Dashboard ADMIN</button></a>

</body>
</html>