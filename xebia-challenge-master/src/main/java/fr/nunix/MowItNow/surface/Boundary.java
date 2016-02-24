package fr.nunix.MowItNow.surface;

import org.javatuples.Pair;

/**
 * Boundary provides constraints information on a Surface (rectangular)
 * An external can verify that constraints are correctly verified
 * on final destination (coordinate remains in the surface), 
 * but also that the path to reach final destination is not blocked
 * 
 * XXX Yet, the boundary does not allow coordinate system. It would be a 
 * plus in future versions ;)
 * 
 * @author gabriel
 *
 */
public interface Boundary {
	
	static final Boundary NO_LIMIT = new SimpleBoundary(new Pair<Integer, Integer>(Integer.MIN_VALUE, Integer.MIN_VALUE)
			, new Pair<Integer, Integer>(Integer.MAX_VALUE, Integer.MAX_VALUE));
	
	boolean widthMove(int i);

	boolean heightMove(int i);

	Pair<Integer, Integer> getUpperLimit();
	Pair<Integer, Integer> getLowerLimit();
}
