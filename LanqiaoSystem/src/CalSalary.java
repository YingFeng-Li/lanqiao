import java.util.Scanner;
public class CalSalary {
    public static void main(String[] args) {
        double engSalary = 0.0;     //Java工程师月薪
        int baseSalary = 3000;      //底薪
        int comResult = 100;        //月工作完成分数（最小为0，最大为150）
        double workDay = 22;        //月实际工作天数
        double inSurance = 3000 * 0.105;//月应扣除保险数

        Scanner input = new Scanner(System.in);     //从控制台获取输入的对象
        System.out.print("请输入Java工程师底薪：");
        baseSalary = input.nextInt();   //从控制台获取输入——底薪，赋值给baseSalary
        System.out.print("请输入Java工程师月完成分数（最小值为0，最大值为150）：");
        comResult = input.nextInt();    //从控制台获取输入——月工作完成分数，赋值给comResult
        System.out.print("请输入Java工程师月工作天数：");
        workDay = input.nextDouble();   //从控制台获取输入——月实际工作天数，赋值给workDa
        System.out.print("请输入Java工程师月应扣除保险数：");
        inSurance = input.nextDouble(); //从控制台获取输入——月应扣除保险数，赋值给inSurance

        //Java工程师月薪 = 底薪 + 底薪 * 25% * 月工作完成分数/100 + 15 * 月实际工作天数 -
        //月应扣除保险数
        engSalary = baseSalary + baseSalary * 0.25 * comResult/100 + 15 * workDay - inSurance;
        System.out.println("Java工程师月薪为：" + engSalary);
    }
}
