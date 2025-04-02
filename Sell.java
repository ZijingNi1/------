import java.sql.*;
public class Sell extends supermarket{
Statement sql;ResultSet rs;Connection conn;boolean a;int price;
public void connect(Connection con){
conn=con;
}
//售卖系统中全部查询功能实现方法，出于安全性，库存、销量、销售额均未显示
public void select(){
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a");
System.out.printf("条码 商品名\t  价格\t\t产地\t保质期\n");
while(rs.next()){
String id=rs.getString(1);
String name=rs.getString(2);
String price=rs.getString(3);
String unit=rs.getString(4);
String origin=rs.getString(6);
String time=rs.getString(7);
System.out.printf("%-5s",id);
System.out.printf("%-10s",name);
System.out.printf("%-10s",(price+"元/"+unit));
System.out.printf("%-8s",origin);
if(time!=null){
System.out.printf("%-10s\n",time+"天");
}
else{
System.out.printf("%-10s\n",time);
}
}
}
catch(SQLException e){
System.out.println(e);
}
}
//售卖系统中根据商品名查询功能实现方法，部分未显示
public void nameselect(String thename){
a=true;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a where name='"+thename+"'");
while(rs.next()){
a=false;
System.out.printf("条码 商品名\t  价格\t\t产地\t保质期\n");
String id=rs.getString(1);
String name=rs.getString(2);
String price=rs.getString(3);
String unit=rs.getString(4);
String origin=rs.getString(6);
String time=rs.getString(7);
System.out.printf("%-5s",id);
System.out.printf("%-10s",name);
System.out.printf("%-10s",(price+"元/"+unit));
System.out.printf("%-8s",origin);
if(time!=null){
System.out.printf("%-10s\n",time+"天");
}
else{
System.out.printf("%-10s\n",time);
}
}
}
catch(SQLException e){
System.out.println(e);
}
if(a){
System.out.println("无此商品！");
}
}
//此方法用来查询数据库中是否存在此条码，存在则返回false，售卖系统和管理系统均有用到
public boolean find(int id){
a=true;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a where id="+(id+""));
while(rs.next()){
a=false;
}
}
catch(SQLException e){
System.out.println(e);
}
return a;
}
//此方法用来查询数据库中是否存在此商品名，存在则返回false，在管理系统修改商品名和添加新商品时使用
public boolean findname(String name){
a=false;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a where name='"+name+"'");
while(rs.next()){
a=true;
}
}
catch(SQLException e){
System.out.println(e);
}
return a;
}
//此方法用来返回商品库存，在售卖系统出售商品和操作系统进货功能使用
public int selectstock(int id){
int stock=0;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select stock from a where id="+(id+""));
while(rs.next()){
stock=rs.getInt(1);
}
}
catch(SQLException e){
System.out.println(e);
}
return stock;
}
//此方法用来返回商品销量，仅用于售卖系统中的售卖功能
public int selectsell(int id){
int sell=0;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select sell from a where id="+(id+""));
while(rs.next()){
sell=rs.getInt(1);
}
}
catch(SQLException e){
System.out.println(e);
}
return sell;
}
//此方法用来返回条码对应的商品名，仅用于验证购买物是否为皮卡丘
public String findpkq(int id){
String str="";
try{
sql=conn.createStatement();
rs=sql.executeQuery("select name from a where id="+(id+""));
while(rs.next()){
str=rs.getString(1);
}
}
catch(SQLException e){
System.out.println(e);
}
return str;
}
//此方法用来返回商品价格，仅用于售卖系统中的售卖功能
public double selectprice(int id){
double price=0.0;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select price from a where id="+(id+""));
while(rs.next()){
price=rs.getDouble(1);
}
}
catch(SQLException e){
System.out.println(e);
}
return price;
}
//此方法用来返回商品总销量，仅用于售卖系统中的售卖功能
public double selectmoney(int id){
double money=0;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select money from a where id="+(id+""));
while(rs.next()){
money=rs.getDouble(1);
}
}
catch(SQLException e){
System.out.println(e);
}
return money;
}
//此方法用来更新销售系统中销售功能产生的数据
public void sell(int id,int much,double money,int sell){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set stock="+(much+"")+" where id="+(id+""));
ok=sql.executeUpdate("update a set money="+(money+"")+" where id="+(id+""));
ok=sql.executeUpdate("update a set sell="+(sell+"")+" where id="+(id+""));
}
catch(SQLException e){
System.out.println(e);
}
}
}