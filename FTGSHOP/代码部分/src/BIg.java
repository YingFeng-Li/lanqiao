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
        System.out.println("��������ӹ�Ӧ����Ϣ��");
        Connection conn = null;
        String url = "jdbc:oracle:thin:@47.113.217.47:1521:orcl";
        PreparedStatement pstmt = null;//SQL������
        String sql = "INSERT INTO supplier VALUES(?,?,?,?,?)";//ռλ��
        System.out.println("����ִ�е�sql��" + sql);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//
            conn = DriverManager.getConnection(url, "lyfsql", "lyfsql1234");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(sid));
            pstmt.setString(2, sname);
            pstmt.setString(3, saddr);
            pstmt.setString(4, sphone);
            pstmt.setString(5, goods);
            pstmt.executeUpdate();//�������
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //�ͷ���Դ�����ݿ����Ӻܰ���
            try {
                pstmt.close();
                conn.close();//������
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
    void DeleteDate(String id){
        Connection conn = null;
        String url = "jdbc:oracle:thin:@47.113.217.47:1521:orcl";
        PreparedStatement pstmt = null;//SQL������
        String sql = "DELETE FROM supplier WHERE sid=?";//ռλ��
        System.out.println("����ִ�е�sql��" + sql);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//
            conn = DriverManager.getConnection(url, "lyfsql", "lyfsql1234");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();//ɾ������
        } catch (ClassNotFoundException ee) {
            ee.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //�ͷ���Դ�����ݿ����Ӻܰ���
            try {
                pstmt.close();
                conn.close();//������
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

}
