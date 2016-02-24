package com.xebia.mowitnow.bean;

/**
 * Bean de Tondeuse
 * @author Stanislas Zarka
 * 
 */
public class Mower {

	private int x;
	private int y;
	private String orientation;
	
	/**
	 * Constructeur avec arguments
	 * @param x
	 * 		abscisse de la tondeuse
	 * @param y
	 * 		ordonnee de la tondeuse
	 * @param orientation
	 * 		orientation de la tondeuse
	 */
	public Mower(int x, int y, String orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	
	/**
	 * Constructeur sans arguments
	 */
	public Mower() {
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
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
}