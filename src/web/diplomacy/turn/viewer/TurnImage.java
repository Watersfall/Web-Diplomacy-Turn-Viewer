/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.diplomacy.turn.viewer;

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
    public TurnImage(int turn, int id) throws MalformedURLException, IOException
    {
        this.turn = turn;
        this.id = id;
        String url = "http://www.webdiplomacy.net/map.php?gameID=" + id + "&turn=" + turn + "&mapType=large";
        image = ImageIO.read(new URL(url));
    }
    
    public BufferedImage getImage()
    {
        return image;
    }
}
