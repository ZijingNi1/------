import java.util.Scanner;
import java.sql.*;
public class A{ 
public static void main(String args[]){
Connection con;Statement sql;ResultSet rs;
int nmain,nsell,ncontrol,nchange;String thename,unit,origin;int theid,n,themuch,thetime,sellmuch;double price,sellmoney;
Scanner reader=new Scanner(System.in);
Board board=new Board();Sell mysell=new Sell();Control mycontrol=new Control();
//连接数据库
con=GetDBConnection.connectDB("supermarket","root","123456");
mysell.connect(con);
mycontrol.connect(con);
//进入主系统
while(true){
board.primary();
//如果输入非整数，则会提示并重新输入
while(!reader.hasNextInt()){
String u=reader.next();
System.out.println("请输入整数！");
}
nmain=reader.nextInt();
//进入超市售货系统
if(nmain==1){
while(true){
board.sell();
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
nsell=reader.nextInt();
// 查询所有商品信息功能
if(nsell==1){
mysell.select();
}
//根据商品名查询信息功能
else if(nsell==2){
System.out.println("请输入所查询商品名:");
thename=reader.next();
mysell.nameselect(thename);
}
//出售商品功能
else if(nsell==3){
System.out.println("请输入所购买商品的条码:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
theid=reader.nextInt();
//检测出售的商品名是否是皮卡丘玩偶
if(mysell.findpkq(theid).equals("皮卡丘玩偶")){
System.out.println("皮卡丘玩偶仅供观赏，概不出售");
continue;
}
//未找到条码时输出无效条码
if(mysell.find(theid)){
System.out.println("无效条码");
continue;
}
//获取商品库存
n=mysell.selectstock(theid);
//如果库存为0，跳出售卖系统，并提示
if(n==0){
System.out.println("抱歉，该商品已告罄");
continue;
}
System.out.println("请输入购买数量:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
themuch=reader.nextInt();
//当商品库存小于购买量时，输出库存不够，并默认售出剩余全部商品
if(themuch>n){
System.out.printf("库存不够，仅能售您%d件\n",n);
themuch=n;
}
n=n-themuch;
//计算商品总销量
sellmuch=themuch+mysell.selectsell(theid);
//计算商品总销售额
sellmoney=themuch*mysell.selectprice(theid)+mysell.selectmoney(theid);
//更新库存、销量、销售额
mysell.sell(theid,n,sellmoney,sellmuch);
System.out.printf("您共消费%.2f元，欢迎下次光临\n",themuch*mysell.selectprice(theid));
}
//退出超市售货系统
else if(nsell==0){
break;
}
//输入数字不在功能选项内时，进行提示
else{
System.out.println("请输入有效数字！");
}
}
}
//进入超市管理系统
else if(nmain==2){
while(true){
board.control();
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
ncontrol=reader.nextInt();
//查询所有商品信息功能
if(ncontrol==1){
mycontrol.select();
}
//根据条码查询商品信息功能
else if(ncontrol==2){
System.out.println("请输入所查询条码:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
theid=reader.nextInt();
mycontrol.idselect(theid);
}
//进货功能
else if(ncontrol==3){
System.out.println("请输入所进货的条码:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
theid=reader.nextInt();
if(mysell.find(theid)){
System.out.println("无效条码");
continue;
}
System.out.println("请输入进货数量:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
themuch=reader.nextInt();
//获取当前库存并进行加和
n=mysell.selectstock(theid);
n+=themuch;
//更新库存数据
mycontrol.buy(theid,n);
System.out.printf("条码为%d的商品当前库存为%d\n",theid,n);
}
//修改商品信息功能，内置选项，可单独选择更改项
else if(ncontrol==4){
System.out.println("请输入所修改商品的条码:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
theid=reader.nextInt();
if(mysell.find(theid)){
System.out.println("无效条码");
continue;
}
while(true){
board.change();
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
nchange=reader.nextInt();
//更改商品名，重复时提示
if(nchange==1){
System.out.printf("请输入新商品名:");
thename=reader.next();
if(mysell.findname(thename)){
System.out.println("该商品名已存在");
continue;
}
mycontrol.changename(theid,thename);
}
//更改商品价格
else if(nchange==2){
System.out.println("请输入新价格:");
while(!reader.hasNextDouble()){
String useless=reader.next();
System.out.println("请输入数字！");
}
price=reader.nextDouble();
mycontrol.changeprice(theid,price);
}
//更改商品单位
else if(nchange==3){
System.out.printf("请输入新单位:");
unit=reader.next();
mycontrol.changeunit(theid,unit);
}
//更改商品产地
else if(nchange==4){
System.out.printf("请输入新产地:");
origin=reader.next();
mycontrol.changeorigin(theid,origin);
}
//更改商品保质期
else if(nchange==5){
System.out.println("请输入新的保质期:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
thetime=reader.nextInt();
mycontrol.changetime(theid,thetime);
}
//退出更改
else if(nchange==0){
break;
}
else{
System.out.println("请输入有效数字！");
}
}
}
//上架商品功能，要求条码和商品名均不重复
else if(ncontrol==5){
System.out.println("请设置商品条码:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
theid=reader.nextInt();
if(!mysell.find(theid)){
System.out.println("该条码已存在");
continue;
}
System.out.printf("请设置商品名:");
thename=reader.next();
if(mysell.findname(thename)){
System.out.println("该商品名已存在");
continue;
}
System.out.println("请设置商品价格:");
while(!reader.hasNextDouble()){
String useless=reader.next();
System.out.println("请输入数字！");
}
price=reader.nextDouble();
System.out.printf("请设置商品单位:");
unit=reader.next();
System.out.println("请设置商品库存:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
themuch=reader.nextInt();
System.out.printf("请设置商品产地:");
origin=reader.next();
System.out.println("请设置商品保质期:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
thetime=reader.nextInt();
System.out.println("请设置商品已销售量:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
sellmuch=reader.nextInt();
System.out.println("请设置商品已销售金额:");
while(!reader.hasNextDouble()){
String useless=reader.next();
System.out.println("请输入数字！");
}
sellmoney=reader.nextDouble();
//利用该方法更新数据
mycontrol.setnew(theid,thename,unit,price,themuch,origin,thetime,sellmuch,sellmoney);
}
//下架商品功能
else if(ncontrol==6){
System.out.println("请输入下架商品的条码:");
while(!reader.hasNextInt()){
String useless=reader.next();
System.out.println("请输入整数！");
}
theid=reader.nextInt();
if(mysell.find(theid)){
System.out.println("无效条码");
continue;
}
mycontrol.remove(theid);
}
//核算总营业额功能
else if(ncontrol==7){
System.out.printf("总营收为:%.2f\n",mycontrol.summoney());
}
//退出管理系统
else if(ncontrol==0){
break;
}
else{
System.out.println("请输入有效数字！");
}
}
}
//退出主系统
else if(nmain==0){
break;
}
else{
System.out.println("请输入有效数字！");
}
}
}
}