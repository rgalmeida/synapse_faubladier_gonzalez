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
    <title>Synapse Gaming | ${ post.title }</title>
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
				<a href="<c:url value="/forum/category/${ post.category.id }"/>">${ post.category.forum.name } &gt; 
				${ post.category.name }</a> &gt; 
				${ post.title }
			</div>
			<div id="post">
				<div id="post-author-avatar" class="post-avatar"><img src="<c:url value="${ post.author.forumAvatar }"/>" width="50" height="50"/></div>
				<div id="post-title" class="post-title">${ post.title } <c:if test="${post.locked}"><font color="red" size="2">[Verrouillé]</font></c:if></div>
				<div id="post-author" class="post-author">${ post.author.nickname } le <fmt:formatDate value="${ post.dateCreate }" pattern="dd/MM/yyyy à HH:mm:ss" /></div>
				<div id="post-action-bar" class="post-action">
					<jsp:useBean id="today" class="java.util.Date" />
					<c:if test="${ !empty user && user.id != null && user.id == post.author.id }">
						<a href="<c:url value="/forum/category/${ post.category.id }/post/${ post.id }/edit"/>" title="Editer"><img src="<c:url value="/resources/img/forum_edit.png"/>" width="20" height="20"/></a>
					</c:if>
				</div>
				<div id="post-content" class="post-content">${ post.content }</div>
			</div>
			<div id="replies">
				<c:forEach items="${ post.replies }" var="reply">
					<div id="reply-${ reply.id }-author-avatar" class="reply-avatar"><img src="<c:url value="${ reply.author.forumAvatar }"/>" width="30" height="30"/></div>
					<div id="reply-${ reply.id }-author" class="reply-author">${ reply.author.nickname }</div>
					<div id="reply-${ reply.id }-date-time" class="reply-date-time">le <fmt:formatDate value="${ reply.dateCreate }" pattern="dd/MM/yyyy à HH:mm:ss" /></div>
					<div id="reply-${ reply.id }-action-bar" class="reply-action">
						&nbsp;
						<c:if test="${ !empty user && user.id != null && user.id == reply.author.id}">
							<!--<a href="#" title="Editer"><img src="<c:url value="/resources/img/forum_edit.png"/>" width="20" height="20"/></a>-->
							<div id="reply-action-delete">
								<form action="<c:url value="/forum/category/${ reply.post.category.id }/post/${ reply.post.id }/reply/${ reply.id }/delete"/>" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette réponse ?') ? true : false;">
									<input type="submit" name="deleteReply${ reply.id }" id="deleteReply${ reply.id }" class="forum-delete-button" value="" title="Supprimer"/>
								</form>
							</div>
						</c:if>
					</div>
					<div id="reply-content" class="reply-content">${ reply.content }</div>
				</c:forEach>
			</div>
			<div id="post-reply">
				<c:if test="${ !empty user && user.mail != null && !post.locked}">
					<h3>Répondre : </h3>
					<form action="<c:url value="/forum/post/${ post.id }/reply/add"/>" method="post">
						<textarea id="post-reply-content" name="reply-content" cols="100" rows="10"></textarea><br/>
						<div id="post-reply-action">
							<input type="submit" name="addReply" id="addReply" value="Soumettre" class="forum-button"/>
						</div>
					</form>
				</c:if>
				<c:if test="${ empty user || user.mail == null }">
					<div id="forum-info" class="info">Vous devez être identifié pour participer aux forums. <a href="<c:url value="/user/signin"/>">Se connecter</a>.</div>
				</c:if>
			</div>
			<div id="breadcrumb-bottom" class="breadcrumb">
				<a href="<c:url value="/forum/"/>">Forum</a> &gt;
				<a href="<c:url value="/forum/category/${ post.category.id }"/>">${ post.category.forum.name } &gt; 
				${ post.category.name }</a> &gt; 
				${ post.title }
			</div>
			<div id="forum-post-clear" class="clear"></div>
		</div>
	</div>
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div>
</div>
</body>
</html>