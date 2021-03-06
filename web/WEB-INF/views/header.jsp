<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">
                <fmt:message key="header.library" bundle="${lang}"/>
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/Controller?command=mainMenu">
                    <fmt:message key="header.mainPage" bundle="${lang}"/>
                </a></li>
                <c:if test="${!user.admin}">
                    <li><a href="/Controller?command=readerInfo">
                        <fmt:message key="header.myBooks" bundle="${lang}"/>

                    </a></li>
                </c:if>
                <c:if test="${user.admin}">
                    <li><a href="/Controller?command=readers">
                        <fmt:message key="header.readers" bundle="${lang}"/>
                    </a></li>
                    <li><a href="/Controller?command=orders">
                        <fmt:message key="header.orders" bundle="${lang}"/>

                    </a></li>
                    <li><a href="/Controller?command=readingRoom">
                        <fmt:message key="header.readingRoom" bundle="${lang}"/>

                    </a></li>

                    <li><a href="/Controller?command=addBook">
                        <fmt:message key="header.addBook" bundle="${lang}"/>
                    </a></li>
                    <li><a href="/Controller?command=addAuthor">
                        <fmt:message key="header.addAG" bundle="${lang}"/>
                    </a></li>
                </c:if>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/Controller?command=switchLang&lang=ru_RU&&page=main">
                    <fmt:message key="header.ru" bundle="${lang}"/>
                </a></li>
                <li><a href="/Controller?command=switchLang&lang=en_US&&page=main">
                    <fmt:message key="header.en" bundle="${lang}"/>
                </a></li>
                <li></li>
                <li>
                    <a href="/Controller?command=logout">
                        <fmt:message key="header.logout" bundle="${lang}"/>
                    </a>
                </li>
            </ul>


        </div>
    </div>
</nav>

<h3><c:out value="${user.name}"/>, <fmt:message key="header.hello" bundle="${lang}"/></h3>
<hr/>




