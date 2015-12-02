<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="news-container" class="theme-default">
	<div id="news-slider" class="slider nivoSlider">
		<c:forEach items="${proms}" var="news">
	    	<a href="/synapse/news/${ news.id }"><img src="<c:url value="${ news.coverImage }" />" title="${ news.title }" border="0"/></a>
	    </c:forEach>
	</div> 
</div>

<script type="text/javascript" src="<c:url value="/resources/js/slider.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/news.js" />"></script>