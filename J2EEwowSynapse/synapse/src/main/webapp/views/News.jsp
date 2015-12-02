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
    <title>Synapse Gaming | News</title>
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
    <!-- JS -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/menu.js" />"></script>
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
		<div id="news-full-container">
			<h3>
				<span>News</span>
			</h3>
			<div id="news_paginator">
				<a href="<c:url value="/news/page/1" />">&laquo;</a>
				<c:forEach items="${newsPages}" var="page">
		     		<a href="${ page.path }" class="<c:if test="${ page.active }">active</c:if>">${ page.number }</a>
				</c:forEach>
				<a href="<c:url value="/news/page/${ fn:length(newsPages)}" />">&raquo;</a>
			</div>
			<div id="news">
				<c:forEach items="${news}" var="newsArticle">
		     		<div id="newsArticle_${ newsArticle.id }" class="newsArticle">
		     			<div id="newsArticle-img_${ newsArticle.id }" class="newsArticle-img"><img src="<c:url value="${ newsArticle.imageUrl }" />"/></div>
		     			<div id="newsArticle-title_${ newsArticle.id }" class="newsArticle-title"><a href="<c:url value="/news/${ newsArticle.id }" />">${ newsArticle.title }</a></div>
		     			<div id="newsArticle-author-date_${ newsArticle.id }" class="newsArticle-author-date">Par ${ newsArticle.author.surname } <i>'${ newsArticle.author.nickname }'</i> ${ newsArticle.author.name } <i>le <fmt:formatDate value="${ newsArticle.dateCreate }" pattern="dd/MM/yyyy" /></i></div>
		     			<div id="newsArticle-desc_${ newsArticle.id }" class="newsArticle-desc">${ newsArticle.description }</div>
		     			<div id="newsArticle-read_${ newsArticle.id }" class="more-news"><a href="<c:url value="/news/${ newsArticle.id }" />">Lire la suite</a></div>
		     		</div>
				</c:forEach>
			</div>
			<div id="news_paginator">
				<a href="<c:url value="/news/page/1" />">&laquo;</a>
				<c:forEach items="${newsPages}" var="page">
		     		<a href="${ page.path }" class="<c:if test="${ page.active }">active</c:if>">${ page.number }</a>
				</c:forEach>
				<a href="<c:url value="/news/page/${ fn:length(newsPages)}" />">&raquo;</a>
			</div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>