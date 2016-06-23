package me.Sauerbier.Antivist.FrameWork;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Vector2i {

    private int x,y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2i vec) {
        this.x = vec.getX();
        this.y = vec.getY();
    }

    public void add(Vector2i vector2i){
        x += vector2i.getX();
        y += vector2i.getY();
    }

    public void add(Vector2d vector2i){
        x += vector2i.getX();
        y += vector2i.getY();
    }

    public void sub(Vector2i vec){
        this.x -= vec.getX();
        this.y -= vec.getY();
    }

    public void multiply(Vector2i vec){
        x *= vec.getX();
        y *= vec.getY();
    }

    public void multiply(int m){
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

    public int scalar(Vector2i vec){
        return (x + vec.getX()) * (y + vec.getY());
    }

    public double distance(Vector2i vec){
        int dx = vec.getX() - x;
        int dy = vec.getY() - y;
        return new Vector2i(dx,dy).length();
    }

    public double angle(Vector2i vec){
        double dx = vec.getX() - x;
        double dy = vec.getY() - y;
        return Math.atan2(dy,dx);
    }



    public Vector2d toDouble(){
        return new Vector2d(x,y);
    }
    public static Vector2d fromAngle(double angle){
        return new Vector2d(Math.cos(angle),Math.sin(angle));
    }


    public Vector2i toPixelPrecision(int pixelMask){
        return new Vector2i((int) x << pixelMask,(int) y << pixelMask);
    }

    public Vector2i toBlockPrecision(int pixelMask){
        return new Vector2i((int) x >> pixelMask,(int) y >> pixelMask);
    }

    public double length(){
        return Math.sqrt(x*x + y*y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
