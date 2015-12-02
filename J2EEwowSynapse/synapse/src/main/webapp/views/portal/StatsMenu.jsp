<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="stats-menu">
<c:choose>
	
	<c:when test="${ branchDepth != '0' }" >
		<div class="stats-menu-option">
			<a href="./
			<c:forEach begin="0" end="${branchDepth }" >
			../
			</c:forEach>
			stats/characterProfiles/">Character Profiles</a>
		</div>
		<div class="stats-menu-option">
			<a href="
			<c:forEach begin="0" end="${branchDepth }" >
			../
			</c:forEach>
			stats/usersWarnings/">Users Avatars</a>
		</div>
		<div class="stats-menu-option">
			<a href="
			<c:forEach begin="0" end="${branchDepth }" >
			../
			</c:forEach>
			stats/userssocialstats/">Users Activity</a>
		</div>
	</c:when>
	<c:when test="${ branchDepth == '0' }">
		<div class="stats-menu-option">
			<a href="./characterProfiles/">Character Profiles</a>
		</div>
		<div class="stats-menu-option">
			<a href="./usersWarnings/">Users Avatars</a>
		</div>
		<div class="stats-menu-option">
			<a href="./userssocialstats/">Users Activity</a>
		</div>	
	</c:when>
</c:choose>
	
</div>