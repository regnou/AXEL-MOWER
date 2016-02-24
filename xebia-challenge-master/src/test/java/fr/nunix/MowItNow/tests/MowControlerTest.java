package fr.nunix.MowItNow.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.javatuples.Pair;
import org.junit.Test;

import fr.nunix.MowItNow.command.Command;
import fr.nunix.MowItNow.object.MovableObjectException;
import fr.nunix.MowItNow.object.Mower;
import fr.nunix.MowItNow.parse.Instruction;
import fr.nunix.MowItNow.parse.InvalidParsingLineException;
import fr.nunix.MowItNow.spatial.Orientation;
import fr.nunix.MowItNow.surface.Coordinate;
import fr.nunix.MowItNow.surface.OutOfBoundaryException;

public class MowControlerTest {

	//XXX externalize configuration
	static final String finalTest = new StringBuilder()
    .append("5 5\n")
    .append("1 2 N\n")
    .append("GAGAGAGAA\n")
    .append("3 3 E\n")
    .append("AADAADADDA").toString();
	
	static final String finalTest2 = new StringBuilder()
    .append("1 1\n")
    .append("1 2 N\n")
    .append("GAGAGAGAA\n")
    .append("3 3 E\n")
    .append("AADAADADDA").toString();

     
	@Test
	public void xebiaSet() throws InvalidParsingLineException, MovableObjectException, IOException {
		Instruction instruction = new Instruction(new StringReader(finalTest));
		List<Coordinate> lc = new ArrayList<Coordinate>();
		lc.add(new Coordinate(1, 3, Orientation.NORTH));
		lc.add(new Coordinate(5, 1, Orientation.EAST));
		checkInstruction(instruction, lc);
	}
	
	@Test(expected=OutOfBoundaryException.class)
	public void tooSmallLawn() throws InvalidParsingLineException, MovableObjectException, IOException {
		Instruction instruction = new Instruction(new StringReader(finalTest2));
		List<Coordinate> lc = new ArrayList<Coordinate>();
		lc.add(new Coordinate(1, 3, Orientation.NORTH));
		lc.add(new Coordinate(5, 1, Orientation.EAST));
		checkInstruction(instruction, lc);
	}
		
		
	public void checkInstruction(Instruction instruction, final List<Coordinate> finalPositions){
		
	
		assertNotNull(instruction.getLawn());
		assertEquals(2,  instruction.getMows().size());
		System.out.println("Extracted Lawn : " + instruction.getLawn());
		
		int pos = 0;
		for (Pair<Mower, List<Command>> pair : instruction.getMows()){
			
			Mower m = pair.getValue0();
			assertNotNull(m);
			
			System.out.println("--- Mow : " + m);
			List<Command> commands = pair.getValue1();
			final Coordinate expectedCoordinate = finalPositions.get(pos);
			m.addObserver(new Observer() {
				
				@Override
				public void update(Observable o, Object arg) {
					assertTrue(arg instanceof Coordinate);
					assertEquals(expectedCoordinate, arg);
					if (arg instanceof Coordinate)
						System.out.println("Final coordinate for mow " + o + " : " + arg);
				
				}
			});
			System.out.println("... execute " + commands.size() + " commands.");
			m.execute(commands);
			++pos;

		}
		
	}

}
