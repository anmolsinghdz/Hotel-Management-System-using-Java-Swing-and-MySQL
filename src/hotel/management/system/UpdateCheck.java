package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateCheck extends JFrame implements ActionListener {
    Choice ccustomer;
    JTextField tfroom, tfname, tfcheckin, tfpaid, tfpending;
    JButton check, update, back;
    UpdateCheck() {
        setSize(980,500);
        setLocation(300,200);
        getContentPane().setBackground(Color.WHITE);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel text=new JLabel("Update Status");
        text.setBounds(90,20,200,30);
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        text.setForeground(Color.BLUE);
        add(text);

        JLabel lblid=new JLabel("Customer Id");
        lblid.setBounds(30,80,100,20);
        add(lblid);

        ccustomer=new Choice();
        ccustomer.setBounds(200,80,150,25);
        add(ccustomer);

        try{
            Conn conn=new Conn();
            String query="select * from customer;";
            ResultSet rs=conn.statement.executeQuery(query);
            while(rs.next()){
                ccustomer.add(rs.getString("number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel lblroom=new JLabel("Room");
        lblroom.setBounds(30,120,100,20);
        add(lblroom);

        tfroom=new JTextField();
        tfroom.setBounds(200,120,150,25);
        add(tfroom);

        JLabel lblname=new JLabel("Name");
        lblname.setBounds(30,160,100,20);
        add(lblname);

        tfname=new JTextField();
        tfname.setBounds(200,160,150,25);
        add(tfname);

        JLabel lblcheckin=new JLabel("Checkin Time");
        lblcheckin.setBounds(30,200,100,20);
        add(lblcheckin);

        tfcheckin=new JTextField();
        tfcheckin.setBounds(200,200,150,25);
        add(tfcheckin);

        JLabel lblpaid=new JLabel("Amount Paid");
        lblpaid.setBounds(30,240,100,20);
        add(lblpaid);

        tfpaid=new JTextField();
        tfpaid.setBounds(200,240,150,25);
        add(tfpaid);

        JLabel lblpending=new JLabel("Pending Amount");
        lblpending.setBounds(30,280,100,20);
        add(lblpending);

        tfpending=new JTextField();
        tfpending.setBounds(200,280,150,25);
        add(tfpending);

        check=new JButton("Check");
        check.setBounds(30,340,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update=new JButton("Update");
        update.setBounds(150,340,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back=new JButton("Back");
        back.setBounds(270,340,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel i2=new JLabel(i1);
        i2.setBounds(400,50,500,300);
        add(i2);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Conn conn=new Conn();
        if(ae.getSource()==check){
            String number=ccustomer.getSelectedItem().toString();
            String query1="select * from customer where number='"+number+"';";
            try {
                ResultSet rs=conn.statement.executeQuery(query1);
                while(rs.next()){
                    tfroom.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkintime"));
                    tfpaid.setText(rs.getString("deposit"));
                }
                String query2="select * from room where roomnumber='"+tfroom.getText()+"'";
                ResultSet rs1=conn.statement.executeQuery(query2);
                while(rs1.next()){
                    String price=rs1.getString("price");
                    int remaining=Integer.parseInt(price)-Integer.parseInt(tfpaid.getText());
                    tfpending.setText(String.valueOf(remaining));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==update){
            String number=ccustomer.getSelectedItem();
            String room=tfroom.getText();
            String name=tfname.getText();
            String checkintime=tfcheckin.getText();
            String deposit=tfpaid.getText();
            try{
                String query="update customer set room='"+room+"', name='"+name+"', checkintime='"+checkintime+"', deposit='"+deposit+"' where number='"+number+"';";
                conn.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(this,"Data Updated Successfully");
                setVisible(false);
                new Reception();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
