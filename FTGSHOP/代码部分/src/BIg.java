import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName BIg
 * @Description: TODO
 * @Author YingFengli
 * @Date 2021/5/7/007
 * @Version V1.0
 **/
public class BIg {
    public void addData(String sid, String sname, String saddr, String sphone, String goods) {
        System.out.println("你正在添加供应商信息！");
        Connection conn = null;
        String url = "jdbc:oracle:thin:@47.113.217.47:1521:orcl";
        PreparedStatement pstmt = null;//SQL语句对象
        String sql = "INSERT INTO supplier VALUES(?,?,?,?,?)";//占位符
        System.out.println("即将执行的sql：" + sql);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//
            conn = DriverManager.getConnection(url, "lyfsql", "lyfsql1234");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(sid));
            pstmt.setString(2, sname);
            pstmt.setString(3, saddr);
            pstmt.setString(4, sphone);
            pstmt.setString(5, goods);
            pstmt.executeUpdate();//添加数据
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源：数据库连接很昂贵
            try {
                pstmt.close();
                conn.close();//关连接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
    void DeleteDate(String id){
        Connection conn = null;
        String url = "jdbc:oracle:thin:@47.113.217.47:1521:orcl";
        PreparedStatement pstmt = null;//SQL语句对象
        String sql = "DELETE FROM supplier WHERE sid=?";//占位符
        System.out.println("即将执行的sql：" + sql);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//
            conn = DriverManager.getConnection(url, "lyfsql", "lyfsql1234");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();//删除数据
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源：数据库连接很昂贵
            try {
                pstmt.close();
                conn.close();//关连接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

}
