//此类中的方法可用来展示功能面板和提示输入
public class Board{
//展示主界面功能面板和提示输入
public void primary(){
System.out.println("_________________________________________________________");
System.out.println("|                       超市系统                         |");
System.out.println("|      1  进入超市售货系统        2  进入超市管理系统    |");
System.out.println("|      0  退出超市系统                                   |");
System.out.println("---------------------------------------------------------");
System.out.printf("请输入所选功能编号:");
}
//展示销售系统功能面板和提示输入
public void sell(){
System.out.println("_________________________________________________________");
System.out.println("|                     超市售货系统                       |");
System.out.println("|     1  查询所有商品信息        2  根据商品名查询信息   |");
System.out.println("|     3  购买商品                0  返回超市系统         |");
System.out.println("---------------------------------------------------------");
System.out.printf("请输入所选功能编号:");
}
//展示管理系统功能面板和提示输入
public void control(){
System.out.println("_________________________________________________________");
System.out.println("|                     超市管理系统                       |");
System.out.println("|     1  查询所有商品信息        2  根据条码查询信息     |");
System.out.println("|     3  进货                    4  修改商品信息         |");
System.out.println("|     5  上架商品                6  下架商品             |");
System.out.println("|     7  核算总营业额            0  返回超市系统         |");
System.out.println("---------------------------------------------------------");
System.out.printf("请输入所选功能编号:");
}
//展示修改信息功能面板和提示输入
public void change(){
System.out.println("_________________________________________________________");
System.out.println("|                     修改信息选择                       |");
System.out.println("|     1  修改商品名字            2  修改商品价格         |");
System.out.println("|     3  修改商品单位            4  修改商品产地         |");
System.out.println("|     5  修改商品保质期          0  返回超市管理系统     |");
System.out.println("---------------------------------------------------------");
System.out.printf("请输入所选功能编号:");
}
}