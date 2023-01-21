package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.AchieveBean;
import Dao.Database;

//step_achieveのデータベースを変更するプログラム

//機能:目標登録時　名前を起点として月と週名を記入し、記入ステップ部分にfalseのフラグを立てる
 
public class Controller_Achieve_Check implements Database{
//    /*SQL SELECT文 _  目標登録時_目標以前登録確認*/
    private static final String SQL_SELECT_STEP =  "SELECT step_num , step_achieve from step_achieve where name = ? and month = ? and week = ?; " ;
//    
 /*SQL INSERT文 _ 目標登録時_新たな目標行の設定 */
 private static final String SQL_INSERT =  "INSERT INTO step_achieve (name , month , week , step_num , step_achieve , register_date)"
                                                                 + "VALUES ( ? , ? , ? , ? , 'false' , ?);";
 
 /*SQL UPDATE文 _ステップ完了時trueのフラグを立てる */
 private static final String SQL_UPDATE =  " UPDATE step_achieve SET step_achieve = 'true' "
                                                                 + "WHERE name = ? and month = ? and week = ? and step_num = ? ;";
 
 /*SQL SELECT文 _過去の目標一覧表示時の内容を表示する */
 private static final String SQL_SELECT = " SELECT step_achieve.month , goal_step.month_goal ,  step_achieve.week , goal_step.week_goal , step_num , step_achieve "
                                                               +  "FROM step_achieve  INNER JOIN goal_step ON goal_step.month = step_achieve.month WHERE goal_step.name = ? "
                                                               +  " ORDER by year ASC , month ASC , week asc , step_num asc  ; " ;
 
//目標登録時　名前を起点として月と週名を記入し、記入ステップ部分にfalseのフラグを立てる
//引数:名前,月,週,ステップ2,ステップ3,ステップ4,ステップ5
public AchieveBean Goal_Step_Register(String name , int month, int week_of_month , int step_num , int date)
    throws SQLException, ClassNotFoundException{
    //SQL文格納の為の準備
    PreparedStatement pstmt = null ;
    ResultSet rs = null;
    Connection conn = Database.getConnection();

////    Bean格納用の返り値の定義
//    AchieveBean Achieve = null;

try {    
//    1、登録したステップの数だけステップをデータベースに格納する
//         (月と週と日付は全て同じ)
    for (int i = 1 ; i <= step_num ; i++) {
        pstmt = conn.prepareStatement(SQL_INSERT);
        
        pstmt.setString(1, name);
        pstmt.setInt(2, month);
        pstmt.setInt(3, week_of_month);
        pstmt.setInt(4, i);
        pstmt.setInt(5, date);
        
//        SQLの実行(実行結果は格納しない)
        pstmt.executeUpdate();
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

//同じ月と週に目標があった場合に登録済みの内容を更新する
//引数:名前,月,週,登録内容
public AchieveBean step_achieve(String name ,int  month , int week , int step_num)
  throws SQLException, ClassNotFoundException{
//SQL文格納の為の準備
PreparedStatement pstmt = null ;
ResultSet rs = null;
Connection conn = Database.getConnection();
//Bean格納用の返り値の定義
AchieveBean Achieve = null;

try {
pstmt = conn.prepareStatement(SQL_UPDATE);
  
//  1、完了したステップの完了状況を更新する
  pstmt.setString(1, name);
  pstmt.setInt(2, month);
  pstmt.setInt(3, week);
  pstmt.setInt(4, step_num);
 
//  SQLの実行(実行結果は格納しない)
  pstmt.executeUpdate();
  
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
}