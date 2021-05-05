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
                        label1.setVisible(true);
                        scrollPane1.setVisible(true);
                        label2.setVisible(false);
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
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(queryData(), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);
        label2 = new JLabel();
        button1 = new JButton();//刷新
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
        button2 = new JButton();//增加

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u4f9b\u5e94\u5546\u4fe1\u606f");//供应商信息
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 23f));
        contentPane.add(label1);
        label1.setBounds(350, 10, 155, 70);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(135, 205, 640, 185);

        //---- label2 ----
        label2.setText("FTG电子店铺");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 49f));
        contentPane.add(label2);
        label2.setBounds(210, 80, 485, 155);

        label1.setVisible(false);
        scrollPane1.setVisible(false);
        label2.setVisible(true);
        //---- button1 ----
        button1.setText("\u5237\u65b0");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(370, 435), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u589e\u52a0");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(445, 435), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(945, 520));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    //查看用户数据
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label2;
    private JButton button1;
    private JButton button2;
    private Object[][] data = null;
    private String head[] = {"sid", "sname", "saddr","sphone","goods"};
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
