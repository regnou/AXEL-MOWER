package fr.nunix.MowItNow.surface;

import org.javatuples.Pair;

/**
 * This is the implementation of a simple boundary for rectangular surface:
 * the lower limits are coordinate (0,0), and upper limit is indicated by (x,y)
 * 
 * We do not track particular constraints such as objects in a move, nor implement
 * strategy to move in the surface (straight movement) 
 * 
 * @author gabriel
 *
 */
public class SimpleBoundary implements Boundary {

	
	public SimpleBoundary(Pair<Integer, Integer> lowerLimit, Pair<Integer, Integer> upperLimit) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
	}
	
	final protected Pair<Integer,Integer> lowerLimit;
	final protected Pair<Integer, Integer> upperLimit;
	
	@Override
	public boolean widthMove(int i) {
		if (i >= lowerLimit.getValue0() && i <= upperLimit.getValue0())
			return true;
		
		return false;
	}
	
	@Override
	public boolean heightMove(int i) {
		if (i >= lowerLimit.getValue1() && i <= upperLimit.getValue1())
			return true;
		
		return false;
	}

	@Override
	public Pair<Integer, Integer> getUpperLimit() {
		return this.upperLimit;
	}

	@Override
	public Pair<Integer, Integer> getLowerLimit() {
		return this.lowerLimit;
	}
	
}
