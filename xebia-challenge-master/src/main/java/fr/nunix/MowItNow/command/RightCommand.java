package fr.nunix.MowItNow.command;

import fr.nunix.MowItNow.object.MovableObject;

public class RightCommand extends Command {

	@Override
	public void execute(MovableObject m) {
		m.turnRight();
	}

}
