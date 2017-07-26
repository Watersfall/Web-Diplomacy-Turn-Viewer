/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.diplomacy.turn.viewer;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Christopher
 */
public class TurnImages {
    
    public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) 
    {
        // The images must be the same size.
        if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) 
        {
            int width = imgA.getWidth();
            int height = imgA.getHeight();

            // Loop over every pixel.
            for (int y = 0; y < height; y++) 
            {
                for (int x = 0; x < width; x++) 
                {
                    // Compare the pixels for equality.
                    if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) 
                    {
                        return false;
                    }
                }
            }
        } 
        else 
        {
            return false;
        }
    return true;
    }
    
    private ArrayList<TurnImage> turnImages = new ArrayList<TurnImage>();
    private int id;
    public TurnImages(int id) throws IOException
    {
        this.id = id;
        BufferedImage previousImage = new TurnImage(-1, this.id).getImage();
        turnImages.add(new TurnImage(0, this.id));
        int turn = - 1;
        while(!compareImages(previousImage, turnImages.get(turn + 1).getImage()))
        {
            previousImage = turnImages.get(turn + 1).getImage();
            turn++;
            turnImages.add(new TurnImage(turn + 1, this.id));
            System.out.println(turn + 1);
        }
    }
    
    public ArrayList<TurnImage> getArray()
    {
        return turnImages;
    }
}
