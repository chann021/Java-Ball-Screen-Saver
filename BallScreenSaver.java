/* Author - Sam Channon, chann021@umn.edu
   Date Last Editted - 10/8/17
   This is a BallScreenSaver class to create and run the bouncing balls screen
   saver with an input amount of balls and updates the screen*/

import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.awt.event.*;
import java.io.IOException;



public class BallScreenSaver extends AnimationFrame{

    private int frameWidth;
    private int frameHeight;
    private String frameName;
    private int numBalls;
    private Ball[] ballArray;
    private int saveCounter = 0;
    private CollisionLogger logger = new CollisionLogger(700, 700, 70);

    public BallScreenSaver(int width, int height, String name, int balls){

        frameWidth = width;
        frameHeight = height;
        frameName = name;
        numBalls = balls;

        ballArray = new Ball[balls];

        for( int i = 0; i < balls; i++){
            Random rand = new Random();

            int randomXPos = rand.nextInt(665) + 5;
            int randomYPos = rand.nextInt(660) + 20;
            int randomXVel = rand.nextInt(200) - 100;
            int randomYVel = rand.nextInt(200) - 100;

            if( i == 0){
                Ball newBall = new Ball(randomXPos, randomYPos, 15, Color.RED);
                newBall.setSpeedX(randomXVel);
                newBall.setSpeedY(randomYVel);
                ballArray[i] = newBall;

            }else{
                Ball newBall = new Ball(randomXPos, randomYPos, 15, Color.GREEN);
                newBall.setSpeedX(randomXVel);
                newBall.setSpeedY(randomYVel);
                ballArray[i] = newBall;
            }
        }
    }//Constructor to store the frame dimensions and create an array of Balls all with random
    //positions and velocities.

    public void action(){

        for(int i = 0; i < ballArray.length; i++){

            double newXPos = ballArray[i].getXPos() + (ballArray[i].getSpeedX()/this.getFPS());
            double newYPos = ballArray[i].getYPos() + (ballArray[i].getSpeedY()/this.getFPS());
            ballArray[i].setPos(newXPos,newYPos);
            //Calculates the new positon of each ball for each frame of the animation.

            if ( ballArray[i].getXPos() >= 680 || ballArray[i].getXPos() <= 0){
                double newXVelocity = (ballArray[i].getSpeedX() * -1);
                ballArray[i].setSpeedX(newXVelocity);
                //Checks to see if the ball ran into one of the X borders of the frame and edits the velocity accorcingly.

            }else if ( ballArray[i].getYPos() >= 680 || ballArray[i].getYPos() <= 20){
                double newYVelocity = (ballArray[i].getSpeedY() * -1);
                ballArray[i].setSpeedY(newYVelocity);
            }//Checks to see if the ball ran into one of the Y borders of the frame and edits the velocity accorcingly.
        }
        int d = 0;
        for(int i = 0; i < ballArray.length; i++){
            d++;
            for( int c = d; c < ballArray.length; c++){
                if(i != c){
                    if(ballArray[i].intersect(ballArray[c]) == true){
                        Ball b = ballArray[i];
                        Ball b2 = ballArray[c];
                        b.collide(b2);
                        logger.collide(b, b2);
                        if(ballArray[i].getColor() == Color.RED){
                            ballArray[c].setColor(Color.RED);
                        }else if(ballArray[c].getColor() == Color.RED){
                            ballArray[i].setColor(Color.RED);
                        }//Compares each of the balls in the array and checks to see if they collided and if so it edits
                    }    //their velocities and stores the collision in a CollisionLogger object and also if one of the balls
                }        //was green and the other red than the green one becomes red.
            }
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,this.frameWidth,this.frameHeight);
        g.setColor(Color.white);
        g.drawRect(725,725,0,0);
        for(int i = 0; i < ballArray.length; i++){
            g.setColor(ballArray[i].getColor());
            double xpos = ballArray[i].getXPos();
            double ypos = ballArray[i].getYPos();
            double radius = ballArray[i].getRadius();
            g.fillOval((int)xpos, (int)ypos, 20, 20);
        }
    }//Draw method to draw every Ball in the ballArray on the screen for every fps.

    public void processKeyEvent(KeyEvent k){
        int keyCode = k.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT){
            for(int i = 0; i < ballArray.length; i++){
                double newXVelocity = ballArray[i].getSpeedX() * .9;
                double newYVelocity = ballArray[i].getSpeedY() * .9;
                ballArray[i].setSpeedX(newXVelocity);
                ballArray[i].setSpeedY(newYVelocity);
            }//Checks to see if the left arrow was pressed on the keyboard and if so lower all the balls speeds
             //by ten percent.
        }else if (keyCode == KeyEvent.VK_RIGHT){
            for(int i = 0; i < ballArray.length; i++){
                double newXVelocity = ballArray[i].getSpeedX() * 1.1;
                double newYVelocity = ballArray[i].getSpeedY() * 1.1;
                ballArray[i].setSpeedX(newXVelocity);
                ballArray[i].setSpeedY(newYVelocity);
            }//Checks to see if the right arrow was pressed on the keyboard and if so raise all the balls speeds
             //by ten percent.
        }else if (k.getID() == KeyEvent.KEY_PRESSED && keyCode == KeyEvent.VK_P) {
          EasyBufferedImage image = EasyBufferedImage.createImage(logger.getNormalizedHeatMap());
            try {
                image.save("heatmap"+saveCounter+".png", EasyBufferedImage.PNG);
                saveCounter++;
            } catch (IOException exc) {
                exc.printStackTrace();
                System.exit(1);
            }
        }//Checks to see if the p key was pressed on the keyboard and if so create a heat map based on the array created
         //in the CollisionLogger class
  }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("How many balls do you want? ");
        int balls = scanner.nextInt();
        scanner.close();

        BallScreenSaver screen = new BallScreenSaver(700, 700, "Bouncing Balls", balls);
        screen.start();

    }//Main method to prompt the user for how many balls they want and create a BallScreenSaver object to run the program
     //and begin the animation.
}
