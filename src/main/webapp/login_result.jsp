<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<jsp:useBean id = "StudentName" scope = "session" class = "Bean.RegisterBean" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
<link rel = "stylesheet" type = "text/css" href = "./css/login.css">
	<title>ログイン結果表示画面</title>
</head>

<body>
<div class = "container">
	
	<header>独学支援システム</header>
	
	<div class = "top_msg">
	
	<h1>ようこそ<jsp:getProperty name = "StudentName" property = "name" />さん</h1>
	
	</div><!-- top_msg -->

	<form action="./goal_check" method = "post">
		<div class = "check_button">
				<input type = "hidden" name = "name"
				value = "<jsp:getProperty name = "StudentName" property = "name" />">
				<button type = "submit" name = "login_result">
				次のページに行く</button>
				<button><a href="Index.jsp">トップページへ戻る</a></button>
		</div><!-- check_button -->
	</form>

	<footer>gen.Inc</footer>

</div><!-- container -->

</body>
</html>
