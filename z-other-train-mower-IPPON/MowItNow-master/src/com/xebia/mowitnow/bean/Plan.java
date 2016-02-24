package com.xebia.mowitnow.bean;

/**
 * Bean de Plan
 * @author Stanislas Zarka
 *
 */
public class Plan {

	private int xMax;
	private int yMax;
	
	/**
	 * Constructeur avec arguments
	 * @param xMax
	 * 		abscisse maximale du plan
	 * @param yMax
	 * 		ordonnee maximale du plan
	 */
	public Plan(int xMax, int yMax) {
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	public int getxMax() {
		return xMax;
	}
	public void setxMax(int xMax) {
		this.xMax = xMax;
	}
	public int getyMax() {
		return yMax;
	}
	public void setyMax(int yMax) {
		this.yMax = yMax;
	}
	
}