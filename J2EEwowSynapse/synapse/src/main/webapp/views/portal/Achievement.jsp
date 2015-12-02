<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="achievement-container">
	<h3>
		<span>Avancée</span>
	</h3>
	<div id="achievement-patch">
		<div id="achievement-raids">
			<c:forEach items="${ patch.raids }" var="raid">
		    		<div id="raid_${ raid.id }" class="raid">
		    			<!--<div id="raid-img"><img src="<c:url value="${ raid.imageUrl }" />"/></div>-->
	    				<c:forEach items="${ raid.achievements }" var="achievement">
	    					<div id="achievement_${ achievement.id }" class="achievement">
	    						<img src="<c:url value="${ achievement.boss.imageUrl }" />" class="rounded <c:if test="${ !achievement.down }">notDown</c:if>"/>
	    					</div>
	    				</c:forEach>
		    		</div>
			</c:forEach>
		</div>
	</div>
</div>