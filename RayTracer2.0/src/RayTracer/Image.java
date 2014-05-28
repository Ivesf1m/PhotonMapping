/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RayTracer;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
 *
 * @author thiago
 */
public class Image {
    
    private BufferedImage image;
    private String name;
    
    public Image(int imageWidth, int imageHeight, String name){
        
        System.out.println(imageWidth + " " + imageHeight);
        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        this.name = name;
    }
    
    public void setImage(BufferedImage im){
        image = im;
    }
    
    public void setPixelColor(int iCoord, int jCoord, double[] color){
        
        image.getRaster().setPixel(iCoord, jCoord, color);
        
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
