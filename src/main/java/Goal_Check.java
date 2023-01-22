
//役割:ログイン後の名前を受け取り、その人が設定したゴールとステップをセッションで渡す
//nullの場合は「○○が未設定です」と表示する

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import Bean.GoalBean;
import Controller.Controller_Goal_Register;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Goal_Check extends HttpServlet{
    public void doPost(HttpServletRequest request , HttpServletResponse response)
    throws ServletException , IOException{
        
      //ログイン時の日付と時間取得
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1 ;
        int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
        int date = cal.get(Calendar.DATE);
        
 try {
     //セッションの取得
     HttpSession session = request.getSession(true);
     
 ////値の取得と返り値を格納する箱の用意
     String name   = request.getParameter("name");

   //インスタンスの作成
     Controller_Goal_Register con = new  Controller_Goal_Register();
     GoalBean gBean = new GoalBean();
      
 if (request.getParameter("login") != null) {
   //登録した目標・ステップと登録した本人の紐づけ
     gBean = con.check_step(name, month, week_of_month);
     
     if (gBean == null) {
         gBean = new GoalBean();
         gBean.setMonth_goal("未設定です");
         gBean.setStep1("未設定です");
         gBean.setStep2("未設定です");
         gBean.setStep3("未設定です");
         gBean.setStep4("未設定です");
         gBean.setStep5("未設定です");
         gBean.setWeek_goal("未設定です");
    }
     
         if (gBean.getStep2() == null || gBean.getStep2() == "") {
             gBean.setStep2("未設定です");
         }
    
         if (gBean.getStep3() == null || gBean.getStep3() == "") {
             gBean.setStep3("未設定です");
         }
         
         if (gBean.getStep4() == null || gBean.getStep4() == "") {
             gBean.setStep4("未設定です");
         }
         
         if (gBean.getStep5() == null || gBean.getStep5() == "") {
             gBean.setStep5("未設定です");
         }
         
         session.setAttribute("GBean", gBean);
         RequestDispatcher dispatcher = request.getRequestDispatcher("/Goal_check.jsp");
         dispatcher.forward(request, response);
         return;
 }
 
     if (request.getParameter("goal_comp") != null) {
         gBean = con.check_goal(name, month, week_of_month ,date);
     
         if (gBean.getStep2() == null || gBean.getStep2() == "") {
             gBean.setStep2("未設定です");
         }
    
         if (gBean.getStep3() == null || gBean.getStep3() == "") {
             gBean.setStep3("未設定です");
         }
         
         if (gBean.getStep4() == null || gBean.getStep4() == "") {
             gBean.setStep4("未設定です");
         }
         
         if (gBean.getStep5() == null || gBean.getStep5() == "") {
             gBean.setStep5("未設定です");
         }
         
         session.setAttribute("GBean", gBean);
         
         RequestDispatcher dispatcher = request.getRequestDispatcher("/Goal_check.jsp");
         dispatcher.forward(request, response);
         return;
     }

} catch (ClassNotFoundException e) {
    e.printStackTrace();
} catch (SQLException e) {
    e.printStackTrace();
}finally {
    

}
}
}