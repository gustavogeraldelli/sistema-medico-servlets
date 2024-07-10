<c:choose>
    <c:when test="${usuario == null}">
        <a href="login"><button>Login</button></a>
    </c:when>
    <c:otherwise>
        <a href="logout"><button>Logout</button></a>
    </c:otherwise>
</c:choose>