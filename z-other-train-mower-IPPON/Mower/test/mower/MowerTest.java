package mower;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Axel Regnoult
 * @version     0.1
 *  @since      2010-04-29
 */
public class MowerTest {
	/**
	 * Test a 0,0 surface
	 */
	@Test public final void zeroSurface()
	{
		Mower m = new Mower();
		m.orientation = "N";
		m.xTerrain = 0;
		m.yTerrain = 0;
		m.moveForward();
		assertEquals("(0,0)", 0, m.x);
		assertEquals("(0,0)", 0, m.y);
	}
	/**
	 * Complex path test (verificated by hand on paper)
	 */
	@Test public final void testComplicatedPath()
	{
		Mower m1 = new Mower(10, 10, 0, 0, "N", "AAADAGAAGAADAAAAAADAAAAAAAAAAA");
		m1.execute();
		assertEquals("x = 10", 10, m1.getX());
		assertEquals("y = 10", 10, m1.getY());
		assertEquals("o = E", "E", m1.getOrientation());
		
		Mower m2 = new Mower(10, 10, 5, 5, "N", "AGAGAAGAAGAAAGAAAGAAAAGAAAGAAAAAAADAAAA");
		m2.execute();
		assertEquals("x = 10", 10, m2.getX());
		assertEquals("y = 10", 10, m2.getY());
		assertEquals("o = E", "E", m2.getOrientation());
	}
	/**
	 * Perimeter Tests
	 */
	@Test public final void testOutSurface()
	{
		Mower m = new Mower();
		m.orientation = "N";
		m.xTerrain = 5;
		m.yTerrain = 5;
		m.x = 0;
		m.y = 0;
		// pas de sortie du terrain
		m.turnLeft();
		m.moveForward();
		assertEquals("(0,0)", 0, m.x);
		assertEquals("(0,0)", 0, m.y);
		m.turnRight();
		m.moveForward();
		m.moveForward();
		m.moveForward();
		m.moveForward();
		m.moveForward();
		assertEquals("(0,0)", 0, m.x);
		assertEquals("(0,5)", 5, m.y);
		// go out of the surface (N)
		m.moveForward();
		m.moveForward();
		assertEquals("(0,5)", 0, m.x);
		assertEquals("(0,5)", 5, m.y);
		// let s go to touch the right corner limit
		m.turnRight();
		m.moveForward();
		m.moveForward();
		m.moveForward();
		m.moveForward();
		m.moveForward();
		assertEquals("(5,5)", 5, m.x);
		assertEquals("(5,5)", 5, m.y);
		// go out of the surface (E)
		m.moveForward();
		m.moveForward();
		assertEquals("(5,5)", 5, m.x);
		assertEquals("(5,5)", 5, m.y);
	}
	/**
	 * Verification of the turnRight instruction
	 */
	@Test public final void testTurnRight()
	{
		Mower m = new Mower();
		m.orientation = "N";
		m.turnRight();
		assertEquals("N d E", m.orientation, "E");
		m.orientation = "E";
		m.turnRight();
		assertEquals("E d S", m.orientation, "S");
		m.orientation = "S";
		m.turnRight();
		assertEquals("S d W", m.orientation, "W");
		m.orientation = "W";
		m.turnRight();
		assertEquals("W d N", m.orientation, "N");
	}
	/**
	 * Verification of the turnLeft instruction
	 */
	@Test public final void testTurnLeft()
	{
		Mower m = new Mower();
		m.orientation = "N";
		m.turnLeft();
		assertEquals("N g W", m.orientation, "W");
		m.orientation = "E";
		m.turnLeft();
		assertEquals("E g N", m.orientation, "N");
		m.orientation = "S";
		m.turnLeft();
		assertEquals("S g E", m.orientation, "E");
		m.orientation = "W";
		m.turnLeft();
		assertEquals("W d S", m.orientation, "S");
	}
	/**
	 * Verification of the moveForward instruction
	 */
	@Test public final void testMoveForward()
	{
		Mower m = new Mower();
		m.orientation = "N";
		m.xTerrain = 5;
		m.yTerrain = 5;
		m.x = 1;
		m.y = 2;
		// pas de sortie du terrain
		m.turnLeft();
		m.moveForward();
		assertEquals("(0,2)", 0, m.x);
		assertEquals("(0,2)", 2, m.y);
		m.turnLeft();
		m.moveForward();
		assertEquals("(0,1)", 0, m.x, 0);
		assertEquals("(0,1)", 1, m.y, 1);
		m.turnLeft();
		m.moveForward();
		assertEquals("(1,1)", 1, m.x, 1);
		assertEquals("(1,1)", 1, m.y, 1);
		m.turnLeft();
		m.moveForward();
		assertEquals("(1,2)", 1, m.x);
		assertEquals("(1,2)", 2, m.y);
		m.moveForward();
		assertEquals("(1,3)", 1, m.x);
		assertEquals("(1,3)", 3, m.y);
	}
}
