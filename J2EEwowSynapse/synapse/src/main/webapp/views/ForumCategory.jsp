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
			<div id="breadcrumb-top" class="breadcrumb">
				<a href="<c:url value="/forum/"/>">Forum</a> &gt;
				${ category.forum.name } &gt; 
				${ category.name }
			</div>
			<c:if test="${ !empty user && user.mail != null }">
				<div id="post-actions-top" class="post-actions">
					<form action="<c:url value="/forum/category/${ category.id }/post/new"/>" method="post">
						<input type="submit" name="newPost" id="newPostTop" value="Créer un post" class="forum-button"/>
					</form>
				</div>
			</c:if>
			<div id="category-posts">
				<div id="forum-category-name" class="forum-name">
					${ category.name }
				</div>
				<div id="forum-category-header" class="forum-name-header">
					<div id="forum-category-header-posts" class="forum-name-header-cell forum-name-header-posts">Posts</div>
					<div id="forum-category-header-replies" class="forum-name-header-cell forum-name-header-replies">Réponses</div>
					<div id="forum-category-header-lastupdates" class="forum-name-header-cell forum-name-header-lastupdates">Dernière modification</div>
					<div id="forum-category-header-authors" class="forum-name-header-cell forum-name-header-authors">Auteurs</div>
				</div>
				<div id="forum-category-posts" class="forum-categories">
					<c:forEach items="${ category.posts }" var="post">
						<div id="forum-category-post-${ post.id }-title" class="cell-post-title"><a href="<c:url value="/forum/post/${ post.id }"/>">${ post.title }</a><c:if test="${post.locked}"> <font color="red" size="2">- [Verrouillé]</font></c:if></div>
						<div id="forum-category-post-${ post.id }-nb-replies" class="cell-post-nb-reply">${ fn:length(post.replies) }</div>
						<div id="forum-category-post-${ post.id }-lastupdate" class="cell-post-lastupdate">&nbsp;${ post.lastUpdateUser.nickname } <c:if test="${ !empty post.dateUpdate && post.dateUpdate != null }">le <fmt:formatDate value="${ post.dateUpdate }" pattern="dd/MM/yyyy à HH:mm" /></c:if></div>
						<div id="forum-category-post-${ post.id }-author" class="cell-post-author">${ post.author.nickname } le <fmt:formatDate value="${ post.dateCreate }" pattern="dd/MM/yyyy à HH:mm" /></div>
					</c:forEach>
					<div id="posts-clear" class="clear"></div>
				</div>
			</div>
			<c:if test="${ !empty user && user.mail != null }">
				<div id="post-actions-bottom" class="post-actions">
					<form action="<c:url value="/forum/category/${ category.id }/post/new"/>" method="post">
						<input type="submit" name="newPost" id="newPostBottom" value="Créer un post" class="forum-button"/>
					</form>
				</div>
			</c:if>
			<div id="breadcrumb-bottom" class="breadcrumb">
				<a href="<c:url value="/forum/"/>">Forum</a> &gt;
				${ category.forum.name } &gt; 
				${ category.name }
			</div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>