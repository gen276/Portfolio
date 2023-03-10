package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.AchieveBean;
import Bean.Goal_ListBean;
import Dao.Database;

//step_achieveのデータベースを変更するプログラム

//機能:目標登録時　名前を起点として月と週名を記入し、記入ステップ部分にfalseのフラグを立てる
 
public class Controller_Achieve_Check implements Database{
    
//    /*SQL SELECT文 _  目標登録時_目標以前登録確認*/
    private static final String SQL_SELECT_STEP =  "SELECT step_num , step_achieve from step_achieve where name = ? and month = ? and week = ?; " ;
//    
 /*SQL INSERT文 _ 目標登録時_新たな目標行の設定 */
 private static final String SQL_INSERT =  "INSERT INTO step_achieve (goal_no , achieve)"
                                                                 + "VALUES ( ? , 'false');";
 
 /*SQL UPDATE文 _ステップ完了時trueのフラグを立てる */
 private static final String SQL_UPDATE =  " UPDATE step_achieve SET achieve = 'true' "
                                                                 + "WHERE goal_no = ? ;";
 
 /*SQL SELECT文 _過去の目標一覧表示時の内容を表示する */
 private static final String SQL_SELECT = " SELECT goal.year , goal.month , goal.week , goal.register_date , goal.month_goal , goal.week_goal , goal.step_num , goal.step_content , step_achieve.achieve"
                                                                 + " FROM goal  INNER JOIN step_achieve ON goal.goal_no = step_achieve.goal_no "
                                                                 + "WHERE goal.name = ? "
                                                                 + " ORDER by year DESC , month DESC , week DESC , step_num asc  "
                                                                 + "LIMIT 10 ; " ;
 
 Goal_ListBean goal = new Goal_ListBean();
 
//目標登録時　goal_noの該当する部分にfalseのフラグを立てる
//引数:goal_no
public AchieveBean Goal_Step_Register(int goal_no)
    throws SQLException, ClassNotFoundException{
    //SQL文格納の為の準備
    PreparedStatement pstmt = null ;
    ResultSet rs = null;
    Connection conn = Database.getConnection();

////    Bean格納用の返り値の定義
//    AchieveBean Achieve = null;

try {    
        pstmt = conn.prepareStatement(SQL_INSERT);
        
        pstmt.setInt(1, goal_no);
        
//        SQLの実行(実行結果は格納しない)
        pstmt.executeUpdate();
}
//    }
//    
   catch (SQLException e) {
    e.printStackTrace();
    }
return null;
}


//同じ月と週に目標があった場合に登録済みの内容を更新する
//引数:名前,月,週,登録内容
public AchieveBean step_achieve(int goal_no)
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
  pstmt.setInt(1, goal_no);
 
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

//過去の履歴を参照するリストを作成する為に値を抽出しリストを返却するSQL
//引数:名前
public List<Goal_ListBean> Create_List(String name)
throws SQLException, ClassNotFoundException{
//SQL文格納の為の準備
PreparedStatement pstmt = null ;
ResultSet rs = null;
Connection conn = Database.getConnection();
Goal_ListBean data = null ;
////SQL実行後に格納するリストの作成
//ArrayList<Goal_ListBean> goal = new ArrayList<Goal_ListBean>();

try {
pstmt = conn.prepareStatement(SQL_SELECT);

pstmt.setString(1, name);

//List<Integer> idList;
List<Goal_ListBean> goal_List = new ArrayList<Goal_ListBean>();

//SQLの実行(実行結果は格納しない)
rs =pstmt.executeQuery();

for (int i = 0 ; i < 5 ; i++) {
    while (rs.next()) {
        int Year            = rs.getInt("year");
        int Month         = rs.getInt("month");
        int Week          = rs.getInt("week");
        int Date           = rs.getInt("register_date");
        String M_Goal      = rs.getString("month_goal");
        String W_Goal     = rs.getString("week_goal");
        int     Step_Num = rs.getInt("step_num");
        String Step_Content = rs.getString("step_content");
        Boolean Achieve    = rs.getBoolean("achieve");
        
        Goal_ListBean goal = new Goal_ListBean() ;
        goal.setDate(String.valueOf(Year) + "/" + String.valueOf(Month) + "/" + String.valueOf(Date));
        goal.setM_goal(M_Goal);
        goal.setW_goal(W_Goal);
        goal.setStep_num(Step_Num);
        goal.setStep_content(Step_Content);
        if (Achieve == true) {
            goal.setAchieve("○");
        }else {
            goal.setAchieve("×");
        }
        
        goal_List.add(goal);
    }
    return goal_List;
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
}