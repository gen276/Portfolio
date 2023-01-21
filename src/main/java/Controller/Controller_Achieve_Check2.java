package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.AchieveBean;
import Dao.Database;

//step_achieveのデータベースを変更するプログラム

//機能:目標登録時　名前を起点として月と週名を記入し、記入ステップ部分にfalseのフラグを立てる
 
public class Controller_Achieve_Check2 implements Database{
 /*SQL INSERT文 _ 目標登録時_新たな目標の設定と全てのステップの達成状況の初期化 */
 private static final String SQL_INSERT =  "INSERT INTO step_achieve (name , month , week , step1 , step2 , step3 , step4 , step5)"
                                                                 + "VALUES ( ? , ? , ? , 'false' , 'false' , 'false' , 'false' , 'false');";
 /*SQL INSERT文 _  目標登録時_達成可否確認*/
 private static final String SQL_SELECT =  "SELECT name , month , week , step1 , step2 , step3 , step4 , step5 from step_achieve where name = ? ; " ;
 
 /*SQL SELECT文 _  目標登録時_目標以前登録確認*/
 private static final String SQL_SELECT_STEP =  "SELECT step1 , step2 , step3 , step4 , step5 from step_achieve where name = ? and month = ? and week = ?; " ;

 /*SQL UPDATE文 _ 目標・ステップ更新時 */
 private static final String SQL_UPDATE =  " UPDATE step_achieve SET step1 = 'false' , step2 = 'false' , step3 = 'false' , step4 = 'false' , step5 = 'false' "
                                                                 + "WHERE name = ? and month = ? and week = ? ;";
 
//目標登録時　名前を起点として月と週名を記入し、記入ステップ部分にfalseのフラグを立てる
//引数:名前,月,週,ステップ2,ステップ3,ステップ4,ステップ5
public AchieveBean Goal_Step_Register(String name , int month, int week_of_month)
    throws SQLException, ClassNotFoundException{
    //SQL文格納の為の準備
    PreparedStatement pstmt = null ;
    ResultSet rs = null;
    Connection conn = Database.getConnection();

//    Bean格納用の返り値の定義
    AchieveBean Achieve = null;

try {
    pstmt = conn.prepareStatement(SQL_INSERT);
    
//    1、設定した目標とステップをデータベースに格納する
//          →ログインされた時点ではこちらを表示させる
    pstmt.setString(1, name);
    pstmt.setInt(2, month);
    pstmt.setInt(3, week_of_month);
//    pstmt.setBoolean(4, step2);
//    pstmt.setBoolean(5, step3);
//    pstmt.setBoolean(6, step4);
//    pstmt.setBoolean(7, step5);
    
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
    String tmpName       = rs.getString("name");
    int      tmpMonth       = rs.getInt("month");
    int      tmpWeek        = rs.getInt("week");
    Boolean tmpStep1     = rs.getBoolean("step1");
    Boolean tmpStep2     = rs.getBoolean("step2");
    Boolean tmpStep3     = rs.getBoolean("step3");
    Boolean tmpStep4     = rs.getBoolean("step4");
    Boolean tmpStep5     = rs.getBoolean("step5");
   
    Achieve = new AchieveBean();
    Achieve.setName(tmpName);
    Achieve.setMonth(tmpMonth);
    Achieve.setWeek(tmpWeek);
    Achieve.setStep1(tmpStep1);
    Achieve.setStep2(tmpStep2);
    Achieve.setStep3(tmpStep3);
    Achieve.setStep4(tmpStep4);
    Achieve.setStep5(tmpStep5);
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

return Achieve;
}


//目標登録時　同月同週で以前に目標を立てた事があるかを確認する
//引数:名前,月,週
public AchieveBean Before_Goal(String name , int month, int week_of_month)
        throws SQLException, ClassNotFoundException{
  //SQL文格納の為の準備
  PreparedStatement pstmt = null ;
  ResultSet rs = null;
  Connection conn = Database.getConnection();

//  Bean格納用の返り値の定義
  AchieveBean Achieve = null;

try {  
//以前に登録された内容があるか確認する
    pstmt = conn.prepareStatement(SQL_SELECT_STEP);   
 
//  1、過去に設定された目標があるかどうかを確認する
  pstmt.setString(1, name);
  pstmt.setInt(2, month);
  pstmt.setInt(3, week_of_month);
  
//SQLの実行と処理結果の格納
  rs = pstmt.executeQuery();
  
//  テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
while (rs.next()) {
  Boolean tmpStep1     = rs.getBoolean("step1");
  Boolean tmpStep2     = rs.getBoolean("step2");
  Boolean tmpStep3     = rs.getBoolean("step3");
  Boolean tmpStep4     = rs.getBoolean("step4");
  Boolean tmpStep5     = rs.getBoolean("step5");
 
  Achieve = new AchieveBean();
  Achieve.setStep1(tmpStep1);
  Achieve.setStep2(tmpStep2);
  Achieve.setStep3(tmpStep3);
  Achieve.setStep4(tmpStep4);
  Achieve.setStep5(tmpStep5);
  return Achieve;
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
return Achieve;
}


//同じ月と週に目標があった場合に登録済みの内容を更新する
//引数:名前,月,週,登録内容
public AchieveBean update_register(String name ,int  month , int week)
  throws SQLException, ClassNotFoundException{
//SQL文格納の為の準備
PreparedStatement pstmt = null ;
ResultSet rs = null;
Connection conn = Database.getConnection();
//Bean格納用の返り値の定義
AchieveBean Achieve = null;

try {
pstmt = conn.prepareStatement(SQL_UPDATE);
  
//  1、以前設定した目標の内容を更新する
//  pstmt.setBoolean(1, step2);
//  pstmt.setBoolean(2, step3);
//  pstmt.setBoolean(3, step4);
//  pstmt.setBoolean(4, step5);
  pstmt.setString(1, name);
  pstmt.setInt(2, month);
  pstmt.setInt(3, week);
 
//  SQLの実行(実行結果は格納しない)
  pstmt.executeUpdate();
  
//UPDATEした内容をSELECT文にて出力し、Beanに格納する準備をする
  pstmt = conn.prepareStatement(SQL_SELECT_STEP);
  
//SELECT文で検索する内容を記述
  pstmt.setString(1, name);
  pstmt.setInt(2, month);
  pstmt.setInt(3, week);
  
//SQLの実行と処理結果の格納
  rs = pstmt.executeQuery();
  
//  テーブル内の列名から抽出する内容をgetStringで取ってくる(※1度格納した内容を再度抽出してくる)
  while (rs.next()) {
      Boolean tmpStep1     = rs.getBoolean("step1");
      Boolean tmpStep2     = rs.getBoolean("step2");
      Boolean tmpStep3     = rs.getBoolean("step3");
      Boolean tmpStep4     = rs.getBoolean("step4");
      Boolean tmpStep5     = rs.getBoolean("step5");
     
      Achieve = new AchieveBean();
      Achieve.setStep1(tmpStep1);
      Achieve.setStep2(tmpStep2);
      Achieve.setStep3(tmpStep3);
      Achieve.setStep4(tmpStep4);
      Achieve.setStep5(tmpStep5);
      return Achieve;
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
  return Achieve;
  }
}