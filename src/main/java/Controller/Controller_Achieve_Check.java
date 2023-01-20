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
 /*SQL INSERT文 _ 目標登録時_達成可否確認 */
 private static final String SQL_INSERT =  "INSERT INTO step_achieve (name , month , week , step1 , step2 , step3 , step4 , step5)"
                                                                 + "VALUES ( ? , ? , ? , 'true' , ? , ? , ? , ?);";
 /*SQL INSERT文 _  目標登録時_達成可否確認*/
 private static final String SQL_SELECT =  "SELECT name , month , week , step1 , step2 , step3 , step4 , step5 from step_achieve where name = ? ; " ;
 
//目標登録時　名前を起点として月と週名を記入し、記入ステップ部分にfalseのフラグを立てる
//引数:名前,月,週,ステップ2,ステップ3,ステップ4,ステップ5
public AchieveBean Goal_Step_Register(String name , int month, int week_of_month , Boolean step2 , Boolean step3 , Boolean step4 , Boolean step5) 
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
    pstmt.setBoolean(4, step2);
    pstmt.setBoolean(5, step3);
    pstmt.setBoolean(6, step4);
    pstmt.setBoolean(7, step5);
    
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
    Boolean tmpStep2     = rs.getBoolean("step2");
    Boolean tmpStep3     = rs.getBoolean("step3");
    Boolean tmpStep4     = rs.getBoolean("step4");
    Boolean tmpStep5     = rs.getBoolean("step5");
   
    Achieve = new AchieveBean();
    Achieve.setName(tmpName);
    Achieve.setMonth(tmpMonth);
    Achieve.setWeek(tmpWeek);
    Achieve.setStep1(true);
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
}
