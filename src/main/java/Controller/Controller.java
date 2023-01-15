package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.RegisterBean;
import Dao.Database;

/**
 * ・社員情報検索サービス
 */
//機能:新規登録時:ユーザ情報の登録
//　　ログイン時:IDとPassWordを元に社員情報を検索
//　　目標登録時:目標登録のフラグをオンにする
 
public class Controller implements Database{
 
/*UPDATE文 _ 新規登録 */
private static final String SQL_INSERT =  "INSERT INTO student_info (name , id , pass , pref , sex , tel , mail , job) VALUES ( ? , ? , ? , ? , ? , ? , ? , ?);";
/*SELECT文 _新規登録内容出力*/
private static final String SQL_REGIST_SELECT = "SELECT * FROM student_info WHERE id = ? AND pass = ? ";
 /*SELECT文 _名前取得*/
 private static final String SQL_GETNAME = "SELECT name FROM student_info WHERE id = ? AND pass = ? ";
 /*SQL UPDATE文 _ 目標登録時_目標登録フラグを立てる */
 private static final String SQL_UPDATE =  "UPDATE student_info set goal_register = 'true' where name = ? ;";
 
// 新規登録時の情報登録
// 引数：個人情報
 public RegisterBean register(String name , String id , String pass , String pref , String sex , int tel ,String mail ,String job)
         throws ClassNotFoundException, SQLException{

     //SQL文格納の為の準備
     PreparedStatement pstmt = null ;
     ResultSet rs = null;
     
     //データベースクラスを使用したデータベースとの接続
     Connection conn= Database.getConnection();
    
 //結果を格納する為の箱を用意する
 RegisterBean Register = null ;

 pstmt = conn.prepareStatement(SQL_INSERT);

 //1、新規登録した内容を入力して結果を表示させる→新規登録の結果表示(register_checkに表示)(name,id,password)
 //→ログインされた時点ではこちらを表示させる
 pstmt.setString(1, name);
 pstmt.setString(2, id);
 pstmt.setString(3, pass);
 pstmt.setString(4, pref);
 pstmt.setString(5, sex);
 pstmt.setInt(6, tel);
 pstmt.setString(7, mail);
 pstmt.setString(8, job);

 //SQLの実行(実行結果は格納しない)
 pstmt.executeUpdate();

 //UPDATE文の直後SELECT文にて画面出力(※executeUpdate文では戻り値がint型の為設定できない)
 pstmt = conn.prepareStatement(SQL_REGIST_SELECT);

 //SELECT文で検索する内容を記述
 pstmt.setString(1, id);
 pstmt.setString(2, pass);

 //SQLの実行と処理結果の格納
 rs = pstmt.executeQuery();

 //テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
 while (rs.next()) {
 String tmpName = rs.getString("name");
 String tmpId       = rs.getString("id");
 String tmppass   = rs.getString("pass");
 String tmppref    = rs.getString("pref");
 String tmpsex     = rs.getString("sex");
 int     tmptel       = rs.getInt("tel");
 String tmpmail    = rs.getString("mail");
 String tmpjob      = rs.getString("job");

 Register = new RegisterBean();
 Register.setName(tmpName);
 Register.setId(tmpId);
 Register.setPass(tmppass);
 Register.setPref(tmppref);
 Register.setSex(tmpsex);
 Register.setTel(tmptel);
 Register.setMail(tmpmail);
 Register.setJob(tmpjob);
 }
     
 try {

 }finally {
     try {
         
         if (rs != null) {
         rs.close();
         }
         if (pstmt != null) {
         pstmt.close();
         }
         if (conn != null) {
         conn.close();
         }
     
         } catch (SQLException e) {
         e.printStackTrace();
         }
     }

 return Register;
 }
 
 // 送信されたIDとPassWordを元に社員情報を検索
//引数：id,パスワード
public RegisterBean SearchName(String id , String pass) 
        throws SQLException, ClassNotFoundException{

  //SQL文格納の為の準備
    PreparedStatement pstmt = null ;
    ResultSet rs = null;

    //データベースクラスを使用したデータベースとの接続
    Connection conn= Database.getConnection();
    
//  StudentBeanに格納する返り値の定義
  RegisterBean student_info = null;
 
 pstmt = conn.prepareStatement(SQL_GETNAME);
 
 pstmt.setString(1, id);
 pstmt.setString(2, pass);
 
 rs = pstmt.executeQuery();
 
 while(rs.next()){
     String tmpName = rs.getString("name");
     student_info = new RegisterBean();
     student_info.setName(tmpName);
 }

 try {
 
 }finally{
     try {
         
         if (rs != null) {
         rs.close();
         }
         if (pstmt != null) {
         pstmt.close();
         }
         if (conn != null) {
         conn.close();
         }
     
         } catch (SQLException e) {
         e.printStackTrace();
         }
 }
 return student_info;
}

//目標登録時目標登録のフラグをオンにする
//引数：名前
public RegisterBean Goal_Regist(String name) 
       throws SQLException, ClassNotFoundException{

 //SQL文格納の為の準備
   PreparedStatement pstmt = null ;
   ResultSet rs = null;

   //データベースクラスを使用したデータベースとの接続
   Connection conn= Database.getConnection();
   
// StudentBeanに格納する返り値の定義
 RegisterBean student_info = null;

//生徒のテーブルに対し目標設定のフラグを立てるようにUPDATEする
pstmt = conn.prepareStatement(SQL_UPDATE);

//SELECT文で検索する内容を記述
pstmt.setString(1, name);

//SQLの実行(実行結果は格納しない)
pstmt.executeUpdate();

student_info = new RegisterBean();

student_info.setGoal_register(true);
try {

}finally{
    try {
        
        if (rs != null) {
        rs.close();
        }
        if (pstmt != null) {
        pstmt.close();
        }
        if (conn != null) {
        conn.close();
        }
    
        } catch (SQLException e) {
        e.printStackTrace();
        }
}
return null;
}
}