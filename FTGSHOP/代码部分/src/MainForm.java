import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JFrame;

import javax.swing.JMenu;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Tue Apr 27 20:11:26 CST 2021
 */


/**
 * @author 1
 */
public class MainForm extends JFrame {
    public MainForm() {
        initComponents();
    }
    public Object[][] queryData() {

        java.util.List<Supplier> list=new ArrayList<Supplier>();
        Connection conn = null;
        String url = "jdbc:oracle:thin:@47.113.217.47:1521:orcl";
        Statement stmt = null;//SQL语句对象，拼SQL
        String sql = "SELECT * FROM supplier";
        System.out.println("即将执行的sql：" + sql);
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//
            conn = DriverManager.getConnection(url, "lyfsql", "lyfsql1234");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //每循环一次就是一个对象，把这个对象放入容器（List（有序可重复）、Set（无序不可重复）、Map（key、value结构）
                Supplier supplier = new Supplier();
                supplier.setSid(rs.getInt("SID"));
                supplier.setSname(rs.getString("SNAME"));
                supplier.setSaddr(rs.getString("SADDR"));
                supplier.setSphone(rs.getString("SPHONE"));
                supplier.setGoods(rs.getString("GOODS"));
                list.add(supplier);
            }
        } catch (ClassNotFoundException ee) {
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
        data = new Object[list.size()][head.length];
        //把集合里的数据放入Obejct这个二维数组
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < head.length; j++) {
                data[i][0] = list.get(i).getSid();
                data[i][1] = list.get(i).getSname();
                data[i][2] = list.get(i).getSaddr();
                data[i][3] = list.get(i).getSphone();
                data[i][4] = list.get(i).getGoods();
            }
        }
        return data;
    }

    private void initComponents() {

        JMenuBar mb = new JMenuBar();
        JMenu mHero = new JMenu("用户管理");
        JMenu mItem = new JMenu("商品管理");
        JMenu order = new JMenu("订单管理");
        JMenu profit = new JMenu("利润统计");
        JMenu stock = new JMenu("进货出货");
        JMenu supply = new JMenu("供应管理");
        JMenu discount = new JMenu("打折优惠");
        // 菜单项
        mHero.add(new JMenuItem("添加用户"));
        //supply.add(new JMenuItem("查看供应商"));

        JMenuItem viewUserMenuItem=new JMenuItem("查看供应商");
        viewUserMenuItem.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("鼠标点了我");
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        //label1.setVisible(true);
                        scrollPane1.setVisible(true);

                        label2.setVisible(false);
                        button2.setVisible(true);

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                }
        );
        supply.add(viewUserMenuItem);
        mHero.add(new JMenuItem("修改密码"));

        mItem.add(new JMenuItem("添加商品"));
        mItem.add(new JMenuItem("查看库存"));

        // 分隔符
        mHero.addSeparator();
        mHero.add(new JMenuItem("退出"));
        mb.add(mHero);
        mb.add(mItem);
        mb.add(order);
        mb.add(profit);
        mb.add(stock);
        mb.add(profit);
        mb.add(supply);
        mb.add(discount);


        this.setJMenuBar(mb);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button5 = new JButton();
        panel3 = new JPanel();
        label2 = new JLabel();
        panel1 = new JPanel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        label8 = new JLabel();
        textField6 = new JTextField();
        label9 = new JLabel();
        textField7 = new JTextField();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(135, 205, 640, 185);

        //======== panel2 ========
        {
            panel2.setLayout(null);

            //---- button1 ----
            button1.setText("\u5237\u65b0");
            panel2.add(button1);
            button1.setBounds(new Rectangle(new Point(55, 10), button1.getPreferredSize()));

            //---- button2 ----
            button2.setText("\u589e\u52a0");
            panel2.add(button2);
            button2.setBounds(new Rectangle(new Point(165, 10), button2.getPreferredSize()));

            //---- button5 ----
            button5.setText("\u5220\u9664");
            panel2.add(button5);
            button5.setBounds(new Rectangle(new Point(275, 10), button5.getPreferredSize()));
        }
        contentPane.add(panel2);
        panel2.setBounds(220, 420, 420, 50);

        //======== panel3 ========
        {
            panel3.setLayout(null);

            //---- label2 ----
            label2.setText("\u6842\u7535\u7535\u5b50\u5c0f\u5356\u94fa");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 49f));
            panel3.add(label2);
            label2.setBounds(185, 55, 485, 155);
        }
        contentPane.add(panel3);
        panel3.setBounds(55, 20, 630, 185);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label5 ----
            label5.setText("sid");
            panel1.add(label5);
            label5.setBounds(new Rectangle(new Point(55, 25), label5.getPreferredSize()));

            //---- label6 ----
            label6.setText("sname");
            panel1.add(label6);
            label6.setBounds(new Rectangle(new Point(50, 65), label6.getPreferredSize()));

            //---- label7 ----
            label7.setText("saddr");
            panel1.add(label7);
            label7.setBounds(new Rectangle(new Point(50, 100), label7.getPreferredSize()));
            panel1.add(textField3);
            textField3.setBounds(105, 15, 215, 25);
            panel1.add(textField4);
            textField4.setBounds(105, 55, 210, 25);
            panel1.add(textField5);
            textField5.setBounds(110, 95, 210, 25);

            //---- label8 ----
            label8.setText("sphone");
            panel1.add(label8);
            label8.setBounds(new Rectangle(new Point(45, 140), label8.getPreferredSize()));
            panel1.add(textField6);
            textField6.setBounds(110, 135, 210, 30);

            //---- label9 ----
            label9.setText("goods");
            panel1.add(label9);
            label9.setBounds(new Rectangle(new Point(45, 185), label9.getPreferredSize()));
            panel1.add(textField7);
            textField7.setBounds(115, 185, 200, 30);

            //---- button3 ----
            button3.setText("\u4fdd\u5b58");
            panel1.add(button3);
            button3.setBounds(new Rectangle(new Point(220, 240), button3.getPreferredSize()));

            //---- button4 ----
            button4.setText("\u9000\u51fa");
            panel1.add(button4);
            button4.setBounds(new Rectangle(new Point(320, 240), button4.getPreferredSize()));
        }
        contentPane.add(panel1);
        panel1.setBounds(615, 5, 650, 315);

        contentPane.setPreferredSize(new Dimension(1275, 620));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        //---------------way------------------
        //button1.setVisible(false);
        panel1.setVisible(false);
        scrollPane1.setVisible(false);
        button1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DefaultTableModel tableModel = new DefaultTableModel(queryData(), head) {
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        table1.setModel(tableModel);
                    }
                }
        );
        button2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel1.setVisible(true);
                        panel3.setVisible(false);
                        scrollPane1.setVisible(false);
                    }
                }
        );
        button3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BIg add = new BIg();
                        String id = textField3.getText();
                        String name = textField4.getText();
                        String addr = textField5.getText();
                        String phone = textField6.getText();
                        String goods = textField7.getText();

                        add.addData("2",id,name,phone,goods);
                    }
                }
        );
        button4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel1.setVisible(false);
                        table1.setVisible(true);
                    }
                }
        );
        button5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BIg del = new BIg();
                        int count=table1.getSelectedRow();//获取你选中的行号（记录）
                        String id= table1.getValueAt(count, 0).toString();//读取你获取行号的某一列的值（也就是字段）
                        del.DeleteDate(id);
                    }
                }
        );

    }

    //查看用户数据


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel2;
    private JButton button1;
    private JButton button2;
    private JButton button5;
    private JPanel panel3;
    private JLabel label2;
    private JPanel panel1;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel label8;
    private JTextField textField6;
    private JLabel label9;
    private JTextField textField7;
    private JButton button3;
    private JButton button4;
    private Object[][] data = null;
    private String head[] = {"sid", "sname", "saddr","sphone","goods"};
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
