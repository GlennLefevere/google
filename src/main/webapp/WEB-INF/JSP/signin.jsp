<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="signin/google" method="POST">
		<button type="submit" class="btn btn-large btn-primary">Sign in with Google</button>
		<input type="hidden" name="scope" value="email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/tasks https://www.googleapis.com/auth/drive https://www.googleapis.com/auth/latitude.all.best https://docs.google.com/feeds/ https://docs.googleusercontent.com/ https://spreadsheets.google.com/feeds/" /> <input type="hidden" name="request_visible_actions"
			value="http://schemas.google.com/AddActivity http://schemas.google.com/BuyActivity http://schemas.google.com/CheckInActivity http://schemas.google.com/CommentActivity http://schemas.google.com/CreateActivity http://schemas.google.com/DiscoverActivity http://schemas.google.com/ListenActivity http://schemas.google.com/ReserveActivity http://schemas.google.com/ReviewActivity http://schemas.google.com/WantActivity" /> 
			<input type="hidden" name="access_type" value="online" />
	</form>

</body>
</html>