package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
 private static final String SQL_INSERT =  "INSERT INTO goal (name , year , month , week , register_date , month_goal , week_goal , step_num , step_content)"
                                                                 + "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ?);";
 
 /*SQL SELECT文_目標登録後、登録した目標とステップを抽出 */
 private static final String SQL_SELECT = "SELECT month_goal , week_goal , step_num , step_content , goal_no FROM goal WHERE name = ? and month = ? and week = ? and register_date = ? ;";
 
 /*SQL SELECT文_目標登録後、登録した目標とステップを抽出 */
 private static final String SQL_COUNT_REGIST = "SELECT max(step_num) as num FROM goal WHERE name = ? and month = ? and week = ? and register_date = ? ;";
 
 /*SQL SELECT文_ログイン時、登録月・週におけるステップの登録数を確認 */
 private static final String SQL_COUNT_STEP = "SELECT max(step_num) as num FROM goal WHERE name = ? and month = ? and week = ?; " ;
 
 /*SQL SELECT文_ログイン時、登録した目標とステップを抽出 */
 private static final String SQL_SELECT_CHECK = "SELECT month_goal , week_goal , step_num , step_content FROM goal WHERE name = ? and month = ? and week = ?;";
 
 /*SQL SELECT文_目標完了時、登達成した目標番号を抽出 */
 private static final String SQL_SELECT_GOAL = "SELECT goal_no from goal where name =? and year = ? and month = ? and week = ? and step_num = ? ;";
 
// /*SQL UPDATE文 _ 目標・ステップ更新時 */
// private static final String SQL_UPDATE =  " UPDATE goal_step SET month_goal = ? , step1 = ? , step2 = ? , step3 = ? , step4 = ? , step5 = ? , week_goal = ?"
//                                                                 + "WHERE name = ? and month = ? and week = ? ;";
 
private static final GoalBean Goal = null;

 //月目標・達成のステップ・週目標を登録
