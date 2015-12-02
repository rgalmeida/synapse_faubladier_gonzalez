<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="header-container">
	<div id="header-logo">
		<a href="<c:url value="/" />"><img src="<c:url value="/resources/img/logo2.png" />" border="0" width="162" height="40"/></a>
	</div>
	<div id="header-membership">
		<c:if test="${ empty user || user.mail == null}">
			<div id="header-membership-subscribe">
				<a href="/synapse/user/subscribe" id="header-subscribe" class="in">Inscription</a>
			</div>
			<div id="header-membership-signin">
				<a href="/synapse/user/signin" id="header-register" class="in">Connexion</a>
			</div>
		</c:if>
		<c:if test="${ !empty user && user.mail != null }">
			<div id="header-membership-account">
				Bienvenue <a href="<c:url value="/user/${ user.id }" />">${ user.surname} <b><i>'${ user.nickname }'</i></b> ${ user.name}</a>
				<c:forEach items="${sessionScope.userResources}" var="resource">
     				 | <a href="<c:url value="${ resource.path }"/>">${ resource.name }</a>
				</c:forEach>

				 | <a href="/synapse/user/logout">Déconnexion</a>
			</div>
		</c:if>
	</div>
</div>
