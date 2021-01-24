import java.sql.SQLOutput;
import java.util.Scanner;

//IDEA的代码是自动保存的
public class LanqiaoSystem {
    //以下是Java工程师资料，在输入Java工程师时输入的内容
    static int engNo = -1;          //Java工程师编号
    static String engName = "";     //Java工程师姓名
    static int engSex = -1;         //Java工程师性别（1代表男，2代表女）
    static int engEdu = -1;         //Java工程师学历（1代表大专，2代表本科，3代表硕士，
                                    //4代表博士，5代表其他）
    static int baseSalary = 3000;   //Java工程师底薪
    static double inSurance = 3000 * 0.105;     //Java工程师月应扣保险金额
    //以下是 Java 工程师月工作情况资料，在计算 Java 工程师月薪时再输入
    static int comResult = 100;     //Java工程师月工作完成分数（最小值为 0，最大值为 150）
    static double workDay = 22;     //Java工程师月实际工作天数
    //以下是Java工程师资料和Java工程师月完成工作情况资料计算出来的Java工程师月薪
    static double engSalary = 0.0;            //Java工程师月薪

    //定义选择位成员变量，由多个方法共享
    static int choose = -1;         //用户在主界面上选择的输入
    static boolean status = false;  //status 表示 Java 工程师资料是否输入完毕
                                    //注意，不包括 Java 工程师月工作情况资料
    static Scanner input = new Scanner(System.in);

    //该方法实现登录功能，无返回值
    public static void Login(){
        //boolean flag = true;
        //System.out.println("flag的值为：" + flag);
        System.out.println("*******登录*******");
        //while(true) {
            //使用字符串String存储密码，
            String userPass = "";       //用户输入的密码
            final String PASSWORD = "123456";//正确密码为123456
            Scanner input = new Scanner(System.in);
            do {//直到输入正确为止,用do...while使得代码更为简洁
                System.out.println("请输入程序密码：");
                userPass = input.nextLine();    //从控制台获取用户输入的密码
                System.out.println();
                //字符串的equals()方法用于判断两个字符串的值是否相同
            } while (!userPass.equals(PASSWORD));//密码输入不正确，继续循环，重新输入
            //flag = false;
            System.out.println("密码正确，继续执行！");
        //}
    }

