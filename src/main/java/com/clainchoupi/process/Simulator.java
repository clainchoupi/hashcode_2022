package com.clainchoupi.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;

import com.clainchoupi.model.Client;
import com.clainchoupi.model.Clients;
import com.clainchoupi.model.Result;

public class Simulator {
	private File file;
    private Clients clients = new Clients();
	private Result result = new Result();

	public Simulator (File file) {
		this.file = file;
	}

	public void parseInput () {
		int bufferSize = 8 * 1024;
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()), bufferSize);
			String line = bufferedReader.readLine();

			//Lire ligne 1
            Integer nbClients = Integer.parseInt(line);
            clients.setClientCount(nbClients);

			//Par blocs de deux lignes, ajouter chaque client
            for (int i = 0; i < nbClients; i++) {
                Client client = new Client();

				//Lecture ligne suivante : Like
                String lineLikes = bufferedReader.readLine();
				String[] likes = lineLikes.split(" ");
				client.setNbLikes(Integer.parseInt(likes[0]));
				likes = Arrays.copyOfRange(likes, 1, likes.length);
                client.setLikes(likes);
				result.getAllLiked().addAll(Arrays.asList(likes));

				//Lecture ligne suivante : Dislike
                String lineDislikes = bufferedReader.readLine();
				String[] dislikes = lineDislikes.split(" ");
				client.setNbDislikes(Integer.parseInt(dislikes[0]));
				dislikes = Arrays.copyOfRange(dislikes, 1, dislikes.length);
                client.setDisikes(dislikes);
				result.getAllDisliked().addAll(Arrays.asList(dislikes));

				//Ajout du client en cours
				clients.addClient(client);
            }

			//Affiche que le mapping fonctionne bien
            //clients.printClients();
			//System.out.println("Liked =" +result.getAllLiked());
			//System.out.println("Disliked =" +result.getAllDisliked());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void simulate() {
		//V2 : mettre tous les liked
		Iterator<String> it = result.getAllLiked().iterator();
		while (it.hasNext()) {
			String ingredient = (String) it.next();
			result.addIngredient(ingredient);
		}

	}

	public void printOutput () {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("src/main/resources/results/"+file.getName() + ".out", "UTF-8");
			String resultString = "";

			resultString += result.getIngredients().size();

			for (String ingredient : result.getIngredients()) {
				resultString += " " + ingredient;
			}
			writer.print(resultString);
			
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
