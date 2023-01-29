<%@page import="java.beans.beancontext.BeanContextMembershipEvent"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.beans.beancontext.BeanContext"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<jsp:useBean id = "StudentName" scope = "session" class = "Bean.RegisterBean" />
<%@ page import="Bean.GoalBean"%>
<%@ page import="java.util.List" %>
<%@ page import="Bean.Goal_ListBean"%>

<%
	Bean.GoalBean goal = (Bean.GoalBean)session.getAttribute("GBean");
	List<Bean.Goal_ListBean> list = (List<Bean.Goal_ListBean>)session.getAttribute("List");
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
	<title>目標・ステップ一覧</title>
	<link rel = "stylesheet" type = "text/css" href = "./css/goal.css">
	<link rel = "stylesheet" type = "text/css" href = "./css/common.css">
</head>
<body>

	<div class = "goal_list_container">
	
		<header>独学支援システム</header>
			
        <main>
        <div class = "top_msg">
		
			<h2>目標・ステップ一覧</h2>
		        <table border = "3" width = "1000" maxheight = "1000" align = "center">
	
				<thead><jsp:getProperty name = "StudentName" property = "name" />さんが過去登録された目標とステップ</thead>
			        <tr><td>登録日</td><td>月目標</td><td>週目標</td><td>ステップ</td><td>ステップ内容</td><td>達成(○×)</td>
			        <% for (Bean.Goal_ListBean bean : list ) { %>
					<tr><th><%= bean.getDate() %></th>
						<td><%= bean.getM_goal() %></td>
						<td><%= bean.getW_goal() %></td>
						<td><%= bean.getStep_num() %></td>
						<td><%= bean.getStep_content() %></td>
						<td><%= bean.getAchieve() %></td></tr>
					<% } %>
          		</table>	
                
                <div class = "check_button">
	                <button class = "check_move"><a href = "Goal_check.jsp">目標・ステップ一覧</button></a>
	                <button class = "check_move"><a href = "Study_start.jsp">学習開始画面へ</button></a>
                </div><!-- check_button -->

            </div> <!-- msg_container -->
            
         <footer>gen.Inc</footer>

        </main>

	</div> <!-- goal_list_container -->

</body>
</html>