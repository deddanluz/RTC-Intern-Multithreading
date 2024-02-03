package org.example;

import java.awt.*;

public class ThreadJulia implements Runnable{
    private final int x;
    private final ComplexNumber constant;
    private final ImageJulia imageJulia;

    public ThreadJulia(int x, ComplexNumber constant, ImageJulia imageJulia){
        this.x=x;
        this.constant=constant;
        this.imageJulia=imageJulia;
    }

    @Override
    public void run(){
        for (int y=0; y<ImageJulia.getHEIGHT(); y++) {
            ComplexNumber newz = new ComplexNumber(2.0*(x-ImageJulia.getWIDTH()/2)/(ImageJulia.getWIDTH()/2), 1.33*(y-ImageJulia.getHEIGHT()/2)/(ImageJulia.getHEIGHT()/2));

            int i;
            int MAX_ITERATION = 256;
            for (i=0; i< MAX_ITERATION; i++){

                newz = newz.squareComplexNumber();
                newz.addComplexNumber(constant);

                if(newz.modComplexNumber() > 2) {
                    break;
                }
            }

            float brightness = i < MAX_ITERATION ? 1f : 0;
            float hue = (i%256)/255.0f;

            Color color = Color.getHSBColor(hue, imageJulia.getSATURATION(), brightness);
            imageJulia.setColor(x, y, color);
        }
    }
}