    //该方法显示程序主界面，并返回用户输入的功能菜单数
    public static int showMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.print("|");
        System.out.print("\t\t蓝桥java工程师管理系统");
        System.out.println("\t\t|");
        System.out.println("-------------------------------------");
        System.out.println("1. 输入Java工程师资料");
        System.out.println("2. 删除指定Java工程师资料资料");
        System.out.println("3. 查询Java工程师资料");
        System.out.println("4. 修改Java工程师资料");
        System.out.println("5. 计算Java工程师的月薪");
        System.out.println("6. 保存新添加的Java工程师的资料");
        System.out.println("7. 对Java工程师信息排序（1编号排序，2姓名排序）");
        System.out.println("8. 输出所有Java工程师信息");
        System.out.println("9. 清空所有Java工程师资料");
        System.out.println("10. 打印Java工程师数据报表");
        System.out.println("11. 从文件从新导入Java工程师数据");
        System.out.println("0. 结束（编辑工程师信息后提示保存）");
        //Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("请输入您的选择：");
        choose = input.nextInt();   //从控制台获取用户输入的选择
        System.out.println("您的选择是：" + choose);
        return choose;
    }

    //1. 输入Java工程师资料，月工作完成分数和月实际工作天数不在此处输入
    public static void inputEngInf(){//表示Java工程师资料已经输入完毕
        if(status == true){
            System.out.println("Java工程师资料已经输入完毕，可以选择4进行修改");
        }else{
            while (!status) {//如果Java工程师信息输入不完整，则全部重新输入
                System.out.println("请输入Java工程师的编号：");
                engNo = input.nextInt();
                if(engNo <= 0){     //Java工程师编号不能为负值
                    status = false; //Java工程师信息输入不正确
                    System.out.println("Java工程师编号不能为负值！");
                    continue;       //跳出本次循环，执行下一次输入Java工程师
                                    // 循环
                }
                else{
                    status = true;  //表示到目前为止，Java工程师信息输入正确
                }

                System.out.println("请输入Java工程师姓名：");
                engName = input.next();
                if(engName.length() == 0){      //没有输入姓名，姓名的长度为0
                    status = false; //Java工程师信息输入不正确
                    System.out.println("Java工程师姓名不能为空！");
                    continue;       //跳出本次循环，执行下一次输入Java工程师
                                    // 循环
                }else{
                    status = true;  //表示到目前为止，Java工程师信息输入正确
                }

                System.out.println("请输入Java工程师的性别：（1代表男，2代表女）");
                engSex = input.nextInt();
                if(engSex != 1 && engSex != 2){//Java工程师性别既不是1，也不是2
                    status = false;     //Java工程师信息输入不正确
                    System.out.println("性别只能输入1或2");
                    continue;       //跳出本次循环，执行下一次输入Java工程师资料循环
                }else {
                    status = true;  //表示到目前为止，Java工程师信息输入正确
                }

                System.out.println("请输入Java工程师的学历：（1代表大专，2代表本科，" +
                        "3代表硕士，4代表博士，5）");
                engEdu = input.nextInt();
                if(engEdu != 1 && engEdu != 2 && engEdu != 3 && engEdu != 4 && engEdu != 5){
                    //工程师学历不是1、2、3、4、5
                    status = false;     //工程师信息输入不正确
                    System.out.println("学历只能输入1、2、3、4、5——(1代表大专，2代表本科，3代表硕士," +
                                        "4代表博士，5代表其他)!");
                    continue;         //跳出本次循环，执行下一次输入Java工程师资料循环
                }else {
                    status = true;    //表示到目前为止，Java工程师信息输入正确
                }

                System.out.println("请输入Java工程师应扣保险金额：");
                inSurance = input.nextDouble();
                if(inSurance <= 0){     //Java工程师应扣保险金额不能为负值！
                    status = false;     //Java工程师信息输入不正确
                    System.out.println("Java工程师应扣保险金额不能为负值！");
                    continue;           //跳出本次循环，执行下一次输入Java工程师资料循环
                }else {
                    status = true;
                }
            }
        }
    }
    //2. 删除Java工程师资料，实际是把Java工程师相关信息设置为初值
    public static void deleteEngInf(){
        if(status == false){//表示Java工程师资料未输入或者已经被删除
            System.out.println("Java工程师资料未输入或者已经被删除！");
        }else {
            engNo = -1;
            engName = "";
            engSex = -1;
            engEdu = -1;
            baseSalary = 3000;
            comResult = 100;
            workDay = 22;
            inSurance = 3000*0.105;
            engSalary = 0.0;
            status = false; //表示Java工程师资料未输入或者已经被删除
        }
    }

    //3. 查询工程师资料，实际是把Java工程师信息资料逐行输出
    public static void searchEngInf(){
        if(status == false){
            System.out.println("Java工程师资料未输入或已删除！");
        }else{
            System.out.println("Java工程师编号：" + engNo);
            System.out.println("Java工程师姓名：" + engName);
            System.out.println("Java工程师性别：" + (engSex == 1?"男":"女"));
            switch (engEdu){
                case 1:
                    System.out.println("Java工程师学历：大专");
                    break;
                case 2:
                    System.out.println("Java工程师学历：本科");
                    break;
                case 3:
                    System.out.println("Java工程师学历：硕士");
                    break;
                case 4:
                    System.out.println("Java工程师学历：博士");
                    break;
                case 5:
                    System.out.println("Java工程师学历：其他");
                    break;
                default:
                    System.out.println("Java工程师学历输入不正确");
                    break;
            }
            System.out.println("Java工程师底薪：" + baseSalary);
            System.out.println("Java工程师月应扣保险数：" + inSurance);
        }
    }

    //4. 修改Java工程师资料，和输入Java工程师资料功能相似，区别在于需要先输出原信息
    //再让用户输入新修改的信息
    public static void modifyEngInf(){
        if(status == false){
            System.out.println("Java工程师资料未输入或已删除，不能进行修改操作！");
        }else{
            status = false;     //将Java工程师资料是否输入完毕置为否，需要修改
            while (!status) {   //如果Java工程师资料修改不完整，则全部重修修改
                System.out.print("原来Java工程师的编号是：" + engNo + "，请输入修改后的编号：");
                engNo = input.nextInt();
                if(engNo < 0){
                    status = false;     //Java工程师信息输入不正确
                    System.out.println("Java工程师编号不能为负值！");
                    continue;
                }else{
                    status = true;      //表示到目前为止，Java工程师资料输入正确
                }

                System.out.println("原来的Java工程师的名字是：" + engName +",请输入修改后的名字：");
                engName = input.next();
                if(engName.length() == 0){//没有输入姓名，姓名长度为0
                    status = false;
                    System.out.println("Java工程师姓名不能为空！");
                    continue;
                }else{
                    status = true; //表示到目前为止Java工程师信息输入正确
                }

                System.out.println("原来Java工程师的性别是：" + (engSex == 1?"男":"女") + ",请输入" +
                        "修改后的性别（1代表男，2代表女）");
                engSex = input.nextInt();
                if(engSex != 1 && engSex != 2){//Java工程师性别既不是1，也不是2
                    status = false;     //Java工程师信息输入不正确
                    System.out.println("性别只能输入1或2");
                    continue;       //跳出本次循环，执行下一次输入Java工程师资料循环
                }else {
                    status = true;  //表示到目前为止，Java工程师信息输入正确
                }

                switch (engEdu){
                    case 1:
                        System.out.println("原来Java工程师学历：大专");
                        break;
                    case 2:
                        System.out.println("原来Java工程师学历：本科");
                        break;
                    case 3:
                        System.out.println("原来Java工程师学历：硕士");
                        break;
                    case 4:
                        System.out.println("原来Java工程师学历：博士");
                        break;
                    case 5:
                        System.out.println("原来Java工程师学历：其他");
                        break;
                    default:
                        System.out.println("原来Java工程师学历输入不正确");
                        break;
                }
                System.out.print("请输入修改后的学历："+"（1代表大专，2代表本科，3代表硕士，"+
                       " 4代表博士，5代表其他)");
                engEdu = input.nextInt();
                if(engEdu != 1 && engEdu != 2 && engEdu != 3 && engEdu != 4 && engEdu != 5){
                    //工程师学历不是1、2、3、4、5
                    status = false;     //工程师信息输入不正确
                    System.out.println("学历只能输入1、2、3、4、5——(1代表大专，2代表本科，3代表硕士," +
                            "4代表博士，5代表其他)!");
                    continue;         //跳出本次循环，执行下一次输入Java工程师资料循环
                }else {
                    status = true;    //表示到目前为止，Java工程师信息输入正确
                }

                System.out.println("原来Java工程月应扣保险金额是：" + inSurance + ",请输入修改后的底薪：");
                inSurance = input.nextDouble();
                if(inSurance <= 0){     //Java工程师应扣保险金额不能为负值！
                    status = false;     //Java工程师信息输入不正确
                    System.out.println("Java工程师应扣保险金额不能为负值！");
                    continue;           //跳出本次循环，执行下一次输入Java工程师资料循环
                }else {
                    status = true;
                }
            }
        }
    }

    //5. 计算Java工程师的月薪，返回月薪值
    //计算之前需要获取月工作完成分数和月实际工作天数两个数值
    public static void calEngSalary(){     //静态的主程序只能调用静态的方法
       if (status = false){     //表示Java工程师资料未输入或已删除
           System.out.println("Java工程师资料未输入或者已经被删除，不能计算！");
       }else {
           while (true){
               System.out.println("请输入Java工程师月完成分数（最小值为0，最大值为150）：");
               comResult = input.nextInt();     //从控制台获取月工作完成分数，赋值给comResult

               if(comResult < 0 || comResult > 150){//月工作完成分数（最小值为0，最大值为150）
                   System.out.println("输入有误，请重新输入！");
                   continue;        //跳出本次循环，执行下一次循环
               }else {
                   break;
               }
           }
           while (true){
               System.out.println("请输入Java工程师月实际工作天数（最小值为0，最大值为31）：");
               workDay = input.nextDouble();    //从控制台获取月实际工作天数，赋值给
                                                //workDay
               if(workDay <0 || workDay > 31){
                   System.out.println("输入有误，请重新输入！");
                   continue;    //跳出本次循环，执行下一次循环
               }else {
                   break;
               }
           }
           //调用CalEngSalaryValue 计算Java工程师月薪
           //输入底薪、月实际工作天数、月应扣除保险数
           engSalary = calEngSalaryValue(baseSalary,comResult,workDay,inSurance);
           System.out.println("Java工程师" + engName + "月薪为：" + engSalary);
       }
    }

    //本方法为计算Java工程师月薪公式
    public static double calEngSalaryValue(int baseSalary,int comResult,double workDay,double inSurance){
        //Java工程师月薪 = 底薪 + 底薪*25%*月工作完成分数/100+
        //15*月实际工作天数 - 月应扣保险金额
        return baseSalary + baseSalary*0.25*comResult/100 + 15*workDay -inSurance;
    }

    public static void main(String[] args) {
            Login();
            while (true) {
                choose = showMenu();    //调用 showMenu()方法获取用户输入
                switch (choose) {
                    case 1:
                        System.out.println("请输入Java工程师资料");
                        inputEngInf();  //调用方法输入Java工程师资料
                        break;
                    case 2:
                        System.out.println("正删除Java工程师资料...");
                        deleteEngInf(); //调用方法删除 Java 工程师资料
                        break;
                    case 3:
                        System.out.println("正在为您查询Java工程师资料...");
                        searchEngInf(); //调用方法查询 Java 工程师资料
                        break;
                    case 4:
                        System.out.println("修改Java工程师资料");
                        modifyEngInf(); //调用方法修改Java工程师资料
                        break;
                    case 5:
                        //调用方法计算Java工程师薪水，计算前需要获取月工作完成分数数
                        //和月实际工作天数两个数值
                        calEngSalary();
                        break;
                    case 6:
                        System.out.println("本模块功能未实现");
                        break;
                    case 7:
                        System.out.println("本模块功能未实现");
                        break;
                    case 8:
                        System.out.println("本模块功能未实现");
                        break;
                    case 9:
                        System.out.println("本模块功能未实现");
                        break;
                    case 10:
                        System.out.println("本模块功能未实现");
                        break;
                    case 11:
                        System.out.println("本模块功能未实现");
                        break;
                    case 0:
                        System.out.println("程序结束！感谢您的使用");
                        break;
                    default:
                        System.out.println("您的输入有误，请重新输入！");
                        break;
                }
                if (choose == 0) {    //当用户输入0时，退出while循环，结束程序
                    break;
                }
            }

    }
}
