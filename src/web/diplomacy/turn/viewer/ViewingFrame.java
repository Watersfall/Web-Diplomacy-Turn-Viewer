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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Christopher
 */
public class ViewingFrame {
    
    private ArrayList<TurnImage> array;
    private JLabel label;
    private int width;
    private int height;
    private JFrame frame;
    private JTextField idField;
    private JPanel imagePanel;
    private JButton pauseButton;
    private JButton nextButton;
    private JButton previousButton;
    private long delay;
    private IdGetter idGetter;
    private int i;
    private boolean pause;
    ActionListener pauseListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("hit pause button");
            pause();
        }
    };
    ActionListener nextListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("hit next button");
            if(!pause)
                pause();
            i++;
            if(i >= array.size())
                i = 0;
            setPanelById(i);
        }
    };
    ActionListener previousListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Hit back button");
            if(!pause)
                pause();
            i--;
            if(i < 0)
                i = array.size() - 1;
            setPanelById(i);
        }
    };
    
    //Creates the viewing frame for the program
    //
    public ViewingFrame()
    {
        i = 0;
        pause = false;
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
        
        pauseButton = new JButton("Pause/Play");
        nextButton = new JButton("->");
        previousButton = new JButton("<-");
        pauseButton.addActionListener(pauseListener);
        nextButton.addActionListener(nextListener);
        previousButton.addActionListener(previousListener);
        
        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(width, height - height / 10));
        imagePanel.setBackground(Color.black);
        
        frame.add(imagePanel);
        frame.add(previousButton);
        frame.add(pauseButton);
        frame.add(nextButton);
        
        
        frame.pack();
        idGetter = new IdGetter();
        frame.setVisible(true);
    }
    
    public void setPanel(ArrayList<TurnImage> array) throws InterruptedException
    {
        this.array = array;
        this.array.remove(array.size() - 1);
        Thread panelThread = new Thread()
        {
            public void run()
            {
                Icon icon;
                for(i = 0; i <= array.size(); i++)
                {
                    icon = new ImageIcon(array.get(i).getImage());
                    label = new JLabel(icon);
                    imagePanel.add(label);
                    imagePanel.updateUI();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ViewingFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (i == array.size() - 1)
                    {
                        i = -1;
                    }
                    while(pause)
                    {
                
                    }
                    imagePanel.remove(label); 
                }
            }
        };
        panelThread.run();
    }
    
    public int getId()
    {
        return idGetter.getId();
    }
    
    public void removeIdGetter()
    {
        idGetter.remove();
    }
    
    public void pause()
    {
        pause = !pause;
    }
    
    public void setPanelById(int id)
    {
        Icon icon = new ImageIcon(array.get(id).getImage());
        imagePanel.remove(label);
        label = new JLabel(icon);
        imagePanel.add(label);
        imagePanel.updateUI();
    }
}
