

//役割:ログイン後の名前を受け取り、その人が設定したゴールとステップを登録する

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import Bean.AchieveBean;
import Controller.Controller_Achieve_Check;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Goal_Record2 extends HttpServlet{
    public void doPost(HttpServletRequest request , HttpServletResponse response)
    throws ServletException , IOException{
        
      //ログイン時の日付と時間取得
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1 ;
        int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
        
 try {
     //セッションの取得
     HttpSession session = request.getSession(true);
     
     //インスタンスの作成
     Controller_Achieve_Check con = new  Controller_Achieve_Check();
     AchieveBean aBean = new AchieveBean();
     
 ////値の取得と返り値を格納する箱の用意
     String name   = request.getParameter("name");
     String step2   = request.getParameter("step2");
     if(step2.equals("未設定です")) {
         step2 = "false";
       }else {
         step2 = "true";
       }
     String step3   = request.getParameter("step3");
     if(step3.equals("未設定です")) {
         step3 = "false";
       }else {
           step3 = "true";
         }
     String step4   = request.getParameter("step4");
     if(step4.equals("未設定です")) {
         step4 = "false";
       }else {
           step4 = "true";
         }
     String step5   = request.getParameter("step5");
     if(step5.equals("未設定です")) {
         step5 = "false";
       }else {
           step5 = "true";
         }
     
     boolean tmpstep2 = Boolean.parseBoolean(step2);   
     boolean tmpstep3 = Boolean.parseBoolean(step3);
     boolean tmpstep4 = Boolean.parseBoolean(step4);
     boolean tmpstep5 = Boolean.parseBoolean(step5);
     
//登録した目標・ステップと登録した本人の紐づけ
 aBean = con.Before_Goal(name , month, week_of_month);

 if (aBean == null) {
     aBean = con.Goal_Step_Register(name , month, week_of_month , tmpstep2 , tmpstep3 , tmpstep4 , tmpstep5);
}else {
    aBean = con.update_register(tmpstep2, tmpstep3, tmpstep4, tmpstep5, name, month, week_of_month);
}
     
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