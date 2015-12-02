<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="recruitment-container">
	<h3>
		<span>Recrutement</span>
	</h3>
	<div id="classes">
		<c:forEach items="${classes}" var="clazz">
     		<div id="clazz_${ clazz.id }" class="clazz"><img src="<c:url value="${ clazz.img }" />" title="${ clazz.name }"/></div>
     		<c:forEach items="${clazz.specs}" var="spec">
     			<div id="spec_${ spec.id }" class="spec"><img src="<c:url value="${ spec.img }"/>" width="35" height="35" class="<c:if test="${ !spec.recruiting }">full</c:if>" title="${ spec.name }"/></div>
     		</c:forEach>
		</c:forEach>
	</div>
</div> 