// 引数:名前,登録内容
@SuppressWarnings("resource")
public ArrayList<Integer> register
(String name , int year , int month , int week , int register_date , String month_goal , String week_goal , int step_num , String step_content) 
    throws SQLException, ClassNotFoundException{
    //SQL文格納の為の準備
    PreparedStatement pstmt = null ;
    ResultSet rs = null;
    Connection conn = Database.getConnection();
    ArrayList<Integer> goal_no = new ArrayList<Integer>();

//    Bean格納用の返り値の定義
    GoalBean Goal = null;

try {
    pstmt = conn.prepareStatement(SQL_INSERT);
    
//    1、設定した目標とステップをデータベースに格納する
//          →ログインされた時点ではこちらを表示させる
    pstmt.setString(1, name);
    pstmt.setInt(2, year);
    pstmt.setInt(3, month);
    pstmt.setInt(4, week);
    pstmt.setInt(5, register_date);
    pstmt.setString(6, month_goal);
    pstmt.setString(7, week_goal);
    pstmt.setInt(8, step_num);
    pstmt.setString(9, step_content);

//    SQLの実行(実行結果は格納しない)
    pstmt.executeUpdate();
    
// UPDATEした内容をSELECT文にて出力し、Beanに格納する準備をする
    pstmt = conn.prepareStatement(SQL_SELECT);
    
// SELECT文で検索する内容を記述
    pstmt.setString(1, name);
    pstmt.setInt(2, month);
    pstmt.setInt(3, week);
    pstmt.setInt(4, register_date);
    
//SQLの実行と処理結果の格納
    rs = pstmt.executeQuery();
    
//    テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
while (rs.next()) {
    String tmpMonth_Goal = rs.getString("month_goal");
    String tmpWeek_Goal = rs.getString("week_goal");
    int tmpStep_Num= rs.getInt("step_num");
    int tmpGoal_no = rs.getInt("goal_no");

    Goal = new GoalBean();
    Goal.setMonth_goal(tmpMonth_Goal);
    Goal.setWeek_goal(tmpWeek_Goal);
    Goal.setStep_num(tmpStep_Num);
    goal_no.add(tmpGoal_no) ;
//
//    return Goal;
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

return goal_no;
}


//トップページ遷移前作業:名前と月,週を基に最新の目標とステップを取り出す(無ければ作業として未設定表示)
//引数:名前,月,週
public GoalBean check_step(String name , int month , int week)
    throws SQLException, ClassNotFoundException{
//SQL文格納の為の準備
PreparedStatement pstmt = null ;
ResultSet rs = null;
Connection conn = Database.getConnection();

try {
//Bean格納用の返り値の定義
GoalBean Goal = null;

//ステップ数を確認する
pstmt = conn.prepareStatement(SQL_COUNT_STEP);

//テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
//SELECT文で検索する内容を記述
pstmt.setString(1, name);
pstmt.setInt(2, month);
pstmt.setInt(3, week);

//SQLの実行と処理結果の格納
rs = pstmt.executeQuery();

while (rs.next()) {
    int tmpTotalStep = rs.getInt("num");
    
    Goal = new GoalBean();
    Goal.setStep_num(tmpTotalStep);
}

pstmt = conn.prepareStatement(SQL_SELECT_CHECK);

//テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
//SELECT文で検索する内容を記述
pstmt.setString(1, name);
pstmt.setInt(2, month);
pstmt.setInt(3, week);

//SQLの実行と処理結果の格納
rs = pstmt.executeQuery();

//テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
for (int i = 0 ; i < Goal.getStep_num() ; i++) {
    while (rs.next()) {
        String tmpMonth_Goal = rs.getString("month_goal");
        String tmpWeek_Goal = rs.getString("week_goal");
        int tmpStep_Num= rs.getInt("step_num");
        String tmpStep_Content = rs.getString("step_content");
        Goal.setMonth_goal(tmpMonth_Goal);
        Goal.setWeek_goal(tmpWeek_Goal);
    
    if (tmpStep_Num == 1) {
        Goal.setStep1(tmpStep_Content);
    }else if (tmpStep_Num == 2) {
        Goal.setStep2(tmpStep_Content);
    }else if (tmpStep_Num == 3) {
        Goal.setStep3(tmpStep_Content);
    }else if (tmpStep_Num == 4) {
        Goal.setStep4(tmpStep_Content);
    }else if (tmpStep_Num == 5) {
        Goal.setStep5(tmpStep_Content);
    }
    }
//    Goal = new GoalBean();

    return Goal;
}
} catch (SQLException e) {
e.printStackTrace();

}
return Goal;
}

//目標・ステップ記入後:登録した内容を選択する
//引数:名前
public GoalBean check_goal(String name , int month , int week , int register_date)
        throws SQLException, ClassNotFoundException{
 //SQL文格納の為の準備
 PreparedStatement pstmt = null ;
 ResultSet rs = null;
 Connection conn = Database.getConnection();

 try {
   //Bean格納用の返り値の定義
   GoalBean Goal = null;

   //ステップ数を確認する
   pstmt = conn.prepareStatement(SQL_COUNT_REGIST);

   //テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
   //SELECT文で検索する内容を記述
   pstmt.setString(1, name);
   pstmt.setInt(2, month);
   pstmt.setInt(3, week);
   pstmt.setInt(4, register_date);

   //SQLの実行と処理結果の格納
   rs = pstmt.executeQuery();

   while (rs.next()) {
       int tmpTotalStep = rs.getInt("num");
       
       Goal = new GoalBean();
       Goal.setStep_num(tmpTotalStep);
   }

   pstmt = conn.prepareStatement(SQL_SELECT);

   //テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
   //SELECT文で検索する内容を記述
   pstmt.setString(1, name);
   pstmt.setInt(2, month);
   pstmt.setInt(3, week);
   pstmt.setInt(4, register_date);

   //SQLの実行と処理結果の格納
   rs = pstmt.executeQuery();

   //テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
   for (int i = 0 ; i < Goal.getStep_num() ;) {
       while (rs.next()) {
           String tmpMonth_Goal = rs.getString("month_goal");
           String tmpWeek_Goal = rs.getString("week_goal");
           int tmpStep_Num= rs.getInt("step_num");
           String tmpStep_Content = rs.getString("step_content");
           Goal.setMonth_goal(tmpMonth_Goal);
           Goal.setWeek_goal(tmpWeek_Goal);
       
       if (tmpStep_Num == 1) {
           Goal.setStep1(tmpStep_Content);
       }else if (tmpStep_Num == 2) {
           Goal.setStep2(tmpStep_Content);
       }else if (tmpStep_Num == 3) {
           Goal.setStep3(tmpStep_Content);
       }else if (tmpStep_Num == 4) {
           Goal.setStep4(tmpStep_Content);
       }else if (tmpStep_Num == 5) {
           Goal.setStep5(tmpStep_Content);
       }
       }
       return Goal;
   }
    
} catch (SQLException e) {
 e.printStackTrace();

}finally {
 }
return Goal;

}


public int check_goalno(String name , int year , int month , int week_of_month , int stepnum)
        throws SQLException, ClassNotFoundException{
    //SQL文格納の為の準備
    PreparedStatement pstmt = null ;
    ResultSet rs = null;
    Connection conn = Database.getConnection();
    int goal_no = 0 ;

    try {
      //Bean格納用の返り値の定義
      GoalBean Goal = null;

      //ステップ数を確認する
      pstmt = conn.prepareStatement(SQL_SELECT_GOAL);

      //テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
      //SELECT文で検索する内容を記述
      pstmt.setString(1, name);
      pstmt.setInt(2, year);
      pstmt.setInt(3, month);
      pstmt.setInt(4, week_of_month);
      pstmt.setInt(5, stepnum);

      //SQLの実行と処理結果の格納
      rs = pstmt.executeQuery();

      while (rs.next()) {
          int tmpGoal_Num = rs.getInt("goal_no");
          
          goal_no = tmpGoal_Num ;
      }
       
   } catch (SQLException e) {
    e.printStackTrace();

   }finally {
    }
   return goal_no;

   }
}
