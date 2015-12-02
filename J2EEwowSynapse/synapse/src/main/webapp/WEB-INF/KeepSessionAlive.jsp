<html>
<head>
</head>
<body>
<h2>Session is alive</h2>
<h3><% out.println("Last access time : " + request.getSession(false).getLastAccessedTime()); %></h3>
<h3><% out.println("Time remaining : " + (System.currentTimeMillis() - request.getSession(false).getLastAccessedTime())); %></h3>
</body>
</html>