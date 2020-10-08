<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 29.09.2020
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>


<div class="container" align="center">
    <h2><fmt:message key="library.login" bundle="${bundle}"/></h2>
    <form action="./login" method="POST" role="form">
        <div class="form-group">
            <label for=><fmt:message key="library.field.enter.email" bundle="${bundle}" />:</label>
            <input class="form-control" id="email" placeholder="123@gmail.com" name="email">
        </div>
        <div class="form-group">
            <label for="password"><fmt:message key="library.field.password" bundle="${bundle}" />:</label>
            <input type="password" class="form-control" id="password"
                   placeholder=<fmt:message key="library.field.enter.password" bundle="${bundle}"/> name="password">
        </div>
        <button type="submit" class="btn btn-default"><fmt:message key="library.button.submit" bundle="${bundle}" /></button>
    </form>
</div>


<%@include file="error.jsp"%>

<%@include file="footer.jsp"%>
