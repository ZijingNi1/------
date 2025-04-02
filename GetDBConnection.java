//此类用来进行数据库连接
import java.sql.*;
public class GetDBConnection{
public static Connection connectDB(String DBName,String user,String mm){
 Connection con = null;
 try{Class.forName("com.mysql.cj.jdbc.Driver");
 }
 catch(Exception e){}
 String uri="jdbc:mysql://localhost:3306/"+DBName+"?useSSL=true&serverTimezone=CST&chatacterEncoding=utf-8";
 try{
 con=DriverManager.getConnection(uri,user,mm);
 }
 catch(SQLException e){}
 return con;
}
}