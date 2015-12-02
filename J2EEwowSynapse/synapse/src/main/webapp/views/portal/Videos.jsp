<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="videos-container">
	<h3>
		<span>Actus vidéos</span>
	</h3>
	<div id="videos">
		<c:forEach items="${videos}" var="video">
     		<div id="video_${ video.id }" class="video">${ video.integrationCode }</div>
		</c:forEach>
		<a href="/synapse/videos/" class="more-videos">Plus de vidéos</a>
	</div>
</div>