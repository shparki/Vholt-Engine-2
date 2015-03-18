package io.shparki.vholt.util;

public class Vector2D {

	public double x, y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Vector2D() { this(0, 0); }
	
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public void incX(double i) { x += i; }
	public double getY() { return y; }
	public void setY(double y) { this.y = y; }
	public void incY(double i) { y += i; }
	
	public void add(Vector2D v){
		x += v.getX();
		y += v.getY();
	}
	public void subtract(Vector2D v){
		x -= v.getX();
		y -= v.getY();
	}
	public void multiply(double i){
		x *= i;
		y *= i;
	}
	public void divide(double i){
		x /= i;
		y /= i;
	}
	
	public double getMagnitude(){ return Math.sqrt(x * x + y * y); }
	public void normalize() { divide(getMagnitude()); }
	public Vector2D getClone() { return new Vector2D(x, y); }
}
