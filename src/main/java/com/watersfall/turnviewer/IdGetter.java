/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watersfall.turnviewer;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Christopher
 */
public class IdGetter {
    
    private int id = -3488975;
    private JFrame panel;
    private JTextField idField;
    private JButton button;
    
    public IdGetter()
    {
        panel = new JFrame("Enter the game ID");
        panel.setLayout(new FlowLayout());
        panel.setAlwaysOnTop(true);
        
        idField = new JTextField(20);
        idField.setToolTipText("Enter the game ID");
        panel.add(idField);
        
        button = new JButton("Go!");
        button.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                id = Integer.parseInt(idField.getText());
            }
        });
        panel.add(button);
        
        panel.pack();
        panel.setVisible(true);
    }
    
    public int getId()
    {
        return id;
    }
    
    public void remove()
    {
        panel.setVisible(false);
    }
}
