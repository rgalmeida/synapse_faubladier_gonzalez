<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Synapse Gaming | ${ user.surname} '${ user.nickname }' ${ user.name}</title>
    <!-- Meta -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- CSS -->
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/menu.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/news.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/slider.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/user.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.datetimepicker.css" />">
    <!-- JS -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/common.js" />"></script>
</head>
<body>
<div id="page-wrapper">
	<div id="header-wrapper">
		<jsp:include page="portal/Header.jsp"/>
	</div>
	<div id="persistent-menu-wrapper">
		<jsp:include page="portal/PersistentMenu.jsp"/>
	</div>
	<div id="menu-wrapper">
		<jsp:include page="portal/Menu.jsp"/>
	</div>
	<div id="news-wrapper">
		<jsp:include page="portal/News.jsp"/>
	</div>
	<div id="middle-full-wrapper">
		<div id="user-container">
            <div id="user-profile">
            <h3>Mes informations personnelles</h3>
            <div id="user-profile-info" class="info">
                Le pseudonyme et le royaume sont utilisés pour récupérer votre avatar. Ils doivent parfaitement correspondre à ceux de votre personnage.
            </div>
                <div id="user-profile-avatar" class="user-profile-avatar">
                    <form action="<c:url value="/user/avatar/update"/>" method="get">
                        <img src="<c:url value="${ user.forumAvatar }"/>"/>
                        <input type="submit" name="updateAvatar" value="Actualiser" class="btn-small"/>
                    </form>
                </div>
                <form:form id="user-profile-form" action="/user/edit" commandName="userForm" method="POST">
                 <div id="user-profile-info" class="user-profile-info">
                     <div id="user-profile-nickname-label" class="user-label">
                         <label for="user-profile-nickname-field">Pseudonyme en jeu : </label>
                     </div>
                     <div id="user-profile-nickname-field" class="user-field">
                         <form:input path="nickname" type="text" placeholder="${ user.nickname }"/>
                     </div>
                     <div id="user-profile-realm-label" class="user-label">
                         <label for="user-profile-realm-field">Royaume : </label>
                     </div>
                     <div id="user-profile-realm-field" class="user-field">
                         <form:select path="realm.id" id="realms" class="select">
                            <form:option value="0">Choisissez votre royaume</form:option>
                            <c:forEach items="${realms}" var="realm">
                                <c:choose>
                                 <c:when test="${ realm.id == user.realm.id }">
                                    <c:set var="selected" scope="request" value="selected"/>
                                 </c:when>
                                 <c:otherwise>
                                    <c:set var="selected" scope="request" value=""/>
                                 </c:otherwise>
                                </c:choose>
                                <form:option value="${realm.id}" selected="${ selected }">${realm.name}</form:option>
                            </c:forEach>
                        </form:select>
                     </div>
                     <div id="user-profile-name-label" class="user-label">
                         <label for="user-profile-name-field">Nom <i>(facultatif)</i> : </label>
                     </div>
                     <div id="user-profile-name-field" class="user-field">
                         <form:input path="name" type="text" placeholder="${ user.name }"/>
                     </div>
                     <div id="user-profile-surname-label" class="user-label">
                         <label for="user-profile-surname-field">Prénom <i>(facultatif)</i> : </label>
                     </div>
                     <div id="user-profile-surname-field" class="user-field">
                         <form:input path="surname" type="text" placeholder="${ user.surname }"/>
                     </div>
                     <div id="user-profile-email-label" class="user-label">
                         <label for="user-profile-email-field">E-mail : </label>
                     </div>
                     <div id="user-profile-email-field" class="user-field">
                         <form:input path="mail" type="text" placeholder="${ user.mail }"/>
                     </div>
                     <div id="user-profile-race-label" class="user-label">
                         <label for="user-profile-race-field">Race : </label>
                     </div>
                     <div id="user-profile-race-field" class="user-field">
                         <form:input path="race" type="text" value="${ user.race.name }" disabled="true"/>
                     </div>
                     <div id="user-profile-clazz-label" class="user-label">
                         <label for="user-profile-clazz-field">Classe : </label>
                     </div>
                     <div id="user-profile-clazz-field" class="user-field">
                         <form:input path="clazz" type="text" value="${ user.clazz.name }" disabled="true"/>
                     </div>
                     <div id="user-profile-spec-label" class="user-label">
                         <label for="user-profile-spec-field">Spécialisation : </label>
                     </div>
                     <div id="user-profile-spec-field" class="user-field">
                        <form:select path="spec.id" id="specialization" class="select">
                            <form:option value="0">Choisissez votre spécialisation</form:option>
                            <c:forEach items="${specializations}" var="spec">
                                <c:choose>
                                 <c:when test="${ spec.id == user.spec.id }">
                                    <c:set var="selected" scope="request" value="selected"/>
                                 </c:when>
                                 <c:otherwise>
                                    <c:set var="selected" scope="request" value=""/>
                                 </c:otherwise>
                                </c:choose>
                                <form:option value="${spec.id}" selected="${ selected }">${spec.name}</form:option>
                            </c:forEach>
                        </form:select>
                     </div>
                     <div id="user-profile-birth-label" class="user-label">
                         <label for="user-profile-birth-field">Naissance <i>(facultatif)</i> : </label>
                     </div>
                     <div id="user-profile-birth-field" class="user-field">
                        <fmt:formatDate value="${ user.birth }" pattern="dd/MM/yyyy" var="formattedBirth"/>
                        <form:input path="birth" type="text" class="date" placeholder="${ formattedBirth }"/>
                     </div>
                     <div id="user-profile-phone-label" class="user-label">
                         <label for="user-profile-phone-field">Téléphone : </label>
                     </div>
                     <div id="user-profile-phone-field" class="user-field">
                         <form:input path="phone" type="text" value="${ user.phone }"/>
                     </div>
                 </div>
                 <div id="user-profile-action" class="user-profile-action">
                    <input type="submit" value="Sauvegarder mes informations" class="btn"/>
                 </div>
                </form:form>
            </div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>