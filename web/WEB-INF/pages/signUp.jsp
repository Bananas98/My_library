<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 01.10.2020
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.entity.Role" %>
<%@include file="header.jsp" %>


<div class="container" align="center">
    <h2><fmt:message key="library.sign_up" bundle="${bundle}"/></h2>
    <form action="./signUp" method="POST" role="form">
        <div class="form-group">
            <label for="name"><fmt:message key="library.field.name" bundle="${bundle}"/>:</label>
            <input type="name" class="form-control" id="name" placeholder=
            <fmt:message key="library.field.enter.surname" bundle="${bundle}"/> name="name">
        </div>
        <div class="form-group">
            <label for="email"><fmt:message key="library.field.phone.email" bundle="${bundle}"/>:</label>
            <input type="email" class="form-control" id="email" placeholder="123@gmail.com" name="phone-number">
        </div>
        <div class="form-group">
            <label for="password"><fmt:message key="library.field.password" bundle="${bundle}"/>:</label>
            <input type="password" class="form-control" id="password" placeholder=
            <fmt:message key="library.field.enter.password" bundle="${bundle}"/> name="password">
        </div>
        <div>

            <label for="role"><fmt:message key="registration.role" bundle="${bundle}"/></label><br/><select
                size="1" name="role" id="role">
            <option selected value="${Role.READER}"><fmt:message key="registration.role.reader"
                                                                 bundle="${bundle}"/></option>
            <option value="${Role.LIBRARIAN}"><fmt:message key="registration.role.librarian"
                                                           bundle="${bundle}"/></option>
            <option value="${Role.ADMIN}"><fmt:message key="registration.role.admin"
                                                       bundle="${bundle}"/></option>
        </select>
        </div>

        <button type="submit" class="btn btn-default"><fmt:message key="library.button.submit"
                                                                   bundle="${bundle}"/></button>
    </form>

    <c:if test="${not empty user_id}">
        <div class="alert alert-success">
            <fmt:message key="library.user_id" bundle="${bundle}"/>: "${user_id}".
        </div>
    </c:if>
</div>


<%@include file="error.jsp" %>

<%@include file="footer.jsp" %>

