package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateRoom extends JFrame implements ActionListener {
    Choice ccustomer;
    JTextField tfroom;
    JButton check, update, back;
    JComboBox<String> tfavailable, tfstatus;
    UpdateRoom() {
        setSize(980,450);
        setLocation(300,200);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel text=new JLabel("Update Room Status");
        text.setBounds(30,20,250,30);
        text.setFont(new Font("Tahoma",Font.PLAIN,25));
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

        JLabel lblroom=new JLabel("Room Number");
        lblroom.setBounds(30,130,100,20);
        add(lblroom);

        tfroom=new JTextField();
        tfroom.setBounds(200,130,150,25);
        add(tfroom);

        JLabel lblavailable=new JLabel("Availability");
        lblavailable.setBounds(30,180,100,20);
        add(lblavailable);

        String optionAvailable[]={"Available","Occupied"};
        tfavailable=new JComboBox<>(optionAvailable);
        tfavailable.setBounds(200,180,150,25);
        tfavailable.setBackground(Color.WHITE);
        add(tfavailable);

        JLabel lblstatus=new JLabel("Cleaning Status");
        lblstatus.setBounds(30,230,100,20);
        add(lblstatus);


        String cleaning_status[]={"Cleaned","Dirty"};
        tfstatus=new JComboBox<>(cleaning_status);
        tfstatus.setBounds(200,230,150,25);
        tfstatus.setBackground(Color.WHITE);
        add(tfstatus);


        check=new JButton("Check");
        check.setBounds(30,300,100,30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update=new JButton("Update");
        update.setBounds(150,300,100,30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back=new JButton("Back");
        back.setBounds(270,300,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2=i1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,50,500,300);
        add(image);

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
                }
                String query2="select * from room where roomnumber='"+tfroom.getText()+"'";
                ResultSet rs1=conn.statement.executeQuery(query2);
                while(rs1.next()){
                    tfavailable.setSelectedItem(rs1.getString("availability"));
                    tfstatus.setSelectedItem(rs1.getString("cleaning_status"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(ae.getSource()==update){
            String number=ccustomer.getSelectedItem();
            String room=tfroom.getText();
            String available=tfavailable.getSelectedItem().toString();
            String status=tfstatus.getSelectedItem().toString();
            try{
                String query="update room set availability='"+available+"', cleaning_status='"+status+"' where roomnumber='"+room+"';";
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
        new UpdateRoom();
    }
}
