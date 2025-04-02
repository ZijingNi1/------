import java.sql.*;
public class Control extends supermarket{
Statement sql;ResultSet rs;Connection conn;boolean a;
public void connect(Connection con){
conn=con;
}
//该方法可查询所有商品的全部信息
public void select(){
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a");
System.out.printf("条码 商品名\t  价格\t   产地\t保质期\t库存  销量  收入\n");
while(rs.next()){
String id=rs.getString(1);
String name=rs.getString(2);
String price=rs.getString(3);
String unit=rs.getString(4);
String stock=rs.getString(5);
String origin=rs.getString(6);
String time=rs.getString(7);
String sell=rs.getString(8);
String money=rs.getString(9);
System.out.printf("%-5s",id);
System.out.printf("%-10s",name);
System.out.printf("%-8s",(price+"元/"+unit));
System.out.printf("%-5s",origin);
if(time!=null){
System.out.printf("%-6s",time+"天");
}
else{
System.out.printf("%-6s",time);
}
System.out.printf("%-6s",stock);
System.out.printf("%-6s",sell);
System.out.printf("%-6s\n",money);
}
}
catch(SQLException e){
System.out.println(e);
}
}
//该方法可根据条码查询商品的全部信息
public void idselect(int theid){
a=true;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a where id="+(theid+""));
System.out.printf("条码 商品名\t  价格\t   产地\t保质期\t库存  销量  收入\n");
while(rs.next()){
a=false;
String id=rs.getString(1);
String name=rs.getString(2);
String price=rs.getString(3);
String unit=rs.getString(4);
String stock=rs.getString(5);
String origin=rs.getString(6);
String time=rs.getString(7);
String sell=rs.getString(8);
String money=rs.getString(9);
System.out.printf("%-5s",id);
System.out.printf("%-10s",name);
System.out.printf("%-8s",(price+"元/"+unit));
System.out.printf("%-5s",origin);
if(time!=null){
System.out.printf("%-6s",time+"天");
}
else{
System.out.printf("%-6s",time);
}
System.out.printf("%-6s",stock);
System.out.printf("%-6s",sell);
System.out.printf("%-6s\n",money);
}
}
catch(SQLException e){
System.out.println(e);
}
if(a){
System.out.println("无此商品！");
}
}
//该方法实现进货功能
public void buy(int theid,int much){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set stock="+(much+"")+" where id="+(theid+""));
}
catch(SQLException e){
System.out.println(e);
}
}
//使用修改方法修改成功后均提示
//该方法实现修改商品名功能
public void changename(int theid,String name){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set name='"+name+"' where id="+(theid+""));
System.out.println("修改成功!");
}
catch(SQLException e){
System.out.println(e);
}
}
//该方法实现修改价格功能
public void changeprice(int theid,double price){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set price="+(price+"")+" where id="+(theid+""));
System.out.println("修改成功!");
}
catch(SQLException e){
System.out.println(e);
}
}
//该方法实现修改单位功能
public void changeunit(int theid,String unit){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set unit='"+unit+"' where id="+(theid+""));
System.out.println("修改成功!");
}
catch(SQLException e){
System.out.println(e);
}
}
//该方法实现修改产地功能
public void changeorigin(int theid,String origin){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set origin='"+origin+"' where id="+(theid+""));
System.out.println("修改成功!");
}
catch(SQLException e){
System.out.println(e);
}
}
//该方法实现修改保质期功能
public void changetime(int theid,int time){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set time="+(time+"")+" where id="+(theid+""));
System.out.println("修改成功!");
}
catch(SQLException e){ 
System.out.println(e);
}
}
//该方法实现上架商品功能
public void setnew(int id,String name,String unit,double price,int stock,String origin,int time,int sell,double money){
String shuju="("+(id+",'")+name+"',"+(price+",'")+unit+"',"+(stock+",'")+origin+"',"+(time+",")+(sell+",")+(money+")");
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("insert into a values"+shuju);
System.out.println("添加成功!");
}
catch(SQLException e){
System.out.println(e);
}
}
//该方法实现下架商品功能，本质上是从数据库中删除该条数据
public void remove(int theid){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("delete from a where id="+(theid+""));
System.out.println("下架成功!");
}
catch(SQLException e){
System.out.println(e);
}
}
//该方法实现核算总营收功能
public double summoney(){
double summoney=0;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a");
while(rs.next()){
double money=rs.getDouble(9);
summoney+=money;
}
}
catch(SQLException e){
System.out.println(e);
}
return summoney;
} 
}