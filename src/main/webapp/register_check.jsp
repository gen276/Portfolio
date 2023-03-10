<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%
	request.setCharacterEncoding("utf-8"); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登録内容確認画面</title>
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
</head>

<body>

	<div class = "container">
	
	<header>独学支援システム</header>
	
		<div class = "top_msg">
		<h2>以下の内容で登録してもよろしいですか</h2>
		
			<h3>氏名:${name}</h3>
	        <h3>ID:${id}</h3>
	        <h3>パスワード:${pass}</h3>
	        <h3>出身地:${pref}</h3>
	        <h3>性別:${sex}</h3>
	        <h3>電話番号:${tel}</h3>
	        <h3>メールアドレス:${mail}</h3>
	        <h3>職業:${job}</h3>

		</div> <!-- top_msg -->
		
		    <form action = "./register" method = "post" >
		    	<input name = "name" type = "hidden" value = "${name}">
				<input name = "id"   type = "hidden" value = "${id}">
				<input name = "pass" type = "hidden" value = "${pass}">
				<input name = "pref" type = "hidden" value = "${pref}">
				<input name = "sex"  type = "hidden" value = "${sex}">
				<input name = "tel"  type = "hidden" value = "${tel}">
				<input name = "mail" type = "hidden" value = "${mail}">
				<input name = "job"  type = "hidden" value = "${job}">
				<button type = "submit" name = "register_check">確定する</button>
				<button><a href = "register.jsp">訂正する</a></button>	
			</form>
		    
	</div> <!-- container -->
		    
</body>
</html>