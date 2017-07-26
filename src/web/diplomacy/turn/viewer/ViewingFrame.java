/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.diplomacy.turn.viewer;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Christopher
 */
public class ViewingFrame {
    
    private int width;
    private int height;
    private JFrame frame;
    private JTextField idField;
    private JPanel imagePanel;
    private Button button;
    private long delay;
    
    //Creates the viewing frame for the program
    //
    public ViewingFrame()
    {
        delay = 1500;
        //Getting the resolution of the primary monitor for proper sizing
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
        
        //Creating the Frame with name "WebDiplomacy Turn Viewer"
        frame = new JFrame("WebDiplomacy Turn Viewer");
        //Setting the size to half the current resolution, and centering it on the screen
        frame.setBounds(width / 4, height / 4, width / 2, height / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(6, 25));
        
        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(1300 / 2, 1000 / 2));
        imagePanel.setBackground(Color.black);
        
        
        
        frame.add(idField);
        frame.add(imagePanel);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void setPanel(ArrayList<TurnImage> array) throws InterruptedException
    {
        Icon icon;
        JLabel label;
        for(int i = 0; i <= array.size(); i++)
        {
            icon = new ImageIcon(array.get(i).getImage());
            label = new JLabel(icon);
            imagePanel.add(label);
            imagePanel.updateUI();
            Thread.sleep(delay);
            if (i == array.size() - 1)
            {
                i = 0;
            }
            imagePanel.remove(label);
            
        }
    }
}
