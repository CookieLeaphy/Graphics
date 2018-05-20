//public class DrawLine//Display code retrieved from https://stackoverflow.com/questions/7708204/set-color-as-int-value-for-use-in-setrgbint-x-int-y-int-rgb-method-java/7815934
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.image.*;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

public class DrawLine extends BufferedImage{

    public int size = 600;
    public BufferedImage image = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
    Graphics g;
    //Creates a buffered image
    public DrawLine (){
//        super(600,600,BufferedImage.TYPE_INT_RGB);
//        g = createGraphics();
//        //createGraphics();
//        g.setColor(Color.WHITE);
//        g.fillRect(0,0,size,size);
//
//        g.setColor(Color.BLACK);
//        g.drawLine(300,0,300,600);
//        g.drawLine(0,300,600,300);
//
//        //g.drawLine(200,300,400,500);
        super(600,600,BufferedImage.TYPE_INT_RGB);
        //g.dispose();
        g = createGraphics();
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,size,size);


    }

    public DrawLine (int x1, int x2, int y1, int y2){
        super(600,600,BufferedImage.TYPE_INT_RGB);
        //g.dispose();
        g = createGraphics();
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,size,size);
        Basic_alg(x1, x2, y1 ,y2);
        Basic_alg(100,200,300,400);
        Basic_alg(400,200,300,400);
    }

    public DrawLine (double[][] pointsArray){
        super(600,600,BufferedImage.TYPE_INT_RGB);
        g = createGraphics();
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,size,size);
        for(int i =0; i<pointsArray.length; i++){
            Basic_alg((int)pointsArray[i][0],(int)pointsArray[i][1],(int)pointsArray[i][2],(int)pointsArray[i][3]);
        }
    }
    public BufferedImage getImage(){

        return this;
    }


    //------------------------------
    // Basic Line Drawing Algorithum
    //------------------------------
    public void Basic_alg(int x0, int x1, int y0, int y1){
        int temp;
        int dy = y1-y0;
        int dx = x1-x0;
        //int x = x0;
        int y;
        int x;

        if (x1>=x0 && y1>=y0) {//Positive slope
            if(dy == 0 && dx == 0)
            {
                if(x0==0 && y0==0)
                {

                }else {
                    setRGB(x0, size - y0, Color.BLACK.getRGB());
                }
            }
            else if (dx > dy) { //Length wise
                {
                    for (x = x0; x <= x1; x++) {  //if all values are greater than 0
                        y = (dy * (x - x0) / dx) + y0; //Prevents Integer Division
                        if ((x > -1) && (x < size) && ((size - y) > -1) && (size - (y) < size)) //Draw lines within boundaries
                            setRGB(x, size - y, Color.BLUE.getRGB());
                    }//for
                }
            } else {  //Height
                for (y = y0; y <= y1; y++) {  //if all values are greater than 0
                    x = (dx * (y - y0) / dy) + x0; //Prevents Integer Division
                    if ((x > -1) && (x < size) && ((size - y) > -1) && ((size - (y)) < size)) //Draw lines within boundaries
                        setRGB(x, size - y, Color.BLUE.getRGB());
                }//for
            }//else
            //
        } else if ( x1<x0 && y1<=y0 ){ //Case 2: Drawing the Line Backwards
            temp = x0; x0 = x1; x1 = temp; //Swapping x0,x1
            temp = y0; y0 = y1; y1 = temp; //Swapping y0,y1
            dy = dy * -1; dx = dx * -1;    //
            if (dy <= dx) {
                for (x = x0; x <= x1; x++) {  //if all values are greater than 0
                    y = (dy * (x - x0) / dx) + y0; //Prevents Integer Division
                    if ((x > -1) && (x < size) && ((size - y) > -1) && (size - (y) < size)) //Draw lines within boundaries
                        setRGB(x, size - y, Color.BLUE.getRGB());
                }//for
            }else {
                for (y = y0; y <= y1; y++) {  //if all values are greater than 0
                    x = (dx * (y - y0) / dy) + x0; //Prevents Integer Division
                    if ((x > -1) && (x < size) && ((size - y) > -1) && ((size - (y)) < size)) //Draw lines within boundaries
                        setRGB(x, size - y, Color.BLUE.getRGB());
                }//for
            }
        } else if (x0<=x1 && y0>y1){ //Case 3 Negative Slope
            if((-dy)<dx) {
                //(0,size,100, 0)
                for (x = x0; x <= x1; x++) {  //if all values are greater than 0
                    y = (dy * (x - x0) / dx) + y0; //Prevents Integer Division
                    if ((x > -1) && (x < size) && ((size - y) > -1) && (size - (y) < size)) //Draw lines within boundaries
                        setRGB(x, size - y, Color.BLUE.getRGB());
                }//for
            }else {
                //(0,600,size, 0) //y values have been changed
                //Currently flipped not
                //y0 = size, y1 = 0
                temp = y1; y1 = y0; y0 = temp; //y0 = 0, y1 = size
                temp = x1; x1 = x0; x0 = temp;
                for (y = y0; y <= y1; y++) {  //if all values are greater than 0
                    x = (dx * (y - y0) / dy) + x0; //Prevents Integer Division
                    if ((x > -1) && (x < size) && ((size - y) > -1) && ((size - (y)) < size)) //Draw lines within boundaries
                        setRGB(x, size - y, Color.BLUE.getRGB());
                }//for
            }

        }else if (x0>x1 && y0<y1){ //Drawing Negative Slope Backwards
            //800,400,0,600
            //dx = -400
            if (dy > -dx) {
                for (y = y0; y <= y1; y++) {  //if all values are greater than 0
                    x = (dx * (y - y0) / dy) + x0; //Prevents Integer Division
                    if ((x > -1) && (x < size) && ((size - y) > -1) && ((size - (y)) < size)) //Draw lines within boundaries
                        setRGB(x, size - y, Color.BLUE.getRGB());
                }//for
            }else {
                temp = x0;x0 = x1;x1 = temp;
                temp = y0;y0 = y1;y1 = temp;
                for (x = x0; x <= x1; x++) {  //if all values are greater than 0
                    y = (dy * (x - x0) / dx) + y0; //Prevents Integer Division
                    if ((x > -1) && (x < size) && ((size - y) > -1) && (size - (y) < size)) //Draw lines within boundaries
                        setRGB(x, size - y, Color.BLUE.getRGB());
                }//for
            }
        }

    }



}

