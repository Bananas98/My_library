<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 29.09.2020
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${not empty error}">

    <div class="alert alert-danger" align="center">
        <strong>
            <fmt:message key="${error}" bundle="${bundle}" />
        </strong>
    </div>
</c:if>
