<%@page import="java.io.PrintWriter"%>
<%@page import="java.beans.beancontext.BeanContext"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<jsp:useBean id = "StudentName" scope = "session" class = "Bean.RegisterBean" />
<%@ page import="Bean.GoalBean"%>

<%
	Bean.GoalBean goal =(Bean.GoalBean)session.getAttribute("GBean");
%>


<%!
  private int GetMonth() {
    Calendar cal = Calendar.getInstance();
    int Month = cal.get(Calendar.MONTH) + 1;
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

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>目標確認画面</title>
	<link rel = "stylesheet" type = "text/css" href = "./css/goal.css">
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
	<script type="text/javascript" src = "./js/common.js"></script>
	
</head>
<body>

	<div class = "goal_container">
	
		<header>独学支援システム</header>
	
        <div class="goal_header">
                    <div class = "login_check">
                        <h2 class="login_header">こんにちは<jsp:getProperty name = "StudentName" property = "name" />さん</h2>
                        <button class="login_header"><a href = "logout.jsp">ログアウト</a></button>            
                    </div> <!-- login_check -->
        </div><!-- goal_header -->
			
        <main>
            <div class = "msg_container">
                
				<div class = "goal_check">
					<h2 class = "goal_box">1月の目標</h2>
					<h3 class = "goal_main"><%=goal.getMonth_goal() %></h3>
				</div>
                
                <div class = "achieve_step">
                    <h2>達成ステップ</h2>


					<table border = "3" width = "600" height = "150">
						<tr><th>完了までの道のり</th><td>ステップ内容</td><td>ステップ内容</td>

				<form action="./step_comp" method = "post">						
			            <tr><th id = "comp1">ステップ1</th><td><%=goal.getStep1() %></td>
			            <td><input type = "hidden" value = "1" name = "complete"><input type = "hidden" value = "<jsp:getProperty name = "StudentName" property = "name" />" name = "name">
			            <button type = "submit" onclick = "return changeline('comp1')">完了</button></td></tr>
			    </form>
			            
			    <form action="./step_comp" method = "post">
						<tr><th>ステップ2</th><td><%=goal.getStep2() %></td>
						<td><input type = "hidden" value = "2" name = "complete"><input type = "hidden" value = "<jsp:getProperty name = "StudentName" property = "name" />" name = "name">
						<button type = "submit" onclick = "return changeline('comp2')">完了</button></td></tr>
				</form>
						
				<form action="./step_comp" method = "post">		
						<tr id = "comp3"><th>ステップ3</th><td><%=goal.getStep3() %></td>
						<td><input type = "hidden" value = "3" name = "complete"><input type = "hidden" value = "<jsp:getProperty name = "StudentName" property = "name" />" name = "name">
						<button type = "submit" onclick = "changeUnderline('comp3');">完了</button></td></tr>
				</form>
						
				<form action="./step_comp" method = "post">		
						<tr id = "comp4"><th>ステップ4</th><td><%=goal.getStep4() %></td>
						<td><input type = "hidden" value = "4" name = "complete"><input type = "hidden" value = "<jsp:getProperty name = "StudentName" property = "name" />" name = "name">
						<button type = "submit" onclick = "changeUnderline('comp4');">完了</button></td></tr>
				</form>
												
				<form action="./step_comp" method = "post">		
						<tr id = "comp5"><th>ステップ5</th><td><%=goal.getStep5() %></td>
						<td><input type = "hidden" value = "5" name = "complete"><input type = "hidden" value = "<jsp:getProperty name = "StudentName" property = "name" />" name = "name">
						<button type = "submit" onclick = "changeUnderline('comp5');">完了</button></td></tr>
				</form>
				                       
                    </table>
                        <!--<h3 class = "achievement_late">達成率○％</h3>-->
                </div><!-- achieve_step -->
                
                <div class = "goal_check">
                    <h2 class = "goal_box"><%= GetMonth() %>月<%= GetW_M() %>週目の目標</h2>
                    <h3 class = "goal_main"><%=goal.getWeek_goal() %></h3>
                </div> <!-- goal_check -->
                
                <div class = "check_button">type = "submit"
	                <button class = "check_move"><a href = "Goal_register.jsp">目標・ステップの登録</button></a>
	                <!--<form action="./goal_record" method="POST" name = "form"></form>-->
		                <input name = "name" type = "hidden" value = "<jsp:getProperty name = "StudentName" property = "name" />">
		                <button class = "check_move" ><a href = "Goal_list.jsp">
		                目標・ステップ一覧へ</button></a>
		            
	                <button class = "check_move"><a href = "Study_start.jsp">学習開始画面へ</button></a>
                </div><!-- check_button -->

            </div> <!-- msg_container -->
            
         <footer>gen.Inc</footer>

        </main>

	</div> <!-- goal_container -->

</body>
</html>