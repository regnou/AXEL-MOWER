package fr.nunix.MowItNow.surface;

import java.util.StringTokenizer;

import fr.nunix.MowItNow.parse.InvalidParsingLineException;
import fr.nunix.MowItNow.spatial.Orientation;

/**
 * Indicate position of a movableobject on a field
 * 
 * @author gabriel
 *
 */
public class Coordinate {

	private int x;
	private int y;
	private Orientation orientation;

	public Coordinate(int x, int y, Orientation o) {
		this.x = x;
		this.y = y;
		this.orientation = o;
	}
	

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Orientation getOrientation(){
		return this.orientation;
	}
	
	@Override 
	public int hashCode() {
		int result = 11;
		
		result = 17 * result + this.x;
		result = 31 * result + this.y;

		result = 13 * result + this.orientation.hashCode();
		
		return result;
	};
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Coordinate){
			Coordinate c = (Coordinate) o;
			if (this.x == c.x && this.y == c.y && this.orientation == c.orientation)
				return true;
		}
		
		return false;
	}

	public static Coordinate parseCoordinate(String mowPos) throws InvalidParsingLineException {

		StringTokenizer st = new StringTokenizer(mowPos);

		if (st.countTokens() != 3)
			throw new InvalidParsingLineException(
					"The mow position has to have exactly two integers and one char on a line.");

		try {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Orientation o = Orientation.parseOrientation(st.nextToken());

			return new Coordinate(x, y, o);
		} catch (NumberFormatException e) {
			throw new InvalidParsingLineException(
					"The mow requires exactly two integers and one character to indicate the position.");
		}
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append("(")
			.append(this.x)
			.append(",")
			.append(this.y)
			.append(",")
			.append(this.orientation)
			.append(")").toString();
			
	}


	/** 
	 * update both (x,y) coordinates
	 * @param x2
	 * @param y2
	 */
	public void update(int x2, int y2) {
		this.x = x2;
		this.y = y2;
		
	}


	public void setOrientation(Orientation o) {
		this.orientation = o;
		
	}
}
