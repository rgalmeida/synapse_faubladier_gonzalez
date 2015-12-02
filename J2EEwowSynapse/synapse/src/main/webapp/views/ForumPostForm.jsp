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
    <title>Synapse Gaming | ${ category.name } - Nouveau post</title>
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
    <script type="text/javascript" src="<c:url value="/resources/js/nicedit.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/forum.js" />"></script>
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
			<div id="breadcrumb-top" class="breadcrumb">
				<a href="<c:url value="/forum/"/>">Forum</a> &gt;
				<a href="<c:url value="/forum/category/${ category.id }"/>">${ category.forum.name } &gt; 
				${ category.name }</a> &gt; 
				<c:if test="${ post.id == null }">Créer un Post <c:set var="action" scope="request" value="add"/></c:if>
				<c:if test="${ post.id > 0 }">Modifier un Post <c:set var="action" scope="request" value="edit"/><c:set var="postId" scope="request" value="${ post.id }/"/></c:if>
			</div>
			<div id="forum-info" class="info">Info : vous pourrez modifier votre post jusqu'à une heure après sa publication.</div><br/>
			<div id="forum-post-new">
				<form id="forum-post-new-form" action="<c:url value="/forum/category/${ category.id }/post/${ postId }${ action }"/>" method="post">
					<div id="forum-post-new-title"><b>Titre : * </b>&nbsp;<input type="text" name="newPostTitle" id="newPostTitle" value="${ post.title }"/></div>
					<div id="forum-post-new-content-label">Contenu : * </div>
					<div id="forum-post-new-content">
						<textarea id="post-content" name="newPostContent" cols="130" rows="20">
							${ post.content }
						</textarea>
					</div>
					<div id="forum-post-new-submit">
						<input type="submit" name="addPost" id="addPost" value="Soumettre"/>
					</div>
				</form>
			</div>
			<div id="breadcrumb-bottom" class="breadcrumb">
				<a href="<c:url value="/forum/"/>">Forum</a> &gt;
				<a href="<c:url value="/forum/category/${ category.id }"/>">${ category.forum.name } &gt; 
				${ category.name }</a> &gt; 
				Nouveau Post
			</div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>