import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Thu Apr 22 21:04:26 CST 2021
 */



/**
 * @author 1
 */
public class LoginForm extends JFrame {
    public static void main(String[] args) {
        new LoginForm();
    }
    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();

        //账号密码
        textField1 = new JTextField("lyf");
        passwordField1 = new JPasswordField("lyf1234");

        label2 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(50, 90), label1.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(110, 85, 115, 30);

        //---- label2 ----
        label2.setText("\u5bc6\u7801\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(50, 120), label2.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u767b\u5f55");
        contentPane.add(button1);
        button1.setBounds(80, 190, 60, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("\u9000\u51fa");
        contentPane.add(button2);
        button2.setBounds(190, 190, 85, button2.getPreferredSize().height);
        contentPane.add(passwordField1);
        passwordField1.setBounds(110, 120, 115, 30);

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        //点击登录
        setVisible(true);//将当前窗口显示为真，显示窗口
        button1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String username = textField1.getText();
                        String password = passwordField1.getText();

                        String sql = "SELECT password FROM users WHERE username='" + username + "'";
                        Connection conn = null;
                        String url = "jdbc:oracle:thin:@47.113.217.47:1521:orcl";
                        ResultSet rs = null;
                        Statement stmt = null;
                        try {
                            Class.forName("oracle.jdbc.driver.OracleDriver");//
                            conn = DriverManager.getConnection(url, "scott", "tiger");
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(sql);
                            rs.next();
                            String encodePassword = rs.getString(1);//从数据库取出的加密后的密码
                            boolean isSuccess = MD5.checkpassword(password, encodePassword);
                            if (isSuccess) {
                                System.out.println("登录成功");
                                MainForm mf = new MainForm();
                                mf.setVisible(true);
                                setVisible(false);//隐藏当前登录窗口
                            } else {
                                System.out.println("登录失败");
                            }

                        } catch (ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ee) {
                            ee.printStackTrace();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } finally {
                            //释放资源：数据库连接很昂贵
                            try {
                                rs.close();
                                stmt.close();
                                conn.close();//关连接
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                        }
                    }
                }
        );
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JButton button1;
    private JButton button2;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
