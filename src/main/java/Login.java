
//役割:ログイン要素としてIDとパスワードを受け取ってログイン時の名前を返す
//セッションタグにて名前の取得を管理

import java.io.IOException;

import Bean.RegisterBean;
import Controller.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet{
    public void doPost(HttpServletRequest request , HttpServletResponse response)
    throws ServletException , IOException{
    
//LoginBeanから受け取った内容を格納する
try {
    // セッションオブジェクトの生成or取得
    HttpSession session = request.getSession(true);
    
    //  インスタンスの代入
    Controller con = new Controller();
    RegisterBean RBean = new RegisterBean();
    
    String id     = request.getParameter("id");
    String pass = request.getParameter("pass");
    
//  IDもしくはパスワードが未入力時のメッセージ表示
  if (id == null && pass != null) {
      session.setAttribute("msg", "IDが未入力です");
      ServletContext context = this.getServletContext();
      RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
  }
  
  if (id != null && pass == null) {
      session.setAttribute("msg", "パスワードが未入力です");
      ServletContext context = this.getServletContext();
      RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
  }

    RBean = con.SearchName(id, pass);
    
//    IDもしくはパスワードが違う時のメッセージ表示
    if (RBean == null) {
        session.setAttribute("msg", "IDまたはパスワードが違います");
        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
        return;
    }
 
// ログイン後のユーザー名が出力されるようにセッションにセットする
    session.setAttribute("StudentName", RBean);

} catch (Exception e) {
   e.printStackTrace();
}finally {
    ServletContext context = this.getServletContext();
    RequestDispatcher dispatcher = context.getRequestDispatcher("/login_result.jsp");
    dispatcher.forward(request, response);
}
}
}