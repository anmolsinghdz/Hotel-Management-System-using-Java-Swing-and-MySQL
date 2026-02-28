package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRooms extends JFrame implements ActionListener {
    JTextField tfroom, tfprice;
    JButton add, cancel;
    JComboBox<String> availablecombo, cleancombo, typecombo;
    AddRooms(){
        setSize(940,470);
        setLocation(330,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);


        JLabel heading=new JLabel("Add Rooms");
        heading.setBounds(150,20,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,18));
        add(heading);

        JLabel lblroomno=new JLabel("Room Number");
        lblroomno.setBounds(60,80,120,30);
        lblroomno.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblroomno);

        tfroom=new JTextField();
        tfroom.setBounds(200,80,150,30);
        add(tfroom);

        JLabel lblavailable=new JLabel("Available");
        lblavailable.setBounds(60,130,120,30);
        lblavailable.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblavailable);

        String availableOptions[]={"Available","Occupied"};
        availablecombo=new JComboBox<>(availableOptions);
        availablecombo.setBounds(200,130,150,30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);

        JLabel lblclean=new JLabel("Cleaning Status");
        lblclean.setBounds(60,180,120,30);
        lblclean.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblclean);

        String cleanOptions[]={"Cleaned","Dirty"};
        cleancombo=new JComboBox<>(cleanOptions);
        cleancombo.setBounds(200,180,150,30);
        cleancombo.setBackground(Color.WHITE);
        add(cleancombo);

        JLabel lblprice=new JLabel("Price");
        lblprice.setBounds(60,230,120,30);
        lblprice.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lblprice);

        tfprice=new JTextField();
        tfprice.setBounds(200,230,150,30);
        add(tfprice);

        JLabel lbltype=new JLabel("Bed Type");
        lbltype.setBounds(60,280,120,30);
        lbltype.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(lbltype);

        String typeOptions[]={"Single Bed","Double Bed"};
        typecombo=new JComboBox<>(typeOptions);
        typecombo.setBounds(200,280,150,30);
        typecombo.setBackground(Color.WHITE);
        add(typecombo);

        add=new JButton("Add Rooms");
        add.setBounds(60,350,130,30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        cancel=new JButton("Cancel");
        cancel.setBounds(220,350,130,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        JLabel image=new JLabel(i1);
        image.setBounds(400,30,500,300);
        add(image);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==add){
            String roomnumber=tfroom.getText();
            String availability=availablecombo.getSelectedItem().toString();
            String cleanstatus=cleancombo.getSelectedItem().toString();
            String price=tfprice.getText();
            String bedtype=typecombo.getSelectedItem().toString();

            try{
                Conn conn=new Conn();
                String query="insert into room values('"+roomnumber+"','"+availability+"','"+cleanstatus+"','"+price+"','"+bedtype+"');";
                int i=conn.statement.executeUpdate(query);
                if(i>0){
                    JOptionPane.showMessageDialog(this,"Room Added Successfully");
                }
                else{
                    JOptionPane.showMessageDialog(this,"Room Not Added Successfully");
                }
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddRooms();
    }


}
