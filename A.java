import java.util.Scanner;
import java.sql.*;
public class A{ 
public static void main(String args[]){
Connection con;Statement sql;ResultSet rs;
int nmain,nsell,ncontrol,nchange;String thename,unit,origin;int theid,n,themuch,thetime,sellmuch;double price,sellmoney;
Scanner reader=new Scanner(System.in);
Board board=new Board();Sell mysell=new Sell();Control mycontrol=new Control();
//�������ݿ�
con=GetDBConnection.connectDB("supermarket","root","123456");
mysell.connect(con);
mycontrol.connect(con);
//������ϵͳ
while(true){
board.primary();
//�������������������ʾ����������
while(!reader.hasNextInt()){
String u=reader.next();
System.out.println("������������");
}
nmain=reader.nextInt();
//���볬���ۻ�ϵͳ
if(nmain==1){
while(true){
board.sell();
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
nsell=reader.nextInt();
// ��ѯ������Ʒ��Ϣ����
if(nsell==1){
mysell.select();
}
//������Ʒ����ѯ��Ϣ����
else if(nsell==2){
System.out.println("����������ѯ��Ʒ��:");
thename=reader.next();
mysell.nameselect(thename);
}
//������Ʒ����
else if(nsell==3){
System.out.println("��������������Ʒ������:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
theid=reader.nextInt();
//�����۵���Ʒ���Ƿ���Ƥ������ż
if(mysell.findpkq(theid).equals("Ƥ������ż")){
System.out.println("Ƥ������ż�������ͣ��Ų�����");
continue;
}
//δ�ҵ�����ʱ�����Ч����
if(mysell.find(theid)){
System.out.println("��Ч����");
continue;
}
//��ȡ��Ʒ���
n=mysell.selectstock(theid);
//������Ϊ0����������ϵͳ������ʾ
if(n==0){
System.out.println("��Ǹ������Ʒ�Ѹ���");
continue;
}
System.out.println("�����빺������:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
themuch=reader.nextInt();
//����Ʒ���С�ڹ�����ʱ�������治������Ĭ���۳�ʣ��ȫ����Ʒ
if(themuch>n){
System.out.printf("��治������������%d��\n",n);
themuch=n;
}
n=n-themuch;
//������Ʒ������
sellmuch=themuch+mysell.selectsell(theid);
//������Ʒ�����۶�
sellmoney=themuch*mysell.selectprice(theid)+mysell.selectmoney(theid);
//���¿�桢���������۶�
mysell.sell(theid,n,sellmoney,sellmuch);
System.out.printf("��������%.2fԪ����ӭ�´ι���\n",themuch*mysell.selectprice(theid));
}
//�˳������ۻ�ϵͳ
else if(nsell==0){
break;
}
//�������ֲ��ڹ���ѡ����ʱ��������ʾ
else{
System.out.println("��������Ч���֣�");
}
}
}
//���볬�й���ϵͳ
else if(nmain==2){
while(true){
board.control();
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
ncontrol=reader.nextInt();
//��ѯ������Ʒ��Ϣ����
if(ncontrol==1){
mycontrol.select();
}
//���������ѯ��Ʒ��Ϣ����
else if(ncontrol==2){
System.out.println("����������ѯ����:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
theid=reader.nextInt();
mycontrol.idselect(theid);
}
//��������
else if(ncontrol==3){
System.out.println("������������������:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
theid=reader.nextInt();
if(mysell.find(theid)){
System.out.println("��Ч����");
continue;
}
System.out.println("�������������:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
themuch=reader.nextInt();
//��ȡ��ǰ��沢���мӺ�
n=mysell.selectstock(theid);
n+=themuch;
//���¿������
mycontrol.buy(theid,n);
System.out.printf("����Ϊ%d����Ʒ��ǰ���Ϊ%d\n",theid,n);
}
//�޸���Ʒ��Ϣ���ܣ�����ѡ��ɵ���ѡ�������
else if(ncontrol==4){
System.out.println("���������޸���Ʒ������:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
theid=reader.nextInt();
if(mysell.find(theid)){
System.out.println("��Ч����");
continue;
}
while(true){
board.change();
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
nchange=reader.nextInt();
//������Ʒ�����ظ�ʱ��ʾ
if(nchange==1){
System.out.printf("����������Ʒ��:");
thename=reader.next();
if(mysell.findname(thename)){
System.out.println("����Ʒ���Ѵ���");
continue;
}
mycontrol.changename(theid,thename);
}
//������Ʒ�۸�
else if(nchange==2){
System.out.println("�������¼۸�:");
while(!reader.hasNextDouble()){
String useless=reader.next();
System.out.println("���������֣�");
}
price=reader.nextDouble();
mycontrol.changeprice(theid,price);
}
//������Ʒ��λ
else if(nchange==3){
System.out.printf("�������µ�λ:");
unit=reader.next();
mycontrol.changeunit(theid,unit);
}
//������Ʒ����
else if(nchange==4){
System.out.printf("�������²���:");
origin=reader.next();
mycontrol.changeorigin(theid,origin);
}
//������Ʒ������
else if(nchange==5){
System.out.println("�������µı�����:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
thetime=reader.nextInt();
mycontrol.changetime(theid,thetime);
}
//�˳�����
else if(nchange==0){
break;
}
else{
System.out.println("��������Ч���֣�");
}
}
}
//�ϼ���Ʒ���ܣ�Ҫ���������Ʒ�������ظ�
else if(ncontrol==5){
System.out.println("��������Ʒ����:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
theid=reader.nextInt();
if(!mysell.find(theid)){
System.out.println("�������Ѵ���");
continue;
}
System.out.printf("��������Ʒ��:");
thename=reader.next();
if(mysell.findname(thename)){
System.out.println("����Ʒ���Ѵ���");
continue;
}
System.out.println("��������Ʒ�۸�:");
while(!reader.hasNextDouble()){
String useless=reader.next();
System.out.println("���������֣�");
}
price=reader.nextDouble();
System.out.printf("��������Ʒ��λ:");
unit=reader.next();
System.out.println("��������Ʒ���:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
themuch=reader.nextInt();
System.out.printf("��������Ʒ����:");
origin=reader.next();
System.out.println("��������Ʒ������:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
thetime=reader.nextInt();
System.out.println("��������Ʒ��������:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
sellmuch=reader.nextInt();
System.out.println("��������Ʒ�����۽��:");
while(!reader.hasNextDouble()){
String useless=reader.next();
System.out.println("���������֣�");
}
sellmoney=reader.nextDouble();
//���ø÷�����������
mycontrol.setnew(theid,thename,unit,price,themuch,origin,thetime,sellmuch,sellmoney);
}
//�¼���Ʒ����
else if(ncontrol==6){
System.out.println("�������¼���Ʒ������:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("������������");
}
theid=reader.nextInt();
if(mysell.find(theid)){
System.out.println("��Ч����");
continue;
}
mycontrol.remove(theid);
}
//������Ӫҵ���
else if(ncontrol==7){
System.out.printf("��Ӫ��Ϊ:%.2f\n",mycontrol.summoney());
}
//�˳�����ϵͳ
else if(ncontrol==0){
break;
}
else{
System.out.println("��������Ч���֣�");
}
}
}
//�˳���ϵͳ
else if(nmain==0){
break;
}
else{
System.out.println("��������Ч���֣�");
}
}
}
}