import java.sql.*;
public class Sell extends supermarket{
Statement sql;ResultSet rs;Connection conn;boolean a;int price;
public void connect(Connection con){
conn=con;
}
//����ϵͳ��ȫ����ѯ����ʵ�ַ��������ڰ�ȫ�ԣ���桢���������۶��δ��ʾ
public void select(){
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a");
System.out.printf("���� ��Ʒ��\t  �۸�\t\t����\t������\n");
while(rs.next()){
String id=rs.getString(1);
String name=rs.getString(2);
String price=rs.getString(3);
String unit=rs.getString(4);
String origin=rs.getString(6);
String time=rs.getString(7);
System.out.printf("%-5s",id);
System.out.printf("%-10s",name);
System.out.printf("%-10s",(price+"Ԫ/"+unit));
System.out.printf("%-8s",origin);
if(time!=null){
System.out.printf("%-10s\n",time+"��");
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
//����ϵͳ�и�����Ʒ����ѯ����ʵ�ַ���������δ��ʾ
public void nameselect(String thename){
a=true;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a where name='"+thename+"'");
while(rs.next()){
a=false;
System.out.printf("���� ��Ʒ��\t  �۸�\t\t����\t������\n");
String id=rs.getString(1);
String name=rs.getString(2);
String price=rs.getString(3);
String unit=rs.getString(4);
String origin=rs.getString(6);
String time=rs.getString(7);
System.out.printf("%-5s",id);
System.out.printf("%-10s",name);
System.out.printf("%-10s",(price+"Ԫ/"+unit));
System.out.printf("%-8s",origin);
if(time!=null){
System.out.printf("%-10s\n",time+"��");
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
System.out.println("�޴���Ʒ��");
}
}
//�˷���������ѯ���ݿ����Ƿ���ڴ����룬�����򷵻�false������ϵͳ�͹���ϵͳ�����õ�
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
//�˷���������ѯ���ݿ����Ƿ���ڴ���Ʒ���������򷵻�false���ڹ���ϵͳ�޸���Ʒ�����������Ʒʱʹ��
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
//�˷�������������Ʒ��棬������ϵͳ������Ʒ�Ͳ���ϵͳ��������ʹ��
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
//�˷�������������Ʒ����������������ϵͳ�е���������
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
//�˷����������������Ӧ����Ʒ������������֤�������Ƿ�ΪƤ����
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
//�˷�������������Ʒ�۸񣬽���������ϵͳ�е���������
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
//�˷�������������Ʒ������������������ϵͳ�е���������
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
//�˷���������������ϵͳ�����۹��ܲ���������
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