/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watersfall.turnviewer;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 *
 * @author Christopher
 */
public class TurnImage {
    
    private BufferedImage image;
    private int turn;
    private int id;
    private int width;
    private int height;
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) 
    { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }  
    
    public TurnImage(int turn, int id) throws MalformedURLException, IOException
    {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.width = gd.getDisplayMode().getWidth();
        this.height = gd.getDisplayMode().getHeight();
        this.turn = turn;
        this.id = id;
        URL url = new URL("http://www.webdiplomacy.net/map.php?gameID=" + this.id + "&turn=" + this.turn + "&mapType=large");
        System.out.println(url.toExternalForm());
        image = ImageIO.read(url);
        image = resize(image, (int)((this.height - this.height / 8) * 1.3), this.height - this.height / 8);
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
}
