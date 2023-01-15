<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<jsp:useBean id = "StudentName" scope = "session" class = "Bean.RegisterBean" />
<%@ page import="Bean.GoalBean"%>

<%
	Bean.GoalBean goal =(Bean.GoalBean)session.getAttribute("GBean");
%>

<%
	request.setCharacterEncoding("utf-8");
%>

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

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>目標確認画面</title>
	<!-- <link rel = "stylesheet" type = "text/css" href = "trial.css"> -->
	<link rel = "stylesheet" type = "text/css" href = "./css/goal.css">
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
    <script type="text/javascript" src="common.js"></script>
</head>

<body>

    <div class="goal_container">

    <header>独学支援システム</header>

        <div class="goal_header">
            <div class = "login_check">
                <h2 class="login_header">こんにちは<jsp:getProperty name = "StudentName" property = "name" />さん</h2>
                <button class="login_header"><a href = "logout.jsp">ログアウト</a></button>           
            </div> <!-- login_check -->
        </div><!-- goal_header -->

        <h2>目標・ステップ登録画面</h2>

        <form action = "./goal" method="post">
            <ul class="goal_register">
                <li><label><%= GetMonth() %>月の目標:</label>
                   <input name = "month_goal" type = "text" size = "50"  class = "register_input"
                    placeholder = "月目標を記入して下さい"/></li>
                    <h4>※現在の目標:<%=goal.getMonth_goal() %></h4>
                    <li><label>達成ステップ1:</label>
                        <input name = "step1" type = "text" size = "50" class = "register_input"
                        placeholder = "ステップを記入して下さい"/></li>
                    <li><label>達成ステップ2:</label>
                        <input name = "step2" type = "text" size = "50" class = "register_input"
                        placeholder = "ステップを記入して下さい"/></li>
                    <li><label>達成ステップ3:</label>
                        <input name = "step3" type = "text" size = "50" class = "register_input"
                        placeholder = "ステップを記入して下さい"/></li>
                    <li><label>達成ステップ4:</label>
                        <input name = "step4" type = "text" size = "50" class = "register_input"
                        placeholder = "ステップを記入して下さい"/></li>
                    <li><label>達成ステップ5:</label>
                        <input name = "step5" type = "text" size = "50" class = "register_input"
                        placeholder = "ステップを記入して下さい"/></li>
                    <h5>※ステップ1は必須入力です</h5>
                <!-- </ul> -->
                <li><label><%= GetMonth() %>月<%= GetW_M() %>週目の目標:</label>
                	<input name = "week_goal" type = "textbox" size = "50"  class = "register_input"
                    placeholder = "週目標を記入して下さい"/></li>
                	<h4>※現在の目標:<%=goal.getWeek_goal() %></h4>
                    <input name = "month" type = "hidden" value = "<%= GetMonth() %>">
                    <input name = "week" type = "hidden" value = "<%= GetW_M() %>">
                <button type = "submit" class = "check_move"><a href = "Goal_check.jsp">目標確認画面へ戻る</a></button>
                <button type = "submit" class = "check_move" name = "goal_register">登録の確認</button>
               
        </ul></form>    

    <footer>gen.Inc</footer>

	</div> <!-- goal_container -->

</body>
</html>