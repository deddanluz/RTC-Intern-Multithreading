package org.example;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageJulia {
    private static double WIDTH;
    private static double HEIGHT;
    private final float SATURATION = (float) Math.random();
    private final BufferedImage img;
    public ImageJulia(){
        WIDTH=1920;
        HEIGHT=1080;
        img = new BufferedImage((int) WIDTH, (int) HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
    }

    public ImageJulia(double width, double height) throws IllegalArgumentException{
        WIDTH = width;
        HEIGHT = height;
        img = new BufferedImage((int) WIDTH, (int) HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
    }

    public static double getWIDTH() {
        return WIDTH;
    }

    public static double getHEIGHT(){
        return HEIGHT;
    }

    public float getSATURATION(){
        return SATURATION;
    }

    public void setColor(int x, int y, @NotNull Color color){
        img.setRGB(x,y,color.getRGB());
    }

    public void write(File file) throws IOException {
        ImageIO.write(img, file.getName().split("\\.")[file.getName().split("\\.").length-1], file);
    }
}
