<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Synapse Gaming | Calendar : ${event.raid.name} le <fmt:formatDate value="${event.date}" pattern="dd MMM yyyy" /> à <fmt:formatDate value="${event.startTime}" pattern="HH:mm" /></title>
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/raid.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.datetimepicker.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.fancybox.css" />">
    <!-- JS -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
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
	<div id="middle-wrapper">
		<div id="middle-container">
			<h1>${event.raid.name} le <fmt:formatDate value="${event.date}" pattern="dd MMM yyyy" /> à <fmt:formatDate value="${event.startTime}" pattern="HH:mm" /></h1>
			<h2>Inscriptions</h2>
			<div class="event-subscribe-actions">
				<a href="<c:url value="/raids/event/subscribe/${event.id}" />">M'inscrire</a> -
				<a href="<c:url value="/raids/event/unsubscribe/${event.id}" />">Me désinscrire</a> -
				<!--<c:if test="${ !empty user && user.id != null && user.group.id < 3 }"><a href="<c:url value="#" />">Tout valider</a> -</c:if>-->
				<a href="<c:url value="/raids" />">Retour</a>
			</div>
			<div id="event-subscribers-tanks" class="event-subscriber-column event-subscriber-column-border-right">
				<h3>Tanks</h3>
				<ul id="event-subscribers-tanks-list">
					<c:forEach items="${tanks}" var="tank">
			     		<li>
			     			<img src="<c:url value="${tank.spec.img}"/>" width="20" height="20"/>
			     			<c:choose>
				     			<c:when test="${ !empty user && user.id != null && user.id == tank.id }">
				     				<b><font color="red">${tank.nickname}</font></b>
				     			</c:when>
				     			<c:otherwise>
				     				${tank.nickname}
				     			</c:otherwise>
			     			</c:choose> 
			     			<!--<c:if test="${ !empty user && user.id != null && user.group.id < 3 }">
			     				<a href="<c:url value="/raids/event/${event.id}/roster/add/${tank.id}" />">Valider</a>
			     			</c:if>-->
			     		</li>
					</c:forEach>
				</ul>
			</div>
			<div id="event-subscribers-melees" class="event-subscriber-column event-subscriber-column-border-right">
				<h3>Melees</h3>
				<ul id="event-subscribers-melee">
					<c:forEach items="${melees}" var="melee">
			     		<li>
			     			<img src="<c:url value="${melee.spec.img}"/>" width="20" height="20"/>
			     			<c:choose>
				     			<c:when test="${ !empty user && user.id != null && user.id == melee.id }">
				     				<b><font color="red">${melee.nickname}</font></b>
				     			</c:when>
				     			<c:otherwise>
				     				${melee.nickname}
				     			</c:otherwise>
			     			</c:choose> 
			     			<!--<c:if test="${ !empty user && user.id != null && user.group.id < 3 }">
			     				<a href="<c:url value="/raids/event/${event.id}/roster/add/${melee.id}" />">Valider</a>
			     			</c:if>-->
			     		</li>
					</c:forEach>
				</ul>
			</div>
			<div id="event-subscribers-rangers" class="event-subscriber-column event-subscriber-column-border-right">
				<h3>Rangers</h3>
				<ul id="event-subscribers-range">
					<c:forEach items="${rangers}" var="ranger">
			     		<li>
			     			<img src="<c:url value="${ranger.spec.img}"/>" width="20" height="20"/>
			     			<c:choose>
				     			<c:when test="${ !empty user && user.id != null && user.id == ranger.id }">
				     				<b><font color="red">${ranger.nickname}</font></b>
				     			</c:when>
				     			<c:otherwise>
				     				${ranger.nickname}
				     			</c:otherwise>
			     			</c:choose> 
			     			<!--<c:if test="${ !empty user && user.id != null && user.group.id < 3 }">
			     				<a href="<c:url value="/raids/event/${event.id}/roster/add/${ranger.id}" />">Valider</a>
			     			</c:if>-->
			     		</li>
					</c:forEach>
				</ul>
			</div>
			<div id="event-subscribers-heals" class="event-subscriber-column">
				<h3>Heals</h3>
				<ul id="event-subscribers-heal">
					<c:forEach items="${heals}" var="heal">
			     		<li>
			     			<img src="<c:url value="${heal.spec.img}"/>" width="20" height="20"/>
			     			<c:choose>
				     			<c:when test="${ !empty user && user.id != null && user.id == heal.id }">
				     				<b><font color="red">${heal.nickname}</font></b>
				     			</c:when>
				     			<c:otherwise>
				     				${heal.nickname}
				     			</c:otherwise>
			     			</c:choose>
			     			<!--<c:if test="${ !empty user && user.id != null && user.group.id < 3 }">
			     				<a href="<c:url value="/raids/event/${event.id}/roster/add/${heal.id}" />">Valider</a>
			     			</c:if>-->
			     		</li>
					</c:forEach>
				</ul>
			</div>
			<!-- 
			<h2>Roster retenu</h2>
			<div id="event-roster-tanks" class="event-subscriber-column event-subscriber-column-border-right">
				<h3>Tanks</h3>
				<ul id="event-roster-tanks-list">
					<c:forEach items="${rosterTanks}" var="rosterTank">
			     		<li>
			     			<img src="<c:url value="${rosterTank.spec.img}"/>" width="20" height="20"/>
			     			<c:choose>
				     			<c:when test="${ !empty user && user.id != null && user.id == rosterTank.id }">
				     				<b><font color="red">${rosterTank.nickname}</font></b>
				     			</c:when>
				     			<c:otherwise>
				     				${rosterTank.nickname}
				     			</c:otherwise>
			     			</c:choose> 
			     			<c:if test="${ !empty user && user.id != null && user.group.id < 3 }">
			     				<a href="<c:url value="/raids/event/${event.id}/roster/remove/${rosterTank.id}" />">Dévalider</a>
			     			</c:if>
			     		</li>
					</c:forEach>
				</ul>
			</div>
			<div id="event-roster-melees" class="event-subscriber-column event-subscriber-column-border-right">
				<h3>Melees</h3>
				<ul id="event-roster-melee">
					<c:forEach items="${rosterMelees}" var="rosterMelee">
			     		<li>
			     			<img src="<c:url value="${rosterMelee.spec.img}"/>" width="20" height="20"/>
			     			<c:choose>
				     			<c:when test="${ !empty user && user.id != null && user.id == rosterMelee.id }">
				     				<b><font color="red">${rosterMelee.nickname}</font></b>
				     			</c:when>
				     			<c:otherwise>
				     				${rosterMelee.nickname}
				     			</c:otherwise>
			     			</c:choose> 
			     			<c:if test="${ !empty user && user.id != null && user.group.id < 3 }">
			     				<a href="<c:url value="/raids/event/${event.id}/roster/remove/${rosterMelee.id}" />">Dévalider</a>
			     			</c:if>
			     		</li>
					</c:forEach>
				</ul>
			</div>
			<div id="event-roster-rangers" class="event-subscriber-column event-subscriber-column-border-right">
				<h3>Rangers</h3>
				<ul id="event-roster-range">
					<c:forEach items="${rosterRangers}" var="rosterRanger">
			     		<li>
			     			<img src="<c:url value="${rosterRanger.spec.img}"/>" width="20" height="20"/>
			     			<c:choose>
				     			<c:when test="${ !empty user && user.id != null && user.id == rosterRanger.id }">
				     				<b><font color="red">${rosterRanger.nickname}</font></b>
				     			</c:when>
				     			<c:otherwise>
				     				${rosterRanger.nickname}
				     			</c:otherwise>
			     			</c:choose> 
			     			<c:if test="${ !empty user && user.id != null && user.group.id < 3 }">
			     				<a href="<c:url value="/raids/event/${event.id}/roster/remove/${rosterRanger.id}" />">Dévalider</a>
			     			</c:if>
			     		</li>
					</c:forEach>
				</ul>
			</div>
			<div id="event-roster-heals" class="event-subscriber-column">
				<h3>Heals</h3>
				<ul id="event-roster-heal">
					<c:forEach items="${rosterHeals}" var="rosterHeal">
			     		<li>
			     			<img src="<c:url value="${rosterHeal.spec.img}"/>" width="20" height="20"/>
			     			<c:choose>
				     			<c:when test="${ !empty user && user.id != null && user.id == rosterHeal.id }">
				     				<b><font color="red">${rosterHeal.nickname}</font></b>
				     			</c:when>
				     			<c:otherwise>
				     				${rosterHeal.nickname}
				     			</c:otherwise>
			     			</c:choose>
			     			<c:if test="${ !empty user && user.id != null && user.group.id < 3 }">
			     				<a href="<c:url value="/raids/event/${event.id}/roster/remove/${rosterHeal.id}" />">Dévalider</a>
			     			</c:if>
			     		</li>
					</c:forEach>
				</ul>
			</div>
			-->
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>