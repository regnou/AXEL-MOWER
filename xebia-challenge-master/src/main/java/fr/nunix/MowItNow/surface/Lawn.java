package fr.nunix.MowItNow.surface;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.javatuples.Pair;

import fr.nunix.MowItNow.object.MovableObject;
import fr.nunix.MowItNow.parse.InvalidParsingLineException;

/**
 * Concrete surface. there is no tree, bush, plants. It's a delimitate empty lawn.
 * @author gabriel
 *
 */
public class Lawn implements Surface{

	private Set<MovableObject> movableObjects;
	private final Boundary boundary;

	public Lawn(int width, int height) throws IncorrectLawnLimitException {
		if (0 > width || 0 > height)
			throw new IncorrectLawnLimitException();
		
		this.movableObjects = new HashSet<MovableObject>();
		this.boundary = new SimpleBoundary (new Pair<Integer, Integer> (0,0), new Pair<Integer, Integer> (width,height));
	}

	/**
	 * Create a lawn from a string containing width and height dimensions
	 * 
	 * @param lawnDim
	 * @return
	 * @throws InvalidParsingLineException
	 */
	public static Lawn parseLawn(String lawnDim) throws InvalidParsingLineException {
		StringTokenizer st = new StringTokenizer(lawnDim);

		if (st.countTokens() != 2)
			throw new InvalidParsingLineException(
					"The lawn dimension has to have exactly two positive integers on a line.");

		try {
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			return new Lawn(width, height);
		} catch (Exception e) {
			throw new InvalidParsingLineException(
					"The lawn requires exactly two positive integers.");
		}

	}

	@Override
	public void notify(MovableObject object) {
		this.movableObjects.add(object);
		
	}

	@Override
	public Boundary getBoundary() {
		return this.boundary;
	}
	
	@Override
	public String toString() {
		return "(" + this.boundary.getUpperLimit().getValue0() 
				+ "," + this.boundary.getUpperLimit().getValue1() + ")";
	}
}
