package hotel.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame implements ActionListener {
    JTable table;
    JButton back;
    Department(){
        setSize(700,480);
        setLocation(400,200);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel l1=new JLabel("Department");
        l1.setBounds(180,10,100,20);
        add(l1);

        JLabel l2=new JLabel("Budget");
        l2.setBounds(420,10,100,20);
        add(l2);


        table=new JTable();
        table.setBounds(0,50,700,350);
        add(table);

        try{
            Conn conn=new Conn();
            String query="select * from department;";
            ResultSet rs=conn.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }

        back=new JButton("Back");
        back.setBounds(280,400,120,30);
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
        new Department();
    }


}
