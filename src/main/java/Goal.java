
//役割:IDとパスワードを受け取ってログイン時の名前を返す

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import Bean.AchieveBean;
import Bean.GoalBean;
import Controller.Controller;
import Controller.Controller_Achieve_Check;
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
 
        //ログイン時の日付と時間取得
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) ;
        int month = cal.get(Calendar.MONTH) + 1 ;
        int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
        int date = cal.get(Calendar.DATE) ;
        
 try {
//セッション作成
HttpSession session = request.getSession(true);
     
//インスタンスの作成
Controller_Goal_Register con = new  Controller_Goal_Register();
Controller conn = new Controller();
Controller_Achieve_Check acon = new  Controller_Achieve_Check();
AchieveBean aBean = new AchieveBean();
GoalBean gBean = new GoalBean();
        
////値の取得
String name            = request.getParameter("name");
String month_goal = request.getParameter("month_goal");
ArrayList<String> StepList = new ArrayList<String>();
String step1            = request.getParameter("step1");
StepList.add(step1);

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

//登録されたステップの数を確認する
int step_num = 0 ;

if (step2 .equals("未設定です")) {
    step_num = 1 ;
}else if (step2 != "未設定です") {
        StepList.add(step2);
}

if (step3 .equals("未設定です") && step_num == 0) {
    step_num = 2 ;
}else if (step3 != "未設定です") {
    StepList.add(step3);
}

if (step4 .equals("未設定です") && step_num == 0) {
    step_num = 3 ;
}else if (step4 != "未設定です") {
    StepList.add(step4);
}

if (step5 .equals("未設定です") && step_num == 0) {
    step_num = 4 ;
}else if (step5 != "未設定です") {
    StepList.add(step5);
}

if (step_num == 0){
   step_num = 5 ;
}

//ステップが登録された数だけデータベースに目標とステップを登録する
for (int i = 1 ;  i <= step_num ; i++) {
    gBean = con.register(name , year , month , week_of_month , date , month_goal , week_goal , i , StepList.get(i-1));
    acon.Goal_Step_Register(gBean.getGoal_number());
}
conn.Goal_Regist(name);

session.setAttribute("GBean", gBean);

RequestDispatcher dispatcher = request.getRequestDispatcher("/Goal_comp.jsp");
dispatcher.forward(request, response);
String disp = "/goal_list";
RequestDispatcher dispatch = request.getRequestDispatcher(disp);
dispatch.forward(request, response);

} catch (Exception e) {
   e.printStackTrace();
}finally {

}
}
}