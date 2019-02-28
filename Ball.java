/* Author - Sam Channon, chann021@umn.edu
   Date Last Editted - 10/8/17
   This is a Ball class to create Ball objects that inherits the Circle class
   and allows the user change the position of the Ball, check to see if two balls
   have intersected, and also to edit the velocities of two Balls that have
   collided.*/


import java.awt.*;

public class Ball extends Circle{

    private double speedX;
    private double speedY;

    public Ball(double xp, double yp, double r, Color c){
        super(xp, yp, r);
        this.setColor(c);
    }//Constructor method to create a Ball object and call the Circle constructor.

    public void setSpeedX(double xv){
        speedX = xv;
    }//Setter method to set the velocity in the X direction.

    public void setSpeedY(double yv){
        speedY = yv;
    }//Setter method to set the velocity in the Y direction.

    public double getSpeedX(){
        return speedX;
    }//Getter method to return the velocity in the X direction.

    public double getSpeedY(){
        return speedY;
    }//Getter method to return the velocity in the Y direction.

    public void updatePosition(double time){

        double newXpos = (this.getXPos() + (speedX * time));
        double newYpos = (this.getYPos() + (speedY * time));
        this.setPos(newXpos, newYpos);
    }//Update method to update a Balls position for each frame of the animation.

    public Boolean intersect(Ball ball){

        double xDiff = (this.getXPos() - ball.getXPos());
        double yDiff = (this.getYPos() - ball.getYPos());
        double distance = Math.sqrt((Math.pow(xDiff, 2)) + (Math.pow(yDiff, 2)));

        if( distance <= (this.getRadius() + ball.getRadius())){
            return true;
        }else{
            return false;
        }
    }//Intersect method that calculates the distance between two Balls and then checks to see if the
     //distance is less than both the balls radius' added together which if it is then that would mean
     //the balls collided.

    public void collide(Ball ball){
        if(this.intersect(ball).equals(true)){

          double xDiff = (this.getXPos() - ball.getXPos());
          double yDiff = (this.getYPos() - ball.getYPos());
          double distance = Math.sqrt((Math.pow(xDiff, 2)) + (Math.pow(yDiff, 2)));
          //Calculates the differences in the X and Y direction and the distance between the two Balls.

          double deltaX = (xDiff/distance);
          double deltaY = (yDiff/distance);
          //Creates variables deltaX and deltaY to be used to calculate the final velocities of the Balls.

          double v1F1x = (deltaX * ((ball.getSpeedX() * deltaX) + (ball.getSpeedY() * deltaY)));
          double v1F2x = (deltaY * (((-1 * this.getSpeedX()) * deltaY) + (this.getSpeedY() * deltaX)));
          double vFinalx1 = v1F1x - v1F2x;
          //Calculates the final velocity in the X direction of the first Ball.

          double v1F1y = (deltaY * ((ball.getSpeedX() * deltaX) + (ball.getSpeedY() * deltaY)));
          double v1F2y = (deltaX * (((-1 * this.getSpeedX()) * deltaY) + (this.getSpeedY() * deltaX)));
          double vFinaly1 = v1F1y + v1F2y;
          //Calculates the final velocity in the Y direction of the first Ball.

          double v2F1x = (deltaX * ((this.getSpeedX() * deltaX) + (this.getSpeedY() * deltaY)));
          double v2F2x = (deltaY * (((-1 * ball.getSpeedX()) * deltaY) + (ball.getSpeedY() * deltaX)));
          double vFinalx2 = v2F1x - v2F2x;
          //Calculates the final velocity in the X direction of the second Ball.

          double v2F1y = (deltaY * ((this.getSpeedX() * deltaX) + (this.getSpeedY() * deltaY)));
          double v2F2y = (deltaX * (((-1 * ball.getSpeedX()) * deltaY) + (ball.getSpeedY() * deltaX)));
          double vFinaly2 = v2F1y + v2F2y;
          //Calculates the final velocity in the Y direction of the second Ball.

          this.setSpeedX(vFinalx1);
          this.setSpeedY(vFinaly1);
          ball.setSpeedX(vFinalx2);
          ball.setSpeedY(vFinaly2);
        }//Sets the new velocities of the Balls
    }
}
