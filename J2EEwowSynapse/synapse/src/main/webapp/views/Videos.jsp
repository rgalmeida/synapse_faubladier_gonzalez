<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Synapse Gaming | Videos</title>
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
    <!-- JS -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/menu.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/videos.js" />"></script>
</head>
<body>
<div id="page-wrapper">
	<div id="header-wrapper">
		<jsp:include page="portal/Header.jsp"/>
	</div>
	<div id="menu-wrapper">
		<jsp:include page="portal/Menu.jsp"/>
	</div>
	<div id="news-wrapper">
		<jsp:include page="portal/News.jsp"/>
	</div>
	<div id="videos-full-wrapper">
		<div id="videos-full-container">
			<h3>
				<span>Actus vidéos</span>
			</h3>
			<div id="find-videos" class="invisible">Rechercher une vidéo : <input type="text" placeholder="Nom de la video" class="findSomeone"/></div>
			<div id="videos_paginator">
				<a href="/synapse/videos/page/1">&laquo;</a>
				<c:forEach items="${videosPages}" var="page">
		     		<a href="${ page.path }" class="<c:if test="${ page.active }">active</c:if>">${ page.number }</a>
				</c:forEach>
				<a href="/synapse/videos/page/${ fn:length(videosPages)}">&raquo;</a>
			</div>
			<div id="videos-full">
				<c:forEach items="${videos}" var="video">
		     		<div id="video_${ video.id }" class="mix video-full" data-name="${ video.title }">
		     			<div id="video_title_${ video.id }" class="name title">${ video.title }</div>
						<div id="video_desc_${ video.id }" class="desc">${ video.description }</div>
		     			${ video.integrationCode }
		     		</div>
				</c:forEach>
			</div>
			<div id="videos_paginator">
				<a href="/synapse/videos/page/1">&laquo;</a>
				<c:forEach items="${videosPages}" var="page">
		     		<a href="${ page.path }" class="<c:if test="${ page.active }">active</c:if>">${ page.number }</a>
				</c:forEach>
				<a href="/synapse/videos/page/${ fn:length(videosPages)}">&raquo;</a>
			</div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>