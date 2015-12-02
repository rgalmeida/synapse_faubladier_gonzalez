<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="navbar">
	 <c:forEach items="${sessionScope.resources}" var="resource">
     	<div id="menu_${ resource.id }" class="menu-cell"><a href="${ resource.path }">${ resource.name }</a></div>
	 </c:forEach>
</div> 