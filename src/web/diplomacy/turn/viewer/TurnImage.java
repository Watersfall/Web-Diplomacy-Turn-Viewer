/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.diplomacy.turn.viewer;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

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
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
        this.turn = turn;
        this.id = id;
        URL url = new URL("http://www.webdiplomacy.net/map.php?gameID=" + id + "&turn=" + turn + "&mapType=large");
        System.out.println(url.toExternalForm());
        image = ImageIO.read(url);
        image = resize(image, (int)((height - height / 12) * 1.3), height - height / 12);
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
}
