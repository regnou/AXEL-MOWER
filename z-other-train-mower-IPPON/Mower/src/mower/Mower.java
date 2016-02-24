package mower;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Axel Regnoult
 * @version     0.1
 * @since      2010-04-29
 * 
 * This class represents a Mower
 */
public class Mower {
	/**
	 * x and y represent the actual coordinates of the mower
	 * xTerrain and YTerrain represent the size of the terrain 
	 */
	int	x, y, xTerrain, yTerrain = 0;
	/**
	 * o : Orientation : N,S,E,W
	 * i : Instructions
	 */
	String	orientation, instructions = "-";



	public Mower() {}
	public Mower(int xMax, int yMax, int x, int y, String orientation, String instruction) {
		this.x = x;
		this.y = y;
		this.xTerrain = xMax;
		this.yTerrain = yMax;
		this.orientation = orientation;
		this.instructions = instruction;
	}
	/**
	 * init Terrain
	 */
	public void initTerrain(int xMax, int yMax)
	{
		this.xTerrain = xMax;
		this.yTerrain = yMax;
	}
	/**
	 * init Mower
	 */
	public void initMower(int x, int y, String orientation)
	{
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	/**
	 * init mower's instructions and Update the mower's status
	 */
	public void execute(String instructions)
	{
		this.instructions = instructions;
		//		lineSplitted = line.split("");
		//					System.out.println("'"+lineSplitted[3]+"'");
		for (int i = 0; i < instructions.length(); i++)
		{
			if (instructions.charAt(i) == 'D') turnRight();
			else if (instructions.charAt(i) == 'G') turnLeft();
			else if (instructions.charAt(i) == 'A') moveForward();
			else System.out.println("Error: File format is not correct - mower cannot execute its instructions: '" + instructions.charAt(i) + "'");
		}
	}
	/**
	 * Shortcut method without parameters, usefull for tests (where it does not use a text file in parameter)
	 */
	public void execute()
	{
		execute(this.instructions);
	}
	/**
	 * Change the mower's orientation to the right
	 */
	public void turnRight()
	{
		if (orientation.equals("N"))
		{
			orientation = "E";
		}
		else if (orientation.equals("E"))
		{
			orientation = "S";
		}
		else if (orientation.equals("S"))
		{
			orientation = "W";
		}
		else if (orientation.equals("W"))
		{
			orientation = "N";
		}
	}
	/**
	 * Change the mower's orientation to the left
	 */
	public void turnLeft()
	{
		if (orientation.equals("N"))
		{
			orientation = "W";
		}
		else if (orientation.equals("E"))
		{
			orientation = "N";
		}
		else if (orientation.equals("S"))
		{
			orientation = "E";
		}
		else if (orientation.equals("W"))
		{
			orientation = "S";
		}
	}
	/**
	 * The mower will move forward
	 * If it goes out of the surface, it will not move
	 */
	public void moveForward()
	{
		int xTry = x;
		int yTry = y;
		if (orientation.equals("N"))
		{
			yTry += 1;
			if (yTry >= 0 && yTry <= yTerrain) y = yTry;
		}
		else if (orientation.equals("E"))
		{
			xTry += 1;
			if (xTry >= 0 && xTry <= xTerrain) x = xTry;
		}
		else if (orientation.equals("S"))
		{
			yTry -= 1;
			if (yTry >= 0 && yTry <= yTerrain) y = yTry;
			//			y = yTry;
		}
		else if (orientation.equals("W"))
		{
			xTry -= 1;
			if (xTry >= 0 && xTry <= xTerrain) x = xTry;
		}
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public String getOrientation()
	{
		return orientation;
	}
}
