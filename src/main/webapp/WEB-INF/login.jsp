<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>

    <h2>Login</h2>

    <p style="color:red;">
        <%= request.getAttribute("erro") != null ?
                request.getAttribute("erro") : "" %>
    </p>

    <form action="login" method="post">
        <input type="text" name="email" placeholder="E-mail">
        <input type="password" name="senha" placeholder="Senha">
        <input type="submit" value="Login">
    </form>

    <a href="index"><button>Voltar</button></a>

</body>
</html>
