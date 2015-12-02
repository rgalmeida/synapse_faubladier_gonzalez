<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Synapse Gaming | Inscription</title>
    <!-- Meta -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- CSS -->
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/img/favicon.ico" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/subscribe.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/menu.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.datetimepicker.css" />">
    <!-- JS -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/subscribe.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/common.js" />"></script>
</head>
<body>
<div id="page-wrapper">
	<div id="header-wrapper">
		<jsp:include page="portal/Header.jsp"/>
	</div>
	<div id="subscribe-wrapper">
		<div id="subscribe-container">
			<div id="subscribe-left">
				<div id="subscribe-title1" class="h1-red">Inscrivez-vous</div>
				<div id="subscribe-title2" class="h1-green">maintenant et :</div>
				<div id="subscribe-illustration">
					<img src="<c:url value="/resources/img/bnet.jpg" />"/>
				</div>
				<div id="subscribe-promo">
					<ul>
						<li>Accédez à un espace privé de guilde pour mieux intégrer la vie communautaire du groupe</li>
						<li>Profitez du système paramétrable de notification d'évènements raids ou autres</li>
						<li>Inscrivez-vous aux différents évènements de guilde programmés à l'avance : raids, défis mais aussi notre célèbre Synapseton</li>
						<li>Tenez-vous informé de l'évolution interne de la communauté Synapse</li>
						<li>Participez à la vie de notre site web avec vos blogs, streamings...</li>
					</ul>
				</div>
			</div>
			<div id="subscribe-right">
				<div id="error" class="error">${ error }</div>
				<div id="warning" class="warn">${ warn }</div>
				<div id="info" class="info">${ info }</div><br/>
				<form:form id="subscribe-form" action="/user/subscribe" commandName="userForm" method="POST">
					<div id="label-email" class="label"><label for="email">E-mail *</label><form:errors path="mail" cssClass="validator"/></div>
					<div id="field-email" class="field"><form:input path="mail" type="text" name="email" id="email" placeholder="Votre adresse email"/></div>
					
					
					<div id="label-password" class="label"><label for="password">Mot de passe *</label><form:errors path="password" cssClass="validator"/></div>
					<div id="field-password" class="field"><form:input path="password" type="password" name="password" id="password" placeholder="Votre mot de passe"/></div>

					<div id="label-nickname" class="label"><label for="nickname">Pseudonyme en jeu *</label><form:errors path="nickname" cssClass="validator"/></div>
					<div id="field-nickname" class="field"><form:input path="nickname" type="text" name="nickname" id="nickname" placeholder="Votre personnage principal"/></div>
					
					<div id="label-realm" class="label"><label for="realm">Royaume *</label><form:errors path="realm.id" cssClass="validator"/></div>
					<div id="field-realm" class="field">
						<form:select path="realm.id" id="realm" class="select">
							<option value="0">Choisissez votre royaume</option>
 							<c:forEach items="${realms}" var="realm">
                  				<form:option value="${realm.id}">${realm.name}</form:option>
                  			</c:forEach>
    					</form:select>
					</div>
					
					<div id="label-race" class="label"><label for="race">Race *</label><form:errors path="race.id" cssClass="validator"/></div>
					<div id="field-race" class="field">
						<form:select path="race.id" id="race" class="select">
							<option value="0">Choisissez votre race</option>
 							<c:forEach items="${races}" var="race">
                  				<form:option value="${race.id}">${race.name}</form:option>
                  			</c:forEach>
    					</form:select>
					</div>
					
					
					<div id="label-classe" class="label"><label for="classe">Classe *</label><form:errors path="clazz.id" cssClass="validator"/></div>
					<div id="field-classe" class="field">
						<form:select path="clazz.id" id="classe" class="select">
							<option value="0">Choisissez votre classe</option>
 							<c:forEach items="${classes}" var="clazz">
                  				<form:option value="${clazz.id}">${clazz.name}</form:option>
                  			</c:forEach>
    					</form:select>
					</div>
					
					
					<div id="label-specialisation" class="label"><label for="specialisation">Spécialisation *</label><form:errors path="spec.id" cssClass="validator"/></div>
					<div id="field-specialisation" class="field">
						<form:select path="spec.id" id="specialization" class="select">
							<option value="0">Choisissez votre spécialisation</option>
 							<c:forEach items="${specializations}" var="spec">
                  				<form:option value="${spec.id}">${spec.name}</form:option>
                  			</c:forEach>
    					</form:select>
					</div>
					
					
					<div id="label-name" class="label"><label for="name">Nom <i><font size="2">(Susceptible d'apparaître sur le site)</font></i></label><form:errors path="name" class="validator"/></div>
					<div id="field-name" class="field"><form:input path="name" type="text" name="name" id="name" placeholder="Votre nom de famille"/></div>
					
					
					<div id="label-surname" class="label"><label for="surname">Prénom <i><font size="2">(Susceptible d'apparaître sur le site)</font></i></label><form:errors path="surname" cssClass="validator"/></div>
					<div id="field-surname" class="field"><form:input path="surname" type="text" name="surname" id="surname" placeholder="Votre prénom"/></div>
					
					
					<div id="label-birth" class="label"><label for="birth">Date de naissance </label><form:errors path="birth" cssClass="validator"/></div>
					<div id="field-birth" class="field">
						<form:input path="birth" type="text" name="birth" id="birth" class="date" placeholder="Jour/Mois/Année"/>
					</div>
					
					
					<div id="radio-legals"><form:checkbox path="legalsAccepted"/> J'accepte les <a href="<c:url value="/legals" />" target="_blank">conditions générales d'utilisation</a><br/><form:errors path="legalsAccepted" class="validator-legals"/></div>
					
					
					<div id="submit-subscribe"><input type="submit" name="subscribe" value="Inscription"/></div>
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