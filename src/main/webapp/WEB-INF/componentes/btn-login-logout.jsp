<div class="mb-6">
    <c:choose>
        <c:when test="${sessionScope.usuario == null}">
            <a href="${pageContext.request.contextPath}/login" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded block text-center mb-4">Login</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/logout" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded block text-center mb-4">Logout</a>
        </c:otherwise>
    </c:choose>
</div>