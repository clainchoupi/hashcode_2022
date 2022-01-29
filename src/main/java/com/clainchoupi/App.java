package com.clainchoupi;

import java.io.File;

import com.clainchoupi.process.Simulator;

public class App {

    public static void main(String[] args){
        //Liste les fichiers en entrée
        File folder = new File("src/main/resources/samples/");

        //Pour chaque fichier, fait l'analyse
		for (File inputFile: folder.listFiles()) {
			Simulator simulator = new Simulator(inputFile);
			simulator.parseInput();
			simulator.simulate();
			simulator.printOutput();
			simulator.printStats();

            //break; //Permet de ne faire que le premier fichier
		}
	}
    
}
