package hotel.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManagerInfo extends JFrame implements ActionListener {
    JTable table;
    JButton back;
    ManagerInfo(){
        setSize(1000,600);
        setLocation(300,200);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1=new JLabel("Name");
        l1.setBounds(40,10,100,20);
        add(l1);

        JLabel l2=new JLabel("Age");
        l2.setBounds(170,10,100,20);
        add(l2);

        JLabel l3=new JLabel("Gender");
        l3.setBounds(290,10,100,20);
        add(l3);

        JLabel l4=new JLabel("Job");
        l4.setBounds(400,10,100,20);
        add(l4);

        JLabel l5=new JLabel("Salary");
        l5.setBounds(540,10,100,20);
        add(l5);

        JLabel l6=new JLabel("Phone");
        l6.setBounds(670,10,100,20);
        add(l6);

        JLabel l7=new JLabel("Email");
        l7.setBounds(790,10,100,20);
        add(l7);

        JLabel l8=new JLabel("Aadhaar");
        l8.setBounds(910,10,100,20);
        add(l8);

        table=new JTable();
        table.setBounds(0,40,1000,400);
        add(table);

        try{
            Conn conn=new Conn();
            String query="select * from employee where job='Manager';";
            ResultSet rs=conn.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }

        back=new JButton("Back");
        back.setBounds(420,500,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Reception();
    }

    public static void main(String[] args) {
        new ManagerInfo();
    }


}
