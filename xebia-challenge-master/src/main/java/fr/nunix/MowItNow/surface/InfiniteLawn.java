package fr.nunix.MowItNow.surface;

import fr.nunix.MowItNow.object.MovableObject;

/**
 * Concrete surface. there is no tree, bush, plants. It's a delimitate empty lawn.
 * @author gabriel
 *
 */
public class InfiniteLawn implements Surface{

	private final Boundary boundary;

	public InfiniteLawn() {
		
		this.boundary = Boundary.NO_LIMIT;
	}

	@Override
	public void notify(MovableObject object) {
		
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
