
//役割:完了ボタンが押された際にその数字の達成状況を変化させる

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import Bean.AchieveBean;
import Controller.Controller_Achieve_Check;
import Controller.Controller_Goal_Register;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Step_Comp extends HttpServlet{
    public void doPost(HttpServletRequest request , HttpServletResponse response)
    throws ServletException , IOException{
        
      //ログイン時の日付と時間取得
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) ;
        int month = cal.get(Calendar.MONTH) + 1 ;
        int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
        int date = cal.get(Calendar.DATE) ;
        
 try {
     //セッションの取得
     HttpSession session = request.getSession(true);
     
     //インスタンスの作成
     Controller_Achieve_Check con = new  Controller_Achieve_Check();
     Controller_Goal_Register conn = new  Controller_Goal_Register();
     AchieveBean aBean = new AchieveBean();
     
 ////値の取得と返り値を格納する箱の用意
     String name   = request.getParameter("name");
     int comp   = Integer.parseInt(request.getParameter("complete"));
     int goal_no = 0 ;

     goal_no = conn.check_goalno(name , year , month , week_of_month , date , comp);
    aBean = con.step_achieve(goal_no);
     
 session.setAttribute("aBean", aBean);
 
RequestDispatcher dispatcher = request.getRequestDispatcher("/Goal_check.jsp");
dispatcher.forward(request, response);

} catch (ClassNotFoundException e) {
    e.printStackTrace();
} catch (SQLException e) {
    e.printStackTrace();
}finally {

}
}
}