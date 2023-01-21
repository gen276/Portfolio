package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.GoalBean;
import Dao.Database;

//goal_stepのデータベースを変更するプログラム

//機能:初回登録時:目標・ステップを未設定と表示させる
//       月目標・達成のステップ・週目標を登録、目標初回設定時:目標フラグを立てる
//　　ログイン後:受け取ったユーザー名を基に目標を抽出する
//　　ログイン後:ユーザー名,月名,週名を基に登録済みのステップを抽出する
//      目標更新時:登録済みの内容を更新する
 
public class Controller_Goal_Register implements Database{
 /*SQL INSERT文 _ 目標・ステップ登録時 */
 private static final String SQL_INSERT =  "INSERT INTO goal_step (name , month_goal , step1 , step2 , step3 , step4 , step5 , week_goal , month , week)"
                                                                 + "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ?);";
 
 /*SQL UPDATE文 _ 目標・ステップ更新時 */
 private static final String SQL_UPDATE =  " UPDATE goal_step SET month_goal = ? , step1 = ? , step2 = ? , step3 = ? , step4 = ? , step5 = ? , week_goal = ?"
                                                                 + "WHERE name = ? and month = ? and week = ? ;";
 
 /*SQL INSERT文 _ 初回登録時 */
 private static final String SQL_INSERT_UNREGISTERD =  "INSERT INTO goal_step (name , month_goal , step1 , step2 , step3 , step4 , step5 , week_goal)"
                                                                 + "VALUES ( ? , '未設定です' , '未設定です' , '未設定です' , '未設定です' , '未設定です' , '未設定です' , '未設定です');";

 /*SQL SELECT文_名前を元に目標とステップを抽出 */
 private static final String SQL_SELECT = "SELECT name , month_goal , step1 , step2 , step3 , step4 , step5 , week_goal FROM goal_step WHERE name = ? ;";

