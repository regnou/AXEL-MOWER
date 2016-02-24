package com.xebia.mowitnow.service;

import java.io.BufferedReader;
import java.io.IOException;

import com.xebia.mowitnow.bean.Mower;
import com.xebia.mowitnow.bean.Plan;

/**
 * Interface du Service de fichier
 * @author Stanislas Zarka
 *
 */
public interface FileService {

	/**
	 * Ouvre le fichier d'entree
	 * @param inputFileName
	 * 		nom du fichier d'entree
	 * @return le buffer associe au fichier
	 * @throws IOException
	 */
	public BufferedReader openInputFile (String inputFileName) throws IOException;
	
	/**
	 * Recupere le plan (coordonnees maximales) d'action de la tondeuse
	 * @param br
	 * 		buffer d'entree
	 * @return le plan associe
	 * @throws IOException
	 */
	public Plan retrievePlan (BufferedReader br) throws IOException;

	/**
	 * Lecture du fichier avec appel aux actions correspondantes
	 * @param br
	 * 		buffer d'entre
	 * @param plan
	 * 		plan d'action de la tondeuse
	 * @param outputFileName
	 * 		nom du fichier de sortie
	 */
	public void readInputFile(BufferedReader br, Plan plan, String outputFileName);

	/**
	 * Action du mouvement d'une tondeuse
	 * @param br
	 * 		buffer d'entre
	 * @param plan
	 * 		plan d'action de la tondeuse
	 * @param outputFileName
	 * 		nom du fichier de sortie
	 * @throws IOException
	 */
	public void makeAction(BufferedReader br, Mower mower, Plan plan, String outputFileName) throws IOException;
	
	/**
	 * Ecriture dans le fichier de sortie
	 * @param outputFileName
	 * 		nom du fichier de sortie
	 * @param mower
	 * 		tondeuse a serialiser
	 */
	public void writeOutput (String outputFileName, Mower mower);
	
}
