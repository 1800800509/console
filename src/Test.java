import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bool = true;
        while (bool) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();//阻塞方法
            System.out.println("你输入的用户名：" + username);

            System.out.println("请输入密码：");
            String password = sc.next();

            System.out.println("你输入的密码是：" + password);

            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");//  /表示的就是classpath

            InputStream inPro = Class.forName("Test").getResourceAsStream("/Product.xlsx");//  /表示的就是classpath
            ReadUserExcel readUserExcel = new ReadUserExcel();//创建对象
            User users[] = readUserExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    ReadProductExcel readProductExcel=new ReadProductExcel();
                    Product products[]=readProductExcel.getAllProduct(inPro);
                    System.out.println("登陆成功");
                    bool=false;
                    for (Product product:products){
                        System.out.print(product.getID());
                        System.out.print("\t"+product.getName());
                        System.out.print("\t"+product.getPrice());
                        System.out.println("\t"+product.getDesc());
                    }
                    int count=0;
                    Product product1[]=new Product[3];
                    System.out.println("请输入商品ID，把该商品加入购物车");
                    String pId=sc.next();
                    ReadProductExcel readProductExcel1=new ReadProductExcel();
                    inPro=null;//输入流重新读取
                    inPro = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                    Product product=readProductExcel.getProductById(pId,inPro);
                    if(product!=null){
                        product1[count++]=product;
                        System.out.println("找到该商品，已加入购物车");
                    }
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}
