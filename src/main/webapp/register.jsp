<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新規登録・ログイン選択画面</title>
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
	<link rel = "stylesheet" type = "text/css" href = "./css/register.css">
	<script type="text/javascript" src = "./js/common.js"></script>
	
</head>

<body>

	<div class = "container">
	
	<header>独学支援システム</header>

		<div class = "top_register">
		
            <h3>新規登録フォーム</h3>
			<h4 class="register_required">※は必須入力です</h4>

		</div> <!-- top_register -->
		
			<form action="./register" method="POST" name = "form">
			<ul class="register_list">
			    <li><span class="required_input">※</span>
			    	<label>ユーザー名:</label>
					<input name = "name" type = "text" size = "20" class = "input_list"/><br></li>
			    <li><span class="required_input">※</span>
			    	<label>ID:</label>
					<input name = "id" type = "text" size = "20" class = "input_list"/><br></li>
			    <li><span class="required_input">※</span>
			    	<label>パスワード:</label>
					<input name = "pass" type = "password" size = "20" class = "input_list"/><br></li>
			    <li><label>都道府県:</label>
					<select name = "pref" size = "1" class = "input_list"><br>
					<option value = " "></option>
					<option value = "北海道">北海道</option>
			        <option value="東北">東北</option>
			        <option value="関東">関東</option>
			        <option value="中部">中部</option>
			        <option value="近畿">近畿</option>
			        <option value="中国">中国</option>
			        <option value="四国">四国</option>
			        <option value="九州">九州</option>
			        <option value="沖縄">沖縄</option></select><br></li>
                <li><label>性別:</label>
                    <input name = "sex" type = "radio" class = "radio_btn" value = "男">男
			        <input name = "sex" type = "radio" class = "radio_btn" value = "女">女<br></li>
			    <li><label>電話番号:</label>
					<input name="tel" type = "number" class = "input_list" value = "0"><br></li>
			    <li><label>メールアドレス:</label>
					<input name = "mail" type = "email" class = "input_list"><br></li>
			    <li><label>職業:</label>
					<select name = "job" size = "1" class = "input_list">
					<option value = " "></option>
                    <option value = "会社員">会社員</option>
                    <option value="公務員">公務員</option>
                    <option value="自営業">自営業</option>
                    <option value="無職">無職</option></select></li><br><br>
		        <button type = "submit"  onclick = "return Check()" name = "register">確認する</button><br>
            </ul></form>
		    
	<footer>gen.Inc</footer>
		    
	</div> <!-- container -->
		    
</body>
</html>