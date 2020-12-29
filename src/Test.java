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
                    Product products[]=readProductExcel.readExcel(inPro);
                    System.out.println("登陆成功");
                    for (Product product:products){
                        System.out.print(product.getID());
                        System.out.print("\t"+product.getName());
                        System.out.print("\t"+product.getPrice());
                        System.out.println("\t"+product.getDesc());
                    }
                    bool=false;
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}
