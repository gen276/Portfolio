<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<jsp:useBean id = "StudentName" scope = "session" class = "Bean.RegisterBean" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
	<title>登録完了画面</title>
</head>

<body>
	<div class = "container">
		
		<header>独学支援システム</header>
		
			<div class = "top_msg">
			
			<h1>登録を完了しました</h1>
		
		</div><!-- top_msg -->
		
		<form action="./goal_check" method = "post">
				<input type = "hidden" name = "name"
				value = "<jsp:getProperty name = "StudentName" property = "name" />">
				<button type = "submit" name = "goal_comp">目標確認画面へ戻る</button>
		</form>
	
		<footer>gen.Inc</footer>
	
	</div><!-- container -->

</body>
</html>
