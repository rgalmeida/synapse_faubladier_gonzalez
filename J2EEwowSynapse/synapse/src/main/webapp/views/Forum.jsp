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
    <title>Synapse Gaming | Forum</title>
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/forum.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css" />">
    <!-- JS -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/menu.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/forum_events.js" />"></script>
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
		<div id="forum-container">
			<c:if test="${ empty user || user.mail == null }">
				<div id="forum-info" class="info">Vous devez être identifié pour participer aux forums. <a href="<c:url value="/user/signin"/>">Se connecter</a>.</div>
			</c:if>
			<br/>
			<div id="forums">
				<c:forEach items="${forums}" var="forum">
					<div id="forum-name-${ forum.id }" class="forum-name">${ forum.name }</div>
					<div id="forum-name-${ forum.id }-header" class="forum-name-header">
						<div id="forum-name-${ forum.id }-header-cat" class="forum-name-header-cell forum-name-header-category">Categories</div>
						<div id="forum-name-${ forum.id }-header-topics" class="forum-name-header-cell forum-name-header-topics">Sujets</div>
						<div id="forum-name-${ forum.id }-header-last-reply" class="forum-name-header-cell forum-name-header-last-reply">Dernière réponse</div>
					</div>
					<div id="forum-${ forum.id }-categories" class="forum-categories">
						<c:forEach items="${forum.categories}" var="category">
							<div id="forum-category-${ category.id }" class="forum-category">
								<jsp:useBean id="today" class="java.util.Date" />
								<c:if test="${ (today.time/100000 - category.lastReply.dateCreate.time/100000) > 6048}"><c:set var="category_img" scope="request" value="2"/></c:if>
								<c:if test="${ (today.time/100000 - category.lastReply.dateCreate.time/100000) <= 6048}"><c:set var="category_img" scope="request" value="1"/></c:if>
								<div id="forum-category-${ category.id }-logo" class="forum-category-cell forum-category-logo"><img src="<c:url value="/resources/img/forum_cat_${ category_img }.png"/>"/></div>
								<div id="forum-category-${ category.id }-cat-desc" class="forum-category-name-desc">
									<div id="forum-category-${ category.id }-cat" class="forum-category-cell forum-category-name"><a href="<c:url value="/forum/category/${ category.id }"/>">${ category.name }</a></div>
									<div id="forum-category-${ category.id }-desc" class="forum-category-cell forum-category-desc">${ category.desc }</div>
								</div>
								<div id="forum-category-${ category.id }-nb-posts" class="forum-category-cell forum-category-nb-posts">${fn:length(category.posts) }</div>
								<div id="forum-category-${ category.id }-last-reply" class="forum-category-cell forum-category-last-reply"><a href="<c:url value="/forum/post/${ category.lastReply.post.id }/#reply-${ category.lastReply.id }-author-avatar"/>">${ category.lastReply.author.nickname} <br/> <fmt:formatDate value="${ category.lastReply.dateCreate}" pattern="dd/MM/yyyy à HH:mm" /></a></div>
							</div>
						</c:forEach>
						<div id="categories-clear" class="clear"></div>
					</div>
					<br/>
				</c:forEach>
			</div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>