
//役割:ログイン後の名前を受け取り、その人が設定したゴールとステップを登録する

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import Bean.AchieveBean;
import Bean.GoalBean;
import Controller.Controller_Achieve_Check;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Goal_List extends HttpServlet{
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
     
     //インスタンスの作成
     Controller_Achieve_Check con = new  Controller_Achieve_Check();
     AchieveBean aBean = new AchieveBean();
     GoalBean gBean = new GoalBean();
     
 ////値の取得と返り値を格納する箱の用意
     String name   = request.getParameter("name");
     int step_num = 0 ;
     
   //ステップ2以下が未登録の場合はその内容を登録
     String step2            = request.getParameter("step2");
     if (step2 .equals("未設定です")) {
         step_num = 1 ;
     }

     String step3            = request.getParameter("step3");
     if (step3 .equals("未設定です") && step_num == 0) {
         step_num = 2 ;
     }

     String step4            = request.getParameter("step4");
     if (step4 .equals("未設定です") && step_num == 0) {
         step_num = 3 ;
     }

     String step5            = request.getParameter("step5");
     if (step5 .equals("未設定です") && step_num == 0) {
         step_num = 4 ;
     }
     
     if (step_num == 0){
        step_num = 5 ;
    }
     
//     月名,週名,日付を使用してステップの数だけSQLに登録する
     aBean = con.Goal_Step_Register(name , month, week_of_month , step_num , date);
     
 session.setAttribute("aBean", aBean);
 
RequestDispatcher dispatcher = request.getRequestDispatcher("/Goal_comp.jsp");
dispatcher.forward(request, response);

} catch (ClassNotFoundException e) {
    e.printStackTrace();
} catch (SQLException e) {
    e.printStackTrace();
}finally {

}
}
}