package fr.nunix.MowItNow.surface;

import fr.nunix.MowItNow.object.MovableObject;

/**
 * Rectangular surface in which movable objects can "live"
 * @author gabriel
 *
 */
public interface Surface {
	
	/**
	 * A movable object can notify the surface to let it know it moves on his ground.
	 * @param object
	 */
	void notify (MovableObject object);

	/**
	 * A surface has boundaries and constraints
	 * @return
	 */
	Boundary getBoundary();
	
}
