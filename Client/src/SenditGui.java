//package Sendit;

import javax.swing.*;
import java.io.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SenditGui
{
   Client client;
   public SenditGui(Client c){
      client = c;
   }
    public void homegGui()
    {

        //Code for the frame of the gui
        JFrame gframe = new JFrame("SendIt - Windows Edition 2019");

        //Creates Gui frame Icon
        ImageIcon img = new ImageIcon(SenditGui.class.getResource("Sendit-icon.png"));
        gframe.setIconImage(img.getImage());

        //Creates header image
        ImageIcon header = new ImageIcon(SenditGui.class.getResource("Sendit.png"));
        JLabel label = new JLabel(header);
        JPanel panel = new JPanel();
        panel.add(label);
        gframe.add(panel);
        gframe.setLayout(new FlowLayout());

        gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//End program when user exits the GUI

        //Creates a choose file button to access computer file directory
        JButton FileButton = new JButton("Choose File");
        FileButton.setBounds(400,450,105,30);
        FileButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                ChooseFile cf = new ChooseFile(); //choose file class
                try{

                    File file = cf.PickFile();
                    //client.sendMessage(file);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //Creates a button to access text GUI
        JButton TextButton = new JButton("Send Text");
        TextButton.setBounds(400,490,105,30);
        TextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg1)
            {
                TextGui st = new TextGui(client); //choose file class
                try{
                    st.SendText();
                    gframe.setVisible(false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        //Displays buttons on frame
        gframe.add(FileButton);
        gframe.add(TextButton);

        //Sets window size
        gframe.setSize(900,900);

        gframe.setVisible(true);
    }
}
