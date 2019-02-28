/* Author - Sam Channon, chann021@umn.edu
   Date Last Editted - 9/27/17
   This is a Circle class to create Circle objects and allow the user to calculate
   the area, and circumference, along with being able to access and change the
   x positon, y position, radius, and color of a Circle object.*/

import java.awt.*;

public class Circle{

    private double xpos;
    private double ypos;
    private double radius;
    private Color cirColor;

    public Circle(double x, double y, double r){
        xpos = x;
        ypos = y;
        radius = r;
    }// Constructor method to create a Circle object with parameters: x - x position, y - y position, and r - radius.

    public double calculatePerimeter(){
        double perimeter = (2 * Math.PI * radius);
        return perimeter;
    }// Method to calculate and return the circumference(perimeter) of a Circle object.

    public double calculateArea(){
        double area = Math.PI *(radius * radius);
        return area;
    }// Method to calculate and return the area of a Circle object.

    public void setColor(Color c){
        cirColor = c;
    }// Setter method to set the Color of a Circle object with the parameter c for Color.

    public void setPos(double x, double y){
        xpos = x;
        ypos = y;
    }// Setter method to set the position of a Circle object with parameters: x - x position, and y - y positon.

    public void setRadius(double r){
        radius = r;
    }// Setter method to set the radius of a Circle object.

    public Color getColor(){
        return cirColor;
    }// Getter method that returns the Color(cirColor) of a Circle object.

    public double getXPos(){
        return xpos;
    }// Getter method that returns the x positon(xpos) of a Circle object.

    public double getYPos(){
        return ypos;
    }// Getter method that returns the y position(ypos) of a Circle object.

    public double getRadius(){
        return radius;
    }// Getter method that returns the radius of a Circle object.
}
