package fr.nunix.MowItNow;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.nunix.MowItNow.object.MovableObject;
import fr.nunix.MowItNow.object.MovableObjectException;
import fr.nunix.MowItNow.object.Mower;
import fr.nunix.MowItNow.spatial.NotSupportedOrientationException;
import fr.nunix.MowItNow.spatial.Orientation;
import fr.nunix.MowItNow.surface.IncorrectLawnLimitException;
import fr.nunix.MowItNow.surface.InfiniteLawn;
import fr.nunix.MowItNow.surface.Lawn;
import fr.nunix.MowItNow.surface.Coordinate;
import fr.nunix.MowItNow.surface.OutOfBoundaryException;

public class CoordinateTest {
	
	@Test
	public void incrementWithBoundary () throws NotSupportedOrientationException, MovableObjectException, IncorrectLawnLimitException{
		
		Coordinate c = new Coordinate(0, 0, Orientation.SOUTH);
		MovableObject m = new Mower(c,new Lawn(0,0));
		m.moveForward();
		m.moveForward();
		m.moveForward();
		assertEquals (0, c.getX());
		assertEquals (0, c.getY());
		assertEquals (Orientation.SOUTH, c.getOrientation());
	}
	
	@Test
	public void increment () throws NotSupportedOrientationException, MovableObjectException{
		Coordinate c = new Coordinate(0, 0, Orientation.SOUTH);
		MovableObject m = new Mower(c,new InfiniteLawn());
		m.moveForward();
		m.moveForward();
		m.moveForward();
		assertEquals (0, c.getX());
		assertEquals (-3, c.getY());
		assertEquals (Orientation.SOUTH, c.getOrientation());
	}
	
	@Test
	public void roundAbout () throws NotSupportedOrientationException, MovableObjectException{
		Coordinate c = new Coordinate(4, 4, Orientation.SOUTH);
		MovableObject m = new Mower(c,new InfiniteLawn());
		m.turnLeft();
		m.moveForward();
		m.turnLeft();
		m.moveForward();
		m.turnLeft();
		m.moveForward();
		m.turnLeft();
		m.moveForward();
		assertEquals (4, c.getX());
		assertEquals (4, c.getY());
		assertEquals (Orientation.SOUTH, c.getOrientation());
	}
	
	@Test
	public void turnAndMove () throws NotSupportedOrientationException, MovableObjectException{
		Coordinate c = new Coordinate(1, 3, Orientation.WEST);
		MovableObject m = new Mower(c,new InfiniteLawn());
		m.turnRight();
		m.moveForward();
		assertEquals (1, c.getX());
		assertEquals (4, c.getY());
		assertEquals (Orientation.NORTH, c.getOrientation());
	}
	

	@Test
	public void rotateRight () throws NotSupportedOrientationException, MovableObjectException{
		Coordinate c = new Coordinate(0, 0, Orientation.NORTH);

		MovableObject m = new Mower(c,new InfiniteLawn());
		m.turnRight();
		assertEquals (Orientation.EAST, c.getOrientation());
		m.turnRight();
		assertEquals (Orientation.SOUTH, c.getOrientation());
		m.turnRight();
		assertEquals (Orientation.WEST, c.getOrientation());
		m.turnRight();
		assertEquals (Orientation.NORTH, c.getOrientation());
	}
	
	@Test
	public void rotateLeft () throws NotSupportedOrientationException, MovableObjectException{
		Coordinate c = new Coordinate(0, 0, Orientation.NORTH);

		MovableObject m = new Mower(c,new InfiniteLawn());
		m.turnLeft();
		assertEquals (Orientation.WEST, c.getOrientation());
		m.turnLeft();
		assertEquals (Orientation.SOUTH, c.getOrientation());
		m.turnLeft();
		assertEquals (Orientation.EAST, c.getOrientation());
		m.turnLeft();
		assertEquals (Orientation.NORTH, c.getOrientation());
	}
	
	@Test(expected=OutOfBoundaryException.class)
	public void outofboundary () throws MovableObjectException, IncorrectLawnLimitException{
		Coordinate c = new Coordinate(5, 6, Orientation.NORTH);
		new Mower(c,new Lawn(5,5));
	}


}
