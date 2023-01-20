
//役割:IDとパスワードを受け取ってログイン時の名前を返す

import java.io.IOException;

import Bean.GoalBean;
import Controller.Controller;
import Controller.Controller_Goal_Register;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Goal extends HttpServlet{
    public void doPost(HttpServletRequest request , HttpServletResponse response)
    throws ServletException , IOException{
 
 try {
//セッション作成
HttpSession session = request.getSession(true);
     
//インスタンスの作成
Controller_Goal_Register con = new  Controller_Goal_Register();
Controller conn = new Controller();
GoalBean gBean = new GoalBean();
        
////値の取得
String name            = request.getParameter("name");
String month_goal = request.getParameter("month_goal");
String step1            = request.getParameter("step1");

//ステップ2以下が未登録の場合はその内容を登録
String step2            = request.getParameter("step2");
if (step2 == null || step2 == "") {
    step2 = "未設定です";
}

String step3            = request.getParameter("step3");
if (step3 == null || step3 == "") {
    step3 = "未設定です";
}

String step4            = request.getParameter("step4");
if (step4 == null || step4 == "") {
    step4 = "未設定です";
}

String step5            = request.getParameter("step5");
if (step5 == null || step5 == "") {
    step5 = "未設定です";
}

String week_goal   = request.getParameter("week_goal");
int      month         = Integer.parseInt(request.getParameter("month"));
int      week           = Integer.parseInt(request.getParameter("week"));

if (request.getParameter("goal_register") != null) {
    request.setAttribute("month_goal" , month_goal);
    request.setAttribute("step1" , step1);
    request.setAttribute("step2" , step2);
    request.setAttribute("step3" , step3);
    request.setAttribute("step4" , step4);
    request.setAttribute("step5" , step5);
    request.setAttribute("week_goal" , week_goal);
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("/Goal_register_check.jsp");
    dispatcher.forward(request, response);
    return;
}

//以前登録した内容で同月同週のものがあればその内容を更新
gBean = con.check_step(name, month, week);

//目標とステップを月名・週名と同時に登録
if (gBean == null) {
gBean = con.register(name , month_goal , step1 , step2, step3, step4, step5, week_goal,month,week);
conn.Goal_Regist(name);
}else {
    gBean = con.update_register(name, month_goal, step1, step2, step3, step4, step5, week_goal, month, week);
}

session.setAttribute("GBean", gBean);

String disp = "/goal_list";
RequestDispatcher dispatch = request.getRequestDispatcher(disp);
dispatch.forward(request, response);

} catch (Exception e) {
   e.printStackTrace();
}finally {

}
}
}