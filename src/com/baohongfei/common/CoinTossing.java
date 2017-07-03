package com.baohongfei.common;

/**
 * Created by terry on 03/07/17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CoinTossing extends JApplet implements ActionListener {
    int tailNumber=0,headNumber=0;
    JLabel TossLable,TailLable,HeadLable;
    JButton TossButton;
    JTextField TailField,HeadField;


    public void init()
    {

        Container  container=getContentPane();
        container.setLayout(new FlowLayout());

        TossButton= new JButton("Toss");
        container.add(TossButton);


        TailLable= new JLabel("Tail frequency is:");
        container.add(TailLable);

        TailField=new JTextField(10);
        container.add(TailField);

        HeadLable= new JLabel("Head frequency is:");
        container.add(HeadLable);

        HeadField=new JTextField(10);
        container.add(HeadField);

        TossButton.addActionListener(this);


    }

    public void actionPerformed (ActionEvent event)
    {
        if (Flip())
            tailNumber++;
        else
            headNumber++;

        TailField.setText(Integer.toString(tailNumber));
        HeadField.setText(Integer.toString(headNumber));

    }

    public boolean Flip()
    {
        return Math.random()<0.5;
    }
}