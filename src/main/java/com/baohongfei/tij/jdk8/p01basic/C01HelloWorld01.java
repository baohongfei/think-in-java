package com.baohongfei.tij.jdk8.p01basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 20/11/2017 by 鲍彻
 */
public class C01HelloWorld01 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("title name");
        JButton jButton = new JButton("button text");

        //jButton.addActionListener(new ActionListener() {
        //    @Override public void actionPerformed(ActionEvent e) {
        //        System.out.println("button pressed!");
        //    }
        //});

        jButton.addActionListener(event -> {
            System.out.println("button pressed!");
        });

        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
