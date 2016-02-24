package com.xebia.mowitnow;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xebia.mowitnow.bean.Plan;
import com.xebia.mowitnow.service.FileService;
import com.xebia.mowitnow.serviceimpl.FileServiceImpl;

/**
 * Classe de lancement de l'application MowItNow
 * @author Stanislas Zarka
 * @param args
 */
public class Main {

	//Fichiers a manipuler
	private static String inputFileName = "C://temp/input.txt";
	private static String outputFileName = "C://temp/output.txt";
	
	//Classe de logging
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	//Interface de traitement des fichiers
	private static FileService fileService = new FileServiceImpl();
	
	//Methode principale
	public static void main(String[] args) throws IOException {
		Plan plan;
		
		//Leture du fichier d'entree
		BufferedReader br = fileService.openInputFile(inputFileName);
		try {
			//Creation du plan
			plan = fileService.retrievePlan(br);
			logger.info("Coordonnees du plan : (" + plan.getxMax() + "," + plan.getyMax() + ")");
			//Mouvements des tondeuses
			fileService.readInputFile(br, plan, outputFileName);
		}		
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
