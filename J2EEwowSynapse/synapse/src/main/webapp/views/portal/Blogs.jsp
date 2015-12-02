<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="blogs-container">
	<h3>
		<span>Actus blogs</span>
	</h3>
	<div id="blogArticles">
		<c:forEach items="${blogsArticles}" var="blogArticle">
     		<div id="blogArticle_${ blogArticle.id }" class="blogArticle">
     			<div id="blogArticle-img_${ blogArticle.id }" class="blogArticle-img"><img src="<c:url value="${ blogArticle.imageUrl }" />" width="200" height="150"/></div>
     			<div id="blogArticle-title_${ blogArticle.id }" class="blogArticle-title"><a href="/synapse/blogs/${ blogArticle.id }">${ blogArticle.title }</a></div>
     			<div id="blogArticle-author-date_${ blogArticle.id }" class="blogArticle-author-date">Par ${ blogArticle.author.surname } <i>'${ blogArticle.author.nickname }'</i> ${ blogArticle.author.name } <i>le <fmt:formatDate value="${ blogArticle.dateCreate }" pattern="dd/MM/yyyy" /></i></div>
     			<div id="blogArticle-desc_${ blogArticle.id }" class="blogArticle-desc">${ blogArticle.description }</div>
     			<div id="blogArticle-read_${ blogArticle.id }" class="more-blogs"><a href="/synapse/blogs/${ blogArticle.id }">Lire la suite</a></div>
     		</div>
		</c:forEach>
		<div id="more-blogs" class="more-blogs"><a href="/synapse/blogs/">Plus de blogs...</a></div>
	</div>
</div> 