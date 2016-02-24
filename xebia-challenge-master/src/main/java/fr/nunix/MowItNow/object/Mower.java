package fr.nunix.MowItNow.object;

import java.util.List;
import java.util.Observable;

import fr.nunix.MowItNow.command.Command;
import fr.nunix.MowItNow.parse.InvalidParsingLineException;
import fr.nunix.MowItNow.surface.OutOfBoundaryException;
import fr.nunix.MowItNow.surface.Coordinate;
import fr.nunix.MowItNow.surface.Surface;

public class Mower extends Observable implements MovableObject{
	
	/**
	 * current position of the mow
	 */
	private Coordinate position;
	
	/**
	 * surface in which the mow moves
	 */
	private Surface surface; 

	/**
	 * 
	 * @param c
	 * @param s
	 * @throws MovableObjectException A movable object has to be placed on a surface, with definite coordinate. If one of the argument is null, this expcetion is thrown
	 */
	public Mower(Coordinate c, Surface s) throws MovableObjectException {
		init(s,c);
	}

	public void moveForward() {
		int x = position.getX();
		int y = position.getY();
		switch (position.getOrientation()) {
		case EAST:
			if (surface.getBoundary().widthMove(x+1))
				x += 1;
			break;
		case NORTH:
			if (surface.getBoundary().heightMove(y+1))
				y += 1;
			
			break;
		case WEST:
			if (surface.getBoundary().widthMove(x-1))
				x -= 1;
			break;
		case SOUTH:
			if (surface.getBoundary().heightMove(y-1))
				y -= 1;
			break;
		}
		
		position.update(x,y);
		
	}


	public void turnRight() {
		position.setOrientation(position.getOrientation().right());
	}

	public void turnLeft() {
		position.setOrientation(position.getOrientation().left());
	}

	/**
	 * 
	 * @return the position which comprises (x,y,orientation) in the 2D lawn
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * Create a mow from a string containing x and y positions
	 * 
	 * @param mowPos
	 * @return
	 * @throws InvalidParsingLineException
	 * @throws MovableObjectException 
	 */
	public static Mower parseMow(String mowPos, Surface s) throws InvalidParsingLineException, MovableObjectException {
		
		Coordinate c = Coordinate.parseCoordinate(mowPos);
		return new Mower(c, s);

	}

	/**
	 * Execute a bunch of commands on the mow,
	 * and trigger the 'end' function
	 * 
	 * @param commands
	 */
	@Override
	public void execute(List<Command> commands) {

		for (Command c: commands)
			c.execute(this);
		
		end();
		
	}
	
	@Override
	public void end (){
		Coordinate position = getPosition();
		setChanged();
		notifyObservers(position);
	}

	@Override
	public void attach(Surface surface) throws MovableObjectException {
		attach(surface, position);
	}

	@Override
	public void attach(Surface surface, Coordinate initial) throws MovableObjectException {
		init (surface, initial);
		
	}
	
	/**
	 * Attach a surface to the mow, and set the boundary limit to the current internal plan.
	 * We notify the surface that we are in its plan as well.
	 * @param s
	 * @param position
	 * @throws MovableObjectException
	 */
	private void init (Surface s, Coordinate position) throws MovableObjectException{
		if (null == position)
			throw new MovableObjectException ("Coordinate can't be null");
		if (null == s)
			throw new MovableObjectException ("Surface can't be null");
		
		if (!(s.getBoundary().heightMove(position.getY()) 
				&& s.getBoundary().widthMove(position.getX())))
			throw new OutOfBoundaryException("The movable object is out of the surface boundaries. Please enlarge the surface or choose different initial position");
		
		
		this.position = position;
		this.surface = s;
		this.surface.notify(this);
	}
	
	@Override
	public String toString() {
		return "Mow@" + surface.toString() + " " + position.toString();
	}
	


}
