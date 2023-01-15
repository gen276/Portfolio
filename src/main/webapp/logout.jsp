<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title>
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
</head>

<body>

<%
//セッション終了
session.invalidate();
%>

	<div class = "container">
	
		<header>独学支援システム</header>
			<h2>ログアウトしました<br></h2>
	
		<button><a href="Index.jsp">トップページに戻る</button></a>
	
		<footer>gen.Inc</footer>
	
	</div> <!-- container -->

</body>
</html>