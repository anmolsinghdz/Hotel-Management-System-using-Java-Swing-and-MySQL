package hotel.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerInfo extends JFrame implements ActionListener {
    JTable table;
    JButton back;
    CustomerInfo(){
        setSize(1000,600);
        setLocation(300,200);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1=new JLabel("Document Type");
        l1.setBounds(10,10,100,20);
        add(l1);

        JLabel l2=new JLabel("Number");
        l2.setBounds(160,10,100,20);
        add(l2);

        JLabel l3=new JLabel("Name");
        l3.setBounds(290,10,100,20);
        add(l3);

        JLabel l4=new JLabel("Gender");
        l4.setBounds(410,10,100,20);
        add(l4);

        JLabel l5=new JLabel("Country");
        l5.setBounds(540,10,100,20);
        add(l5);

        JLabel l6=new JLabel("Room Number");
        l6.setBounds(640,10,100,20);
        add(l6);

        JLabel l7=new JLabel("Checkin Time");
        l7.setBounds(760,10,100,20);
        add(l7);

        JLabel l8=new JLabel("Deposit");
        l8.setBounds(900,10,100,20);
        add(l8);

        table=new JTable();
        table.setBounds(0,40,1000,400);
        add(table);

        try{
            Conn conn=new Conn();
            String query="select * from customer;";
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
        new CustomerInfo();
    }


}
