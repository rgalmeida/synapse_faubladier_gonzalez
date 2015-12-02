<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Synapse Gaming | Calendar</title>
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/videos.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/blogs.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/achievement.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/recruitment.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/JQcalendar.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/raid.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.datetimepicker.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.fancybox.css" />">
    <!-- JS -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.calendar.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.fancybox.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/raid.js" />"></script>
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
			<div id="calendar" class="calendar"></div>
			<div id="event-form" class="invisible">
				<div id="extension-image">
						<img src="<c:url value="${extension.imageUrl}"/>" width="240" height="125"/>
				</div>
				<form:form id="eventForm" action="/raids/event/add" commandName="eventForm" method="post">
					<div id="event-form-content">
						<div id="label_event_name" class="event-label">
							<label for="event-name">Raid <font color="red">*</font> </label>
						</div>
						<div id="raids" class="event-input-container">
							<form:select id="raid" path="raid.id">
								<option value="0">-- Choisissez un raid --</option>
								<c:forEach items="${raids}" var="raid">
	                  				<option value="${raid.id}">${raid.name}</option>
	                  			</c:forEach>
							</form:select>
						</div>
						<div id="label_event_hexagram" class="event-label">
							<label for="event-hexagram">Acronyme <font color="red">*</font> </label>
						</div>
						<div id="event-hexagram" class="event-input-container">
							<form:input type="text" name="eventHexagram" id="eventHexagram" path="name" size="6" maxlength="6" readonly="true" class="readonly"/>
						</div>
						<div id="label_event_difficulty" class="event-label">
							<label for="event-difficulty">Difficulté <font color="red">*</font> </label>
						</div>
						<div id="event-difficulty" class="event-input-container">
							<form:select name="difficulty" id="difficulty" path="difficulty.id">
								<option value="0">-- Choisissez une difficulté --</option>
								<c:forEach items="${difficulties}" var="difficulty">
	                  				<option value="${difficulty.id}">${difficulty.name}</option>
	                  			</c:forEach>
							</form:select>
						</div>
						<div id="label_event_date" class="event-label">
							<label for="event-date">Date du raid <font color="red">*</font> </label>
						</div>
						<div id="event-date" class="event-input-container">
							<form:input type="text" name="eventDate" id="eventDate" path="date" readonly="true" class="readonly"/>
						</div>
						<div id="label_event_author" class="event-label">
							<label for="event-author">Créé par <font color="red">*</font> </label>
						</div>
						<div id="event-author" class="event-input-container">
							<form:input type="hidden" name="eventAuthor" id="eventAuthor" path="creator.id" value="${ user.id }"/>
							<input type="text" name="eventAuthorName" id="eventAuthorName" value="${ user.nickname }" disabled="disabled"/>
							<input type="hidden" name="currentUserGroup" id="currentUserGroup" value="${ user.group.id }"/>
						</div>
						<div id="label_event_time_start" class="event-label">
							<label for="event-time-start">Début du raid <font color="red">*</font> </label>
						</div>
						<div id="event-time-start" class="event-input-container">
							<form:input type="text" name="eventTimeStart" id="eventTimeStart" path="startTime" value="20:45" class="time"/>
						</div>
						<div id="label_event_time_stop" class="event-label">
							<label for="event-time-stop">Fin du raid <font color="red">*</font> </label>
						</div>
						<div id="event-time-stop" class="event-input-container">
							<form:input type="text" name="eventTimeStop" id="eventTimeStop" path="stopTime" value="00:00" class="time"/>
						</div>
						<div id="label_event_status" class="event-label">
							<label for="event-status">Inscriptions <font color="red">*</font></label>
						</div>
						<div id="event-status" class="event-input-container">
							<form:radiobutton path="state.id" value="1" checked="checked"/>Ouvertes
							<form:radiobutton path="state.id" value="2"/>Fermées
						</div>
					</div>
					<div id="create-event">
						<input type="submit" name="createEvent" id="createEvent" value="Créer l'évènement"/>
					</div>
				</form:form>
				<div id="event-image">
					<img src="<c:url value="/resources/img/raid_door.jpg"/>"/>
				</div>
			</div>
			<div id="event-form-copy" class="invisible event-label">
				<form action="<c:url value="/raids/event/copy"/>" method="post">
					<div id="label-copy-event" class="label-event-copy">Evènement à copier : </div>
					<div id="eventToCopy-input" class="input-event-copy">
						<select name="eventToCopy" id="eventToCopy">
							<option value="0">-- Choisissez un event --</option>
						</select>
					</div>
					<div id="label-copy-event-date" class="label-event-copy">Date à laquelle copier l'évènement : </div>
					<div id="dateEventToCopy-input" class="input-event-copy">
						<input type="text" id="dateEventToCopy" name="dateEventToCopy" class="date"/>
					</div>
					<br/><br/><br/><br/>
					<input type="submit" name="copyEventToDay" id="copyEventToDay" value="Copier"/>
				</form>
			</div>
			<div id="raid-clear" class="clear"></div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>