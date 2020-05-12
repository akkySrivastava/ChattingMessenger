/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author akky
 */
public class server extends JFrame implements ActionListener {
    JLabel l,l1,l2,l3,l4,l5,l6;
    JButton btn;
    JTextField t;
    static JTextArea t1;
    JPanel p1;
    static Socket skt;
    static ServerSocket s;
    static DataInputStream din;
    static DataOutputStream dout;
    server()
    {
        getContentPane().setBackground(new Color(2,255,72));
        setLayout(null);
        setLocation(400,200);
        setSize(435,550);
        
        p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(255,255,255));
        p1.setBounds(0,0,450,70);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("messenger/icon/back.jpg"));
        Image I=i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i2=new ImageIcon(I);
        l=new JLabel(i2);
        l.setBounds(2,10,30,30);
        l.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m)
                {
                    dispose();
                }
        });
        p1.add(l);
        
        l5=new JLabel("ALIEN");      
        l5.setFont(new Font("serif",Font.BOLD,18));
        l5.setForeground(Color.blue);
        l5.setBounds(50,10,150,20);
        p1.add(l5);
        
        l6=new JLabel("Online");
        l6.setFont(new Font("Serif",Font.BOLD,12));
        l6.setForeground(Color.green);
        l6.setBounds(50,35,150,30);
        p1.add(l6);
        
        ImageIcon a=new ImageIcon(ClassLoader.getSystemResource("messenger/icon/toggle.png"));
        Image I1=a.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon a1=new ImageIcon(I1);
        l2=new JLabel(a1);
        l2.setBounds(380,2,50,50);
        p1.add(l2);
        
        ImageIcon b=new ImageIcon(ClassLoader.getSystemResource("messenger/icon/video.jpg"));
        Image I2= b.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon b1=new ImageIcon(I2);
        l3=new JLabel(b1);
        l3.setBounds(250,10,30,30);
        p1.add(l3);
        
        ImageIcon c=new ImageIcon(ClassLoader.getSystemResource("messenger/icon/voice.png"));
        Image I3=c.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon c1=new ImageIcon(I3);
        l4=new JLabel(c1);
        l4.setBounds(320,10,30,30);
        p1.add(l4);
        add(p1);
        
        t1=new JTextArea();
        t1.setBounds(2,71,430,430);
        t1.setFont(new Font("Algerian",Font.PLAIN,15));
        t1.setEditable(false);
        t1.setLineWrap(true);
        t1.setWrapStyleWord(false);
        t1.setBackground(new Color(208,246,247));
        add(t1);
        
        t=new JTextField();
        t.setBounds(2,502,340,40);
        t.setFont(new Font("Serif",Font.BOLD,17));
        add(t);
        
        btn=new JButton("send");
        btn.setFont(new Font("serif",Font.BOLD,15));
        btn.setBounds(348,502,85,40);
        btn.setForeground(Color.white);
        btn.setBackground(Color.blue);
        add(btn);
        
        btn.addActionListener(this);
        setUndecorated(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btn)
        {
            String msg=t.getText();
            t1.setText(t1.getText()+"\n\t\t\t"+msg);
            t.setText("");
                      
            try {
                dout.writeUTF(msg);
            } catch (IOException ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }
    public static void main(String[] args)
    {
        new server().setVisible(true);
        try{
            
            s=new ServerSocket(6000);
            skt=s.accept();
            din=new DataInputStream(skt.getInputStream());
            dout=new DataOutputStream(skt.getOutputStream());
            String msginput="";
            msginput=din.readUTF();
            t1.setText(t1.getText()+"\n"+msginput);
            
            }
        
            catch(Exception e)
            {
                
            }
        
    }

    
}
