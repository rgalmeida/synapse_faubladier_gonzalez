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
    <title>Synapse Gaming | Blogs</title>
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/blogs.css" />">
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
		<div id="blogs-full-container">
			<h3>
				<span>Actus blogs</span>
			</h3>
			<div id="blogs_paginator">
				<a href="<c:url value="/blogs/page/1"/>">&laquo;</a>
				<c:forEach items="${blogsPages}" var="page">
		     		<a href="${ page.path }" class="<c:if test="${ page.active }">active</c:if>">${ page.number }</a>
				</c:forEach>
				<a href="<c:url value="/blogs/page/${ fn:length(blogsPages)}"/>">&raquo;</a>
			</div>
			<div id="blogs-full-articles">
				<c:forEach items="${blogs}" var="blogArticle">
		     		<div id="blogArticle_${ blogArticle.id }" class="blogArticle-full">
		     			<div id="blogArticle-img_${ blogArticle.id }" class="blogArticle-img"><img src="<c:url value="${ blogArticle.imageUrl }" />" width="200" height="150"/></div>
		     			<div id="blogArticle-title_${ blogArticle.id }" class="blogArticle-full-title"><a href="<c:url value="/blogs/${ blogArticle.id }"/>">${ blogArticle.title }</a></div>
		     			<div id="blogArticle-author-date_${ blogArticle.id }" class="blogArticle-author-date">Par ${ blogArticle.author.surname } <i>'${ blogArticle.author.nickname }'</i> ${ blogArticle.author.name } <i>le <fmt:formatDate value="${ blogArticle.dateCreate }" pattern="dd/MM/yyyy" /></i></div>
		     			<div id="blogArticle-desc_${ blogArticle.id }" class="blogArticle-full-desc">${ blogArticle.description }</div>
		     			<div id="blogArticle-read_${ blogArticle.id }" class="more-blogs"><a href="<c:url value="/blogs/${ blogArticle.id }"/>">Lire la suite</a></div>
		     		</div>
				</c:forEach>
			</div>
			<div id="blogs_paginator">
				<a href="<c:url value="/blogs/page/1"/>">&laquo;</a>
				<c:forEach items="${blogsPages}" var="page">
		     		<a href="${ page.path }" class="<c:if test="${ page.active }">active</c:if>">${ page.number }</a>
				</c:forEach>
				<a href="<c:url value="/blogs/page/${ fn:length(blogsPages)}"/>">&raquo;</a>
			</div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>