 /*SQL SELECT文_名前を元に目標とステップを抽出 */
 private static final String SQL_SELECT_STEP = "SELECT month_goal , step1 , step2 , step3 , step4 , step5 , week_goal FROM goal_step WHERE name = ? and month = ? and week = ? ;";
  
private static final GoalBean Goal = null;
 
//初回登録時:名前を登録と同時に目標・ステップを未設定と表示
//引数:名前
@SuppressWarnings("resource")
public GoalBean First_Goal_Register(String name) 
    throws SQLException, ClassNotFoundException{
    //SQL文格納の為の準備
    PreparedStatement pstmt = null ;
    ResultSet rs = null;
    Connection conn = Database.getConnection();

//    Bean格納用の返り値の定義
    GoalBean Goal = null;

try {
    pstmt = conn.prepareStatement(SQL_INSERT_UNREGISTERD);
    
//    1、設定した目標とステップをデータベースに格納する
//          →ログインされた時点ではこちらを表示させる
    pstmt.setString(1, name);
   
//    SQLの実行(実行結果は格納しない)
    pstmt.executeUpdate();
    
// UPDATEした内容をSELECT文にて出力し、Beanに格納する準備をする
    pstmt = conn.prepareStatement(SQL_SELECT);
    
// SELECT文で検索する内容を記述
    pstmt.setString(1, name);
    
//SQLの実行と処理結果の格納
    rs = pstmt.executeQuery();
    
//    テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
while (rs.next()) {
    String tmpMonth_Goal = rs.getString("month_goal");
    String tmpStep1   = rs.getString("step1");
    String tmpStep2   = rs.getString("step2");
    String tmpStep3   = rs.getString("step3");
    String tmpStep4   = rs.getString("step4");
    String tmpStep5   = rs.getString("step5");
    String tmpWeek_Goal = rs.getString("week_goal");
   
    Goal = new GoalBean();
    Goal.setMonth_goal(tmpMonth_Goal);
    Goal.setStep1(tmpStep1);
    Goal.setStep2(tmpStep2);
    Goal.setStep3(tmpStep3);
    Goal.setStep4(tmpStep4);
    Goal.setStep5(tmpStep5);
    Goal.setWeek_goal(tmpWeek_Goal);
}
    
} catch (SQLException e) {
    e.printStackTrace();

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

return null;
}

 //月目標・達成のステップ・週目標を登録、目標初回設定の場合に目標フラグを立てる
// 引数:名前,登録内容
//@SuppressWarnings("resource")
public GoalBean register
(String name , String month_goal , String step1 , String step2 , String step3 , String step4 , String step5 , String week_goal , int month , int week) 
    throws SQLException, ClassNotFoundException{
    //SQL文格納の為の準備
    PreparedStatement pstmt = null ;
    ResultSet rs = null;
    Connection conn = Database.getConnection();

//    Bean格納用の返り値の定義
    GoalBean Goal = null;

try {
    pstmt = conn.prepareStatement(SQL_INSERT);
    
//    1、設定した目標とステップをデータベースに格納する
//          →ログインされた時点ではこちらを表示させる
    pstmt.setString(1, name);
    pstmt.setString(2, month_goal);
    pstmt.setString(3, step1);
    pstmt.setString(4, step2);
    pstmt.setString(5, step3);
    pstmt.setString(6, step4);
    pstmt.setString(7, step5);
    pstmt.setString(8, week_goal);
    pstmt.setInt(9, month);
    pstmt.setInt(10, week);
   
//    SQLの実行(実行結果は格納しない)
    pstmt.executeUpdate();
    
// UPDATEした内容をSELECT文にて出力し、Beanに格納する準備をする
    pstmt = conn.prepareStatement(SQL_SELECT);
    
// SELECT文で検索する内容を記述
    pstmt.setString(1, name);
    
//SQLの実行と処理結果の格納
    rs = pstmt.executeQuery();
    
//    テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
while (rs.next()) {
    String tmpName = rs.getString("name");
    String tmpMonth_Goal = rs.getString("month_goal");
    String tmpStep1   = rs.getString("step1");
    String tmpStep2   = rs.getString("step2");
    String tmpStep3   = rs.getString("step3");
    String tmpStep4   = rs.getString("step4");
    String tmpStep5   = rs.getString("step5");
    String tmpWeek_Goal = rs.getString("week_goal");
   
    Goal = new GoalBean();
    Goal.setName(tmpName);
    Goal.setMonth_goal(tmpMonth_Goal);
    Goal.setStep1(tmpStep1);
    Goal.setStep2(tmpStep2);
    Goal.setStep3(tmpStep3);
    Goal.setStep4(tmpStep4);
    Goal.setStep5(tmpStep5);
    Goal.setWeek_goal(tmpWeek_Goal);
    return Goal;
}
    
} catch (SQLException e) {
    e.printStackTrace();

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

return Goal;
}

//ログイン後:受け取ったユーザー名を基に目標を抽出する
//引数:名前
public GoalBean check_goal(String name)
        throws SQLException, ClassNotFoundException{
 //SQL文格納の為の準備
 PreparedStatement pstmt = null ;
 ResultSet rs = null;
 Connection conn = Database.getConnection();

try {
 // Bean格納用の返り値の定義
    GoalBean Goal = null;
    pstmt = conn.prepareStatement(SQL_SELECT);
 
//名前で目標・ステップを検索する
 pstmt.setString(1, name);

//SQLの実行と処理結果の格納
rs = pstmt.executeQuery();
 
// テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
if (rs.next()) {

 String tmpMonth_Goal = rs.getString("month_goal");
 String tmpStep1   = rs.getString("step1");
 String tmpStep2   = rs.getString("step2");
 String tmpStep3   = rs.getString("step3");
 String tmpStep4   = rs.getString("step4");
 String tmpStep5   = rs.getString("step5");
 String tmpWeek_Goal = rs.getString("week_goal");
 
 Goal = new GoalBean();
Goal.setMonth_goal(tmpMonth_Goal);
Goal.setStep1(tmpStep1);
Goal.setStep2(tmpStep2);
Goal.setStep3(tmpStep3);
Goal.setStep4(tmpStep4);
Goal.setStep5(tmpStep5);
Goal.setWeek_goal(tmpWeek_Goal);
}
    return Goal;
    
} catch (SQLException e) {
 e.printStackTrace();

}finally {
 }
return Goal;

}

//ユーザー名,月名,週名を基に登録済みのステップを抽出する
//引数:名前,月,週
public GoalBean check_step(String name , int month , int week)
      throws SQLException, ClassNotFoundException{
//SQL文格納の為の準備
PreparedStatement pstmt = null ;
ResultSet rs = null;
Connection conn = Database.getConnection();

try {
// Bean格納用の返り値の定義
  GoalBean Goal = null;
  pstmt = conn.prepareStatement(SQL_SELECT_STEP);

//名前で目標・ステップを検索する
pstmt.setString(1, name);
pstmt.setInt(2, month);
pstmt.setInt(3, week);

//SQLの実行と処理結果の格納
rs = pstmt.executeQuery();

//テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
while (rs.next()) {
    String tmpMonth_Goal = rs.getString("month_goal");
    String tmpStep1   = rs.getString("step1");
    String tmpStep2   = rs.getString("step2");
    String tmpStep3   = rs.getString("step3");
    String tmpStep4   = rs.getString("step4");
    String tmpStep5   = rs.getString("step5");
    String tmpWeek_Goal = rs.getString("week_goal");

Goal = new GoalBean();
Goal.setMonth_goal(tmpMonth_Goal);
Goal.setStep1(tmpStep1);
Goal.setStep2(tmpStep2);
Goal.setStep3(tmpStep3);
Goal.setStep4(tmpStep4);
Goal.setStep5(tmpStep5);
Goal.setWeek_goal(tmpWeek_Goal);
return Goal;
  }
} catch (SQLException e) {
e.printStackTrace();

}
return null;
}


//同じ月と週に目標があった場合に登録済みの内容を更新する
//引数:名前,月,週,登録内容
public GoalBean update_register(String name , String month_goal , String step1 , String step2 , String step3 , String step4 , String step5 , String week_goal , int month , int week)
    throws SQLException, ClassNotFoundException{
//SQL文格納の為の準備
PreparedStatement pstmt = null ;
ResultSet rs = null;
Connection conn = Database.getConnection();

try {
//Bean格納用の返り値の定義
GoalBean Goal = null;
pstmt = conn.prepareStatement(SQL_UPDATE);
    
//    1、以前設定した目標の内容を更新する
    pstmt.setString(1, month_goal);
    pstmt.setString(2, step1);
    pstmt.setString(3, step2);
    pstmt.setString(4, step3);
    pstmt.setString(5, step4);
    pstmt.setString(6, step5);
    pstmt.setString(7, week_goal);
    pstmt.setString(8, name);
    pstmt.setInt(9, month);
    pstmt.setInt(10, week);
   
//    SQLの実行(実行結果は格納しない)
    pstmt.executeUpdate();
    
// UPDATEした内容をSELECT文にて出力し、Beanに格納する準備をする
    pstmt = conn.prepareStatement(SQL_SELECT_STEP);
    
// SELECT文で検索する内容を記述
    pstmt.setString(1, name);
    pstmt.setInt(2, month);
    pstmt.setInt(3, week);
    
//SQLの実行と処理結果の格納
    rs = pstmt.executeQuery();
    
//    テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
while (rs.next()) {
    String tmpMonth_Goal = rs.getString("month_goal");
    String tmpStep1   = rs.getString("step1");
    String tmpStep2   = rs.getString("step2");
    String tmpStep3   = rs.getString("step3");
    String tmpStep4   = rs.getString("step4");
    String tmpStep5   = rs.getString("step5");
    String tmpWeek_Goal = rs.getString("week_goal");
   
    Goal = new GoalBean();
    Goal.setMonth_goal(tmpMonth_Goal);
    Goal.setStep1(tmpStep1);
    Goal.setStep2(tmpStep2);
    Goal.setStep3(tmpStep3);
    Goal.setStep4(tmpStep4);
    Goal.setStep5(tmpStep5);
    Goal.setMonth_goal(tmpWeek_Goal);
}
    
} catch (SQLException e) {
    e.printStackTrace();

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

return Goal;
}
}