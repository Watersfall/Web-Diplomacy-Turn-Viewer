/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.diplomacy.turn.viewer;

import java.awt.Graphics2D;
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
        this.turn = turn;
        this.id = id;
        URL url = new URL("http://www.webdiplomacy.net/map.php?gameID=" + id + "&turn=" + turn);
        System.out.println(url.toExternalForm());
        image = ImageIO.read(url);
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
}
