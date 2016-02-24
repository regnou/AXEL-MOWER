package mower;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Axel Regnoult
 * @version     0.1
 * @since      2010-04-29
 *
 * The MowerReader class parse a file to extract informations
 * and is able to detect some errors done in the file
 */
public class MowerReader {
	/**
	 * 
	 * "D:\\testFile"
	 * @param args the file containing the mower's instructions
	 */
	public static void main(String[] args)
	{
		if (args.length == 0) System.out.println("Error: filename parameter is missing !");
		else
		{
			MowerReader r = new MowerReader();
			r.readFile(args[0]);
		}
	}



	/*----------------------------------------------------------------------------------*/
	/**
	 * This file contains the mower's instructions 
	 */
	String	file						= "";
	/**
	 * The current mower 
	 */
	Mower	mower						= new Mower();
	//	int		xMax, yMax = 0;
	/**
	 * Each mower has 2 lines of instructions (true => line 1 (initial coordinates); false => line 2 (instructions))
	 */
	boolean	isLine1						= true;
	/**
	 * the file must contains at least one mower or it would be considered such as an ERROR
	 */
	boolean	isMowerInstructionFileBad	= true;



	/**
	 * Read a given initialization file and parse it in order to extract instructions
	 * @param file_ the filename 
	 */
	public void readFile(String file_)
	{
		this.file = file_;
		BufferedReader br = null;
		String line = "";
		String[] lineSplitted;
		try
		{
			br = new BufferedReader(new FileReader(file));
			// the terrain
			line = br.readLine();
			if (line == null)
			{
				System.out.println("Error: File format is not correct - the file does not contains information");
				return;
			}
			String[] tokens = line.split(" ");
			if (tokens.length == 2)
			{
				mower.initTerrain(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			}
			else
			{
				System.out.println("Error: File format is not correct - line 1");
				return;
			}
			// the mowers
			while ((line = br.readLine()) != null)
			{
				// line 1 : mower's initial coordinates
				if (isLine1)
				{
					lineSplitted = line.split(" ");
					mower.initMower(Integer.parseInt(lineSplitted[0]), Integer.parseInt(lineSplitted[1]), lineSplitted[2]);
				}
				else
				// line 2 : mower's instructions
				{
					mower.execute(line);
					//result
					System.out.println(String.valueOf(mower.getX()) + " " + String.valueOf(mower.getY()) + " " + mower.getOrientation());
					isMowerInstructionFileBad = false;
				}
				isLine1 = !isLine1;
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (isMowerInstructionFileBad) System.out.println("Error: File format is not correct - there is no mower OR it is missing the mower's instructions");
			if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
