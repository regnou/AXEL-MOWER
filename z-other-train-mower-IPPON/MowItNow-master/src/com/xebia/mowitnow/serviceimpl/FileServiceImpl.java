package com.xebia.mowitnow.serviceimpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xebia.mowitnow.bean.Mower;
import com.xebia.mowitnow.bean.Plan;
import com.xebia.mowitnow.service.FileService;

/**
 * Classe d'implementation du service de fichier
 * @author Stanislas Zarka
 *
 */
public class FileServiceImpl implements FileService {

	//Classe de logging
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	/**
	 * {@inheritDoc}	
	 */
	public BufferedReader openInputFile (String inputFileName) throws IOException{
		File inputFile = new File(inputFileName);
		
		//Controle d'existence du fichier
		if (!inputFile.exists()) {
            throw new IOException("Input file doesn't exist.");
        }
		else
		{
			InputStream ips = new FileInputStream(inputFile); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			return new BufferedReader(ipsr);
		}
	}
	
	/**
	 * {@inheritDoc}	
	 */
	public Plan retrievePlan (BufferedReader br) throws IOException{
		int xMax, yMax;
		//Coordonnees du plan d'etude
		String firstLine = br.readLine();
		String[] coordinates = firstLine.split(" ");
		xMax = Integer.parseInt(coordinates[0]);
		yMax = Integer.parseInt(coordinates[1]);
		return new Plan(xMax, yMax);
	}

	/**
	 * {@inheritDoc}	
	 */
	public void readInputFile(BufferedReader br, Plan plan, String outputFileName) {
		//Mouvement des tondeuses
		String line;
		try {
			while ((line = br.readLine()) != null) {
				Mower mower = new Mower();
				//Position et orientation de base de la tondeuse
				String[] mowerLine = line.split(" ");
				mower.setX(Integer.parseInt(mowerLine[0]));
				mower.setY(Integer.parseInt(mowerLine[1]));
				mower.setOrientation(mowerLine[2]);
				logger.info("DEPART : " + mower.getX() + " " + mower.getY() + " " + mower.getOrientation());
				
				makeAction(br, mower, plan, outputFileName);
			}
			br.close();
		} 
		catch (NumberFormatException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}	
	 */
	public void makeAction(BufferedReader br, Mower mower, Plan plan, String outputFileName) throws IOException {
		//Recuperation de la ligne d'action
		String actionLine = br.readLine();
		char action;
		//Decoupage par caractere
		for (int i = 0; i < actionLine.length(); i++)
		{
			action = actionLine.charAt(i);
			logger.info("Lettre : " + action);
			//En fonction de la lettre, on effectue l'action correspondante
			switch (action)
			{
				case 'G' :
					if (mower.getOrientation().equals("N"))
						mower.setOrientation("O");
					else if (mower.getOrientation().equals("E"))
						mower.setOrientation("N");
					else if (mower.getOrientation().equals("S"))
						mower.setOrientation("E");
					else if (mower.getOrientation().equals("O"))
						mower.setOrientation("S");
					break;
				case 'D' :
					if (mower.getOrientation().equals("N"))
						mower.setOrientation("E");
					else if (mower.getOrientation().equals("E"))
						mower.setOrientation("S");
					else if (mower.getOrientation().equals("S"))
						mower.setOrientation("O");
					else if (mower.getOrientation().equals("O"))
						mower.setOrientation("N");
					break;
				case 'A' :
					//On fait attention a ne pas "deborder" du plan
					if (mower.getOrientation().equals("N") && mower.getY() < plan.getyMax())
						mower.setY(mower.getY() + 1);
					//On fait attention a ne pas "deborder" du plan
					else if (mower.getOrientation().equals("E") && mower.getX() < plan.getxMax())
						mower.setX(mower.getX() + 1);
					else if (mower.getOrientation().equals("S") && mower.getY() > 0)
						mower.setY(mower.getY() - 1);
					else if (mower.getOrientation().equals("O") && mower.getX() > 0)
						mower.setX(mower.getX() - 1);
					break;
				default :
					break;
			}
		}
		logger.info("ARRIVEE : " + mower.getX() + " " + mower.getY() + " " + mower.getOrientation());
		
		//Ecriture du resultat dans le fichier de sortie
		writeOutput(outputFileName, mower);
	}
	
	/**
	 * {@inheritDoc}	
	 */
	public void writeOutput(String outputFileName, Mower mower){
		//Ouverture du fichier de sortie
		File outputFile = new File(outputFileName);
		FileWriter fw = null;
		try {
			fw = new FileWriter(outputFile, true);
			//Ecriture du flux de sortie avec la syntaxe demandee
			fw.write(mower.getX() + " " + mower.getY() + " " + mower.getOrientation() + System.getProperty("line.separator"));
			fw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
