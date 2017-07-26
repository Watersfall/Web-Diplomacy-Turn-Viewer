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
    private IdGetter idGetter;
    
    //Creates the viewing frame for the program
    //
    public ViewingFrame()
    {
        //Setting the monitor resolution to width and height
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
        
        //Setting the delay to a default of 1.5 seconds
        delay = 1500;
        
        //Creating the Frame with name "WebDiplomacy Turn Viewer"
        frame = new JFrame("WebDiplomacy Turn Viewer");
        //Setting the size to half the current resolution, and centering it on the screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(6, 25));
        
        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(width, height));
        imagePanel.setBackground(Color.black);
        
        frame.add(imagePanel);
        
        frame.pack();
        idGetter = new IdGetter();
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
                i = -1;
            }
            imagePanel.remove(label); 
        }
    }
    
    public int getId()
    {
        return idGetter.getId();
    }
    
    public void removeIdGetter()
    {
        idGetter.remove();
    }
}
