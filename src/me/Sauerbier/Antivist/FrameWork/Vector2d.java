package me.Sauerbier.Antivist.FrameWork;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Vector2d {
    private double x,y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2i vector2i) {
        x = vector2i.getX();
        y = vector2i.getY();
    }

    public void add(Vector2i vector2i){
        x += vector2i.getX();
        y += vector2i.getY();
    }

    public void add(Vector2d vector2d){
        x += vector2d.getX();
        y += vector2d.getY();
    }

    public void sub(Vector2i vec){
        this.x -= vec.getX();
        this.y -= vec.getY();
    }

    public void multiply(Vector2i vec){
        x *= vec.getX();
        y *= vec.getY();
    }

    public void multiply(Vector2d vec){
        x *= vec.getX();
        y *= vec.getY();
    }

    public void multiply(double m){
        x *= m;
        y *= m;
    }

    public void divide(Vector2i vec){
        x /= vec.getX();
        y /= vec.getY();
    }

    public void divide(int m){
        x /= m;
        y /= m;
    }

    public double scalar(Vector2i vec){
        return (x + vec.getX()) * (y + vec.getY());
    }

    public double distance(Vector2i vec){
        double dx = vec.getX() - x;
        double dy = vec.getY() - y;
        return new Vector2d(dx,dy).lenght();
    }

    public double angle(Vector2i vec){
        double dx = vec.getX() - x;
        double dy = vec.getY() - y;
        return Math.atan2(dy,dx);
    }

    public Vector2i toInt(){
        return new Vector2i((int)x,(int)y);
    }

    public static Vector2d fromAngle(double angle){
        return new Vector2d(Math.cos(angle),Math.sin(angle));
    }

    public double lenght(){
        return Math.sqrt(x*x + y*y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
