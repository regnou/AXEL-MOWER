package fr.nunix.MowItNow.spatial;

import fr.nunix.MowItNow.controler.LetterConvention;
import fr.nunix.MowItNow.parse.InvalidParsingLineException;

/**
 * Another possibility was to manage orientation given the degree angle, ranging
 * from 0 to 360
 * 
 * @author gabriel
 * 
 */
public enum Orientation {

	NORTH {
		@Override
		public Orientation left() {
			return WEST;
		}

		@Override
		public Orientation right() {
			return EAST;
		}
	},
	EAST {
		@Override
		public Orientation left() {
			return NORTH;
		}

		@Override
		public Orientation right() {
			return SOUTH;
		}
	},
	SOUTH {
		@Override
		public Orientation left() {
			return EAST;
		}

		@Override
		public Orientation right() {
			return WEST;
		}
	},
	WEST {
		@Override
		public Orientation left() {
			return SOUTH;
		}

		@Override
		public Orientation right() {
			return NORTH;
		}
	},
	;

	abstract public Orientation left();

	abstract public Orientation right();
	
	public static Orientation parseOrientation(String st) throws InvalidParsingLineException{
		if (st.equals(LetterConvention.NORTH))
			return NORTH;
		if (st.equals(LetterConvention.SOUTH))
			return SOUTH;
		if (st.equals(LetterConvention.EAST))
			return EAST;
		if (st.equals(LetterConvention.WEST))
			return WEST;
		
		throw new InvalidParsingLineException("The letter '" + st + "' is not recognized as a valid orientation");
	}

}
