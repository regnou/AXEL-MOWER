package fr.nunix.MowItNow.command;

import fr.nunix.MowItNow.object.MovableObject;

public class ForwardCommand extends Command {

	@Override
	public void execute(MovableObject m) {
		m.moveForward();
	}

}
