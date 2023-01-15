<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<jsp:useBean id = "StudentName" scope = "session" class = "Bean.RegisterBean" />

<%!
  private int GetMonth() {
    Calendar cal = Calendar.getInstance();
    int Month = cal.get(Calendar.MONTH)+1;
    return Month;
  }

 private int GetW_M() {
    Calendar cal = Calendar.getInstance();
    int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
    return week_of_month;
  }
%>

<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>目標確認画面</title>
	<link rel = "stylesheet" type = "text/css" href = "./css/goal.css">
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
	
</head>

<body>

	<div class = "goal_container">
	
	<header>独学支援システム</header>
	
		<div class = "top_msg">
		
		<h2>以下の内容で登録してもよろしいですか</h2>
	        <table border = "3" width = "400" height = "150" align = "center">

			<thead><jsp:getProperty name = "StudentName" property = "name" />さんの<%= GetMonth() %>月<%= GetW_M() %>週目の目標</thead>
            <tr><th>月間目標</th> <td>${month_goal}</td></tr>
            <tr><th>ステップ1</th><td>${step1}</td></tr>
			<tr><th>ステップ2</th><td>${step2}</td></tr>
			<tr><th>ステップ3</th><td>${step3}</td></tr>
			<tr><th>ステップ4</th><td>${step4}</td></tr>
			<tr><th>ステップ5</th><td>${step5}</td></tr>
			<tr><th>週間目標</th> <td>${week_goal}</td></tr>
          
          	</table>	

		</div> <!-- top_msg -->
		
		    <form action = "./goal" method = "post" >
		    	<input name = "name" type = "hidden" value = "<jsp:getProperty name = "StudentName" property = "name" />">
				<input name = "month_goal" type = "hidden" value = "${month_goal}">
				<input name = "step1"   type = "hidden" value = "${step1}">
				<input name = "step2"   type = "hidden" value = "${step2}">
				<input name = "step3"   type = "hidden" value = "${step3}">
				<input name = "step4"   type = "hidden" value = "${step4}">
				<input name = "step5"   type = "hidden" value = "${step5}">
				<input name = "week_goal"   type = "hidden" value = "${week_goal}">
				<input name = "month" type = "hidden" value = "<%= GetMonth() %>">
                <input name = "week" type = "hidden" value = "<%= GetW_M() %>">
			<div class = "register_check_btn">
				<button type = "submit">確定する</button>
				<button><a href = "Goal_register.jsp">訂正する</a></button>
			</div> <!-- register_check_btn -->
			</form>

            
         <footer>gen.Inc</footer>

        </main>

	</div> <!-- goal_container -->

</body>
</html>