

//役割:ログイン後の名前を受け取り、その人が設定したゴールとステップを登録する

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Bean.Goal_ListBean;
import Controller.Controller_Achieve_Check;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Goal_Record extends HttpServlet{
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
//     AchieveBean aBean = new AchieveBean();
     List<Goal_ListBean> list = new ArrayList<Goal_ListBean>();
     
 ////値の取得と返り値を格納する箱の用意
     String name   = request.getParameter("name");
     
//登録した目標・ステップと登録した本人の紐づけ
list = con.Create_List(name);
     
 session.setAttribute("List", list);
 
RequestDispatcher dispatcher = request.getRequestDispatcher("/Goal_list.jsp");
dispatcher.forward(request, response);
return;

} catch (ClassNotFoundException e) {
    e.printStackTrace();
} catch (SQLException e) {
    e.printStackTrace();
}finally {

}
}
}