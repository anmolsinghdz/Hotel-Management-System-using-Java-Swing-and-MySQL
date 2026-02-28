package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Checkout extends JFrame implements ActionListener {

    Choice ccustomer;
    JLabel lblroomnumber, lblcheckintime, lblcheckouttime;
    JButton checkout, back;
    Checkout() {
        setSize(800,400);
        setLocation(300,200);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Conn conn = new Conn();

        JLabel text=new JLabel("Checkout");
        text.setBounds(100,20,100,30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(text);

        JLabel lblid=new JLabel("Customer Id");
        lblid.setBounds(30,80,100,30);
        add(lblid);

        ccustomer=new Choice();
        ccustomer.setBounds(150,80,150,25);
        add(ccustomer);


        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2=i1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel i4=new JLabel(i3);
        i4.setBounds(310,80,20,20);
        add(i4);

        JLabel lblroom=new JLabel("Room Number");
        lblroom.setBounds(30,130,100,30);
        add(lblroom);

        lblroomnumber=new JLabel();
        lblroomnumber.setBounds(150,130,100,30);
        add(lblroomnumber);

        JLabel lblcheckin=new JLabel("Checkin Time");
        lblcheckin.setBounds(30,180,100,30);
        add(lblcheckin);

        lblcheckintime=new JLabel();
        lblcheckintime.setBounds(150,180,150,30);
        add(lblcheckintime);

        JLabel lblcheckout=new JLabel("Checkout Time");
        lblcheckout.setBounds(30,230,100,30);
        add(lblcheckout);

        Date date=new Date();
        lblcheckouttime=new JLabel(""+date);
        lblcheckouttime.setBounds(150,230,150,30);
        add(lblcheckouttime);

        checkout=new JButton("Checkout");
        checkout.setBounds(30,280,120,30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(this);
        add(checkout);

        back=new JButton("Back");
        back.setBounds(170,280,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        try{
            String query="select number from customer;";
            ResultSet rs=conn.statement.executeQuery(query);
            while(rs.next()){
                ccustomer.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ccustomer.getItemCount()>0){
            loadCustomerDetails(ccustomer.getItem(0));
        }

        ccustomer.addItemListener(e->{
            if(e.getStateChange()== ItemEvent.SELECTED){
                String number=ccustomer.getSelectedItem();
                loadCustomerDetails(number);
            }
        });

        ImageIcon i5=new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i6=i5.getImage().getScaledInstance(400,250,Image.SCALE_DEFAULT);
        ImageIcon i7=new ImageIcon(i6);
        JLabel image=new JLabel(i7);
        image.setBounds(350,50,400,250);
        add(image);

        setVisible(true);
    }

    private void loadCustomerDetails(String number) {
        Conn conn=new Conn();
        String query="select room, checkintime from customer where number='"+number+"';";
        try {
            ResultSet rs=conn.statement.executeQuery(query);
            if(rs.next()){
                lblroomnumber.setText(rs.getString("room"));
                lblcheckintime.setText(rs.getString("checkintime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==checkout){
            Conn conn=new Conn();
            String number=ccustomer.getSelectedItem();
            String query1="delete from customer where number='"+number+"';";
            String query2="update room set availability='Available' where roomnumber='"+lblroomnumber.getText()+"';";
            try {
                conn.statement.executeUpdate(query1);
                conn.statement.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Checkout Successful");
                setVisible(false);
                new Reception();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) throws SQLException {
        new Checkout();
    }


}
