import java.sql.*;
public class Control extends supermarket{
Statement sql;ResultSet rs;Connection conn;boolean a;
public void connect(Connection con){
conn=con;
}
//�÷����ɲ�ѯ������Ʒ��ȫ����Ϣ
public void select(){
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a");
System.out.printf("���� ��Ʒ��\t  �۸�\t   ����\t������\t���  ����  ����\n");
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
System.out.printf("%-8s",(price+"Ԫ/"+unit));
System.out.printf("%-5s",origin);
if(time!=null){
System.out.printf("%-6s",time+"��");
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
//�÷����ɸ��������ѯ��Ʒ��ȫ����Ϣ
public void idselect(int theid){
a=true;
try{
sql=conn.createStatement();
rs=sql.executeQuery("select * from a where id="+(theid+""));
System.out.printf("���� ��Ʒ��\t  �۸�\t   ����\t������\t���  ����  ����\n");
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
System.out.printf("%-8s",(price+"Ԫ/"+unit));
System.out.printf("%-5s",origin);
if(time!=null){
System.out.printf("%-6s",time+"��");
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
System.out.println("�޴���Ʒ��");
}
}
//�÷���ʵ�ֽ�������
public void buy(int theid,int much){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set stock="+(much+"")+" where id="+(theid+""));
}
catch(SQLException e){
System.out.println(e);
}
}
//ʹ���޸ķ����޸ĳɹ������ʾ
//�÷���ʵ���޸���Ʒ������
public void changename(int theid,String name){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set name='"+name+"' where id="+(theid+""));
System.out.println("�޸ĳɹ�!");
}
catch(SQLException e){
System.out.println(e);
}
}
//�÷���ʵ���޸ļ۸���
public void changeprice(int theid,double price){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set price="+(price+"")+" where id="+(theid+""));
System.out.println("�޸ĳɹ�!");
}
catch(SQLException e){
System.out.println(e);
}
}
//�÷���ʵ���޸ĵ�λ����
public void changeunit(int theid,String unit){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set unit='"+unit+"' where id="+(theid+""));
System.out.println("�޸ĳɹ�!");
}
catch(SQLException e){
System.out.println(e);
}
}
//�÷���ʵ���޸Ĳ��ع���
public void changeorigin(int theid,String origin){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set origin='"+origin+"' where id="+(theid+""));
System.out.println("�޸ĳɹ�!");
}
catch(SQLException e){
System.out.println(e);
}
}
//�÷���ʵ���޸ı����ڹ���
public void changetime(int theid,int time){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("update a set time="+(time+"")+" where id="+(theid+""));
System.out.println("�޸ĳɹ�!");
}
catch(SQLException e){ 
System.out.println(e);
}
}
//�÷���ʵ���ϼ���Ʒ����
public void setnew(int id,String name,String unit,double price,int stock,String origin,int time,int sell,double money){
String shuju="("+(id+",'")+name+"',"+(price+",'")+unit+"',"+(stock+",'")+origin+"',"+(time+",")+(sell+",")+(money+")");
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("insert into a values"+shuju);
System.out.println("��ӳɹ�!");
}
catch(SQLException e){
System.out.println(e);
}
}
//�÷���ʵ���¼���Ʒ���ܣ��������Ǵ����ݿ���ɾ����������
public void remove(int theid){
try{
sql=conn.createStatement();
int ok=sql.executeUpdate("delete from a where id="+(theid+""));
System.out.println("�¼ܳɹ�!");
}
catch(SQLException e){
System.out.println(e);
}
}
//�÷���ʵ�ֺ�����Ӫ�չ���
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