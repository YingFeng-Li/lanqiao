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
        textField1 = new JTextField("lyf");//�û���
        label2 = new JLabel();
        textField2 = new JTextField("lyf1234");//����
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
        textField1.setBounds(110, 85, 115, textField1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u5bc6\u7801\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(50, 120), label2.getPreferredSize()));
        contentPane.add(textField2);
        textField2.setBounds(115, 120, 110, textField2.getPreferredSize().height);

        //---- ��¼��ť ----
        button1.setText("\u767b\u5f55");
        contentPane.add(button1);
        button1.setBounds(80, 190, 60, button1.getPreferredSize().height);
        /*
        ����¼��ť��Ӽ����¼�
         */
        button1.addActionListener(
                //�����ڲ��ࣺ�ֲ��ڲ���
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //�õ��û���������
                        String username=textField1.getText();//��ȡ�û���
                        String password=textField2.getText();//��ȡ����

                        Connection conn=null;
                        String url="jdbc:oracle:thin:@47.113.217.47:1521:orcl";
                        Statement stmt=null;//SQL������ƴSQL
                        String sql="SELECT password FROM users WHERE username='"+username+"'";//ֻ��1��
                        System.out.println("����ִ�е�sql��"+sql);
                        ResultSet rs=null;
                        try {
                            Class.forName("oracle.jdbc.driver.OracleDriver");//
                            conn= DriverManager.getConnection(url,"scott","tiger");
                            stmt=conn.createStatement();
                            rs=stmt.executeQuery(sql);
                            rs.next();
                            String encodePassword=rs.getString(1);
                            if(MD5.checkpassword(password,encodePassword)){
                                System.out.println("��¼�ɹ�");
                                setVisible(false);

                                MainForm mf=new MainForm();
                                mf.setVisible(true);
                                //���ص�¼���ڣ���ʾMainForm���ڣ��в˵���
                            }else{
                                System.out.println("��¼ʧ��");
                            }

                        } catch (ClassNotFoundException ee) {
                            ee.printStackTrace();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (NoSuchAlgorithmException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedEncodingException ex) {
                            ex.printStackTrace();
                        } finally {
                            //�ͷ���Դ�����ݿ����Ӻܰ���
                            try {
                                rs.close();
                                stmt.close();
                                conn.close();//������
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                        }
                    }
                }
        );

        //---- �˳���ť ----
        button2.setText("\u9000\u51fa");
        contentPane.add(button2);
        button2.setBounds(190, 195, 85, button2.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
