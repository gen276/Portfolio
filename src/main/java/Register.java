import java.io.IOException;

import Bean.RegisterBean;
import Controller.Controller;
import Controller.Controller_Goal_Register;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register extends HttpServlet{
    public void doPost(HttpServletRequest request , HttpServletResponse response)
    throws ServletException , IOException{
        
//LoginBeanから受け取った内容を格納する
try {
    request.setCharacterEncoding("UTF-8");
    
// 値の取得
    String name      = request.getParameter("name") ;
    String id            = request.getParameter("id") ;
    String pass        = request.getParameter("pass") ;
    String pref         = request.getParameter("pref") ;
    String sex          = request.getParameter("sex") ;
    int     tel            = Integer.parseInt(request.getParameter("tel")) ;
    String mail         = request.getParameter("mail") ;
    String job           = request.getParameter("job") ;

    if (request.getParameter("register") != null) {
        
        request.setAttribute("name", name);
        request.setAttribute("id", id);
        request.setAttribute("pass", pass);
        request.setAttribute("pref", pref);
        
        if (pref == null) {
            request.setAttribute("pref", " ");
        }
        
        request.setAttribute("sex", sex);
        if (sex == null) {
            request.setAttribute("sex", " ");
        }
        
        request.setAttribute("tel", tel);
        
        request.setAttribute("mail", mail);
        if (mail == null) {
            request.setAttribute("mail", " ");
        }
        
        request.setAttribute("job", job);
        if (job == null) {
            request.setAttribute("job", " ");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/register_check.jsp");
        dispatcher.forward(request, response);
        return;
    }
    
    if (request.getParameter("register_check") != null) {
//      インスタンスの代入
        Controller_Goal_Register con = new Controller_Goal_Register();
        Controller conn    = new Controller();
        RegisterBean RBean = new RegisterBean();
        
//        サーチ関数を使用して返り値をSBeanに格納(SBeanには最終的な値を代入して保管しておく)
        RBean = conn.register(name , id , pass , pref , sex , tel , mail , job);
        con.First_Goal_Register(name);
        
    // SBeanを呼び出した時にサーチ結果が格納できるように返り値をセットする
        request.setAttribute("RBean", RBean);
        
      RequestDispatcher dispatcher = request.getRequestDispatcher("/register_comp.jsp");
      dispatcher.forward(request, response);
    }
 
} catch (Exception e) {
   e.printStackTrace();
   
}finally {

}
}
}