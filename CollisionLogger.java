/* Author - Sam Channon, chann021@umn.edu
   Date Last Editted - 10/8/17
   This is a CollisionLogger class to create a 2D array to store collisions in
   different parts of the grid.*/

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class CollisionLogger {

    private int screenWidth;
    private int screenHeight;
    private int bucketWidth;
    private int numRows;
    private int[][] normalizedHeatMap;

    public CollisionLogger(int sw, int sh, int bw) {

    	  screenWidth = sw;
        screenHeight = sh;
        bucketWidth = bw;
        numRows = sw/bw;
        normalizedHeatMap = new int[sw/bw][sh/bw];

        for(int row = 0; row < numRows; row++){
            for(int column = 0; column < numRows; column++){
                normalizedHeatMap[row][column] = 0;
            }
        }
    }//Constructor class to create a CollisionLogger object with the screenwidth, screenheight and
    //bucketwidth for each sector on the grid. Also useds a nested for loop to create an array with
    //zeros in each position of the array.

    public void collide(Ball one, Ball two) {

    	 double averageX = ((one.getXPos() + two.getXPos())/2);
       double averageY = ((one.getYPos() + two.getYPos())/2);
       int row = (int)averageX/bucketWidth;
       int column = (int)averageY/bucketWidth;
       normalizedHeatMap[row][column]++;
    }//Collide method that calculates the average of the X and Y positions of each colliding ball and
    //stores them into the 2D array.

    public int[][] getNormalizedHeatMap() {

        int mostCollisions = 0;
        int newRow = 0;
        int newColumn = 0;

        for(int row = 0; row < numRows; row++){
            for(int column = 0; column < numRows; column++){
                if(normalizedHeatMap[row][column] > mostCollisions){
                    mostCollisions = normalizedHeatMap[row][column];
                    newRow = row;
                    newColumn = column;
                }
            }
        }
        normalizedHeatMap[newRow][newColumn] = 255;
        return normalizedHeatMap;
    }//getNormalizedHeatMap method that sorts through the current 2D array and finds the grid sector
     //with the most collisions and sets its value to 255.
}
