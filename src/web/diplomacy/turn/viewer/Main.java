/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.diplomacy.turn.viewer;

import java.io.IOException;

/**
 *
 * @author Christopher
 */
public class Main {
     
    public static void main(String args[]) throws IOException, InterruptedException
    {
        System.setProperty ("http.agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        System.setProperty ("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        ViewingFrame viewingFrame  = new ViewingFrame();
        while(viewingFrame.getId() == -3488975)
        {
            Thread.sleep(50);
        }
        viewingFrame.removeIdGetter();
        TurnImages images = new TurnImages(viewingFrame.getId());
        Thread.sleep(2500);
        viewingFrame.setPanel(images.getArray());
    }
}
