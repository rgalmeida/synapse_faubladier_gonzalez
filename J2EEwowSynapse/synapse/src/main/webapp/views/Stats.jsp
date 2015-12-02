<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Synapse Gaming | Home</title>
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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/videos.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/blogs.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/achievement.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/recruitment.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/footer.css" />">
    
    
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.datetimepicker.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.fancybox.css" />">    
    
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/JQcalendar.css" />">
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/stats.css" />">
    <!-- JS -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/menu.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.datetimepicker.js" />"></script>
            <script type="text/javascript" src="<c:url value="/resources/js/common.js" />"></script>
        
    
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
	<!-- PARA EL EJERCICIO -->
	
	<div id="middle-wrapper">
	<div id="middle-container">
	
	<c:if test="${ !empty user && user.mail != null }">
	
	
		<c:if test="${user.group.id == 1 }"><!-- if the user is admin -->
		
			
			<!-- SUBMODULE FAST REVIEW AND OPTIONS -->
			
			
			
			<jsp:include page="portal/StatsMenu.jsp" />
				<c:if test="${submodule == 'CHARACTER_PROFILES' }">
				
				
				<!-- this form is an all-in-one block to dinamically modify the number of results -->
				
				<form action="./" method="post">
					<div class="stats-block">
					<c:if test="${ empty examineSpecialization || examineSpecialization == 'EXAMINE_CLASSES' }">
					
						<div class="stats-contcontainer">
						<div class="stats-container">
							<h1>Top ${numberOfMostUsedClasses } Classes!</h1>
							<p>The following table represents the ${numberOfMostUsedClasses } most used classes in the game</p>
							<br>
							<table>
								<c:forEach items="${listMostUsedClasses }" var="line">
									<tr>
										<c:forEach items="${line }" var="cell">
											<td>${cell }</td>
										</c:forEach>
									</tr>
								</c:forEach>
								<!-- ----------------- -->
								<tr>
								<td></td><td></td>
								</tr>
								<tr>
									<td style="color:#500">Items to show</td>
									<td></td>
								</tr>
								<tr>
									<td><input id="classesNumItems" name="classesNumItems" type="number" value="${numberOfMostUsedClasses }" min="1"></input></td>
									<td><input type="submit" value="go!"></td>
								</tr>
								<!-- -------------------- -->
							</table>
								<!-- -------------------- -->
							<br><br/>
							<c:if test="${examineSpecialization != 'EXAMINE_CLASSES' }">
								Inspect <a href="./TopClasses/">this!</a>
							</c:if>
						
								<!-- ------------------- -->
						</div>
						</div>
						</c:if>
					<c:if test="${ empty examineSpecialization || examineSpecialization == 'EXAMINE_RACES' }">
						
						<div class="stats-contcontainer">
						<div class="stats-container">
							<h1>Top ${numberOfMostUsedRaces } Races</h1>
							<p> The following table shows the top ${numberOfMostUsedRaces } races used by users </p>
							<br>
							<table>
								<c:forEach items="${listMostUsedRaces }" var="line">
									<tr>
											<c:forEach items="${line }" var="cell">
												<td>${cell }</td>
											</c:forEach>
										</tr>
								</c:forEach>
								<tr>
								<td></td><td></td>
								</tr>
								<tr>
									<td style="color:#500">Items to show</td>
									<td></td>
								</tr>
								<tr>
									<td><input id="racesNumItems" name="racesNumItems" type="number" value="${numberOfMostUsedRaces }" min="1"></input></td>
									<td><input type="submit" value="go!"></td>
								</tr>
							</table>
							<br><br/>
							<c:if test="${examineSpecialization != 'EXAMINE_RACES' }">
								Inspect <a href="./TopRaces/">this!</a>
							</c:if>
						</div>
						</div>
						</c:if>
					<c:if test="${ empty examineSpecialization || examineSpecialization == 'EXAMINE_SPECIALIZATIONS' }">
						
						<div class="stats-contcontainer">
						<div class="stats-container">
							<h1>Top ${numberOfMostUsedSpecs } Specializations</h1>
							<p> The following table shows the top ${numberOfMostUsedSpecs } specializations used by users </p>
							<br>
								<table>
									<c:forEach items="${listMostUsedSpecs }" var="line">
									<tr>	
										<c:forEach items="${line }" var="cell">
											<td>${cell }</td>
										</c:forEach>
									</tr>
								</c:forEach>
								<tr>
								<td></td><td></td>
								</tr>
								<tr>
									<td style="color:#500">Items to show</td>
									<td></td>
								</tr>
								<tr>
									<td><input id="specializationsNumItems" name="specializationsNumItems" type="number" value="${numberOfMostUsedSpecs }" min="1"></input></td>
									<td><input type="submit" value="go!"></td>
								</tr>
							</table>
							<br><br/>
							<c:if test="${examineSpecialization != 'EXAMINE_SPECIALIZATIONS' }">
								Inspect <a href="./TopSpecializations/">this!</a>
							</c:if>
						</div>
						</div>
					</c:if>
					<c:if test="${examineSpecialization == 'OUT_OF_THE_ROAD' }">
						You are out of the road... please... give one step <a href="javascript:history.back()" >back</a>
					</c:if>
						</div>
						</form>
					
					<c:if test="${ empty showComments }">
					
						<div class="little_button"><a href="./?showComments='5736356'">show<br>comments</a></div>
					</c:if>
					<c:if test="${showComments == '1'}">
						<div class="little_button"><a href="./">hide<br>comments</a></div>
					
					<div class="explanations-doc">
					<h1> CHARACTER PROFILE PAGE:</h1>
					
					<div class="container">
						<div class="block">
						<p> This queries has been done using the User class given in the project</p>
						<p> In the UserDao class, some methods have been created to COUNT the number of times that one specific FIELD of the table USER has been repeated</p>
						</div>
						<div class="block" style="width:350px">
						<p><strong> StatsService.java file</strong></p>
						<p>public List &ltObject&gt listTopClassPlayed(int n)&#59</p>
				
						<p>public List&ltObject&gt getTopRacesPlayed(int n)&#59</p>
					
						<p>public List&ltObject&gt listTopSpecializationsPlayed(int n)&#59</p>
						</div>
					</div>
					<div class="container">
						<div class="block"></div>
						<div class="block" style="width:350px">
						<p><strong> UserDao.java file</strong></p>
							<p>public List&ltObject&gt listTopSpecializationsPlayed(int n);</p>
							<p>public List&ltObject&gt listTopClassPlayed(int n);</p>
							<p>public List&ltObject&gt listTopRacesPlayed(int n);</p>
						</div>
						<div class="block" style="width:300px">
							<p><strong>Difficulties found:</strong></p>
							<ul>
								<li>1st contact with the Apache-Tomcat-Spring System</li>
								<li>deciding a non intrusive strategy to save memory: hql query</li>
								<li>Tests</li>
							</ul>
						</div>
					</div>
					<div class="container">
						<div class="block"></div>
						<div class="block" style="width:350px">
						</div>
						<div class="block" style="width:300px">
							<p><strong>Welth found:</strong></p>
							<ul>
								<li>Apache-Tomcat-Spring System</li>
								<li>contact with the Hibernate System</li>
								<li>Spring FrameWork and the <strong>page.addObject</strong> method, that comunicates a "back-end" (java) perspective with a "front-end" perspective (jsp, jstl)</li>
							</ul>
						</div>
					</div>
					
					</div>
					
					</c:if> <!-- show comments -->
					</c:if> <!-- CHARACTER_PROFILES -->







	
	
	





						<c:if test="${submodule == 'USERS_SOCIAL_ACTIVITY' }">
					
				
