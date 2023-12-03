<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新規登録・ログイン選択画面</title>
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
</head>
<body>

	<div class = "container">
	
	<header>独学支援システム</header>
	
		<div class = "menu_container">
		
				<div class = "top_msg">
				
				<%
					String message = "新規登録,ログインを選択してください";
				%>
				
				<%= message %><br>
		
				</div> <!-- top_msg -->
			
			<div class = "msg_container">
			
			<h1>ログインの方はこちら</h1>
				<button action = "login.jsp" class = "index_btn"><a href = "login.jsp">ログイン</button></a><br>
			
			<h1>新規登録の方はこちら</h1>
				<button action = "register.jsp"><a href = "register.jsp">新規登録</button></a>
				
			</div> <!-- msg_container -->
		
		</div> <!-- menu_container -->
	
	<footer>gen.Inc</footer>

	</div> <!-- container -->

</body>
</html>