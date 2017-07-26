/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.diplomacy.turn.viewer;

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
