package fr.nunix.MowItNow.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.javatuples.Pair;

import fr.nunix.MowItNow.command.Command;
import fr.nunix.MowItNow.object.MovableObjectException;
import fr.nunix.MowItNow.object.Mower;
import fr.nunix.MowItNow.surface.Coordinate;
import fr.nunix.MowItNow.surface.Lawn;

/**
 * This class abstracts the interpretation of input files
 * 
 * @author gabriel
 *
 */
public class Instruction {
	
	/**
	 * Instruction is, by convention
	 * 
	 * One line with the lawn dimension e.g, 5 6
	 * A set of two consecutive lines containing definition and configuration for mows:
	 * 		* initial position of the mow e.g, 1 3 N
	 * 		* list of command to turn and forward in the lawn e.g, GADGDGDA.
	 * 
	 * 
	 * @param instructions
	 * @throws InvalidParsingLineException 
	 * @throws MovableObjectException 
	 * @throws Exception 
	 */
	public Instruction(Reader reader) throws InvalidParsingLineException{
		
		BufferedReader bf = new BufferedReader(reader);
		List<Pair<Mower, List<Command>>> mows = new ArrayList<Pair<Mower,List<Command>>>();
		
		try {
			String lawnDim = bf.readLine();
			lawn = Lawn.parseLawn(lawnDim);
			
			String line;
			while (null != (line = bf.readLine())){
				String mowPos = line;
				String mowInst = bf.readLine();
				
				Mower m = Mower.parseMow(mowPos, lawn);
				List<Command> commands = Command.parseCommands(mowInst);
				
				Pair<Mower, List<Command>> mowInstruction = new Pair<Mower, List<Command>>(m, commands);
				mows.add(mowInstruction);
			}
			
			this.mows = mows;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InvalidParsingLineException("Error while reading the lines");
		} catch (MovableObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InvalidParsingLineException("Error while parsing the lines. Make sure that lawn is existing and mow has coordinates.");
		}
		
	}
	
	public Lawn getLawn() {
		return lawn;
	}
	public List<Pair<Mower, List<Command>>> getMows() {
		return mows;
	}

	final private Lawn lawn;
	final private List<Pair<Mower, List<Command>>> mows;
	
	public final static void main (String args[]) throws InvalidParsingLineException{
		
		final String finalTest = new StringBuilder()
	    .append("5 5\n")
	    .append("1 2 N\n")
	    .append("GAGAGAGAA\n")
	    .append("3 3 E\n")
	    .append("AADAADADDA").toString();
	
		Instruction instruction = new Instruction(new StringReader(finalTest));

		System.out.println("Extracted Lawn : " + instruction.getLawn());
		for (Pair<Mower, List<Command>> pair : instruction.getMows()){

			Mower m = pair.getValue0();
			System.out.println("--- Mow : " + m);
			List<Command> commands = pair.getValue1();
			
			m.addObserver(new Observer() {
				
				@Override
				public void update(Observable o, Object arg) {
					if (arg instanceof Coordinate)
						System.out.println("Final coordinate for mow " + o + " : " + arg);
				
				}
			});
			System.out.println("... execute " + commands.size() + " commands.");
			m.execute(commands);
		}
	}

}
