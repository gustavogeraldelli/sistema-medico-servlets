<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">

    <h2 class="text-3xl font-bold mb-4">Login</h2>

    <p style="color: red;">
        ${erro}
    </p>

    <form action="login" method="post" class="bg-gray-800 p-8 rounded shadow-lg max-w-md w-full">
        <div class="mb-4">
            <input type="text" name="email" placeholder="E-mail" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-4">
            <input type="password" name="senha" placeholder="Senha" class="bg-gray-700 text-white border-2 border-gray-600 rounded py-2 px-4 w-full focus:outline-none focus:border-blue-500">
        </div>
        <div class="mb-6">
            <input type="submit" value="Login" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full">
        </div>
    </form>

    <%@ include file="componentes/btn-voltar-index.jsp" %>

</body>
</html>
