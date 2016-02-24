package fr.nunix.MowItNow.command;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fr.nunix.MowItNow.parse.InvalidParsingLineException;

public class CommandTest {

	@Test
	public void testCommand() throws InvalidParsingLineException{
		String commands = "AAAAA";
		List<Command> c = Command.parseCommands(commands);
		assertEquals(commands.length(), c.size());
	}
	

	@Test
	public void testCommand2() throws InvalidParsingLineException{
		String commands = "DGAADGAADGAADGAAADAD";
		List<Command> c = Command.parseCommands(commands);
		assertEquals(commands.length(), c.size());
	}
	
	@Test(expected= InvalidParsingLineException.class) 
	public void testWrongCommand() throws InvalidParsingLineException{
		String commands = "XEBIA";
		Command.parseCommands(commands);
		fail("Wrong command not detected");
	}
	
	@Test(expected= InvalidParsingLineException.class) 
	public void testWrongCommand2() throws InvalidParsingLineException{
		String commands = "111";
		Command.parseCommands(commands);
		fail("Wrong command not detected");
	}
}
