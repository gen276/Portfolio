<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title><!-- top_msg -->
	<link rel = "stylesheet" type = "text/css" href = "./css/login.css">
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
	<script type="text/javascript" src = "./js/common.js"></script>
</head>

<body>

	<div class = "container">
	
	<header>独学支援システム</header>
	
		<div class = "top_msg">
		<%
			String message = "ログインフォーム";
		%>
		
		<%= message %><br>
		</div> <!-- top_msg -->

		<form action="./login" method = "POST" name = "form">
			<span class="label-danger"> ${msg}</span>
			<ul class="login_list">
				<li><label for = "id">ID:</label>
				<input name = "id" type = "text" size = "20" class = "input_list"/></li><br>
				<li><label for = "pass">パスワード:</label>
				<input name = "pass" type = "password" size = "20" class = "input_list"/></li><br>
			    <button type = "submit" onclick = "return Check()">確認する</button>
			 </ul>
		</form>
	
	<footer>gen.Inc</footer>
	
	</div> <!-- container -->
		    
</body>
</html>