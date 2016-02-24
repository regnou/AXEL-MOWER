package fr.nunix.MowItNow;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.nunix.MowItNow.spatial.Orientation;

public class OrientationTest {

	public OrientationTest(){
	}
	
	
	
	@Test
	public void orientationLeftRight() {
		
		assertEquals(Orientation.EAST, Orientation.EAST.left().right());

		assertEquals(Orientation.SOUTH, Orientation.SOUTH.left().right());

		assertEquals(Orientation.NORTH, Orientation.NORTH.left().right());

		assertEquals(Orientation.WEST, Orientation.WEST.left().right());
		
		
	}
	
	
	@Test
	public void orientationRight() {
		
		assertEquals(Orientation.EAST, Orientation.NORTH.right());

		assertEquals(Orientation.SOUTH, Orientation.EAST.right());

		assertEquals(Orientation.NORTH, Orientation.WEST.right());

		assertEquals(Orientation.WEST, Orientation.SOUTH.right());
		
	}
	

	@Test
	public void orientationLeft() {
		
		assertEquals(Orientation.EAST.left(), Orientation.NORTH);

		assertEquals(Orientation.SOUTH.left(), Orientation.EAST);

		assertEquals(Orientation.NORTH.left(), Orientation.WEST);

		assertEquals(Orientation.WEST.left(), Orientation.SOUTH);
		
	}
	
	@Test
	public void orientationMultipleLeft() {
		assertEquals(Orientation.WEST, Orientation.EAST.left().left());
		assertEquals(Orientation.SOUTH, Orientation.EAST.left().left().left());
		assertEquals(Orientation.EAST, Orientation.EAST.left().left().left().left());
	}
	
	@Test
	public void orientationMisc() {
		assertNotEquals(Orientation.NORTH, Orientation.SOUTH.left());
		assertNotEquals(Orientation.WEST, Orientation.SOUTH.left().left());
		assertNotEquals(Orientation.SOUTH, Orientation.SOUTH.left().left().left());
		assertNotEquals(Orientation.EAST, Orientation.SOUTH.left().left().left().left());
		assertEquals(Orientation.SOUTH, Orientation.EAST.left().right().right());
		
	}
	
	

}
