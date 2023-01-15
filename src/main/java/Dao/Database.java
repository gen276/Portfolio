package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//データベースに接続する為のクラス

public interface Database{
    /*ドライバー名 */
    static final String DRIVER = "org.postgresql.Driver";
    /*JDBC接続先情報 */
    static final String CONNECT = "jdbc:postgresql://localhost:5432/study";
    /*ユーザー名 */
    static final String USER = "postgres";
    /*パスワード */
    static final String PASS = "yato1501";
    
    public static Connection getConnection() 
            throws SQLException, ClassNotFoundException {
    Class.forName(DRIVER);
    Connection connection = null;
    connection = DriverManager.getConnection(CONNECT , USER , PASS);
    
    return connection;
}
}
