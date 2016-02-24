package fr.nunix.MowItNow;

import static org.junit.Assert.*;

import org.javatuples.Pair;
import org.junit.Test;

import fr.nunix.MowItNow.surface.Boundary;
import fr.nunix.MowItNow.surface.SimpleBoundary;

public class BoundaryTest {
	
	@Test
	public void testBoundary(){
		Boundary a = new SimpleBoundary(new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(4, 4));
		
		assertTrue(a.widthMove(0));
		assertTrue(a.widthMove(1));
		assertTrue(a.widthMove(2));
		assertTrue(a.widthMove(3));
		assertTrue(a.widthMove(4));

		assertTrue(a.heightMove(0));
		assertTrue(a.heightMove(1));
		assertTrue(a.heightMove(2));
		assertTrue(a.heightMove(3));
		assertTrue(a.heightMove(4));

	}
	
	@Test
	public void testBoundaryLimit(){
		Boundary a = new SimpleBoundary(new Pair<Integer, Integer>(-1, 0), new Pair<Integer, Integer>(3, 2));
		
		assertFalse(a.widthMove(-2));
		assertFalse(a.widthMove(-10));
		assertFalse(a.widthMove(4));
		assertFalse(a.widthMove(5));
		

		assertFalse(a.heightMove(-2));
		assertFalse(a.heightMove(-1));
		assertFalse(a.heightMove(3));
		assertFalse(a.heightMove(4));


	}


}
