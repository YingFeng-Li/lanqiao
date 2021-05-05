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
        JMenu mHero = new JMenu("�û�����");
        JMenu mItem = new JMenu("��Ʒ����");
        JMenu order = new JMenu("��������");
        JMenu profit = new JMenu("����ͳ��");
        JMenu stock = new JMenu("��������");
        JMenu supply = new JMenu("��Ӧ����");
        JMenu discount = new JMenu("�����Ż�");
        // �˵���
        mHero.add(new JMenuItem("����û�"));
        //supply.add(new JMenuItem("�鿴��Ӧ��"));

        JMenuItem viewUserMenuItem=new JMenuItem("�鿴��Ӧ��");
        viewUserMenuItem.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("��������");
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
        mHero.add(new JMenuItem("�޸�����"));

        mItem.add(new JMenuItem("�����Ʒ"));
        mItem.add(new JMenuItem("�鿴���"));

        // �ָ���
        mHero.addSeparator();
        mHero.add(new JMenuItem("�˳�"));
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
        button1 = new JButton();//ˢ��
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
        button2 = new JButton();//����

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u4f9b\u5e94\u5546\u4fe1\u606f");//��Ӧ����Ϣ
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
        label2.setText("FTG���ӵ���");
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

    //�鿴�û�����
    public Object[][] queryData() {

        java.util.List<Supplier> list=new ArrayList<Supplier>();
        Connection conn = null;
        String url = "jdbc:oracle:thin:@47.113.217.47:1521:orcl";
        Statement stmt = null;//SQL������ƴSQL
        String sql = "SELECT * FROM supplier";
        System.out.println("����ִ�е�sql��" + sql);
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//
            conn = DriverManager.getConnection(url, "lyfsql", "lyfsql1234");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //ÿѭ��һ�ξ���һ�����󣬰�����������������List��������ظ�����Set�����򲻿��ظ�����Map��key��value�ṹ��
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
            //�ͷ���Դ�����ݿ����Ӻܰ���
            try {
                rs.close();
                stmt.close();
                conn.close();//������
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        data = new Object[list.size()][head.length];
        //�Ѽ���������ݷ���Obejct�����ά����
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