<!-- tests users activity -->
							
						
						
					<div class="stats-block">
					<div class="stats-contcontainer">
					<div class="stats-input" style="width:250px;height:300px">
					<br/>
					<form action="./" method="post" id="formToSend">
							<div>
								<table>
									<tr>
										<td></td>
										<td>Date range</td>
									</tr>
									<tr>
										<td>
											Initial :
										</td>
										<td>
											<div class="input-event-copy">
												<input type="text" id="dateRangeBegin" name="dateRangeBegin" class="date" style="width:75px" value="${currentDateRangeBegin }"></input>	
											</div>
										</td>
									</tr>
									<tr>
										<td>
											End : 
										</td>
										<td>
											<div class="input-event-copy">
										    	<input id="dateRangeEnd" class="date" type="text" name="dateRangeEnd" style="width:75px" value="${currentDateRangeEnd }"></input>
											</div>
										</td>
									</tr>
									<tr>
										<td> Results / page:<br /><span style="font-size:10px"><i>(0 = all in one)</span></i>)</td>
										<td>
											<div class="input-event-copy">
												<input id="numResultsPage" name="numResultsPage" type="number" min="0" value="${currentMaxResultsPage }" style="width:75px"></input>
											</div>
									</tr>
									<!-- will be deleted -->
									
									
									
									
									
								</table>
								<br/><br/>
								<div style="width:20%;margin: 0 auto;background-color:#dddddd;">
									<input type="submit" value="Launch Query">
								</div>
							</div>
							<script type="text/javascript">
							function adaptFormulaire(type)
							{
					        	form=document.getElementById('formToSend');
					        	if(type==null)
					        	{
					        		form.action='./2';
					        	}
					        	if(type=='next')
					        	{
					        		form.action='./${currentPageIndex+1}';
					        	}
					        	if(type=='before')
					        	{
					        		form.action='./${currentPageIndex-1}';
					        	}
					        	tmp=document.getElementById('numResultsPage');
					        	tmp.value='${currentMaxResultsPage }';
					        	tmp=document.getElementById('dateRangeBegin');
					        	tmp.value='${currentDateRangeBegin }';
					        	tmp=document.getElementById('dateRangeEnd');
					        	tmp.value='${currentDateRangeEnd }';
					        	form.submit();
							}
							</script>
							<br/><br/><br/>
							<c:if test="${ !empty currentMaxResultsPage && currentMaxResultsPage != '0' }">
								<c:if test="${ empty currentPageIndex }">
									
									<button onclick="adaptFormulaire(null);">&gt</button>
								</c:if>
								<c:if test="${ !empty currentPageIndex }">
									<c:if test="${currentPageIndex != 0 }">
									<button onclick="adaptFormulaire('before');">&lt</button>
									- ${currentPageIndex +1 } -
									</c:if>
									<button onclick="adaptFormulaire('next');">&gt</button>								
								
								</c:if>
							</c:if>
							
					</div>
					
					
					
					<div style="width:200px"></div>
					
					
					<div class="stats-container" style="">
					
						
						<div>
								<h1>Stats test container</h1>
								<h1> --------------------------- </h1>
								<br>
									<table>
									<tr><td style="color:#a00;background-color:#ddd">user:</td><td style="color:#a00;background-color:#ddd">score</td></tr>
									<tr><td style="background-color:#ddd"></td><td style="background-color:#aaa"></td></tr>
										<c:forEach items="${testForumPosts }" var="line">
										<tr>	
											
												<td>${line[0] }</td>
											<td>${line[2] }</td>
										</tr>
									</c:forEach>
								</table>
							</div>

						</div>
						
						</div>
						</div>
						
							<c:if test="${showComments == '1'}">
						<div class="little_button"><a href="./">hide<br>comments</a></div>
					
					<div class="explanations-doc">
					<h1> CHARACTER PROFILE PAGE:</h1>
					
					<div class="container">
						<div class="block">
						<p> This queries has been done using the User class given in the project</p>
						<p> In the UserDao class, some methods have been created to COUNT the number of times that one specific FIELD of the table USER has been repeated</p>
						</div>
						<div class="block" style="width:350px">
						<p><strong> StatsService.java file</strong></p>
						<p>public List &ltObject&gt listTopClassPlayed(int n)&#59</p>
				
						<p>public List&ltObject&gt getTopRacesPlayed(int n)&#59</p>
					
						<p>public List&ltObject&gt listTopSpecializationsPlayed(int n)&#59</p>
						</div>
					</div>
					<div class="container">
						<div class="block"></div>
						<div class="block" style="width:350px">
						<p><strong> UserDao.java file</strong></p>
							<p>public List&ltObject&gt listTopSpecializationsPlayed(int n);</p>
							<p>public List&ltObject&gt listTopClassPlayed(int n);</p>
							<p>public List&ltObject&gt listTopRacesPlayed(int n);</p>
						</div>
						<div class="block" style="width:300px">
							<p><strong>Difficulties found:</strong></p>
							<ul>
								<li>1st contact with the Apache-Tomcat-Spring System</li>
								<li>deciding a non intrusive strategy to save memory: hql query</li>
								<li>Tests</li>
							</ul>
						</div>
					</div>
					<div class="container">
						<div class="block"></div>
						<div class="block" style="width:350px">
						</div>
						<div class="block" style="width:300px">
							<p><strong>Welth found:</strong></p>
							<ul>
								<li>Apache-Tomcat-Spring System</li>
								<li>contact with the Hibernate System</li>
								<li>Spring FrameWork and the <strong>page.addObject</strong> method, that comunicates a "back-end" (java) perspective with a "front-end" perspective (jsp, jstl)</li>
							</ul>
						</div>
					</div>
					
					</div>
					
					</c:if> <!-- show comments -->
						
						
						</c:if>
						
				
				
				
				
				
				<!-- SUB-MODULE ABOUT USERS -->
				<c:if test="${submodule == 'USERS_WARNINGS'}">
						<div class="stats-block">
						<div class="stats-contcontainer">
							<div class="stats-container" style="width:400px;">
								<h1>Users withOUT avatar.</h1>
								<p> The following users still don't have an associated avatar. you can click in the Check Button in the third column to go to their user pages</p>
								<br>
								<table style="width:370px;">
									<c:forEach items="${listUsersDefaultAvatar }" var="line">
										<tr>
											<td>${line.nickname }</td>
											<td><a href="<c:url value="/user/${ line.id }" />">${line.mail }</a></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						</div>
				</c:if>
			</c:if><!-- fin si admin -->
		</c:if>
			
	<!-- 
	
	difficulties:
		1) understand the structure of the Spring platform
		
		2) decide the strategy to attack the needs respecting the principes of non-intrusion
		
		3) learn the HQL syntax and be able to develop queries that are accepted by the test modules
		
		4) decide the structure and the location of the methods created, to be able to be easily adapted for different needs
		
		5) once the data has been collected, be able to develop an ergonomic interface to show them
	
	
	benefits:
		1) be in contact with maven projects : team projects
		
		2) take contact with the apache-tomcat and spring framework : support to develop applications for multi-platform databases  
	
	 -->
	
	
		

	

	
	<c:if test="${ !empty user && user.mail != null }"> <!-- user is not allowed to watch this page -->
		<c:if test="${user.group.id != 1 }">
			<div id="middle-wrapper">
				<br/><br/>
				user not allowed to watch this page.
				
				
				<br>
				
				<br>
				
				please... keep <strong>calmed</strong> and put your <strong>hands</strong> in a <strong>visible</strong> place
				
				<br/>
				<br/>
				<br/>
				
				a <strong>police car</strong> will arrive in less than:
				<br/>
				
				
				 <spam style="font-size:75px">5</spam> 
				 
				 
				 <br/>
				 minutes
				
				<br/>
				<br/>
				<br/>
								
				thank you for your <strong>cooperation</strong> mr Hacker.
				<br/><br/>
				
			</div>
		</c:if>
		
	</c:if>
	<c:if test="${ empty user && user.mail == null }"> <!-- user not logged -->
		<div id="middle-wrapper">
			please log-in.
		</div>
	</c:if>
	</div>  <!-- middle container -->
	</div>	
	
	<div id="footer-wrapper">
		<jsp:include page="portal/Footer.jsp"/>
	</div> 
	
		</div>	<!-- fin page wrapper -->	
	
</body>
</html>